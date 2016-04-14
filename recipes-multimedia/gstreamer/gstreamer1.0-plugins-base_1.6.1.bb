DEFAULT_PREFERENCE = "-1"

include gstreamer1.0-plugins-base.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=c54ce9345727175ff66d17b67ff51f58 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d \
                   "

GSTREAMER_1_0_GIT_BRANCH = "1.6"
GSTREAMER_TAR = "true"
S = "${WORKDIR}/gst-plugins-base-${PV}"
SRC_URI[md5sum] = "a89933afbe45d8f8c92d89c2f1199ecb"
SRC_URI[sha256sum] = "9533dcfaa4ee32d435483d9fa88c06b1eba6e9bb234aacd7583f207199f44ba3"

SRC_URI_remove = "file://gstplaybin-remove-flag-deinterlace.patch"

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}
