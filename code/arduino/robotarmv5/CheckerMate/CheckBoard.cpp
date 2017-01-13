/*
    CheckBoard.cpp - Library for checking the position of the stones
    Created by William Goudswaard
*/
#include "Arduino.h"
#include "CheckBoard.h"

int array[10][10];
const int rows[5] = {23, 25, 27, 29, 31};// checkerboard
const int columns[10] = {33, 35, 37, 39, 41, 43, 45, 47, 49, 51};


CheckBoard::CheckBoard(int init){
    
  // Set the pins for the checkerboard
  for (int i = 0; i < 10; i++) {
    pinMode(columns[i], OUTPUT);
    digitalWrite(columns[i], LOW);
  }

  for (int i = 0; i < 5; i++) {
    pinMode(rows[i], INPUT);
  }
}
void CheckBoard::checkB() { // This reads the sensors under the board to determine the position of all the pieces
  int foo = 0;
  for (int i = 0; i < 10; i++) {
    digitalWrite(columns[i], HIGH);
    delay(2);
    for (int j = 0; j < 5; j++) {
      delay(2);
      //Serial.print(digitalRead(rows[j]));
      if (i % 2 == 0 ) {
        foo = 1;
      } else {
        foo = 0;
      }
      if (digitalRead(rows[j])) {
        array[(j * 2) + foo][i] = 0;
      } else {
        array[(j * 2) + foo][i] = 1;
      }
    }
    digitalWrite(columns[i], LOW);
    delay(2);
    //Serial.println();
  }
}
bool CheckBoard::changedBoard(){
  int oldArray[10][10];

  for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 10; j++) {
      oldArray[i][j] = array[i][j];   
    }
  }

  checkB();

  for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 10; j++) {
      if(array[i][j] != oldArray[i][j]){
        return true;
      }
    }
  }
  return false;
}
void CheckBoard::printB() {
  Serial.print("f");
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

