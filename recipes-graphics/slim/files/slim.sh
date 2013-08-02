#! /bin/sh -e

[ -e /usr/bin/slim ] || exit 0

case "$1" in
	start)
		echo -n "Starting SLIM"
		start-stop-daemon -S -x /usr/bin/slim &
		sleep 1
		echo "."
		;;
	stop)
		echo -n "Stopping SLIM"
		start-stop-daemon -K -x /usr/bin/slim
		sleep 1
		# start-stop-daemon -K --oknodo -x /usr/bin/slim >/dev/null 2&>1  &
		echo "."
		;;
	restart)
		echo -n "Restarting SLIM"
		/etc/init.d/slim.sh stop
		/etc/init.d/slim.sh start
		echo "."
		;;
	*)
		;;
esac

exit 0

