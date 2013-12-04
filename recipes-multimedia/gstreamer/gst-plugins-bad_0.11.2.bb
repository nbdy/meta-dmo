require gst-plugins.inc

LICENSE = "GPLv2+ & LGPLv2+ & LGPLv2.1+ "
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://gst/tta/filters.h;beginline=12;endline=29;md5=629b0c7a665d155a6677778f4460ec06 \
                    file://COPYING.LIB;md5=55ca817ccb7d5b5b66355690e9abc605 \
                    file://gst/tta/crc32.h;beginline=12;endline=29;md5=71a904d99ce7ae0c1cf129891b98145c"

DEPENDS += "gst-plugins-base tremor curl"

PR = "${INC_PR}.5"

inherit gettext

EXTRA_OECONF += "--enable-external --enable-experimental --disable-directsound \
                 --disable-direct3d --disable-directdraw --disable-apple_media \
                 --disable-osx_video --disable-quicktime --disable-sdltest"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'x11', 'librsvg', '', d)}"
PACKAGECONFIG[librsvg] = "--enable-librsvg,--disable-librsvg,librsvg,"

ARM_INSTRUCTION_SET = "arm"

do_configure_prepend() {
	# This m4 file contains nastiness which conflicts with libtool 2.2.2
	rm ${S}/m4/lib-link.m4 || true
}

SRC_URI[md5sum] = "0353596b7911a46f2e65316e6af4b4c4"
SRC_URI[sha256sum] = "86de7a4409d93933a9409fc86d807de1dd17123d589bd351350f97baf0658f8c"
