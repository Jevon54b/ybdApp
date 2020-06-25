package com.bingo.ybd.base.adapter

import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseRecyclerAdapter<T, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {
    protected val mList: MutableList<T> = ArrayList()
    val list: List<T>
        get() = mList

    override fun getItemCount(): Int {
        return mList.size
    }

    val isEmpty: Boolean
        get() = mList.isEmpty()

    fun getItem(position: Int): T? {
        return if (position < 0 || position >= mList.size) null else mList[position]
    }

    val lastItem: T?
        get() = if (mList.isEmpty()) null else mList[mList.size - 1]

    val firstItem: T?
        get() = if (mList.isEmpty()) null else mList[0]

    fun getItemPosition(item: T): Int {
        return mList.indexOf(item)
    }

    fun add(item: T): BaseRecyclerAdapter<T, VH> {
        mList.add(item)
        return this
    }

    fun add(position: Int, item: T): BaseRecyclerAdapter<T, VH> {
        mList.add(position, item)
        return this
    }

    fun remove(position: Int): BaseRecyclerAdapter<T, VH> {
        mList.removeAt(position)
        return this
    }

    fun remove(item: T): BaseRecyclerAdapter<T, VH> {
        mList.remove(item)
        return this
    }

    operator fun set(index: Int, item: T): BaseRecyclerAdapter<T, VH> {
        mList[index] = item
        return this
    }

    fun addAll(items: Array<T>): BaseRecyclerAdapter<T, VH> {
        mList.addAll(Arrays.asList(*items))
        return this
    }

    fun addAll(items: Collection<T>): BaseRecyclerAdapter<T, VH> {
        mList.addAll(items)
        return this
    }

    fun replaceAll(items: Collection<T>): BaseRecyclerAdapter<T, VH> {
        mList.clear()
        mList.addAll(items)
        return this
    }

    fun clear(): BaseRecyclerAdapter<T, VH> {
        mList.clear()
        return this
    }
}