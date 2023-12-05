package entieties;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.constants.Directions.*;
import static utilz.constants.Directions.DOWN;
import static utilz.constants.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private boolean left, down, up, right;
    private boolean moving = false;
    private float playerSpeed = 2.0f;
    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update(){

        updatePos();
        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex],(int)x,(int)y, 90, 100 ,null);
    }

    private void updateAnimation() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction))
                aniIndex = 0;
        }
    }

    private void setAnimation() {
        int startAnimation = playerAction;

        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if(startAnimation != playerAction){
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniIndex =0;
        aniIndex =0;
    }


    private void updatePos() {

        moving = false;

        if(left && !right){
            x -= playerSpeed;
            moving = true;
        }else if(right && !left){
            x += playerSpeed;
            moving = true;
        }

        if(up && !down){
            y -= playerSpeed;
            moving = true;
        }else if(down && !up){
            y += playerSpeed;
            moving = true;
        }

    }

    private void loadAnimations() {

        InputStream is = getClass().getResourceAsStream("/Player_1_sprite.png");

        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[4][6];
            for(int j = 0; j< animations.length; j++)
                for(int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i*48, 345 + j * 48,48, 50);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetBooleans(){
        left =false;
        up = false;
        down = false;
        right = false;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
