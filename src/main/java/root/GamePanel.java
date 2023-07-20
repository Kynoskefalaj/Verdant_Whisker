package root;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    public final int originalTileSize = 32; //32x32 tile
    public final double scale = 2;

    public final int tileSize = (int) (originalTileSize * scale);
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

//    WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

//    FPS
    int FPS = 60;

//    SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public Sound sound = new Sound();
    public Sound se = new Sound(); //Sound Effect
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter asSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public UtilityTool uTool = new UtilityTool();
    Thread gameThread;

//    ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
//    We can display 10 object on screen at the same time
    public Entity objects[] = new Entity[10];
    public Entity npcs[] = new Entity[10];
    public Entity monsters[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;

    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //enabling this will improve performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame () {

        asSetter.setObject();
        asSetter.setNPC();
        asSetter.setMonster();

        playMusic(sound.mainTheme);
        gameState = titleState;
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
        if(gameState == playState) {
            // PLAYER
            player.update();
            // NPC
            for (Entity entity : npcs) {
                if (entity != null) {
                    entity.update();
                }
            }
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i] != null) {
                    if (monsters[i].alive == true && monsters[i].dying == false) {
                        monsters[i].update();
                    }
                    if (monsters[i].alive == false) {
                        monsters[i] = null;
                    }
                }
            }
        } if(gameState == pauseState) {
            // NOTHING (for that moment)
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            // TILE
            tileM.draw(g2); //tiles must be drawn before player, otherwise tiles would override player image

            //ADD ENTITIES TOP THE LIST
            entityList.add(player);
            for (Entity npc : npcs) {
                if (npc != null) {
                    entityList.add(npc);
                }
            }

            for (Entity obj : objects) {
                if (obj != null) {
                    entityList.add(obj);
                }
            }

            for (Entity monster : monsters) {
                if (monster != null) {
                    entityList.add(monster);
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity ent1, Entity ent2) {

                    int result = Integer.compare(ent1.worldY, ent2.worldY);
                    return result;
                }
            });

            // DRAW ENTITIES
            for (Entity entity : entityList) {
                entity.draw(g2);
            }
            // EMPTY ENTITY LIST (otherwise it will grow on every loop)
            entityList.clear();

            // USER INTERFACE
            ui.draw(g2);

            if (keyH.checkDrawTime == true) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor((Color.white));
                g2.drawString("Draw Time: " + passed, 10, 400);
                System.out.println("Draw Time: " + passed);
            }
        }

        g2.dispose();
    }
    public void playMusic(URL url) {

        sound.setFile(url);
        sound.play();
        sound.loop();
    }
    public void stopMusic() {

        sound.stop();
    }
    public void playSE(URL url) {
        //Sound Effect
        se.setFile(url);
        se.play();
    }
}
