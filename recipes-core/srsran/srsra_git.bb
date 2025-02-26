GIT_BRANCH = "master"
GITHUB_USER = "srsran"
LICENSE="AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=73f1eb20517c55bf9493b7dd6e480788"
SRCREV="ec29b0c1ff79cebcbe66caa6d6b90778261c42b8"

SRC_URI = "git://github.com/${GITHUB_USER}/srsRAN_4G;branch=${GIT_BRANCH};protocol=https"


DEPENDS += "libiio fftw mbedtls boost soapy lksctp-tools usrsctp libconfig zeromq soapysdr"
S = "${WORKDIR}/git"
RDEPENDS:${PN}+= "libiio fftw mbedtls boost soapy lksctp-tools srsran usrsctp libconfig zeromq bash soapysdr"


inherit cmake pkgconfig
