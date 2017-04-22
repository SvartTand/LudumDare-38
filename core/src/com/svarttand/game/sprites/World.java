package com.svarttand.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.svarttand.game.Application;

public class World implements Disposable{
	
	private Texture image;
	private int hitPoints;
	
	public World(){
		image = new Texture("CityPlaceholder.png");
		hitPoints = 1000;
	}
	
	public void update(float delta){
		
	}
	
	public void render(SpriteBatch batch){
		batch.draw(image, (float) (Application.V_WIDTH*0.5 - image.getWidth()*0.5), 0);
	}
	
	public Texture getTexture(){
		return image;
	}
	@Override
	public void dispose() {
		image.dispose();
		
	}

}
