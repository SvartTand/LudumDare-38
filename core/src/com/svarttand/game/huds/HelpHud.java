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

public class HelpHud {
	
	private Viewport viewport;
	public Stage stage;
	
	private Label backLabel;
	private Label objectiveLabel;
	private Label keyLabel;
	
	
	private int isPressed;
	
	private float buttonWidth;
	private float buttonHeight;
	
	public HelpHud(Camera camera){
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT,camera);
		isPressed = 0;
	}
	
	public void initialize(Textures textures){
		
		stage = new Stage(viewport);
		
		buttonHeight = textures.getTextureRegion("BigButton").getRegionHeight();
		buttonWidth = textures.getTextureRegion("BigButton").getRegionWidth();
		
		backLabel = new Label("BACK", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        backLabel.setPosition(Application.V_WIDTH*0.5f - backLabel.getWidth()*0.5f,  Application.V_HEIGHT*0.80f - buttonHeight*0.3f);
        
        objectiveLabel = new Label("Objective:\nDestroy the dome\nbefore the evil ailiens\ndestroy your city", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        objectiveLabel.setPosition(30,   Application.V_HEIGHT*0.08f);
        
        
        
		Button button = new ImageButton(new TextureRegionDrawable(textures.getTextureRegion("BigButton")));
        button.setPosition(Application.V_WIDTH*0.5f - buttonWidth*0.5f, Application.V_HEIGHT*0.80f - buttonHeight*0.5f);
        button.addListener( new ClickListener() {              
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPressed = 1;
            };
        });
        
        stage.addActor(button);
        
        keyLabel = new Label("Keys you can use:\nSPACE, detonates\nthe bomb instantly\nESC, Go back to the Menu", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        keyLabel.setPosition(Application.V_WIDTH*0.5f ,   Application.V_HEIGHT*0.08f);
     
        stage.addActor(backLabel);
        stage.addActor(objectiveLabel);
        stage.addActor(keyLabel);
        Gdx.input.setInputProcessor(stage);
        
	}
	public int isPressed(){
		return isPressed;
	}
	
	public Viewport getViewport(){
		return viewport;
	}
	
	public void dispose(){
		stage.dispose();
	}

}
