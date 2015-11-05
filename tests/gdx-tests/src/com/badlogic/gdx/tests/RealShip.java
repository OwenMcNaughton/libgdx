package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.ArrayList;
import java.util.Random;

public class RealShip extends Group {
  Sprite shiptex;
  float time = 0;
  int xer = 0, yer = 0;
  int layout[][] = {
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,2,0,0,0,6,0,0,0,0,0,2,0,0,0,0,0,0},
    {0,0,0,0,0,5,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,2,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0},
    {0,0,0,0,0,5,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0},
    {0,8,0,0,0,5,0,5,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,2,0,0,0,0,0,0,0,0,0,2,0,0,4,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,5,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,2,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
  Texture img2;
  TextureRegion[][] tmp;
  Animation nwork, swork, ework, wwork, gen_start, gen_go;

  float xs, ys;
  float rot;

  OrthographicCamera cam;
  InputHandler ih;

  public RealShip(float x, float y, Texture shiptex, Texture img2, TextureRegion[][] tmp,
                  Animation gen_start, Animation gen_go,
                  Animation nwork, Animation swork, Animation ework, Animation wwork,
                  Animation sgs, Animation sgg, Animation sfs, Animation sfg,
                  Animation bgs, Animation bgg, Texture shield,
                  OrthographicCamera cam, InputHandler ih) {
    this.cam = cam;
    this.ih = ih;
    this.shiptex = new Sprite(shiptex);
    this.img2 = img2;
    this.tmp = tmp;
    this.nwork = nwork;
    this.swork = swork;
    this.ework = ework;
    this.wwork = wwork;
    this.gen_start = gen_start;
    this.gen_go = gen_go;

    setOrigin(shiptex.getWidth()/2, shiptex.getHeight()/2);

    boolean gen = false;
    boolean bgea = false;
    boolean rea = false;
    boolean brea = false;
    boolean sgea = false;
    for (int i = 0; i != layout.length; i++) {
      for (int j = 0; j != layout[i].length; j++) {
        switch (layout[i][j]) {
          case 2:
            addActor(new Reactor(i*32, j*32, img2, rea, tmp,
                                 nwork, swork, ework, wwork));
            addActor(new Reactor(i*32, j*32, img2, rea, tmp,
              nwork, swork, ework, wwork));
            //rea = !rea;
            break;
          case 3:
            addActor(new Generator(i*32, j*32, img2, gen, tmp, gen_go, gen_start, nwork, swork, shield));
            //gen = !gen;
            break;
          case 4:
            addActor(new SmallGen(i*32, j*32, img2, sgea, 0, sgs, sgg, nwork, swork, ework, wwork, shield));
            //sgea = !sgea;
            break;
          case 5:
            addActor(new SmallGen(i*32, j*32, img2, sgea, 1, sfs, sfg, nwork, swork, ework, wwork, shield));
            //sgea = !sgea;
            break;
          case 6:
            addActor(new BigGen(i*32, j*32, img2, bgea, 1, bgs, bgg, nwork, swork, ework, wwork, shield));
            bgea = !bgea;
            break;
          case 8:
            addActor(new BigReactor(i*32, j*32, img2, brea, tmp,
              nwork, swork, ework, wwork));
            //brea = !brea;
            break;
        }
      }
    }
    moveBy(x, y);
    Random r = new Random();
    xs = r.nextFloat() * 8 - 4;
    ys = r.nextFloat() * 8 - 4;
    rot = r.nextFloat() * 2 - 1;
    addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
        /*getIh().focus = me();
        getCam().up.set(0, -1, 0);
        getCam().direction.set(0, 0, 1);
        getCam().rotate(getRotation());
        Gdx.app.log("arse", "rotating");*/
        return false;
      }
    });
    setWidth(shiptex.getWidth());
    setHeight(shiptex.getHeight());
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
    super.positionChanged();
    shiptex.setPosition(getX(), getY());
    shiptex.setRotation(getRotation());
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    shiptex.draw(batch);
    super.draw(batch, parentAlpha);
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    //time += delta;

    if (time > .3f) {
      this.clearChildren();
      time = 0;
      if (xer == layout.length) {
        xer = 0;
        yer++;
      }
      layout[xer++][yer] = 2;
      for (int i = 0; i != layout.length; i++) {
        for (int j = 0; j != layout[i].length; j++) {
          if (layout[i][j] == 2) {
            addActor(new Reactor(-i * 32, +j * 32, img2, false, tmp,
              nwork, swork, ework, wwork));
          }
        }
      }
    }

    moveBy(xs, ys);
    rotateBy(rot);
  }
}
