package app.cntandroidver2.workshop

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import app.cntandroidver2.workshop.ui.tab1.Tab1Fragment
import app.cntandroidver2.workshop.ui.tab2.Tab2Fragment
import app.cntandroidver2.workshop.ui.tab3.Tab3Fragment
import app.cntandroidver2.workshop.ui.tab4.Tab4Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        centerToolbarTitle(toolbar)

//        val navView: BottomNavigationView = findViewById(R.id.nav_view)

//        val navController = findNavController(R.id.nav_host_fragment)
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_tab1,
//                R.id.navigation_tab2,
//                R.id.navigation_tab3,
//                R.id.navigation_tab4
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        nav_view.itemIconTintList = null

        nav_view.setOnNavigationItemSelectedListener(bottomNavClicked)

        defaultIcon()
        nav_view.menu.getItem(0).setIcon(R.drawable.fx_select)
        changeFragment(Tab1Fragment.newInstance())

        toolbar?.setTitle(R.string.title_tab1)


    }

    fun centerToolbarTitle(toolbar: Toolbar) {
        val title: CharSequence = toolbar.getTitle()
        val outViews: ArrayList<View> = ArrayList(1)
        toolbar.findViewsWithText(outViews, title, View.FIND_VIEWS_WITH_TEXT)
        if (!outViews.isEmpty()) {
            val titleView = outViews[0] as TextView
            titleView.gravity = Gravity.CENTER
            val layoutParams: Toolbar.LayoutParams =
                titleView.layoutParams as Toolbar.LayoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            toolbar.requestLayout()
        }
    }

    private fun initListener() {


    }

    val bottomNavClicked = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {

            defaultIcon()

            when (item.itemId) {
                R.id.navigation_tab1 -> {
                    item.setIcon(R.drawable.fx_select)
                    changeFragment(Tab1Fragment.newInstance())
                    toolbar.setTitle(R.string.title_tab1)
                    return true
                }
                R.id.navigation_tab2 -> {
                    item.setIcon(R.drawable.sc_select)
                    changeFragment(Tab2Fragment.newInstance())
                    toolbar.setTitle(R.string.title_tab2)
                    return true
                }
                R.id.navigation_tab3 -> {
                    item.setIcon(R.drawable.sd_select)
                    changeFragment(Tab3Fragment.newInstance())
                    toolbar.setTitle(R.string.title_tab3)
                    return true
                }
                R.id.navigation_tab4 -> {
                    item.setIcon(R.drawable.sj_select)
                    changeFragment(Tab4Fragment.newInstance())
                    toolbar.setTitle(R.string.title_tab4)
                    return true
                }
            }
            return false;
        }
    }

    private fun changeFragment(fm: Fragment) {

        supportFragmentManager?.apply {
            beginTransaction().replace(R.id.contents, fm).commitAllowingStateLoss()
        }

    }

    private fun defaultIcon() {

        nav_view.menu.getItem(0).setIcon(R.drawable.fx)
        nav_view.menu.getItem(1).setIcon(R.drawable.sc)
        nav_view.menu.getItem(2).setIcon(R.drawable.sd)
        nav_view.menu.getItem(3).setIcon(R.drawable.sj)

    }
}
