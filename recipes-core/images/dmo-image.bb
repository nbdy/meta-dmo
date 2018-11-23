require dmo-image.inc

SPLASH_SW = "${@bb.utils.contains('SPLASH_SCREEN_TYPE', 'dietsplash', 'dietsplash', '', d)}"

# hardware independent packages
IMAGE_INSTALL = " \
    bash \
    blanking \
    bluez-hcidump \
    bluez5 \
    bluez5-testtools \
    bluez5-noinst-tools \
    bluez5-obex \
    gstreamer1.0-plugins-bad-bluez \
    openobex \
    obexftp \
    obexd \
    canutils \
    coreutils \
    dmo-gst-videoscripts \
    dhcp-client \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    ethtool \
    fb-test \
    fbida \
    gstreamer1.0 \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    hdparm \
    i2c-tools \
    icu \
    iperf3 \
    iproute2 \
    iputils \
    iw \
    kernel-modules \
    kmod \
    liberation-fonts \
    linux-firmware \
    mtd-utils \
    mtools \
    minicom \
    nfs-utils \
    nfs-utils-client \
    obexd \
    openobex \
    openssh \
    packagegroup-core-full-cmdline \
    pciutils \
    powertop \
    procps \
    root-user-homedir \
    smartmontools \
    systemd-vconsole-setup \
    strace \
    smartmontools \
    sudo \
    tslib-conf \
    tslib-calibrate \
    tslib-tests \
    udev-extraconf \
    usbutils \
    util-linux \
    wget \
    wireless-tools \
    wpa-supplicant \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    ${SPLASH_SW} \
"
IMAGE_INSTALL_append_dmo-x11 = " \
    alsa-utils \
    mesa-demos \
    packagegroup-xfce-base \
    unclutter \
    xdg-utils \
    xf86-input-evdev \
    xf86-video-fbdev \
    xhost \
    xinput-calibrator \
    xmodmap \
    xrdb \
    xserver-nodm-init \
    xserver-xorg \
    xserver-xorg-extension-dbe \
    xserver-xorg-extension-extmod \
    xset \
"
IMAGE_INSTALL_append_dmo-qt = " \
    alsa-utils \
    cinematicexperience \
    qupzilla \
    qt3d \
    qtbase \
    qtbase-examples \
    qtbase-mkspecs \
    qtbase-plugins \
    qtbase-tools \
    qtdeclarative \
    qtdeclarative-examples \
    qtdeclarative-mkspecs \
    qtdeclarative-plugins \
    qtdeclarative-qmlplugins \
    qtdeclarative-tools \
    qtmultimedia \
    qtmultimedia-examples \
    qtmultimedia-mkspecs \
    qtmultimedia-plugins \
    qtmultimedia-qmlplugins \
    qtquickcontrols2 \
    qtquickcontrols2-dev \
    qtquickcontrols2-mkspecs \
    qtquickcontrols2-qmldesigner \
    qtquickcontrols2-qmlplugins \
"
# Hardware depend packages
IMAGE_INSTALL_append_mx6q = " \
    firmware-imx-vpu-imx6q \
"
IMAGE_INSTALL_append_mx6dl = " \
    firmware-imx-vpu-imx6d \
"
IMAGE_INSTALL_append_mx6 = " \
    imx-gpu-viv \
    imx-gpu-viv-demos \
"
IMAGE_INSTALL_append_mx6_dmo-x11 = " \
    xf86-video-imxfb-vivante \
"
IMAGE_INSTALL_append_dmo-edm-comb_dmo-x11 = " \
    alsa-utils \
    gstreamer-vaapi-1.0 \
    intel-microcode \
    libva-intel-driver \
    mesa-megadriver \
    xf86-video-intel \
    xserver-xorg-extension-glx \
"
IMAGE_INSTALL_append_dmo-edm-comb = " \
    dmec-driver \
    eapi-lib \
    eapi-app \
    eapi-gui \
"

