package sg.edu.rp.c347.taskmanagerp06;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 15035634 on 25/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASKNAME = "taskname";
    private static final String COLUMN_DESCR = "description";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TASKNAME + " VARCHAR, "
                + COLUMN_DESCR + " VARCHAR )";
        db.execSQL(createSongTableSql);
        Log.i("info", "created tables");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }

    public long insertTask(String taskname, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASKNAME, taskname);
        values.put(COLUMN_DESCR, description);
        long result = db.insert(TABLE_TASK, null, values);
        db.close();
        return result;
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        db.delete(TABLE_TASK, condition, args);
        db.close();
    }

    public void updateTask(Task data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASKNAME, data.getTaskname());
        values.put(COLUMN_DESCR, data.getDescription());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        db.update(TABLE_TASK, values, condition,args);
        db.close();
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();

        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_TASKNAME + ", " + COLUMN_DESCR + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String taskname = cursor.getString(1);
                String description = cursor.getString(2);

                Task obj = new Task(id, taskname, description);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }

}
