package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    public int CLIENT_TAB = 0;
    public int SERVER_TAB = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // tablayout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("clients"));
        tabLayout.addTab(tabLayout.newTab().setText("servers"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // floating buttons
        FloatingActionButton fabClient = findViewById(R.id.fab_client);
        FloatingActionButton fabServer = findViewById(R.id.fab_server);

        fabClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddClientActivity.class);
                context.startActivity(intent);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            }
        });
        fabServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddServerActivity.class);
                context.startActivity(intent);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            }
        });
        int tab_position = tabLayout.getSelectedTabPosition();
        choose_right_fab(tab_position);

        // viewpager (related to tablayout)
        final ViewPager viewPager = findViewById(R.id.pager);
        final pagerAdapter adapter = new pagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                choose_right_fab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }

    public void choose_right_fab(int pos){
        FloatingActionButton fabClient = findViewById(R.id.fab_client);
        FloatingActionButton fabServer = findViewById(R.id.fab_server);
        if (pos == SERVER_TAB) {
            fabClient.hide();
            fabServer.show();
        }
        else if (pos == CLIENT_TAB){
            fabClient.show();
            fabServer.hide();
        } else {
            fabClient.hide();
            fabServer.hide();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Context context = MainActivity.this;
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            context.startActivity(intent);
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            return true;
        } else if (id == R.id.action_info){
            Context context = MainActivity.this;
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            context.startActivity(intent);
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            return true;
        } else if (id == R.id.actions_add_server){
            Context context = MainActivity.this;
            Intent intent = new Intent(MainActivity.this, AddServerActivity.class);
            context.startActivity(intent);
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            return true;
        } else if (id == R.id.actions_add_client) {
            Context context = MainActivity.this;
            Intent intent = new Intent(MainActivity.this, AddClientActivity.class);
            context.startActivity(intent);
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            return true;
        } else if (id == R.id.action_donation) {
            Context context = MainActivity.this;
            Intent intent = new Intent(MainActivity.this, DonationActivity.class);
            context.startActivity(intent);
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            return true;
        } else if (id == R.id.action_logout) {
            Context context = MainActivity.this;
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            context.startActivity(intent);
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
