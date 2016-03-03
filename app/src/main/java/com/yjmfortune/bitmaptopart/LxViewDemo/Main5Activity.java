package com.yjmfortune.bitmaptopart.LxViewDemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.yjmfortune.bitmaptopart.R;

public class Main5Activity extends AppCompatActivity {
    ImageView m5ImageView;
    private mParticle[][] mParticles;
    private static final Canvas mCanvas = new Canvas();
    mImageView  mmImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        m5ImageView = (ImageView) findViewById(R.id.m5ImageView);
        m5ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rect rect = new Rect();
//                rect.left;rect.top;rect.right;rect.bottom;
                m5ImageView.getGlobalVisibleRect(rect); //得到view相对于整个屏幕的坐标
                rect.offset(0, -getStatusHeight(Main5Activity.this)); //去掉状态栏高度
                //创建bitmap
                Bitmap bitmap = Bitmap.createBitmap(m5ImageView.getWidth(), m5ImageView.getHeight(), Bitmap.Config.ARGB_8888);
                if (bitmap != null) {
                    synchronized (mCanvas) {
                        //bitmap 里面设置图片
                        mCanvas.setBitmap(bitmap);
                        m5ImageView.draw(mCanvas);
                        mCanvas.setBitmap(null); //清除引用
                    }
                }
                mParticles = generateParticles(bitmap, rect);
                mmImageView.setBitmap(bitmap);
                mmImageView.setmParticles(mParticles);
                mmImageView.startMyAnimation(m5ImageView);


            }
        });


        mmImageView= (mImageView) findViewById(R.id.mmImageView);


    }
    private mParticle[][] generateParticles(Bitmap bitmap, Rect bound) {
        int w = bound.width();
        int h = bound.height();

        int partW_Count = w / mParticle.PART_WH;
        //宽的个数
        int partH_Count = h / mParticle.PART_WH;
        //高的个数

        int bitmap_part_w = bitmap.getWidth() / partW_Count;
        //图片中每个宽的长度
        int bitmap_part_h = bitmap.getHeight() / partH_Count;
        //图片中每个高的长度

        mParticle[][] particles = new mParticle[partH_Count][partW_Count];
        //高为一维
        //那么宽度就为二维
        Point point = null;
        for (int row = 0; row < partH_Count; row ++) { //行
            for (int column = 0; column < partW_Count; column ++) { //列
                //取得当前粒子所在位置的颜色
                //获取x，y位置的像素质  返回指定位置的颜色。
                //column * bitmap_part_w + bitmap_part_w/2  如果这样的话 会造成图片 左移动 bitmap_part_w/2 的图像感觉
                int color = bitmap.getPixel(column * bitmap_part_w, row * bitmap_part_h);
                if(color>0){
                    Log.e("","");
                }
                point = new Point(column, row); //x是列，y是行
                particles[row][column] = mParticle.generateParticle(color, bound, point);
            }
        }

        return particles;
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
