package com.example.rollbookapp;


import java.io.FileNotFoundException;

import classes.StudentManager;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;


public class MainActivity extends FragmentActivity implements  TabListener {

	ViewPager viewPager;
	ActionBar actionBar;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//leer del file
		try {

			StudentManager.read(openFileInput(StudentManager.direction));

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}



	

		viewPager = (ViewPager)findViewById(R.id.pager);

		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		actionBar = getActionBar();

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.Tab tab1 = actionBar.newTab();
		tab1.setText("Students");
		tab1.setTabListener(this);
		
		ActionBar.Tab tab2 = actionBar.newTab();
		tab2.setText("Stats");
		tab2.setTabListener(this);

		actionBar.addTab(tab1);
		actionBar.addTab(tab2);

		

	}

	

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		ActionBar action = getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color.rgb(51, 102, 153)));
		action.setTitle("RollBook App");
		
		
		return true;
	}
	
	
}

class MyAdapter extends FragmentPagerAdapter{

	public MyAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment= null;

		if(arg0==0)
		{
			fragment = new FragmentStudent();
		}
		if(arg0==1)
		{
			fragment = new FragmentStats();
		}


		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}


}

