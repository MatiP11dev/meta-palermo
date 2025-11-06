GIT_BRANCH = "master"
GITHUB_USER = "srsran"
LICENSE="AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=73f1eb20517c55bf9493b7dd6e480788"
SRCREV="ec29b0c1ff79cebcbe66caa6d6b90778261c42b8"

SRC_URI = "git://github.com/${GITHUB_USER}/srsRAN_4G;branch=${GIT_BRANCH};protocol=https"


DEPENDS += "libiio fftw mbedtls boost soapy lksctp-tools usrsctp libconfig zeromq soapysdr"
S = "${WORKDIR}/git"
RDEPENDS:${PN}+= "libiio mbedtls boost soapy lksctp-tools srsran usrsctp libconfig zeromq bash soapysdr"
RDEPENDS:${PN}-staticdev += "bash"
FILES:${PN}-staticdev += "/data/bin/* /data/include/* /data/lib/*.a /data/share/*"
FILES:${PN}-staticdev:remove = "/data/lib/*.so"
FILES:${PN}-dev += "/data/lib/*.so*"
INSANE_SKIP_${PN} += " ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""
# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = "\
                  -DCMAKE_INSTALL_PREFIX=/data/"
TOOLCHAIN_TARGET_TASK += "example-staticlib-staticdev"
inherit cmake pkgconfig
