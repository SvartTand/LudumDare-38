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
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.oracle.webservices.internal.api.EnvelopeStyle.Style;
import com.svarttand.game.Application;
import com.svarttand.game.misc.Textures;

public class PlayHud {

	private Camera camera;
	private Viewport viewport;
	
	public Stage stage;
	
	private ArrayList<Button> buttonList;
	private ArrayList<ButtonStyle> buttonStyles;
	
	private int currentPressed;
	private ShapeRenderer renderer;
	private float cityHealthWidth;
	private float domeHealthWidth;
	
	private Label cityHP;
	private Label domeHP;
	
	private float buttonWidth;
	private float buttonHeight;
	
	public PlayHud(Camera camera){
		this.camera = camera;
		
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT,this.camera);
		buttonList = new ArrayList<Button>();
		buttonStyles = new ArrayList<ButtonStyle>();
		renderer = new ShapeRenderer();
		cityHealthWidth = 0;
		domeHealthWidth = 0;
	}
	
	public void initialize(Textures textures){
		stage = new Stage(viewport);
		cityHP = new Label(cityHealthWidth + "", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		domeHP = new Label(domeHP + "", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		buttonHeight = textures.getTextureRegion("Button").getRegionHeight();
		buttonWidth = textures.getTextureRegion("Button").getRegionWidth();
		for (int i = 0; i < 12; i++) {
			final int buttonType = i;
			Button button;
			if (i == 1 || i == 2) {
				ImageButtonStyle style1 = new ImageButtonStyle(new TextureRegionDrawable(textures.getTextureRegion("Button" + i)), null, null, null, null, null);
				ImageButtonStyle style2 = new ImageButtonStyle(new TextureRegionDrawable(textures.getTextureRegion("Button" + i + "Pressed")), null, null, null, null, null);
				buttonStyles.add(style1);
				buttonStyles.add(style2);
				button = new ImageButton(style1);
				
			}else{
				ImageButtonStyle style1 = new ImageButtonStyle(new TextureRegionDrawable(textures.getTextureRegion("Button")), null, null, null, null, null);
				ImageButtonStyle style2 = new ImageButtonStyle(new TextureRegionDrawable(textures.getTextureRegion("Button2Pressed")), null, null, null, null, null);
				buttonStyles.add(style1);
				buttonStyles.add(style2);
				button = new ImageButton(style1);
			}
			
	        button.setPosition(3 + (buttonWidth+1) * i, 3);
	        
	        button.addListener( new ClickListener() {              
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                currentPressed = buttonType;
	                update();
	            };
	        });
	        buttonList.add(button);
	        stage.addActor(button);
	        
	        
		}
		cityHP.setPosition(Application.V_WIDTH*0.5f - cityHP.getWidth()*0.5f, buttonHeight + 6);
		domeHP.setPosition(Application.V_WIDTH*0.5f - cityHP.getWidth()*0.5f, (buttonHeight + 6) * 1.5f);
		stage.addActor(cityHP);
		stage.addActor(domeHP);
		Gdx.input.setInputProcessor(stage);
		
	}
	public void update(){
		for (int i = 0; i < buttonList.size(); i++) {
			if (currentPressed == i) {
				buttonList.get(i).setStyle(buttonStyles.get(i*2+1));
			}else{
				buttonList.get(i).setStyle(buttonStyles.get(i*2));
			}
		}
	}
	
	public void render(){
		renderer.setColor(Color.CYAN);
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Filled);
		renderer.rect(3, buttonHeight+6, cityHealthWidth, buttonHeight*0.5f);
		renderer.setColor(Color.RED);
		renderer.rect(3, (buttonHeight+6) * 1.5f, domeHealthWidth, buttonHeight*0.5f);
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
