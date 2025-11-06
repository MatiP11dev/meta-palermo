DESCRIPTION = "LoRa Gateway Bridge"
HOMEPAGE = "https://www.loraserver.io/"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bc4546f147d6f9892ca1b7d23bf41196"
GIT_BRANCH="master"
GO_IMPORT = "git://github.com/chirpstack/chirpstack-gateway-bridge"
SRCREV="ea8985d5b9126f6948a8f362dbe770041691b3f8"
SRC_URI = "git://github.com/chirpstack/chirpstack-gateway-bridge;branch=${GIT_BRANCH};protocol=https \
           file://lora-gateway-bridge.toml \
"
#SRC_URI[md5sum] = "e7700cd481eb1c6545ae5b4bf51379cd"
#SRC_URI[sha256sum] = "99dcda3fe7c880f857960ff6f2ab0f63fed3739afed648c34b5d8666c13b32e4"
SRC_URI[sha256sum] = "02c01e1d72d5c82416a0e6a51e7ba7203a98f00a646ed730e796dfe4430614b3"
PR = "r1"
GO_INSTALL = "${GO_IMPORT}"
LORA_GATEWAY_BRIDGE_DIR = "/opt/lora-gateway-bridge"
#DEFAULTTUNE = "armv7athf-neon"
DEPENDS="libgcc libb64 mosquitto redis postgresql bash"
RDEPENDS:${PN}="libgcc libb64 mosquitto redis postgresql bash"
#INSANE_SKIP:${PN} = "ldflags"
INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "textrel"
INSANE_SKIP:${PN} += "buildpaths"
#INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
#INHIBIT_PACKAGE_STRIP = "1"
S="${WORKDIR}/git"

do_compile(){
    cd ${S}
    oe_runmake build
}

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/build/chirpstack-gateway-bridge ${D}${bindir}
    install -d ${D}/etc
    install -m 755 ${S}/packaging/files/chirpstack-gateway-bridge.service ${D}/etc
    #install -d ${D}/var/config/lora-gateway-bridge
    #install -m 0644 ${S}/packaging/files/chirpstack-gateway-bridge.toml ${D}/opt/lora-gateway-bridge/lora-gateway-bridge.toml
}

FILES:${PN} += "${bindir}/*"
FILES:${PN}-dbg += "${bindir}/*.debug"

#CONFFILES:${PN} += "/var/config/lora-gateway-bridge/lora-gateway-bridge.toml"

inherit go
