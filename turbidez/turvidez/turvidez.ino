#include <NewPing.h>
 
/*Aqui se configuran los pines donde debemos conectar el sensor*/
#define TRIGGER_PIN  12
#define ECHO_PIN     11
#define MAX_DISTANCE 9

// Pin donde se conectan los leds
int pinLed1 = 2;
int pinbom = 3;
int pinval1 = 4;
int pinval2 = 5;

// Pin analogico de entrada para el LDR
int pinLDR = 0;
 
// Variable donde se almacena el valor del LDR
int valorLDR = 0; 

/*Crear el objeto de la clase NewPing*/
NewPing sonar(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE);
 
void setup()
{
  // Configuramos como salidas los pines donde se conectan los led
  pinMode(pinLed1, OUTPUT);
  pinMode(pinbom, OUTPUT);
  pinMode(pinval1, OUTPUT);
  pinMode(pinval2, OUTPUT);
  
  //  Configurar el puerto serial
  Serial.begin(9600);
}
 
void loop()
{
  digitalWrite(pinLed1, HIGH);
 
  // Guardamos el valor leido del ADC en una variable
  // El valor leido por el ADC (voltaje) aumenta de manera directamente proporcional
  // con respecto a la luz percibida por el LDR
  valorLDR= analogRead(pinLDR);
  
  // Devolver el valor leido a nuestro monitor serial en el IDE de Arduino
  Serial.println(valorLDR);
  //Serial.println("");
  // Esperar unos milisegundos antes de actualizar
  delay(500);
  // Obtener medicion de tiempo de viaje del sonido y guardar en variable uS
  int uS = sonar.ping_median();
  // Imprimir la distancia medida a la consola serial
  
  // Calcular la distancia con base en una constante
  Serial.print(uS / US_ROUNDTRIP_CM);
  if(Serial.available()>0){
    int b = Serial.read();
     if(b=='0'){
      digitalWrite(pinbom, LOW);
      digitalWrite(pinval1, LOW);
      digitalWrite(pinval2, LOW);
    }
    if(b=='1'){
      digitalWrite(pinbom, HIGH);
    }
    else{
      digitalWrite(pinbom, LOW);
    }
    if(b=='2'){
      digitalWrite(pinval1, HIGH);
    }
    else{
      digitalWrite(pinval1, LOW);
    }
    if(b=='3'){
      digitalWrite(pinval2, HIGH);
    }
    else{
      digitalWrite(pinval2, LOW);
    }
  }
}
