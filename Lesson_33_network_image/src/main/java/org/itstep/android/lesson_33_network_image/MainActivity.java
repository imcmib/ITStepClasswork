package org.itstep.android.lesson_33_network_image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class MainActivity extends ActionBarActivity {

    public static final String URL =
            "http://www.menucool.com/slider/prod/image-slider-2.jpg";

    private static final String TAG = MainActivity.class.getSimpleName();

    private DisplayImageOptions mImageOptions;
    private ImageView mImageView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final App app = (App) getApplication();

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                loadImage();
            }
        });

        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mImageOptions = new DisplayImageOptions.Builder()
                        .cloneFrom(app.mDisplayImageOptions)
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .showImageOnLoading(android.R.drawable.stat_sys_download)
                        .showImageOnFail(android.R.drawable.stat_notify_error)
                        .showImageForEmptyUri(android.R.drawable.ic_dialog_info)
                        .resetViewBeforeLoading(true)
                        .preProcessor(new BitmapProcessor() {
                            @Override
                            public Bitmap process(final Bitmap bitmap) {
                                final Bitmap mutableBitmap = Bitmap.createBitmap(bitmap)
                                                                   .copy(Bitmap.Config.ARGB_8888,
                                                                           true);

                                bitmap.recycle();

                                final Canvas canvas = new Canvas(mutableBitmap);
                                final Paint paint = new Paint();
                                paint.setColor(Color.BLACK);
                                paint.setTextSize(130);
                                canvas.drawText("Test", 10, 140, paint);

                                return mutableBitmap;
                            }
                        })
                        .build();
            }
        });

        findViewById(R.id.get_uri_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ImageLoader.getInstance().cancelDisplayTask(mImageView);
            }
        });

        findViewById(R.id.picasso_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Picasso.with(MainActivity.this)
                       .load(URL)
                       .resize(100, 100)
                       .rotate(90)
                       .into(mImageView, new Callback() {
                           @Override
                           public void onSuccess() {

                           }

                           @Override
                           public void onError() {

                           }
                       });
            }
        });
    }

    private void loadImage() {
        ImageLoader.getInstance().denyNetworkDownloads(true);
        ImageLoader.getInstance().displayImage(URL, mImageView, mImageOptions, listener, progressListener);
    }

    final ImageLoadingProgressListener progressListener = new ImageLoadingProgressListener() {
        @Override
        public void onProgressUpdate(final String imageUri, final View view,
                                     final int current, final int total) {
            mButton.setText(String.format("%d/%d", current, total));
        }
    };

    private final ImageLoadingListener listener = new ImageLoadingListener() {
        @Override
        public void onLoadingStarted(final String s, final View view) {
            Log.i(TAG, "onLoadingStarted: url=" + s + ", view: " + view);
        }

        @Override
        public void onLoadingFailed(final String s, final View view, final FailReason failReason) {
            Log.w(TAG,
                    "onLoadingFailed: url=" + s + ", view: " + view + ", reason: " + failReason
                            .getType());
        }

        @Override
        public void onLoadingComplete(final String s, final View view, final Bitmap bitmap) {
            Log.i(TAG, "onLoadingComplete: url=" + s + ", view: " + view);
        }

        @Override
        public void onLoadingCancelled(final String s, final View view) {
            Log.i(TAG, "onLoadingCancelled: url=" + s + ", view: " + view);
        }
    };
}