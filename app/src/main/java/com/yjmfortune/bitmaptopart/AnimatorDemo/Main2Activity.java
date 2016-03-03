package com.yjmfortune.bitmaptopart.AnimatorDemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yjmfortune.bitmaptopart.R;

public class Main2Activity extends AppCompatActivity {
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mTextView = (TextView) findViewById(R.id.mTextView);

    }

    public void onclick1(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "alpha", 1f, 0f, 1f);
        animator.setDuration(5000);
        animator.start();
    }

    public void onclick2(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "rotation", 0f, 360f);
        animator.setDuration(5000);
        animator.start();
    }

    public void onclick3(View v) {
        float curTranslationX = mTextView.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "translationX", curTranslationX, -500f, curTranslationX);
        animator.setDuration(5000);
        animator.start();
    }

    public void onclick4(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "scaleY", 1f, 3f, 1f);
        animator.setDuration(5000);
        animator.start();
    }

    public void onclick5(View v) {
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(mTextView, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mTextView, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mTextView, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        //顺序
        //        animSet.play(rotate).with(fadeInOut).after(moveIn);
        //        moveIn->(rotate,fadeInOut)
        animSet.play(rotate).with(fadeInOut).before(moveIn);
        //       (rotate,fadeInOut)-> moveIn
        animSet.setDuration(5000);
        animSet.start();
//        animSet.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });

        //可以用 AnimatorListenerAdapter 适配一个
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });


    }

    public void onclick6(View v) {

        Animator animator = AnimatorInflater.loadAnimator(Main2Activity.this, R.animator.anim_file);
        animator.setTarget(mTextView);
        animator.start();
    }

    public void onclick7(View v) {

        Animator animator = AnimatorInflater.loadAnimator(Main2Activity.this, R.animator.anim_file1);
        animator.setTarget(mTextView);
        animator.start();
    }

}
