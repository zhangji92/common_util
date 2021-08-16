package com.zj.common.livedata;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.zj.common.utils.DebugUtils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Time: 5/7/2021 16:04
 * Author: TAO
 * Description:
 */
public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private final AtomicBoolean pending = new AtomicBoolean(false);

    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        if (hasActiveObservers()) {
            DebugUtils.INSTANCE.printfLog("Multiple observers registered but only one will be notified of changes.");
        }
        super.observe(owner, t -> {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t);
            }
        });
    }

    @MainThread
    public void setValue(T value) {
        pending.set(true);
        super.setValue(value);
    }

    @MainThread
    public void call() {
        setValue(null);
    }
}
