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

import java.util.Random;

public class Reactor extends Group {

  Animation nwork, swork;
  float stateTime = 0;

  TextureRegion currentFrame, currentFrame2, nframe, sframe;
  Sprite nworker, sworker, nc, wc, sc, ec;
  static Animation startup;
  static Sprite on;
  static Animation[] sparks;

  public static void Init(TextureRegion[][] tmp) {
    sparks = new Animation[7];

    on = new Sprite(tmp[0][0]);

    TextureRegion[] frames2 = new TextureRegion[24];
    for (int i = 0; i != 10; i++) {
      frames2[i] = tmp[1][0];
    }
    for (int i = 14; i != 24; i++) {
      frames2[i] = tmp[0][0];
    }
    frames2[10] = tmp[1][0];
    frames2[11] = tmp[1][1];
    frames2[12] = tmp[1][2];
    frames2[13] = tmp[1][3];
    startup = new Animation(0.1f, frames2);
    startup.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

    TextureRegion[] f1 = new TextureRegion[4];
    f1[0] = tmp[0][1]; f1[1] = tmp[0][2]; f1[2] = tmp[0][3]; f1[3] = tmp[0][4];
    sparks[0] = new Animation(0.06f, f1);

    f1 = new TextureRegion[4];
    f1[0] = tmp[0][5]; f1[1] = tmp[0][6]; f1[2] = tmp[0][7]; f1[3] = tmp[0][8];
    sparks[1] = new Animation(0.06f, f1);

    f1 = new TextureRegion[4];
    f1[0] = tmp[0][9]; f1[1] = tmp[0][8]; f1[2] = tmp[0][7]; f1[3] = tmp[0][6];
    sparks[2] = new Animation(0.06f, f1);

    f1 = new TextureRegion[4];
    f1[0] = tmp[0][5]; f1[1] = tmp[0][4]; f1[2] = tmp[0][3]; f1[3] = tmp[0][2];
    sparks[3] = new Animation(0.06f, f1);

    f1 = new TextureRegion[4];
    f1[0] = tmp[0][1]; f1[1] = tmp[0][0]; f1[2] = tmp[0][0]; f1[3] = tmp[0][5];
    sparks[4] = new Animation(0.06f, f1);

    f1 = new TextureRegion[9];
    for (int i = 1 ; i != 10; i++) f1[i-1] = tmp[0][i];
    sparks[5] = new Animation(0.06f, f1);

    f1 = new TextureRegion[9];
    int j = 0;
    for (int i = 9; i != 0; i--) {

      f1[j++] = tmp[0][i];
    }
    sparks[6] = new Animation(0.06f, f1);
  }

  Guy g1;
  Random r;

  public Reactor(float x, float y, Texture img2, boolean arse, TextureRegion[][] tmp,
                 Animation nwork, Animation swork,
                 Animation ework, Animation wwork) {
    addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
        Gdx.app.log("arse", "reac");
        return false;
      }
    });

    r = new Random();
    if (arse) {
      Guy g2 = new Guy(startup, "reac_starting");
      g2.setPosition(0, 0);
      addActor(g2);
    } else {
      g1 = new Guy(on, "reac_on");
      g1.setPosition(0, 0);

      Guy g3 = new Guy(nwork, "nwork");
      g3.setPosition(32, 0);

      Guy g4 = new Guy(swork, "swork");
      g4.setPosition(32, 64);

      Guy g9 = new Guy(ework, "ework");
      g9.setPosition(0, 32);

      Guy g10 = new Guy(wwork, "wwork");
      g10.setPosition(64, 32);

      addActor(g1);
      addActor(g4);
      addActor(g3);
      addActor(g9);
      addActor(g10);
    }
    moveBy(x, y);
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
    stateTime += delta;
    if (stateTime > r.nextFloat()*20) {
      stateTime = 0;
      if (g1 != null && g1.type == Guy.Type.SPRITE) {
        int rr = r.nextInt(sparks.length);
        g1.setAnim(sparks[rr]);
      }
    }
  }

}
