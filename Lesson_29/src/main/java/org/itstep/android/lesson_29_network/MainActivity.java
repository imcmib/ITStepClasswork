package org.itstep.android.lesson_29_network;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textEditorButton).setOnClickListener(this);
        findViewById(R.id.dirsButton).setOnClickListener(this);
        findViewById(R.id.filesButton).setOnClickListener(this);
        findViewById(R.id.jsonButton).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.textEditorButton:
                TextEditorActivity.startActivity(this);
                break;
            case R.id.dirsButton:
                DirsActivity.startActivity(this);
                break;
            case R.id.filesButton:
                FilesActivity.startActivity(this);
                break;
			case R.id.jsonButton:
				JsonActivity.startActivity(this);
				break;
			default:
                Log.w(TAG, "Unhandled onClick event for view: " + getResources()
                        .getResourceName(view.getId()));
                break;
        }
    }
}
