package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class Ship extends Group{
  Sprite sprite;

  public Ship() {
    Texture tex = new Texture(Gdx.files.internal("sheet.png"));
    sprite = new Sprite(tex, 0, 0, 256, 256);
    setBounds(200, 200, sprite.getWidth(), sprite.getHeight());
    setOrigin(128, 128);
    setTouchable(Touchable.enabled);
    addListener(new InputListener() {
      public void touchDragged(InputEvent event, float x, float y, int pointer) {
        MoveByAction mba = new MoveByAction();
        mba.setAmount(x, y);
        mba.setDuration(5f);
        Gdx.app.log("arse", "move: " + x + " " + y + " " + pointer);
        Ship.this.addAction(mba);
      }
    });
    //setPosition(200, 200);
    setDebug(true, true);
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
    super.draw(batch, parentAlpha);
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    moveBy(1, 1);
    rotateBy(1);
  }
}
