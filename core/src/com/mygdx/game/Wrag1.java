package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Random;

public class Wrag1 {
    static int x;
    static int y;

    static int dmg = 0;
    static boolean sideW;
    static boolean sideA;
    static boolean sideS;
    static boolean sideD;
    static String tip;
    static TextureAtlas wrag1 = new TextureAtlas("wrag1.txt");
    static TextureAtlas wrag2 = new TextureAtlas("wrag2.txt");
    static TextureAtlas wrag3 = new TextureAtlas("wrag3.txt");
    static TextureAtlas wrag4 = new TextureAtlas("wrag4.txt");
    static int speed = (int) ( Gdx.graphics.getHeight() * Gdx.graphics.getDeltaTime() );
    static Sprite sprite;
    static Random random = new Random();

    public Wrag1() {


        sideW = false;
        sideA = false;
        sideS = false;
        sideD = false;

    }

    public static void move(Batch batch, int xx, int yy, int index, int tipTexture) {
        x = xx;
        y = yy;
        tip = String.valueOf(tipTexture);


        wragAI(index);

        if (sideW) {

            Screen.wragY.set(index, y + speed);

            if (Screen.wragFlag.get(index) == 1) {

                drawSprite(batch, "w", index);
                Screen.wragFlag.set(index, 2);
            } else if (Screen.wragFlag.get(index) == 2) {

                drawSprite(batch, "w (2)", index);

                Screen.wragFlag.set(index, 3);
            } else if (Screen.wragFlag.get(index) == 3) {


                drawSprite(batch, "w (3)", index);
                Screen.wragFlag.set(index, 4);
            } else if (Screen.wragFlag.get(index) == 4) {

                drawSprite(batch, "w (2)", index);

                Screen.wragFlag.set(index, 1);
            }


        } else if (sideA) {

            Screen.wragX.set(index, x - speed);

            if (Screen.wragFlag.get(index) == 1) {

                drawSprite(batch, "a", index);
                Screen.wragFlag.set(index, 2);
            } else if (Screen.wragFlag.get(index) == 2) {

                drawSprite(batch, "a (2)", index);

                Screen.wragFlag.set(index, 3);
            } else if (Screen.wragFlag.get(index) == 3) {


                drawSprite(batch, "a (3)", index);
                Screen.wragFlag.set(index, 4);
            } else if (Screen.wragFlag.get(index) == 4) {

                drawSprite(batch, "a (2)", index);

                Screen.wragFlag.set(index, 1);
            }


        } else if (sideS) {

            Screen.wragY.set(index, y - speed);


            if (Screen.wragFlag.get(index) == 1) {

                drawSprite(batch, "s", index);
                Screen.wragFlag.set(index, 2);
            } else if (Screen.wragFlag.get(index) == 2) {

                drawSprite(batch, "s (2)", index);

                Screen.wragFlag.set(index, 3);
            } else if (Screen.wragFlag.get(index) == 3) {


                drawSprite(batch, "s (3)", index);
                Screen.wragFlag.set(index, 4);
            } else if (Screen.wragFlag.get(index) == 4) {

                drawSprite(batch, "s (2)", index);

                Screen.wragFlag.set(index, 1);
            }


        } else if (sideD) {

            Screen.wragX.set(index, x + speed);
            if (Screen.wragFlag.get(index) == 1) {

                drawSprite(batch, "d", index);
                Screen.wragFlag.set(index, 2);
            } else if (Screen.wragFlag.get(index) == 2) {

                drawSprite(batch, "d (2)", index);

                Screen.wragFlag.set(index, 3);
            } else if (Screen.wragFlag.get(index) == 3) {


                drawSprite(batch, "d (3)", index);
                Screen.wragFlag.set(index, 4);
            } else if (Screen.wragFlag.get(index) == 4) {

                drawSprite(batch, "d (2)", index);

                Screen.wragFlag.set(index, 1);
            }

        }

        if (Screen.wragLive.get(index) <= 0) {
            Screen.wragLive.remove(index);
            Screen.wragFlag.remove(index);
            Screen.wragY.remove(index);
            Screen.wragX.remove(index);
            if (Screen.wragStrong.get(index) == 1) {
                Screen.spawnController += 5;
                Screen.money += 1;
                Screen.score++;
            } else if (Screen.wragStrong.get(index) == 2) {
                Screen.spawnController += 25;
                Screen.money += 20;
                Screen.score += 5;
            } else if (Screen.wragStrong.get(index) == 3) {
                Screen.spawnController += 35;
                Screen.money += 50;
                Screen.score += 10;
            } else if (Screen.wragStrong.get(index) == 4) {
                Screen.spawnController += 255;
                Screen.money += 100;
                Screen.score += 30;
            } else if (Screen.wragStrong.get(index) == 5) {
                Screen.spawnController += 15;
                Screen.money += 2;
                Screen.score += 2;
            } else if (Screen.wragStrong.get(index) == 6) {
                Screen.spawnController += 35;
                Screen.money += 30;
                Screen.score += 10;
            } else if (Screen.wragStrong.get(index) == 7) {
                Screen.spawnController += 25;
                Screen.money += 25;
                Screen.score += 5;
            }
            Screen.wragStrong.remove(index);
            Screen.wragTextureWid.remove(index);


        }


    }

