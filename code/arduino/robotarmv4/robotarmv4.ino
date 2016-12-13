#define IN1  46
#define IN2  44
#define IN3  42
#define IN4  40
int Steps = 0;
boolean Direction = true;// gre
unsigned long last_time;
unsigned long currentMillis ;
int steps_left = 0;

// defines pins numbers
const int magnetPin = 2;
const int stepPin = 3; // X as
const int stepPin2 = 5; // Y as
const int dirPin = 4;
const int dirPin2 = 6;
const int limitsX = 52;
const int limitsY = 50;
const int limitsZ = 48;
const int magnet = 14;

// checkerboard
const int columns[10] = {33,35,37,39,41,43,45,47,49,51};
const int rows[5] = {23,25,27,29,31};
int array[10][10];


void setup() {
  // Sets the pins as outputs
  pinMode(stepPin, OUTPUT);
  pinMode(dirPin, OUTPUT);
  pinMode(stepPin2, OUTPUT);
  pinMode(dirPin2, OUTPUT);
  
  pinMode(magnet,OUTPUT);
  digitalWrite(magnet,LOW);
  
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  pinMode(IN3, OUTPUT);
  pinMode(IN4, OUTPUT);
  
  // Set the pins for the checkerboard
  for(int i =0; i<10;i++){
    pinMode(columns[i],OUTPUT);
    digitalWrite(columns[i],LOW);
  }
  
  for(int i =0;i<5;i++){
    pinMode(rows[i],INPUT);
  }
  
  // Serial
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
    if (byteIn == 'c') {
      gotoX = Serial.parseInt();
      gotoY = Serial.parseInt();
      Serial.print("X: ");
      Serial.print(gotoX);
      Serial.print(" Y: ");
      Serial.print(gotoY);
      Serial.println();
      beweegArm = 1;
    }

    if (byteIn == 'p') {//pickup de damsteen
      Serial.println("pak de steen");
      beweegMagneet = 1;
    }

    if (byteIn == 'd') { // drop de damsteen
      Serial.println("drop de steen");
      beweegMagneet = 2;
    }
    
    if (byteIn == 'r') {
      Serial.println("reset");
      Serial.println(digitalRead(limitsX));
      resetRobot();
    }
    if(byteIn== 'b'){
      Serial.println("printing board layout");
      checkBoard();
      printBoard(); 
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
    //digitalWrite(magnetPin,HIGH);
      
  }
}

void moveMagnet(){
    steps_left = 8192;
    if(beweegMagneet == 1){
      Direction = true;
    }else{
      Direction = false;
    }
    while (steps_left > 0) {
      currentMillis = micros();
      if (currentMillis - last_time >= 1000) {
        stepper(1);
        last_time = micros();
        steps_left--;
      }
    }
  }


void moveRobot(int x, int y) { //  beweeg de robot naar deze positie in mm

  int diffX = x - curX;
  int diffY = y - curY;

  if (diffX < 0) {
    digitalWrite(dirPin, HIGH);
  } else {
    digitalWrite(dirPin, LOW);
  }

  if (diffY < 0) {
    digitalWrite(dirPin2, HIGH);
  } else {
    digitalWrite(dirPin2, LOW);
  }

  int stepsX = abs(diffX) * 4;
  int stepsY = abs(diffY) * 8;

  int steps = 0;
  boolean endX = false;
  boolean endY = false;

  while (endX == false || endY == false) {
    steps++;

    if (endX == false) {
      digitalWrite(stepPin, HIGH);
    }
    if (endY == false) {
      digitalWrite(stepPin2, HIGH);
    }

    delayMicroseconds(1000);

    if (endX == false) {
      digitalWrite(stepPin, LOW);
      if (steps > stepsX) {
        endX = true;
      }
    }

    if (endY == false) {
      digitalWrite(stepPin2, LOW);
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

  digitalWrite(dirPin, HIGH);
  digitalWrite(dirPin2, LOW);
  

  while (endX == false || endY == false) {

    if (endX == false) {
      endX = digitalRead(limitsX);
    }
    if (endY == false) {
      endY = digitalRead(limitsY);
      Serial.println(endY);
    }

    if (endX == false) {
      digitalWrite(stepPin, HIGH);
    }
    if (endY == false) {
      digitalWrite(stepPin2, HIGH);
    }

    delayMicroseconds(1000);

    if (endX == false) {
      digitalWrite(stepPin, LOW);
    }
    if (endY == false) {
      digitalWrite(stepPin2, LOW);
    }

    delayMicroseconds(1000);
  }
  delay(300);
  moveRobot(4,0);

  curX = 0;
  curY = 0;
}

void stepper(int xw) {
  for (int x = 0; x < xw; x++) {
    switch (Steps) {
      case 0:
        digitalWrite(IN1, LOW);
        digitalWrite(IN2, LOW);
        digitalWrite(IN3, LOW);
        digitalWrite(IN4, HIGH);
        break;
      case 1:
        digitalWrite(IN1, LOW);
        digitalWrite(IN2, LOW);
        digitalWrite(IN3, HIGH);
        digitalWrite(IN4, HIGH);
        break;
      case 2:
        digitalWrite(IN1, LOW);
        digitalWrite(IN2, LOW);
        digitalWrite(IN3, HIGH);
        digitalWrite(IN4, LOW);
        break;
      case 3:
        digitalWrite(IN1, LOW);
        digitalWrite(IN2, HIGH);
        digitalWrite(IN3, HIGH);
        digitalWrite(IN4, LOW);
        break;
      case 4:
        digitalWrite(IN1, LOW);
        digitalWrite(IN2, HIGH);
        digitalWrite(IN3, LOW);
        digitalWrite(IN4, LOW);
        break;
      case 5:
        digitalWrite(IN1, HIGH);
        digitalWrite(IN2, HIGH);
        digitalWrite(IN3, LOW);
        digitalWrite(IN4, LOW);
        break;
      case 6:
        digitalWrite(IN1, HIGH);
        digitalWrite(IN2, LOW);
        digitalWrite(IN3, LOW);
        digitalWrite(IN4, LOW);
        break;
      case 7:
        digitalWrite(IN1, HIGH);
        digitalWrite(IN2, LOW);
        digitalWrite(IN3, LOW);
        digitalWrite(IN4, HIGH);
        break;
      default:
        digitalWrite(IN1, LOW);
        digitalWrite(IN2, LOW);
        digitalWrite(IN3, LOW);
        digitalWrite(IN4, LOW);
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

void checkBoard(){ // This reads the sensors under the board to determine the position of all the pieces
  int foo = 0;
  for(int i =0; i<10;i++){
    digitalWrite(columns[i],HIGH);
    delay(2);
    for(int j=0; j<5;j++){
      delay(2);
       //Serial.print(digitalRead(rows[j]));
       if(i%2 == 0 ){
         foo = 1;
       }else{
         foo =0;
       }
       if(digitalRead(rows[j])){
         array[(j*2)+foo][i] = 0;
       } else{
         array[(j*2)+foo][i] = 1;
       }
    }
    digitalWrite(columns[i],LOW);
    delay(2);
    //Serial.println();
  }
}

void printBoard(){
  for(int i = 0; i<10; i++){
      for (int j = 0; j<10;j++){
         if(array[i][j]){
            Serial.print("1");
            //Serial.print("[X]");
         }else{
           Serial.print("0");
           //Serial.print("[ ]");
         }
      }
      Serial.print(",");
      //Serial.println();
    }
    Serial.println();
}
