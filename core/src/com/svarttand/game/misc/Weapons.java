package com.svarttand.game.misc;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public interface Weapons extends Disposable{
	
	
	
	public void update(float mousePositionX, float mousePositionY, float delta);
	public void release();
	public void detonate();
	public Circle getBlast();
	public Vector2 getPosition();
	public String getTextureName();
	public float getCooldown();
	public TextureRegion getTextureRegion();
	public void render(SpriteBatch batch);
	

}
