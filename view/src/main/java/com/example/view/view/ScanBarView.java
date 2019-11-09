package com.example.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by taihong on 2019/11/9
 */
public class ScanBarView extends View {

    private final String TAG=ScanBarView.class.getName();

    Paint paint;

    public ScanBarView(Context context) {
        super(context);
    }

    public ScanBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScanBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量出的宽和高
        int width = 0;
        int height = 0;
        // 1. 获取测量模式（Mode）
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // 2. 获取测量大小（Size）
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 3. 通过Mode 和 Size 生成新的SpecMode(这个是由我们给出的尺寸大小和模式生成一个包含这两个信息的int变量，这里这个模式这个参数，传三个常量中的一个)
        int measureSpec=MeasureSpec.makeMeasureSpec(widthSize, widthMode);
        Log.e(TAG,"widthMeasureSpec："+widthMeasureSpec+""+"widthMode："+heightMode+""+"widthSize："+widthSize+""+"width_measureSpec："+measureSpec);
        if (widthMode==MeasureSpec.EXACTLY){

        }else {

        }
        if (heightMode==MeasureSpec.EXACTLY){

        }else {

        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
