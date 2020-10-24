package ru.codeoverflow.openspaceapp.extension

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.codeoverflow.openspaceapp.ui.MainActivity
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


fun Fragment.setTitle(title: String) {
    if (requireActivity() is MainActivity) {
        requireActivity().toolbar.title = title
    }
}

fun Fragment.hideKeyboard() {
    val imm: InputMethodManager =
        activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view: View? = activity?.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}