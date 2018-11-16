package com.example.santiago.myapplication

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.item_task.view.*

class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(task: Task, deleteFunction: (Task) -> () -> Unit) {
        itemView.taskTextView.text = task.name
        itemView.levelTextView.text = task.level.toString()
        /*itemView.favouriteButton.visibility = View.GONE {
            if (var favourite = false )
        }*/
        itemView.deleteButton.setOnClickListener { deleteFunction(task).invoke() }
        itemView.favouriteButton.setOnClickListener { view ->
            Snackbar.make(view, "Esta función no esta disponible en su dispositivo disculpe las molestias estamos trabajando para solucionarlo", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()}
    }
}