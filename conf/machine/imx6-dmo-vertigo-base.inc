#@TYPE: Machine
#@NAME: i.MX6Q edm-qmx6
#@DESCRIPTION: Machine configuration for Data Modul Vetrigo Board

require conf/machine/include/imx-base.inc
require conf/machine/include/tune-cortexa9.inc

MACHINEOVERRIDES =. "mx6:mx6dl:mx6q:imx6-dmo-edm-qmx:"

# build this packages, but do not include in image
EXTRA_IMAGEDEPENDS = "virtual/bootloader virtual/kernel"

# Which type should be the image?
IMAGE_CLASSES += "dmo-images"
IMAGE_FSTYPES = "tar.bz2 ext4 dmosdcard"
IMAGE_TYPEDEP_dmosdcard = "ext4"

INITRAMFS_IMAGE = "dmo-image-initramfs"

# barebox configurations
IMAGE_BOOTLOADER = "barebox-vertigo"
BAREBOX_MACHINE = "dmo-vertigo_defconfig"
PREFERRED_PROVIDER_virtual/bootloader = "barebox-vertigo"

# kernel configuration
KERNEL_IMAGETYPE = "zImage"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-dmo-vertigo"

PREFERRED_VERSION_linux-dmo ?= "4.4.57%"

SERIAL_CONSOLE = "115200 ttymxc0"

MACHINE_FEATURES += " pci"

SDCARD_WITH_SEPARATED_BARBOXENV = "false"

DMOFTP_append = "/edm-qmx6/source/"

require conf/machine/splash-screen.inc

