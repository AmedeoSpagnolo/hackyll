package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClientPreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_client);

        // ADD TOOLBAR
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
            }
        });

        // add fake data
        ArrayList<DataMeterpreter> myFakeDataset = new ArrayList<DataMeterpreter>();
        buttonListAdapter adapter = new ClientPreActivity.buttonListAdapter(this, myFakeDataset);
        adapter.add(new DataMeterpreter("help","",""));
        adapter.add(new DataMeterpreter("background","",""));
        adapter.add(new DataMeterpreter("cat","",""));
        adapter.add(new DataMeterpreter("cd &gt; pwd","",""));
        adapter.add(new DataMeterpreter("clearev","",""));
        adapter.add(new DataMeterpreter("download","",""));
        adapter.add(new DataMeterpreter("edit","",""));
        adapter.add(new DataMeterpreter("execute","",""));
        adapter.add(new DataMeterpreter("getuid","",""));
        adapter.add(new DataMeterpreter("hashdump","",""));
        adapter.add(new DataMeterpreter("idletime","",""));
        adapter.add(new DataMeterpreter("ipconfig","",""));
        adapter.add(new DataMeterpreter("lpwd &gt; lcd","",""));
        adapter.add(new DataMeterpreter("ls","",""));
        adapter.add(new DataMeterpreter("migrate","",""));
        adapter.add(new DataMeterpreter("ps","",""));
        adapter.add(new DataMeterpreter("resource","",""));
        adapter.add(new DataMeterpreter("search","",""));
        adapter.add(new DataMeterpreter("shell","",""));
        adapter.add(new DataMeterpreter("upload","",""));
        adapter.add(new DataMeterpreter("webcam_list","",""));
        adapter.add(new DataMeterpreter("webcam_snap","",""));
        ListView listView = findViewById(R.id.list_buttons);
        listView.setAdapter(adapter);
    }

    public class buttonListAdapter extends ArrayAdapter<DataMeterpreter> {
        public buttonListAdapter(Context context, ArrayList<DataMeterpreter> my_items) {
            super(context, 0, my_items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DataMeterpreter my_items = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_button, parent, false);
            }
            TextView chatMessage = convertView.findViewById(R.id.button_name);
            chatMessage.setText(my_items.name);

            return convertView;
        }
    }
}
