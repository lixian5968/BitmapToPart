package com.yjmfortune.bitmaptopart.LxViewDemo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lixian on 2016/3/2.
 */
public class mImageView extends View {

    private mParticle[][] mParticles;
    Paint mPaint;
    Bitmap bitmap;

    public void setmParticles(mParticle[][] mParticles) {
        this.mParticles = mParticles;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public mImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    public mImageView(Context context) {
        super(context);
        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mParticles != null) {
            for (mParticle[] particle : mParticles) {
                for (mParticle p : particle) {
                    mPaint.setColor(p.color);
                    canvas.drawCircle(p.cx, p.cy, p.radius, mPaint);
                }
            }
        }

    }


    public void startMyAnimation(final ImageView imageView) {
        final ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
        anim.setDuration(2000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = (float) animation.getAnimatedValue();
                for (mParticle[] particle : mParticles) {
                    for (mParticle p : particle) {
                        p.advance(fraction);
                    }
                }
                Log.e("AnimatedValue", (float) animation.getAnimatedValue() + "");
                invalidate();
            }
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                imageView.setVisibility(View.GONE);
                setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                imageView.setVisibility(View.VISIBLE);
                setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();




    }


}
