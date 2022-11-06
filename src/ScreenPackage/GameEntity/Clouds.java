package ScreenPackage.GameEntity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author vhqua
 */
public class Clouds {
    Resource resource;
    private List<ImageCloud> listCloud;
    private BufferedImage cloud;
    private int Width;

    /**
     *
     * @param width
     */
    public Clouds(int width) {
        this.Width = width;
        resource = new Resource();
        cloud = resource.getResouceImage("src/data/cloud.png");
        listCloud = new ArrayList<>();

        ImageCloud imageCloud = new ImageCloud();
        imageCloud.posX = 0;
        imageCloud.posY = 30;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 150;
        imageCloud.posY = 40;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 300;
        imageCloud.posY = 50;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 450;
        imageCloud.posY = 20;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 600;
        imageCloud.posY = 60;
        listCloud.add(imageCloud);
    }

    /**
     *
     */
    public void update() {
        Iterator<ImageCloud> itr = listCloud.iterator();
        ImageCloud firstElement = itr.next();
        firstElement.posX -= Dinosaur.SPEED_X / 8;
        while (itr.hasNext()) {
            ImageCloud element = itr.next();
            element.posX -= Dinosaur.SPEED_X / 8;
        }
        if (firstElement.posX < -cloud.getWidth()) {
            listCloud.remove(firstElement);
            firstElement.posX = this.Width;
            listCloud.add(firstElement);
        }
    }

    /**
     *
     * @param g
     */
    public void draw(Graphics g) {
        for (ImageCloud imgLand : listCloud) {
            g.drawImage(cloud, (int) imgLand.posX, imgLand.posY, null);
        }
    }

    private class ImageCloud {

        float posX;
        int posY;
    }
}
