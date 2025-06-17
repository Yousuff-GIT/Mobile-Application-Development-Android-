// RSSFeedExtract.java
// Launches the RSS reader feed view

package lab.cahcet.edu.rssfeedreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class RSSFeedExtract extends AppCompatActivity {
    ImageButton rss_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed_extract);
    }

    // RSS button click triggers RSS Feed Display Activity
    public void onclickReader(View view) {
        rss_btn = (ImageButton) findViewById(R.id.rss_btn);
        rss_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("lab.cahcet.edu.rssfeedreader.RssFeedDisplay");
                startActivity(intent);
            }
        });
    }
}