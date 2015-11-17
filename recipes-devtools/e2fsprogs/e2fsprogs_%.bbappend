FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += "file://e2fsck.conf"

do_install_append(){
    install -m 0755 -d /etc/
    cp ${WORKDIR}/e2fsck.conf ${D}/etc/
}

FILES_e2fsprogs-e2fsck_append = "/etc/"

