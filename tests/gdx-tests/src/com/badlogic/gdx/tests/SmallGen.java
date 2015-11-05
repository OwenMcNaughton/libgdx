package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class SmallGen extends Group {

  Animation spark, startup, nwork, swork;
  float stateTime = 0;

  TextureRegion currentFrame, currentFrame2, nframe, sframe;
  Sprite nworker, sworker, sparker, startuper, nc, wc, sc, ec;

  public SmallGen(float x, float y, Texture img2, boolean arse, int rot,
                  Animation gen_start, Animation gen_go,
                  Animation nwork, Animation swork, Animation ework, Animation wwork, Texture shield) {
    if (arse) {
      if (rot == 0) {
        Guy g2 = new Guy(gen_start, "smallGenstart");
        g2.setPosition(0, 0);
        addActor(g2);
      } else {
        Guy g2 = new Guy(gen_start, 90, "smallGenstart");
        g2.setSpritePosition(16, 16);
        g2.moveBy(96, 32);
        addActor(g2);
      }
    } else {
      if (rot == 0) {
        Guy g1 = new Guy(gen_go, "smallgengo");
        g1.setPosition(0, 0);

        Guy g4 = new Guy(ework, "ework");
        g4.setPosition(0, 32);

        Guy g6 = new Guy(wwork, "wwork");
        g6.setPosition(32, 32);

        addActor(g1);
        addActor(g4);
        addActor(g6);

        Sprite sss = new Sprite(shield);
        sss.scale(.03f);
        Guy ga = new Guy(sss, "shield");
        ga.setSpritePosition(-512 + 16, -512 + 32 + 16);
        //addActor(ga);
      } else {
        Guy g1 = new Guy(gen_go, 90, "smallgengo");
        g1.setPosition(16, 16);
        g1.moveBy(80, 16);

        Guy g4 = new Guy(nwork, "nwork");
        g4.setSpritePosition(32, 32);
        g4.moveBy(32, 64);

        Guy g6 = new Guy(swork, "swork");
        g6.setSpritePosition(32, 64);
        g6.moveBy(32, 32);

        addActor(g1);
        addActor(g4);
        addActor(g6);

        Sprite sss = new Sprite(shield);
        sss.scale(.03f);
        Guy ga = new Guy(sss, "shield");
        ga.setSpritePosition(-512 + 32 + 16, -512 + 32 + 16);
        //addActor(ga);
      }
    }
    moveBy(x, y);
    addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
        Gdx.app.log("arse", "smallgen");
        return false;
      }
    });
  }

  @Override
  protected void positionChanged() {
    super.positionChanged();
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
  }

  @Override
  public void act(float delta) {
    super.act(delta);
  }

}
