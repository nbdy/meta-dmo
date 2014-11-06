DESCRIPTION = "A small image just capable of allowing a device to boot."
HOMEPAGE="https://emb.data-modul.com"

require dmo-image.inc

# hardware independent
IMAGE_INSTALL_append = " \
    bash \
    chromium \
    canutils \
    cinematicexperience \
    dmo-gst-videoscripts \
    gstreamer1.0 \
    gstreamer1.0-plugins-good-meta \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-bad-meta \
    i2c-tools \
    icu \
    kmod \
    kmsfbwrap \
    mtd-utils \
    mtools \
    obexd \
    openssh \
    openobex \
    packagegroup-xfce-base \
    procps \
    psplash \
    qtbase \
    qtbase-tools \
    qtbase-plugins \
    qtbase-fonts \
    slim \
    strace \
    sudo \
    unclutter \
    util-linux \
    wget \
    xdg-utils \
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
    gpu-viv-bin-mx6q \
    xf86-video-imxfb-vivante \
"

# hardware depent packages

