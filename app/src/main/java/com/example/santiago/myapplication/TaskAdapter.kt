package com.example.santiago.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class TaskAdapter(var taskList: MutableList<Task> = mutableListOf(), val deleteFunction: (Task) -> () -> Unit): RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TaskViewHolder {
        val rootView = LayoutInflater.from(p0.context).inflate(R.layout.item_task, p0, false)
        return TaskViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(p0: TaskViewHolder, position: Int) {
        p0.bind(taskList[position], deleteFunction)
    }
}