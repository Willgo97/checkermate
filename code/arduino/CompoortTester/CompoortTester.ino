void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  

}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.available()){
    byte bytes = Serial.read();
    Serial.print("Dit komt binnen: ");
    Serial.print(bytes);
    Serial.print(" char: ");
    Serial.print((char)bytes);
    Serial.println();
  }
  delay(50);
}
