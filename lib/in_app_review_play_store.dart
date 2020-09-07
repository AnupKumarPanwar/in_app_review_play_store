import 'package:flutter/services.dart';

class InAppReview {
  static const MethodChannel _channel =
      const MethodChannel('in_app_review_play_store');

  // Initialises the In App Review Plugin
  static init() async {
    _channel.invokeMethod('init');
  }

  // Launches the In App Review Flow
  static launch() async {
    _channel.invokeMethod('launch');
  }
}
