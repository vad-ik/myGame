package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
    static TextureAtlas character;
    static Music Music;
    static Screen screen;
    public static float red = 0f;
    public static float blue = 0f;
    public static float green = 0f;
    static Texture load;
    public static int PlayerWith;
    public static int Playerheight;
    public static int PlayerCdvig;

    public static ShapeRenderer sr;
    @Override
    public void create() {
        Music = Gdx.audio.newMusic(Gdx.files.internal("them.ogg"));
        Music.setLooping(true);
        Music.play();
        Music.setVolume(0.5f);
        Playerheight = Gdx.graphics.getHeight()/8;
        PlayerWith = Gdx.graphics.getWidth()/15;
        load = new Texture("loaddd2.jpg");
        character = new TextureAtlas("character.txt");

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        screen = new Screen();
        sr = new ShapeRenderer();
        sr.setColor(Color.RED);
        sr.setProjectionMatrix(MyGdxGame.camera.combined);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(red, green, blue, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();  sr.begin(ShapeRenderer.ShapeType.Filled);
        screen.render(batch, character, load);





        batch.end();
        sr.end();
    }




    @Override
    public void dispose() {
        batch.dispose();

        Wrag1.wrag1.dispose();
        Wrag1.wrag2.dispose();
        Wrag1.wrag3.dispose();
        character.dispose();
        load.dispose();
        screen.backgraund.dispose();
        Player.skinText.dispose();
        Player.skin.dispose();
        Screen.skin.dispose();
        Skins.character1.dispose();
        Skins.character2.dispose();
        Skins.skin.dispose();
        UpdateMenu.skin.dispose();

    }
}
