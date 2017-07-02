package Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brik.mariobros.MarioBros;

/**
 * Our Hud will hold all game info.
 *
 * Organize a table into our stage (empty box)  then we can lay out table in a way to organize
 * our labels into a certain position in our stage.
 */

public class Hud {
    public Stage stage; //basically an empty box...
    private Viewport viewport; //Cam moves we need Hud to stay the same.

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countdownLabel, scoreLabel, timeLabel, levelLabel, worldLabel, marioLabel;

    public Hud(SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        viewport = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
    }

}
