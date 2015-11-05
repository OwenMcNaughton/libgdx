package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class Subship extends Actor{
  Sprite sprite;

  public Subship(int x, int y) {
    Texture tex = new Texture(Gdx.files.internal("sheet.png"));
    sprite = new Sprite(tex, 192, 48, 64, 64);
    sprite.setPosition(x, y);
    setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
    setTouchable(Touchable.enabled);
    setOrigin(32, 32);
    addListener(new InputListener() {
      @Override
      public void touchDragged(InputEvent event, float x, float y, int pointer) {
        MoveByAction mba = new MoveByAction();
        mba.setAmount(x, y);
        mba.setDuration(5f);
        Gdx.app.log("arse", "move: " + x + " " + y + " " + pointer);
        Subship.this.addAction(mba);
      }
    });
    setDebug(true);
  }

  @Override
  protected void positionChanged() {
    sprite.setPosition(getX(), getY());
    sprite.setRotation(getRotation());
    super.positionChanged();
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    sprite.draw(batch);
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    moveBy(0, .3f);
    //rotateBy(1);
    //positionChanged();
  }
}
