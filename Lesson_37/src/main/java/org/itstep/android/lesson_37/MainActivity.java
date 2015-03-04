package org.itstep.android.lesson_37;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ExTextView textView = (ExTextView) findViewById(R.id.textView);
                textView.setCustomFont(FontUtils.FONT_2);
            }
        });

        final InputStream inputStream = getResources().openRawResource(R.raw.dafont);
        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);

        findViewById(R.id.refresh_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                RefreshActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.sliding_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                SlidingActivity.startActivity(MainActivity.this);
            }
        });
    }
}