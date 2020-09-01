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
 * AnupKumarPanwar
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
        setupMethodChannel(null, binding.getBinaryMessenger(), binding.getApplicationContext());
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        teardownMethodChannel();
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        methodCallHandler.setActivity(binding.getActivity());

    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        methodCallHandler.setActivity(null);
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        onAttachedToActivity(binding);

    }

    @Override
    public void onDetachedFromActivity() {
        methodCallHandler.setActivity(null);

    }

    private void setupMethodChannel(Activity activity, BinaryMessenger messenger, Context context) {
        methodChannel = new MethodChannel(messenger, "in_app_review");
        methodCallHandler =
                new MethodCallHandlerImpl(activity, context);
        methodChannel.setMethodCallHandler(methodCallHandler);
    }

    private void teardownMethodChannel() {
        methodChannel.setMethodCallHandler(null);
        methodChannel = null;
        methodCallHandler = null;
    }

    public class MethodNames {
        static final String INIT =
                "init";
        static final String LAUNCH =
                "launch";
        
    }
}
