package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amedeospagnolo.hack.dummy.DummyContent;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // floating button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddServerActivity.class);
                //intent.putExtra(ItemDetailsFragment.ARG_ITEM_ID, item.name);
                context.startActivity(intent);
            }
        });
        fab.hide();

        // tablayout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("VICTIMS"));
        tabLayout.addTab(tabLayout.newTab().setText("SERVERS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

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
                FloatingActionButton fab = findViewById(R.id.fab);
                if (tab.getPosition() == 0) fab.hide();
                else fab.show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void show_add_victim_page (View view) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Context context = view.getContext();
        Intent intent = new Intent(context, AddVictimActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Context context = MainActivity.this;
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            context.startActivity(intent);
            return true;
        } else if (id == R.id.action_info){
            Context context = MainActivity.this;
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            context.startActivity(intent);
            return true;
        } else if (id == R.id.actions_add_server){
            Context context = MainActivity.this;
            Intent intent = new Intent(MainActivity.this, AddServerActivity.class);
            context.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
