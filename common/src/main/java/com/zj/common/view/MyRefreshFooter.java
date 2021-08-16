package com.zj.common.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.zj.common.R;

/**
 * 上拉加载布局
 */
public class MyRefreshFooter extends LinearLayout implements RefreshFooter {

    private ImageView footerIv;
    private TextView footerTv;
    private AnimationDrawable drawable;

    public MyRefreshFooter(Context context) {
        this(context, null);
    }

    public MyRefreshFooter(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRefreshFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.my_refresh_footer, this);
        footerIv = view.findViewById(R.id.footer_iv);
        drawable = (AnimationDrawable) footerIv.getDrawable();
        footerTv = view.findViewById(R.id.footer_tv);
        footerTv.setText("加载中...");

    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        if (noMoreData) {
            footerTv.setText("没有更多数据了");
            footerIv.setVisibility(GONE);
        } else {
            footerIv.setVisibility(VISIBLE);
            footerTv.setText("加载中...");
        }
        return true;
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (drawable != null && drawable.isRunning()) {
            drawable.stop();
        }
        return 0;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        if (newState == RefreshState.Loading) {
            drawable.start();
        }
    }
}
