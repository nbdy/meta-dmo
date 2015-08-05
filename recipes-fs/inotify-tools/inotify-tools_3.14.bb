# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

inherit autotools

DESCRIPTION = "a set of command-line programs providing a simple interface to inotify"
HOMEPAGE = "https://github.com/rvoicilas/inotify-tools/wiki"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=ac6c26e52aea428ee7f56dc2c56424c6"
SECTION = "fs tools"

FILESEXTRAPATH_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \ 
            http://github.com/downloads/rvoicilas/${PN}/${PN}-${PV}.tar.gz \
            file://0001-Fix-include-path-for-inotifytools.patch \
          "

SRC_URI[md5sum] = "b43d95a0fa8c45f8bab3aec9672cf30c"
SRC_URI[sha256sum] = "222bcca8893d7bf8a1ce207fb39ceead5233b5015623d099392e95197676c92f"

