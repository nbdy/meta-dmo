#! /bin/sh -e

[ -e /usr/bin/startxfce4 ] || exit 0

. /etc/default/xfce4

disable_dpms () {
    sleep 1
    xset -display :0 s off -dpms
}

case "$1" in
	start)
		echo -n "Starting XFCE4"
		killall X >/dev/null 2&>1
		sleep 1
		start-stop-daemon -S -x /usr/bin/startxfce4 >/dev/null 2&>1  &
                disable_dpms
		echo "."
		;;
	stop)
		echo -n "Stopping XFCE4"
		start-stop-daemon -K --oknodo -x /usr/bin/startxfce4 >/dev/null 2&>1  &
		echo "."
		;;
	restart)
		echo -n "Restarting XFCE4"
		killall X >/dev/null 2&>1
		sleep 1
		start-stop-daemon -S -x /usr/bin/startxfce4 >/dev/null 2&>1  &
                disable_dpms
		echo "."
		;;
	*)
		;;
esac

exit 0

