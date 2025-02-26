GIT_BRANCH = "master"
GITHUB_USER = "eclipse-mosquitto"
LICENSE="AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ca9a8f366c6babf593e374d0d7d58749"
SRCREV="c85313dbde34883a150a897533d6ea5357fe3c00"

SRC_URI = "git://github.com/${GITHUB_USER}/mosquitto;branch=${GIT_BRANCH};protocol=https \
           file://0001-change-chown-user.patch"

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
    install -d ${D}${sbindir}
    install -d ${D}${libdir}
    install -d ${D}${bindir}
    install -m 755 ${S}/src/mosquitto ${D}${sbindir}
    install -m 755 ${S}/lib/libmosquitto.so.1 ${D}${libdir}
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/mosquitto
    install -m 644 ${S}/mosquitto.conf "${D}${sysconfdir}/mosquitto/mosquitto.conf"
    install -m 644 ${S}/aclfile.example "${D}${sysconfdir}/mosquitto/aclfile.example"
    install -m 644 ${S}/pwfile.example "${D}${sysconfdir}/mosquitto/pwfile.example"
    install -m 644 ${S}/pskfile.example "${D}${sysconfdir}/mosquitto/pskfile.example"
    install -m 755 ${S}/client/mosquitto_pub ${D}${bindir}
    install -m 755 ${S}/client/mosquitto_rr ${D}${bindir}
    install -m 755 ${S}/client/mosquitto_sub ${D}${bindir}
    install -d ${D}${systemd_system_unitdir}
    install -m 755 ${S}/service/systemd/mosquitto.service.simple ${D}${systemd_system_unitdir}/mosquitto.service
}
INSANE_SKIP:${PN}="patch-status"
FILES:${PN} += "${libdir}/* ${systemd_unitdir}/* ${systemd_system_unitdir}/* ${systemd_system_unitdir}/mosquitto.service"
#FILES:${PN}-dev = "${libdir}/* ${systemd_unitdir}/* ${systemd_system_unitdir}/* ${systemd_system_unitdir}/mosquitto.service"
SYSTEMD_SERVICE:${PN} = "mosquitto.service"
#inherit cmake pkgconfig

