SUMMARY="Package group for loragw"
LICENSE="CLOSED"


RDEPENDS:${PN}="mosquitto postgresql postgresql-client postgresql-contrib postgresql-timezone chirpstack chirpstack-gateway sx1302"
inherit packagegroup

