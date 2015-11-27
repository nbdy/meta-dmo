require recipes-graphics/xorg-lib/xorg-lib-common.inc
SUMMARY = "X Athena Widget Set"
DEPENDS += "xproto virtual/libx11 libxext xextproto libxt libxmu libxpm libxau xmlto-native"

LIC_FILES_CHKSUM = "file://COPYING;md5=8d5e9a1707c9f4300b992b34e21ac54c"

PE = "1"
PR = "r2"

XORG_PN = "libXaw3d"

SRC_URI[md5sum] = "b804b1eeba6008a1c11c767eb69f8b12"
SRC_URI[sha256sum] = "b74f11681061c1492c03cbbe6e318f9635b3877af0761fc0e67e1467c3a6972b"

