GIT_BRANCH = "master"
GITHUB_USER = "eclipse-mosquitto"
LICENSE="AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ca9a8f366c6babf593e374d0d7d58749"
SRCREV="c85313dbde34883a150a897533d6ea5357fe3c00"

SRC_URI = "git://github.com/${GITHUB_USER}/mosquitto;branch=${GIT_BRANCH};protocol=https \
           file://0001-change-chown-user.patch \
           file://0001-mosquitto-loragw.patch \
           file://mosquitto.service"

INHIBIT_PACKAGE_STRIP="1"
ERROR_QA:remove = "patch-status"

WARN_QA:append = " patch-status"
 
DEPENDS += "glibc cjson libgcc openssl libb64"
S = "${WORKDIR}/git"
RDEPENDS:${PN}+= "glibc cjson libgcc openssl libb64"
do_compile(){
    oe_runmake binary
}
do_install(){
    install -d ${D}/loragw/sbin
    install -d ${D}/loragw/lib
    install -d ${D}/loragw/bin
    install -d ${D}/loragw/run
    install -d ${D}/loragw/var/lib/mosquitto
    install -m 755 ${S}/src/mosquitto ${D}/loragw/sbin
    install -m 755 ${S}/lib/libmosquitto.so.1 ${D}/loragw/lib
    install -d ${D}/loragw/etc
    install -d ${D}/loragw/etc/mosquitto
    install -m 644 ${S}/mosquitto.conf "${D}/loragw/etc/mosquitto/mosquitto.conf"
    install -m 644 ${S}/aclfile.example "${D}/loragw/etc/mosquitto/aclfile.example"
    install -m 644 ${S}/pwfile.example "${D}/loragw/etc/mosquitto/pwfile.example"
    install -m 644 ${S}/pskfile.example "${D}/loragw/etc/mosquitto/pskfile.example"
    install -m 755 ${S}/client/mosquitto_pub ${D}/loragw/bin
    install -m 755 ${S}/client/mosquitto_rr ${D}/loragw/bin
    install -m 755 ${S}/client/mosquitto_sub ${D}/loragw/bin
    install -d ${D}${systemd_system_unitdir}
    install -m 755 ${WORKDIR}/mosquitto.service ${D}${systemd_system_unitdir}/mosquitto.service
}
INSANE_SKIP:${PN}="patch-status"
FILES:${PN} += "/loragw/sbin/* /loragw/run/ /loragw/var/* /loragw/lib/* /loragw/bin/* /loragw/etc/* /lib/systemd/system/mosquitto.service"
#FILES:${PN}-dev = "${libdir}/* ${systemd_unitdir}/* ${systemd_system_unitdir}/* ${systemd_system_unitdir}/mosquitto.service"
SYSTEMD_SERVICE:${PN} = "mosquitto.service"
#inherit cmake pkgconfig

