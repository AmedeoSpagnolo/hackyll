package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    public int CLIENT_TAB = 0;
    public int SERVER_TAB = 1;
    public int LAST_TAB;

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // TabLayout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("clients"));
        tabLayout.addTab(tabLayout.newTab().setText("servers"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // FloatingActionButton Client
        FloatingActionButton fabClient = findViewById(R.id.fab_client);
        fabClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddClientActivity.class);
                context.startActivity(intent);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            }
        });

        // FloatingActionButton Server
        FloatingActionButton fabServer = findViewById(R.id.fab_server);
        fabServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddServerActivity.class);
                context.startActivity(intent);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            }
        });

        // viewpager (related to tablayout)
        final ViewPager viewPager = findViewById(R.id.pager);
        final pagerAdapter adapter = new pagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                choose_right_fab(tab.getPosition());
                Context context = MainActivity.this;
                SharedPreferences sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPref.edit();
                edit.putInt("lasttab", tab.getPosition());
                edit.apply();
                LAST_TAB = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }

    @Override
    protected void onResume() {

        int active_tab = sharedPref.getInt("lasttab", 0);
        LAST_TAB = active_tab;
        final ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setCurrentItem(LAST_TAB);
        choose_right_fab(LAST_TAB);
        super.onResume();
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
