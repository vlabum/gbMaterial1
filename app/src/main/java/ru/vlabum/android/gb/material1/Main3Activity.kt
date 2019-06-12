package ru.vlabum.android.gb.material1

import android.animation.ValueAnimator
import android.os.Bundle
import android.transition.*
import android.view.Gravity
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main3.*
import java.util.concurrent.TimeUnit

class Main3Activity : AppCompatActivity() {

    val orbitsConstraint = ConstraintSet()
    val detailsConstraint = ConstraintSet()
    lateinit var constraintLayout: ConstraintLayout

    private var state = true

    lateinit var sun: ImageView
    lateinit var planet1: ImageView
    lateinit var planet2: ImageView
    lateinit var planet3: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        setSupportActionBar(toolbar)

        constraintLayout = findViewById(R.id.root)
        orbitsConstraint.clone(constraintLayout)
        detailsConstraint.clone(this, R.layout.activity_main3_details)
        sun = findViewById(R.id.sun)
        planet1 = findViewById(R.id.planet1)
        planet2 = findViewById(R.id.planet2)
        planet3 = findViewById(R.id.planet3)

        val planet1Anim = getAnimator(planet1, TimeUnit.SECONDS.toMillis(2))
        val planet2Anim = getAnimator(planet2, TimeUnit.SECONDS.toMillis(6))
        val planet3Anim = getAnimator(planet3, TimeUnit.SECONDS.toMillis(12))

        startAnim(planet1Anim, planet2Anim, planet3Anim)

        sun.setOnClickListener { view ->
            run {
                val set = TransitionSet()
                val changeBounds = ChangeBounds()
                changeBounds.pathMotion = ArcMotion()
                changeBounds.propagation = CircularPropagation()

                set.addTransition(Fade())
                set.addTransition(changeBounds)
                set.addTransition(Slide(Gravity.RIGHT))

                set.addTransition(Explode())
                set.ordering = TransitionSet.ORDERING_TOGETHER
                set.duration = 300
                set.interpolator = AccelerateInterpolator()

                if (state) {
                    canceltAnim(planet1Anim, planet2Anim, planet3Anim)
                    TransitionManager.beginDelayedTransition(constraintLayout, set)
                    detailsConstraint.applyTo(constraintLayout)
                    state = false
                } else {
                    set.addListener(object : Transition.TransitionListener {
                        override fun onTransitionResume(transition: Transition?) {}
                        override fun onTransitionPause(transition: Transition?) {}
                        override fun onTransitionCancel(transition: Transition?) {}
                        override fun onTransitionStart(transition: Transition?) {}
                        override fun onTransitionEnd(transition: Transition) {
                            startAnim(planet1Anim, planet2Anim, planet3Anim)
                        }
                    })
                    TransitionManager.beginDelayedTransition(constraintLayout, set)
                    orbitsConstraint.applyTo(constraintLayout)
                    state = true
                }
            }
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun getAnimator(iView: ImageView, duration: Long): ValueAnimator {
        val layoutParam = iView.layoutParams as ConstraintLayout.LayoutParams
        val angle: Int = layoutParam.circleAngle.toInt()

        val anim = ValueAnimator.ofInt(angle + 359)
        anim.addUpdateListener { valueAnimator ->
            run {
                layoutParam.circleAngle = valueAnimator.animatedValue.toString().toFloat()
                iView.layoutParams = layoutParam
            }
        }
        anim.duration = duration
//        anim.interpolator = LinearInterpolator()
        anim.repeatMode = ValueAnimator.RESTART
        anim.repeatCount = ValueAnimator.INFINITE

        return anim
    }

    private fun startAnim(vararg animators: ValueAnimator) {
        for (anim in animators) {
            anim.start()
        }
    }

    private fun canceltAnim(vararg animators: ValueAnimator) {
        for (anim in animators) {
            anim.cancel()
        }
    }

}
