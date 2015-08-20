import QtQuick 2.3
import QtQuick.Dialogs 1.2
import QtMultimedia 5.0

Item {
    id: root
    width: 800
    height: 600

    property int inputHeight: 30

    property variant playlist: []
    property bool usePlaylist: false
    property int currentIndex: 0

    function readPlaylistFile() {
        var file = new XMLHttpRequest();
        file.open("GET", textinput.text);
        file.onreadystatechange = function() {
            if(file.readyState === XMLHttpRequest.DONE) {
                playlist = file.responseText.split("\n");
                usePlaylist = true;
                if(playlist.length > 0) {
                    video.source = "file://" + playlist[currentIndex];
                    video.play();
                }
            }
        }
        file.send(null);
    }

    Rectangle {
        width: root.width
        height: root.height - inputHeight
        color: "red"

        Rectangle {
            x: 19
            y: 9
            width: parent.width - 38
            height: parent.height - 18
            color: "transparent"
            border.color: "white"
            border.width: 1

            Video {
                id: video
                x: 1
                y: 1
                width: parent.width - 2
                height: parent.height - 2
                autoPlay: false
                fillMode: VideoOutput.Stretch
                onStopped: {
                    visible = false;
                    if(usePlaylist) {
                        currentIndex = (currentIndex + 1) % playlist.length;
                        while(playlist[currentIndex] === "")
                            currentIndex = (currentIndex + 1) % playlist.length;
                        source = "file://" + playlist[currentIndex];
                        play();
                    }
                }
                onPlaying: visible = true;
            }
        }
    }

    Rectangle {
        y: root.height - inputHeight
        width: parent.width
        height: inputHeight
        color: "black"

        TextInput {
            id: textinput
            height: parent.height
            width: parent.width * 0.6
            text: "file:///home/ps/Videos/BUCHERER.VOB"
            font.pointSize: parent.height * 0.6
            color: "white"
            MouseArea {
                anchors.fill: parent
                onClicked: fileDialog.open();
            }
        }
        
        FileDialog {
            id: fileDialog
            title: "Choose a video file or playlist"
            selectMultiple: false
            selectFolder: false
            onAccepted: textinput.text = fileDialog.fileUrl
        }

        Text {
            id: playbutton
            x: textinput.width
            width: (parent.width - x) / 3
            height: parent.height
            text: "Play"
            font.pointSize: parent.height * 0.6
            color: "white"

            MouseArea {
                anchors.fill: parent
                onClicked:  {
                    if(textinput.text.indexOf(".txt") > 0 && !usePlaylist)
                        readPlaylistFile();
                    else if(usePlaylist)
                        video.play()
                    else {
                        video.source = textinput.text;
                        video.play()
                    }
                }
            }
        }
        Text {
            id: pausebutton
            x: playbutton.x + playbutton.width
            width: playbutton.width
            height: parent.height
            text: "Pause"
            font.pointSize: parent.height * 0.6
            color: "white"

            MouseArea {
                anchors.fill: parent
                onClicked:  {
                    video.pause()
                }
            }
        }
        Text {
            id: stopbutton
            x: pausebutton.x + pausebutton.width
            width: playbutton.width
            height: parent.height
            text: "Stop"
            font.pointSize: parent.height * 0.6
            color: "white"

            MouseArea {
                anchors.fill: parent
                onClicked:  {
                    usePlaylist = false;
                    currentIndex = 0;
                    video.stop()
                }
            }
        }
        Text {
            id: closebutton
            x: root.width - width
            width: 30
            height: parent.height
            text: "X"
            font.pointSize: parent.height * 0.6
            color: "white"

            MouseArea {
                anchors.fill: parent
                onClicked: Qt.quit()
            }
        }
    }
}
