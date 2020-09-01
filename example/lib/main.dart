import 'package:flutter/material.dart';
import 'package:in_app_review_play_store/in_app_review_play_store.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    initInAppReview();
  }

  initInAppReview() async {
    await Future.delayed(Duration(seconds: 1));
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
