package com.selfviewdemo.admin.selfviewapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by admin on 2016/7/14.
 */
public class SelfTextView extends View {
    /**
     * 文本
     */
    private String mTitleText = "0.00";
    /**
     * 文本的颜色
     */
    private int mTitleTextColor = 0xFF666666;
    /**
     * 文本的大小
     */
    private int mTitleTextSize = 12;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;

    private Paint mPaint;

    private int duration = 1000;
    private int width;
    private int height;

    public SelfTextView(Context context) {
        this(context, null);
    }

    public SelfTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelfTextView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mTitleTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SelfTextView);
        mTitleText = typedArray.getString(R.styleable.SelfTextView_titlesText);
        mTitleTextColor = typedArray.getColor(R.styleable.SelfTextView_titlesTextColor, mTitleTextColor);
        mTitleTextSize = typedArray.getDimensionPixelSize(R.styleable.SelfTextView_titlesTextSize, mTitleTextSize);

        typedArray.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setColor(mTitleTextColor);
        mBound = new Rect();

//        this.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setmTitleText(Float.parseFloat(randomText()), 2);
//            }
//        });

    }

    private String randomText() {
        Random random = new Random();
        Set<String> set = new HashSet<String>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt + "");
        }
        StringBuffer sb = new StringBuffer();
        for (String i : set) {
            sb.append(i);
        }

        return sb.toString() + ".00";
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = getPaddingLeft() + getPaddingRight() + widthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            width = getPaddingLeft() + getPaddingRight() + mBound.width();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = getPaddingTop() + getPaddingBottom() + heightSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            height = getPaddingTop() + getPaddingBottom() + mBound.height();
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setAntiAlias(true);
        canvas.drawText(mTitleText, getPaddingLeft() - mBound.left, getPaddingTop() + mBound.height(), mPaint);
        super.onDraw(canvas);
    }

    public void setmTitleText(float text, final int decimalDigits) {
        BigDecimal decimal = new BigDecimal(text + "");
        if (text >= 1) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat((float) Math.pow(10, decimal.setScale(0, RoundingMode.HALF_UP).toString().length() - 1), text);
            valueAnimator.setDuration(duration);
            valueAnimator.start();
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
            alphaAnimation.setDuration(duration);
            startAnimation(alphaAnimation);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    BigDecimal decimal = new BigDecimal(animation.getAnimatedValue() + "");
                    mTitleText = decimal.setScale(decimalDigits, RoundingMode.HALF_UP).toString();
                    invalidate();
                }
            });
        } else if (text > 0 && text < 1) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, text);
            valueAnimator.setDuration(duration);
            valueAnimator.start();
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
            alphaAnimation.setDuration(duration);
            startAnimation(alphaAnimation);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    BigDecimal decimal = new BigDecimal(animation.getAnimatedValue() + "");
                    mTitleText = decimal.setScale(decimalDigits, RoundingMode.HALF_UP).toString();
                    invalidate();
                }
            });
        }
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
