package com.svarttand.game.sprites;


import com.badlogic.gdx.math.Circle;
import com.svarttand.game.Application;
import com.svarttand.game.misc.Textures;

public class World{
	
	private static final int MAX_HP = 500; 
	private String imageName;
	private float hitPoints;
	private Circle bounds;
	
	private float width;
	private float height;
	
	public World(Textures textures){
		imageName = "City1";
		hitPoints = MAX_HP;
		width = textures.getTextureRegion(imageName).getRegionWidth();
		height = textures.getTextureRegion(imageName).getRegionHeight();
		bounds = new Circle((float) (Application.V_WIDTH*0.5 - width*0.5), 0, height);
	}
	
	public void update(float delta){
		if (getHitPoints()<= 0.6) {
			imageName = "City2";
		}
		if (getHitPoints()<= 0.2) {
			imageName = "City3";
		}
		
	}
	
	
	public String getTextureName(){
		return imageName;
	}
	
	public Circle getBounds() {
		return bounds;
	}

	
	public void takeDmg(int dmg){
		hitPoints -= dmg;
	}
	
	public float getHitPoints() {
		return hitPoints/MAX_HP;
	}
	public float getWidth(){
		return width;
	}


}
