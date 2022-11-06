package ScreenPackage.GameEntity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author vhqua
 */
public class Cactus extends Enemy{
	
	private int catusX;
        private int catusY;
	private int width;
	private int height;
	private BufferedImage image;
	
	private Rectangle rectBound;
	
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param image
     */
    public Cactus(int x, int y, int width, int height, BufferedImage image) {
		this.catusX = x;
                this.catusY = y;
		this.width = width;
		this.height = height;
		this.image = image;
		rectBound = new Rectangle();
	}
	
    /**
     *
     */
    @Override
	public void update() {
		catusX -= Dinosaur.SPEED_X;
	}
        
    /**
     *
     * @param g
     */
    @Override
	public void draw(Graphics g) {
		g.drawImage(image, catusX, catusY, null);
		g.setColor(Color.red);
	}
	
    /**
     *
     * @return
     */
    @Override
	public Rectangle getBound() {
		rectBound = new Rectangle();
		rectBound.x = ((int) catusX + (image.getWidth() - width)/2) + 2;
		rectBound.y = catusY;
		rectBound.width = width - 5;
		rectBound.height = height - 5;
		return rectBound;
	}
        
    /**
     *
     * @return
     */
    @Override
	public boolean isOutOfScreen() {
		if(catusX < -image.getWidth()) {
                    return true;
		}
		return false;
	}
	
}
