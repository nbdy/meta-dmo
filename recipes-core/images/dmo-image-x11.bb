DESCRIPTION = "A small image just capable of allowing a device to boot."
HOMEPAGE="https://emb.data-modul.com"

#OVERRIDES.=":x11"
require dmo-image.inc

# hardware independent
IMAGE_INSTALL_append = " \
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
