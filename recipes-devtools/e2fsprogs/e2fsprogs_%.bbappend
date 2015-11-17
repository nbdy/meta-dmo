FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_class-target += "file://e2fsck.conf"

do_install_append_class-target(){
    install -m 0755 -d /etc/
    cp ${WORKDIR}/e2fsck.conf ${D}/etc/
}

FILES_e2fsprogs-e2fsck_append_class-target = "/etc/"

