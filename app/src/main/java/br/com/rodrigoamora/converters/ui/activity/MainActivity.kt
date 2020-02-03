package br.com.rodrigoamora.converters.ui.activity

import android.annotation.TargetApi
import android.content.pm.ShortcutManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import br.com.rodrigoamora.converters.R
import br.com.rodrigoamora.converters.extemsion.createShortcutInfos
import br.com.rodrigoamora.converters.shared.extension.changeFragment
import br.com.rodrigoamora.converters.shared.extension.share
import br.com.rodrigoamora.converters.ui.fragment.AboutFragment
import br.com.rodrigoamora.converters.ui.fragment.DistanceFragment
import br.com.rodrigoamora.converters.ui.fragment.TemperatureFragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val TAG_LOG = "converters-android"
    var drawer_layout :DrawerLayout ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createToolbarAndNavigationView()
        hideNavigationBar()


        if (Build.VERSION.SDK_INT >= 26) {
            createShorcuts()
            checkOptionInIntent()
        } else {
            changeFragment(TemperatureFragment(), this, R.id.container, null)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout!!.isDrawerOpen(GravityCompat.START)) {
            drawer_layout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_about -> {
                Log.i(TAG_LOG, "action_about clicked")
                changeFragment(AboutFragment(), this, R.id.container, null)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_about -> {
                Log.i(TAG_LOG, "nav_about clicked")
                changeFragment(AboutFragment(), this, R.id.container, null)
            }
            R.id.nav_distance -> {
                Log.i(TAG_LOG, "nav_distance clicked")
                changeFragment(DistanceFragment(), this, R.id.container, null)
            }
            R.id.nav_temperature -> {
                Log.i(TAG_LOG, "nav_temperature clicked")
                changeFragment(TemperatureFragment(), this, R.id.container, null)
            }
            R.id.nav_share -> {
                Log.i(TAG_LOG, "nav_share clicked")
                val textSahre = getString(R.string.share_app)+" "+
                                        getString(R.string.link_app)
                share(this, getString(R.string.app_name), textSahre)
            }
        }

        drawer_layout?.closeDrawer(GravityCompat.START)

        return true
    }

    private fun createToolbarAndNavigationView() {
        val toolbar:Toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar)

        drawer_layout = findViewById(R.id.drawer_layout) as DrawerLayout

        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)

        drawer_layout?.addDrawerListener(toggle)
        toggle.syncState()

        val nav_view :NavigationView = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun hideNavigationBar() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        decorView.systemUiVisibility = uiOptions
    }


    private fun checkOptionInIntent() {
        val option = intent.getStringExtra("option")
        if (option != null && !option.isEmpty()) {
            when(option) {
                "distance" -> {
                    changeFragment(DistanceFragment(), this, R.id.container, null)
                }
                "temperature" -> {
                    changeFragment(TemperatureFragment(), this, R.id.container, null)
                }
            }
        } else {
            changeFragment(TemperatureFragment(), this, R.id.container, null)
        }
    }

    @TargetApi(26)
    private fun createShorcuts() {
        val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java!!)

        val shortLabels = arrayOf(getString(R.string.distance), getString(R.string.temperature))

        val disabledMessage = arrayOf(getString(R.string.distance), getString(R.string.temperature))

        val options = arrayOf("distance", "temperature")

        val icons = arrayOf<Int>(R.drawable.ic_shortcut_ic_distance, R.drawable.ic_shortcut_ic_temperature)

        val shortcutInfos = createShortcutInfos(this, shortLabels, disabledMessage, options, icons)
        shortcutManager.setDynamicShortcuts(shortcutInfos.toMutableList())
    }
}
