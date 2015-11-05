package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

public class SuperReactor extends Group {

  public SuperReactor(Texture img2, TextureRegion[][] tmp, Animation startup, Animation spark,
                      Animation nwork, Animation swork, Animation ework, Animation wwork) {
    Guy g = new Guy(new Sprite(img2, 0, 192, 864, 576), "superreac");
    addActor(g);

    boolean arse = true;
    for (int i = 0; i != 10; i++) {
      for (int j = 0; j != 10; j++) {
        arse = !arse;
      }
    }
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

    moveBy(0, .1f);
    rotateBy(.1f);
    positionChanged();
  }

}
