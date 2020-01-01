package com.example.screenadaptation;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class ScreenadAptationUtils {
    private static ScreenadAptationUtils instance;
    //这里是设计稿参考宽高
    //标准值(如果项目中设计稿尺寸不同可以改成非常量)
    public static final float STANDARD_WIDTH = 1080f;
    public static final float STANDARD_HEIGHT = 1920f;
    //获取屏幕实际宽高
    public static float displayMetricsWidth;
    public static float displayMetricsHeight;
    public int statusBarHeight;

    public static ScreenadAptationUtils getInstance(Context context) {
        if (instance == null) {
            instance = new ScreenadAptationUtils(context.getApplicationContext());
        }
        return instance;
    }

    public static ScreenadAptationUtils getInstance() {
        if (instance == null) {
            throw new RuntimeException("UIUtils应先初始化");
        }
        return instance;
    }

    public static ScreenadAptationUtils notifyInstance(Context context) {
        instance = new ScreenadAptationUtils(context.getApplicationContext());
        //修改标准尺寸
        return instance;
    }

    public ScreenadAptationUtils(Context context) {
        //获取屏幕宽高
        if (displayMetricsWidth == 0 || displayMetricsHeight == 0) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager != null) {
                //宽高获取
                DisplayMetrics displayMetrics = new DisplayMetrics();
                //如果不是NavigationBar沉浸式（不包含NavigationBar下面的导航栏）
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);   //不包含NavigationBar
//                windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);//真实屏幕宽高 包含NavigationBar
                statusBarHeight = getSystemBarHeight(context);
                //判断当前的横竖屏
                if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                    //横屏
                    this.displayMetricsWidth = displayMetrics.heightPixels;
                    this.displayMetricsHeight = displayMetrics.widthPixels - statusBarHeight;
                } else {
                    //竖屏
                    this.displayMetricsWidth = displayMetrics.widthPixels;
                    this.displayMetricsHeight = displayMetrics.heightPixels - statusBarHeight;
                }
            }
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public int getStatusBarHeight(Context context) {
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return context.getResources().getDimensionPixelSize(resId);//获取具体的像素值
        }
        return 0;
    }


    //获取水平方向的缩放比例
    public float getHorizontalScale() {
        return  displayMetricsWidth / STANDARD_WIDTH;
    }
    //获取垂直方向的缩放比例
    public float getVerticalScale() {
        return  displayMetricsHeight / STANDARD_HEIGHT;
    }
    /**
     * 取整 ，获取接近但不小于当前值的整数
     *
     * @param width
     * @return
     */
    public int getWidth(int width) {
        return Math.round(width * displayMetricsWidth / STANDARD_WIDTH);
    }
    public int getHeight(int height) {
        return Math.round(height * displayMetricsHeight / STANDARD_HEIGHT);
    }


    /**
     * 获取系统状态栏高
     *
     * @param context
     * @return
     */
    public static int getSystemBarHeight(Context context) {
        return getValue(context, "com.android.internal.R$dimen", "status_bar_height", 48);
    }

    private static int getValue(Context context, String dimenName, String status_bar_height, int defaultValue) {
        try {
            Class<?> clz = Class.forName(dimenName);
            Object instance = clz.newInstance();
            Field field = clz.getField(status_bar_height);
            int id = Integer.parseInt(field.get(instance).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
