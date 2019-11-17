package com.example.view.view.BezierView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BezierViewTest extends View {
    public BezierViewTest(Context context) {
        super(context);
        init();
    }

    public BezierViewTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierViewTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);      //线画笔
    private Paint pointPaint=new Paint(Paint.ANTI_ALIAS_FLAG);//控制点画笔
    private Path mPath=new Path();

    public void init(){
        mPaint.setAntiAlias(true);  //抗锯齿
        mPaint.setDither(true);     //抗抖动
        mPaint.setStyle(Paint.Style.STROKE); //描边效果
        mPaint.setStrokeWidth(10);     //描边宽度

        pointPaint.setAntiAlias(true);  //抗锯齿
        pointPaint.setDither(true);     //抗抖动
        pointPaint.setStyle(Paint.Style.STROKE); //描边效果
        pointPaint.setStrokeWidth(20);     //描边宽度
        pointPaint.setColor(Color.RED);
        //一阶贝塞尔曲线
//        mPath.moveTo(100,100);
        mPath.lineTo(300,300);
        //二阶贝塞尔曲线    主要设置的是控制点到终点的路径
//        mPath.quadTo(500,0,700,300);  //控制点XY坐标和终点XY坐标（如果没有为执行moveTo（）调用此轮廓，第一个点自动设置为（0,0））
        //quadTo的相对实现  相对于上一个点去设置贝塞尔曲线的控制点和终点坐标
        //但坐标是相对于上一个点(即现在的300，300)在这个轮廓上。如果没有上一点，则移动到（0,0）自动插入。  现在这个基于300,300这个点 要画出quadTo(500,0,700,300)相同的结果而设置的值是 控制点x=500-300 y=0-300 终点x=700-300 y=300-300
        mPath.rQuadTo(200,-300,400,0);
        /**
         * quadTo与rQuadTo的区别
         * quadTo基于原始位置设置，如果上一个点发生改变控制点和终点都需要更改
         * rQuadTo基于上一个点位置设置  如果上一个点发生改变控制点和终点都不需要更改
         */
        mPath.moveTo(400,800);
        //三阶贝塞尔曲线
//        mPath.cubicTo(500,600,700,1200,800,800);
        mPath.rCubicTo(100,-200,300,400,400,0);     //基于cubicTo的效果   每个值拿cubicTo的值减去moveTo的坐标值
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
        canvas.drawPoint(500,0,pointPaint);       //画除控制点
    }
}
