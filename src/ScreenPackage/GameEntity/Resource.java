package ScreenPackage.GameEntity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 * @author vhqua
 */
public class Resource {

    /**
     *
     * @param path
     * @return
     */
    public BufferedImage getResouceImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
        }
        return img;
    }

}
