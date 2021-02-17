package game.coreGame;

import game.Setting;
import game.background.*;
import game.player.PlayerHPBar;
import lib.Vector2D;
import game.enemy.AddObstacle;
import game.enemy.Enemy;
import game.player.Player;
import physics.BoxCollider;
import renderer.Renderer;

import java.awt.*;
import java.util.ArrayList;

public class GameObject {
    public Vector2D position;
    public Vector2D velocity;
    public Vector2D anchor;
    public Renderer renderer;
    public BoxCollider boxCollider;
    public boolean active;
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    public static ArrayList<GameObject> topLayer = new ArrayList<>();
    public static ArrayList<GameObject> midLayer = new ArrayList<>();
    public static ArrayList<GameObject> botLayer = new ArrayList<>();
    public static ArrayList<Enemy> allEnemies = new ArrayList<>();

    public GameObject(){
        this.position = new Vector2D(0,0);
        this.anchor = new Vector2D(2, 2);
        this.velocity = new Vector2D();
        this.active = true;
    }

    public static void runAll(){
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i).active && !Setting.gamePaused || gameObjects.get(i).getClass().isAssignableFrom(BackgroundGameOver.class) || gameObjects.get(i).getClass().isAssignableFrom(BackgroundGamePause.class)) gameObjects.get(i).run();
        }
        removeInactive();
    }


    public static void renderAll(Graphics g) {
        for (int i = 0; i < botLayer.size(); i++) {
            if (botLayer.get(i).active) botLayer.get(i).render(g);
        }
        for (int i = 0; i < midLayer.size(); i++) {
            if (midLayer.get(i).active) midLayer.get(i).render(g);
        }
        for (int i = 0; i < topLayer.size(); i++) {
            if (topLayer.get(i).active) topLayer.get(i).render(g);
        }
    }

    public static void resetGame() {
        gameObjects.clear();
        allEnemies.clear();
        topLayer.clear();
        midLayer.clear();
        botLayer.clear();
        gameObjects.add(new Background_back());
        gameObjects.add(new Background_front());
        gameObjects.add(new Player());
        gameObjects.add(new Background_fore());
        gameObjects.add(new PlayerHPBar());
        gameObjects.add(new AddObstacle());
        gameObjects.add(new BackgroundGamePause());
        Setting.bossDied = false;
    }

    private static void removeInactive() {
        ArrayList<GameObject> foundInactive = new ArrayList<>();

        for (int i = 0; i < gameObjects.size(); i ++) {
            if (!gameObjects.get(i).active && !gameObjects.get(i).getClass().isAssignableFrom(Player.class) && !gameObjects.get(i).getClass().isAssignableFrom(BackgroundLoading.class) && !gameObjects.get(i).getClass().isAssignableFrom(BackgroundGameOver.class)) {
                foundInactive.add(gameObjects.get(i));
            }
        }
        gameObjects.removeAll(foundInactive);
        allEnemies.removeAll(foundInactive);
    }

    public static Player getPlayer() {
        for (GameObject go : gameObjects) {
            if (go instanceof Player) {
                return (Player) go;
            }
        }
        return null;
    }

    public void run() {
        this.position.addThis(this.velocity.x, this.velocity.y);
    }

    public void render(Graphics g){
        if (this.renderer != null){
            this.renderer.render(g, this);
        }
    }


    public void destroy(){
        this.active = false;
    }

//    public static <Object extends GameObject> Object findIntersected(Class<Object> clazz, BoxCollider boxCollider){
//    }

}
