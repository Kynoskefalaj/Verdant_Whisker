package root;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 32; //32x32 tile
    final double scale = 3;

    public final int tileSize = (int) (originalTileSize * scale); //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768 px
    final int screenHeight = tileSize * maxScreenRow; //576 px

//    FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true); //enabling this will improve performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { //SLEEP game loop

        //          1000000000 nanoseconds == 1 second
        double drawInterval = (double) 1000000000 / FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
//            System.out.println("The game is running in a loop");
//            1 UPDATE: update information such as character position
            update();
//            2 DRAW: draw the screen with the updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000; //converts time from nano to miliseconds

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
//    @Override
//    public void run() { //DELTA / ACCUMULATOR game loop
//
//        double drawInterval = 1000000000 / FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//        long timer = 0;
//        int drawCount = 0;
//
//        while (gameThread != null) {
//            currentTime = System.nanoTime();
//            delta += (currentTime - lastTime) / drawInterval;
//            timer += (currentTime - lastTime);
//            lastTime = currentTime;
//
//            if (delta >= 1) {
//                update();
//                repaint();
//                delta--;
//                drawCount++;
//            }
//            if (timer >= 1000000000) {
//                System.out.println("FPS: " + drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
//        }
//    }

    public void update() {

//        don't have to write like "(keyH.upPressed == true)" in each statement because this field is boolean type,
//        so statement is true if that field is true
        player.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}
