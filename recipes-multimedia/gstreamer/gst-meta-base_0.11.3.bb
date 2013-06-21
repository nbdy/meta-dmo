DESCRIPTION = "Gstreamer package groups"
LICENSE = "MIT"
DEPENDS = "gstreamer gst-plugins-base gst-plugins-good gst-plugins-bad"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


PR = "r15"

PACKAGES = "\
    gst-meta-base \
    gst-meta-x11-base \
    gst-meta-audio \
    gst-meta-debug \
    gst-meta-video \
    gst-meta-unsorted"

ALLOW_EMPTY_gst-meta-base = "1"
ALLOW_EMPTY_gst-meta-x11-base = "1"
ALLOW_EMPTY_gst-meta-audio = "1"
ALLOW_EMPTY_gst-meta-debug = "1"
ALLOW_EMPTY_gst-meta-video = "1"
ALLOW_EMPTY_gst-meta-unsorted = "1"

RDEPENDS_gst-meta-base = "\
    ${@base_contains('DISTRO_FEATURES', 'x11', 'gst-meta-x11-base', '', d)} \
    gstreamer \
    gst-plugins-base-app \
    gst-plugins-base-gio \
    gst-plugins-base-encodebin \
    gst-plugins-base-tcp \
    gst-plugins-base-adder \
    gst-plugins-base-alsa \
    gst-plugins-base-volume \
    gst-plugins-base-audioconvert \
    gst-plugins-base-audioresample \
    gst-plugins-base-audiorate \
    gst-plugins-base-audioconvert \
    gst-plugins-base-typefindfunctions \
    gst-plugins-base-videorate \
    gst-plugins-base-videoconvert \
    gst-plugins-base-videoscale \
    gst-plugins-base-playback \
    gst-plugins-good-autodetect \
    gst-plugins-good-souphttpsrc"

RDEPENDS_gst-meta-unsorted = "\
    gst-plugins-good-wavparse \
    gst-plugins-good-wavenc \
    gst-plugins-good-videomixer \
    gst-plugins-good-videofilter \
    gst-plugins-good-videocrop \
    gst-plugins-good-video4linux2 \
    gst-plugins-good-udp \
    gst-plugins-good-speex \
    gst-plugins-good-spectrum \
    gst-plugins-good-souphttpsrc \
    gst-plugins-good-shapewipe \
    gst-plugins-good-rtsp \
    gst-plugins-good-rtpmanager \
    gst-plugins-good-replaygain \
    gst-plugins-good-png \
    gst-plugins-good-navigationtest \
    gst-plugins-good-multipart \
    gst-plugins-good-multifile \
    gst-plugins-good-mulaw \
    gst-plugins-good-matroska \
    gst-plugins-good-level \
    gst-plugins-good-jpeg \
    gst-plugins-good-isomp4 \
    gst-plugins-good-imagefreeze \
    gst-plugins-good-id3demux \
    gst-plugins-good-icydemux \
    gst-plugins-good-goom2k1 \
    gst-plugins-good-goom \
    gst-plugins-good-flv \
    gst-plugins-good-flac \
    gst-plugins-good-equalizer \
    gst-plugins-good-effectv \
    gst-plugins-good-cutter \
    gst-plugins-good-avi \
    gst-plugins-good-autodetect \
    gst-plugins-good-auparse \
    gst-plugins-good-audioparsers \
    gst-plugins-good-audiofx \
    gst-plugins-good-apetag \
    gst-plugins-good-annodex \
    gst-plugins-good-alphacolor \
    gst-plugins-good-alpha \
    gst-plugins-good-alaw \
    gst-plugins-bad-adpcmdec \
    gst-plugins-bad-adpcmenc \
    gst-plugins-bad-audiovisualizers \
    gst-plugins-bad-autoconvert \
    gst-plugins-bad-bayer \
    gst-plugins-bad-bz2 \
    gst-plugins-bad-camerabin2 \
    gst-plugins-bad-dataurisrc \
    gst-plugins-bad-debugutilsbad \
    gst-plugins-bad-dtmf \
    gst-plugins-bad-dvbsuboverlay \
    gst-plugins-bad-dvdspu \
    gst-plugins-bad-mpegdemux \
    gst-plugins-bad-mpegtsdemux \
    gst-plugins-bad-pcapparse \
    gst-plugins-bad-rawparse \
    gst-plugins-bad-removesilence \
    gst-plugins-bad-rtpmux \
    gst-plugins-bad-rtpvp8 \
    gst-plugins-bad-scaletempoplugin \
    gst-plugins-bad-sdpelem \
    gst-plugins-bad-segmentclip \
    gst-plugins-bad-shm \
    gst-plugins-bad-smooth \
    gst-plugins-bad-videoparsersbad \
    gst-plugins-bad-y4mdec \
    "


RRECOMMENDS_gst-meta-x11-base = "\
    gst-plugins-base-ximagesink \
    gst-plugins-base-xvimagesink"

RDEPENDS_gst-meta-audio = "\
    gst-meta-base \
    gst-plugins-base-vorbis \
    gst-plugins-base-ivorbisdec \
    gst-plugins-base-ogg \
    gst-plugins-good-wavparse \
    gst-plugins-good-flac \
    ${COMMERCIAL_AUDIO_PLUGINS}"


RDEPENDS_gst-meta-debug = "\
    gst-meta-base \
    gst-plugins-good-debug \
    gst-plugins-base-audiotestsrc \
    gst-plugins-base-videotestsrc"


RDEPENDS_gst-meta-video = "\
    gst-meta-base \
    gst-plugins-good-avi \
    gst-plugins-good-matroska \
    gst-plugins-base-theora \
    gst-plugins-base-subparse \
    gst-plugins-good-y4menc \
    gst-plugins-good-ximagesrc \
    ${COMMERCIAL_VIDEO_PLUGINS}"

RRECOMMENDS_gst-meta-video = "\
    gst-meta-audio"
