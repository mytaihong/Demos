package com.example.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import androidx.annotation.Nullable;

import com.example.view.R;

/**
 * Created by taihong on 2019/11/9
 * SurfaceView自带Canvas（就是缓冲画布），支持在线程中更新Canvas中的内容。
 */
public class ScanBarView extends SurfaceView implements SurfaceHolder.Callback {

    private final String TAG = ScanBarView.class.getName();

    private volatile SurfaceHolder surfaceHolder;
    private Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.scan_icon_scanline);

    Paint paint;

    public ScanBarView(Context context) {
        super(context);
        init();
    }

    public ScanBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScanBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setZOrderOnTop(true);
//        setWillNotDraw(false);        如果使用view的onDraw方法绘制内容把这个方法设置为false  ScanBarView默认设置为true打开的不使用onDraw方法绘制内容而是使用双缓冲画布去绘制内容
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(100);
        paint.setStyle(Paint.Style.STROKE);
    }



    public void start(SurfaceHolder holder) {
        holder.setFormat(PixelFormat.TRANSPARENT);              //设置背景透明  默认为黑色
        Canvas canvas = holder.lockCanvas();        //防止多个线程更新错乱
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        if (canvas != null) {
            Log.e(TAG,"开始绘制");
            //绘图操作
//            canvas.drawBitmap(bitmap,new Matrix(),new Paint());
            canvas.drawBitmap(bitmap,0,0,new Paint());
            // 将画布原点向右移200px，向下移100px
            canvas.translate(0, 100);

            // 参数（src，dst） = 两个矩形区域
// Rect src：指定需要绘制图片的区域（即要绘制图片的哪一部分）
// Rect dst 或RectF dst：指定图片在屏幕上显示(绘制)的区域
// 下面我将用实例来说明

// 实例
//            // 指定图片绘制区域
//            // 仅绘制图片的二分之一
//            Rect src = new Rect(0,0,bitmap.getWidth()/2,bitmap.getHeight());
//
//            // 指定图片在屏幕上显示的区域
//            Rect dst = new Rect(100,100,250,250);
//
//            canvas.drawBitmap(bitmap,src,dst,paint);
//            canvas.drawPoint(0, 300, paint);


            //将缓冲画布释放，并将所画的内容更新到主线程的画布上，显示的显示在屏幕上。
            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG,"surfaceCreated");
        surfaceHolder = holder;
        start(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        surfaceHolder = holder;
        start(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
