#!/bin/sh
# remove beep
if [ -x $HOME/.xinitrc ]; then
    exec $HOME/.xinitrc
else
   echo $HOME
   /usr/bin/setterm -blength 0 &
   /usr/bin/xset b off &

   # remove screenblanking and energystar options
   /usr/bin/xset -display :0 s off -dpms &

   xfconf-query --channel=xfwm4 --property=/general/use_compositing --set=false &
   exec /usr/bin/startxfce4
fi
