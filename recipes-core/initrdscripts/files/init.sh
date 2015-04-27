#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin

init_setup() {
	mkdir -p /proc
	mkdir -p /sys

	echo "Mount proc, sys, dev"
	mount -t proc proc /proc
	mount -t sysfs sysfs /sys
	mount -t devtmpfs none /dev

	mkdir -p /run
	mkdir -p /var/run
}

mount_root() {
	echo "Mount root, and move"
	mkdir -p /rootfs
	mkdir -p /rootfs.ro
	mkdir -p /rootfs.rw
	mount -o ro /dev/mmcblk0p2 /rootfs.ro/
	mount /dev/mmcblk0p4 /rootfs.rw/

	mount -t overlay overlay -olowerdir=/rootfs.ro,upperdir=/rootfs.rw/datadir,workdir=/rootfs.rw/workdir /rootfs/
	mkdir -p /rootfs/rootfs.ro /rootfs/rootfs.rw
	mount --move /rootfs.ro /rootfs/rootfs.ro
	mount --move /rootfs.rw /rootfs/rootfs.rw

	mount -n --move /proc /rootfs/proc
	mount -n --move /sys /rootfs/sys
	mount -n --move /dev /rootfs/dev
}

parse_cmd() {
	echo "Parse cmd"
	[ -z "$CMDLINE" ] && CMDLINE=`cat /proc/cmdline`
	for arg in $CMDLINE; do
		optarg=`expr "x$arg" : 'x[^=]*=\(.*\)'`
		case $arg in
			debugshell*)
				shell='1'
		esac
	done
}

init_setup
[ -z "$CONSOLE" ] && CONSOLE="/dev/console"
parse_cmd
mount_root

echo "Check for shell"
if [ ! -n "$shell" ]; then
	echo "Go init"
	cd /rootfs
	exec switch_root -c /dev/console /rootfs/ /sbin/init
else
	echo "Execute shell"
	exec sh
fi

