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

public class BigReactor extends Group {
  float stateTime = 0;

  TextureRegion currentFrame, currentFrame2, nframe, sframe;
  Sprite nworker, sworker, nc, wc, sc, ec;
  static Animation startup;
  static Sprite on;
  static Animation[] sparks;

  public static void Init(TextureRegion[][] tmp) {
    sparks = new Animation[7];

    on = new Sprite(tmp[5][4]);

    TextureRegion[] frames2 = new TextureRegion[23];
    for (int i = 0; i != 10; i++) {
      frames2[i] = tmp[5][0];
    }
    for (int i = 13; i != 23; i++) {
      frames2[i] = tmp[5][4];
    }
    frames2[10] = tmp[5][1];
    frames2[11] = tmp[5][2];
    frames2[12] = tmp[5][3];
    startup = new Animation(0.1f, frames2);
    startup.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

    TextureRegion[] f1 = new TextureRegion[4];
    f1[0] = tmp[3][0]; f1[1] = tmp[3][1]; f1[2] = tmp[3][3]; f1[3] = tmp[3][3];
    sparks[0] = new Animation(0.06f, f1);

    f1 = new TextureRegion[4];
    f1[0] = tmp[3][4]; f1[1] = tmp[3][5]; f1[2] = tmp[4][0]; f1[3] = tmp[4][1];
    sparks[1] = new Animation(0.06f, f1);

    f1 = new TextureRegion[4];
    f1[0] = tmp[4][2]; f1[1] = tmp[4][3]; f1[2] = tmp[4][4]; f1[3] = tmp[4][5];
    sparks[2] = new Animation(0.06f, f1);

    f1 = new TextureRegion[4];
    f1[0] = tmp[3][3]; f1[1] = tmp[3][2]; f1[2] = tmp[3][1]; f1[3] = tmp[3][0];
    sparks[3] = new Animation(0.06f, f1);

    f1 = new TextureRegion[4];
    f1[0] = tmp[3][2]; f1[1] = tmp[3][3]; f1[2] = tmp[3][4]; f1[3] = tmp[3][5];
    sparks[4] = new Animation(0.06f, f1);

    f1 = new TextureRegion[12];
    for (int i = 0 ; i != 12; i++) {
      if (i > 5) {
        f1[i] = tmp[4][i-6];
      } else {
        f1[i] = tmp[3][i];
      }
    }
    sparks[5] = new Animation(0.03f, f1);

    f1 = new TextureRegion[6];
    f1[0] = tmp[4][2]; f1[1] = tmp[4][1]; f1[2] = tmp[4][0];
    f1[3] = tmp[3][5]; f1[4] = tmp[3][4]; f1[5] = tmp[3][3];
    sparks[6] = new Animation(0.06f, f1);
  }

  Guy g1;
  Random r;

  public BigReactor(float x, float y, Texture img2, boolean arse, TextureRegion[][] tmp,
                    Animation nwork, Animation swork,
                    Animation ework, Animation wwork) {
    addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
        Gdx.app.log("arse", "bigreac");
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

      Guy g33 = new Guy(nwork, "nwork");
      g33.setPosition(96, 0);

      Guy g4 = new Guy(swork, "swork");
      g4.setPosition(32, 128);

      Guy g44 = new Guy(swork, "swork");
      g44.setPosition(96, 128);

      Guy g9 = new Guy(ework, "ework");
      g9.setPosition(0, 32);

      Guy g99 = new Guy(ework, "ework");
      g99.setPosition(0, 96);

      Guy g10 = new Guy(wwork, "wwork");
      g10.setPosition(128, 32);

      Guy g1010 = new Guy(wwork, "wwork");
      g1010.setPosition(128, 96);

      addActor(g1);
      addActor(g4);
      addActor(g3);
      addActor(g9);
      addActor(g10);
      addActor(g44);
      addActor(g33);
      addActor(g99);
      addActor(g1010);
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
