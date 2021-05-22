package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Skins {
    static Skin skin;
    static Table table = new Table();
    static Table tablePetButton = new Table();
    static Table tableManey = new Table();
    static TextureAtlas character1;
    static TextureAtlas character2;
    static TextureAtlas character4;
    static boolean multiTexture = false;
    static int numberMultiTexture = 1;
    int colVoSkinsConstant = 3;
    String moneyText;
    TextField money;
    static Skin skinText;
    PetSkins petSkins;
    Sprite sprite;
    static Boolean petRender = false;

    public Skins() {
        petSkins = new PetSkins();
        skinText = new Skin(Gdx.files.internal("terra-mother/skin/terra-mother-ui.json"));
        tablePetButton.setPosition(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 100);
        tablePetButton.setFillParent(true);

        table.setPosition(0, Gdx.graphics.getHeight() / -3);
        table.setFillParent(true);
        tableManey.setPosition(Gdx.graphics.getWidth() / -2 + 100, Gdx.graphics.getHeight() / 2 - 100);
        tableManey.setFillParent(true);
        moneyText = "money: " + MyGdxGame.preferences.getInteger("money");
        money = new TextField(moneyText, skinText);
        character1 = new TextureAtlas("character.txt");
        character2 = new TextureAtlas("character2.txt");
        character4 = new TextureAtlas("character4.txt");
        skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));
        TextButton skin1 = new TextButton("set", skin);
        TextButton skin2 = new TextButton("set", skin);
        TextButton skin3 = new TextButton("set", skin);
        TextButton out = new TextButton("out", skin);
        TextButton Pets = new TextButton("Pets", skin);
      //  tablePetButton.add(Pets).fillX();
        tableManey.add(money).fillX();
        table.add(out).fillX();
        table.add(skin1).fillX();
        table.add(skin2).fillX();
        table.add(skin3).fillX();
        table.row().pad(10, 0, 10, 0);

//        Pets.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                Screen.stage.clear();
//                Screen.stage.addActor(petSkins.table);
//                Screen.stage.addActor(petSkins.tableManey);
//                // Screen.stage.addActor(petSkins.tableTexture);
//                petRender = true;
//
//            }
//        });


        skin1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MyGdxGame.character = character1;
                MyGdxGame.playerWith = Gdx.graphics.getWidth() / 15;
                multiTexture = false;
                MyGdxGame.playerHeight = Gdx.graphics.getHeight() / 8;
                MyGdxGame.playerCdvig = 0;
            }
        });
        skin2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MyGdxGame.character = new TextureAtlas("character2.txt");
                MyGdxGame.playerWith = Gdx.graphics.getWidth() / 10;
                MyGdxGame.playerHeight = Gdx.graphics.getHeight() / 5;
                MyGdxGame.playerCdvig = 30;
                multiTexture = false;
            }
        });
        skin3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MyGdxGame.character = new TextureAtlas("character4.txt");
                MyGdxGame.playerWith = Gdx.graphics.getWidth() / 15;
                multiTexture = true;
                numberMultiTexture = 1;
                MyGdxGame.playerHeight = Gdx.graphics.getHeight() / 8;
                MyGdxGame.playerCdvig = 30;
            }
        });

        out.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Screen.skinsFlag = false;
                Screen.stage.clear();
                Screen.skinsFlagws = true;
                Screen.stage.addActor(Screen.table);
                Screen.stage.addActor(Screen.table2Levl);
            }
        });


    }

    void render(Batch batch) {


        Screen.stage.act();
        Screen.stage.draw();

        if (!petRender) {
            sprite = character1.createSprite("s");
            sprite.setPosition(Gdx.graphics.getWidth() / 2 + 80 * ( colVoSkinsConstant + 1 ) / 2 - 80 * ( colVoSkinsConstant - 0 ), Gdx.graphics.getHeight() / -3 + Gdx.graphics.getHeight() / 3 * 2);
            sprite.setSize(100, 170);
            sprite.draw(batch);

            sprite = character2.createSprite("s");
            sprite.setPosition(Gdx.graphics.getWidth() / 2 + 80 * ( colVoSkinsConstant + 1 ) / 2 - 80 * ( colVoSkinsConstant - 1 ), Gdx.graphics.getHeight() / -3 + Gdx.graphics.getHeight() / 3 * 2);
            sprite.setSize(160, 170);
            sprite.draw(batch);

            sprite = character4.createSprite("1s");
            sprite.setPosition(Gdx.graphics.getWidth() / 2 + 80 * ( colVoSkinsConstant + 1 ) / 2 - 80 * ( colVoSkinsConstant - 3 ), Gdx.graphics.getHeight() / -3 + Gdx.graphics.getHeight() / 3 * 2);
            sprite.setSize(100, 170);
            sprite.draw(batch);


        } else {
            // petSkins.render(batch);
        }

    }
}
