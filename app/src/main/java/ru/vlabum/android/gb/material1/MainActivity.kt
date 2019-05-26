package ru.vlabum.android.gb.material1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val LOGD = "DDDDD"

    //    lateinit var bottomLayout: ViewGroup
//    lateinit var bottomSheetBehavior: BottomSheetBehavior<ViewGroup>
    lateinit var navView: BottomNavigationView

    private val onNavigationItemSelectedLoader = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_main, Fragment1()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_main, Fragment2()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_main, Fragment3()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedLoader)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_main, Fragment1()).commit()

        fab.setOnClickListener { view ->
            var rotated = view.rotation != 0f
            view.animate().rotation(if (rotated) 0f else 45f)

            if (rotated) {
                fab1.hide()
                fab2.hide()
                fab3.hide()
            } else {
                fab1.show()
                fab2.show()
                fab3.show()
            }

            fab1.animate()
                .alpha(if (rotated) 0f else 1f)
                .translationY(if (rotated) 0f else -fab1.height.toFloat() - 8f)
            fab2.animate()
                .alpha(if (rotated) 0f else 1f)
                .translationY(if (rotated) 0f else 2 * (-fab2.height.toFloat() - 8f))
            fab3.animate()
                .alpha(if (rotated) 0f else 1f)
                .translationY(if (rotated) 0f else 3 * (-fab3.height.toFloat() - 8f))
        }

        fab1.hide()
        fab2.hide()
        fab3.hide()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
