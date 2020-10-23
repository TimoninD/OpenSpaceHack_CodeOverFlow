package ru.codeoverflow.openspaceapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.model.storage.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import ru.codeoverflow.openspaceapp.di.globalNavScopeId
import ru.codeoverflow.openspaceapp.extension.updatePaddingTopOnApplySystemWindowInsets
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment


class MainActivity : AppCompatActivity() {

    private val prefs: Prefs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = mainActivityNavHostFragment as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.main)
        val navController = navHostFragment.navController

        when {

            prefs.isFirstLaunch -> {
                /*prefs.isFirstLaunch = false
                navGraph.startDestination = R.id.welcomeContainerFragment
                navController.graph = navGraph*/
                navController.graph = navGraph
            }
            prefs.token == null -> {

            }
            else -> {
                /*navGraph.startDestination = R.id.homeFragment
                navController.graph = navGraph*/
            }
        }

        mainActivityNavHostFragment.findNavController()
            .addOnDestinationChangedListener { controller, destination, arguments ->

            }
    }

    override fun onStop() {
        getKoin().deleteScope(globalNavScopeId)
        super.onStop()
    }

    override fun onBackPressed() {
        if (mainActivityNavHostFragment.findNavController().currentDestination?.id == R.id.homeFragment) {
            if ((mainActivityNavHostFragment.childFragmentManager.fragments[0] as BaseFragment).onBackPressed()) {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }


}