package ru.codeoverflow.openspaceapp.ui.fragment.scanner

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.FLASH_MODE_OFF
import androidx.camera.core.ImageCapture.FLASH_MODE_ON
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_scanner.*
import kotlinx.android.synthetic.main.layout_scanner_check_info.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.util.LuminosityAnalyzer
import ru.codeoverflow.openspaceapp.viewmodel.scanner.ScannerViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ScannerFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_scanner

    private val vm: ScannerViewModel by viewModel()

    private var imageCapture: ImageCapture? = null

    private val args: ScannerFragmentArgs by navArgs()
 
    private val errorToast by lazy {
        Toast.makeText(requireContext(), R.string.default_error, Toast.LENGTH_LONG)
    }

    private val outputDirectoryFile: File by lazy {
        getOutputDirectory()
    }
    private val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        ivTakePhoto.setOnClickListener { takePhoto() }
        ivFlash.setOnClickListener {
            ivFlash.isActivated = !ivFlash.isActivated
            imageCapture?.flashMode =
                if (imageCapture?.flashMode == FLASH_MODE_ON) {
                    FLASH_MODE_OFF
                } else {
                    FLASH_MODE_ON
                }
        }

        vm.meterResult.observe(viewLifecycleOwner) { meterModel ->
            if (meterModel.value != null) {
                bgDescription.isVisible = false
                tvDescription.isVisible = false
                layoutConfirm.isVisible = true
                ivTakePhoto.isEnabled = false

                layoutConfirm.etIndication.setText(meterModel.value)

                layoutConfirm.btnNext.setOnClickListener {
                    if (args.meter != null) {
                        args.meter?.let {
                            vm.editMeter(
                                addressId = args.addressId.orEmpty(),
                                meterModel = it.copy(value = layoutConfirm.etIndication.text.toString())
                            )
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Фича находится в доработке, данные: serialNumber:${meterModel.serialNumber} value:${meterModel.value}",
                            Toast.LENGTH_SHORT
                        ).show()

                        bgDescription.isVisible = true
                        tvDescription.isVisible = true
                        layoutConfirm.isVisible = false
                        ivTakePhoto.isEnabled = true
                    }
                }

                layoutConfirm.btnTryAgain.setOnClickListener {
                    bgDescription.isVisible = true
                    tvDescription.isVisible = true
                    layoutConfirm.isVisible = false
                    ivTakePhoto.isEnabled = true
                }
            } else {
                Toast.makeText(requireContext(), R.string.ml_error, Toast.LENGTH_SHORT).show()
            }
        }
        vm.meterEditResult.observe(viewLifecycleOwner) {
            ivTakePhoto.isEnabled = true
            findNavController().popBackStack()
        }

        vm.isLoading.observe(viewLifecycleOwner) {
            pbLoading.isVisible = it
        }
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = File(
            outputDirectoryFile,
            SimpleDateFormat(
                FILENAME_FORMAT, Locale.getDefault()
            ).format(System.currentTimeMillis()) + ".jpg"
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    errorToast.show()
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    vm.postMeterImage(photoFile)
                }
            })
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener(Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()
            imageCapture?.flashMode = FLASH_MODE_ON
            ivFlash.isActivated = true
            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, LuminosityAnalyzer { })
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture, imageAnalyzer
                )
            } catch (exc: Exception) {
                errorToast.show()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }


    private fun getOutputDirectory(): File {
        val mediaDir = activity?.externalMediaDirs?.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else requireActivity().filesDir
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}