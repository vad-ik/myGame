package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class UpdateMenu {
    static int xp;
    static int dmg;
    static int range;
    static float timer;
    static int turretLimit;
    static Table table = new Table();
    static Skin skin;
    static int updateCost;
    static TextButton dmgButton;
    static TextButton turretColVoButton;
    static TextButton timerButton;
    static TextButton rangeButton;
    static TextButton xpButton;

    public UpdateMenu() {
        table.clear();
        table.setPosition(0, 0);
        table.setFillParent(true);
        skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));
        xp = 10;
        dmg = 1;
        range = Gdx.graphics.getHeight() / 5;
        timer = 30;
        updateCost = 10;
        turretLimit = 100;


        table.pad(10).defaults().expandX().space(4);

        xpButton = new TextButton("update xp " + updateCost + " coins. " + xp, skin);

        dmgButton = new TextButton("update dmg " + updateCost + " coins. " + dmg, skin);
        rangeButton = new TextButton("update range " + updateCost + " coins. " + range, skin);
        timerButton = new TextButton("update reloading " + updateCost + " coins. " + timer, skin);
        turretColVoButton = new TextButton("update turetLimit " + updateCost + " coins. " + turretLimit, skin);
        TextButton Out = new TextButton("out to game", skin);
        int HeightNormal = Gdx.graphics.getHeight() / 7;
        table.add(xpButton).height(HeightNormal);
        table.row().pad(2, 0, 2, 0);

        table.add(dmgButton).height(HeightNormal);
        table.row().pad(2, 0, 2, 0);
        table.add(rangeButton).height(HeightNormal);
        table.row().pad(2, 0, 2, 0);
        table.add(timerButton).height(HeightNormal);
        table.row().pad(2, 0, 2, 0);
        table.add(turretColVoButton).height(HeightNormal);
        table.row().pad(2, 0, 2, 0);
        table.add(Out).height(HeightNormal);
        table.row().pad(2, 0, 2, 0);

        xpButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Screen.money >= 10) {
                    Screen.money -= 10;
                    xp = xp + 10;
                    xpButton.setText("update xp " + updateCost + " coins. " + xp);
                    for (int i = 0; i < Screen.turetArray.size(); i++) {
                        Screen.turetHP.set(i, Screen.turetHP.get(i) + 10);
                    }
                }
            }
        });

        dmgButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Screen.money >= 10) {
                    Screen.money -= 10;
                    dmg = dmg + 1;
                    dmgButton.setText("update dmg " + updateCost + " coins. " + dmg);
                    for (int i = 0; i < Screen.turetArray.size(); i++) {
                        Turet.dmg += 1;
                    }
                }
            }
        });

        rangeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Screen.money >= 10) {
                    Screen.money -= 10;
                    range = range + 10;
                    rangeButton.setText("update range " + updateCost + " coins. " + range);
                    for (int i = 0; i < Screen.turetArray.size(); i++) {
                        Turet.range += 1;
                    }
                }
            }
        });

        timerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Screen.money >= 10) {
                    Screen.money -= 10;
                    timer -= ( timer * 0.05 );
                    timerButton.setText("update reloading " + updateCost + " coins. " + timer);
                    for (int i = 0; i < Screen.turetArray.size(); i++) {
                        Turet.timer = timer;
                    }
                }
            }
        });

        turretColVoButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Screen.money >= 10) {
                    Screen.money -= 10;
                    turretLimit = turretLimit + 1;
                    turretColVoButton.setText("update turetLimit " + updateCost + " coins. " + turretLimit);
                    Player.turretLimit++;
                }
            }
        });


        Out.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Screen.stage.clear();
                Screen.updateMenFlag = false;
                Screen.stage.addActor(Player.tableStats);
                Screen.stage.addActor(Player.tableControl);

                Screen.stage.addActor(Player.tableName);
            }
        });


    }

    static void update() {
        xpButton.setText("update xp " + updateCost + " coins. " + xp);
        turretColVoButton.setText("update turetLimit " + updateCost + " coins. " + turretLimit);
        timerButton.setText("update reloading " + updateCost + " coins. " + timer);
        dmgButton.setText("update dmg " + updateCost + " coins. " + dmg);
        rangeButton.setText("update range " + updateCost + " coins. " + range);

    }

    void render() {


        Screen.stage.act();
        Screen.stage.draw();
    }
}
