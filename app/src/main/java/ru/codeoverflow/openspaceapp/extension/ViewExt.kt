package ru.codeoverflow.openspaceapp.extension

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding

fun View.updatePaddingTopOnApplySystemWindowInsets(
    offset: Int = 0,
    vararg applyTo: View? = arrayOf(this)
) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        applyTo.forEach { v ->
            v?.updatePadding(
                top = insets.systemWindowInsetTop + offset
            )
        }
        insets
    }
}