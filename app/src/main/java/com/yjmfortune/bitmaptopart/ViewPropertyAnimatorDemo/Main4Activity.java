package com.yjmfortune.bitmaptopart.ViewPropertyAnimatorDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.yjmfortune.bitmaptopart.R;

public class Main4Activity extends AppCompatActivity {
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mTextView = (TextView) findViewById(R.id.mTextView);

    }

    public void onclick1(View v) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "alpha", 1f, 0f, 1f);
//        animator.setDuration(5000);
//        animator.start();

        //只会 执行 alpha(0.5f).setDuration(500) 前面会取消
//        mTextView.animate().alpha(0f).setDuration(1000).alpha(0.5f).setDuration(500);

        mTextView.animate().alpha(0f).setDuration(1000);
    }

    public void onclick2(View v) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "rotation", 0f, 360f);
//        animator.setDuration(5000);
//        animator.start();


        mTextView.animate().rotation(360f).setDuration(1000);
    }

    public void onclick3(View v) {
//        float curTranslationX = mTextView.getTranslationX();
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "translationX", curTranslationX, -500f, curTranslationX);
//        animator.setDuration(5000);
//        animator.start();


        mTextView.animate().translationXBy(-500f).setDuration(1000);
    }

    public void onclick4(View v) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "scaleY", 1f, 3f, 1f);
//        animator.setDuration(5000);
//        animator.start();

        mTextView.animate().scaleY(3f).setDuration(1000);
    }

    public void onclick5(View v) {
        mTextView.animate().x(500).y(500).setDuration(5000)
                .setInterpolator(new BounceInterpolator());

    }


}
