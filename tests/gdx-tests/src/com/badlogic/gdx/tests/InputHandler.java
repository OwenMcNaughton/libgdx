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

public class InputHandler implements GestureListener {

  OrthographicCamera cam;
  Stage stage;
  String jetName;
  Texture img;

  public InputHandler(OrthographicCamera cam, Stage stage) {
    this.cam = cam;
    this.stage = stage;
    jetName = "a";
  }

  float initialScale;

  int count = 0;

  @Override
  public boolean touchDown(float x, float y, int pointer, int button) {
    flinging = false;
    initialScale = cam.zoom;
/*
    if (count < 3) {
      Vector3 screen = cam.unproject(new Vector3(x, y, 0));
      Gdx.app.log("touchdown", "" + x + "," + y + " -> " + screen.x + "," + screen.y);
      Random r = new Random();
      Sprite s = null;
      switch (count) {
        case 0: s = new Sprite(img, 0, 0, 512, 512); break;
        case 1: s = new Sprite(img, 512, 0, 512, 512); break;
        case 2: s = new Sprite(img, 0, 512, 512, 512); break;
      }
      s.setCenter(256, 256);
      s.setPosition(screen.x, screen.y);
      Jet jet = new Jet(s, (r.nextFloat() * 2 - 1), (r.nextFloat() * 2 - 1), cam, this);
      jet.setName(jetName);
      jetName += "a";
      stage.addActor(jet);
      for (int i = 0; i != 2; i++) {
        for (int j = 0; j != 2; j++) {
          Sprite ss = new Sprite(img, 512 + i * 256, 512 + j * 256, 256, 256);
          ss.setPosition(r.nextFloat() * 400 - 200, r.nextFloat() * 400 - 200);
          Jet jj = new Jet(ss, (r.nextFloat() * 2 - 1), (r.nextFloat() * 2 - 1), cam, this);
          jet.addActor(jj);
        }
      }
    } else {
    }
*/
    //Gdx.app.log("arse", "touchdown " + pointer + " " + button);

    count++;

    return false;
  }

  @Override
  public boolean tap(float x, float y, int count, int button) {
    //Gdx.app.log("arse", "tap " + count + " " + button);
    stage.touchDown((int) x, (int) y, 0, button);
    return false;
  }

  @Override
  public boolean longPress(float x, float y) {
    return false;
  }

  boolean flinging = false;
  float velx, vely, veld;

  @Override
  public boolean fling(float velocityX, float velocityY, int button) {
    flinging = true;
    velx = cam.zoom * velocityX;
    vely = cam.zoom * velocityY;
    veld = 1;
    return true;
  }

  @Override
  public boolean pan(float x, float y, float deltaX, float deltaY) {
    cam.translate(-deltaX * cam.zoom, deltaY * cam.zoom);
    return true;
  }

  @Override
  public boolean panStop(float x, float y, int pointer, int button) {
    return false;
  }

  @Override
  public boolean zoom(float initialDistance, float distance) {
    //Calculate pinch to zoom
    float ratio = (initialDistance / distance);

    
    //Clamp range and set zoom
    cam.zoom = MathUtils.clamp(initialScale * ratio, 0.01f, 100.0f);

    return true;
  }

  @Override
  public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
    return false;
  }

  public Actor focus;

  public void update(float delta) {
    if (focus != null) {
      cam.up.set(0, -1, 0);
      cam.direction.set(0, 0, 1);
      cam.position.set(focus.getX() + focus.getWidth()/2, focus.getY() + focus.getHeight()/2, 0);
      cam.rotate(focus.getRotation() + 180);
    }

    if (flinging) {
      veld -= delta/2;
      velx *= veld;
      vely *= veld;
      cam.position.add(-velx * Gdx.graphics.getDeltaTime(), vely * Gdx.graphics.getDeltaTime(), 0);
      if (Math.abs(velx) < 0.01f) velx = 0;
      if (Math.abs(vely) < 0.01f) vely = 0;
    }
  }
}
