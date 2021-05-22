package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

import java.util.Random;

public class Screen {
    static int dificultNow = 1;
    static int money;
    static boolean updateMenFlag;
    static int score;
    static Stage stage;
    static boolean startFlag = true;
    static Player player;
    static int spawnController = 10;
    static TextButton newGame;
    static TextButton difficult;
    static Table table = new Table();
    static Table table2Levl = new Table();
    static ArrayList<Integer> wragX = new ArrayList<>();
    static ArrayList<Integer> wragY = new ArrayList<>();
    static ArrayList<Integer> wragTextureWid = new ArrayList<>();
    static ArrayList<Integer> wragFlag = new ArrayList<>();
    static ArrayList<Integer> turetHP = new ArrayList<>();
    static int xMir;
    static int yMir;
    static ArrayList<Integer> wragLive = new ArrayList<>();
    static ArrayList<Integer> wragStrong = new ArrayList<>();

    static UpdateMenu updateMenu;

    static ArrayList<Turet> turetArray = new ArrayList<>();
    static Skin skin;
    static Texture backgraund = new Texture("back.jpg");
    static int totalMoney;
    static boolean skinsFlag = false;
    static boolean skinsFlagws = true;
    static Skins skins = new Skins();
    static TextField name;
    TextButton musicOff;
    Random random = new Random();

