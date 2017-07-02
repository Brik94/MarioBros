package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brik.mariobros.MarioBros;

/**
 * Created by Bri on 7/2/2017.
 */

public class playScreen implements Screen {
    private MarioBros game;
    Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    public playScreen(MarioBros game){
        this.game = game;
        texture = new Texture("badlogic.jpg");
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, gameCam);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears screen

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin(); //opens box.
        game.batch.draw(texture, 0, 0);
        game.batch.end(); //close the box.

    }

    @Override
    public void resize(int width, int height) {
        //change the size of our screen must adjust the Viewport
        gamePort.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
