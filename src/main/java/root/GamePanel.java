package root;

import entities.Entity;
import entities.Player;
import tile.TileManager;
import tile.interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    public final int originalTileSize = 32; //32x32 tile
    public final double scale = 2;

    public final int tileSize = (int) (originalTileSize * scale);
    public int maxScreenCol;
    public int maxScreenRow;
    public int screenWidth;
    public int screenHeight;

//    WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;

//    FOR FULL SCREEN
    int screenWidth2;
    int screenHeight2;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;
    public ScreenProportions screenProportions;

//    FPS
    int FPS = 60;

//    SYSTEM
    TileManager tileM;
    public KeyHandler keyH;
    public Sound music;
    public Sound se; //Sound Effect
    public CollisionChecker cChecker;
    public AssetSetter asSetter;
    public UI ui;
    public EventHandler eHandler;
    public Config config;
    public UtilityTool uTool;
    public ScreenConfig screenConfig;
    Thread gameThread;

//    ENTITY AND OBJECT
    public Player player;
//    We can display 10 object on screen at the same time
    public Entity[][] objects;
    public Entity[][] npcs;
    public Entity[][] monsters;
    public InteractiveTile[][] iTile = new InteractiveTile[maxMap][50];
    public ArrayList<Entity> projectilesList = new ArrayList<>();
    public ArrayList<Entity> particlesList = new ArrayList<>();
    public ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;

    public GamePanel () {

        this.screenConfig = new ScreenConfig();
        setScreenProportions();

        //INSTANTIATE
        this.tileM = new TileManager(this);
        this.keyH = new KeyHandler(this);
        this.music = new Sound();
        this.se = new Sound(); //Sound Effect
        this.cChecker = new CollisionChecker(this);
        this.asSetter = new AssetSetter(this);
        this.ui = new UI(this);
        this.eHandler = new EventHandler(this);
        this.config = new Config(this);
        this.uTool = new UtilityTool();

        this.player = new Player(this, keyH);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //enabling this will improve performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setScreenProportions() {

        screenConfig.loadConfig();
        screenProportions = screenConfig.sp;

        switch (screenProportions) {
            case res16_9 -> maxScreenCol = 21;
            case res21_9 -> maxScreenCol = 28;
            case res3_2 -> maxScreenCol = 18;
        }

        maxScreenRow = 12;

        screenWidth = tileSize * maxScreenCol;
        screenHeight = tileSize * maxScreenRow;

        screenWidth2 = screenWidth;
        screenHeight2 = screenHeight;
    }

    public void setupGame () {

//        setScreenProportions();
        asSetter.setObjects();
        asSetter.setNPC();
        asSetter.setMonster();
        asSetter.setInteractiveTile();

        playMusic(music.mainTheme);
        gameState = titleState;

        //Blank buffered image as large as the game screen
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        //Everything what that g2 draws will be recorded in this tempScreen BufferedImage
        g2 = (Graphics2D) tempScreen.getGraphics();

        if(fullScreenOn == true) {
            setFullScreen();
        }
    }

    public void retry() {
        player.setDefaultPosition();
        player.restoreLifeAndMana();
        asSetter.setNPC();
        asSetter.setMonster();
    }

    public void restore() {
        player.setDefaultValues();
        player.setItems();
        asSetter.setNPC();
        asSetter.setMonster();
        asSetter.setObjects();
        asSetter.setInteractiveTile();
    }

    public void setFullScreen() {
        // GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
//    public void run() { //SLEEP game loop
//
//        //          1000000000 nanoseconds == 1 second
//        double drawInterval = (double) 1000000000 / FPS; // 0.01666 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread != null) {
////            System.out.println("The game is running in a loop");
////            1 UPDATE: update information such as character position
//            update();
////            2 DRAW: draw the screen with the updated information
//            drawToTempScreen(); // Draw everything to the buffered image
//            drawToScreen(); // draw the buffered image to the screen
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime /= 1000000; //converts time from nano to miliseconds
//
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
    public void run() { //DELTA / ACCUMULATOR game loop

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                drawToTempScreen(); // Draw everything to the buffered image
                drawToScreen(); // draw the buffered image to the screen
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {
//        don't have to write like "(keyH.upPressed == true)" in each statement because this field is boolean type,
//        so statement is true if that field is true
        if(gameState == playState) {
            // PLAYER
            player.update();
            // NPC
//            for (Entity entity : npcs[1]) {
//                if (entity != null) {
//                    entity.update();
//                }
//            }
            for (int i = 0; i < npcs[1].length; i++) {
                if (npcs[currentMap][i] != null) {
                    npcs[currentMap][i].update();
                }
            }
            // MONSTERS
            for (int i = 0; i < monsters[1].length; i++) {
                if (monsters[currentMap][i] != null) {
                    if (monsters[currentMap][i].alive &&  ! monsters[currentMap][i].dying) {
                        monsters[currentMap][i].update();
                    }
                    if (! monsters[currentMap][i].alive) {
                        monsters[currentMap][i].checkDrop();
                        monsters[currentMap][i] = null;
                    }
                }
            }
            // PROJECTILES
            for (int i = 0; i < projectilesList.size(); i++) {
                if (projectilesList.get(i) != null) {
                    if (projectilesList.get(i).alive) {
                        projectilesList.get(i).update();
                    }
                    if ( ! projectilesList.get(i).alive) {
                        projectilesList.remove(i);
                    }
                }
            }

            // PARTICLES
            for (int i = 0; i < particlesList.size(); i++) {
                if (particlesList.get(i) != null) {
                    if (particlesList.get(i).alive) {
                        particlesList.get(i).update();
                    }
                    if ( ! particlesList.get(i).alive) {
                        particlesList.remove(i);
                    }
                }
            }
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }
        }
        if(gameState == pauseState) {
            // NOTHING (for that moment)
        }
    }

    public void drawToTempScreen() {
        // DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            // TILE
            tileM.draw(g2); //tiles must be drawn before player, otherwise tiles would override player image

            // INTERACTIVE TILE
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }

            //ADD ENTITIES TO THE LIST
            entityList.add(player);
//            for (Entity npc : npcs[1]) {
//                if (npc != null) {
//                    entityList.add(npc);
//                }
//            }
            for (int i = 0; i < npcs[1].length; i++) {
                if (npcs[currentMap][1] != null) {
                    entityList.add(npcs[currentMap][1]);
                }
            }

//            for (Entity obj : objects[1]) {
//                if (obj != null) {
//                    entityList.add(obj);
//                }
//            }
            for (int i = 0; i < objects[1].length; i++) {
                if (objects[currentMap][i] != null) {
                    entityList.add(objects[currentMap][i]);
                }
            }

//            for (Entity monster : monsters[1]) {
//                if (monster != null) {
//                    entityList.add(monster);
//                }
//            }
            for (int i = 0; i < monsters[1].length; i++) {
                if (monsters[currentMap][i] != null) {
                    entityList.add(monsters[currentMap][i]);
                }
            }

            for (Entity projectile : projectilesList) {
                if (projectile != null) {
                    entityList.add(projectile);
                }
            }

            for (Entity particle : particlesList) {
                if (particle != null) {
                    entityList.add(particle);
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
    }

    public void drawToScreen() {

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic(URL url) {
        music.setFile(url);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(URL url) {
        //Sound Effect
        se.setFile(url);
        se.play();
    }
}
