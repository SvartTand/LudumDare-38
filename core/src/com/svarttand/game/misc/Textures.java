package com.svarttand.game.misc;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures {
	
	private TextureAtlas atlas;
	private Audio sounds;
	
	
	public Textures(){
		atlas = new TextureAtlas("DestroyTheDome.pack");
		sounds = new Audio();
	}
	
	public TextureRegion getTextureRegion(String name){
		return atlas.findRegion(name);
	}
	
	public Sound getSound(int index){
		return sounds.getsound(index);
	}

	public void dispose() {
		atlas.dispose();
		
	}
	
	public TextureAtlas getAtlas(){
		return atlas;
	}

}
