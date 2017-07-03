package com.brik.mariobros.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brik.mariobros.MarioBros;

import com.brik.mariobros.Scenes.Hud;
import com.brik.mariobros.Sprites.Mario;
import com.brik.mariobros.Tools.B2WorldCreator;

/**
 * Created by Bri on 7/2/2017.
 */

public class PlayScreen implements Screen {
    //Reference to the  Game, used to set Screens
    private MarioBros game;
    private TextureAtlas atlas;

    //basic playscreen variables
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr; //graphical represenation of fixtures and body.

    //Sprites
    private Mario player;

    public PlayScreen(MarioBros game){
        atlas = new TextureAtlas("Mario_and_Enemies.pack");
        this.game = game;

        //create cam used to follow mario through cam world
        gameCam = new OrthographicCamera();

        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(MarioBros.V_WIDTH / MarioBros.PPM, MarioBros.V_HEIGHT /  MarioBros.PPM, gameCam);

        //create game HUD for scores/timers/level info
        hud = new Hud(game.batch);

        //Load map and setup map renderer
        maploader = new TmxMapLoader();
        map = maploader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1 / MarioBros.PPM );
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        //Box2D world, setting no gravity in X, -10 gravity
        world = new World(new Vector2(0, -10), true);  // edited
        //Debug lines in box2d world.
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);
        player = new Mario(world, this); // initialization of  Mario class object
    }

    //Controls the Mario using immediate impulses.
    public void handleInput(float dt){

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);


    }

    //All updating of game world.
    public void update(float dt){
        handleInput(dt);

        world.step(1/60f, 6, 2);
        player.update(dt);

        gameCam.position.x = player.b2body.getPosition().x;

        //update cam every iteration of render cycle.
        gameCam.update();
        renderer.setView(gameCam);
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //seperates the update logic from the render
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears screen

        //render game map
        renderer.render();

        //render Box2DDebugLines
        b2dr.render(world, gameCam.combined);

        //Draw the Mario sprite
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        //Set out batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //change the size of the screen must adjust the Viewport
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}
