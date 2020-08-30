import 'dart:async';

import 'package:flutter/services.dart';

class InAppReview {
  static const MethodChannel _channel = const MethodChannel('in_app_review');

  static init() async {
    final String version = await _channel.invokeMethod('init');
  }
}
