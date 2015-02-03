DESCRIPTION = "A small image just capable of allowing a device to boot."
HOMEPAGE="https://emb.data-modul.com"

require dmo-image.inc

# hardware independent
IMAGE_INSTALL_append_dmo-x11 = " \
    chromium \
    packagegroup-xfce-base \
    slim \
    unclutter \
    xdg-utils \
    xf86-input-evdev \
    xf86-video-fbdev \
    xf86-video-imxfb-vivante \
    xhost \
    xinput-calibrator \
    xmodmap \
    xrdb \
    xserver-xorg \
    xserver-xorg-extension-dbe \
    xserver-xorg-extension-extmod \
    xset \
"
IMAGE_INSTALL_append_dmo-qt = " \
    qtbase \
    qtbase-examples \
    qtbase-fonts \
    qtbase-fonts-pfa \
    qtbase-fonts-pfb \
    qtbase-fonts-qpf \
    qtbase-fonts-ttf-dejavu \
    qtbase-fonts-ttf-vera \
    qtbase-mkspecs \
    qtbase-plugins \
    qtbase-tools \
    qtdeclarative \
    qtdeclarative-examples \
    qtdeclarative-mkspecs \
    qtdeclarative-plugins \
    qtdeclarative-qmlplugins \
    qtdeclarative-tools \
"
