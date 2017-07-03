package com.brik.mariobros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.brik.mariobros.Screens.playScreen;

public class MarioBros extends Game {
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
	public SpriteBatch batch; //very memory intensive so make this batch public. All screens will have access.
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new playScreen(this));
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
