package org.itstep.android.lesson_44;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gcm.subscribe(this);

        final EditText idEditText = (EditText) findViewById(R.id.id_edit_text);

        findViewById(R.id.get_id_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String registrationId = Gcm.getRegistrationId(MainActivity.this);
                Log.i(TAG, "registrationId: " + registrationId);
                idEditText.setText(registrationId);
            }
        });
    }
}
