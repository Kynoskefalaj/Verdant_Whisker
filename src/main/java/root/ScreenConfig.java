package root;

import java.io.*;

public class ScreenConfig {

    ScreenProportions sp;

    public void loadConfig() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();


            if (s!= null) {
                s = br.readLine();
                switch (s) {
                    case "res16_9" -> sp = ScreenProportions.res16_9;
                    case "res21_9" -> sp = ScreenProportions.res21_9;
                    case "res3_2" -> sp = ScreenProportions.res3_2;
                }
            }
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
