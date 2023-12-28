package utilz;

import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER1_ATLAS = "Player_1_sprite.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String MENU_BUTTONS = "menu_buttons.png";
    public static final String PAUSE_MENU = "pause_menu.png";
    public static final String PAUSE_BUTTONS = "pause_buttons.png";
    public static final String PAUSE_SOUND = "sound_button.png";
    public static final String PAUSE_VOLUME = "volume_buttons.png";
    public static BufferedImage GetSpriteAtlas(String fileName){
        BufferedImage img = null;

        InputStream is = LoadSave.class.getResourceAsStream("/"+ fileName);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }

    public static int [][]getLevelData(){
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

        for(int j = 0; j < img.getHeight();j++)
            for(int i = 0; i < img.getWidth();i++){
                Color color = new Color(img.getRGB(i,j));
                int value = color.getRed();
                if(value >= 48)
                    value = 0;
                lvlData[j][i] = value;
            }
        return lvlData;
    }
}
