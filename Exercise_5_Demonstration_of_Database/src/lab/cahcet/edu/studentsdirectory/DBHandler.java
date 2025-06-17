// DBHandler.java
// Handles SQLite database operations such as insert, delete, and fetch.

package lab.cahcet.edu.studentsdirectory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "students_db";
    private static final String TABLE_NAME = "studentsdata";
    private static final String COLUMN_rollno = "rollnumber";
    private static final String COLUMN_name = "studentname";
    private static final String COLUMN_course = "course";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATION", "Database Created / Opened");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_rollno + " INTEGER PRIMARY KEY, " +
                COLUMN_name + " TEXT, " +
                COLUMN_course + " TEXT);";
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATION", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_QUERY);
        onCreate(db);
    }

    public boolean addStudent(int rollno, String name, String course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_rollno, rollno);
        values.put(COLUMN_name, name);
        values.put(COLUMN_course, course);
        return db.insert(TABLE_NAME, null, values) != -1;
    }

    public Cursor displayDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Integer deleteStudent(String rollno) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_rollno + " = ?", new String[]{rollno});
    }
}