package ru.vlabum.android.gb.material1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import layout.UserRecyclerAdapter

class Fragment2 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerView: RecyclerView? = view?.findViewById(R.id.recyclier_fragment2)
        recyclerView?.layoutManager = LinearLayoutManager(view?.context)
        recyclerView?.adapter = UserRecyclerAdapter(generateUsers())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment2_main, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun generateUsers(): List<User> {
        var users = ArrayList<User>()
        context?.resources?.let { res ->
            for (i in 0 until 20) {
                when (i % 5 == 0) {
                    true ->
                        users.add(Friend(res.getIdentifier("p${i % 10}", "drawable", context?.packageName), "Vasya $i"))
                    else ->
                        users.add(User(res.getIdentifier("p${i % 10}", "drawable", context?.packageName), "Vasya $i"))
                }
            }
        }
        return users
    }

}