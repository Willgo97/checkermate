/*
    CheckBoard.cpp - Library for checking the position of the stones
    Created by William Goudswaard
*/
#include "Arduino.h"
#include "CheckBoard.h"

CheckBoard::CheckBoard(int rows[5], int columns[10]){
    
  // Set the pins for the checkerboard
  for (int i = 0; i < 10; i++) {
    pinMode(columns[i], OUTPUT);
    _columns[10] = columns[10];
    digitalWrite(_columns[i], LOW);
  }

  for (int i = 0; i < 5; i++) {
    pinMode(rows[i], INPUT);
    _rows[5] = rows[5];
  }
}
void CheckBoard::checkB() { // This reads the sensors under the board to determine the position of all the pieces
  int foo = 0;
  for (int i = 0; i < 10; i++) {
    digitalWrite(_columns[i], HIGH);
    delay(2);
    for (int j = 0; j < 5; j++) {
      delay(2);
      //Serial.print(digitalRead(rows[j]));
      if (i % 2 == 0 ) {
        foo = 1;
      } else {
        foo = 0;
      }
      if (digitalRead(_rows[j])) {
        array[(j * 2) + foo][i] = 0;
      } else {
        array[(j * 2) + foo][i] = 1;
      }
    }
    digitalWrite(_columns[i], LOW);
    delay(2);
    //Serial.println();
  }
}
void CheckBoard::printB() {
  for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 10; j++) {
      if (array[i][j]) {
        Serial.print("1");
        //Serial.print("[X]");
      } else {
        Serial.print("0");
        //Serial.print("[ ]");
      }
    }
    Serial.print(",");
    //Serial.println();
  }
  Serial.println();
}

