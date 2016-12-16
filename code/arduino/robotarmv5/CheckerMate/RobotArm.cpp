/*
    RobotArm.cpp - Library for checking the position of the stones
    Created by William Goudswaard
*/

#include "Arduino.h"
#include "RobotArm.h"

const int stepPinY = 3; // Y axis
const int dirPinY = 4;
const int stepPinX = 5; // X axis
const int dirPinX = 6;

const int limitsY = 50;
const int limitsX = 52;

int curX = 0;
int curY = 0;
int delayms = 1000;

RobotArm::RobotArm(int init){
  pinMode(stepPinY, OUTPUT);
  pinMode(dirPinY, OUTPUT);
  pinMode(stepPinX, OUTPUT);
  pinMode(dirPinX, OUTPUT);

  pinMode(limitsX, INPUT);
  pinMode(limitsY, INPUT);
}

void RobotArm::moveRobot(int x, int y) { //  beweeg de robot naar deze positie in mm
  int diffX = x - curX;
  int diffY = y - curY;

  (diffX < 0) ? digitalWrite(dirPinX, HIGH) : digitalWrite(dirPinX, LOW);
  (diffY < 0) ? digitalWrite(dirPinY, HIGH) : digitalWrite(dirPinY, LOW);

  int stepsX = abs(diffX) * 4;
  int stepsY = abs(diffY) * 8;

  int steps = 0;
  boolean endX = false;
  boolean endY = false;

  while (endX == false || endY == false) {
    steps++;

    if (endX == false){
      digitalWrite(stepPinX, HIGH);
    }
    if (endY == false){
      digitalWrite(stepPinY, HIGH);
    }

    delayMicroseconds(delayms);

    if (endX == false){
      digitalWrite(stepPinX, LOW);
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
    delayMicroseconds(delayms);
  }
  // tijdelijk op 0
  curX = 0; // curX = x
  curY = 0; // curY = y
}

void RobotArm::resetRobot() { // beweeg de robot terug naar de begin positie
  boolean limitX = digitalRead(limitsX);
  boolean limitY = digitalRead(limitsY);

  digitalWrite(dirPinY, LOW);
  digitalWrite(dirPinX, LOW);

  while (limitX == false) {
    limitX = digitalRead(limitsX);
    digitalWrite(stepPinX, HIGH);
    delayMicroseconds(delayms * 0.8);
    digitalWrite(stepPinX, LOW);
    delayMicroseconds(delayms * 0.8);
  }
  delay(300);
  
  while (limitY == false) {
    limitY = digitalRead(limitsY);
    digitalWrite(stepPinY, HIGH);
    delayMicroseconds(delayms * 0.8);
    digitalWrite(stepPinY, LOW);
    delayMicroseconds(delayms * 0.8);
  }
  delay(300);
  moveRobot(-4, -4);

  curX = 0;
  curY = 0;
}
