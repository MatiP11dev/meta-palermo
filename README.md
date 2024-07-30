# meta-palermo
Applicaciones de srsRan con soporte soapy para projectos de 4g.
layer dependencies:
* meta-python.
* meta-adi.
* meta-gomspace.
* meta-openembedded.
* networking-layer.
# Instalar paquetes en yocto.
Para instalar paquetes en yocto se ejecuta ```bitbake <nombre_del_paquete>```.
Ejemplo:
```
bitbake gcc
```
Para limpiar la compilacion de un paquete es
```
bitbake -c clean gcc
```
Para limpiar la compilacion y el repositorio.
```
bitbake -fc cleanall gcc
```
# Instalar paquete de una version especifica.
Para instalar un paquete de una version especifica se escribe en el archivo ```conf/local.conf``` lo siguuiente```PREFERRED_VERSION_<nombre_del_paquete> = "<version>"```
Ejemplo:
```
PREFERRED_VERSION_gcc = "arm-10.2"
```
# DO_FETCH FAILED 
En caso de que los paquetes linux-adi y u-boot-xlnx fallen al hacer ```do_fetch``` 
ejecutar el siguiente comando para eliminar el cache de los paquetes:
```
bitbake -fc cleanall linux-adi
```
```
bitbake -fc cleanall u-boot-xlnx
```
En otros casos puede ser que el nombre de rama no existe como me paso con el  AD9361.

#GRABAR LA IMAGEN POR DFU
Para grabar la imagen ext4 de la zed_board hay que conectarse por consola UART 
y tener conectado el USB OTG de la placa zed. Una vez hecho 
ejecutar el siguiente comando:
```
run update_payload 
```
Una vez ejectuado el comando, ejecute el siguente script que se encuentra
en ```/home/usuario/build/tmp/deploy/images/nanomind-z7020-zed```:
```
./flash-dfu -b
```
Luego en consola U-BOOT le va aparecer que se esta cargando la imagen. 
Una vez que se termina de bajar la imagen le va a pedir a usted que haga CTRL+C. 
Por ultimo corra la imagen en la placa con el siguiente comando u-boot:
```
run boot_payload.
```

# VER PAQUETES DISPONIBLES POR BITBAKE
Para ver los siguientes paquetes dispnibles ejecutar:
```
bitbake-layers show-recipes
```
Mi recomendacion es pasarlo en un archivo de tipo texto asi se tiene una lista de paquetes.
```
bitbake-layers show-recipes > lista_de_paquetes.txt
```

# PROGRAMAR PL EN LINUX
Para programar el pl (area del SoC que es reconfigurable por vhdl) en linux lo que se 
necesita saber es lo siguiente:
1. Hay un servicio de systemd que se corre cuando inicia la placa.
2. Al hacerle reboot el servicio no vuelve a correr. Solo corre cuando inicia la placa.
3. No se puede correr el comando por consola. Tira un error de que el archivo o directorio no se encuentra.
4. El directorio del servicio es el siguiente ```/lib/systemd/system/nanomind-bitstream.service```. Y el
   directorio del bitstream ```/lib/firmware/hdl-nanocom-sdr-z7020-zed-a.bit.bin```.
Para programar el area de pl lo que hay que hacer es:
1. Ubicar el bitstream en el directorio ```/lib/firmware/```
2. Editar el servicio en ```/lib/systemd/system/nanomind-bitstream.service```.
3. Una vez editado hay que actualizar el systemctl con este comando ```systemctl daemon-reload```.
4. Programar el pl ```systemctl start nanomind-bitstream```.

# Capas incompatibles 
Puede ser que algunos ```meta-<package>``` sean incompatibles con el poky debido a que usan
distinta rama. Pero se puede arreglar usando sobreescribiendo el archivo ```meta-<package>/conf/layer.conf```
Ahora al sobreescribir y poner otra compatibilidad tambien debera sobreescribir otras variables. Por
ejemplo:
do_install_prepend_class-target para que sea compatible con langdale(que es la rama que trabaja gomspace) 
se sobrescribe a ```do_install:prepend:class-target```.
Otro ejemplo es ```do_install_append``` que pasa a ser ```do_install:append``` para langdale.
Para encontrar la mayoria de las incompatibilidades busque las palabras append, depend, prepend.
