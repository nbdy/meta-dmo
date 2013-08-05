DESCRIPTION = "A small image just capable of allowing a device to boot."

require dmo-image.inc

PR = "${INC_PR}.20130710.1"

IMAGE_INSTALL += " \
    bash \
    chromium \
    cpu-status-info \
    canutils \
    dmo-gst-videoscripts \
    gpu-viv-bin-mx6q \
    gstreamer \
    gst-meta-base \
    gst-meta-x11-base \
    gst-meta-audio \
    gst-meta-debug \
    gst-meta-video \
    gst-meta-unsorted \
    i2c-tools \
    icu \
    kmod \
    mesa-driver-swrast \
    mtools \
    obexd \
    openssh \
    openobex \
    packagegroup-xfce-base \
    procps \
    strace \
    slim \
    sudo \
    unclutter \
    util-linux \
    wget \
    xf86-dri-vivante \
    xf86-input-evdev \
    xf86-video-fbdev \
    xmodmap \
    xrdb \
    xserver-xorg \
    xserver-xorg-extension-dbe \
    xserver-xorg-extension-extmod \
    xset \
"

