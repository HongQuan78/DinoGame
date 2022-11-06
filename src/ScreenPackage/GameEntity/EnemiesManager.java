/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScreenPackage.GameEntity;

import ScreenPackage.GamePanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author vhqua
 */
public class EnemiesManager {

    private Resource resource;
    private BufferedImage Cactus1;
    private BufferedImage Cactus2;
    private List<Enemy> enemies;
    private Dinosaur dino;

    /**
     *
     * @param dino
     */
    public EnemiesManager(Dinosaur dino) {
        this.dino = dino;
        resource = new Resource();
        enemies = new ArrayList<>();
        Cactus1 = resource.getResouceImage("src/data/cactus1.png");
        Cactus2 = resource.getResouceImage("src/data/cactus2.png");
        enemies.add(createEnemy());

    }

    private Enemy createEnemy() {
        Random rand = new Random();
        int typeEnemy = rand.nextInt(15);
        if (typeEnemy == 1 || typeEnemy == 3 || typeEnemy == 5 || typeEnemy == 7 ) {
            return new Cactus(GamePanel.SCREEN_WIDTH, 120, Cactus2.getWidth(), Cactus2.getHeight(), Cactus2);
        } else if (typeEnemy == 8 || typeEnemy == 15 || typeEnemy == 13) {
        return new Bird(600);
        } else {
            return new Cactus(GamePanel.SCREEN_WIDTH, 105, Cactus1.getWidth(), Cactus1.getHeight(), Cactus1);
        }
    }

    /**
     *
     */
    public void update() {
        for (Enemy e : enemies) {
            e.update();
        }
        Enemy enemy = enemies.get(0);
        if (enemy.isOutOfScreen()) {
            enemies.clear();
            enemies.add(createEnemy());
        }
    }

    /**
     *
     * @param g
     */
    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            e.draw(g);
        }
    }

    /**
     *
     */
    public void reset() {
        enemies.clear();
        enemies.add(createEnemy());
    }

    /**
     *
     * @return
     */
    public boolean isCollision() {
        for (Enemy e : enemies) {
            if (e.getBound().intersects(dino.getBound())) {
                return true;
            }
        }
        return false;
    }
}
