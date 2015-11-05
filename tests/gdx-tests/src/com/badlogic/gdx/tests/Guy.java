package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Guy extends Actor {

  public enum Type {
    SPRITE, ANIMATION
  }
  public Type type;
  Sprite sprite, old_sprite;
  Animation animation;
  float stateTime = 0f;
  Vector2 pos;
  float rot = 0;

  boolean alpha = false;
  float alpha_value = 1.0f;
  boolean alph_inc = true;
  float scale_speed = 0.0f;
  float proper_scale;

  public void input(final String name) {
    addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
        Gdx.app.log("arse", name);
        if (name == "shield") {
          scale_speed = 0.02f;
        }
        return false;
      }
    });
  }

  public Guy(Sprite sprite, String name) {
    type = Type.SPRITE;
    this.sprite = sprite;
    input(name);
    setWidth(sprite.getWidth());
    setHeight(sprite.getHeight());
    proper_scale = sprite.getScaleX();
  }

  public Guy(Animation animation, String name) {
    type = Type.ANIMATION;
    this.animation = animation;
    sprite = new Sprite(animation.getKeyFrame(stateTime, true));
    input(name);
    setWidth(sprite.getWidth());
    setHeight(sprite.getHeight());
  }

  public Guy(Sprite sprite, float angle, String name) {
    type = Type.SPRITE;
    this.sprite = sprite;
    setRotation(angle);
    input(name);
    rot = angle;
    setWidth(sprite.getWidth());
    setHeight(sprite.getHeight());
    proper_scale = sprite.getScaleX();
  }

  public Guy(Animation animation, float angle, String name) {
    type = Type.ANIMATION;
    this.animation = animation;
    sprite = new Sprite(animation.getKeyFrame(stateTime, true));
    setRotation(angle);
    input(name);
    rot = angle;
    setWidth(sprite.getWidth());
    setHeight(sprite.getHeight());
  }

  public void setAnim(Animation anim) {
    old_sprite = sprite;
    type = Type.ANIMATION;
    animation = anim;
    stateTime = 0;
    sprite = new Sprite(animation.getKeyFrame(stateTime, true));
    setWidth(sprite.getWidth());
    setHeight(sprite.getHeight());
  }

  public void setSprite(Sprite s) {
    type = Type.SPRITE;
    sprite = s;
    setWidth(sprite.getWidth());
    setHeight(sprite.getHeight());
  }

  public void setPosition(float x, float y) {
    pos = new Vector2(x, y);
    sprite.setPosition(x, y);
    moveBy(x, y);
  }

  public void setAlpha(boolean a) {
    alpha = a;
  }

  @Override
  public void setScale(float scale) {
    super.setScale(scale);
    proper_scale = scale;
    sprite.setScale(scale, scale);
  }

  public void setSpritePosition(float x, float y) {
    sprite.setPosition(x, y);
  }

  @Override
  protected void positionChanged() {
    super.positionChanged();
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    //debug();
    switch (type) {
      case SPRITE:
        sprite.setRotation(rot);
        sprite.setAlpha(alpha_value);
        sprite.draw(batch);
        break;
      case ANIMATION:
        stateTime += Gdx.graphics.getDeltaTime();
        if (animation.getKeyFrame(stateTime, true) != null) {
          sprite.setRegion(animation.getKeyFrame(stateTime, true));
          sprite.setRotation(rot);
          setWidth(sprite.getWidth());
          setHeight(sprite.getHeight());
          sprite.draw(batch);
        } else {
          Gdx.app.log("arse", "null:" + animation.getKeyFrameIndex(stateTime));
        }
    }
  }

  @Override
  public void act(float delta) {
    debug();
    super.act(delta);
    if (alpha) {
      if (alpha_value > .5f)
        alph_inc = false;
      if (alpha_value < .2f)
        alph_inc = true;
      if (scale_speed > .0001f) {
        sprite.setScale(sprite.getScaleX() - scale_speed, sprite.getScaleY() - scale_speed);
        super.setScale(sprite.getScaleX() - scale_speed, sprite.getScaleY() - scale_speed);
        sprite.setScale(getScaleX(), getScaleY());
        scale_speed -= .002f;
        if (scale_speed < 0f)
          scale_speed = 0f;
      } else {
        if (sprite.getScaleX() < proper_scale) {
          super.setScale(sprite.getScaleX() + .002f, sprite.getScaleY() + .002f);
          sprite.setScale(getScaleX(), getScaleY());
        }
      }
      if (alph_inc) alpha_value += .005f; else alpha_value -= .005f;
    }

    if (animation != null && old_sprite != null && animation.isAnimationFinished(stateTime)) {
      setSprite(old_sprite);
    }
  }
}
