package ru.vlabum.android.gb.material1

interface SimpleItemAdapter {
    fun onItemDelete(position: Int)
    fun onItemMove(fromPosition: Int, toPosition: Int)
}