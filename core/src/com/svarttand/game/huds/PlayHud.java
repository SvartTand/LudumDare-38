package com.svarttand.game.huds;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
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

public class PlayHud {

	private Camera camera;
	private Viewport viewport;
	
	public Stage stage;
	private Texture buttonTexture;
	
	private ArrayList<Button> buttonList;
	
	private int currentPressed;
	private ShapeRenderer renderer;
	private float cityHealthWidth;
	private float domeHealthWidth;
	
	private Label cityHP;
	private Label domeHP;
	
	public PlayHud(Camera camera){
		this.camera = camera;
		
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT,this.camera);
		buttonTexture = new Texture("Button.png");
		buttonList = new ArrayList<Button>();
		renderer = new ShapeRenderer();
		cityHealthWidth = 0;
		domeHealthWidth = 0;
	}
	
	public void initialize(){
		stage = new Stage(viewport);
		cityHP = new Label(cityHealthWidth + "", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		domeHP = new Label(domeHP + "", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		for (int i = 0; i < 12; i++) {
			final int buttonType = i;
			Button button = new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonTexture)));
	        button.setPosition(3 + (buttonTexture.getWidth()+1) * i, 3);
	        
	        button.addListener( new ClickListener() {              
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                currentPressed = buttonType;
	            };
	        });
	        buttonList.add(button);
	        stage.addActor(button);
	        
	        
		}
		cityHP.setPosition(Application.V_WIDTH*0.5f - cityHP.getWidth()*0.5f, buttonTexture.getHeight() + 6);
		domeHP.setPosition(Application.V_WIDTH*0.5f - cityHP.getWidth()*0.5f, (buttonTexture.getHeight() + 6) * 1.5f);
		stage.addActor(cityHP);
		stage.addActor(domeHP);
		Gdx.input.setInputProcessor(stage);
		
	}
	public void render(){
		renderer.setColor(Color.CYAN);
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Filled);
		renderer.rect(3, buttonTexture.getHeight()+6, cityHealthWidth, buttonTexture.getHeight()*0.5f);
		renderer.setColor(Color.RED);
		renderer.rect(3, (buttonTexture.getHeight()+6) * 1.5f, domeHealthWidth, buttonTexture.getHeight()*0.5f);
		renderer.end();
		
	}
	
	public void update(float cityValue, float domeValue ){
		cityHealthWidth = (Application.V_WIDTH - 6) * cityValue;
		domeHealthWidth = (Application.V_WIDTH - 6) * domeValue;
		cityHP.setText(cityValue*100 + "%");
		domeHP.setText(domeValue*100 + "%");
	}
	
	public int getCurrentPressed(){
		return currentPressed;
	}
	
	public Viewport getViewport(){
		return viewport;
	}
	
	public void dispose(){
		stage.dispose();
	}

}
