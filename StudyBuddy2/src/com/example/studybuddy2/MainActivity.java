package com.example.studybuddy2;

import android.os.Bundle;
import android.os.CountDownTimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	boolean timer = false;
	
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
	    if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) 
	    {
	    	new AlertDialog.Builder(this)
	        .setTitle("Really Leave?")
	        .setMessage("Shouldn't you be studying?")
	        .setPositiveButton("No I have to go!", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) { 
	            	displayAlert();
	            	//System.exit(0);
	            }
	         })
	        .setNegativeButton("Yes you're right...", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) { 
	                // do nothing
	            }
	         })
	         .show();
	    }
	    return true;
	}
    protected void displayAlert() {
		// TODO Auto-generated method stub
    	new AlertDialog.Builder(this)
        .setTitle("What about the timer?")
        .setMessage("Don't want to get back to work?")
        .setPositiveButton("No I really have to go!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
            
            	System.exit(0);
            }
         })
        .setNegativeButton("Ok fine...", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                // do nothing
            }
         })
         .show();
		
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button b = (Button)findViewById(R.id.timer_start);
        b.setOnClickListener(new View.OnClickListener() {
        
        public void onClick(View v){
        if(timer == false){
	        EditText hoursIn = (EditText)findViewById(R.id.input_hours); 
	        String hoursStr = hoursIn.getText().toString(); 
	        int hours = Integer.parseInt(hoursStr);
	        
	        EditText minutesIn = (EditText)findViewById(R.id.input_minutes); 
	        String minutesStr = minutesIn.getText().toString(); 
	        int minutes = Integer.parseInt(minutesStr);
	        
	        int totalTimeMillis = (hours * 1000 * 60 * 60) + (minutes * 1000 * 60);
	        
	        new CountDownTimer(totalTimeMillis, 1000) {
	        	
	        	TextView time = (TextView) findViewById(R.id.timer);
	        	
	            public void onTick(long millisUntilFinished) {
	            	int seconds = (int) (millisUntilFinished / 1000) % 60 ;
	            	int minutes = (int) ((millisUntilFinished/ (1000*60)) % 60);
	            	int hours   = (int) ((millisUntilFinished/ (1000*60*60)) % 24);
	                time.setText(+hours+" : "+minutes+" : " +seconds);
	            }
	
	            public void onFinish() {
	                time.setText("done!");
	                timer = false;
	            }
	         }.start();
	         timer = true;
        }  
	    }
    });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}