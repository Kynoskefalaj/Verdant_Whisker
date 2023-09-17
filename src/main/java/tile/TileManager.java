package tile;

import root.GamePanel;
import root.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tiles;
    public int[][][] mapTileNum;
    boolean drawPath = true;

    public TileManager (GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[100];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/worldV3.txt", 0);
        loadMap("/maps/interior01.txt", 1);
    }

    public void getTileImage() {

        // PLACEHOLDERS prevent from nullPointerException
        setUp(0, "grass/Grass00", false);
        setUp(1, "grass/Grass00", false);
        setUp(2, "grass/Grass00", false);
        setUp(3, "grass/Grass00", false);
        setUp(4, "grass/Grass00", false);
        setUp(5, "grass/Grass00", false);
        setUp(6, "grass/Grass00", false);
        setUp(7, "grass/Grass00", false);
        setUp(8, "grass/Grass00", false);
        setUp(9, "grass/Grass00", false);

        //TILES SET HERE

        // GRASS
        setUp(10, "grass/Grass00", false);
        setUp(11, "grass/Grass01", false);

        // WATER
        setUp(12, "water/Water00", true);
        setUp(13, "water/Water01", true);
        setUp(14, "water/Water02", true);
        setUp(15, "water/Water03", true);
        setUp(16, "water/Water04", true);
        setUp(17, "water/Water05", true);
        setUp(18, "water/Water06", true);
        setUp(19, "water/Water07", true);
        setUp(20, "water/Water08", true);
        setUp(21, "water/Water09", true);
        setUp(22, "water/Water10", true);
        setUp(23, "water/Water11", true);
        setUp(24, "water/Water12", true);
        setUp(25, "water/Water13", true);

        // PATH
        setUp(26, "path/Path00", false);
        setUp(27, "path/Path01", false);
        setUp(28, "path/Path02", false);
        setUp(29, "path/Path03", false);
        setUp(30, "path/Path04", false);
        setUp(31, "path/Path05", false);
        setUp(32, "path/Path06", false);
        setUp(33, "path/Path07", false);
        setUp(34, "path/Path08", false);
        setUp(35, "path/Path09", false);
        setUp(36, "path/Path10", false);
        setUp(37, "path/Path11", false);
        setUp(38, "path/Path12", false);

        setUp(39, "dirt1", false);
        setUp(40, "BrickWall01", false);

        // BUSHES
        setUp(41, "grass/Bush01", true);
        setUp(42, "grass/Bush02", true);
        setUp(43, "grass/Bush03", true);

        // INTERIOR
        setUp(44, "interior/Floor00", false);
        setUp(45, "interior/Interior01", true);
        setUp(46, "interior/Interior02", true);
        setUp(47, "interior/Interior03", true);
        setUp(48, "interior/Interior04", true);
        setUp(49, "interior/Interior05", true);
        setUp(50, "interior/Interior06", true);
        setUp(51, "interior/Interior07", true);
        setUp(52, "interior/Interior08", true);
        setUp(53, "interior/Interior09", false);
        setUp(54, "interior/Interior10", true);
        setUp(55, "interior/Interior11", true);
        setUp(56, "interior/Interior12", true);
        setUp(57, "interior/Interior13", true);

        setUp(58, "interior/BlackBackground", true);

        setUp(59, "BigHouse00", false);

        setUp(60, "path/Path_hole", false);



    }

    public void setUp(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/tiles/" + imageName + ".png")));
            tiles[index].image = uTool.scaleImage(tiles[index].image, gp.tileSize, gp.tileSize);
            tiles[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine(); // read a line of text

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

//        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, gp.tileSize, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 2*gp.tileSize, 0, gp.tileSize, gp.tileSize, null);

        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(tiles[tileNum].image, screenX, screenY,null);
        }
            worldCol++;


            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }
        }

        if(drawPath = true) {
            g2.setColor((new Color (255, 0, 0, 70)));

            for (int i = 0 ; i <gp.pFinder.pathList.size(); i++) {

                int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
        }
    }
}
