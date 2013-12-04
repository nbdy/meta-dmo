require gst-plugins.inc

LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

SRC_URI += " \
	file://0001-v4l2-called-base-class-start.patch \
	file://0002-v4l2-fix-latency.patch \
	file://0003-v4l2src-don-t-error-in-shutdown.patch \
	file://0004-v4l2-update-for-get_param.patch \
	file://0005-caps-improve-caps-handling.patch \
	file://0006-v4l2-setup-strides-and-offsets-for-all-planes.patch \
	file://0007-v4l2-fix-build-with-recent-kernels-the-v4l2_buffer-i.patch \
	file://0008-gstv4l2bufferpool-remove-unused-includes.patch \
	file://0009-gstv4l2bufferpool-don-t-modify-buffers-that-are-only.patch \
	file://0010-v4l2-don-t-stop-streaming-if-set_caps-doesn-t-change.patch \
	file://0011-v4l2-handle-return-value-ENOTTY-for-unimplemented-io.patch \
	file://0012-v4l2-add-support-for-mpeg4-H.263-and-H.264.patch \
	file://0013-v4l2src-set-min-buffers-to-3-if-nothing-is-defined.patch \
	file://0014-v4l2src-set-max-buffers-for-the-pool.patch \
	file://0015-Add-GOP-size-property.patch \
	file://0016-add-jpeg-quality-property.patch \
	file://0017-cache-CIDs-until-the-device-is-opened.patch \
	file://0018-reset-buffer-size-when-done.patch \
	file://0019-fix-copying-of-encoded-buffers-variable-length-must-.patch \
	file://0020-default-to-progressive-mode-if-interlace-mode-is-not.patch \
	file://0021-v4l2object-add-more-properties.patch \
	file://0022-v4l2-also-poll-for-OUTPUT-devices.patch \
	file://0023-gst-plugins-good11-handle-ENODATA-return-value-for-V.patch \
	file://0024-Let-v4l2sink-use-V4L2_BUF_TYPE_VIDEO_OUTPUT_OVERLAY.patch \
	file://0025-v4l2_calls-add-gst_v4l2_clone.patch \
	file://0026-gstv4l2object-add-gst_v4l2_object_start_shared.patch \
	file://0027-v4l2-handle-propose_allocation-in-gstv4l2object.patch \
	file://0028-gstv4l2bufferpool-alloc-up-to-max_buffers-when-runni.patch \
	file://0029-gstv4l2filter-first-steps.patch \
	file://0030-v4l2-register-v4l2filter.patch \
	file://0031-v4l2-initial-sink-USERPTR-support.patch \
	file://0032-gstv4l2bufferpool-make-number-of-queued-output-buffe.patch \
	file://0033-fill-out-v4l2_buffer.bytesused-field.patch \
	file://0034-wait-for-mmaped-buffers-when-needed.patch \
	file://0035-v4l2filter-try-to-keep-size-when-fixating.patch \
	file://0036-v4l2filter-sort-timing-data-by-pts.patch \
	file://0037-rtpjpegpay-skip-all-JPEG-Application-Markers.patch \
	file://0038-HACK-Fix-v4l2src-failure-when-streaming-h.264-from-U.patch \
	file://0039-v4l2filter-add-support-for-V4L2_EVENT_EOS.patch \
	file://0040-add-flip-vertical-and-horizontal-options-on-gstreame.patch \
        file://0041-v4l2_calls-remove-some-defines-which-removed-from-ke.patch \
"

PR = "${INC_PR}.11"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"
PACKAGECONFIG[pulseaudio] = "--enable-pulse,--disable-pulse,pulseaudio"

DEPENDS += "gst-plugins-base gconf cairo jpeg libpng zlib libid3tag flac \
            speex libsoup-2.4"

inherit gettext gconf

# SRC_URI += "file://0001-v4l2-fix-build-with-recent-kernels-the-v4l2_buffer-i.patch"

EXTRA_OECONF += "--disable-aalib --disable-esd --disable-shout2 --disable-libcaca --disable-hal --without-check \
                 --disable-orc --disable-examples --disable-taglib"

# this doesn't solve my problem with acpaths - i need to create a empty directory
# acpaths = "-I ${S}/m4"
do_configure_prepend () {
    # This m4 file contains nastiness which conflicts with libtool 2.2.2
    rm ${S}/m4/lib-link.m4 || true
}


# do_configure_prepend() {
# 	# This m4 file contains nastiness which conflicts with libtool 2.2.2
# 	rm ${S}/m4/lib-link.m4 || true
# }

SRC_URI[md5sum] = "56a8f48b11478ea75b1d9e5323e156f4"
SRC_URI[sha256sum] = "fc8e2d83c012e12600e6f8c5d55e9de9507ba8280082dfa6b5ee785fa69f1ace"

FILES_${PN}-gconfelements += "${sysconfdir}/gconf/schemas/gstreamer-0.10.schemas"
