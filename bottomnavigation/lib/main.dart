import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  static const String _title = 'Flutter';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: _title,
      home: MyStatefulWidget(),
    );
  }
}

class MyStatefulWidget extends StatefulWidget {
  MyStatefulWidget({Key? key}) : super(key: key);

  @override
  _MyStatefulWidgetState createState() => _MyStatefulWidgetState();
}


class _MyStatefulWidgetState extends State<MyStatefulWidget> {
  int _selectedIndex = 0;

  static List<Widget> _widgetOptions = <Widget>[

    sampleWidget('Home'),
    sampleWidget('Settings'),
    sampleWidget('Help')

  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Bottom Navigation')
        //backgroundColor: Color.alphaBlend(Colors.blueAccent,Colors.grey),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Center(
            child: _widgetOptions.elementAt(_selectedIndex),
          ),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home),label: "Home",
            //title: Text('Home'),
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.settings),label: "Setting"
            //title: Text('Settings'),
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.help),label: "Help"
            //title: Text('Help'),
          ),
        ],
        currentIndex: _selectedIndex,
        selectedItemColor: Colors.black54,
        onTap: _onItemTapped,
      ),
    );
  }
}


Widget sampleWidget(widgetDesc){

  return Column(
    mainAxisAlignment: MainAxisAlignment.center,
    children: <Widget>[

      Text(widgetDesc),

    ],
  );

}