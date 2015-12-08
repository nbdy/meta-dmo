#!/bin/sh
# remove beep
/usr/bin/setterm -blength 0 &
/usr/bin/xset b off &

# remove screenblanking and energystar options
/usr/bin/xset -display :0 s off -dpms &

xfconf-query --channel=xfwm4 --property=/general/use_compositing --set=false &

