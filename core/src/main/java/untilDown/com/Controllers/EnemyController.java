package untilDown.com.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import untilDown.com.Main;
import untilDown.com.Models.Bullet;
import untilDown.com.Models.Player;
import untilDown.com.Models.enemies.Enemy;
import untilDown.com.Models.enemies.Tentacle;
import untilDown.com.Models.enemies.Tree;

import java.util.ArrayList;

public class EnemyController {
    private static ArrayList<Bullet> enemyBullets = new ArrayList<>();

    private final int numOfTrees = 30;
    private int numOfTentaclesPerTime = 0;
    private float timeToSpawnTentacles = 3f;

    private int numOfEyeBatsPerTime = 0;
    private float timeToSpawnEyeBats = 10f;

    private boolean isBossAdded = true;

    private int backgroundWidth, backgroundHeight;

    ArrayList<Enemy> enemies = new ArrayList<>();

    public EnemyController() {
        addTrees();
    }

    public void setBackground(int backgroundX, int backgroundY) {
        this.backgroundWidth = backgroundX;
        this.backgroundHeight = backgroundY;
    }

    public static void addBullet(Bullet bullet) {
        enemyBullets.add(bullet);
    }


    public void updateGame(float delta, float time, int totalTime, Player player) {
        numOfTentaclesPerTime = (int) time/30;
        if (time >= (float) totalTime / 4) {
            numOfEyeBatsPerTime = (int) (4 * time - totalTime) / 30 + 1;
        }
        if (time >= (float) totalTime / 2 && !isBossAdded) {
            isBossAdded = true;
        }

        if (numOfTentaclesPerTime > 0) {
            timeToSpawnTentacles -= delta;
        }
        if (numOfEyeBatsPerTime > 0) {
            timeToSpawnEyeBats -= delta;
        }

        update(delta, player);
        draw();
    }

    public void update(float delta, Player player) {
        for (Enemy enemy : enemies) {
            enemy.update(delta, player);
        }
    }

    public void draw() {
        for (Enemy enemy : enemies) {
            enemy.render(Main.getBatch());
        }
    }

    public void addTrees() {
        for (int i = 0; i < numOfTrees; i++) {
            float x = (float) Math.random() * backgroundWidth;
            float y = (float) Math.random() * backgroundHeight;

            Tree tree = new Tree(new Vector2(x, y));
            enemies.add(tree);
        }
    }

    public void addTentacles() {
        for (int i = 0; i < numOfTentaclesPerTime; i++) {
            float x = (float) Math.random() * backgroundWidth;
            float y = (float) Math.random() * backgroundHeight;
            Tentacle tentacle = new Tentacle(new Vector2(x, y));
            enemies.add(tentacle);
        }
    }
}
