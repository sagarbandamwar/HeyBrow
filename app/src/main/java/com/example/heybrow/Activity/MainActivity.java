package com.example.heybrow.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.heybrow.Adapter.SectionPagerAdapter;
import com.example.heybrow.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    // declare instance
    private FirebaseAuth mAuth;
    private Context mContext;
    private Toolbar mToolBar;


    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private SectionPagerAdapter pagerAdapter;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initrialize instancce
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        // toolbar

        mToolBar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("HeyBrow Chat");
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));

        viewPager = findViewById(R.id.pager);

        pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(viewPager);

        // set color to selected tabs
        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor(String.valueOf(R.color.tab_underline)));
        mTabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        mTabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));


    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseApp.initializeApp(this);
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.d(TAG, "  " + currentUser);
        if (currentUser == null) {
            sendToStart();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            sendToStart();
        }
        return true;
    }

    private void sendToStart() {

        startActivity(new Intent(MainActivity.this, StartActivity.class));
        finish();
    }

}
