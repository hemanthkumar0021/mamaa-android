import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(const MamaaApp());

class MamaaApp extends StatelessWidget {
  const MamaaApp({super.key});
  // Updated channel name
  static const platform = MethodChannel('com.example.mamaa/overlay');

  Future<void> _triggerOverlay() async {
    try {
      await platform.invokeMethod('showOverlay');
    } on PlatformException catch (e) {
      debugPrint("Failed to show overlay: ${e.message}");
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: const Text('Mamaa App Settings')),
        body: Center(
          child: ElevatedButton(
            onPressed: _triggerOverlay,
            child: const Text('Test Notch Overlay'),
          ),
        ),
      ),
    );
  }
}