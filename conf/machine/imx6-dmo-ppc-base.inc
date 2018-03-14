#@TYPE: Machine
#@NAME: i.MX6Q edm-qmx6
#@DESCRIPTION: Machine configuration for PPC Board

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
IMAGE_BOOTLOADER = "barebox-ppc"
BAREBOX_MACHINE = "dmo-imx6-ppc_defconfig"
PREFERRED_PROVIDER_virtual/bootloader = "barebox-ppc"

# kernel configuration
KERNEL_IMAGETYPE = "zImage"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-dmo-ppc"

PREFERRED_VERSION_linux-dmo ?= "4.4.57%"

SERIAL_CONSOLE = "115200 ttymxc0"

MACHINE_FEATURES += " pci"

SDCARD_WITH_SEPARATED_BARBOXENV = "false"

DMOFTP_append = "/edm-qmx6/source/"

# The image source can be set as url path or file.
# If the splash image is defined with file, it needs to be placed in dhe dietsplash
# folder.
SPLASH_SCREEN_IMAGE ?= "Logo_DM_HD_bw.png"

# It seems, that the logo size is capped somwhere around WXGA (1280x800) or to
# be a bit more prezice, the byte size.
# The convert tool rssizes the image to match either the height or the width
# depending on the source image (portrait/landscapea)
# To force the given image, add an ! to the dimension. e.g. 800x480!
SPLASH_SCREEN_DIMENSION ?= "800x480!"

SPLASH_SCREEN_PATH = "${COREBASE}/meta-dmo/conf/logos/"

SPLASH_SCREEN_TYPE ?= "dietsplash"

PREFERRED_VERSION_gstreamer1.0 ?= "1.6.3"
PREFERRED_VERSION_gstreamer1.0-meta-base ?= "1.6.3"
PREFERRED_VERSION_gstreamer1.0-plugins-bad ?= "1.6.3"
PREFERRED_VERSION_gstreamer1.0-plugins-base ?= "1.6.3"
PREFERRED_VERSION_gstreamer1.0-plugins-good ?= "1.6.3"
PREFERRED_VERSION_gstreamer1.0-plugins-ugly ?= "1.6.3"
