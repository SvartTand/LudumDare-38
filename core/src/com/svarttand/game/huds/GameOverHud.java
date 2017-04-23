package com.svarttand.game.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.game.Application;
import com.svarttand.game.misc.Textures;

public class GameOverHud {
	
	private Camera camera;
	private Viewport viewport;
	public Stage stage;
	private int score;
	
	private Label resultLabel;
	private Label retryLabel;
	
	private float buttonWidth;
	private float buttonHeight;
	
	private int isPressed;
	
	private Label winLabel;
	
	public GameOverHud(Camera cam, int score){
		this.camera = cam;
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT,camera);
		stage = new Stage(viewport);
		this.score = score;
		isPressed = 0;
	}
	
	public void initialize(Textures textures, boolean win){
		buttonHeight = textures.getTextureRegion("BigButton").getRegionHeight();
		buttonWidth = textures.getTextureRegion("BigButton").getRegionWidth();
		
		retryLabel = new Label("RETRY", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		retryLabel.setPosition(Application.V_WIDTH*0.5f - retryLabel.getWidth()*0.5f,  Application.V_HEIGHT*0.15f - buttonHeight*0.3f);
		
		resultLabel = new Label("YOUR KILL COUNT WAS: " + score, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		resultLabel.setPosition(Application.V_WIDTH*0.5f - resultLabel.getWidth()*0.5f,  Application.V_HEIGHT*0.22f - buttonHeight*0.3f);
		
		if (win) {
			winLabel = new Label("VICTORY!", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
			winLabel.setPosition(Application.V_WIDTH*0.5f - winLabel.getWidth()*0.5f,  Application.V_HEIGHT*0.8f - buttonHeight*0.3f);
		}else{
			winLabel = new Label("DEFEATED!", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
			winLabel.setPosition(Application.V_WIDTH*0.5f - winLabel.getWidth()*0.5f,  Application.V_HEIGHT*0.8f - buttonHeight*0.3f);
		}
		
		Button button = new ImageButton(new TextureRegionDrawable(textures.getTextureRegion("BigButton")));
        button.setPosition(Application.V_WIDTH*0.5f - buttonWidth*0.5f, Application.V_HEIGHT*0.15f - buttonHeight*0.5f);
        button.addListener( new ClickListener() {              
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPressed = 1;
            };
        });
        
        
        stage.addActor(button);
        stage.addActor(resultLabel);
        stage.addActor(retryLabel);
        stage.addActor(winLabel);
        Gdx.input.setInputProcessor(stage);
	}
	
	public int getButtonPressed(){
		return isPressed;
	}
	
	public Viewport getViewport(){
		return viewport;
	}
	
	public void dispose() {
		stage.dispose();
	}

}
