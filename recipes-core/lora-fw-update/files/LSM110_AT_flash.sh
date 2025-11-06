echo 918 > /sys/class/gpio/export # Exporta en el filesystem el pin gpio12 que esta conectado al pin NRST de lora
echo 919 > /sys/class/gpio/export #Exporta en el filesystem el pin gpio13 que esta conectado al pin BOOT de lora
echo out > /sys/class/gpio/gpio918/direction # Se pone gpio12 como salida
echo out > /sys/class/gpio/gpio919/direction # Se pone gpio13 como salida
echo 1 > /sys/class/gpio/gpio919/value # BOOT se pone en 1 para flashear por la uart
#Resetar el modulo de lora
echo 0 > /sys/class/gpio/gpio918/value  
sleep 0.5
echo 1 > /sys/class/gpio/gpio918/value
#Flashear el programa AT_Slave en la uart
stm32flash -w LoRaWAN_AT_Slave.hex -v -g 0x0 /dev/ttyPS1

echo 0 > /sys/class/gpio/gpio919/value #BOOT se lo pone en 0 para que bootee el programa AT_Slave
#se resetea el dispositivo

echo 0 > /sys/class/gpio/gpio918/value
sleep 0.5
echo 1 > /sys/class/gpio/gpio918/value
# Se elimina los pines gpio del filesystem
echo 918 > /sys/class/gpio/unexport
echo 919 > /sys/class/gpio/unexport
