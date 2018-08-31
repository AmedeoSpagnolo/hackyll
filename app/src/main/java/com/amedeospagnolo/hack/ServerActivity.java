package com.amedeospagnolo.hack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ServerActivity extends AppCompatActivity {
    private TextView srName;
    private TextView srIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        srName = findViewById(R.id.this_server_name);
        srIp = findViewById(R.id.this_server_ip);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            srName = null;
            srIp = null;

        } else {
            srName.setText(extras.getString("server_name"));
            srIp.setText(extras.getString("server_ip"));
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
