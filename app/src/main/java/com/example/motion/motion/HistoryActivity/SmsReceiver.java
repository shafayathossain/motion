package com.example.motion.motion.HistoryActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

    private static SmsListener mListener;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();

        Object[] pdus = (Object[]) data.get("pdus");

        for(int i = 0; i<pdus.length; i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])pdus[i]);

            String sender = smsMessage.getDisplayOriginatingAddress();

            if(sender.equals("GADGETSAINT")){
                String messageBody = smsMessage.getMessageBody();
                mListener.messageReceived(messageBody);
            }
        }
    }

    public static void bindListener(SmsListener listener){
        mListener = listener;
    }
}
