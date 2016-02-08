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

MACHINEOVERRIDES.=":imx6-dmo-edm-qmx"

# The image source can be set as url path or file.
# If the splash image is defined with file, it needs to be placed in dhe dietsplash
# folder.
SPLASH_SCREEN_IMAGE ?= "file://Logo_DM.jpg"

SPLASH_IMAGE_MD5SUM ?= "8b560a679af07bbb7b392ff3943a7948"
SPLASH_IMAGE_SHA256SUM ?= "3eaca5d3755791d276bc4aacad9aa76a272d8d07b34d8fbad8b02889f0bfb562"

# It seems, that the logo size is capped somwhere around WXGA (1280x800) or to
# be a bit more prezice, the byte size.
# The convert tool rssizes the image to match either the height or the width
# depending on the source image (portrait/landscapea)
# To force the given image, add an ! to the dimension. e.g. 800x480!
SPLASH_SCREEN_DIMENSION ?= "800x480"

