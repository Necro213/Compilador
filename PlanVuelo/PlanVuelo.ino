// Rutina de Setup, corre una vez p cuando se presiona Reset
void setup() {
  // Inicializa los pines 6, 9, 10 y como pines de salida
  pinMode(6, OUTPUT);    // Stick Izquierdo Acelerar - Desacelerar
  pinMode(9, OUTPUT);    // Stick Izquierdo Rotación Izq - Der
  pinMode(10, OUTPUT);   // Stick Derecho Eje X
  pinMode(11 , OUTPUT);  // Stick Izquierdo Eje Y
  Serial.begin(9600);
}

// Valor mayor a 127
void up(int time){
  analogWrite(6, 190);
  delay(time);
  digitalWrite(6, 0);
  delay(time);
}

// Valor mayor a 127
void forward(int time){
  analogWrite(6, 210);
  analogWrite(11, 185);
  delay(time);
  digitalWrite(6, 0);
  digitalWrite(11,0);
  delay(time);
}

// Valor menor a 127
void backward(int time){
  analogWrite(6, 210);
  analogWrite(11, 60);
  delay(time);
  digitalWrite(6, 0);
  digitalWrite(11,0);
  delay(time);
}

// Valor menor a 127 rota izquierda, valor mayor a 127 rota derecha
void rotate(int valor){
  if(valor <= 127){
    analogWrite(6, 210);
    analogWrite(9, 75);
    delay(1500);
    digitalWrite(6, 0);
    digitalWrite(9,0);
    delay(1500); 
  }

  if(valor > 127){
    analogWrite(6, 210);
    analogWrite(9, 185);
    delay(1500);
    digitalWrite(6, 0);
    digitalWrite(9,0);
    delay(1500); 
  }
}

// Valor menor a 127
void left(int time){
  analogWrite(6, 210);
  analogWrite(10, 75);
  delay(time);
  digitalWrite(6, 0);
  digitalWrite(10,0);
  delay(time);
}

// Valor mayor a 127
void right(int time){
  analogWrite(6, 210);
  analogWrite(10, 185);
  delay(time);
  digitalWrite(6, 0);
  digitalWrite(10,0);
  delay(time);
}

// Sincronizar control con drone
void sincronizar(){
  analogWrite(6,254);
  analogWrite(6,0);
}

void loop() {
   

  for(int A=0;A<255 ;A+=2){
    analogWrite(6,A);
    delay(20);
  }
  digitalWrite(6,0);
for(int i=0; i<50; i++){
	up(5);
	forward(10);
	left(3);
	right(4);
}

}