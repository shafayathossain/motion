package com.example.motion.motion.HistoryActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.motion.juthiapu.R;

import java.util.ArrayList;

public class History extends AppCompatActivity implements OnItemClickListener {
    private static History inst;
    ArrayAdapter arrayAdapter;
    ListView smsListView;
    ArrayList<String> smsMessageList = new ArrayList();

    public static History instance() {
        return inst;
    }

    protected void onStart() {
        super.onStart();
        inst = this;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_history);
        this.smsListView = (ListView) findViewById(R.id.SMSList);
        this.arrayAdapter = new ArrayAdapter(
                this, android.R.layout.simple_list_item_1,
                this.smsMessageList
        );
        this.smsListView.setAdapter(this.arrayAdapter);
        this.smsListView.setOnItemClickListener(this);
        m3r();
    }

    public void m3r() {
        if (ContextCompat.checkSelfPermission(
                this,
                "android.permission.READ_SMS"
        ) != 0) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{"android.permission.READ_SMS"},
                    100
            );
            return;
        }
        refreshSmsInbox();
    }

    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == 0) {
                    refreshSmsInbox();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void refreshSmsInbox() {
        Cursor smsInboxCursor =
                getContentResolver().query(Uri.parse("content://sms/inbox"),
                                null, null, null, null);
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        if (indexBody >= 0 && smsInboxCursor.moveToFirst()) {
            this.arrayAdapter.clear();
            do {
                if(smsInboxCursor.getString(indexBody).contains("motion")) {
                    this.arrayAdapter.add("SMS From: " + smsInboxCursor.getString(indexAddress) +
                            "\n" + smsInboxCursor.getString(indexBody) + "\n");
                }
                } while (smsInboxCursor.moveToNext());
        }
    }

    public void updateList(String smsMessage) {
        this.arrayAdapter.insert(smsMessage, 0);
        this.arrayAdapter.notifyDataSetChanged();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        try {
            String[] smsMessages = ((String) this.smsMessageList.get(position)).split("\n");
            String address = smsMessages[0];
            String smsMessage = "";
            int i = 1;
            while (1 < smsMessages.length) {
                smsMessage = smsMessage + smsMessages[i];
                i++;
            }
            Toast.makeText(this, (address + "\n") + smsMessage, 0).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
