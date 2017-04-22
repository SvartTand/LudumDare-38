package com.svarttand.game.misc;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures {
	
	private TextureAtlas atlas;
	
	
	public Textures(){
		atlas = new TextureAtlas("DestroyTheDome.pack");
	}
	
	public TextureRegion getTextureRegion(String name){
		return atlas.findRegion(name);
	}

	public void dispose() {
		atlas.dispose();
		
	}

}
