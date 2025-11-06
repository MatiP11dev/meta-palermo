GIT_BRANCH = "master"
GITHUB_USER = "Lora-net"
LICENSE="AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=d2119120bd616e725f4580070bd9ee19"
SRCREV="4b42025d1751e04632c0b04160e0d29dbbb222a5"

SRC_URI = "git://github.com/${GITHUB_USER}/sx1302_hal;branch=${GIT_BRANCH};protocol=https \
           file://0001-target-arm.patch \
           file://0002-fix-makefile.patch \
           file://config.h"
TCMODE = "default"
CPPFLAGS:prepend = "-I${STAGING_INCDIR}"
ERROR_QA:remove = "patch-status"
#inherit autotools pkgconfig
export CPP_FOR_BUILD = "${BUILD_CPP}"
export CPPFLAGS_FOR_BUILD = "${BUILD_CPPFLAGS}"

export CC_FOR_BUILD = "${BUILD_CC}"
export CFLAGS_FOR_BUILD = "${BUILD_CFLAGS}"

export CXX_FOR_BUILD = "${BUILD_CXX}"
export CXXFLAGS_FOR_BUILD="${BUILD_CXXFLAGS}"

export LD_FOR_BUILD = "${BUILD_LD}"
export LDFLAGS_FOR_BUILD = "${BUILD_LDFLAGS}"

CONFIGUREOPTS = " --build=${BUILD_SYS} \
                  --host=${HOST_SYS} \
                  --target=${TARGET_SYS} \
                  --prefix=${prefix} \
                  --exec_prefix=${exec_prefix} \
                  --bindir=${bindir} \
                  --sbindir=${sbindir} \
                  --libexecdir=${libexecdir} \
                  --datadir=${datadir} \
                  --sysconfdir=${sysconfdir} \
                  --sharedstatedir=${sharedstatedir} \
                  --localstatedir=${localstatedir} \
                  --libdir=${libdir} \
                  --includedir=${includedir} \
                  --oldincludedir=${includedir} \
                  --infodir=${infodir} \
                  --mandir=${mandir} \
                  --disable-silent-rules \
                  ${CONFIGUREOPT_DEPTRACK}"

do_compile() {
    #oe_runmake -e -C "${S}/libtools"
    #oe_runmake -e -C "${S}/libloragw"
    #oe_runmake -e -C "${S}/packet_forwarder"
    #oe_runmake -e -C "${S}/util_net_downlink"
    #oe_runmake -e -C "${S}/util_chip_id"
    #oe_runmake -e -C "${S}/util_boot"
    #oe_runmake -e -C "${S}/util_spectral_scan"
    oe_runmake -C ${S} clean all
}
do_compile:prepend() {
    mkdir -p "${S}/libtools/obj"
    mkdir -p "${S}/libloragw/obj"
    mkdir -p "${S}/packet_forwarder/obj"
    mkdir -p "${S}/util_net_downlink/obj"
    mkdir -p "${S}/util_chip_id/obj"
    mkdir -p "${S}/util_boot/obj"
    mkdir -p "${S}/util_spectral_scan/obj"
    install -d ${WORKDIR}/recipe-sysroot
    install -m 0755 ${S}/libtools/inc/* ${WORKDIR}/recipe-sysroot/usr/include
    install -m 0755 ${S}/libloragw/inc/* ${WORKDIR}/recipe-sysroot/usr/include
    install -m 0755 ${S}/packet_forwarder/inc/* ${WORKDIR}/recipe-sysroot/usr/include
    install -m 0755 ${WORKDIR}/config.h ${WORKDIR}/recipe-sysroot/usr/include       
    export CFLAGS="${CFLAGS} -I${STAGING_INCDIR}"
    export CXXFLAGS="${CXXFLAGS} -I${STAGING_INCDIR}"
    echo "INCLUDE_DIR := ${STAGING_INCDIR}" >> ${S}/Makefile
}
do_install:append(){
    install -d ${D}/loragw/bin
    install -d ${D}/loragw/etc/lorafwd
    install -m 0755 ${S}/libloragw/test_* ${D}/loragw/bin
    install -m 0755 ${S}/packet_forwarder/global_conf* ${D}/loragw/etc/lorafwd
    install -m 0755 ${S}/packet_forwarder/lora_pkt_fwd ${D}/loragw/bin
    install -m 0755 ${S}/util_boot/boot ${D}/loragw/bin
    install -m 0755 ${S}/util_chip_id/chip_id ${D}/loragw/bin
    install -m 0755 ${S}/util_net_downlink/net_downlink ${D}/loragw/bin
    install -m 0755 ${S}/util_spectral_scan/spectral_scan ${D}/loragw/bin
    install -m 0755 ${S}/tools/reset_lgw.sh ${D}/loragw/bin
}
FILES:${PN} +="/loragw/bin/* /loragw/etc/lorafwd*"
TARGET_CC_ARCH += "${LDFLAGS}"
DEPENDS += "glibc gcc libgcc libb64"
S = "${WORKDIR}/git"
RDEPENDS:${PN}+="glibc gcc libgcc libb64"
#FILES:${PN}="${D}${bindir}/*"
