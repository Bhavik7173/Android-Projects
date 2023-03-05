import 'dart:async';
import 'dart:html';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'MyLogin.dart';

void main() {
  runApp(SplashScreen());
}

class SplashScreen extends StatefulWidget {
  const SplashScreen({Key key}) : super(key: key);

  @override
  State<SplashScreen> createState() => StartState();
}

class StartState extends State<SplashScreen> {
  void iniitate() {
    super.initState();
    Future.delayed(Duration(seconds: 3),()async{
      Navigator.pushAndRemoveUntil(context, MaterialPageRoute(builder: (context)=>MyLogin()), (route) => false);
    });
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          Container(
            decoration: BoxDecoration(
                color: new Color(0xffF5591F),
                gradient: LinearGradient(
                    colors: [(new Color(0x483d8b)), new Color(0xffF2861E)],
                    begin: Alignment.topCenter,
                    end: Alignment.bottomCenter)),
          ),
          Center(
            child: Container(
              child: Image.asset('assets/login.png'),
            ),
          )
        ],
      ),
    );
  }

}
