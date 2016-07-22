package com.selfviewdemo.admin.selfviewapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SelfTextView tv1 = ((SelfTextView) findViewById(R.id.tv1));
        tv1.setmTitleText(1.15f, 2);
        tv1.setDuration(2000);
        tv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tv1.setmTitleText(1.15f, 2);
                tv1.requestLayout();
            }

        });

        final SelfTextView tv2 = ((SelfTextView) findViewById(R.id.tv2));
        tv2.setmTitleText(13624.34f, 2);
        tv2.setDuration(2000);
        tv2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tv2.setmTitleText(136.34f, 2);
                tv2.requestLayout();
            }

        });

        final SelfTextView tv3 = ((SelfTextView) findViewById(R.id.tv3));
        tv3.setmTitleText(0.6564f, 4);
        tv3.setDuration(2000);
        tv3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tv3.setmTitleText(0.6564f, 4);
                tv3.requestLayout();
            }

        });

        final SelfTextView tv4 = ((SelfTextView) findViewById(R.id.tv4));
        tv4.setmTitleText(335.12f, 2);
        tv4.setDuration(2000);
        tv4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tv4.setmTitleText(335.12f, 2);
                tv4.requestLayout();
            }

        });

        final TextView refresh = (TextView) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setmTitleText(1.15f, 2);
                tv1.requestLayout();

                tv2.setmTitleText(13643.34f, 2);
                tv2.requestLayout();

                tv3.setmTitleText(0.6564f, 4);
                tv3.requestLayout();

                tv4.setmTitleText(335.12f, 2);
                tv4.requestLayout();

                refresh.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.btn_anim));
            }
        });
    }
}
