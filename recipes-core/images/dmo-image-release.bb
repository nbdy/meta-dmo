DESCRIPTION = "A small image just capable of allowing a device to boot."

require dmo-image.inc

PR = "${INC_PR}.20131217.1"

# hardware independent
IMAGE_INSTALL_append = " \
    bash \
    chromium \
    canutils \
    dmo-gst-videoscripts \
    gstreamer \
    i2c-tools \
    icu \
    kmod \
    mtools \
    obexd \
    openssh \
    openobex \
    packagegroup-xfce-base \
    procps \
    psplash \
    strace \
    sudo \
    unclutter \
    util-linux \
    wget \
    xf86-input-evdev \
    xf86-video-fbdev \
    xhost \
    xinput-calibrator \
    xmodmap \
    xrdb \
    xserver-xorg \
    xserver-xorg-extension-dbe \
    xserver-xorg-extension-extmod \
    xset \
"
IMAGE_INSTALL_append_mx6 += " \
    cpu-status-info \
    gpu-viv-bin-mx6q \
    xf86-video-imxfb-vivante \
"

# hardware depent packages

