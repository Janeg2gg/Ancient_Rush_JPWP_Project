package entieties;

import Main.Game;
import utilz.LoadSave;
import static utilz.HelpMethods.*;


import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.constants.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private boolean left, down, up, right, jump;
    private boolean moving = false;
    private float playerSpeed = 2.0f;
    private int [][] lvlData;
    private float xDrawOffset = 2 * Game.SCALE;
    private float yDrawOffset = 8 * Game.SCALE;

    //Gravity/Jumping-------------------------------
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = - 2.25f * Game.SCALE;
    private float fallSpeedAfterColission = 0.5f * Game.SCALE;
    private boolean InAir = false;
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        inithitbox(x, y, 20 * Game.SCALE, 28 * Game.SCALE);
    }

    public void update(){

        updatePos();
        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex],(int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height ,null);
        drawHitbox(g);
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
        aniTick =0;
        aniIndex =0;
    }


    private void updatePos() {

        moving = false;

        if(up)
            jump();
        if(!left && !right && !InAir)
            return;

        float xSpeed = 0;

        if(left)
            xSpeed -= playerSpeed;
        if(right)
            xSpeed += playerSpeed;


        if(InAir){
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)){
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed); //already checking if can move left/right
            }else{
                hitbox.y = EntityNextToRoof(hitbox, airSpeed);
                if(airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterColission;
                updateXPos(xSpeed);
            }
        }else
            updateXPos(xSpeed);

        moving = true;
    }

    private void jump() {
        if(InAir)
            return;
        InAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        InAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if(CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)){
           hitbox.x += xSpeed;
        }else {
            hitbox.x = EntityNextToWall(hitbox, xSpeed);
        }
    }


    private void loadAnimations() {

        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER1_ATLAS);
        animations = new BufferedImage[4][6];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * 48, 345 + j * 48, 48, 50);
    }

    public void loadLvlData(int [][] lvlData){
        this.lvlData = lvlData;
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
