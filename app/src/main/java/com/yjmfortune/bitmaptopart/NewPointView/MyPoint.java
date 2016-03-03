package com.yjmfortune.bitmaptopart.NewPointView;

import android.graphics.Rect;

import java.util.Random;

/**
 * Created by lixian on 2016/3/3.
 */
public class MyPoint {

    public static int mWH = 8;
    public static int mHG = 8;

    //x 轴坐标
    float x;
    //y 轴坐标
    float y;
    //颜色值
    int color;
    //半径
    float Radio;
    //透明度
    float alpay;

    //整体的矩形
    Rect rect;

    static Random random = new Random();

    public MyPoint(float x, float y, int color, int radio, float alpay, Rect rect) {
        this.x = x;
        this.y = y;
        this.color = color;
        Radio = radio;
        this.alpay = alpay;
        this.rect = rect;
    }

    public void updateByTime(float factor) {
        x = (float) (x + factor * rect.width() * (random.nextFloat() - 0.5));
        y = y +factor*random.nextInt(rect.height()/2);
        Radio = Radio - factor*random.nextInt(2);
        alpay = (1-factor)*(1+random.nextFloat());
    }
}
