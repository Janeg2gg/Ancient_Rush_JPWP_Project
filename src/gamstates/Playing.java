package gamstates;

import Levels.LevelHandler;
import Main.Game;
import entieties.Player;
import ui.PauseOverlay;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Playing extends State implements Statemethods{
    private Player player1;
    private LevelHandler levelHandler;
    private PauseOverlay pauseOverlay;
    private boolean paused = false;

    private BufferedImage backgroundImg;

    public Playing(Game game) {
        super(game);
        initClasses();

        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.GAME_BACKGROUND_IMAGE);
    }

    private void initClasses() {
        levelHandler = new LevelHandler(game);
        player1 = new Player(200, 200, (int)(45*Game.SCALE),(int)(50*Game.SCALE));
        player1.loadLvlData(levelHandler.getCurrentLevel().getLvlData());
        pauseOverlay = new PauseOverlay(this);
    }

    public Player getPlayer1(){
        return player1;
    }

    public void windowFocusLost(){
        player1.resetBooleans();
    }

    public void unpauseGame(){
        paused = false;
    }

    @Override
    public void update() {
        if(!paused) {
            levelHandler.update();
            player1.update();
        }else{
            pauseOverlay.update();
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

        levelHandler.draw(g);
        player1.render(g);
        if(paused){
            g.setColor(new Color(0,0,0,150));
            g.fillRect(0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT);
            pauseOverlay.draw(g);
        }

    }

    public void mouseDragged(MouseEvent e){
        if(paused)
            pauseOverlay.mouseDragged(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(paused)
            pauseOverlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(paused)
            pauseOverlay.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(paused)
            pauseOverlay.mouseMoved(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()){

            case KeyEvent.VK_W:
                player1.setUp(true);
                break;
            case KeyEvent.VK_A:
                player1.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player1.setRight(true);
                break;
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()){

            case KeyEvent.VK_W:
                player1.setUp(false);
                break;
            case KeyEvent.VK_A:
                player1.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player1.setRight(false);
                break;
        }
    }
}
