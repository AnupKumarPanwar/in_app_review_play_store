import 'package:flutter/services.dart';

class InAppReview {
  static const MethodChannel _channel = const MethodChannel('in_app_review');

  static init() async {
    _channel.invokeMethod('init');
  }

  static launch() async {
    _channel.invokeMethod('launch');
  }
}
