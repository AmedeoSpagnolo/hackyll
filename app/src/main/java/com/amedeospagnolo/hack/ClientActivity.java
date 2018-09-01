package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


public class ClientActivity extends AppCompatActivity {
    private EditText editText;
    private TextView clName;
    private TextView clIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        editText = findViewById(R.id.myNewMessageBox);
        clName = findViewById(R.id.this_client_name);
        clIp = findViewById(R.id.this_client_ip);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            clName = null;
            clIp = null;

        } else {
            clName.setText(extras.getString("client_name"));
            clIp.setText(extras.getString("client_ip"));
        }

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
        ArrayList<DataChat> myFakeDataset = new ArrayList<DataChat>();
        myChatAdapter_mine adapter = new myChatAdapter_mine(this, myFakeDataset);
//        myChatAdapter_their adapter = new myChatAdapter_their(this, myFakeDataset);
        adapter.add(new DataChat("Hi, Nathan!"));
        adapter.add(new DataChat("Hi, Bob!"));
        adapter.add(new DataChat("Hi, Francene!"));
        adapter.add(new DataChat("Hi, Cammie!"));
        adapter.add(new DataChat("Hi, Ines!"));
        adapter.add(new DataChat("Hi, Celeste!"));
        adapter.add(new DataChat("Hi, Renata!"));
        adapter.add(new DataChat("Hi, Stephan!"));
        adapter.add(new DataChat("Hi, Marcia!"));
        adapter.add(new DataChat("Hi, Romona!"));
        adapter.add(new DataChat("Hi, Carter!"));
        adapter.add(new DataChat("Hi, Roslyn!"));
        adapter.add(new DataChat("Hi, Marshall!"));
        adapter.add(new DataChat("Hi, Donnell!"));
        adapter.add(new DataChat("Hi, Sigrid!"));
        ListView listView = findViewById(R.id.messages_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
//                String selectedmovie=myFakeDataset.get(position);
//                Toast.makeText(getApplicationContext(), "Movie Selected : "+selectedmovie,   Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_client_details) {
            // do something
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class myChatAdapter_their extends ArrayAdapter<DataChat> {
        public myChatAdapter_their(Context context, ArrayList<DataChat> my_items) {
            super(context, 0, my_items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DataChat my_items = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_their_message, parent, false);
            }
            TextView chatMessage = (TextView) convertView.findViewById(R.id.chat_message);
            chatMessage.setText(my_items.message);
            return convertView;
        }
    }

    public class myChatAdapter_mine extends ArrayAdapter<DataChat> {
        public myChatAdapter_mine(Context context, ArrayList<DataChat> my_items) {
            super(context, 0, my_items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DataChat my_items = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_my_message, parent, false);
            }
            TextView chatMessage = (TextView) convertView.findViewById(R.id.chat_message);
            chatMessage.setText(my_items.message);
            return convertView;
        }
    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            editText.getText().clear();
            // do something
        }
    }

}
