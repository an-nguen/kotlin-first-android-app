package org.learning.firstapp.data_layer

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.learning.firstapp.domains.Task

class SQLiteDBLayer(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    DatabaseLayer<Task, Int>, SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE IF EXISTS $TABLE_NAME ( " +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "$COLUMN_TEXT NVARCHAR(255)" +
                " )")
        db?.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    override fun create(item: Task) {
        val db = this.writableDatabase
        db?.execSQL("INSERT INTO $TABLE_NAME (${COLUMN_TEXT}) VALUES('${item.taskText}')")
    }

    override fun update(id: Int, item: Task) {
        val db = this.writableDatabase
        db?.execSQL("UPDATE $TABLE_NAME SET ${COLUMN_TEXT} = '${item.taskText}'," +
                " WHERE ${COLUMN_ID} = ${item.id}")
    }

    override fun delete(id: Int) {
        val db = this.writableDatabase
        db?.execSQL("DELETE FROM $TABLE_NAME t WHERE t.${COLUMN_ID} = ${id}")
    }

    override fun select(): ArrayList<Task> {
        val db = this.readableDatabase
        var res = ArrayList<Task>()
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        cursor!!.moveToFirst()
        while (cursor.moveToNext()) {
            res.add(Task(
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_TEXT))
            ))
        }
        cursor.close()
        return res
    }

    fun initDb() {
        val db = this.writableDatabase
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ( " +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "$COLUMN_TEXT NVARCHAR(255)," +
                "$COLUMN_EXP_DATE DATETIME" +
                " )")
        db?.execSQL(CREATE_TABLE_QUERY)
    }

    companion object {
        private val DATABASE_NAME = "firstApp.db"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "tasks"
        val COLUMN_ID = "id"
        val COLUMN_TEXT = "task_text"
        val COLUMN_EXP_DATE = "exp_date"
    }
}