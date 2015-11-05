package com.badlogic.gdx.tests;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.CpuSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.badlogic.gdx.tests.utils.GdxTest;

import java.util.ArrayList;
import java.util.Random;

public class AsTest extends GdxTest {
	CpuSpriteBatch batch;
  SpriteBatch batch2;
  SpriteBatch miniBatch;
	Texture img;
	TextureRegion region;
	int w, h, bx;
  Sprite ss;
  Stage stage;
  Stage stage2;

  ShapeRenderer shaper;

  FPSLogger l;

  OrthographicCamera cam;
  OrthographicCamera cam2;
  OrthographicCamera miniCam;
  SuperIh sih;

  boolean init = true;

  ArrayList<Sprite> sprites;

  float stateTime = 0f;
  Animation spark, startup, nwork, swork, ework, wwork, gen_go, gen_start;
  TextureRegion nframe;
  TextureRegion sframe;
  TextureRegion currentFrame;
  TextureRegion currentFrame2;

  Sprite nc, sc, wc, ec;

  Reactor reactor;

  Texture img2;
  TextureRegion[][] tmp;

	public void create () {
    w = Gdx.graphics.getWidth();
    h = Gdx.graphics.getHeight();
    bx = (int)(w * (0.67));
    batch = new CpuSpriteBatch();
    batch2 = new SpriteBatch();
    shaper = new ShapeRenderer();
    l  = new FPSLogger();

		Random r = new Random();

    ScreenViewport sv = new ScreenViewport();
    ScreenViewport sv2 = new ScreenViewport();
    cam = new OrthographicCamera(w, h);
    cam2 = new OrthographicCamera(w, h);
    cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
    cam2.position.set(cam2.viewportWidth / 2f, cam2.viewportHeight / 2f, 0);
    cam.update();
    cam2.update();
    stage = new Stage(sv);
    stage2 = new Stage(sv2);
    InputHandler ih = new InputHandler(cam, stage);
    InputHandler ih2 = new InputHandler(cam2, stage2);
    sih = new SuperIh(ih, ih2);
    GestureDetector gd = new GestureDetector(sih);
    sv.setCamera(cam);
    sv2.setCamera(cam2);

    img2 = new Texture("data/sprites_halo.png");
    Texture img3 = new Texture("data/ship.png");
    tmp = TextureRegion.split(img2, 96, 96);
    TextureRegion[][] tmp6 = TextureRegion.split(img2, 160, 160);

    Reactor.Init(tmp);
    BigReactor.Init(tmp6);

    TextureRegion[][] tmp2 = TextureRegion.split(img2, 32, 32);
    TextureRegion[] nframes = new TextureRegion[4];
    for (int i = 28; i != 32; i++) {
      nframes[i - 28] = tmp2[4][i];
    }
    nwork = new Animation(0.05f, nframes);

    TextureRegion[] sframes = new TextureRegion[4];
    for (int i = 28; i != 32; i++) {
      sframes[i - 28] = tmp2[5][i];
    }
    swork = new Animation(0.05f, sframes);

    TextureRegion[] eframes = new TextureRegion[4];
    for (int i = 28; i != 32; i++) {
      eframes[i - 28] = tmp2[7][i];
    }
    ework = new Animation(0.05f, eframes);

    TextureRegion[] wframes = new TextureRegion[4];
    for (int i = 28; i != 32; i++) {
      wframes[i - 28] = tmp2[6][i];
    }
    wwork = new Animation(0.05f, wframes);

    TextureRegion[][] tmp3 = TextureRegion.split(img2, 160, 96);
    TextureRegion[] gen_go_frames = new TextureRegion[6];
    for (int i = 0; i != 6; i++) {
      gen_go_frames[i] = tmp3[3][i];
    }
    gen_go = new Animation(0.10f, gen_go_frames);

    TextureRegion[] gen_start_frames = new TextureRegion[24];
    for (int i = 0; i != 10; i++) {
      gen_start_frames[i] = tmp3[2][0];
    }
    for (int i = 14; i != 24; i++) {
      gen_start_frames[i] = tmp3[3][0];
    }
    gen_start_frames[10] = tmp3[2][0];
    gen_start_frames[11] = tmp3[2][1];
    gen_start_frames[12] = tmp3[2][2];
    gen_start_frames[13] = tmp3[2][3];
    gen_start = new Animation(0.1f, gen_start_frames);
    gen_start.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

    TextureRegion[][] tmp5 = TextureRegion.split(img2, 64, 96);
    TextureRegion[] sgs_frames = new TextureRegion[23];
    for (int i = 0; i != 10; i++) {
      sgs_frames[i] = tmp5[4][0];
    }
    sgs_frames[10] = tmp5[4][1]; sgs_frames[11] = tmp5[4][2]; sgs_frames[12] = tmp5[4][3];
    for (int i = 13; i != 23; i++) {
      sgs_frames[i] = tmp5[4][4];
    }
    Animation sgs = new Animation(0.1f, sgs_frames);
    sgs.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    TextureRegion[] sfs_frames = new TextureRegion[23];
    for (int i = 0; i != 23; i++) {
      TextureRegion t = sgs_frames[i];
      float v1 = t.getV();
      t.setV(t.getV2());
      t.setV2(v1);
      sfs_frames[i] = t;
    }
    Animation sfs = new Animation(0.1f, sfs_frames);
    sfs.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

    TextureRegion[] sgg_frames = new TextureRegion[6];
    for (int i = 0; i != 6; i++) {
      sgg_frames[i] = tmp5[4][i + 4];
    }
    Animation sgg = new Animation(0.1f, sgg_frames);
    TextureRegion[] sfg_frames = new TextureRegion[6];
    for (int i = 0; i != 6; i++) {
      TextureRegion t = sgg_frames[i];
      float v1 = t.getV();
      t.setV(t.getV2());
      t.setV2(v1);
      sfg_frames[i] = t;
    }
    Animation sfg = new Animation(0.1f, sfg_frames);

    Texture img6 = new Texture("data/sprite_halo2.png");
    TextureRegion tmp7[][] = TextureRegion.split(img6, 224, 160);
    TextureRegion[] bgs_frames = new TextureRegion[23];
    for (int i = 0; i != 10; i++) {
      bgs_frames[i] = tmp7[0][0];
    }
    bgs_frames[10] = tmp7[0][1]; bgs_frames[11] = tmp7[0][2]; bgs_frames[12] = tmp7[0][3];
    for (int i = 13; i != 23; i++) {
      bgs_frames[i] = tmp7[1][0];
    }
    Animation bgs = new Animation(0.1f, bgs_frames);
    bgs.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    TextureRegion[] bgg_frames = new TextureRegion[6];
    bgg_frames[0] = tmp7[1][0]; bgg_frames[1] = tmp7[1][1]; bgg_frames[2] = tmp7[1][2];
    bgg_frames[3] = tmp7[2][0]; bgg_frames[4] = tmp7[2][1]; bgg_frames[5] = tmp7[2][2];
    Animation bgg = new Animation(0.1f, bgg_frames);

    Texture shield = new Texture("data/shield.png");

    for (int i = 0; i != 1; i++) {
   	 Random rrr = new Random();
       RealShip rs = new RealShip(rrr.nextInt(10000) - 5000, rrr.nextInt(10000) - 5000, img3, img2, tmp, gen_go, gen_start,
          nwork, swork, ework, wwork, sgs, sgg, sfs, sfg, bgs, bgg, shield, cam, ih);
        stage.addActor(rs);
    }

    if (Gdx.app.getType() == ApplicationType.Desktop) {
   	 Gdx.input.setInputProcessor(new DesktopInput(cam, stage));
    } else {
   	 Gdx.input.setInputProcessor(gd);
    }

    miniCam = new OrthographicCamera(w, h);
    miniCam.zoom = 4;
    miniBatch = new SpriteBatch();

/*
    for (int i = 0; i != 16; i++) {
      for (int j = 0; j != 1; j++) {
        for (int k = 0; k != 200; k++) {
          Sprite s = new Sprite(img, i * 16, j * 16, 16, 16);
          s.setCenter(8, 8);
          s.setPosition(r.nextInt(w - 32) + 16, r.nextInt(h - 32) + 16);
          Jet jet = new Jet(s, (r.nextFloat() * 2 - 1), (r.nextFloat()*2 - 1), cam);
          stage.addActor(jet);
        }
      }
    }
*/

/*
    Ship ship = new Ship();
    ship.addActor(new Subship(10, 10));
    ship.addActor(new Subship(50, 50));
    Subship ssss = new Subship(100, 100);
    ssss.setZIndex(1);
    ship.setZIndex(10);
    ship.addActor(ssss);
    stage.addActor(ship);
*/
    // Gdx.input.setInputProcessor(stage);

	}

