package com.svarttand.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Disposable;
import com.svarttand.game.Application;

public class World implements Disposable{
	
	private static final int MAX_HP = 1000; 
	private Texture image;
	private float hitPoints;
	private Circle bounds;
	
	public World(){
		image = new Texture("CityPlaceholder.png");
		hitPoints = MAX_HP;
		bounds = new Circle((float) (Application.V_WIDTH*0.5 - image.getWidth()*0.5), 0, image.getHeight());
	}
	
	public void update(float delta){
		
	}
	
	public void render(SpriteBatch batch){
		batch.draw(image, (float) (Application.V_WIDTH*0.5 - image.getWidth()*0.5), 0);
	}
	
	public Texture getTexture(){
		return image;
	}
	
	public Circle getBounds() {
		return bounds;
	}
	@Override
	public void dispose() {
		image.dispose();
		
	}
	
	public void takeDmg(int dmg){
		hitPoints -= dmg;
		System.out.println(MAX_HP + "/" + hitPoints);
	}
	
	public float getHitPoints() {
		return hitPoints/MAX_HP;
	}

}
