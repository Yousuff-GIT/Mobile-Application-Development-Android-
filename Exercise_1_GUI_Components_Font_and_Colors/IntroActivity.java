/*
 * IntroActivity.java
 * This Java class defines the logic for an Android application that demonstrates
 * the use of GUI components, font styles, and color manipulation.
 * The class extends AppCompatActivity and uses event-driven methods to respond to user actions.
 */

package lab.cahcet.edu.android_intro;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class IntroActivity extends AppCompatActivity {

    // Declare TextViews and Typeface objects
    private TextView title, content;
    private Typeface candella_font, stencil_font, alex_font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Initialize UI elements
        title = findViewById(R.id.title_txt);
        content = findViewById(R.id.intro_txt);

        // Load custom fonts from assets
        candella_font = Typeface.createFromAsset(getAssets(), "Candella.otf");
        stencil_font = Typeface.createFromAsset(getAssets(), "Stencil.ttf");
        alex_font = Typeface.createFromAsset(getAssets(), "Alex.ttf");
    }

    // Method to show a Toast message on clicking the ImageButton
    public void onreleaseClick(View view) {
        Toast.makeText(getApplicationContext(), "Android 6.0 Marshmallow released on October 5, 2015", Toast.LENGTH_LONG).show();
    }

    // Method to change the font style based on user selection
    public void changeFont(View view) {
        boolean Rflag = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.candella_btn:
                if (Rflag) {
                    title.setTypeface(candella_font);
                    content.setTypeface(candella_font);
                }
                break;
            case R.id.stencil_btn:
                if (Rflag) {
                    title.setTypeface(stencil_font);
                    content.setTypeface(stencil_font);
                }
                break;
            case R.id.alex_btn:
                if (Rflag) {
                    title.setTypeface(alex_font);
                    content.setTypeface(alex_font);
                }
                break;
        }
    }

    // Method to change text or background color based on checkbox selection
    public void changeColor(View view) {
        boolean Cflag = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.charcoal_txt:
                if (Cflag) {
                    title.setTextColor(Color.parseColor("#FF0000"));
                    content.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    title.setTextColor(Color.parseColor("#000000"));
                    content.setTextColor(Color.parseColor("#000000"));
                }
                break;
            case R.id.greenish_txt:
                if (Cflag) {
                    title.setBackgroundColor(Color.parseColor("#FFFFAA"));
                    content.setBackgroundColor(Color.parseColor("#FFFFAA"));
                } else {
                    title.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    content.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                break;
        }
    }

    // Method to toggle the visibility of version history image
    public void onchangeState(View view) {
        ImageView versions = findViewById(R.id.version_image);
        boolean tflag = ((ToggleButton) view).isChecked();
        if (tflag) {
            versions.setImageResource(R.drawable.android_versions);
            versions.setVisibility(View.VISIBLE);
        } else {
            versions.setVisibility(View.INVISIBLE);
        }
    }
}
