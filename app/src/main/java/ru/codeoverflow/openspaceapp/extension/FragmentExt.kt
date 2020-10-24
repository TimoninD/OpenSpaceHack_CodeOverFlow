package ru.codeoverflow.openspaceapp.extension

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.codeoverflow.openspaceapp.ui.MainActivity


fun Fragment.setTitle(title: String) {
    if (requireActivity() is MainActivity) {
        requireActivity().toolbar.title = title
    }
}