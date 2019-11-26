package com.example.screenadaptation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 *  以一个特定宽高尺寸的设备为参考，在View的加载过程，根据当前设备的实际像素，换算出目标像素，再作用在控件上。
 * 自定义像素适配
 */
public class ScreenAdapterLayout extends RelativeLayout {
    private boolean flag;

    public ScreenAdapterLayout(Context context) {
        super(context);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!flag) {        //防止设置两次而导致计算错误
            flag = true;
            //获取横向和纵向缩放比
            float scaleX = Utils.getInstance(getContext()).getHorizontalScale();
            float scaleY = Utils.getInstance(getContext()).getVerticalScale();

            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);     //重新设置子view的布局属性，再进行Vide的测量
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                params.width = (int) (params.width * scaleX);       //换算宽度目标值
                params.height = (int) (params.height * scaleY);     //换算高度目标值
                //换算四周间距目标值
                params.leftMargin = (int) (params.leftMargin * scaleX);
                params.rightMargin = (int) (params.rightMargin * scaleX);
                params.topMargin = (int) (params.topMargin * scaleY);
                params.bottomMargin = (int) (params.bottomMargin * scaleY);
//            child.setPadding();
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
