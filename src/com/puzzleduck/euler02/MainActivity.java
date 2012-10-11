package com.puzzleduck.euler02;

import android.app.*;
import android.content.Intent;
import android.net.Uri;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.text.*;
import android.text.style.*;
import java.util.*;



import android.graphics.*;
import android.util.*;

public class MainActivity extends Activity implements View.OnClickListener
{
	private static EditText mainText;
	private static EditText count;
	private static TextView resultView;
	private static ToggleButton evenodd;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		Button compute = (Button)findViewById(R.id.compute);
		compute.setOnClickListener(this);
	    evenodd = (ToggleButton)findViewById(R.id.evenodd);
		evenodd.setChecked(true);
		
		mainText = (EditText) findViewById(R.id.displaytext);
		mainText.setHorizontalScrollBarEnabled(true);
		mainText.setHorizontallyScrolling(true);
		mainText.setTextSize(11);
		mainText.setTextColor(Color.WHITE);
    	resultView = (TextView) findViewById(R.id.result);
		count = (EditText)findViewById(R.id.count);
	
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menulayout, menu);
 
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.aboutapp:
            setContentView(R.layout.aboutapp);

    		//listeners for menu
          findViewById(R.id.githubButton).setOnClickListener( (OnClickListener) this);
          findViewById(R.id.visitEulerButton).setOnClickListener( (OnClickListener) this);
          findViewById(R.id.photoButton).setOnClickListener( (OnClickListener) this);
            return true;
        case R.id.aboutdev:
            setContentView(R.layout.aboutdev);
    		Button appsButton = (Button)findViewById(R.id.appsButton);
    		appsButton.setOnClickListener(this);
            findViewById(R.id.appsButton).setOnClickListener( (OnClickListener) this);
            findViewById(R.id.dropboxButton).setOnClickListener( (OnClickListener) this);
            findViewById(R.id.githubButton).setOnClickListener( (OnClickListener) this);
            findViewById(R.id.contactDevButton).setOnClickListener( (OnClickListener) this);
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    
    
	public void onClick(View p1)
	{
		if(p1.getId() == R.id.compute)
		{
            int countLimit = Integer.parseInt(count.getText().toString());
		//	String separator = "";

			ArrayList<Integer> strikesStart = new ArrayList<Integer>();
			ArrayList<Integer> strikesEnd = new ArrayList<Integer>();
			int sumTotal = 0;
			StringBuffer unmarkedText = new StringBuffer();

			int isEven = evenodd.isChecked() ? 0 : 1;
			int fibNumber = fib(1);
			for( int i = 1; fibNumber < countLimit; i++)
			{	

				int startTag = unmarkedText.length();

				unmarkedText.append("\nfib("+i + ") is: " + fibNumber);
				if(fibNumber%2 == isEven)
				{
					sumTotal += fibNumber;
				}	else {

						strikesStart.add(new Integer(startTag));
						strikesEnd.add(new Integer(unmarkedText.length()));
				}
			//prepare next fib number
			    fibNumber = fib(i+1);
			}

			SpannableString htmlText = new SpannableString(unmarkedText);
			for (int index = 0; index < strikesStart.size(); index++)
			{
				htmlText.setSpan(new  ForegroundColorSpan(Color.RED), strikesStart.get(index), strikesEnd.get(index) , 0);
			}

			htmlText.setSpan(new  UnderlineSpan(), 0, htmlText.length() , 0);
			mainText.setText(htmlText);
			resultView.setText("\n\nGrand total: " + sumTotal + "\n\n");
		
		}//compute
		


        if(p1.getId() == R.id.contactDevButton)
        {
          Intent intent = new Intent(Intent.ACTION_SEND);
          intent.setType("plain/text");
          intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"puzzleduck+euler01@gmail.com"});
          intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "User feedback for Eular 02: ");
          intent = Intent.createChooser(intent, "Thank you for your feedback, please select an app:");
        	startActivity(intent);
        }//if contact button

        
        if(p1.getId() == R.id.appsButton)
        {
        	Intent intent = new Intent(Intent.ACTION_VIEW);
        	intent.setData(Uri.parse("market://search?q=PuZZleDucK Industries"));
        	startActivity(intent);
        }//if apps button

        if(p1.getId() == R.id.dropboxButton)
        {
        	Intent intent = new Intent(Intent.ACTION_VIEW);
        	intent.setData(Uri.parse("http://db.tt/41Y5NAS"));
        	startActivity(intent);
        }//if apps button

        if(p1.getId() == R.id.githubButton)
        {
        	Intent intent = new Intent(Intent.ACTION_VIEW);
        	intent.setData(Uri.parse("https://github.com/PuZZleDucK/Euler02"));
        	startActivity(intent);
        }//if apps button

        if(p1.getId() == R.id.visitEulerButton)
        {
        	Intent intent = new Intent(Intent.ACTION_VIEW);
        	intent.setData(Uri.parse("http://projecteuler.net/problems"));
        	startActivity(intent);
        }//if apps button
        
        if(p1.getId() == R.id.photoButton)
        {
        	Intent intent = new Intent(Intent.ACTION_VIEW);
        	intent.setData(Uri.parse("http://es.fotopedia.com/users/1cn802s648dlg"));
        	startActivity(intent);
        }//if apps button
        
        
		
	}//click

	private int fib(int i)
	{
		if(i < 3)
		{
			return 1;
			
		}else{
			return fib(i-1) + fib(i-2);
		}
		//only getting to 40000
		// remove some text... now up to 60000
		// woot... it ran
	}
	
	
}
	
	
	
