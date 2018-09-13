package com.example.ahmed.marvelwall;


import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.Nullable;
import androidx.test.espresso.IdlingResource;

public class SimpleIdlingResource implements IdlingResource {

    @Nullable
    ResourceCallback resourceCallback;

    private AtomicBoolean isIdleNow = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(IdlingResource.ResourceCallback callback) {
        resourceCallback = callback;
    }

    public void setIdleState(boolean mIsIdleNow) {
        isIdleNow.set(mIsIdleNow);
        if (mIsIdleNow && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
    }
}
