#@TYPE: Machine
#@NAME: i.MX6Q edm-qmx6
#@DESCRIPTION: Machine configuration for PPC Board

require conf/machine/include/imx-base.inc
require conf/machine/include/tune-cortexa9.inc

MACHINEOVERRIDES =. "mx6:imx6-dmo-edm-qmx:"

# build this packages, but do not include in image
EXTRA_IMAGEDEPENDS = "virtual/bootloader virtual/kernel"

# Which type should be the image?
IMAGE_CLASSES += "dmo-images"
IMAGE_FSTYPES = "tar.bz2 ext4 dmosdcard"
IMAGE_TYPEDEP_dmosdcard = "ext4"

INITRAMFS_IMAGE = "dmo-image-initramfs"

# barebox configurations
IMAGE_BOOTLOADER = "barebox-ppc"
BAREBOX_MACHINE = "dmo-imx6-ppc_defconfig"
BAREBOX_BINARY = "images/barebox-dmo-ppc.img"
PREFERRED_PROVIDER_virtual/bootloader = "barebox-ppc"

# kernel configuration
KERNEL_IMAGETYPE = "zImage"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-dmo-ppc"

PREFERRED_VERSION_linux-dmo ?= "4.9.99%"

SERIAL_CONSOLE = "115200 ttymxc0"

MACHINE_FEATURES += " pci"

SDCARD_WITH_SEPARATED_BARBOXENV = "false"

DMOFTP_append = "/edm-qmx6/source/"

require conf/machine/splash-screen.inc

PREFERRED_VERSION_gstreamer1.0 ?= "1.8.3"
PREFERRED_VERSION_gstreamer1.0-meta-base ?= "1.8.3"
PREFERRED_VERSION_gstreamer1.0-plugins-bad ?= "1.8.3"
PREFERRED_VERSION_gstreamer1.0-plugins-base ?= "1.8.3"
PREFERRED_VERSION_gstreamer1.0-plugins-good ?= "1.8.3"
PREFERRED_VERSION_gstreamer1.0-plugins-ugly ?= "1.8.3"
