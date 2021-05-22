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

public class PetSkins {
    static Skin skin;
   // static Skin skinTexture;
    static Table table = new Table();
    static Table tableTexture = new Table();
    static TextureAtlas pets;
    int colVoSkinsConstant =2;
    String moneyText;
    TextField money;
    static Skin skinText;
    static Table tableManey = new Table();

    public PetSkins() {
//skinTexture = new Skin(Gdx.files.internal("pet.json"));




        skinText = new Skin(Gdx.files.internal("terra-mother/skin/terra-mother-ui.json"));
        table.setPosition(0,Gdx.graphics.getHeight()/-3);
        table.setFillParent(true);
        tableTexture.setPosition(0,Gdx.graphics.getHeight()/-3);
        tableTexture.setFillParent(true);
        tableManey.setPosition(Gdx.graphics.getWidth()/-2+100,Gdx.graphics.getHeight()/2-100);
        tableManey.setFillParent(true);

        moneyText = "money: "+MyGdxGame.preferences.getInteger("money");
        money= new TextField(moneyText,skinText);
        skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        TextButton Pets = new TextButton("Pets", skin);
        TextButton out = new TextButton("out", skin);
        tableManey.add(money).fillX();
        //table.add().fillX();
        table.add(out).fillX();

        table.row().pad(10, 0, 10, 0);

out.addListener(new ChangeListener() {
    @Override
    public void changed(ChangeEvent event, Actor actor) {
       Screen. stage.clear();
        Screen. skinsFlagws = false;
        Screen. stage.addActor(Skins.table);
        Screen. stage.addActor(Skins.tableManey);
        Screen. stage.addActor(Skins.tablePetButton);
        Skins.petRender=false;
    }
});



    }



    public void render(Batch batch){


    }





}
