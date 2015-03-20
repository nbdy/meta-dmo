#!/bin/bash

enable_blanking () {
	for i in $(seq 0 63)
	do
		[ -e /dev/tty${i} ] && /usr/bin/setterm -blank 1 > /dev/tty${i}
	done
}

disable_blanking () {
	for i in $(seq 0 63)
	do
		[ -e /dev/tty${i} ] && /usr/bin/setterm -blank 0 > /dev/tty${i}
	done
}

case ${1} in
	"start")
		disable_blanking
		;;

	"stop")
		enable_blanking
		;;
	"restart")
		${0} stop
		${0} start
		;;
	*)
		;;
esac
