package com.example.mamaa_app

import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.provider.Settings
import android.content.Intent

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.example.mamaa/overlay"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            call, result ->
            if (call.method == "showOverlay") {
                if (Settings.canDrawOverlays(this)) {
                    // TODO: Insert WindowInsets and ValueAnimator logic here
                    result.success("Overlay launched")
                } else {
                    startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION))
                    result.error("PERMISSION_DENIED", "Overlay permission missing", null)
                }
            } else {
                result.notImplemented()
            }
        }
    }
}