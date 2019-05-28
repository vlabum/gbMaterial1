package ru.vlabum.android.gb.material1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.content_main.*

class Fragment1 : Fragment() {

    lateinit var shakeAnim: Animation
    lateinit var btLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.content_main, container, false)
        btLogin = view.findViewById(R.id.bt_login)
        btLogin.setOnClickListener {
            clickBtLogin()
        }
        shakeAnim = AnimationUtils.loadAnimation(view.context, R.anim.shake)
        return view
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

}