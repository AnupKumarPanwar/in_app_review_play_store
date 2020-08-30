import 'package:flutter/material.dart';
import 'package:in_app_review/in_app_review.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
    initInAppReview();
  }

  initInAppReview() {
    InAppReview.init();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: RaisedButton(
            child: Text("Launch In App Review"),
            onPressed: () {
              InAppReview.launch();
            },
          ),
        ),
      ),
    );
  }
}
