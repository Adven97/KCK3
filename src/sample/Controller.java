package sample;

import com.sun.media.sound.JavaSoundAudioClip;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.applet.AppletAudioClip;

import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller  {

   // String songName;

    @FXML
    Slider volume;

    @FXML
    RadioButton rU,rD,rS,rK;

    MediaPlayer mp;
    boolean isOn=false;
    boolean initialized = true;

    ToggleGroup group1 = new ToggleGroup();
    ToggleGroup group2 = new ToggleGroup();

    @FXML
    void initRadioButtons(){
        rU.setToggleGroup(group1);
        rD.setToggleGroup(group1);
        rS.setToggleGroup(group2);
        rK.setToggleGroup(group2);

        if(!initialized) {

            if (rD.isSelected() == true && rS.isSelected() == true) {
                // stopSong("ledzep.mp3");
                playSong("house.mp3");

            }
            else if (rU.isSelected() == true && rK.isSelected() == true) {
                // stopSong("ledzep.mp3");
                playSong("ledzep.mp3");

            }
            else if (rU.isSelected() == true && rS.isSelected() == true) {
                // stopSong("ledzep.mp3");
                playSong("paranoid.mp3");

            }
            else {
                // stopSong("ledzep.mp3");
                playSong("africa.mp3");

            }
        }
    }

    @FXML
    private void startPlaying(){
        initRadioButtons();
          rD.setSelected(true);
          rK.setSelected(true);
        playSong("africa.mp3");

        initialized=false;



    }


   // @FXML
    private void playSong(String songName) {
        initRadioButtons();
       if(!isOn ) {
           mp = new MediaPlayer(new Media(new File(songName).toURI().toString()));
          // System.out.println((new Media(new File("blusky.wav").getPath().toString())));

           mp.play();
           isOn=true;
       }

        glosnij();

    }
    private void stopSong(String songName) {

            mp = new MediaPlayer(new Media(new File(songName).toURI().toString()));
            mp.stop();
    }


    public void glosnij() {
       // doShit();
        volume.setValue(mp.getVolume()*100);
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(volume.getValue()/100);
            }
        });

    }
}
