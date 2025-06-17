// StudentDetails.java
// This activity manages student information input and displays data from SQLite DB

package lab.cahcet.edu.studentsdirectory;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StudentDetails extends AppCompatActivity {
    private EditText rollno, name, course;
    DBHandler dbhandler;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        dbhandler = new DBHandler(this);
        rollno = findViewById(R.id.rollnumber_ET);
        name = findViewById(R.id.name_ET);
        course = findViewById(R.id.course_ET);
    }

    // Handles saving student record
    public void onaddbtnClicked(View view) {
        if (rollno.getText().toString().isEmpty() ||
            name.getText().toString().isEmpty() ||
            course.getText().toString().isEmpty()) {
            Toast.makeText(this, "Invalid Inputs", Toast.LENGTH_SHORT).show();
        } else {
            int std_rollno = Integer.parseInt(rollno.getText().toString());
            String std_name = name.getText().toString();
            String std_course = course.getText().toString();
            boolean isInserted = dbhandler.addStudent(std_rollno, std_name, std_course);
            Toast.makeText(this, isInserted ? "Details Saved" : "Saving Failed", Toast.LENGTH_SHORT).show();
            rollno.setText(""); name.setText(""); course.setText("");
        }
    }

    // Handles deleting student record
    public void ondeletebtnClicked(View view) {
        if (rollno.getText().toString().isEmpty()) {
            Toast.makeText(this, "Invalid Inputs", Toast.LENGTH_SHORT).show();
        } else {
            Integer deleteStatus = dbhandler.deleteStudent(rollno.getText().toString());
            Toast.makeText(this, deleteStatus > 0 ? "Details Deleted" : "Deleting Failed", Toast.LENGTH_SHORT).show();
            rollno.setText(""); name.setText(""); course.setText("");
        }
    }

    // Handles viewing all student records
    public void onviewbtnClicked(View view) {
        rollno.setText(""); name.setText(""); course.setText("");
        Cursor cursor = dbhandler.displayDetails();
        if (cursor.getCount() == 0) {
            showMessage("Error", "No Data Found!");
            return;
        }
        StringBuilder studentsinfo = new StringBuilder();
        while (cursor.moveToNext()) {
            studentsinfo.append("RollNumber: ").append(cursor.getString(0)).append("\n");
            studentsinfo.append("Student Name: ").append(cursor.getString(1)).append("\n");
            studentsinfo.append("Course: ").append(cursor.getString(2)).append("\n\n");
        }
        showMessage("STUDENT DETAILS", studentsinfo.toString());
    }

    public void showMessage(String title, String message) {
        new AlertDialog.Builder(this)
            .setCancelable(true)
            .setTitle(title)
            .setMessage(message)
            .show();
    }
}