const int column1 = 33;
const int column2 = 35;
const int column3 = 37;
const int column4 = 39;
const int column5 = 41;
const int column6 = 43;
const int column7 = 45;
const int column8 = 47;
const int column9 = 49;
const int column10 = 51;

const int row1 = 23;
const int row2 = 25;
const int row3 = 27;
const int row4 = 29;
const int row5 = 31;

const int columns[10] = {33,35,37,39,41,43,45,47,49,51};
const int rows[5] = {23,25,27,29,31};
int array[10][5];

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  Serial.println("begin");
  
  
  for(int i =0; i<10;i++){
    pinMode(columns[i],OUTPUT);
    digitalWrite(columns[i],LOW);
  }
  
  for(int i =0;i<5;i++){
    pinMode(rows[i],INPUT);
  }
  
}

void loop() {  
  
  for(int i =0; i<10;i++){
    digitalWrite(columns[i],HIGH);
    delay(2);
    for(int j=0; j<5;j++){
      delay(2);
       //Serial.print(digitalRead(rows[j]));
       if(digitalRead(rows[j])){
         array[i][j] = 0;
       } else{
         array[i][j] = 1;
       }
    }
    digitalWrite(columns[i],LOW);
    delay(2);
    //Serial.println();
  }
  //Serial.println("\n\n");
  delay(1000);
  if(Serial.available()){
    Serial.read();
    
    for(int i = 0; i<10; i++){
      for (int j = 0; j<5;j++){
         if(array[i][j]){
            Serial.print("[X]");
         }else{
           Serial.print("[ ]");
         }
      }
      Serial.println();
    }
    Serial.println("\n\n");
  }
  
  
  //*/
  /*
  digitalWrite(51,LOW);
  digitalWrite(49,HIGH);
  Serial.print(digitalRead(29));
  Serial.print(digitalRead(31));
  Serial.println();
  delay(2);
  digitalWrite(51,HIGH);
  digitalWrite(49,LOW);
  delay(2);
  Serial.print(digitalRead(29));
  Serial.print(digitalRead(31));
  Serial.println();
  Serial.println();
  delay(500);
  //*/
}
