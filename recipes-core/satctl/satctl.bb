
SRC_URI = "file://satlab-satctl-v1.21.0.tar.xz"
SRC_URI[sha256sum]="62613cfc5af51cd25102068fce746e4e48cbf9f1aeac29d44689e19d59379965"
LICENSE = "CLOSED"
DEPENDS += "python3 libsocketcan python3-jinja2 python3-jsonschema gcc"
do_compile[nostamp] = "1"
RDEPENDS:${PN}+= "python3 libsocketcan python3-jinja2 python3-jsonschema gcc"
S = "${WORKDIR}/satctl-1.0/satlab-satctl-v1.21.0"
do_configure:append(){
    cp -r ${WORKDIR}/sources-unpack/satlab-satctl-v1.21.0 ${WORKDIR}/satctl-1.0/
    #${WORKDIR}/recipe-sysroot-native/usr/bin/python3-native/python3.13 waf distclean configure
}
do_compile:prepend(){
 echo "Ejecutando do_compile..."
 cd ${S}
 ${WORKDIR}/recipe-sysroot-native/usr/bin//python3-native/python3.13 waf distclean configure build
}
do_install(){
    install -d ${D}${bindir}
    install -m 0755 ${S}/build/satctl ${D}${bindir}
}

