package com.example.screenadaptation;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewCalculateUtil {
    /**
     * 字体适配
     *
     * @param view
     * @param size
     */
    public static void setTextSize(TextView view, int size) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, ScreenadAptationUtils.getInstance().getHeight(size));
    }

    public static void setViewRelativeLayoutParam(View view, int width, int height, int leftMargin, int topMargin,
                                                  int rightMargin, int bottomMargin, boolean asWidth) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (layoutParams != null) {
            if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT
                    && width != RelativeLayout.LayoutParams.FILL_PARENT) {
                layoutParams.width = ScreenadAptationUtils.getInstance().getWidth(width);
            } else {
                layoutParams.width = width;
            }

            if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT
                    && height != RelativeLayout.LayoutParams.FILL_PARENT) {
                layoutParams.height = asWidth ? ScreenadAptationUtils.getInstance().getWidth(height)
                        : ScreenadAptationUtils.getInstance().getHeight(height);
            } else {
                layoutParams.height = height;
            }

            layoutParams.topMargin = asWidth ? ScreenadAptationUtils.getInstance().getWidth(topMargin) :
                    ScreenadAptationUtils.getInstance().getHeight(topMargin);
            layoutParams.bottomMargin = asWidth ? ScreenadAptationUtils.getInstance().getWidth(bottomMargin) :
                    ScreenadAptationUtils.getInstance().getHeight(bottomMargin);
            layoutParams.leftMargin = ScreenadAptationUtils.getInstance().getWidth(leftMargin);
            layoutParams.rightMargin = ScreenadAptationUtils.getInstance().getWidth(rightMargin);
            view.setLayoutParams(layoutParams);
        }

    }


    /**
     * @param view
     * @param width
     * @param height
     * @param topMargin
     * @param bottomMargin
     * @param lefMargin
     * @param rightMargin
     */
    public static void setViewFrameLayoutParam(View view, int width, int height, int lefMargin, int topMargin, int bottomMargin,
                                               int rightMargin, boolean asWidth) {

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT && width != RelativeLayout.LayoutParams.FILL_PARENT) {
            layoutParams.width = ScreenadAptationUtils.getInstance().getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT && height != RelativeLayout.LayoutParams.FILL_PARENT) {
            layoutParams.height = asWidth ? ScreenadAptationUtils.getInstance().getWidth(height) : ScreenadAptationUtils.getInstance().getHeight(height);
        } else {
            layoutParams.height = height;
        }

        layoutParams.topMargin = asWidth ? ScreenadAptationUtils.getInstance().getWidth(topMargin) : ScreenadAptationUtils.getInstance().getHeight(topMargin);
        layoutParams.bottomMargin = asWidth ? ScreenadAptationUtils.getInstance().getWidth(bottomMargin) : ScreenadAptationUtils.getInstance().getHeight(bottomMargin);
        layoutParams.leftMargin = ScreenadAptationUtils.getInstance().getWidth(lefMargin);
        layoutParams.rightMargin = ScreenadAptationUtils.getInstance().getWidth(rightMargin);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 设置view的内边距
     *
     * @param view
     * @param topPadding
     * @param bottomPadding
     * @param leftPadding
     * @param rightPadding
     */
    public static void setViewPadding(View view, int topPadding, int bottomPadding, int leftPadding, int rightPadding) {
        view.setPadding(ScreenadAptationUtils.getInstance().getWidth(leftPadding),
                ScreenadAptationUtils.getInstance().getHeight(topPadding),
                ScreenadAptationUtils.getInstance().getWidth(rightPadding),
                ScreenadAptationUtils.getInstance().getHeight(bottomPadding));
    }


    /**
     * 设置LinearLayout中 view的高度宽度
     *
     * @param view
     * @param width
     * @param height
     */
    public static void setViewLinearLayoutParam(View view, int width, int height, boolean asWidth) {

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT && width != RelativeLayout.LayoutParams.FILL_PARENT) {
            layoutParams.width = ScreenadAptationUtils.getInstance().getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT && height != RelativeLayout.LayoutParams.FILL_PARENT) {
            layoutParams.height = asWidth ? ScreenadAptationUtils.getInstance().getWidth(height) : ScreenadAptationUtils.getInstance().getHeight(height);
        } else {
            layoutParams.height = height;
        }

        view.setLayoutParams(layoutParams);
    }

    public static void setViewGroupLayoutParam(View view, int width, int height, boolean asWidth) {

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT && width != RelativeLayout.LayoutParams.FILL_PARENT) {
            layoutParams.width = ScreenadAptationUtils.getInstance().getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT && height != RelativeLayout.LayoutParams.FILL_PARENT) {
            layoutParams.height = asWidth ? ScreenadAptationUtils.getInstance().getWidth(height) : ScreenadAptationUtils.getInstance().getHeight(height);
        } else {
            layoutParams.height = height;
        }
        view.setLayoutParams(layoutParams);
    }

    /**
     * 设置LinearLayout中 view的高度宽度
     *
     * @param view
     * @param width
     * @param height
     */
    public static void setViewLinearLayoutParam(View view, int width, int lefMargin, int height, int topMargin, int bottomMargin,
                                                int rightMargin, boolean asWidth) {

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT && width != RelativeLayout.LayoutParams.FILL_PARENT) {
            layoutParams.width = ScreenadAptationUtils.getInstance().getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT && height != RelativeLayout.LayoutParams.FILL_PARENT) {
            layoutParams.height = asWidth ? ScreenadAptationUtils.getInstance().getWidth(height) : ScreenadAptationUtils.getInstance().getHeight(height);
        } else {
            layoutParams.height = height;
        }

        layoutParams.topMargin = asWidth ? ScreenadAptationUtils.getInstance().getWidth(topMargin) : ScreenadAptationUtils.getInstance().getHeight(topMargin);
        layoutParams.bottomMargin = asWidth ? ScreenadAptationUtils.getInstance().getWidth(bottomMargin) : ScreenadAptationUtils.getInstance().getHeight(bottomMargin);
        layoutParams.leftMargin = ScreenadAptationUtils.getInstance().getWidth(lefMargin);
        layoutParams.rightMargin = ScreenadAptationUtils.getInstance().getWidth(rightMargin);
        view.setLayoutParams(layoutParams);
    }
}
