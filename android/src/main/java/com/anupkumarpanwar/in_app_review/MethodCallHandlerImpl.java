package com.anupkumarpanwar.in_app_review;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class MethodCallHandlerImpl implements MethodChannel.MethodCallHandler, Application.ActivityLifecycleCallbacks {
    private MethodChannel methodChannel;
    private Activity activity;
    private ReviewManager reviewManager;
    private ReviewManagerFactory reviewManagerFactory;
    private Context applicationContext;

    public MethodCallHandlerImpl(Activity activity, Context applicationContext, MethodChannel methodChannel) {
        this.applicationContext = applicationContext;
        this.activity = activity;
        this.methodChannel = methodChannel;
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        switch (call.method) {
            case InAppReviewPlugin.MethodNames.INIT:
                startConnection(
                        (int) call.argument("handle"),
                        (boolean) call.argument("enablePendingPurchases"),
                        result);
                break;
            default:
                result.notImplemented();
        }
    }

    private void startConnection(int handle, boolean enablePendingPurchases, MethodChannel.Result result) {
        if (reviewManager == null) {
            reviewManager = reviewManagerFactory.create(applicationContext);
        }
        Task<ReviewInfo> request = reviewManager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();
                result.success(reviewInfo.toString());
            } else {
                result.error("error", "Requesting review flow failed", null);
                // There was some problem, continue regardless of the result.
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

    }
}
