package com.svarttand.game.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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


public class MenuHud {
	
	private Camera camera;
	private Viewport viewport;
	public Stage stage;
	
	private Label playLabel;
	private Label helpLabel;
	
	
	private int isPressed;
	
	private float buttonWidth;
	private float buttonHeight;
	
	public MenuHud(Camera camera){
		this.camera = camera;
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT,camera);
		isPressed = 0;
	}
	
	public void initialize(Textures textures){
		
		stage = new Stage(viewport);
		
		buttonHeight = textures.getTextureRegion("BigButton").getRegionHeight();
		buttonWidth = textures.getTextureRegion("BigButton").getRegionWidth();
		
		playLabel = new Label("PLAY", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playLabel.setPosition(Application.V_WIDTH*0.5f - playLabel.getWidth()*0.5f,  Application.V_HEIGHT*0.20f - buttonHeight*0.3f);
        
        helpLabel = new Label("HELP", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        helpLabel.setPosition(Application.V_WIDTH*0.5f - helpLabel.getWidth()*0.5f,  Application.V_HEIGHT*0.10f - buttonHeight*0.3f);
        
		Button button = new ImageButton(new TextureRegionDrawable(textures.getTextureRegion("BigButton")));
        button.setPosition(Application.V_WIDTH*0.5f - buttonWidth*0.5f, Application.V_HEIGHT*0.20f - buttonHeight*0.5f);
        button.addListener( new ClickListener() {              
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPressed = 1;
            };
        });
        
        stage.addActor(button);
        
        Button button2 = new ImageButton(new TextureRegionDrawable(textures.getTextureRegion("BigButton")));
        button2.setPosition(Application.V_WIDTH*0.5f - buttonWidth*0.5f, Application.V_HEIGHT*0.10f - buttonHeight*0.5f);
        button2.addListener( new ClickListener() {              
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPressed = 2;
            };
        });
        stage.addActor(button2);
        stage.addActor(playLabel);
        stage.addActor(helpLabel);
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
