package com.kindergeschichten.romanisch.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.data.StoryType
import com.kindergeschichten.romanisch.databinding.ActivityMainBinding
import com.kindergeschichten.romanisch.tools.openRomanisch
import com.kindergeschichten.romanisch.tools.openWebsite
import com.kindergeschichten.romanisch.tools.rateApp
import com.kindergeschichten.romanisch.tools.showParentCheckDialog
import com.kindergeschichten.romanisch.ui.base.VBActivity
import com.kindergeschichten.romanisch.ui.fragment.MainFragment
import com.kindergeschichten.romanisch.ui.languageselection.LanguageSelectionActivity
import com.kindergeschichten.romanisch.ui.menu.MenuAction

class MainActivity : VBActivity<ActivityMainBinding>(),MainFragment.BottomMenuListener,
    NavigationView.OnNavigationItemSelectedListener {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.appbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navController = findNavController(R.id.nav_host_fragment)
        binding.navigationDrawer.setNavigationItemSelectedListener(this)
    }

    fun enableNav()
    {
        binding.appbar.toolbar.setNavigationIcon(R.drawable.menu_icon)
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        openDrawer()

    }


    fun disableNav()
    {
        binding.appbar.toolbar.navigationIcon = null
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun onResume() {
        super.onResume()
        disableNav()
    }

     fun onMenuAction(menuAction: MenuAction) {
        when (menuAction) {
            MenuAction.NAV_VEREIN -> navController.navigate(R.id.nav_verein)
            MenuAction.NAV_IMPRESSUM -> navController.navigate(R.id.nav_impressum)
            MenuAction.NAV_SPONSOR -> navController.navigate(R.id.nav_sponsor)
            MenuAction.SETTINGS -> navController.navigate(R.id.nav_settings)
            MenuAction.SHARE -> navController.navigate(R.id.nav_share)
            MenuAction.RATE -> {rateApp()}
            else -> {}
        }
        binding.drawerLayout.closeDrawers()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {



        when(item.itemId)
        {
            R.id.nav_verein -> navController.navigate(R.id.nav_verein)
            R.id.nav_impressum -> navController.navigate(R.id.nav_impressum)
            R.id.nav_bedienung -> navController.navigate(R.id.nav_sponsor)
            R.id.nav_setting -> navController.navigate(R.id.nav_settings)
            R.id.nav_share -> navController.navigate(R.id.nav_share)
            R.id.nav_rate -> {rateApp()}
            R.id.nav_weitere -> navController.navigate(R.id.nav_romontsch)
            R.id.nav_other_products -> navController.navigate(R.id.nav_visunia)
            R.id.nav_romanisch  -> openRomanisch()
            R.id.nav_webseite -> openWebsite()

            else ->{}
        }
        binding.drawerLayout.closeDrawers()
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            openDrawer()
            return true
        }
        if(item.itemId==R.id.home)
        {
            navController.navigate(R.id.nav_home)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    companion object{
        fun start(context:AppCompatActivity)
        {
            context.startActivity(Intent(context,MainActivity::class.java))
        }
    }

    override fun bottomMenuClicked(menuItem: MainFragment.BottomMenu) {
        if(menuItem==MainFragment.BottomMenu.Roman)
            StorySelectionActivity.start(this,StoryType.ROMANISCH)
        else    if(menuItem==MainFragment.BottomMenu.Deutsch)
            StorySelectionActivity.start(this,StoryType.DEUTSCH)
        else if(menuItem==MainFragment.BottomMenu.SelectDialect)
            LanguageSelectionActivity.start(this,true)
        else if(menuItem==MainFragment.BottomMenu.Parents)
            showParentCheckDialog{
                enableNav()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


}