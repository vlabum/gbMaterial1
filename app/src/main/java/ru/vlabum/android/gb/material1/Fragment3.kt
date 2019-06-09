package ru.vlabum.android.gb.material1

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

class Fragment3 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.tv_f3)
        val textView2 = view.findViewById<TextView>(R.id.tv2_f3)
        val imageView = view.findViewById<ImageView>(R.id.iv_f3)
        startValueAnimator(textView)
        startObjectAnimator(textView2)
        startAnimatedVectorDrawable(imageView)
    }

    private fun startValueAnimator(textView: TextView) {
        val valueAnimator = ValueAnimator.ofFloat(0f, 360f)
        valueAnimator.addUpdateListener(
            object : ValueAnimator.AnimatorUpdateListener {
                override fun onAnimationUpdate(animation: ValueAnimator?) {
                    animation?.let {
                        textView.rotation = animation.animatedValue.toString().toFloat()
                    }
                }
            }
        )
        valueAnimator.duration = 700
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.start()
    }

    private fun startObjectAnimator(textView: TextView) {
        val objAnimator = ObjectAnimator.ofArgb(textView, "textColor", Color.BLACK, Color.YELLOW)
        objAnimator.setDuration(700)
        objAnimator.setRepeatMode(ObjectAnimator.RESTART)
        objAnimator.setRepeatCount(ObjectAnimator.INFINITE)

        objAnimator.start()
    }

    private fun startAnimatedVectorDrawable(imageView: ImageView) {
        (imageView.drawable as? AnimatedVectorDrawableCompat)?.let {
            (imageView.drawable as? AnimatedVectorDrawableCompat)?.start()
                ?: (imageView.drawable as AnimatedVectorDrawable)?.start()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment3_main, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

}
