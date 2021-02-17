package game.enemy;

import game.Setting;
import lib.FrameCounter;
import game.coreGame.GameObject;

public class AddObstacle extends GameObject {
    private FrameCounter waveCounter1;
    private FrameCounter waveCounter2;
    private FrameCounter waveCounter4;
    private FrameCounter switchCounter;
    private int currentLevel;
    private int waveSize1;
    private int waveSize2;
    private int waveSize3;
    private int waveSize4;


    public AddObstacle() {
        this.currentLevel = 1;
        this.waveSize1 = 0;
        this.waveSize2 = 0;
        this.waveSize3 = 0;
        this.waveSize4 = 0;
        this.waveCounter1 = new FrameCounter(180);
        this.waveCounter2 = new FrameCounter(120);
        this.waveCounter4 = new FrameCounter(25);
        this.switchCounter = new FrameCounter(180);
    }


    public void run(){
        if (Setting.gameStarted) {
            switch (this.currentLevel) {
                case 1:
                    this.wave1();
                    break;
                case 2:
                    this.wave2();
                    break;
                case 3:
                    this.wave3();
                    break;
                case 4:
                    this.wave4();
                    break;
                case 5:
                    this.bossWave();
                    break;

            }
        }
    }

    private void bossWave() {
        GameObject.gameObjects.add(new Boss());
        this.currentLevel ++;
    }

    private void wave1() {
        if (this.waveCounter1.run() && this.waveSize1 < 5) {
            GameObject.gameObjects.add(new NormalEnemy((int) (Math.random() * ((2 - 1) + 1)) + 1, (int) (Math.random() * ((150 - 50) + 1)) + 50, (int) (Math.random() * ((-50 - -70) + 1)) + -70));
            GameObject.gameObjects.add(new NormalEnemy((int) (Math.random() * ((2 - 1) + 1)) + 1, (int) (Math.random() * ((480 - 280) + 1)) + 280, (int) (Math.random() * ((-70 - -100) + 1)) + -100));
            this.waveSize1++;
            this.waveCounter1.reset();
        }
        if (this.waveSize1 == 5) {
            if (this.switchCounter.run()) {
                this.currentLevel ++;
                this.switchCounter.reset();
            }
        }

    }

    private void wave2() {
        if (this.waveCounter2.run() && this.waveSize2 < 6) {
            GameObject.gameObjects.add(new Meteorite((int) (Math.random() * ((150 - 50) + 1)) + 50, -50));
            GameObject.gameObjects.add(new Meteorite((int) (Math.random() * ((400 - 250) + 1)) + 250, -70));
            this.waveSize2 ++;
            this.waveCounter2.reset();
        }
        if (this.waveSize2 == 6) {
            if (this.switchCounter.run()) {
                this.currentLevel ++;
                this.switchCounter.reset();
            }
        }

    }

    private void wave3() {
        if (this.waveSize3 == 0) {
            SpecialEnemy enemy1 = new SpecialEnemy();
            GameObject.gameObjects.add(enemy1);
            this.waveSize3 ++;
        }
        if (this.waveSize3 == 1 && GameObject.allEnemies.size() == 0) {
            GameObject.gameObjects.add(new SpecialEnemy());
            this.waveSize3 ++;
        }
        if (this.waveSize3 == 2 && GameObject.allEnemies.size() == 0) {
            GameObject.gameObjects.add(new SpecialEnemy());
            this.waveSize3 ++;
        }
        if (this.waveSize3 == 3 && GameObject.allEnemies.size() == 0) {
            if (this.switchCounter.run()) {
                this.currentLevel ++;
                this.switchCounter.reset();
            }
        }
    }

    private void wave4() {
        if (this.waveCounter4.run() && this.waveSize4 < 50) {
            GameObject.gameObjects.add(new Obstacle());
            this.waveSize4 ++;
            this.waveCounter4.reset();
        }
        if (this.waveSize4 == 50) {
            if (this.switchCounter.run()) {
                this.currentLevel ++;
                this.switchCounter.reset();
            }
        }
    }



}
