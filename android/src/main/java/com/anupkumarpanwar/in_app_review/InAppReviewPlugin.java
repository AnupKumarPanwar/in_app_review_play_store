package com.anupkumarpanwar.in_app_review;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/**
 * InAppReviewPlugin
 */
public class InAppReviewPlugin implements FlutterPlugin, ActivityAware {

    private MethodChannel methodChannel;
    private MethodCallHandlerImpl methodCallHandler;

    public static void registerWith(PluginRegistry.Registrar registrar) {
        InAppReviewPlugin plugin = new InAppReviewPlugin();
        plugin.setupMethodChannel(registrar.activity(), registrar.messenger(), registrar.context());
        ((Application) registrar.context().getApplicationContext())
                .registerActivityLifecycleCallbacks(plugin.methodCallHandler);
    }

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {

    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {

    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {

    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {

    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {

    }

    @Override
    public void onDetachedFromActivity() {

    }

    private void setupMethodChannel(Activity activity, BinaryMessenger messenger, Context context) {
        methodChannel = new MethodChannel(messenger, "plugins.flutter.io/in_app_purchase");
        methodCallHandler =
                new MethodCallHandlerImpl(activity, context, methodChannel);
        methodChannel.setMethodCallHandler(methodCallHandler);
    }

    public class MethodNames {
        static final String INIT =
                "ReviewClient#init(ReviewClientStateListener)";
    }
}
