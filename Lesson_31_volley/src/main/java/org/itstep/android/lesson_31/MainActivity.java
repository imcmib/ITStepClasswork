package org.itstep.android.lesson_31;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends ActionBarActivity {

	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTextView = (TextView) findViewById(R.id.text_view);

		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				getGoogleHtml();
			}
		});
	}

	private void getGoogleHtml() {
		final RequestQueue requestQueue = Volley.newRequestQueue(this);

		ModelRequest request = new ModelRequest(new Response.Listener<Model>() {
					@Override
					public void onResponse(final Model response) {
						mTextView.setText(response.getOne());
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(final VolleyError error) {
						mTextView.setText(error.getMessage());
					}
				});

		requestQueue.add(request);
	}
}