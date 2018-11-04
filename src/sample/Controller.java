package sample;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import sun.applet.AppletAudioClip;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.applet.AudioClip;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    Pane pane;

    @FXML
    Slider volume, slider;

    @FXML
    RadioButton rU,rD,rS,rK;


    MediaPlayer toto = new MediaPlayer(new Media(new File("africa.mp3").toURI().toString()));
    MediaPlayer animals = new MediaPlayer(new Media(new File("house.mp3").toURI().toString()));
    MediaPlayer klocuch = new MediaPlayer(new Media(new File("aezakmi.mp3").toURI().toString()));
    MediaPlayer dre = new MediaPlayer(new Media(new File("still.mp3").toURI().toString()));
    MediaPlayer currentSong;

    ToggleGroup group1 = new ToggleGroup();
    ToggleGroup group2 = new ToggleGroup();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initRadioButtons();
       // playOther();

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(slider.getValue() <= 92 && slider.getValue() >=80  && rD.isSelected() == true && rS.isSelected() == true){
                    if(currentSong != animals) {
                        currentSong.stop();
                        animals.play();
                        currentSong = animals;
                        glosnij(currentSong);
                    }

                }
                if(slider.getValue() < 78 && slider.getValue() >=65  && rU.isSelected() == true && rK.isSelected() == true){

                    if(currentSong != dre) {
                        currentSong.stop();
                        dre.play();
                        currentSong = dre;
                        glosnij(currentSong);
                    }
                }
                if(slider.getValue() < 45 && slider.getValue() >=30  && rU.isSelected() == true && rS.isSelected() == true){

                    if(currentSong != klocuch) {
                        currentSong.stop();
                        klocuch.play();
                        currentSong = klocuch;
                        glosnij(currentSong);
                    }
                }
                if(slider.getValue() < 15 && slider.getValue() >=0  && rD.isSelected() == true && rK.isSelected() == true){

                    if(currentSong != toto) {
                        currentSong.stop();
                        toto.play();
                        currentSong = toto;
                        glosnij(currentSong);
                    }
                }
            }
        });
    }

    @FXML
    void initRadioButtons() {
        rU.setToggleGroup(group1);
        rD.setToggleGroup(group1);
        rS.setToggleGroup(group2);
        rK.setToggleGroup(group2);
    }

    @FXML
    private void startPlaying(){


          rD.setSelected(true);
          rK.setSelected(true);

            toto.play();

            currentSong =toto;
            glosnij(currentSong);

        pane.setOnMouseDragged(new EventHandler<javafx.scene.input.MouseEvent>() {
            double oldX = 725.0;
            double oldRot = 11;

            @Override
            public void handle(javafx.scene.input.MouseEvent event) {


             //   if (slider.getValue() <= slider.getMax() && slider.getValue() >= slider.getMin()) {
                    if (oldX - event.getX() < 0) {
                        if (event.getY() < 100) {
                            slider.setValue(slider.getValue() + 0.35);
                            if (slider.getValue() < slider.getMax())
                                pane.setRotate(oldRot + 1.3);

                        } else {
                            slider.setValue(slider.getValue() - 0.35);
                            if (slider.getValue() > slider.getMin())
                                pane.setRotate(oldRot - 1.3);
                        }
                    } else if (oldX - event.getX() > 0) {
                        if (event.getY() < 100) {
                            slider.setValue(slider.getValue() - 0.35);
                            if (slider.getValue() > slider.getMin())
                                pane.setRotate(oldRot - 1.3);
                        } else {
                            slider.setValue(slider.getValue() + 0.35);
                            if (slider.getValue() < slider.getMax())
                                pane.setRotate(oldRot + 1.3);
                        }
                    }
                    oldX = event.getX();
                    oldRot = pane.getRotate();
                System.out.println(slider.getValue());

                }
        });

    }


    public void glosnij(MediaPlayer mp) {
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
