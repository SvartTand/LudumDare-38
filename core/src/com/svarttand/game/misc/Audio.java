package com.svarttand.game.misc;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Audio {
	
	public static final int BOMB_EXPLOSION = 0;
	public static final int GRANADE_EXPLOSION = 1;
	
	private ArrayList<Sound> sounds;
	
	public Audio(){
		sounds = new ArrayList<Sound>();
		String path = "data/";
		for (int i = 0; i < 2; i++) {
			sounds.add(Gdx.audio.newSound(Gdx.files.internal(path + i + ".wav")));
		}
	}
	
	public Sound getsound(int index){
		return sounds.get(index);
	}
	
	public void dispose(){
		for (int i = 0; i < sounds.size(); i++) {
			sounds.get(i).dispose();
		}
	}

}
