package ru.codeoverflow.openspaceapp.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

class NavControllerNavigator : Navigator {

    lateinit var navController: NavController

    override fun to(directions: NavDirections) = navController.navigate(directions)
    override fun back() = navController.navigateUp()
    override fun backTo(destinationId: Int, inclusive: Boolean) =
        navController.popBackStack(destinationId, inclusive)
}