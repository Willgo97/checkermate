#include <CheckBoard.h>
#include <MagnetControl.h>
#include <RobotArm.h>

CheckBoard checkBoard(0);// I have to give an int as parameter, otherwise it won't work
MagnetControl magnetControl(0);
RobotArm robotArm(0);

int gotoX = 0;
int gotoY = 0;

void setup() {
  Serial.begin(57600);
  Serial.flush();
  robotArm.moveRobot(0, 0);
  robotArm.resetRobot();
  delay(3000);
  Serial.println("ready");
  checkBoard.checkB();
  checkBoard.printB();
  Serial.flush();

}

void loop() {
  if (Serial.available()) {
    char byteIn = Serial.read();
    //Serial.println("inc: " + (char)byteIn);
    switch (byteIn) {
      case 'c':
        gotoX = Serial.parseInt();
        gotoY = Serial.parseInt();
        //String s = "X: ", String(gotoX), " Y; ", String(gotoY);
        //Serial.println(s);
        robotArm.moveRobot(gotoX, gotoY);
        delay(300);
        break;
      case 'a' :
        //Serial.println("enable magnet");
        magnetControl.enMagnet();
        break;
      case 'u' :
        //Serial.println("disable magnet");
        magnetControl.disMagnet();
        break;
      case 'p' :
        //Serial.println("pick stone up");
        magnetControl.goUp();
        break;
      case 'd' :
        //Serial.println("drop the stone");
        magnetControl.goDown();
        break;
      case 'r' :
        //Serial.println("move to origin");
        robotArm.resetRobot();
        //Serial.println("at origin");
        break;
      case 'b' :
        //Serial.println("printing board layout");
        checkBoard.checkB();
        checkBoard.printB();
        break;
    }
  } else {
    if(checkBoard.changedBoard()){
      checkBoard.checkB();
      checkBoard.printB();
      Serial.flush();
      delay(2500);
    }
  }
  delay(300);
}
