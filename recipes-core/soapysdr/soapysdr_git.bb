GIT_BRANCH = "sdr_gadget_timestamping"
GITHUB_USER = "pgreenland"
LICENSE="AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fc178bcd425090939a8b634d1d6a9594"
SRCREV="271c1b29d0b9a536162c5dbda5a8108ef95a2306"

SRC_URI = "git://github.com/${GITHUB_USER}/SoapyPlutoSDR;branch=${GIT_BRANCH};protocol=https \
           file://0001-fix-compiler-error.patch \ 
           file://0001-remove-compiler-option.patch \
           file://0002-fix-compiler-error.patch \
           file://0001-gomspace-context.patch \
           file://0001-timestamp-support.patch \
           file://0002-timestamp-support.patch"


DEPENDS += "libiio libad9361-iio soapy libusb1"
S = "${WORKDIR}/git"
RDEPENDS:${PN}+= "libiio libad9361-iio soapy  libusb1"

FILES:${PN}="${libdir}/SoapySDR/modules0.8-3/* \
             ${bindir}/*" 

inherit cmake pkgconfig

