/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScreenPackage.GameAudio;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author vhqua
 */
public class AudioControl {

    private AudioClip jump;
    private AudioClip dead;
    private AudioClip score;

    public AudioControl() {
        try {
            jump = Applet.newAudioClip(new URL("file", "", "src/ScreenPackage/GameAudio/jump.wav"));
            dead = Applet.newAudioClip(new URL("file", "", "src/ScreenPackage/GameAudio/dead.wav"));
            score = Applet.newAudioClip(new URL("file", "", "src/ScreenPackage/GameAudio/scoreup.wav"));
//src/ScreenPackage/GameAudio/jump.wav

        } catch (MalformedURLException ex) {
        }
    }

    public void playScore() {
        if (score != null) {
            score.play();
        }
    }

    public void playJump() {
        if (jump != null) {
            jump.play();
        }
    }

    public void playDead() {
        if (dead != null) {
            dead.play();
        }
    }
}
