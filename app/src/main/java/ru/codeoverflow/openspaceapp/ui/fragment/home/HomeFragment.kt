package ru.codeoverflow.openspaceapp.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.di.getNavScopeName
import ru.codeoverflow.openspaceapp.di.navScopeScopeName
import ru.codeoverflow.openspaceapp.extension.removePaddingFromNavigationItem
import ru.codeoverflow.openspaceapp.extension.setupWithNavController
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.ui.navigation.NavControllerNavigator
import java.util.HashSet

class HomeFragment : BaseFragment() {

    override val layoutResId = R.layout.fragment_home

    private var currentNavController: LiveData<NavController>? = null

    private var navScopesIds: HashSet<String> = hashSetOf()

    private val listWithoutToolbar =
        listOf(
            R.id.addressFragment,
            R.id.scannerFragment,
            R.id.statisticFragment,
            R.id.settingsFragment
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        clHome.setOnApplyWindowInsetsListener { clHome, insets ->
            clHome.updatePadding(top = insets.systemWindowInsetTop)
            return@setOnApplyWindowInsetsListener insets
        }

        bottomMenu.setOnApplyWindowInsetsListener { view, insets ->
            view.updatePadding(bottom = insets.systemWindowInsetBottom)
            insets
        }

        toolbar.setNavigationOnClickListener {
            currentNavController?.value?.popBackStack()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        bottomMenu.removePaddingFromNavigationItem()

        val navGraphIds = listOf(
            R.navigation.address,
            R.navigation.scanner,
            R.navigation.statistic,
            R.navigation.settings
        )

        val controller = bottomMenu.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.homeNavHostContainer,
            intent = requireActivity().intent
        )

        controller.observe(viewLifecycleOwner, Observer { navController ->

            currentNavController?.value?.addOnDestinationChangedListener { controller, destination, arguments ->
                toolbar.isVisible = !listWithoutToolbar.contains(destination.id)
                val navScopeId = getNavScopeName(controller.graph.label ?: "")

                val navigatorScope =
                    getKoin().getOrCreateScope(navScopeId, named(navScopeScopeName))
                navigatorScope.get<NavControllerNavigator>().navController = controller
                navScopesIds.add(navScopeId)
            }

        })

        currentNavController = controller
    }

    override fun onDestroy() {
        navScopesIds.forEach {
            getKoin().deleteScope(it)
        }
        super.onDestroy()
    }

    override fun onBackPressed(): Boolean {
        val needActivityHandleBackPressed = currentNavController?.value?.navigateUp() ?: true
        return !needActivityHandleBackPressed
    }
}