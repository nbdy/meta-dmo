DESCRIPTION = "dmctouch: some tools for dmctouch tests"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/usb26/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI = "file://tsc_213_121126.tgz \
           file://usbtest--add-sys-types-h.patch \
           file://usbtest--change-read-endpoint.patch \
"
S = "${WORKDIR}/TSC10LD_V213"

do_compile () {
    cd usb26
    ${CC} usbtest.c -o usbtest
}

do_install () {
    install -d ${D}/usr/bin
    install ${S}/usb26/usbtest ${D}/usr/bin/usbtest
}

