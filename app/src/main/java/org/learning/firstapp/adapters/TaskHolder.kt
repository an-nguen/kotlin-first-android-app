package org.learning.firstapp.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import org.learning.firstapp.R
import org.learning.firstapp.domains.Task

class TaskHolder(v : View) : RecyclerView.ViewHolder(v), View.OnClickListener {
    private var id = v.findViewById<TextView>(R.id.id)
    private var taskText = v.findViewById<TextView>(R.id.taskText)

    init {
        v.setOnClickListener(this)
    }

    fun bind(item: Task) {
        id.text = item.id.toString()

        if (item.taskText.length < 20)
            taskText.text = item.taskText
        else
            taskText.text = String.format("{}...",item.taskText.subSequence(0, 19))
    }

    override fun onClick(v: View?) {
        Log.d("RecyclerView", "CLICK!")
    }

    companion object {
        private val TASK_KEY = "TASK"
    }

}