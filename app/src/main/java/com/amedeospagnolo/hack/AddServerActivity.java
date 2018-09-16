package com.amedeospagnolo.hack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.msfrpc.MsfServerList;
import com.msfrpc.model.RpcServer;

public class AddServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addserver);


        EditText editTextName = findViewById(R.id.edittext_ip);

        MsfServerList msfServerList = MsfApplication.Msf().msfServerList;
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int position = extras.getInt("server_id");
            RpcServer rpcServer = msfServerList.getServerList().get(position);
            editTextName.setText(rpcServer.rpcHost);
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
    }

}
