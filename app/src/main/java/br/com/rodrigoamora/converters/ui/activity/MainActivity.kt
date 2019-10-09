package br.com.rodrigoamora.converters.ui.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import br.com.rodrigoamora.converters.R
import br.com.rodrigoamora.converters.extensions.changeFragment
import br.com.rodrigoamora.converters.ui.fragment.AboutFragment
import br.com.rodrigoamora.converters.ui.fragment.TemperatureFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val TAG_LOG = "converters-android"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createToolbarAndActionBar()
        changeFragment(TemperatureFragment(), this, R.id.container, null)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
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
            R.id.nav_distance -> {
                Log.i(TAG_LOG, "nav_distance clicked")
            }
            R.id.nav_temperature -> {
                Log.i(TAG_LOG, "nav_temperature clicked")
                changeFragment(TemperatureFragment(), this, R.id.container, null)
            }
            R.id.nav_share -> {
                Log.i(TAG_LOG, "nav_share clicked")
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun createToolbarAndActionBar() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }
}
