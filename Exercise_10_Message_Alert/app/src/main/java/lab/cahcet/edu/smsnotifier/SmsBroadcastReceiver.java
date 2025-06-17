package lab.cahcet.edu.smsnotifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

// BroadcastReceiver for detecting incoming SMS and updating UI
public class SmsBroadcastReceiver extends BroadcastReceiver {
    public static final String SMS_BUNDLE = "pdus";

    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            StringBuilder smsMessageStr = new StringBuilder();

            for (Object sm : sms) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sm);
                String smsBody = smsMessage.getMessageBody();
                String address = smsMessage.getOriginatingAddress();

                smsMessageStr.append("SMS From: ").append(address).append("\n").append(smsBody).append("\n");
            }

            Toast.makeText(context, smsMessageStr.toString(), Toast.LENGTH_LONG).show();

            SMSNotification smsinstance = SMSNotification.instance();
            smsinstance.updateList(smsMessageStr.toString());
        }
    }
}
