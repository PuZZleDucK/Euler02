package com.puzzleduck.euler02;

import android.app.*;
import android.os.*;
import android.view.*;
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
	
	
	
