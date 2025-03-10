GIT_BRANCH = "main"
GITHUB_USER = "pgreenland"
LICENSE="MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=18eca73dfa9b64a2fecee3525402a828"
SRCREV="9b3864db0898e9e5c38659d45cc02babb5548541"

SRC_URI = "git://github.com/${GITHUB_USER}/pluto-sdr-ip-gadget;branch=${GIT_BRANCH};protocol=https \
           file://S55sdr_ip_gadget \
           file://0001-gomspace-context.patch"


DEPENDS += "libiio"
S = "${WORKDIR}/git"
RDEPENDS:${PN}+= "libiio"



inherit cmake pkgconfig

do_install:append() {
  install -d ${D}${sysconfdir}/init.d/
  install -m 0755 ${WORKDIR}/S55sdr_ip_gadget ${D}${sysconfdir}/init.d/
}
