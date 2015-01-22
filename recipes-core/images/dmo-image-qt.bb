DESCRIPTION = "A small image just capable of allowing a device to boot."
HOMEPAGE="https://emb.data-modul.com"

DISTROOVERRIDES.=":qt"

require dmo-image.inc

# hardware independent
IMAGE_INSTALL_append = " \
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
