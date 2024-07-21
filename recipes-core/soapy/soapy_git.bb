GIT_BRANCH = "master"
GITHUB_USER = "pothosware"
LICENSE="BSL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE_1_0.txt;md5=e4224ccaecb14d942c71d31bef20d78c"
SRCREV="8c6cb7c5223fad995e355486527589c63aa3b21e"

SRC_URI = "git://github.com/${GITHUB_USER}/SoapySDR;branch=${GIT_BRANCH};protocol=https"



S = "${WORKDIR}/git"
DEPENDS += "python3 python3-numpy swig"

RDEPENDS:${PN}+= "python3 python3-numpy swig"

inherit cmake pkgconfig

