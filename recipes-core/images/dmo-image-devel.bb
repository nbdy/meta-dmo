DESCRIPTION = "A small image just capable of allowing a device to boot."

require dmo-image.inc

PR = "${INC_PR}.20131217.1"

# hardware independent
IMAGE_INSTALL_append = " \
    bash \
    binutils \
    gcc \
    icecc \
    openssh \
    sudo \
    util-linux \
    wget \
    xf86-input-evdev \
    xf86-video-fbdev \
    xhost \
    xinput-calibrator \
    xrdb \
    xset \
"

# hardware depent packages

