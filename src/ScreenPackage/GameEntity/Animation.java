package ScreenPackage.GameEntity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vhqua
 */
public class Animation {

	private List<BufferedImage> list;
	private final long deltaTime;
	private int currentFrame = 0;
	private long previousTime;

    /**
     *
     * @param deltaTime
     */
    public Animation(int deltaTime) {
		this.deltaTime = deltaTime;
		list = new ArrayList<>();
		previousTime = 0;
	}

    /**
     *
     */
    public void updateFrame() {
		if (System.currentTimeMillis() - previousTime >= deltaTime) {
			currentFrame++;
			if (currentFrame >= list.size()) {
				currentFrame = 0;
			}
			previousTime = System.currentTimeMillis();
		}
	}

    /**
     *
     * @param image
     */
    public void addFrame(BufferedImage image) {
		list.add(image);
	}

    /**
     *
     * @return
     */
    public BufferedImage getFrame() {
		return list.get(currentFrame);
	}

}