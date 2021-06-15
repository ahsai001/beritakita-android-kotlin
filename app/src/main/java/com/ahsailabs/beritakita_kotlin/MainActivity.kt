package com.ahsailabs.beritakita_kotlin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ahsailabs.beritakita_kotlin.ui.login.models.LoginData
import com.ahsailabs.beritakita_kotlin.utils.SessionUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var navView: NavigationView? = null
    private var fab: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val isLoggedIn = SessionUtil.isLoggedIn(this@MainActivity)
            if (isLoggedIn) {
                AddNewsActivity.start(this@MainActivity)
            } else {
                findNavController(R.id.nav_host_fragment).navigate(R.id.nav_login)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_login, R.id.nav_logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView?.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            run {
                refreshDrawer()
                if (destination.id == R.id.nav_login) {
                    fab.hide()
                } else {
                    fab.show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshDrawer()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    @SuppressLint("SetTextI18n")
    private fun refreshDrawer() {
        val navLogin: MenuItem = navView!!.menu.findItem(R.id.nav_login)
        val navLogout: MenuItem = navView!!.menu.findItem(R.id.nav_logout)
        val loginData: LoginData
        //update menu drawer
        if (SessionUtil.isLoggedIn(this)) {
            navLogin.isVisible = false
            navLogout.isVisible = true
            loginData = SessionUtil.getLoginData(this)
        } else {
            navLogin.isVisible = true
            navLogout.isVisible = false
            loginData = LoginData()
            loginData.name = "anonymous"
            loginData.username = "anonymous"
        }

        //update headerview with loginData
        val headerView: View = navView!!.getHeaderView(0)
        val tvName = headerView.findViewById<TextView>(R.id.tvName)
        val tvUserName = headerView.findViewById<TextView>(R.id.tvUserName)

        tvName.text = loginData.name
        tvUserName.text = "@${loginData.username}"
    }

    companion object {
        fun start(context: Context) {
            val mainIntent = Intent(context, MainActivity::class.java)
            context.startActivity(mainIntent)
        }
    }
}