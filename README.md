# in_app_review

A Flutter Plugin for In-App-Review. The Google Play In-App Review API lets you prompt users to submit Play Store ratings and reviews without the inconvenience of leaving your app or game.

## Install
To install the package, add the following dependency to your `pubspec.yaml`
```
dependencies:
  in_app_review: ^0.0.1
```

## Usage

```
import 'package:in_app_review/in_app_review.dart';

// Initialize the In App Review Plugin.
InAppReview.init();

//Launch the Review Flow.
InAppReview.launch();
```

### Plugin Design Reference
https://github.com/flutter/plugins/tree/master/packages/in_app_purchase