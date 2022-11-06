/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScreenPackage.GameEntity;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author vhqua
 */
public class Bird extends Enemy {

    private Resource resource;
    private Animation biAnimation;
    private int bX;
    private int bY;
    private Rectangle bound;

    /**
     *
     * @param Gamewidth
     */
    public Bird(int Gamewidth) {
        resource = new Resource();
        this.bX = Gamewidth;
        this.bY = 90;
        this.biAnimation = new Animation(120);
        biAnimation.addFrame(resource.getResouceImage("src/data/bird1.png"));
        biAnimation.addFrame(resource.getResouceImage("src/data/bird2.png"));
        this.bound = new Rectangle();
    }

    /**
     *
     */
    @Override
    public void update() {
        biAnimation.updateFrame();
        bX -= Dinosaur.SPEED_X;

    }

    /**
     *
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(biAnimation.getFrame(), bX, bY, null);
        
    }

    /**
     *
     * @return
     */
    @Override
    public Rectangle getBound() {
        this.bound.x = bX;
        this.bound.y = bY;
        this.bound.width = biAnimation.getFrame().getWidth();
        this.bound.height = biAnimation.getFrame().getHeight();
        return this.bound;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isOutOfScreen() {
        if (bX <= 0) {
            return true;
        }
        return false;
    }

}
