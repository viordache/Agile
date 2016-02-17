package com.example.agile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class LoginActivity extends Activity {

	TextView t;
	//essentially Main() in other languages. Do NOT to any heavy processing here, spawn new threads.
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//set the target xml layout
		setContentView(R.layout.activity_login);
		t = (TextView)findViewById(R.id.tv);
	    new test().execute();
	}//end onCreate()

	//create a menu when the settings button on the device is pressed (ignore just now)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}//end onCreateOptionsMenu()

	//do something when a menu item from the settings menu is selected (ignore just now)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings)
			return true;
		return super.onOptionsItemSelected(item);
	}//end onOptionsItemSelected()


/* ASYNC */

	public class test extends AsyncTask<Void, Void, String>
	{
		HttpURLConnection urlConnection;
		@Override
		protected String doInBackground(Void... arg0) {
			StringBuilder result = new StringBuilder();
            try {
                URL url = new URL("http://agileteam5.esy.es/api.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            }catch( Exception e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }
            Log.e("test", "here");
            return result.toString();
		}//end doInBackground	
	
		@Override
		protected  void onPostExecute(String result)
		{
			t.setText("Result: "+ result);
		}//end onPostExecute
	}//end Async
}//end activity