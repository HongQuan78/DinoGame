/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScreenPackage.GameEntity;

import ScreenPackage.GameAudio.AudioControl;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author vhqua
 */
public class Dinosaur {

    private final int LAND_Y = 110;
    private final float GRAVITY = 0.4f;
    private final int NORMAL_RUN = 0;
    private final int JUMPING = 1;
    private final int DOWN_RUN = 2;
    private final int DEATH = 3;
    private float dinoY;
    private float dinoX;

    /**
     *
     */
    public static float SPEED_X = 4;
    private float SPEED_Y;
    private Rectangle rectBound;
    private int DINO_STATE = NORMAL_RUN;
    private Animation DinoNormalRunAnimate;
    private BufferedImage DinoJumping;
    private Animation DinoDownRunAnimate;
    private BufferedImage DinoDeath;
    private Resource resource;
    private AudioControl audio;

    /**
     *
     */
    public Dinosaur() {
        reset();
    }

    /**
     *
     * @return
     */
    public float getSPEED_X() {
        return SPEED_X;
    }

    /**
     *
     * @param SPEED_X
     */
    public void setSPEED_X(float SPEED_X) {
        Dinosaur.SPEED_X = SPEED_X;
    }

    /**
     *
     * @param g
     */
    public void draw(Graphics g) {
        switch (DINO_STATE) {
            case NORMAL_RUN:
                g.drawImage(DinoNormalRunAnimate.getFrame(), (int) dinoX, (int) dinoY, null);
                break;
            case JUMPING:
                g.drawImage(DinoJumping, (int) dinoX, (int) dinoY, null);
                break;
            case DOWN_RUN:
                g.drawImage(DinoDownRunAnimate.getFrame(), (int) dinoX, (int) dinoY + 20, null);
                break;
            case DEATH:
                g.drawImage(DinoDeath, (int) dinoX, (int) dinoY, null);
                break;
        }
    }

    /**
     *
     */
    public void update() {
        DinoNormalRunAnimate.updateFrame();
        DinoDownRunAnimate.updateFrame();
        if (dinoY >= LAND_Y) {
            dinoY = LAND_Y;
            if (DINO_STATE != DOWN_RUN) {
                DINO_STATE = NORMAL_RUN;
            }
        } else {
            SPEED_Y += GRAVITY;
            dinoY += SPEED_Y;
        }
    }

    /**
     *
     */
    public void jump() {
        if (dinoY >= LAND_Y) {
            audio.playJump();
            SPEED_Y = -7.5f;
            dinoY += SPEED_Y;
            DINO_STATE = JUMPING;
        }
    }

    /**
     *
     * @param isDown
     */
    public void down(boolean isDown) {
        if (DINO_STATE == JUMPING) {
            return;
        }
        if (isDown) {
            DINO_STATE = DOWN_RUN;
        } else {
            DINO_STATE = NORMAL_RUN;
        }
    }

    /**
     *
     * @param isdead
     */
    public void isDead(boolean isdead) {
        if (isdead) {
            audio.playDead();
            DINO_STATE = DEATH;
        } else {
            DINO_STATE = NORMAL_RUN;
        }
    }

    /**
     *
     */
    public void reset() {
        dinoX = 50;
        dinoY = LAND_Y;
        this.resource = new Resource();
        rectBound = new Rectangle();
        DinoNormalRunAnimate = new Animation(90);
        DinoNormalRunAnimate.addFrame(resource.getResouceImage("src/data/main-character1.png"));
        DinoNormalRunAnimate.addFrame(resource.getResouceImage("src/data/main-character2.png"));
        DinoJumping = resource.getResouceImage("src/data/main-character3.png");
        DinoDownRunAnimate = new Animation(90);
        DinoDownRunAnimate.addFrame(resource.getResouceImage("src/data/main-character5.png"));
        DinoDownRunAnimate.addFrame(resource.getResouceImage("src/data/main-character6.png"));
        DinoDeath = resource.getResouceImage("src/data/main-character4.png");
        audio = new AudioControl();
    }

    /**
     *
     * @return
     */
    public Rectangle getBound() {
        if (DINO_STATE == DOWN_RUN) {
            this.rectBound.x = (int) dinoX + 5;
            this.rectBound.y = (int) dinoY + 20;
            this.rectBound.width = DinoDownRunAnimate.getFrame().getWidth() - 10;
            this.rectBound.height = DinoDownRunAnimate.getFrame().getHeight();
        } else {
            this.rectBound.x = (int) dinoX + 5;
            this.rectBound.y = (int) dinoY;
            this.rectBound.width = DinoNormalRunAnimate.getFrame().getWidth() - 10;
            rectBound.height = DinoNormalRunAnimate.getFrame().getHeight();
        }
        return this.rectBound;
    }

}
