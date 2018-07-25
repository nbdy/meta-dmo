# splash-screen.inc
# Customer logo will be configured here

# - Copy logo image to folder meta-dmo/conf/logos/
#   and set logo name in SPLASH_SCREEN_IMAGE
# - Adapt resolution by setting SPLASH_SCREEN_DIMENSION
# - Set screen type by commenting/uncommenting the respective SPLASH_SCREEN_TYPE

# The image source can be set as url path or file.
# If the splash image is defined with file, it needs to be placed in the dietsplash
# folder.
SPLASH_SCREEN_IMAGE ?= "Logo_DM_HD_bw.png"

# It seems, that the logo size is capped somwhere around WXGA (1280x800) or to
# be a bit more precise, the byte size.
# The convert tool resizes the image to match either the height or the width
# depending on the source image (portrait/landscapea)
# To force the given image, add an ! to the dimension. e.g. 800x480!
SPLASH_SCREEN_DIMENSION ?= "800x480!"

SPLASH_SCREEN_PATH = "${COREBASE}/meta-dmo/conf/logos/"

SPLASH_SCREEN_TYPE ?= "dietsplash"
# SPLASH_SCREEN_TYPE ?= "kernel"
