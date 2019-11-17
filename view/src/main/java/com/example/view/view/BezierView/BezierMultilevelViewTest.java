package com.example.view.view.BezierView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 多阶贝塞尔曲线设置
 */
public class BezierMultilevelViewTest extends View {
    public BezierMultilevelViewTest(Context context) {
        super(context);
        init();
    }

    public BezierMultilevelViewTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierMultilevelViewTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);      //线画笔
    private Paint pointPaint=new Paint(Paint.ANTI_ALIAS_FLAG);//控制点画笔
    private Path mPath=new Path();
    private Path mSystemBezierPath=new Path();

    public void init(){
        mPaint.setAntiAlias(true);  //抗锯齿
        mPaint.setDither(true);     //抗抖动
        mPaint.setStyle(Paint.Style.STROKE); //描边效果
        mPaint.setStrokeWidth(10);     //描边宽度
        mSystemBezierPath.cubicTo(200,700,500,1200,700,200);
        new Thread(new Runnable() {
            @Override
            public void run() {
                initBezier();
            }
        }).start();
    }

    public void initBezier(){
        //控制点XY坐标
 //     现在是4阶
//        float[] xPoints=new float[]{0,300,200,500,700};
//        float[] yPoints=new float[]{0,300,700,1200,200};
        //多阶
//        float[] xPoints=new float[]{0,300,200,500,700,800};
//        float[] yPoints=new float[]{0,300,700,1200,200,800};
        float[] xPoints=new float[]{0,200,500,700};
        float[] yPoints=new float[]{0,700,1200,200};

        int fps=10;
        for (int i=0;i<=fps;i++){
            float pregress=i/(float)fps;        //进度
            float x = calculateBezier(pregress, xPoints);
            float y = calculateBezier(pregress, yPoints);
            //使用链接的方式，当XY变动足够小的情况下，，就是平滑线
            mPath.lineTo(x,y);
            //刷新绘制点
            postInvalidate();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 计算某时刻的贝塞尔所处的值（X或Y）
     * @param t  （0~1） 用于计算每条线的百分比
     * @param values 贝塞尔点集合（X或Y）
     * @return  当前T时刻的贝塞尔所处的点
     */
    public float calculateBezier(float t,float... values){
        int length = values.length;     //阶数
        for (int i = length-1; i >0 ; i--) {
            for (int j=0;j<i;j++){
                //计算
                values[j]=values[j]+(values[j+1]-values[j])*t;
                Log.e("test",t+"计算贝塞尔所处的值："+values[j]);
            }
        }

        //运算时结构保存在第一位，所以我们返回第一位
        Log.e("test","计算贝塞尔所处的值："+values[0]);
        return  values[0];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        canvas.drawPath(mSystemBezierPath,mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            init();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
