package com.example.santiago.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.HORIZONTAL
import android.view.Menu
import android.view.MenuItem
import com.example.santiago.myapplication.R.layout.activity_main2
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: TaskAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    val taskList = mutableListOf<Task>(
            Task(HashUtils.sha1(System.currentTimeMillis().toString()), "Llamar a la abuela", 3, true, true),
            Task(HashUtils.sha1(System.currentTimeMillis().toString()), "Comprar regalo de cumpleaños", 2, true, true),
            Task(HashUtils.sha1(System.currentTimeMillis().toString()), "Limpiar auto", 1, true, true),
            Task(HashUtils.sha1(System.currentTimeMillis().toString()), "limpiar cuarto", 2, true, true),
            Task(HashUtils.sha1(System.currentTimeMillis().toString()), "Lavar pantalones", 1, true, true),
            Task(HashUtils.sha1(System.currentTimeMillis().toString()), "Preparar preubas", 1, true, true),
            Task(HashUtils.sha1(System.currentTimeMillis().toString()), "Hacer tareas", 3, true, true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewManager = LinearLayoutManager(this)
        viewAdapter = TaskAdapter(taskList) { task: Task ->
            {
                val taskToDelete =
                        taskList.firstOrNull() {
                            it.id == task.id
                        }
                taskList.remove(taskToDelete)
                viewAdapter.taskList = taskList
                viewAdapter.notifyDataSetChanged()

            }
        }

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

            val dividerItemDecoration: RecyclerView.ItemDecoration =  DividerItemDecoration(this@MainActivity, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
        }

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, Main2Activity::class.java)
            startActivityForResult(intent, INPUT_TASK)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val INPUT_TASK = 999
        const val TASK_DATA = "taskData"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val taskData : Task = data?.getSerializableExtra(TASK_DATA) as Task
        taskList.add(taskData)
        viewAdapter.taskList = taskList
        viewAdapter.notifyDataSetChanged()

    }
}
