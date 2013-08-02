DESCRIPTION = "Add a developer user"
HOMEPAGE = "https://emb.data-modul.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r3"

RDEPENDS_${PN} = "bash packagegroup-xfce-base"

inherit useradd

DMOUSERNAME ?= "dmo"
DMOUSERHOME ?= "/home/${DMOUSERNAME}"

# useradd command the initial password is "dmo"
USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--home ${DMOUSERHOME} \
                       --create-home --shell /bin/bash \
                       --user-group --password 6DWW10GSt.8to \
                       ${DMOUSERNAME}"

SRC_URI = "file://xinitrc"
FILES_${PN} += "/home"

do_install () {
    install -m 700 -d ${D}/${DMOUSERHOME}
    install -m 700 ${WORKDIR}/xinitrc ${D}/${DMOUSERHOME}/.xinitrc

    chown -R ${DMOUSERNAME}:${DMOUSERNAME} ${D}/${DMOUSERHOME}
}

# Prevents do_package failures with:
# debugsources.list: No such file or directory:
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

