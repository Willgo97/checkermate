#include <CheckBoard.h>
#include <MagnetControl.h>
#include <RobotArm.h>

CheckBoard checkBoard(0);// I have to give an int as parameter, otherwise it won't work
MagnetControl magnetControl(0);
RobotArm robotArm(0);

int gotoX;
int gotoY;
//_____________________________________________________setup_____________________________________________//
void setup() {
  Serial.begin(9600);
}

void loop() {
  if (Serial.available()) {
    char byteIn = Serial.read();

    switch (byteIn) {
      case 'c':
        gotoX = Serial.parseInt();
        gotoY = Serial.parseInt();
        printf("X: ", gotoX, " Y: ", gotoY);
        robotArm.moveRobot(gotoX, gotoY);
        delay(1000);
        break;
      case '1' :
        Serial.println("enable magnet");
        magnetControl.enMagnet();
        break;
      case '2' :
        Serial.println("disable magnet");
        magnetControl.disMagnet();
        break;
      case 'p' :
        Serial.println("pick stone up");
        magnetControl.goUp();
        break;
      case 'd' :
        Serial.println("drop the stone");
        magnetControl.goDown();
        break;
      case 'r' :
        Serial.println("move to origin");
        robotArm.resetRobot();
        break;
      case 'b' :
        Serial.println("printing board layout");
        checkBoard.checkB();
        checkBoard.printB();
        break;
    }
  }
}
