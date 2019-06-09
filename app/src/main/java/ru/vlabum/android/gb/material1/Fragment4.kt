package ru.vlabum.android.gb.material1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.transition.*

class Fragment4 : Fragment() {

    lateinit var rootScene: ViewGroup
    lateinit var scene1: Scene
    lateinit var scene2: Scene
    var isFirstScene = true
    var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootScene = view as ViewGroup
        this.view?.let {
            scene2 = Scene.getSceneForLayout(rootScene, R.layout.scene2, it.context)
            scene1 = Scene.getSceneForLayout(rootScene, R.layout.scene1, it.context)
        }
        button = view.findViewById(R.id.button2)
        button?.setOnClickListener() {
            changeScene()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment4_main, container, false)
        return view
    }

    private fun changeScene() {
        val set = TransitionSet()
        set.addTransition(Fade())
        set.addTransition(ChangeBounds())
        set.ordering = TransitionSet.ORDERING_TOGETHER
        set.duration = 1000
        set.interpolator = OvershootInterpolator(1.5f)
        TransitionManager.go(if (isFirstScene) scene2 else scene1, set)
        isFirstScene = !isFirstScene
    }

}