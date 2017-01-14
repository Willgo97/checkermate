/*
    MagnetControl.cpp - Library for checking the position of the stones
    Created by William Goudswaard
*/

#include "Arduino.h"
#include "MagnetControl.h"

const int magnetMotorPin1 = 46;// z axis
const int magnetMotorPin2 = 44;
const int magnetMotorPin3 = 42;
const int magnetMotorPin4 = 40;

const int limitsZ = 48;// limitswitch
const int magnet = 12;//magnet connected to MOSFET

int stepsLeft = 0;
int Steps = 0;
boolean Direction = true;// gre

MagnetControl::MagnetControl(int init){
    pinMode(magnetMotorPin1, OUTPUT);
    pinMode(magnetMotorPin2, OUTPUT);
    pinMode(magnetMotorPin3, OUTPUT);
    pinMode(magnetMotorPin4, OUTPUT);
    
    pinMode(magnet, OUTPUT);
    pinMode(limitsZ, INPUT);
}

void SetDirection() {
  if (Direction == 1) {
    Steps++;
  }
  if (Direction == 0) {
    Steps--;
  }
  if (Steps > 7) {
    Steps = 0;
  }
  if (Steps < 0) {
    Steps = 7;
  }
}

void stepper(int xw) {
  for (int x = 0; x < xw; x++) {
    switch (Steps) {
      case 0:
        digitalWrite(magnetMotorPin1, LOW);
        digitalWrite(magnetMotorPin2, LOW);
        digitalWrite(magnetMotorPin3, LOW);
        digitalWrite(magnetMotorPin4, HIGH);
        break;
      case 1:
        digitalWrite(magnetMotorPin1, LOW);
        digitalWrite(magnetMotorPin2, LOW);
        digitalWrite(magnetMotorPin3, HIGH);
        digitalWrite(magnetMotorPin4, HIGH);
        break;
      case 2:
        digitalWrite(magnetMotorPin1, LOW);
        digitalWrite(magnetMotorPin2, LOW);
        digitalWrite(magnetMotorPin3, HIGH);
        digitalWrite(magnetMotorPin4, LOW);
        break;
      case 3:
        digitalWrite(magnetMotorPin1, LOW);
        digitalWrite(magnetMotorPin2, HIGH);
        digitalWrite(magnetMotorPin3, HIGH);
        digitalWrite(magnetMotorPin4, LOW);
        break;
      case 4:
        digitalWrite(magnetMotorPin1, LOW);
        digitalWrite(magnetMotorPin2, HIGH);
        digitalWrite(magnetMotorPin3, LOW);
        digitalWrite(magnetMotorPin4, LOW);
        break;
      case 5:
        digitalWrite(magnetMotorPin1, HIGH);
        digitalWrite(magnetMotorPin2, HIGH);
        digitalWrite(magnetMotorPin3, LOW);
        digitalWrite(magnetMotorPin4, LOW);
        break;
      case 6:
        digitalWrite(magnetMotorPin1, HIGH);
        digitalWrite(magnetMotorPin2, LOW);
        digitalWrite(magnetMotorPin3, LOW);
        digitalWrite(magnetMotorPin4, LOW);
        break;
      case 7:
        digitalWrite(magnetMotorPin1, HIGH);
        digitalWrite(magnetMotorPin2, LOW);
        digitalWrite(magnetMotorPin3, LOW);
        digitalWrite(magnetMotorPin4, HIGH);
        break;
      default:
        digitalWrite(magnetMotorPin1, LOW);
        digitalWrite(magnetMotorPin2, LOW);
        digitalWrite(magnetMotorPin3, LOW);
        digitalWrite(magnetMotorPin4, LOW);
        break;
    }
    SetDirection();
  }
}

void MagnetControl::enMagnet(){
    digitalWrite(magnet, HIGH);
}

void MagnetControl::disMagnet(){
    digitalWrite(magnet, LOW);
}

void MagnetControl::goUp(){
    stepsLeft = 2048;
    Direction = false;
    while (stepsLeft > 0 && Direction == false) {
        stepper(1);
        stepsLeft--;
        delayMicroseconds(800);
      }
   // delay(2000);
}

void MagnetControl::goUpReset(int hitStonesCount){
    for (int i = 0; i < hitStonesCount; i++){
        goUp();
    }
}

void MagnetControl::goDown(){
    Direction = true;
    while (digitalRead(48) == LOW) {
      stepper(1);
      delayMicroseconds(700);
    }
    digitalWrite(magnet, LOW);
    //delay(2000);
}

