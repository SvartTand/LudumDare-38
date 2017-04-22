package com.svarttand.game.misc;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
	
	private Array<TextureRegion> frames;
	private float maxFrameTime;
	private float currentFrameTime;
	private int frameCount;
	private int frame;
	
	public Animation(String name, int frameCount, float cycletime, Textures textures){
		frames = new Array<TextureRegion>();
		for (int i = 1; i <= frameCount; i++) {
			frames.add(textures.getTextureRegion(name + i));
		}
		this.frameCount = frameCount;
		maxFrameTime = cycletime / frameCount;
		frame = 0;
	}
	
	public Animation(String name, int frameCount, float cycletime, Textures textures, int extraFrames){
		frames = new Array<TextureRegion>();
		for (int i = 1; i <= frameCount+extraFrames; i++) {
			
			frames.add(textures.getTextureRegion(name + i));
		}
		this.frameCount = frameCount;
		maxFrameTime = cycletime / frameCount;
		frame = 0;
	}
	
	public void update(float delta){
		currentFrameTime += delta;
		if (currentFrameTime > maxFrameTime){
			frame++;
			currentFrameTime = 0;
		}
		if (frame >= frameCount){
			frame = 0;
		}
	}
	
	public TextureRegion getFrame(){
		return frames.get(frame);
	}
	
	public TextureRegion getFrame(int index){
		return frames.get(index);
	}
	
	

}