  @Override
	public void render() {
    if (init) {
      create();
      init = false;
    }
    
    float delta = Gdx.graphics.getDeltaTime();

    Gdx.gl.glClearColor(.20f, .20f, .20f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    cam.update();
    batch.setProjectionMatrix(cam.combined);
    sih.update(delta);

    batch.begin();

    //nc.draw(batch);
    //batch.draw(img2, 300, 300, 96, 96, 0, 0, 96, 96, false, false);

    stage.act(delta);
    stage.draw();

    //stateTime += delta;

    //TextureRegion a = spark.getKeyFrame(stateTime, true);
    //batch.draw(a, 100, 100);

/*
    for (int i = -2; i != 3; i++) {
      for (int j = -2; j != 3; j++) {
        batch.draw(img2, i*160, j*160, 160, 160, 384, 96, 160, 160, false, false);
      }
    }
*/

    batch.end();

    l.log();

/*
		batch.begin();
    stage.act(delta);
    stage.draw();
		batch.end();

    shaper.begin(ShapeRenderer.ShapeType.Filled);
    shaper.setColor(.1f, .1f, .1f, 1);
    shaper.rect(bx, 0, w - bx, h);
    shaper.end();

    cam2.update();
    batch2.setProjectionMatrix(cam2.combined);

    batch2.begin();
    stage2.act(delta);
    stage2.draw();
    l.log();
    batch2.end();

    miniCam.update();

    miniBatch.setProjectionMatrix(miniCam.combined);

    // draw the player
    miniBatch.begin();
    miniBatch.draw(img, -(w/2)*4, -(h/2)*4, 512, 512, 0, 0, 512, 512, false, false);
    miniBatch.end();
    */
	}

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void dispose() {


  }
}
