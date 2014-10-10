DESCRIPTION = "A small image just capable of allowing a device to boot."

require dmo-image.inc

PR = "${INC_PR}.20131217.1"

# hardware independent
IMAGE_INSTALL_append = " \
    bash \
    chromium \
    canutils \
    cinematicexperience \
    dmo-gst-videoscripts \
    dmo-base-user \
    gstreamer \
    i2c-tools \
    icu \
    kmod \
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

