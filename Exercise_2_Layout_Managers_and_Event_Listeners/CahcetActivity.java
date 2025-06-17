/*
  CahcetActivity.java
  This Java class is the main entry point (launcher activity) for the CAHCET app.
  It defines event listeners to navigate to other activities (Aim, Courses, Gallery)
  using explicit Intent objects on button clicks.
*/

package lab.cahcet.edu.cahcet;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CahcetActivity extends AppCompatActivity {
    private static Button aim_btn;
    private static Button courses_btn;
    private static Button galary_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cahcet);
    }

    public void onaimClick(View view) {
        aim_btn = (Button) findViewById(R.id.moto_btn);
        aim_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("lab.cahcet.edu.cahcet.Aim");
                startActivity(intent);
            }
        });
    }

    public void oncoursesClick(View view) {
        courses_btn = (Button) findViewById(R.id.courses_btn);
        courses_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("lab.cahcet.edu.cahcet.Courses_Offered");
                startActivity(intent);
            }
        });
    }

    public void ongalaryClick(View view) {
        galary_btn = (Button) findViewById(R.id.galary_btn);
        galary_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("lab.cahcet.edu.cahcet.Galary");
                startActivity(intent);
            }
        });
    }
}
