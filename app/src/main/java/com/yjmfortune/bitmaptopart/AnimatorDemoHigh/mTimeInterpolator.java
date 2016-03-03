package com.yjmfortune.bitmaptopart.AnimatorDemoHigh;

import android.animation.TimeInterpolator;
import android.util.Log;

/**
 * Created by lixian on 2016/3/2.
 */
public class mTimeInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {

        Log.e("lx",input+"");
        //0.0034  每次增加 0.0034

        // 曲线变化了
        float result;
        if (input <= 0.5) {
            result = (float) (Math.sin(Math.PI * input)) / 2;
        } else {
            result = (float) (2 - Math.sin(Math.PI * input)) / 2;
        }

        return result;
    }
}
