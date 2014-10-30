DESCRIPTION = "Hide X11 Cursor"
HOMEPAGE = "http://sourceforge.net/projects/unclutter"
LICENSE = "PD"

DEPENDS = "virtual/libx11"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/${PN}/${PN}/source_${PV}/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "8c4464367b2db1d15fe36a8752e917c8"
SRC_URI[sha256sum] = "3a53575fe2a75a34bc9a2b0ad92ee0f8a7dbedc05d8783f191c500060a40a9bd"

LIC_FILES_CHKSUM = "file://${S}/README;beginline=33;endline=34;md5=cb2ba5fee7e5798cd86665662d0be127"

do_compile () {
    ${CC} ${LDFLAGS} ./unclutter.c -o ./unclutter -lX11
}

do_install () {
    install -d ${D}/usr/bin
    install -m 755 ./unclutter ${D}/usr/bin
}
