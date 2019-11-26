package com.example.screenadaptation;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Utils {
    private static Utils utils;
    //这里是设计稿参考宽高
    private static final float STANDARD_WIDTH = 1080;
    private static final float STANDARD_HEIGHT = 1920;
    //这里是屏幕显示宽高
    private int mDisplayWidth;
    private int mDisplayHeight;

    public Utils(Context context) {
        //获取屏幕宽高
        if (mDisplayWidth == 0 || mDisplayHeight == 0) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager != null) {
                //宽高获取
                DisplayMetrics displayMetrics = new DisplayMetrics();
                //如果不是NavigationBar沉浸式（不包含NavigationBar下面的导航栏）
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);   //不包含NavigationBar
//                windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);//真实屏幕宽高 包含NavigationBar
                //判断当前的横竖屏
                if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                    //横屏
                    mDisplayWidth = displayMetrics.heightPixels;
                    mDisplayHeight = displayMetrics.widthPixels - getStatusBarHeight(context);
                } else {
                    mDisplayWidth = displayMetrics.widthPixels;
                    mDisplayHeight = displayMetrics.heightPixels - getStatusBarHeight(context);
                }
            }
        }
    }

    public static Utils getInstance(Context context) {
        if (utils == null) {
            utils = new Utils(context);
        }
        return utils;
    }

    /**
     * 获取状态栏高度
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
        return mDisplayWidth / STANDARD_WIDTH;
    }

    //获取垂直方向的缩放比例
    public float getVerticalScale() {
        return mDisplayHeight / STANDARD_HEIGHT;
    }
}
