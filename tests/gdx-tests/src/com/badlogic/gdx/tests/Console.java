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

public class Console extends Group {

  Animation spark, startup, nwork, swork;
  float stateTime = 0;

  TextureRegion currentFrame, currentFrame2, nframe, sframe;
  Sprite nworker, sworker, sparker, startuper, nc, wc, sc, ec;

  public Console(float x, float y, Texture img2, boolean arse, int dir, TextureRegion[][] tmp,
                 Sprite con_off, Animation con_go,
                 Animation nwork, Animation swork, Animation ework, Animation wwork) {
    if (arse) {
      Guy g2 = new Guy(con_off, "conoff");
      switch (dir) {
        case 0:
          g2.setRotation(0);
          break;
        case 1:
          g2.setRotation(90);
          break;
        case 2:
          g2.setRotation(180);
          break;
        case 3:
          g2.setRotation(270);
          break;
      }
      addActor(g2);
    } else {
      Guy g1 = null;
      Guy g2 = null;
      Guy g3 = null;
      switch (dir) {
        case 0:
          g1 = new Guy(con_go, 0, "congo");
          g2 = new Guy(nwork, "nwork");
          g2.setPosition(32, 32);
          break;
        case 1:
          g1 = new Guy(con_go, 90, "congo");
          g1.setSpritePosition(-16, 16);
          g1.moveBy(64, 0);
          g2 = new Guy(wwork, "wwork");
          g2.setPosition(32, 16);
          g3 = new Guy(wwork, "wwork");
          g3.setPosition(32, 48);
          break;
        case 2:
          g1 = new Guy(con_go, 180, "congo");
          g2 = new Guy(swork, "swork");
          g2.setPosition(32, 0);
          break;
        case 3:
          g1 = new Guy(con_go, 270, "congo");
          g1.setSpritePosition(-16, 16);
          g1.moveBy(0, 96);
          g2 = new Guy(ework, "ework");
          g2.setPosition(0, 16);
          g3 = new Guy(ework, "ework");
          g3.setPosition(0, 48);
          break;
      }

      addActor(g1);
      addActor(g2);
      addActor(g3);
    }
    moveBy(x, y);
    addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
        Gdx.app.log("arse", "cons");
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
