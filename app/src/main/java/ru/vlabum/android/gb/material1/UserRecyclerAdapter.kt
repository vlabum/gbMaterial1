package layout

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.vlabum.android.gb.material1.R
import ru.vlabum.android.gb.material1.SimpleItemAdapter
import ru.vlabum.android.gb.material1.entity.Friend
import ru.vlabum.android.gb.material1.entity.User
import java.util.*

class UserRecyclerAdapter(val users: MutableList<User>, val typeface: Typeface) :
    RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>(),
    SimpleItemAdapter {

    companion object {
        private val FRIEND: Int = 1
        private val USER: Int = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecyclerAdapter.ViewHolder {
        when (viewType) {
            FRIEND ->
                return ViewHolderFriend(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_friend,
                        parent,
                        false
                    )
                )
            else ->
                return ViewHolderUser(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
        }
    }

    fun removeItem(position: Int) {
        users.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemDelete(position: Int) {
        users.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(users, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun onItemSelected() {
        //view
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (users[position] is Friend) FRIEND else USER
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderFriend -> holder.bind((users[position] as Friend))
            else -> holder.bind((users[position]))
        }
    }

    inner open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun bind(user: User) {}
        open fun bind(user: Friend) {}
    }

    inner class ViewHolderUser(itemView: View) : ViewHolder(itemView) {
        var avatar: ImageView? = null
        var name: TextView? = null

        init {
            avatar = itemView.findViewById(R.id.avatar)
            name = itemView.findViewById(R.id.name)
        }

        override fun bind(user: User) {
            name?.text = user.name
            name?.typeface = typeface
            avatar?.setImageResource(user.avatarID)
        }
    }

    inner class ViewHolderFriend(itemView: View) : ViewHolder(itemView) {
        var avatar: ImageView? = null
        var name: TextView? = null

        init {
            avatar = itemView.findViewById(R.id.avatar)
            name = itemView.findViewById(R.id.name)
        }

        override fun bind(user: Friend) {
            name?.text = user.name
            name?.typeface = typeface
            avatar?.setImageResource(user.avatarID)
        }
    }

}