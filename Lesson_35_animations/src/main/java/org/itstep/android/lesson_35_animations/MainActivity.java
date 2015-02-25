package org.itstep.android.lesson_35_animations;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final Animation rotateAnimation = new RotateAnimation(0, 360,
//                Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF, 0.5f);
//        rotateAnimation.setRepeatCount(3);
//
//        final Animation scaleAnimation = new ScaleAnimation(1, 2, 1, 2,
//                Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF, 0.5f);
//
//        final AnimationSet set = new AnimationSet(true);
//        set.addAnimation(rotateAnimation);
//        set.addAnimation(scaleAnimation);
//        set.setDuration(2000);
//        set.setInterpolator(new AnticipateInterpolator());
//        set.setFillAfter(true);
//
//        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(final Animation animation) {
//                Log.i(TAG, "onAnimationStart");
//            }
//
//            @Override
//            public void onAnimationEnd(final Animation animation) {
//                Log.i(TAG, "onAnimationEnd");
//            }
//
//            @Override
//            public void onAnimationRepeat(final Animation animation) {
//                Log.i(TAG, "onAnimationRepeat");
//            }
//        });

        final View view1 = findViewById(R.id.text_1_view);
        final View view2 = findViewById(R.id.text_2_view);
        view2.setRotation(45);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
//                view1.startAnimation(rotateAnimation);
//                view2.startAnimation(set);

                animateFrom(view1);
            }
        });

        findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ImageView imageView = (ImageView) findViewById(R.id.image_view);
                imageView.setImageResource(R.drawable.key_animation);

                final AnimationDrawable drawable = (AnimationDrawable) imageView.getDrawable();
                drawable.start();

                //                SecondActivity.startActivity(MainActivity.this);
//                overridePendingTransition(R.anim.alpha_animation,
//                        R.anim.alpha_animation);
            }
        });
    }

    private void animateFrom(final View view) {
        view.animate()
                .alpha(1)
                .rotation(360)
                .scaleX(2)
                .scaleY(2)
                .setDuration(2000)
                .setInterpolator(new BounceInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        animateTo(view);
                    }
                })
                .start();
    }

    private void animateTo(final View view) {
        view.animate()
            .alpha(0)
            .rotation(0)
            .scaleX(1)
            .scaleY(1)
            .setDuration(2000)
            .setInterpolator(new BounceInterpolator())
            .withEndAction(new Runnable() {
                @Override
                public void run() {
                    animateFrom(view);
                }
            })
            .start();
    }
}