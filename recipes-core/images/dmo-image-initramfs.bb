# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>

DESCRIPTION = "Initramfs"
HOMEPAGE = "https://emb.data-modul.com"
LICENSE = "GPLv2"

PACKAGE_INSTALL = "\
                    base-passwd \
                    busybox \
                    dmo-initscript \
                    e2fsprogs \
                    inotify-tools \
                    strace \
                    udev \
                    util-linux-blkid \
                    util-linux-mount \
                    ${ROOTFS_BOOTSTRAP_INSTALL}"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "dmo-image-initramfs"
IMAGE_LINGUAS = ""

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"

BAD_RECOMMENDATIONS += "busybox-syslog"

