package com.example.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

/**
 * Created by taihong on 2019/11/9
 */
public class TestView extends SurfaceView {

    private final String TAG= TestView.class.getName();

    Paint mPaint;

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {

    }

    private void initPaint() {
        mPaint=new Paint();
        // 设置最基本的属性
        // 设置画笔颜色
        // 可直接引入Color类，如Color.red等
//        mPaint.setColor(int color);
        // 设置画笔模式
        mPaint.setStyle(Paint.Style.STROKE);
        // Style有3种类型：
        // 类型1：Paint.Style.FILLANDSTROKE（描边+填充）
        // 类型2：Paint.Style.FILL（只填充不描边）
        // 类型3：Paint.Style.STROKE（只描边不填充）
        // 具体差别请看下图：
        // 特别注意：前两种就相差一条边
        // 若边细是看不出分别的；边粗就相当于加粗

        //设置画笔的粗细
//        mPaint.setStrokeWidth(float width)
        // 如设置画笔宽度为10px
        mPaint.setStrokeWidth(10f);

        // 不常设置的属性
        // 得到画笔的颜色
//        mPaint.getColor()
        // 设置Shader
        // 即着色器，定义了图形的着色、外观
        // 可以绘制出多彩的图形
        // 具体请参考文章：http://blog.csdn.net/iispring/article/details/50500106
//        Paint.setShader(Shader shader)

        //设置画笔的a,r,p,g值
//        mPaint.setARGB(int a, int r, int g, int b)
        //设置透明度
//        mPaint.setAlpha(int a)
        //得到画笔的Alpha值
//        mPaint.getAlpha()


        // 对字体进行设置（大小、颜色）
        //设置字体大小
//        mPaint.setTextSize(float textSize)

        // 文字Style三种模式：
//        mPaint.setStyle(Style style);
        // 类型1：Paint.Style.FILLANDSTROKE（描边+填充）
        // 类型2：Paint.Style.FILL（只填充不描边）
        // 类型3：Paint.Style.STROKE（只描边不填充）

        // 设置对齐方式
//        setTextAlign（）
        // LEFT：左对齐
        // CENTER：居中对齐
        // RIGHT：右对齐

        //设置文本的下划线
//        setUnderlineText(boolean underlineText)

        //设置文本的删除线
//        setStrikeThruText(boolean strikeThruText)

        //设置文本粗体
//        setFakeBoldText(boolean fakeBoldText)

        // 设置斜体
//        Paint.setTextSkewX(-0.5f);


        // 设置文字阴影
//        Paint.setShadowLayer(5,5,5,Color.YELLOW);
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
        //如EXACTLY的模式设置：1向左进位30 = 01后跟30个0 ，即01 00000
        int measureSpec=MeasureSpec.makeMeasureSpec(widthSize, widthMode);
        Log.e(TAG,"widthMeasureSpec："+widthMeasureSpec+""+"widthMode："+heightMode+""+"widthSize："+widthSize+""+"width_measureSpec："+measureSpec);
        Log.e(TAG,"widthMeasureSpec："+widthMeasureSpec+""+"widthMode："+heightMode+""+"widthSize："+widthSize+""+"width_measureSpec："+Integer.toBinaryString(measureSpec));
        if (widthMode==MeasureSpec.EXACTLY){
            Log.e(TAG,"宽为精确EXACTLY模式");
        }else  if (widthMode==MeasureSpec.AT_MOST){
            Log.e(TAG,"宽为自适应模式AT_MOST模式");
        }
        if (heightMode==MeasureSpec.AT_MOST){

        }else {

        }
        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
