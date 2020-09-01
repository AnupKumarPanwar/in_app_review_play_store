package com.anupkumarpanwar.in_app_review;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class MethodCallHandlerImpl implements MethodChannel.MethodCallHandler, Application.ActivityLifecycleCallbacks {
    private Activity activity;
    private ReviewManager reviewManager;
    private Context applicationContext;
    private ReviewInfo reviewInfo;

    public MethodCallHandlerImpl(Activity activity, Context applicationContext) {
        this.applicationContext = applicationContext;
        this.activity = activity;
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        switch (call.method) {
            case InAppReviewPlugin.MethodNames.INIT:
                init(result);
                break;
            case InAppReviewPlugin.MethodNames.LAUNCH:
                launch(result);
                break;
            default:
                result.notImplemented();
        }
    }

    private void launch(MethodChannel.Result result) {
        if (reviewManager == null) {
            result.error("NOT_FOUND", "In App Review manager has not been initialized", null);
        }
        Task<Void> flow = reviewManager.launchReviewFlow(activity, reviewInfo);
        flow.addOnCompleteListener(task -> {
            result.success(task);
        });
    }

    private void init(MethodChannel.Result result) {
        if (reviewManager == null) {
            reviewManager = ReviewManagerFactory.create(applicationContext);
        }
        Task<ReviewInfo> request = reviewManager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                reviewInfo = task.getResult();
                result.success(reviewInfo);
            } else {
                result.error("REQUEST_ERROR", "Requesting review flow failed", null);
            }
        });
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (this.activity == activity && this.applicationContext != null) {
            ((Application) this.applicationContext).unregisterActivityLifecycleCallbacks(this);
        }
    }

    void setActivity(@Nullable Activity activity) {
        this.activity = activity;
    }
}
