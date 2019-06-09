package ru.vlabum.android.gb.material1

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import layout.UserRecyclerAdapter
import ru.vlabum.android.gb.material1.animation.FlipXAnimator
import ru.vlabum.android.gb.material1.entity.Friend
import ru.vlabum.android.gb.material1.entity.User

class UsersFragment() : Fragment() {

    private var isFriendOnly = false

    constructor(isFriendOnly: Boolean) : this() {
        this.isFriendOnly = isFriendOnly
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView? = view.findViewById(R.id.recyclier_users)

        recyclerView?.let {
            it.layoutManager = LinearLayoutManager(view.context)
            it.itemAnimator = FlipXAnimator()
            it.setHasFixedSize(true)
            val adapter = UserRecyclerAdapter(
                generateUsers(),
                Typeface.createFromAsset(context?.assets, "DancingScript-Regular.otf")
            )
            val animationAdapter = ScaleInAnimationAdapter(adapter)
            it.adapter = animationAdapter

            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(it)

            val itemTouchHelper = ItemTouchHelper(SimpleItemCallback(adapter))
            itemTouchHelper.attachToRecyclerView(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users_main, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun generateUsers(): MutableList<User> {
        val users = ArrayList<User>()
        context?.resources?.let { res ->
            for (i in 0 until 20) {
                when (i % 5 == 0) {
                    true ->
                        users.add(
                            Friend(res.getIdentifier("p${i % 10}", "drawable", context?.packageName), "Vasya $i")
                        )
                    else ->
                        if (!isFriendOnly) {
                            users.add(
                                User(res.getIdentifier("p${i % 10}", "drawable", context?.packageName), "Vasya $i")
                            )
                        }
                }
            }
        }
        return users
    }

}