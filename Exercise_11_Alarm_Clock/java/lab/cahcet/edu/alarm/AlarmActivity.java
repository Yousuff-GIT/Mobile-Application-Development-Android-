//AlarmActivity.java
package lab.cahcet.edu.alarm;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class AlarmActivity extends Activity {
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        RegisterAlarmBroadcast();
    }

    public void onClickSetAlarm(View v) {
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + 10000,
            pendingIntent
        );
    }

    private void RegisterAlarmBroadcast() {
        Log.i("Broadcast: ", "Register Alarm Broadcast");
        mReceiver = new BroadcastReceiver() {
            private static final String TAG = "Alarm Receiver";
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(TAG, "BroadcastReceiver-OnReceive()");
                Toast.makeText(context, "Alarm. Wakeup! Wakeup! Wakeup!", Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(mReceiver, new IntentFilter("lab.cahcet.edu.alarm"));
        pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent("lab.cahcet.edu.alarm"), 0);
        alarmManager = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
