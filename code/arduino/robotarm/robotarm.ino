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
 
void setup() {
  // Sets the two pins as Outputs
  pinMode(stepPin,OUTPUT); 
  pinMode(dirPin,OUTPUT);
  pinMode(stepPin2,OUTPUT); 
  pinMode(dirPin2,OUTPUT);
}
void loop() {
  digitalWrite(dirPin,HIGH); // Enables the motor to move in a particular direction
  digitalWrite(dirPin2,HIGH); // Enables the motor to move in a particular direction
  // Makes 200 pulses for making one full cycle rotation
  for(int x = 0; x < 1200; x++) {
    digitalWrite(stepPin,HIGH); 
    digitalWrite(stepPin2,HIGH);
    delayMicroseconds(500); 
    digitalWrite(stepPin,LOW); 
    digitalWrite(stepPin2,LOW);
    delayMicroseconds(500); 
  }
  delay(0); // One second delay
  
  digitalWrite(dirPin,LOW); //Changes the rotations direction
  digitalWrite(dirPin2,LOW); // Enables the motor to move in a particular direction
  // Makes 400 pulses for making two full cycle rotation
  for(int x = 0; x < 1200; x++) {
    digitalWrite(stepPin,HIGH);
    digitalWrite(stepPin2,HIGH);
    delayMicroseconds(500);
    digitalWrite(stepPin,LOW);
    digitalWrite(stepPin2,LOW);
    delayMicroseconds(500);
  }
  delay(0);
}
