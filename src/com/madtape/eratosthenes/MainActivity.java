package com.madtape.eratosthenes;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.OnClickListener;
import java.util.LinkedList;
import java.util.Calendar;

public class MainActivity extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Button btn = (Button)findViewById(R.id.findPrime);
		btn.setOnClickListener(this);
    }

	@Override
	public void overridePendingTransition(int enterAnim, int exitAnim)
	{
		// TODO: Implement this method
		super.overridePendingTransition(enterAnim, exitAnim);
	}

	@Override
	public void onClick(View p1)
	{
		EditText begin = (EditText)findViewById(R.id.begin);
		EditText end = (EditText)findViewById(R.id.end);
		final int min = Integer.parseInt(begin.getText().toString());
		final int max = Integer.parseInt(end.getText().toString());
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Eratosthenes era = new Eratosthenes();
				long begin = 0;
				long end = 0;
				begin = Calendar.getInstance().getTimeInMillis();
				LinkedList<Integer> result = era.findPrimes(min, max);
				end = Calendar.getInstance().getTimeInMillis();
				final StringBuilder msg = new StringBuilder();
				for(Integer num: result) {
					msg.append(num);
					msg.append(' ');
				}
				msg.append("\ncount: " + result.size());
				msg.append("\ntime: " + (end - begin) + "[ms]");
				
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						TextView tv = (TextView)findViewById(R.id.primeView);
						tv.setText(msg.toString());
					}
				});
			}
		}).start();
	}
}
