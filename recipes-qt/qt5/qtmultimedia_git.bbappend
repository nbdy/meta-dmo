FILESEXTRAPATHS_prepend := "${THISDIR}/qtmultimedia:"

PACKAGECONFIG_append_mx6 += "gstreamer"

SRC_URI_append_mx6 += "\
	    file://0001-gstreamer-hack-v4l2sink-in-playbin.patch \
	    file://0002-gstreamer-get-right-window-position.patch \
	    file://0003-gstreamer-fix-for-qml-window-crop-coordinates.patch \
	    file://0004-gstreamer-use-dma-between-decoder-and-converter.patch \
	    file://0005-gstreamer-set-v4l2sink-crop-win-at-play-too.patch \
	    file://0006-gstreamer-fix-stop-function.patch \
	    file://0007-gstreamer-detect-v4l2-device.patch \
	    file://0008-gstreamer-fix-overlay-window-coordinates.patch \
"

LIC_FILES_CHKSUM_remove = "file://LICENSE.LGPLv3;md5=b8c75190712063cde04e1f41b6fdad98"
LIC_FILES_CHKSUM_remove = "file://LICENSE.GPLv3;md5=40f9bf30e783ddc201497165dfb32afb"
LIC_FILES_CHKSUM_append = "file://LICENSE.LGPLv3;md5=c4fe8c6de4eef597feec6e90ed62e962"

SRCREV_mx6="04e086e33667ca1a7aa8a7be7427bfba491a72b0"

do_patch_append_mx6() {
    bb.warn ("Additional patching on qtmultimedia is done in meta-dmo, this needs to be reviewed before release")
}
