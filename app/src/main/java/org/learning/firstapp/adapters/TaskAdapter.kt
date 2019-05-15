package org.learning.firstapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.learning.firstapp.R
import org.learning.firstapp.domains.Task

class TaskAdapter(val tasks: ArrayList<Task>, val callback: Callback) : RecyclerView.Adapter<TaskHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TaskHolder
        = TaskHolder(LayoutInflater
        .from(p0.context)
        .inflate(R.layout.recyclerview_item_row, p0, false))

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(p0: TaskHolder, p1: Int) {
        p0.bind(tasks[p1])
    }

    interface Callback {
        fun onItemClicked(item: Task)
    }
}