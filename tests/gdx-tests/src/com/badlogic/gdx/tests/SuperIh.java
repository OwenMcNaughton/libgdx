package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Random;

public class SuperIh implements GestureListener {

  InputHandler ih;
  InputHandler ih2;
  int w = Gdx.graphics.getWidth();
  int h = Gdx.graphics.getHeight();
  int bx = (int)(w * 0.67);

  public SuperIh(InputHandler ih, InputHandler ih2) {
    this.ih = ih;
    this.ih2 = ih2;
  }

  float lastx;

  @Override
  public boolean touchDown(float x, float y, int pointer, int button) {
    lastx = x;
    //if (x < bx) {
      ih.touchDown(x, y, pointer, button);
    //} else {
      //ih2.touchDown(x, y, pointer, button);
    //}
    return true;
  }

  @Override
  public boolean tap(float x, float y, int count, int button) {
    ih.tap(x, y, count, button);
    return false;
  }

  @Override
  public boolean longPress(float x, float y) {
    return false;
  }
  @Override
  public boolean fling(float velocityX, float velocityY, int button) {
    //if (lastx < bx) {
      ih.fling(velocityX, velocityY, button);
    //} else {
    //  ih2.fling(velocityX, velocityY, button);
    //}
    return true;
  }

  @Override
  public boolean pan(float x, float y, float deltaX, float deltaY) {
    //if (x < bx) {
      ih.pan(x, y, deltaX, deltaY);
    //} else {
      ih2.pan(x, y, deltaX, deltaY);
    //}
    return true;
  }

  @Override
  public boolean panStop(float x, float y, int pointer, int button) {
    return false;
  }

  @Override
  public boolean zoom(float initialDistance, float distance) {
    //if (lastx < bx) {
      ih.zoom(initialDistance, distance);
    //} else {
    //  ih2.zoom(initialDistance, distance);
    //}
    return true;
  }

  @Override
  public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
    return false;
  }

  public Actor focus;

  public void update(float delta) {
    ih.update(delta);
    ih2.update(delta);
  }
}
