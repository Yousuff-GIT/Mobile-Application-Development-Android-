/*
 * ReadWrite.java
 * Demonstrates writing and reading from SD card using Android framework
 */
package lab.cahcet.edu.takenotes;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ReadWrite extends AppCompatActivity {
    EditText notes;
    Button write, read;
    TextView readcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write);

        notes = (EditText) findViewById(R.id.notes_ET);
        write = (Button) findViewById(R.id.save_btn);
        read = (Button) findViewById(R.id.read_btn);
        readcontent = (TextView) findViewById(R.id.read_txt);
    }

    public void checkNotes(View view) {
        if (notes.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
        else
            writeNotes();
    }

    public void writeNotes() {
        readcontent.setText("");
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File root = Environment.getExternalStorageDirectory();
            File folder = new File(root.getAbsolutePath() + "/TakeNotes");
            if (!folder.exists()) {
                folder.mkdir();
            }
            File file = new File(folder, "Notes.txt");
            String writecontent = notes.getText().toString();
            try {
                FileOutputStream output = new FileOutputStream(file);
                output.write(writecontent.getBytes());
                output.close();
                notes.setText("");
                Toast.makeText(getApplicationContext(), "Notes Saved", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "SD card not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void readNotes(View view) {
        File root = Environment.getExternalStorageDirectory();
        File folder = new File(root.getAbsolutePath() + "/TakeNotes");
        File file = new File(folder, "Notes.txt");
        String content;
        try {
            FileInputStream inputstream = new FileInputStream(file);
            InputStreamReader inputreader = new InputStreamReader(inputstream);
            BufferedReader bufferreader = new BufferedReader(inputreader);
            StringBuffer buffer = new StringBuffer();
            while ((content = bufferreader.readLine()) != null) {
                buffer.append(content + "\n");
            }
            inputstream.close();
            readcontent.setText(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
