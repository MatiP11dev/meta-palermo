DESCRIPTION = "LoRa Gateway Bridge"
HOMEPAGE = "https://www.loraserver.io/"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e52a56a5b272102f5f53d67fdc2dd122"
GIT_BRANCH="master"
SRCREV="01246dd1240873e12ffe246aab4e98fe8ecec5c3"
SRC_URI = "git://github.com/chirpstack/chirpstack;branch=${GIT_BRANCH};protocol=https \
           crate://crates.io/cargo-deb/1.43.1\
           crate://crates.io/cargo-generate-rpm/0.12.1\
           crate://crates.io/ar/0.9.0"
#SRC_URI[md5sum] = "e7700cd481eb1c6545ae5b4bf51379cd"
#SRC_URI[sha256sum] = "99dcda3fe7c880f857960ff6f2ab0f63fed3739afed648c34b5d8666c13b32e4"
SRC_URI[sha256sum] = "02c01e1d72d5c82416a0e6a51e7ba7203a98f00a646ed730e796dfe4430614b3"
SRC_URI[cargo-deb-1.43.1.sha256sum] = "35d7a62f626b537fb110b4a2588a18be8f9588b1a1c5c0e16c33f56a18cb9ecd"
SRC_URI[cargo-generate-rpm-0.12.1.sha256sum] = "057752862ab29745dc104d49256a362cec78688bfc5699261ad73ea796616d04"
SRC_URI[ar-0.9.0.sha256sum] = "d67af77d68a931ecd5cbd8a3b5987d63a1d1d1278f7f6a60ae33db485cdebb69"
PR = "r1"
#LORA_GATEWAY_BRIDGE_DIR = "/opt/lora-gateway-bridge"
DEPENDS="rust libgcc libb64 gcc mosquitto redis postgresql cargo bash"
RDEPENDS:${PN}="rust libgcc libb64 gcc mosquitto redis postgresql cargo bash"
#INSANE_SKIP:${PN} = "ldflags"
INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "textrel"
INSANE_SKIP:${PN} += "buildpaths"
#INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
#INHIBIT_PACKAGE_STRIP = "1"
S="${WORKDIR}/git"


do_compile(){
    cd ${S}
    oe_runmake dev-dependencies
}
inherit cargo
