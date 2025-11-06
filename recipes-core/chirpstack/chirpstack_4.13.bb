SUMMARY="Chirpstack api for lora gateway"
SRC_URI="file://chirpstack_4.13.0_postgres_linux_armv7hf.tar.gz;name=foo \
         file://chirpstack.toml \
         file://start.sh \
         file://chirpstack.service"
LICENSE="CLOSED"
S="${WORKDIR}/src"
SRC_URI[sha256sum]="3beedac6151225cbe7e4cb0aec7921efeea14e1959a7424203a6cd57ebbf8b69"
SYSTEMD_AUTO_ENABLE = "disable"
do_install(){
    install -d ${D}/loragw/bin/
    install -d ${D}/loragw/etc/chirpstack
    install -d ${D}/loragw/var/lib/chirpstack
    install -m 644 ${WORKDIR}/chirpstack ${D}/loragw/bin/
    install -d ${D}${systemd_system_unitdir}
    install -m 644 ${WORKDIR}/chirpstack.service ${D}${systemd_system_unitdir}
    install -m 644 ${WORKDIR}/chirpstack.toml ${D}/loragw/etc/chirpstack
    install -m 644 ${WORKDIR}/start.sh ${D}/loragw/
}
FILES:${PN}="/loragw/bin/* /loragw/start.sh /loragw/var/lib/chirpstack /loragw/etc/* ${systemd_system_unitdir}/chirpstack.service"
SYSTEMD_SERVICE:${PN} = "chirpstack.service"
