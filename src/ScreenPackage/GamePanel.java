/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScreenPackage;

import ScreenPackage.GameAudio.AudioControl;
import ScreenPackage.GameEntity.Clouds;
import ScreenPackage.GameEntity.Dinosaur;
import ScreenPackage.GameEntity.EnemiesManager;
import ScreenPackage.GameEntity.Land;
import ScreenPackage.GameEntity.Resource;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author vhqua
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int SCREEN_HEIGHT = 175;
    public static final int SCREEN_WIDTH = 600;
    private final int FPS = 60;
    private final int GAME_PLAYING = 1;
    private final int GAME_OVER = 2;

    private int SPEED_Y;
    private boolean isKeyPressed;
    private float Score;
    private float HighestScore;
    private BufferedImage replayButton;
    private BufferedImage gameOverButton;
    private int GAME_STATE = GAME_PLAYING;
    private Dinosaur dino;
    private Land land;
    private Clouds clouds;
    private EnemiesManager eManager;
    private Resource resource;
    private boolean checkContinue;
    private static final NumberFormat scoreFM = new DecimalFormat("000000");
    private float time;
    private AudioControl audio;
    Thread thread;

    /**
     * Constructor
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(600, 175));
        this.setBackground(Color.decode("#f7f7f7"));
        dino = new Dinosaur();
        land = new Land(SCREEN_WIDTH);
        clouds = new Clouds(SCREEN_WIDTH);
        eManager = new EnemiesManager(dino);
        resource = new Resource();
        replayButton = resource.getResouceImage("src/data/replay_button.png");
        gameOverButton = resource.getResouceImage("src/data/gameover_text.png");
        thread = new Thread(this);
        this.Score = 0;
        time = 0;
        audio = new AudioControl();
        startGame();
    }

    /**
     *
     */
    public void checkHighestScore() {
        if (this.Score >= this.HighestScore) {
            setHighestScore(this.Score);
        }
    }

    /**
     *
     */
    public void calscore() {
        loadHighestScore();
        DecimalFormat increaseFm = new DecimalFormat("##.0");
        boolean check = true;
        float scoreIncrease = 0.1f;
        PlayScreen.tHighestScore.setText(scoreFM.format(getHighestScore()));
        if (GAME_STATE == GAME_PLAYING) {
            this.Score += Float.parseFloat(increaseFm.format(scoreIncrease));
            PlayScreen.tScore1.setText(scoreFM.format(this.Score));
            if (Float.parseFloat(increaseFm.format(this.Score)) % 100.0 == 0) {
                audio.playScore();
                dino.setSPEED_X(dino.getSPEED_X() + 1);
            }
        }
        saveHighestScore();
    }

    /**
     *
     * @return
     */
    public float getHighestScore() {
        return HighestScore;
    }

    /**
     *
     * @param HighestScore
     */
    public void setHighestScore(float HighestScore) {
        this.HighestScore = HighestScore;
    }

    /**
     *
     */
    public void loadHighestScore() {
        try {
            Scanner scanner = new Scanner(new File("score.txt"));
            HighestScore = Float.parseFloat(scanner.nextLine());
            setHighestScore(HighestScore);
        } catch (IOException ex) {
        }
    }

    /**
     *
     */
    public void saveHighestScore() {
        try {
            try (FileWriter fw = new FileWriter("score.txt")) {
                checkHighestScore();
                fw.write(getHighestScore() + "");
                fw.flush();
            }
        } catch (IOException ex) {
        }

    }

    /**
     *
     */
    public void startGame() {
        thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        clouds.draw(g);
        land.draw(g);
        eManager.draw(g);
        dino.draw(g);
        if (getGAME_STATE() == GAME_OVER) {
            g.setColor(Color.black);
            g.drawImage(gameOverButton, 200, 30, null);
            g.drawImage(replayButton, 283, 50, null);
            g.setFont(new Font("Yu Gothic", Font.BOLD, 10));
            g.drawString("ENTER TO PLAY AGAIN!", 245, 100);
        }

    }

    /**
     *
     */
    public void update() {
        if (GAME_STATE == GAME_PLAYING) {
            time += 0.025;
            PlayScreen.lbTime.setText(float2time(time));
            calscore();
            PlayScreen.btnback.setVisible(false);
            land.update();
            dino.update();
            clouds.update();
            eManager.update();
            if (eManager.isCollision()) {
                dino.setSPEED_X(4);
                PlayScreen.btnback.setVisible(true);
                this.setGAME_STATE(GAME_OVER);
                dino.isDead(true);
            }
        }

    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (thread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!isKeyPressed) {
            isKeyPressed = true;
            switch (getGAME_STATE()) {
                case GAME_PLAYING:
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        //jump
                        dino.jump();
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        //down
                        dino.down(true);
                    }
                    break;
                case GAME_OVER:
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        this.time = 0;
                        this.setGAME_STATE(GAME_PLAYING);
                        this.Score = 0;
                        reset();
                    }
            }
        }
    }

    /**
     *
     * @param GAME_STATE
     */
    public void setGAME_STATE(int GAME_STATE) {
        this.GAME_STATE = GAME_STATE;
    }

    /**
     *
     * @return
     */
    public int getGAME_STATE() {
        return GAME_STATE;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isKeyPressed = false;
        if (getGAME_STATE() == GAME_PLAYING) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                dino.down(false);
            }
        }
    }

    private void reset() {
        eManager.reset();
        dino.isDead(false);
        dino.reset();
    }

    private String float2time(float time) {
        return String.format("%02d:%02d:%02d", Math.round(time) / 3600, (Math.round(time) / 60) % 60, Math.round(time) % 60);
    }
}
