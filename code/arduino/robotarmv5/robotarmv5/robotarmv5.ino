#include <CheckBoard.h>
#include <MagnetControl.h>
#include <RobotArm.h>

CheckBoard checkBoard(0);// I have to give an int as parameter, otherwise it won't work
MagnetControl magnetControl(0);
RobotArm robotArm(0);

int gotoX = 0;
int gotoY = 0;
int geslagenSteen = 0;
int kingNumberZ = 0;
int kingNumberW = 0;

bool resetje = true;

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
void(* resetFunc) (void) = 0;

void loop() {
  if (Serial.available()) {
    resetje = false;
    char byteIn = Serial.read();
    //Serial.println("inc: " + (char)byteIn);
    switch (byteIn) {
      case 'c':
        gotoX = Serial.parseInt();
        gotoY = Serial.parseInt();
        Serial.println(gotoX);
        if (gotoX == 0 && gotoY == 0) {
          geslagenSteen++;
          
          if (geslagenSteen == 4) {
            magnetControl.goUpReset(geslagenSteen);
            robotArm.moveRobot(0, 0);
            magnetControl.disMagnet();            
            robotArm.moveRobot(0, 50);
            magnetControl.goDown();
            geslagenSteen = 0;
            magnetControl.goUpReset(geslagenSteen);
            robotArm.moveRobot(0, 0);
          }
          else {
            magnetControl.goUpReset(geslagenSteen);
            robotArm.moveRobot(gotoX, gotoY);
          }
          //(geslagenSteen < 8) ? magnetControl.goUpReset(geslagenSteen) : magnetControl.goUpReset(8);
          //robotArm.moveRobot(gotoX, gotoY);
          delay(300);
        }
        else {
          robotArm.moveRobot(gotoX, gotoY);
          delay(300);
        }
        //robotArm.moveRobot(gotoX, gotoY);
        //delay(300);
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
        resetje = true;
        //Serial.println("move to origin");
        robotArm.resetRobot();
        //Serial.println("at origin");
        break;
      case 'w':
        robotArm.moveRobot(0,80+kingNumberW*40);
        kingNumberW ++;
        break;
      case 'z':
        robotArm.moveRobot(0,240+kingNumberZ*40);
        kingNumberZ ++;
        break;
        
      case 'b' :
        //Serial.println("printing board layout");
        checkBoard.checkB();
        checkBoard.printB();
        break;
      case 'y' :
        resetFunc();
        break;
    }
  } else {
    if (checkBoard.changedBoard()) {
      checkBoard.checkB();
      checkBoard.printB();
      Serial.flush();
      delay(2500);
    }
    if (resetje == false) {
      robotArm.resetRobot();
      resetje = true;
    }
  }
  delay(300);
}
