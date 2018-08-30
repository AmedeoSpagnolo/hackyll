package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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

        // fake data
        ListView moviesList = findViewById(R.id.messages_view);

        movies = new ArrayList<String>();
        movies.add("X-Men");
        movies.add("IRONMAN");
        movies.add("SPIDY");
        movies.add("NARNIA");
        movies.add("LIONKING");
        movies.add("AVENGERS");

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, R.layout.chat_my_message, movies);
        // Set The Adapter
        moviesList.setAdapter(arrayAdapter);

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_client_details) {
            // do something special
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            editText.getText().clear();
        }
    }

}
