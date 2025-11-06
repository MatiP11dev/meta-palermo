SUMMARY="Chirpstack gateway bridge for lora gateway"
SRC_URI="file://chirpstack-gateway-bridge_4.0.11_linux_armv7.tar.gz;name=foo \
         file://chirpstack-gateway-bridge.service"
LICENSE="CLOSED"
S="${WORKDIR}/src"
SRC_URI[sha256sum]="183e3b85ada2a9b63e53bab9477c9215571867cf0764f0e4fe7c729cde3242c9"

do_install(){
    install -d ${D}/loragw/bin/
    install -d ${D}${systemd_system_unitdir}
    install -m 644 ${WORKDIR}/chirpstack-gateway-bridge.service ${D}${systemd_system_unitdir}
    install -m 644 ${WORKDIR}/chirpstack-gateway-bridge ${D}/loragw/bin/
}
FILES:${PN}="/loragw/bin/* ${systemd_system_unitdir}/chirpstack-gateway-bridge.service"
SYSTEMD_AUTO_ENABLE="disable"
SYSTEMD_SERVICE:${PN} = "chirpstack-gateway-bridge.service"
