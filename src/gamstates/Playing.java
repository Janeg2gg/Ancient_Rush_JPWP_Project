package gamstates;

import Levels.LevelHandler;
import Main.Game;
import entieties.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods{
    private Player player1;
    private LevelHandler levelHandler;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelHandler = new LevelHandler(game);
        player1 = new Player(200, 200, (int)(45*Game.SCALE),(int)(50*Game.SCALE));
        player1.loadLvlData(levelHandler.getCurrentLevel().getLvlData());
    }

    public Player getPlayer1(){
        return player1;
    }

    public void windowFocusLost(){
        player1.resetBooleans();
    }

    @Override
    public void update() {
        levelHandler.update();
        player1.update();
    }

    @Override
    public void draw(Graphics g) {
        levelHandler.draw(g);
        player1.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

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
                Gamestate.state = Gamestate.MENU;
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
