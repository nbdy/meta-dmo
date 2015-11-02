#@TYPE: Machine
#@NAME: i.MX6Q edm-qmx6
#@DESCRIPTION: Machine configuration for Data-Modul eDM-QMX6

require conf/machine/include/imx-base.inc
require conf/machine/include/tune-cortexa9.inc

# build this packages, but do not include in image
EXTRA_IMAGEDEPENDS = "virtual/bootloader virtual/kernel"

# Which type should be the image?
IMAGE_FSTYPES = "tar.bz2 ext4 dmosdcard"
IMAGE_TYPEDEP_dmosdcard = "ext4"

INITRAMFS_IMAGE = "dmo-image-initramfs"

# barebox configurations
IMAGE_BOOTLOADER = "barebox"
BAREBOX_MACHINE = "datamodul-edm-qmx6_defconfig"
PREFERRED_PROVIDER_virtual/bootloader = "barebox"

# kernel configuration
KERNEL_IMAGETYPE = "zImage"
PREFERRED_PROVIDER_virtual/kernel = "linux-dmo"

SERIAL_CONSOLE = "115200 ttymxc1"

MACHINE_FEATURES += " pci"

SDCARD_WITH_SEPARATED_BARBOXENV = "false"

DMOFTP_append = "/edm-qmx6/source/"
