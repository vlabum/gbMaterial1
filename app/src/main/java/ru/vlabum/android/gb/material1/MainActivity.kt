package ru.vlabum.android.gb.material1

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_content.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    val LOGD = "DDDDD"

    lateinit var bottomLayout: ViewGroup
    lateinit var bottomSheetBehavior: BottomSheetBehavior<ViewGroup>
    lateinit var shakeAnim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        bottomLayout = bottom_layout
        bottomSheetBehavior = BottomSheetBehavior.from(bottomLayout)

        var bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
                Log.d("$LOGD onSlide", "" + p1)
            }

            override fun onStateChanged(p0: View, p1: Int) {
                Log.d("$LOGD onStateChanged", "" + p1)
            }

        }
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback)

        fab.setOnClickListener { view ->
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        shakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake)

        bt_login.setOnClickListener {
            clickBtLogin()
        }

    }

    fun clickBtLogin() {
        var isError = false
        et_username.text?.let { t ->
            if (t.toString().equals("")) {
                et_username.error = "username is empty"
                isError = true
            }
        }
        et_password.text?.let { t ->
            if (t.toString().equals("")) {
                et_password.error = "password is empty"
                isError = true
            }
        }
        if (isError) bt_login.startAnimation(shakeAnim)
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
