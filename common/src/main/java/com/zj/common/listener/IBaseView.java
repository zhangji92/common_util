package com.zj.common.listener;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface IBaseView<VM> {

    @LayoutRes
    int layoutId();

    @Nullable
    VM createViewModel();

    void initData(@Nullable Bundle savedInstanceState);

    int variableId();

    boolean isTranslucent();

}
