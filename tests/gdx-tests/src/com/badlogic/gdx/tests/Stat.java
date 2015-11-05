package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class Stat extends Group {
  Sprite sprite;
  float velx, vely;
  public OrthographicCamera cam;
  public InputHandler ih;

  public Stat(Sprite s, float x, float y, OrthographicCamera cam, InputHandler ih) {
    this.ih = ih;
    this.cam = cam;
    velx = x;
    vely = y;
    sprite = s;
    setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
    setTouchable(Touchable.enabled);
    setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
    addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
        getIh().focus = me();
        getCam().up.set(0, -1, 0);
        getCam().direction.set(0, 0, 1);
        getCam().rotate(getRotation());
        Gdx.app.log("touchdown", "rotating");
        return false;
      }
    });
    setDebug(true);
  }

  public Actor me() {
    return this;
  }

  public OrthographicCamera getCam() {
    return cam;
  }

  public InputHandler getIh() {
    return ih;
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
  }
}
