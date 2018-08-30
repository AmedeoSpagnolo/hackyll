package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ClientActivity extends AppCompatActivity {
    private EditText editText;
    ArrayList<String> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        editText = findViewById(R.id.myNewMessageBox);

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

        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        UsersAdapter adapter = new UsersAdapter(this, arrayOfUsers);
        User newUser = new User("Nathan", "San Diego");
        adapter.add(newUser);

        ListView listView = (ListView) findViewById(R.id.messages_view);
        listView.setAdapter(adapter);

//        // register onClickListener to handle click events on each item
//        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            // argument position gives the index of item which is clicked
//            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
//            {
//                String selectedmovie=movies.get(position);
//                Toast.makeText(getApplicationContext(), "Movie Selected : "+selectedmovie,   Toast.LENGTH_LONG).show();
//            }
//        });

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
    public class UsersAdapter extends ArrayAdapter<User> {
        public UsersAdapter(Context context, ArrayList<User> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            User user = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_their_message, parent, false);
            }
            // Lookup view for data population
            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
            TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
            // Populate the data into the template view using the data object
            tvName.setText(user.name);
            tvHome.setText(user.hometown);
            // Return the completed view to render on screen
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
