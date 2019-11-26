package com.example.view.view.BezierView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.View;

import com.example.view.R;

public class PathMeasureView extends View {
    private Paint mPaint = new Paint();
    private Paint mLinePaint = new Paint(); //坐标系
    private final Bitmap bitmap;

    public PathMeasureView(Context context) {
        super(context);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(8);

        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStrokeWidth(6);
        //缩小图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制坐标轴
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mLinePaint);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mLinePaint);
        //移动原点到中心位置
        canvas.translate(getWidth() / 2, getHeight() / 2);

        Path path = new Path();
//        path.lineTo(200, 0);
//        path.lineTo(200, 200);
//        path.lineTo(0, 200);
////        canvas.drawPath(path, mPaint);
//        //PathMeasure需要关联Path,forceClosed会影响path测量结果（不会改变原本的path）
//        PathMeasure pathMeasure2 = new PathMeasure();
//        pathMeasure2.setPath(path, true);
//        Log.e("TAG", "forceClosed:true " + pathMeasure2.getLength());
//        PathMeasure pathMeasure = new PathMeasure();
//        pathMeasure.setPath(path, false);
//        Log.e("TAG", "forceClosed:false " + pathMeasure.getLength());
//
//        PathMeasure pathMeasure3 = new PathMeasure(path, false);
//        Log.e("TAG", "pathMeasure3:forceClosed:false " + pathMeasure3.getLength());
//
//        path.reset();
//        path.addCircle(0, 0, 100, Path.Direction.CW);
////        canvas.drawPath(path, mPaint);
//        float[] pos = new float[2];
//        float[] tan = new float[2];
//        //重新绑定path路径
//        pathMeasure3.setPath(path, false);
//        //pos[]表示坐标值
//        //tan[]表示当前点再路径上的切线与X轴正方向的夹角A
//        //tan[0] ==>x==>cosA
//        //tan[1] ==>y==>sinA
//        pathMeasure3.getPosTan(200, pos, tan);
////        canvas.drawCircle(pos[0], pos[1], 10, mPaint);
//        Log.e("TAG", pos[0] + "  " + pos[1] + "  " + tan[0] + "  " + tan[1]);
//        //计算当前点与X轴的夹角
//        double degree = Math.atan2(tan[1], tan[0]) * 180 / Math.PI;   //这个公式 就可以求得当前点的切线和X正半轴的角度
//        Log.e("TAG", "degree:  " + degree);
//        //startWithMoveTo 为true可以保证截取的路径不变形
//        path.reset();
//        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
//        path.addCircle(0, 0, 200, Path.Direction.CW);
//        canvas.drawPath(path, mPaint);
//        Path dst = new Path();
//        //重新匹配Path
//        pathMeasure3.setPath(path, false);
//        //nextCounter
////        Log.e("TAG", "pathMeasure3:nextContour:false " + pathMeasure3.getLength());
//        pathMeasure3.nextContour();
////        Log.e("TAG", "pathMeasure3:nextContour:true " + pathMeasure3.getLength());
//        pathMeasure3.getSegment(0, 1200, dst, true);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawPath(dst, mPaint);

        mFloat += 0.01;
        if (mFloat >= 1) {
            mFloat = 0;
        }

        path.addCircle(0, 0, 200, Path.Direction.CCW);
        canvas.drawPath(path, mPaint);

        PathMeasure pathMeasure = new PathMeasure(path, false);
//        pathMeasure.getPosTan(pathMeasure.getLength() * mFloat, pos, tan);
//        double degree = Math.atan2(tan[1], tan[0]) * 180 / Math.PI;
//        matrix.reset();
//        matrix.postRotate((float) degree, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
//        matrix.postTranslate(pos[0] - bitmap.getWidth() / 2, pos[1] - bitmap.getWidth() / 2);
        //POSITION_MATRIX_FLAG
        //TANGENT_MATRIX_FLAG
        pathMeasure.getMatrix(pathMeasure.getLength() * mFloat, matrix,
                PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        //偏移加旋转
        matrix.preTranslate(-bitmap.getWidth() / 2, -bitmap.getWidth() / 2);
        canvas.drawBitmap(bitmap, matrix, mPaint);
        invalidate();
    }

    private Matrix matrix = new Matrix();
    private float[] pos = new float[2];
    private float[] tan = new float[2];
    private float mFloat;
}
