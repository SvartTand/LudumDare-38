package com.svarttand.game.huds;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
import com.svarttand.game.Application;
import com.svarttand.game.constants.Constants;
import com.svarttand.game.misc.Textures;

public class PlayHud {

	private Camera camera;
	private Viewport viewport;
	
	public Stage stage;
	
	private ArrayList<Button> buttonList;
	private ArrayList<ButtonStyle> buttonStyles;
	
	private int currentPressed;
	private float cityHealthWidth;
	private float domeHealthWidth;
	private float cooldownWidth;
	
	private Label cityHP;
	private Label domeHP;
	private Label scoreLabel;
	private Label currentlySelected;
	
	private float buttonWidth;
	private float buttonHeight;
	
	public PlayHud(Camera camera){
		this.camera = camera;
		
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT,this.camera);
		buttonList = new ArrayList<Button>();
		buttonStyles = new ArrayList<ButtonStyle>();
		cityHealthWidth = 0;
		domeHealthWidth = 0;
	}
	
	public void initialize(Textures textures){
		stage = new Stage(viewport);
		cityHP = new Label("City HP: " + cityHealthWidth, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		domeHP = new Label("Dome HP: " + domeHP, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		scoreLabel = new Label("Kills: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		currentlySelected = new Label("---Nothingness---" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		buttonHeight = textures.getTextureRegion("Button").getRegionHeight();
		buttonWidth = textures.getTextureRegion("Button").getRegionWidth();
		for (int i = 0; i < 6; i++) {
			final int buttonType = i;
			Button button;
			if (i<=4) {
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
			
	        button.setPosition(3 + (buttonWidth+40) * i, 3);
	        
	        button.addListener( new ClickListener() {              
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                currentPressed = buttonType;
	                
	            };
	        });
	        buttonList.add(button);
	        stage.addActor(button);
	        
	        
		}
		scoreLabel.setPosition(5, (buttonHeight + 6) * 2f);
		currentlySelected.setPosition(Application.V_WIDTH - currentlySelected.getWidth(), (buttonHeight + 6) * 2f);
		cityHP.setPosition(Application.V_WIDTH*0.5f - cityHP.getWidth()*0.5f, buttonHeight + 6);
		domeHP.setPosition(Application.V_WIDTH*0.5f - domeHP.getWidth()*0.5f, (buttonHeight + 6) * 1.5f);
		stage.addActor(cityHP);
		stage.addActor(domeHP);
		stage.addActor(scoreLabel);
		stage.addActor(currentlySelected);
		Gdx.input.setInputProcessor(stage);
		
	}
	private void ButtonUpdate(){
		
	}
	
	public void render(ShapeRenderer renderer){
		renderer.setColor(Color.SKY);
		renderer.rect(3, buttonHeight+6, cityHealthWidth, buttonHeight*0.5f);
		renderer.setColor(Color.FIREBRICK);
		renderer.rect(3, (buttonHeight+6) * 1.5f, domeHealthWidth, buttonHeight*0.5f);
		renderer.setColor(Color.BLUE);
		renderer.rect(Application.V_WIDTH*0.5f - Application.V_WIDTH *0.1f, (buttonHeight+6) * 2f, cooldownWidth, buttonHeight*0.25f);
		
	}
	
	public void update(float cityValue, float domeValue, float score, float cooldown ){
		cityHealthWidth = (Application.V_WIDTH - 6) * cityValue;
		domeHealthWidth = (Application.V_WIDTH - 6) * domeValue;
		if (cooldown <= 0) {
			cooldownWidth = 0;
		}else{
			cooldownWidth = Application.V_WIDTH*0.25f * cooldown;
		}
		
		cityHP.setText("City HP: " +  cityValue*100 + "%");
		domeHP.setText("Dome HP: " + domeValue*100 + "%");
		scoreLabel.setText("Kills: " + score);
		
		if (currentPressed == Constants.STONE) {
			currentlySelected.setText("Rock");
		}
		if (currentPressed == Constants.GRANADE) {
			currentlySelected.setText("Granade");
		}
		if (currentPressed == Constants.BOMB) {
			currentlySelected.setText("Bomb");
		}
		if (currentPressed == Constants.MYSTERYBOX) {
			currentlySelected.setText("MysteryBox");
		}
		if (currentPressed == Constants.NUKE) {
			currentlySelected.setText("Nuke");
		}
		for (int i = 0; i < buttonList.size(); i++) {
			if (currentPressed == i) {
				buttonList.get(i).setStyle(buttonStyles.get(i*2+1));
			}else{
				buttonList.get(i).setStyle(buttonStyles.get(i*2));
			}
		}
	}
	
	public void setCurrentpressed(int nr){
		currentPressed = nr;
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
