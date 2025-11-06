#!/bin/sh

export LD_LIBRARY_PATH=/home/root/loragw/lib
export PATH=/home/root/loragw/bin:$PATH
export PGDATA=/home/root/loragw/var/lib/postgresql

echo "Iniciando PostgreSQL..."
/home/root/loragw/bin/postgres -D "$PGDATA" &
sleep 2

echo "Iniciando Mosquitto..."
/home/root/loragw/bin/mosquitto -c /loragw/etc/mosquitto/mosquitto.conf &

echo "Iniciando ChirpStack..."
/home/root/loragw/bin/chirpstack-gateway-bridge
/home/root/loragw/bin/chirpstack -c /loragw/etc/chirpstack/chirpstack.toml &

echo "Iniciando LoRa Packet Forwarder..."
/home/root/loragw/bin/lora_pkt_fwd -c /loragw/etc/lorafwd/global_conf.json &

echo "Todos los servicios han sido lanzados."
