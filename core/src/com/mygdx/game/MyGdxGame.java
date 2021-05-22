package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    static OrthographicCamera camera;
    static int playerCdvig;
    static TextureAtlas character;
    static Music music;
    static Screen screen;
    public static float red = 0f;
    public static float blue = 0f;
    public static float green = 0f;
    static Texture load;
    public static int playerWith;
    public static int playerHeight;
    public static Preferences preferences;
    public static ShapeRenderer sr;

    @Override
    public void create() {
        preferences = Gdx.app.getPreferences("name");
        preferences = Gdx.app.getPreferences("soundOff");
        preferences = Gdx.app.getPreferences("money");
        music = Gdx.audio.newMusic(Gdx.files.internal("them.ogg"));
        music.setLooping(true);
        music.play();

        playerHeight = Gdx.graphics.getHeight() / 8;
        playerWith = Gdx.graphics.getWidth() / 15;
        load = new Texture("loaddd2.jpg");
        character = new TextureAtlas("character.txt");

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        screen = new Screen();
        sr = new ShapeRenderer();

        if (preferences.getBoolean("soundOff") == true) {
            music.setVolume(0);
            screen.musicOff.setText("Music On");
        } else {
            music.setVolume(0.5f);
        }


        if (!preferences.getString("name").equals("")) {
            Screen.name.setText(preferences.getString("name"));
        } else {
            Screen.name.setText("name");
        }


        Screen.dead();
        Screen.startFlag = true;
        Screen.skinsFlag = false;
        Screen.updateMenFlag = false;

    }


    @Override
    public void render() {
        sr.setColor(Color.RED);
        sr.setProjectionMatrix(MyGdxGame.camera.combined);
        Gdx.gl.glClearColor(red, green, blue, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        screen.render(batch, character, load);
        batch.end();
        sr.end();
        preferences.flush();
    }


    @Override
    public void dispose() {
        batch.dispose();
        Wrag1.wrag1.dispose();
        Wrag1.wrag2.dispose();
        Wrag1.wrag3.dispose();
        character.dispose();
        load.dispose();
        Screen.backgraund.dispose();
        Player.skinText.dispose();
        Player.skin.dispose();
        Screen.skin.dispose();
        Skins.character1.dispose();
        Skins.character2.dispose();
        Skins.character4.dispose();
        Skins.skin.dispose();
        UpdateMenu.skin.dispose();
        Screen.stage.dispose();
        sr.dispose();
        Skins.skinText.dispose();
        Screen.table.clear();
        Screen.table2Levl.clear();
        Skins.table.clear();
        Skins.tableManey.clear();
        Player.tableName.clear();
        Player.tableStats.clear();
        Player.tableControl.clear();
    }
}
