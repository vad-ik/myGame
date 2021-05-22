package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Turet {

    static int dmg;
    static int range;
    static float timer;
    int cordX;
    int cordY;
    float reloading = 0;
    double rotation = 0;
    String name;
    Sprite sprite;
    TextureAtlas turetTexture;

    public Turet(int cordX, int cordY) {
        Screen.turetHP.add(UpdateMenu.xp);
        dmg = UpdateMenu.dmg;
        range = UpdateMenu.range;
        this.cordX = cordX;
        this.cordY = cordY;
        timer = UpdateMenu.timer;
        this.turetTexture = new TextureAtlas("TuretAtlas.txt");


        this.reloading = 0;
    }

    int shot(int x, int y) {
        int retur = 0;
        if (( cordY - y < range ) && ( cordY - y > -1 * range ) && ( reloading == 0 ) && ( cordX - x < range ) && ( cordX - x > -1 * range )) {
            retur = dmg;
            reloading = timer;

            rotation = Math.atan2(cordY - y, cordX - x) / Math.PI * 180;
            rotation = ( rotation < 0 ) ? rotation + 180 : rotation;
            rotation += 180;
            if (rotation >= 360) {
                rotation -= 360;
            }

           // MyGdxGame.sr.rectLine(cordX + 10, cordY + 10, x + 10, y + 10, 1f);


        }

        return retur;
    }

    void fight(int index) {
        Screen.turetHP.set(index, Screen.turetHP.get(index) - 1);

    }


    void draw(Batch batch, int index) {

        if (Screen.turetHP.get(index) <= 0) {
            Screen.turetArray.remove(index);
            Screen.turetHP.remove(index);
            Player.turretColVo--;

        } else {

            name = String.valueOf((int) ( rotation / 30 ) * 30);
            sprite = turetTexture.createSprite(name);

            sprite.setPosition(cordX - Screen.xMir, cordY - Screen.yMir);
            sprite.setSize(Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
            sprite.draw(batch);
        }
        if (reloading > 0) {

            reloading--;
        }
    }
}
