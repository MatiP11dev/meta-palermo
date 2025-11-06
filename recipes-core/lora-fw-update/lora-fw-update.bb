DESCRIPTION="Recipes provide scripts for update LSM110 firmware"
LICENSE="CLOSED"
SRC_URI="file://LSM110_AT_flash.sh \
      file://LSM110_End_Node_flash.sh"
do_install(){
    install -d ${D}/${ROOT_HOME}/
    install -m 0664 ${WORKDIR}/LSM110_AT_flash.sh ${D}/${ROOT_HOME}/
    install -m 0664 ${WORKDIR}/LSM110_End_Node_flash.sh ${D}/${ROOT_HOME}/
}
FILES:${PN}="${ROOT_HOME}/LSM110_AT_flash.sh ${ROOT_HOME}/LSM110_End_Node_flash.sh"
