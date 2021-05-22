package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Player {
    static boolean sideW;
    static boolean sideA;
    static boolean sideS;
    static boolean sideD;

    static int life;
    static int positionDrawable = 1;
    static boolean lastSideW;
    static boolean lastSideA;
    static boolean lastSideS;
    static boolean lastSideD;
    static int turretColVo;
    static public int x;
    static public int y;
    static int turretLimit;
    static ProgressBar lifeBar;
    static TextField scoredText;
    static TextField nameText;
    static TextField moneyText;
    static TextField turretLimitText;
    static Table tableStats = new Table();
    static Table tableName = new Table();
    static Table tableControl = new Table();
    static Skin skin;
    public static Skin skinText;
    static String scoredForInt;
    static String moneyForInt;
    TextButton update;
    static ImageButton turretButon;
    static Touchpad touchpad;
    static int speed;
    double xThis;
    double yThis;
    static Sprite sprite;
    static String turretLimitTextString;

    public Player() {
        speed = Gdx.graphics.getHeight() / 100;

        tableStats.setPosition(-Gdx.graphics.getWidth() / 2 + 150, Gdx.graphics.getHeight() / 2 - 150);
        tableStats.setFillParent(true);
        tableName.setPosition(0, 0);
        tableName.setFillParent(true);
        tableControl.setPosition(0, -Gdx.graphics.getHeight() / 3);
        tableControl.setFillParent(true);
        skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));
        skinText = new Skin(Gdx.files.internal("terra-mother/skin/terra-mother-ui.json"));
        lifeBar = new ProgressBar(0, 100, 1, false, skin);
        touchpad = new Touchpad(10, skin);
        turretButon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("turetIcon.png"))));
        tableControl.add(touchpad).fillX().pad(0, Gdx.graphics.getWidth() / 30, 10, Gdx.graphics.getWidth() / 5 * 3);
        tableControl.add(turretButon).fillX().pad(0, 0, 10, Gdx.graphics.getWidth() / 30);
        turretButon.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (( Screen.money > 0 ) && ( turretColVo < turretLimit )) {
                    Screen.turetArray.add(new Turet(x + Screen.xMir, y + Screen.yMir));
                    Screen.money--;
                    turretColVo++;
                }

            }
        });

        scoredForInt = ( "socer: " + Screen.score );
        moneyForInt = ( "money: " + Screen.money );
        update = new TextButton("Update", skin);
        scoredText = new TextField(scoredForInt, skinText);
        moneyText = new TextField(moneyForInt, skinText);
        nameText = new TextField(Screen.name.getText(), skinText);
        turretLimitTextString = ( "Turret " + turretColVo + "/" + turretLimit );
        Player.turretLimitText = new TextField(turretLimitTextString, skinText);
        tableName.add(nameText).fillX();
        tableStats.add(update).fillX();
        tableStats.row().pad(0, 0, 10, 0);
        tableStats.add(lifeBar).fillX();
        tableStats.row().pad(0, 0, 10, 0);
        tableStats.add(scoredText).fillX();
        tableStats.row().pad(0, 0, 10, 0);
        tableStats.add(moneyText).fillX();
        tableStats.row().pad(0, 0, 10, 0);
        tableStats.add(Player.turretLimitText).fillX();
        tableStats.row().pad(0, 0, 10, 0);

        update.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


                Screen.stage.clear();
                Screen.stage.addActor(UpdateMenu.table);

                Screen.updateMenFlag = true;
                UpdateMenu.update();
            }
        });

        turretColVo = 0;
        turretLimit = 10;
        life = 100;
        lifeBar.setValue(100);
        x = Gdx.graphics.getWidth() / 2;
        y = Gdx.graphics.getHeight() / 2;
        lastSideW = false;
        lastSideA = false;
        lastSideS = true;
        lastSideD = false;

    }

    public void play(Batch batch, TextureAtlas character) {
        playerController();
        changeStats(life);
        Screen.stage.act();
        Screen.stage.draw();
        move(batch, character);
    }

    private void playerController() {

        xThis = ( ( touchpad.getKnobX() - touchpad.getKnobY() ) / Math.sqrt(2) );
        yThis = ( ( touchpad.getKnobX() + touchpad.getKnobY() - 162 ) / Math.sqrt(2) );
        if (( xThis == 0 ) && ( yThis == 0 )) {
            sideA = false;
            sideS = false;
            sideD = false;
            sideW = false;
        } else if (( xThis > 0 ) && ( yThis > 0 )) {
            sideA = false;
            sideS = false;
            sideD = true;
            sideW = false;
        } else if (( xThis > 0 ) && ( yThis < 0 )) {
            sideA = false;
            sideS = true;
            sideD = false;
            sideW = false;
        } else if (( xThis < 0 ) && ( yThis < 0 )) {
            sideA = true;
            sideS = false;
            sideD = false;
            sideW = false;
        } else if (( xThis < 0 ) && ( yThis > 0 )) {
            sideA = false;
            sideS = false;
            sideD = false;
            sideW = true;
        }

    }

    static void move(Batch batch, TextureAtlas character) {
        if (sideW) {
            lastSideW = true;
            lastSideA = false;
            lastSideS = false;
            lastSideD = false;
            if (y < Gdx.graphics.getHeight() - MyGdxGame.playerHeight) {
                y = y + speed;
            } else {
                Screen.yMir += speed;

            }
            if (positionDrawable == 1) {

                drawSprite(batch, character, "wa");
                positionDrawable = 2;
            } else if (positionDrawable == 2) {

                drawSprite(batch, character, "w");

                positionDrawable = 3;
            } else if (positionDrawable == 3) {


                drawSprite(batch, character, "wd");
                positionDrawable = 4;
            } else if (positionDrawable == 4) {

                drawSprite(batch, character, "w");

                positionDrawable = 1;
            }


        } else if (sideA) {
            lastSideW = false;
            lastSideA = true;
            lastSideS = false;
            lastSideD = false;
            if (x > 0) {
                x = x - speed;
            } else {
                Screen.xMir -= speed;
            }
            if (positionDrawable == 1) {

                drawSprite(batch, character, "aa");
                positionDrawable = 2;
            } else if (positionDrawable == 2) {

                drawSprite(batch, character, "a");

                positionDrawable = 3;
            } else if (positionDrawable == 3) {


                drawSprite(batch, character, "ad");
                positionDrawable = 4;
            } else if (positionDrawable == 4) {

                drawSprite(batch, character, "a");

                positionDrawable = 1;
            }

        } else if (sideS) {
            lastSideW = false;
            lastSideA = false;
            lastSideS = true;
            lastSideD = false;
            if (y > 0) {
                y = y - speed;
            } else {

                Screen.yMir -= speed;
            }
            if (positionDrawable == 1) {

                drawSprite(batch, character, "sa");
                positionDrawable = 2;
            } else if (positionDrawable == 2) {

                drawSprite(batch, character, "s");

                positionDrawable = 3;
            } else if (positionDrawable == 3) {


                drawSprite(batch, character, "sd");
                positionDrawable = 4;
            } else if (positionDrawable == 4) {

                drawSprite(batch, character, "s");

                positionDrawable = 1;
            }


        } else if (sideD) {

            lastSideW = false;
            lastSideA = false;
            lastSideS = false;
            lastSideD = true;
            if (x < Gdx.graphics.getWidth() - MyGdxGame.playerWith) {
                x = x + speed;
            } else {
                Screen.xMir += speed;
            }
            if (positionDrawable == 1) {

                drawSprite(batch, character, "da");
                positionDrawable = 2;
            } else if (positionDrawable == 2) {

                drawSprite(batch, character, "d");

                positionDrawable = 3;
            } else if (positionDrawable == 3) {


                drawSprite(batch, character, "dd");
                positionDrawable = 4;
            } else if (positionDrawable == 4) {

                drawSprite(batch, character, "d");

                positionDrawable = 1;
            }

        } else if (lastSideS) {
            drawSprite(batch, character, "s");
        } else if (lastSideW) {
            drawSprite(batch, character, "w");
        } else if (lastSideA) {
            drawSprite(batch, character, "a");
        } else if (lastSideD) {
            drawSprite(batch, character, "d");
        }

    }

    static void drawSprite(Batch batch, TextureAtlas textureAtlas, String name) {


        if (Skins.multiTexture) {
            sprite = textureAtlas.createSprite(Skins.numberMultiTexture + name);
        } else {
            sprite = textureAtlas.createSprite(name);
        }

        sprite.setPosition(x - MyGdxGame.playerCdvig, y);
        sprite.setSize(MyGdxGame.playerWith, MyGdxGame.playerHeight);
        sprite.draw(batch);
        tableName.setPosition(( Gdx.graphics.getWidth() / -2 ) + x - MyGdxGame.playerCdvig + 100, y - Gdx.graphics.getHeight() / 2 - 10);

    }


    static void changeStats(int life) {
        turretLimit = UpdateMenu.turretLimit;
        turretLimitTextString = ( "Turret " + turretColVo + "/" + turretLimit );
        turretLimitText.setText(turretLimitTextString);
        scoredForInt = ( "socer: " + Screen.score );
        scoredText.setText(scoredForInt);
        moneyForInt = ( "money: " + Screen.money );
        moneyText.setText(moneyForInt);

        lifeBar.setValue(life);
    }
}
