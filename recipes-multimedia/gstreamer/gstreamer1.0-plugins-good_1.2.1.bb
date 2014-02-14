include gstreamer1.0-plugins-good.inc

SRC_URI += " \
    file://0001-v4l2-create-dmabuf-buffers-for-GST_V4L2_IO_MMAP-if-p.patch \
    file://0002-v4l2-initial-sink-USERPTR-support.patch \
    file://0003-gstv4l2bufferpool-make-number-of-queued-output-buffe.patch \
    file://0004-wait-for-mmaped-buffers-when-needed.patch \
    file://0005-v4l2-support-min-queued-0-again.patch \
    file://0101-v4l2-better-names-for-the-bufferpools.patch \
    file://0102-v4l2-don-t-require-a-framerate-if-the-v4l2-device-do.patch \
    file://0103-v4l2-set-GST_BUFFER_FLAG_DELTA_UNIT-when-appropriate.patch \
    file://0104-v4l2-pass-buffer-timestamps-for-mem2mem-devices.patch \
    file://0105-v4l2-add-support-for-cropping-meta.patch \
    file://0106-v4l2-object-clear-probed-caps-on-close.patch \
    file://0107-v4l2bufferpool-take-over-the-floating-reference-for-.patch \
    file://0201-v4l2-handle-propose_allocation-in-gstv4l2object.patch \
    file://0202-v4l2-v4l2_calls-add-gst_v4l2_clone.patch \
    file://0203-v4l2-add-gst_v4l2_object_start_shared.patch \
    file://0204-v4l2-add-filter-element.patch \
    file://0205-v4l2-filter-sort-timing-data-by-pts-and-forward-seri.patch \
    file://0206-v4l2-filter-send-V4L2_DEC_CMD_STOP-on-EOS.patch \
    file://0207-v4l2-filter-reorder-device-configuration-and-activat.patch \
    file://0208-v4l2-filter-ignore-pixel-aspect-ratio.patch \
    file://0209-v4l2-filter-fix-shutdown-handling.patch \
    file://0210-v4l2-filter-implement-latency-QOS-handling.patch \
    file://0211-v4l2-filter-rework-event-queue-handling.patch \
    file://0212-v4l2-filter-implement-GstForceKeyUnit-handling.patch \
    file://0213-v4l2-filter-sort-caps-event-after-stream-start-event.patch \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

SRC_URI[md5sum] = "082156e9fb737ee6132b6b663f2901a1"
SRC_URI[sha256sum] = "660fa02dbe01086fcf702d87acc0ba5dde2559d6a11ecf438874afe504c50517"
S = "${WORKDIR}/gst-plugins-good-${PV}"

