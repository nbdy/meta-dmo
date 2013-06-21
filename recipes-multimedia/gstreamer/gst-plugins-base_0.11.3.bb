require gst-plugins.inc

LICENSE = "GPLv2+ & LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=622921ffad8cb18ab906c56052788a3f \
                    file://COPYING.LIB;md5=55ca817ccb7d5b5b66355690e9abc605"

DEPENDS += "${@base_contains('DISTRO_FEATURES', 'x11', 'virtual/libx11 libxv libsm libice', '', d)}"
DEPENDS += "alsa-lib freetype liboil libogg libvorbis libtheora util-linux tremor"

SRC_URI[md5sum] = "d403bdb5eac3bff7808c972877ea8e35"
SRC_URI[sha256sum] = "d2ab63dc5af6f1759a5619c9838cb3a2371b2fb37b953cc85db8c5fa28dcd6d5"

PR = "r2"

inherit gettext

EXTRA_OECONF += "--disable-freetypetest --disable-pango --disable-gnome_vfs \
                 --disable-orc"

do_configure_prepend() {
	# This m4 file contains nastiness which conflicts with libtool 2.2.2
	rm -f ${S}/m4/lib-link.m4
}

FILES_${PN} += "${datadir}/${BPN}"
