package com.example.view.view.BezierView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BezierViewDemoOne extends View {
    //圆的画笔
    private Paint mCirclePaint;
    //圆的半径
    private float mCircleRadius = 40;
    //圆的中心点坐标
    private float mCirclePointX, mCirclePointY;
    //可拖动的高度
    private int mDragHeight = 800;
    //进度值
    private float mProgress;
    //目标宽度
    private int mTargetWidth=200;
    //贝赛尔曲线的路径和画笔
    private Path mPath = new Path();
    private Paint mPathPaint;
    //重心点最终高度，决定控制点的Y坐标
    private int mTargetGravityHeight;
    //角度变化0~135度
    private int mTangentAngle = 120;

    public BezierViewDemoOne(Context context) {
        super(context);
        init();
    }

    public BezierViewDemoOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierViewDemoOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setAntiAlias(true);
        p.setDither(true);
        p.setStyle(Paint.Style.FILL);
        p.setColor(0xFF000000);
        mCirclePaint = p;
        //初始化路径部分画笔
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setAntiAlias(true);
        p.setDither(true);
        p.setStyle(Paint.Style.FILL);
        p.setColor(0xFF000000);
        mPathPaint = p;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画贝塞尔曲线
        canvas.drawPath(mPath, mPathPaint);
        //画圆
        canvas.drawCircle(mCirclePointX, mCirclePointY, mCircleRadius, mCirclePaint);
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMode);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMode);
        int iHeight = (int) ((mDragHeight * mProgress + 0.5f) + getPaddingTop() + getPaddingBottom());
        int iWidth = (int) (2 * mCircleRadius + getPaddingLeft() + getPaddingRight());
        //测量出的宽高值
        int measureWidth, measureHeigth;
        if (widthMode == MeasureSpec.EXACTLY) {        //精确尺寸，控件的宽高指定大小或者为FILL_PARENT
            measureWidth = width;
        } else if (widthMode == MeasureSpec.AT_MOST) {       //最大尺寸，控件的宽高为WRAP_CONTENT，控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸
            measureWidth = Math.min(iWidth, width);
        } else {
            measureWidth = iHeight;
        }
        if (heightMode == MeasureSpec.EXACTLY) {        //精确尺寸，控件的宽高指定大小或者为FILL_PARENT
            measureHeigth = height;
        } else if (heightMode == MeasureSpec.AT_MOST) {       //最大尺寸，控件的宽高为WRAP_CONTENT，控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸
            measureHeigth = Math.min(iHeight, height);
        } else {
            measureHeigth = iHeight;
        }
        setMeasuredDimension(measureWidth, measureHeigth);
    }

    public void setProgress(float progress) {
        mProgress = progress;
        //请求重新测量
        requestLayout();
    }

    /**
     * 当大小改变时触发
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        mCirclePointX=getWidth()>>1;
//        mCirclePointY=getHeight()>>1;
        updatePathLayout();
    }

    /**
     * 更新路径等相关操作
     */
    private void updatePathLayout() {
        //获取进度
        final float progress = mProgress;

        //获取可绘制区域宽度和高度
        final float w = getValueByLine(getWidth(), mTargetWidth, progress);
        final float h = getValueByLine(0, mDragHeight, progress);
        //X对称轴的参数，圆的圆心X
        final float cPointx = w / 2;
        //圆的半径
        final float cRadius = mCircleRadius;
        //圆的圆心Y坐标
        final float cPointy=h-cRadius;
        //控制点结束Y的值
        final float endControlY=mTargetGravityHeight;
        //更新圆的坐标
        mCirclePointX=cPointx;
        mCirclePointY=cPointy;
        final Path path = mPath;
        path.reset();
        path.moveTo(0,0);

    }

    /**
     * 获取当前值
     *
     * @param start
     * @param end
     * @param progress
     * @return
     */
    private float getValueByLine(float start, float end, float progress) {
        return start + (end - start) * progress;
    }

}
