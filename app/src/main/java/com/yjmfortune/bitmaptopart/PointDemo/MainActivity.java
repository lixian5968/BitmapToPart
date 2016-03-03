package com.yjmfortune.bitmaptopart.PointDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.yjmfortune.bitmaptopart.R;

public class MainActivity extends AppCompatActivity {
    ImageView mImageView;
    ExplosionFieldView explosionField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        explosionField = new ExplosionFieldView(this);

        mImageView = (ImageView) findViewById(R.id.mImageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explosionField.reload(v);
            }
        });


    }





}
