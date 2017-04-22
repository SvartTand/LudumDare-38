package com.svarttand.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Invader implements Disposable{
	
	private static final int SPEED = 10;
	
	private Vector2 velocity;
	private Vector2 position;
	
	private Texture texture;
	
	private int hitpoints;
	private Rectangle bounds;
	
	
	public Invader(){
		velocity = new Vector2();
		position = new Vector2();
	}
	
	public Texture getTexture(){
		return texture;
	}

	@Override
	public void dispose() {
		texture.dispose();
		
	}
	
	

}
