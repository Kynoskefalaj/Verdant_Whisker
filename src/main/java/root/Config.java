package root;

import java.io.*;

public class Config {

    GamePanel gp;

    public Config (GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            // FULL SCREEN
            if (gp.fullScreenOn) {
                bw.write("On");
            }
            if (!gp.fullScreenOn) {
                bw.write("Off");
            }
            bw.newLine();

            // PROPORTIONS
            bw.write(String.valueOf(gp.screenProportions));
            bw.newLine();

            // MUSIC VOLUME
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            // SE VOLUME
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfig() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();

            // FULL SCREEN
            if (s!= null) {
                if (s.equals("On")) {
                    gp.fullScreenOn = true;
                }
                if (s.equals("Off")) {
                    gp.fullScreenOn = false;
                }

                s = br.readLine();
                switch (s) {
                    case "res16_9" -> gp.screenProportions = ScreenProportions.res16_9;
                    case "res21_9" -> gp.screenProportions = ScreenProportions.res21_9;
                    case "res3_2" -> gp.screenProportions = ScreenProportions.res3_2;
                }

                // MUSIC VOLUME
                s = br.readLine();
                gp.music.volumeScale = Integer.parseInt(s);

                // SE VOLUME
                s = br.readLine();
                gp.se.volumeScale = Integer.parseInt(s);
            }
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
