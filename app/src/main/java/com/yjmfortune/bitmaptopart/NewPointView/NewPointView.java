package com.yjmfortune.bitmaptopart.NewPointView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by lixian on 2016/3/3.
 */
public class NewPointView extends View {

    Context ct;
    MyPoint[][] mParticles;
    Paint paint;

    public NewPointView(Context context) {
        super(context);
        ct = context;

        ViewGroup rootView = (ViewGroup) ((Activity) (context)).findViewById(Window.ID_ANDROID_CONTENT);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(this, lp);
        paint = new Paint();

    }

    public NewPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mParticles != null) {
            for (MyPoint[] poins : mParticles) {
                for (MyPoint point : poins) {
                    paint.setColor(point.color);
//                    paint.setAlpha((int) (Color.alpha(point.color) * point.alpay)); //这样透明颜色就不是黑色了
                    paint.setAlpha((int) (point.alpay*point.color));
                    canvas.drawCircle(point.x, point.y, point.Radio, paint);
                }
            }
        }
    }

    public void startPoint(final ImageView newPointImageView) {
        Rect rect = new Rect();
        newPointImageView.getGlobalVisibleRect(rect); //得到view相对于整个屏幕的坐标
        rect.offset(0, -getStatusHeight(ct)); //去掉状态栏高度
        //创建bitmap
        Bitmap bitmap = Bitmap.createBitmap(newPointImageView.getWidth(), newPointImageView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas();
        if (bitmap != null) {
            synchronized (mCanvas) {
                //bitmap 里面设置图片
                mCanvas.setBitmap(bitmap);
                newPointImageView.draw(mCanvas);
                mCanvas.setBitmap(null); //清除引用
            }
        }
        mParticles = generateParticles(bitmap, rect);

        ValueAnimator animator = ValueAnimator.ofFloat(0f,1f);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = (float) animation.getAnimatedValue();
                Log.e("lx","fraction:"+fraction);
                if(mParticles!=null){
                    for (MyPoint[] poins : mParticles) {
                        for (MyPoint point : poins) {
                            point.updateByTime(fraction);
                        }
                    }

                    invalidate();
                }
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                newPointImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                newPointImageView.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    private MyPoint[][] generateParticles(Bitmap bitmap, Rect rect) {
        //因为 bitmap 的大小与 rect的大小相同，所以个数 宽度直接弄成一样的了
        //宽的个数
        int WCount = rect.width() / MyPoint.mWH;
        //高的个数
        int HCount = rect.height() / MyPoint.mHG;
        //如果y轴位第一维度的话  每一个 y 对应着 一行的x
        MyPoint[][] points = new MyPoint[HCount][WCount];
        for (int i = 0; i < HCount; i++) {
            for (int j = 0; j < WCount; j++) {
                //(float x, float y, int color, int radio, float alpay)
                points[i][j] = new MyPoint(
                        rect.left + j * MyPoint.mWH + MyPoint.mWH / 2,
                        rect.top + i * MyPoint.mHG + MyPoint.mHG / 2,
                        bitmap.getPixel(j * MyPoint.mWH, i * MyPoint.mHG),
                        MyPoint.mWH,
                        1,
                        rect
                );
            }
        }
        return points;
    }


    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

}
