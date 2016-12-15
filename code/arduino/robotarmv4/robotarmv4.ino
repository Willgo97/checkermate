const int stepPinY = 3; // Y axis
const int dirPinY = 4;
const int stepPinX = 5; // X axis
const int dirPinX = 6;

const int magnet = 12;//magnet connected to MOSFET

const int rows[5] = {23, 25, 27, 29, 31};// checkerboard
const int columns[10] = {33, 35, 37, 39, 41, 43, 45, 47, 49, 51};
int array[10][10];

const int magnetMotorPin1 = 46;// z axis
const int magnetMotorPin2 = 44;
const int magnetMotorPin3 = 42;
const int magnetMotorPin4 = 40;

const int limitsZ = 48;// limitswitches
const int limitsY = 50;
const int limitsX = 52;

int Steps = 0;
boolean Direction = true;// gre
unsigned long last_time;
unsigned long currentMillis ;
int steps_left = 0;

void setup() {
  pinMode(magnetMotorPin1, OUTPUT);
  pinMode(magnetMotorPin2, OUTPUT);
  pinMode(magnetMotorPin3, OUTPUT);
  pinMode(magnetMotorPin4, OUTPUT);

  pinMode(stepPinY, OUTPUT);
  pinMode(dirPinY, OUTPUT);
  pinMode(stepPinX, OUTPUT);
  pinMode(dirPinX, OUTPUT);

  pinMode(limitsX, INPUT);
  pinMode(limitsY, INPUT);
  pinMode(limitsZ, INPUT);

  pinMode(magnet, OUTPUT);

  // Set the pins for the checkerboard
  for (int i = 0; i < 10; i++) {
    pinMode(columns[i], OUTPUT);
    digitalWrite(columns[i], LOW);
  }

  for (int i = 0; i < 5; i++) {
    pinMode(rows[i], INPUT);
  }

  Serial.begin(9600);
}

int beweegArm = 0;
int beweegMagneet = 0;
int curX = 0;
int curY = 0;
int gotoX;
int gotoY;

void loop() {
  if (Serial.available() && beweegArm == 0) {
    char byteIn = Serial.read();

    switch (byteIn) {
      case 'c':
        gotoX = Serial.parseInt();
        gotoY = Serial.parseInt();
        printf("X: ", gotoX, " Y: ", gotoY);
        beweegArm = 1;
        break;
      case '1' :
        digitalWrite(magnet, HIGH);
        break;
      case '2' :
        digitalWrite(magnet, LOW);
        break;
      case 'p' :
        Serial.println("pick stone up");
        beweegMagneet = 1;
        break;
      case 'd' :
        Serial.println("drop the stone");
        beweegMagneet = 2;
        break;
      case 'r' :
        Serial.println("move to origin");
        resetRobot();
        break;
      case 'b' :
        Serial.println("printing board layout");
        checkBoard();
        printBoard();
        break;
    }
  }
  if (beweegArm) {
    moveRobot(gotoX, gotoY);
    delay(1000);
    beweegArm = 0;
  }

  if (beweegMagneet) {
    moveMagnet();
    beweegMagneet = 0;

  }
}

void moveMagnet() {
  steps_left = 2048;
  if (beweegMagneet == 1) {
    Direction = false;
    while (steps_left > 0 && Direction == false) {
      currentMillis = micros();
      if (currentMillis - last_time >= 1000) {
        stepper(1);
        last_time = micros();
        steps_left--;
      }
    }
    delay(3000);

  } else {
    Direction = true;
    while (digitalRead(48) == LOW) {
      stepper(1);
      delay(1);
    }
    digitalWrite(magnet, LOW);
    delay(3000);
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
      Serial.println(endY);
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

void checkBoard() { // This reads the sensors under the board to determine the position of all the pieces
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

void printBoard() {
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
