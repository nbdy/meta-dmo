#@TYPE: Machine
#@NAME: x86 edm-comb
#@DESCRIPTION: Machine configuration for Data-Modul eDM-comb

require conf/machine/intel-corei7-64.conf
do_rootfs[depends] += "intel-microcode:do_deploy"
do_rootfs[depends] += "grub-efi:do_populate_lic"

IMAGE_BOOTLOADER = "gummiboot"
EFI_PROVIDER = "grub-efi"

IMAGE_FSTYPES += "tar.bz2 hdddirect"

MACHINEOVERRIDES .= ":dmo-edm-comb"

PACKAGECONFIG_append_pn-gstreamer1.0-plugins-bad += "opengl"

# The image source can be set as url path or file.
# If the splash image is defined with file, it needs to be placed in dhe dietsplash
# folder.
SPLASH_SCREEN_IMAGE ?= "Logo_DM_HD_bw.png"

# It seems, that the logo size is capped somwhere around WXGA (1280x800) or to
# be a bit more prezice, the byte size.
# The convert tool rssizes the image to match either the height or the width
# depending on the source image (portrait/landscapea)
# To force the given image, add an ! to the dimension. e.g. 800x480!
SPLASH_SCREEN_DIMENSION ?= "1920x1080"

SPLASH_SCREEN_PATH = "${COREBASE}/meta-dmo/conf/logos/"

SPLASH_SCREEN_TYPE ?= "kernel"

