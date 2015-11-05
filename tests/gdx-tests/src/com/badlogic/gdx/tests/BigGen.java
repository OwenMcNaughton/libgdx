package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.graphics.g2d.Batch;

public class BigGen extends Group {

  Animation spark, startup, nwork, swork;
  float stateTime = 0;

  TextureRegion currentFrame, currentFrame2, nframe, sframe;
  Sprite nworker, sworker, sparker, startuper, nc, wc, sc, ec;

  public BigGen(float x, float y, Texture img2, boolean arse, int rot,
                  Animation gen_start, Animation gen_go,
                  Animation nwork, Animation swork, Animation ework, Animation wwork, Texture shield) {
    if (arse) {
      Guy g2 = new Guy(gen_start, "bigGenstart");
      g2.setPosition(0, 0);
      addActor(g2);
    } else {
      Guy g1 = new Guy(gen_go, "biggengo");
      g1.setPosition(0, 0);

      Guy g4 = new Guy(nwork, "nwork");
      g4.setPosition(96, 0);

      Guy g6 = new Guy(swork, "swork");
      g6.setPosition(96, 128);

      addActor(g1);
      addActor(g4);
      addActor(g6);

      Sprite sss = new Sprite(shield);
      Guy ga = new Guy(sss, "shield");
      ga.setAlpha(true);
      ga.setScale(2);
      ga.setSpritePosition(-512 + 96 + 16, -512 + 64 + 16);
      ga.setOrigin(sss.getOriginX(), sss.getOriginY());
      ga.moveBy(-512 + 96 + 16, -512 + 64 + 16);
      addActor(ga);
    }
    
    moveBy(x, y);
    addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
        Gdx.app.log("arse", "biggen");
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
