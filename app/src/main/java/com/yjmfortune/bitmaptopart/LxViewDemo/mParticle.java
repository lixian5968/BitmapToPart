package com.yjmfortune.bitmaptopart.LxViewDemo;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by azz on 15/11/19.
 * 爆破粒子
 */
public class mParticle {

    public static final int PART_WH = 8; //默认小球宽高
    float cx; //center x of circle
    float cy; //center y of circle
    float radius;
    int color;
    float alpha;
    Rect mBound;
    static Random random = new Random();
    public static mParticle generateParticle(int color, Rect bound, Point point) {
        int row = point.y; //行是高
        int column = point.x; //列是宽
        mParticle particle = new mParticle();
        particle.mBound = bound;
        particle.color = color;
        particle.alpha = 1f;
        particle.radius = PART_WH;
        particle.cx = bound.left + PART_WH * column+PART_WH/2;
        particle.cy = bound.top + PART_WH * row+PART_WH/2;
        return particle;
    }

    public void advance(float factor) {
        cx = cx + factor * random.nextInt(mBound.width()) * (random.nextFloat() - 0.5f);
        cy = cy + factor * random.nextInt(mBound.height() / 2);
        radius = radius - factor * random.nextInt(2);
        alpha = (1f - factor) * (1 + random.nextFloat());
    }

}
