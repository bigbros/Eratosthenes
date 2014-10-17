package com.madtape.eratosthenes;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.OnClickListener;
import java.util.LinkedList;

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
				LinkedList<Integer> result = era.findPrimes(min, max);
				String text = "";
				for(Integer num: result) {
					text = text + num + " ";
				}
				final String msg = text;
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						TextView tv = (TextView)findViewById(R.id.primeView);
						tv.setText(msg);
					}
				});
			}
		}).start();
	}
}
