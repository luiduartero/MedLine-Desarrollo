package com.example.josejoescobar.medlinenetwork2.test;

import com.example.josejoescobar.medlinenetwork2.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class test extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public test() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Take screenshot
        solo.takeScreenshot();
	}
}
