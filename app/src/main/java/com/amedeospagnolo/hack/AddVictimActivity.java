package com.amedeospagnolo.hack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddVictimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvictim);

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

        // spinner Server
        Spinner spinnerServer = findViewById(R.id.add_client_spinner_server);
        ArrayAdapter<CharSequence> adapterServer = ArrayAdapter.createFromResource(this,
                R.array.server_array, android.R.layout.simple_spinner_item);
        adapterServer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServer.setAdapter(adapterServer);

        // Spinner Os
        Spinner spinnerOs = findViewById(R.id.add_client_spinner_os);
        ArrayAdapter<CharSequence> adapterOs = ArrayAdapter.createFromResource(this,
                R.array.os_array, android.R.layout.simple_spinner_item);
        adapterOs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOs.setAdapter(adapterOs);

    }


}
