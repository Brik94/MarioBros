package com.brik.mariobros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.brik.mariobros.Screens.PlayScreen;

public class MarioBros extends Game {
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
	public static final float PPM = 100;
	public SpriteBatch batch; //very memory intensive so make this batch public. All screens will have access.
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render(); //delegate the render method to whatever screen is active at the time.
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
