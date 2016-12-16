#include <CheckBoard.h>
#include <MagnetControl.h>

const int stepPinY = 3; // Y axis
const int dirPinY = 4;
const int stepPinX = 5; // X axis
const int dirPinX = 6;

CheckBoard checkBoard(0);// I have to give a int as parameter, otherwise it doesn't work
MagnetControl magnetControl(0);

const int limitsY = 50;
const int limitsX = 52;

unsigned long last_time;
unsigned long currentMillis ;
//_____________________________________________________setup_____________________________________________//
void setup() {
  pinMode(stepPinY, OUTPUT);
  pinMode(dirPinY, OUTPUT);
  pinMode(stepPinX, OUTPUT);
  pinMode(dirPinX, OUTPUT);

  pinMode(limitsX, INPUT);
  pinMode(limitsY, INPUT);

  Serial.begin(9600);
}

int beweegArm = 0;
int curX = 0;
int curY = 0;
int gotoX;
int gotoY;
//_____________________________________________________loop_____________________________________________//
void loop() {
  if (Serial.available()) {
    char byteIn = Serial.read();

    switch (byteIn) {
      case 'c':
        gotoX = Serial.parseInt();
        gotoY = Serial.parseInt();
        printf("X: ", gotoX, " Y: ", gotoY);
        moveRobot(gotoX, gotoY);
        delay(1000);
        break;
      case '1' :
        magnetControl.enMagnet();
        break;
      case '2' :
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
        resetRobot();
        break;
      case 'b' :
        Serial.println("printing board layout");
        checkBoard.checkB();
        checkBoard.printB();
        break;
    }
  }
}

void moveRobot(int x, int y) { //  beweeg de robot naar deze positie in mm

  int diffX = x - curX;
  int diffY = y - curY;

  if (diffX < 0) {
    digitalWrite(dirPinY, HIGH);
  } else {
    digitalWrite(dirPinY, LOW);
  }

  if (diffY < 0) {
    digitalWrite(dirPinX, HIGH);
  } else {
    digitalWrite(dirPinX, LOW);
  }

  int stepsX = abs(diffX) * 4;
  int stepsY = abs(diffY) * 8;

  int steps = 0;
  boolean endX = false;
  boolean endY = false;

  while (endX == false || endY == false) {
    steps++;

    if (endX == false) {
      digitalWrite(stepPinY, HIGH);
    }
    if (endY == false) {
      digitalWrite(stepPinX, HIGH);
    }

    delayMicroseconds(1000);

    if (endX == false) {
      digitalWrite(stepPinY, LOW);
      if (steps > stepsX) {
        endX = true;
      }
    }

    if (endY == false) {
      digitalWrite(stepPinX, LOW);
      if (steps > stepsY) {
        endY = true;
      }
    }

    delayMicroseconds(1000);
  }
  // tijdelijk op 0
  curX = 0; // curX = x
  curY = 0; // curY = y
}

void resetRobot() { // beweeg de robot terug naar de begin positie
  boolean endX = digitalRead(limitsX);
  boolean endY = digitalRead(limitsY);

  digitalWrite(dirPinY, LOW);
  digitalWrite(dirPinX, LOW);

  while (endX == false || endY == false) {

    if (endX == false) {
      endX = digitalRead(limitsX);
    }
    if (endY == false) {
      endY = digitalRead(limitsY);
    }

    if (endX == false) {
      digitalWrite(stepPinY, HIGH);
    }
    if (endY == false) {
      digitalWrite(stepPinX, HIGH);
    }

    delayMicroseconds(1000);

    if (endX == false) {
      digitalWrite(stepPinY, LOW);
    }
    if (endY == false) {
      digitalWrite(stepPinX, LOW);
    }

    delayMicroseconds(1000);
  }
  delay(300);
  moveRobot(-4, -4);

  curX = 0;
  curY = 0;
}
