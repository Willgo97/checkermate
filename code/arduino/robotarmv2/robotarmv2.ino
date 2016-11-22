/*     Simple Stepper Motor Control Exaple Code
 *      
 *  by Dejan Nedelkovski, www.HowToMechatronics.com
 *  
 */
// defines pins numbers
const int stepPin = 3; 
const int stepPin2 = 5;
const int dirPin = 4; 
const int dirPin2 = 6;
const int limitsX = 13;
const int limitsY = 12;

 
void setup() {
  // Sets the two pins as Outputs
  pinMode(stepPin,OUTPUT); 
  pinMode(dirPin,OUTPUT);
  pinMode(stepPin2,OUTPUT); 
  pinMode(dirPin2,OUTPUT);
  // Serial
  Serial.begin(9600);
}

int beweegArm = 0;
int curX = 0;
int curY = 0;
int gotoX;
int gotoY;

void loop() {
  if(Serial.available() && beweegArm==0){
           
      char byteIn = Serial.read();
      //met 'c' vanuit Java begint de motor naar de volgende 2 coördinaten te gaan
      if(byteIn == 'c') {
        gotoX = Serial.parseInt();
        gotoY = Serial.parseInt();
        Serial.print("X: ");
        Serial.print(gotoX);
        Serial.print(" Y: ");
        Serial.print(gotoY);
        Serial.println();
        beweegArm = 1;
      }
      
  }
  if(beweegArm){
    moveRobot(gotoX,gotoY);
    delay(6000);
    beweegArm = 0;
  }
}
//  beweeg de robot naar deze positie in mm
void moveRobot(int x, int y){

  int diffX = x-curX;
  int diffY = y-curY;
  
  if(diffX < 0){
    digitalWrite(dirPin,HIGH);
  }else{
    digitalWrite(dirPin,LOW);
  }
  
  if(diffY < 0){
    digitalWrite(dirPin2,HIGH);
  }else{
    digitalWrite(dirPin2,LOW);
  }

  int stepsX = abs(diffX) * 5;
  int stepsY = abs(diffY) * 5;
  
  int steps = 0;
  boolean endX = false;
  boolean endY = false;
  
  while(endX == false || endY == false){
    steps++;
    
    if(endX == false){
      digitalWrite(stepPin,HIGH);
    }
    if(endY == false){
      digitalWrite(stepPin2,HIGH);     
    }
    
    delayMicroseconds(500); 
    
    if(endX == false){
      digitalWrite(stepPin,LOW);
      if(steps > stepsX){
        endX = true;
      }
    }
    
    if(endY == false){
      digitalWrite(stepPin2,LOW);     
      if(steps > stepsY){
        endY = true;
      }
    }
    
    delayMicroseconds(500);
  }

  curX = x;
  curY = y;
}

// beweeg de robot terug naar de begin positie
void resetRobot(){ 
  boolean endX = digitalRead(limitsX);
  boolean endY = digitalRead(limitsY);

  digitalWrite(dirPin,LOW);
  digitalWrite(dirPin2,LOW);


  while(endX == false || endY == false){
    if(endX ==false){
      endX = digitalRead(limitsX);
    }
    if(endY == false){
      endY = digitalRead(limitsY);
    }
    
    if(endX == false){
      digitalWrite(stepPin,HIGH);
    }
    if(endY == false){
      digitalWrite(stepPin2,HIGH);     
    }
    
    delayMicroseconds(500); 
    
    if(endX == false){
      digitalWrite(stepPin,LOW);
    }
    if(endY == false){
      digitalWrite(stepPin2,LOW);     
    }
    
    delayMicroseconds(500);
  }
  
  curX = 0;
  curY = 0;
}
