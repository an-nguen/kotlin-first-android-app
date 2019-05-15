package org.learning.firstapp

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.learning.firstapp.adapters.TaskAdapter
import org.learning.firstapp.data_layer.SQLiteDBLayer
import org.learning.firstapp.domains.Task

class MainActivity : AppCompatActivity() {

    val db = SQLiteDBLayer(this, null)
    val items = ArrayList<Task>()

    val mainAdapter = TaskAdapter(items, object : TaskAdapter.Callback {
        override fun onItemClicked(item: Task) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            errorDialog("It's error message")
        }

        val recyclerView : RecyclerView = findViewById(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mainAdapter

        initDb()
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

    fun initDb() {
        items.clear()
        items.addAll(db.select())
    }

    fun errorDialog(errorMessage: String) {
        val builder = AlertDialog.Builder(this)

        with(builder) {
            setTitle("Error alert")
            setMessage(errorMessage)
            show()
        }
    }
}
