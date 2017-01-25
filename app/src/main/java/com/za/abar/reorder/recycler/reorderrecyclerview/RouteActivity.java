package com.za.abar.reorder.recycler.reorderrecyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.za.abar.reorder.recycler.reorderrecyclerview.adapters.OrderAdapter;
import com.za.abar.reorder.recycler.reorderrecyclerview.adapters.SectionsPagerAdapter;

public class RouteActivity extends AppCompatActivity {

  private static final String TAG = RouteActivity.class.getSimpleName();
  public boolean isSortEnabled = false;
  private OrderAdapter mOrderAdapter;
  private FloatingActionButton mFab;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    setContentView(R.layout.activity_routes_example);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.
    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);

    // Set up the ViewPager with the sections adapter.
    ViewPager viewPager = (ViewPager) findViewById(R.id.container);
    viewPager.setAdapter(sectionsPagerAdapter);

    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);

    mFab = (FloatingActionButton) findViewById(R.id.fab);
    mFab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        isSortEnabled = false;
        if (mOrderAdapter != null) {
          mOrderAdapter.notifyDataSetChanged();
        }
        Log.d(TAG, "onClick: " + isSortEnabled);
        view.setVisibility(View.GONE);
      }
    });

    Log.d(TAG, "onCreate: " + isSortEnabled);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_routes_example, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    switch (id) {
      case R.id.action_settings:
        break;
      case R.id.action_reorder:
        isSortEnabled = true;
        if (mOrderAdapter != null) {
          mOrderAdapter.notifyDataSetChanged();
        }
        mFab.setVisibility(View.VISIBLE);
        mFab.setImageResource(R.drawable.ic_reorder);

        break;
    }


    return super.onOptionsItemSelected(item);
  }

  public void setAdapter(OrderAdapter orderAdapter) {
    mOrderAdapter = orderAdapter;
  }


}