    private static void drawSprite(Batch batch, String name, int index) {

        sprite = wrag1.createSprite(tip);



        if (Screen.wragStrong.get(index) == 1) {
            sprite = wrag1.createSprite(tip + name);
        } else if (Screen.wragStrong.get(index) == 2) {
            sprite = wrag2.createSprite(tip + name);
        } else if (Screen.wragStrong.get(index) == 3) {
            sprite = wrag3.createSprite(tip + name);
        } else if (Screen.wragStrong.get(index) == 4) {
            name=transformator(name);
            sprite = wrag4.createSprite(tip + name);
        } else if (Screen.wragStrong.get(index) == 5) {
            name=transformator(name);
            sprite = wrag4.createSprite(tip + name);
        } else if (Screen.wragStrong.get(index) == 6) {
            name=transformator(name);
            sprite = wrag4.createSprite(tip + name);
        } else if (Screen.wragStrong.get(index) == 7) {
            name=transformator(name);
            sprite = wrag4.createSprite(tip + name);
        }


        sprite.setPosition(x - Screen.xMir, y - Screen.yMir);


        sprite.setSize(Gdx.graphics.getWidth() / 14, Gdx.graphics.getHeight() / 12);
        if (Screen.wragStrong.get(index) == 2) {
            sprite.setSize(Gdx.graphics.getWidth() / 14, Gdx.graphics.getHeight() / 6);
        }
        if (Screen.wragStrong.get(index) == 3) {
            sprite.setSize(Gdx.graphics.getWidth() / 14, Gdx.graphics.getHeight() / 6);
        }


        sprite.draw(batch);


    }
static String transformator(String n){
        String name="s";
    switch (n){
        case ("d"):
            name="da";
            break;
        case ("d (2)"):
            name="d";
            break;
        case ("d (3)"):
            name="dd";
            break;
        case ("s"):
            name="sa";
            break;
        case ("s (2)"):
            name="s";
            break;
        case ("s (3)"):
            name="sd";
            break;
        case ("a"):
            name="aa";
            break;
        case ("a (2)"):
            name="a";
            break;
        case ("a (3)"):
            name="ad";
            break;
        case ("w"):
            name="wa";
            break;
        case ("w (2)"):
            name="w";
            break;
        case ("w (3)"):
            name="wd";
            break;

    }
    return name;
};
    static void wragAI(int index) {
        sideA = false;
        sideW = false;
        sideS = false;
        sideD = false;

        int xR = x - Player.x - Screen.xMir;
        int yR = y - Player.y - Screen.yMir;
        if (( xR < 33 ) && ( xR > -33 ) && ( yR < 33 ) && ( yR > -33 )) {

            Player.life--;

        }

        for (int i = 0; i < Screen.turetArray.size(); i++) {

            int xRT = x - Screen.turetArray.get(i).cordX + Screen.xMir;
            int yRT = y - Screen.turetArray.get(i).cordY + Screen.yMir;
            if (( xRT < 10 ) && ( xRT > -10 ) && ( yRT < 10 ) && ( yRT > -10 )) {

                Screen.turetArray.get(i).fight(i);


            }
            if (Screen.wragLive.get(index) > 0) {
                dmg = Screen.turetArray.get(i).shot(x, y);

            }
            if (dmg > 0) {

                if (( Screen.wragLive.get(index) - dmg ) == 0) {
                    Screen.wragLive.set(index, 0);

                } else {
                    Screen.wragLive.set(index, ( Screen.wragLive.get(index) ) - dmg);
                }
            }
        }


        int randNapr = random.nextInt(2);

        if (( xR + 8 <= 23 ) && ( xR - 8 >= -23 ) && ( yR < 0 )) {
            sideW = true;
        } else if (( xR + 8 <= 23 ) && ( xR - 8 >= -23 ) && ( yR > 0 )) {
            sideS = true;
        } else if (( yR + 8 <= 23 ) && ( yR - 8 >= -23 ) && ( xR < 0 )) {
            sideD = true;
        } else if (( yR + 8 <= 23 ) && ( yR - 8 >= -23 ) && ( xR > 0 )) {
            sideA = true;
        } else if (( yR > 0 ) && ( xR > 0 )) {
            if (randNapr == 0) {
                sideA = true;
            } else {
                sideS = true;
            }
        } else if (( yR > 0 ) && ( xR < 0 )) {
            if (randNapr == 0) {
                sideD = true;
            } else {
                sideS = true;
            }

        } else if (( yR <= 0 ) && ( xR > 0 )) {
            if (randNapr == 0) {
                sideW = true;
            } else {
                sideA = true;
            }
        } else if (( yR <= 0 ) && ( xR < 0 )) {
            if (randNapr == 0) {
                sideD = true;
            } else {
                sideW = true;
            }
        }


    }


}




