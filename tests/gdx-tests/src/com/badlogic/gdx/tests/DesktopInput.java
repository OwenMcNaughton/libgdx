package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class DesktopInput implements InputProcessor {

   OrthographicCamera cam;
   Stage stage;
  
   public DesktopInput(OrthographicCamera cam, Stage stage) {
	    this.cam = cam;
	    this.stage = stage;
	  }
	
	@Override
	public boolean keyDown (int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp (int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped (char character) {
		// TODO Auto-generated method stub
		return false;
	}

	boolean mouseDown = false;
	
	@Override
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		stage.touchDown((int) screenX, (int) screenY, 0, button);
		mouseDown = true;
		System.out.println("mousedown");
		return false;
	}

	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		mouseDown = false;
		System.out.println("mouseup");
		return false;
	}

	@Override
	public boolean touchDragged (int screenX, int screenY, int pointer) {
		int deltaX = screenX - lastx; int deltaY = screenY - lasty;
		cam.translate(-deltaX * cam.zoom, deltaY * cam.zoom);
		lastx = screenX; lasty = screenY;
		return false;
	}

	int lastx = 0, lasty = 0;
	
	@Override
	public boolean mouseMoved (int screenX, int screenY) {
		if (mouseDown) {
			int deltaX = screenX - lastx; int deltaY = screenY - lasty;
			cam.translate(-deltaX * cam.zoom, deltaY * cam.zoom);
			lastx = screenX; lasty = screenY;
		}
		lastx = screenX; lasty = screenY;
		return false;
	}

	@Override
	public boolean scrolled (int amount) {
	    //Clamp range and set zoom
		System.out.println("zoom: " + cam.zoom + " + " + 1.0 * amount/10);
	   //cam.zoom = MathUtils.clamp((float)(1.0 * amount/10), 0.01f, 100.0f);
	   cam.zoom += (1.0 * amount);
		return false;
	}

}
