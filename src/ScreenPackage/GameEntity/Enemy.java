package ScreenPackage.GameEntity;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author vhqua
 */
public abstract class Enemy {

    /**
     *
     */
    public abstract void update();

    /**
     *
     * @param g
     */
    public abstract void draw(Graphics g);

    /**
     *
     * @return
     */
    public abstract Rectangle getBound();

    /**
     *
     * @return
     */
    public abstract boolean isOutOfScreen();
}
