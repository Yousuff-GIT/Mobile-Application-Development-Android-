// RSSFeedDisplay.java
// Parses and displays the list of RSS items using ListView

package lab.cahcet.edu.rssfeedreader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RSSFeedDisplay extends AppCompatActivity {
    ListView listview;
    ArrayList<RSSItems> channelvalues = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed_display);
        listview = findViewById(R.id.rssshow);
        displayTag();
    }

    // Loads RSS items into ListView
    public void displayTag() {
        try {
            channelvalues = SAXXMLParser.rssParser(getAssets().open("WebTutorials.xml"));
            ArrayAdapter<RSSItems> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, channelvalues);
            listview.setAdapter(adapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Uri uri = Uri.parse(channelvalues.get(position).getLink());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}