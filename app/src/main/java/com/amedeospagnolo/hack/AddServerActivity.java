package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.msfrpc.MsfServerList;
import com.msfrpc.model.RpcServer;

public class AddServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addserver);

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

        MsfServerList msfServerList = MsfApplication.Msf().msfServerList;
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int position = extras.getInt("position");
            RpcServer rpcServer = msfServerList.getServerList().get(position);

            setTitle("Edit Server");

            EditText edit_text_host = findViewById(R.id.edit_text_host);
            edit_text_host.setText(rpcServer.rpcHost);

            EditText edit_text_port = findViewById(R.id.edit_text_port);
            edit_text_port.setText(String.valueOf(rpcServer.rpcPort));

            EditText edit_text_user = findViewById(R.id.edit_text_user);
            edit_text_user.setText(rpcServer.rpcUser);

            EditText edit_text_passwd = findViewById(R.id.edit_text_passwd);
            edit_text_passwd.setText("*****");

            Button submit_server = findViewById(R.id.submit_server);
            submit_server.setText("Save");
            submit_server.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // to do: logic save changes
                    Context context = AddServerActivity.this;
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
                }
            });

        } else {
            setTitle("Add Server");

            Button submit_server = findViewById(R.id.submit_server);
            submit_server.setText("Add Server");
            submit_server.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // to do: logic add server
                    Context context = AddServerActivity.this;
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
                }
            });
        }

    }

}
