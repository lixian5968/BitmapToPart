package com.yjmfortune.bitmaptopart.NewPointView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.yjmfortune.bitmaptopart.R;

public class Main6Activity extends AppCompatActivity {
    NewPointView point;
    ImageView   newPointImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        newPointImageView = (ImageView) findViewById(R.id.newPointImageView);
        point = new NewPointView(Main6Activity.this);
        newPointImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point.startPoint(newPointImageView);
            }
        });



    }


}
