#include <CheckBoard.h>

int rows[5] = {23, 25, 27, 29, 31};// checkerboard
int columns[10] = {33, 35, 37, 39, 41, 43, 45, 47, 49, 51};

CheckBoard checkBoard(rows, columns);

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  checkBoard.checkB();
  checkBoard.printB();
  delay(5000);
}
