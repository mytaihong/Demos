package com.example.screenadaptation;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * Px （Pixel像素）
 * 也称为图像元素，是作为图像构成的基本单元，单个像素的大小并不固定，跟随屏幕大小和像素数量的关系变化（屏幕越大，像素越低，单个像素越大，反之亦然）。所以在使用像素作为设计单位时，在不同的设备上可能会有缩放或拉伸的情况。
 *
 * Resolution（分辨率）
 * 是指屏幕的垂直和水平方向的像素数量，如果分辨率是 1920*1080 ，那就是垂直方向有 1920 个像素，水平方向有 1080 个像素。
 *
 * Dpi（像素密度）
 * 是指屏幕上每英寸（1英寸 = 2.54 厘米）距离中有多少个像素点。如果屏幕为 320*240，屏幕长 2 英寸宽 1.5 英寸，Dpi = 320 / 2 = 240 / 1.5 = 160。
 *
 * densityDpi（密度）
 * 这个是指屏幕上每平方英寸（2.54 ^ 2 平方厘米）中含有的像素点数量。
 *
 * density（屏幕密度）
 * 这个是针对【每平方英寸含有160的像素点】为基础的一个缩放比例。比如值为2，表示每平方英寸含有160*2=320的像素点。
 *
 * scaleDensity（字体缩放比例）
 * 默认与Density相同。
 *
 * Dip / dp (设备独立像素)
 * 也可以叫做dp，长度单位，同一个单位在不同的设备上有不同的显示效果，具体效果根据设备的密度有关，详细的公式请看下面 。
 * density 表示屏幕密度，针对于某个尺寸的分辨率
 *
 * 可以将setDensity方法运用于自定义的BaseActivity即可
 * 也可以在BaseApplication的onCreate中，在registerActivityLifecycleCallbacks的onActivityCreated回调中调用Density.setDensity。
 *
 *  修改density，scaleDensity，densityDpi值，直接更改系统内部对于目标尺寸而言的像素密度。
 */
public class DensityUtils {
    private static final float WIDTH = 360;//参考设备的宽，单位是dp 如果设置一个控件为屏幕宽度一半 则为180dp   参考UI图的DPI值
    private static float appDensity;//表示屏幕密度
    private static float appScaleDensity;//字体缩放比例，默认为appDensity

    public static void setDensity(final Application application, Activity activity) {
        //获取当前屏幕信息
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0) {
            //初始化赋值
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;
            //监听字体变化
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体发生更改，重新计算scaleDensity
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        //计算目标density scaledDensity
        float targetDensity = displayMetrics.widthPixels / WIDTH;//1080/360=3;
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);
        int targetDensityDpi = (int) (targetDensity * 160);
        //替换Activity的值
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        dm.density = targetDensity;
        dm.scaledDensity = targetScaleDensity;
        dm.densityDpi = targetDensityDpi;
    }
}