    public Screen() {

        skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));
        name = new TextField("player", skin);
        player = new Player();
        updateMenu = new UpdateMenu();
        score = 0;
        money = 60;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table.setPosition(0, -Gdx.graphics.getWidth() / 15);
        table.setFillParent(true);
        table2Levl.setPosition(0, -Gdx.graphics.getWidth() / 15 - 120);
        table2Levl.setFillParent(true);
        newGame = new TextButton("New Game", skin);
        difficult = new TextButton("medium", skin);
        TextButton skins = new TextButton("Skins", skin);
        musicOff = new TextButton("Music off", skin);
        table.add(newGame).fillX();
        table.row().pad(10, 0, 10, 0);
        table2Levl.add(musicOff);
        table2Levl.add(skins);
        table2Levl.add(name).fillY();
        table2Levl.add(difficult);
        stage.addActor(table);
        stage.addActor(table2Levl);
        difficult.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (dificultNow == 1) {
                    difficult.setText("hard");
                    dificultNow = 2;
                    UpdateMenu.updateCost = 20;
                    spawnController = 50;
                } else if (dificultNow == 2) {
                    difficult.setText("easy");
                    spawnController = 1;
                    dificultNow = 3;
                    UpdateMenu.updateCost = 5;
                } else if (dificultNow == 3) {
                    difficult.setText("medium");
                    dificultNow = 1;
                    spawnController = 10;
                    UpdateMenu.updateCost = 10;
                }
            }
        });

        musicOff.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (MyGdxGame.music.getVolume() != 0) {
                    MyGdxGame.music.setVolume(0);
                    MyGdxGame.preferences.putBoolean("soundOff", false);

                    musicOff.setText("Music on");
                } else {


                    MyGdxGame.music.setVolume(0.5f);

                    MyGdxGame.preferences.putBoolean("soundOff", true);
                    musicOff.setText("Music off");
                }
            }
        });
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                stage.clear();

                stage.addActor(Player.tableStats);
                stage.addActor(Player.tableControl);
                stage.addActor(Player.tableName);
                startFlag = false;
                MyGdxGame.red = 1f;
                MyGdxGame.green = 0.5f;
                MyGdxGame.blue = 0f;
                Player.nameText.setText(name.getText());
            }
        });
        skins.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                skinsFlag = true;

            }
        });
    }


    void render(Batch batch, TextureAtlas character, Texture load) {

        MyGdxGame.preferences.putString("name", name.getText());

        MyGdxGame.preferences.putInteger("money", money);

        if (skinsFlag) {
            skinsChangeMenu(batch);
        }
        if (Player.life <= 0) {
            dead();
        }

        if (( startFlag ) && ( !skinsFlag )) {
            startMenu(batch, load);
        } else if (( !skinsFlag )) {
            if (spawnController > 0) {
                if (( spawnController < 30 ) && ( wragX.size() < 10 )) {
                    wragGenerate();
                    wrag5Generator();
                } else if (( ( spawnController < 100 ) && ( wragX.size() < 50 ) )) {
                    wragGenerate();
                    wrag2Generator();
                    wrag6Generator();
                } else if (( ( spawnController < 300 ) && ( wragX.size() < 400 ) )) {
                    wragGenerate();
                    wrag2Generator();
                    wrag3Generator();
                    wrag7Generator();
                } else if (( ( spawnController < 500 ) && ( wragX.size() < 10000 ) )) {
                    wragGenerate();
                    wrag2Generator();
                    wrag3Generator();
                    wrag4Generator();
                }

            }
            if (updateMenFlag) {
                updateMenu.render();
            } else {
                play(batch, character);
            }
        }


    }

    public static void dead() {
        totalMoney += money;
        stage.clear();
        startFlag = true;
        newGame.setText("start a new game?");
        MyGdxGame.blue = 0f;
        MyGdxGame.green = 0f;
        MyGdxGame.red = 0f;
        Player.life = 100;
        Player.x = Gdx.graphics.getWidth() / 2;
        Player.y = Gdx.graphics.getHeight() / 2;
        spawnController = 10;
        wragStrong.clear();
        wragX.clear();
        wragY.clear();
        wragFlag.clear();
        wragTextureWid.clear();
        wragLive.clear();
        turetArray.clear();
        stage.addActor(table);
        stage.addActor(table2Levl);
        turetHP.clear();
        xMir = 0;
        yMir = 0;
        money = 60;
        Player.turretColVo = 0;
        updateMenu = new UpdateMenu();


    }

    void play(Batch batch, TextureAtlas character) {


        batch.draw(backgraund, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        for (int i = 0; i < wragX.size(); i++) {

            Wrag1.move(batch, wragX.get(i), wragY.get(i), i, wragTextureWid.get(i));
        }

        for (int i = 0; i < turetArray.size(); i++) {

            turetArray.get(i).draw(batch, i);
        }


        player.play(batch, character);
        try {
            Thread.sleep(( 100 ));
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    static void startMenu(Batch batch, Texture load) {
        batch.draw(load, Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 18 * 2, Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth() / 18 * 4, Gdx.graphics.getWidth() / 18 * 4);
        stage.act();
        stage.draw();

    }


    void wragGenerate() {

        spawnController -= 1;

        int position_x = random.nextInt(200);
        int position_y = random.nextInt(200);
        int wragTextur = random.nextInt(8) + 1;
        if (position_x < 100) {
            position_x = -80 - position_x + xMir;
        } else {
            position_x = Gdx.graphics.getHeight() + position_x + xMir;
        }
        if (position_y < 100) {
            position_y = -60 - position_y + yMir;
        } else {
            position_y = Gdx.graphics.getWidth() + position_y + yMir;
        }
        wragTextureWid.add(wragTextur);
        wragFlag.add(1);
        wragX.add(position_x);
        wragY.add(position_y);
        wragLive.add(1);
        wragStrong.add(1);

    }

    void wrag2Generator() {

        spawnController -= 10;

        int position_x = random.nextInt(200);
        int position_y = random.nextInt(200);
        int wragTextur = random.nextInt(2) + 1;
        if (position_x < 100) {
            position_x = -80 - position_x + xMir;
        } else {
            position_x = Gdx.graphics.getHeight() + position_x + xMir;
        }
        if (position_y < 100) {
            position_y = -60 - position_y + yMir;
        } else {
            position_y = Gdx.graphics.getWidth() + position_y + yMir;
        }

        wragTextureWid.add(wragTextur);
        wragFlag.add(1);
        wragX.add(position_x);
        wragY.add(position_y);
        wragLive.add(10);
        wragStrong.add(2);


    }

    void wrag3Generator() {

        spawnController -= 100;

        int position_x = random.nextInt(200);
        int position_y = random.nextInt(200);
        int wragTextur = random.nextInt(8) + 1;
        if (position_x < 100) {
            position_x = -80 - position_x + xMir;
        } else {
            position_x = Gdx.graphics.getHeight() + position_x + xMir;
        }
        if (position_y < 100) {
            position_y = -60 - position_y + yMir;
        } else {
            position_y = Gdx.graphics.getWidth() + position_y + yMir;
        }

        wragTextureWid.add(wragTextur);
        wragFlag.add(1);
        wragX.add(position_x);
        wragY.add(position_y);
        wragLive.add(10);
        wragStrong.add(3);


    }

    void wrag4Generator() {

        spawnController -= 200;

        int position_x = random.nextInt(200);
        int position_y = random.nextInt(200);
        int wragTextur = random.nextInt(4) + 1;
        if (position_x < 100) {
            position_x = -80 - position_x + xMir;
        } else {
            position_x = Gdx.graphics.getHeight() + position_x + xMir;
        }
        if (position_y < 100) {
            position_y = -60 - position_y + yMir;
        } else {
            position_y = Gdx.graphics.getWidth() + position_y + yMir;
        }

        wragTextureWid.add(wragTextur);
        wragFlag.add(1);
        wragX.add(position_x);
        wragY.add(position_y);
        wragLive.add(200);
        wragStrong.add(4);


    }

    void wrag5Generator() {

        spawnController -= 2;

        int position_x = random.nextInt(200);
        int position_y = random.nextInt(200);
        int wragTextur = random.nextInt(2) + 5;
        if (position_x < 100) {
            position_x = -80 - position_x + xMir;
        } else {
            position_x = Gdx.graphics.getHeight() + position_x + xMir;
        }
        if (position_y < 100) {
            position_y = -60 - position_y + yMir;
        } else {
            position_y = Gdx.graphics.getWidth() + position_y + yMir;
        }

        wragTextureWid.add(wragTextur);
        wragFlag.add(1);
        wragX.add(position_x);
        wragY.add(position_y);
        wragLive.add(2);
        wragStrong.add(5);


    }

    void wrag6Generator() {

        spawnController -= 50;

        int position_x = random.nextInt(200);
        int position_y = random.nextInt(200);
        int wragTextur = 7;
        if (position_x < 100) {
            position_x = -80 - position_x + xMir;
        } else {
            position_x = Gdx.graphics.getHeight() + position_x + xMir;
        }
        if (position_y < 100) {
            position_y = -60 - position_y + yMir;
        } else {
            position_y = Gdx.graphics.getWidth() + position_y + yMir;
        }

        wragTextureWid.add(wragTextur);
        wragFlag.add(1);
        wragX.add(position_x);
        wragY.add(position_y);
        wragLive.add(30);
        wragStrong.add(6);


    }

    void wrag7Generator() {

        spawnController -= 40;

        int position_x = random.nextInt(200);
        int position_y = random.nextInt(200);
        int wragTextur = random.nextInt(2) + 8;
        if (position_x < 100) {
            position_x = -80 - position_x + xMir;
        } else {
            position_x = Gdx.graphics.getHeight() + position_x + xMir;
        }
        if (position_y < 100) {
            position_y = -60 - position_y + yMir;
        } else {
            position_y = Gdx.graphics.getWidth() + position_y + yMir;
        }

        wragTextureWid.add(wragTextur);
        wragFlag.add(1);
        wragX.add(position_x);
        wragY.add(position_y);
        wragLive.add(20);
        wragStrong.add(7);


    }

    static void skinsChangeMenu(Batch batch) {

        if (skinsFlagws) {
            stage.clear();
            skinsFlagws = false;
            stage.addActor(Skins.table);
            stage.addActor(Skins.tableManey);
            stage.addActor(Skins.tablePetButton);
        }

        skins.render(batch);

    }

}
