package com.svarttand.game.misc;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Audio {
	
	public static final int NUKE_EXPLOSION = 0;
	public static final int GRANADE_EXPLOSION = 1;
	public static final int ROCK_HIT = 2;
	public static final int MYSTERYBOX = 3;
	public static final int WALKING = 4;
	public static final int BUTTON_PRESS = 5;
	public static final int BOMB_EXPLOSION = 6;
	public static final int SPLAT = 7;
	public static final int FIRE = 8;
	
	private ArrayList<Sound> sounds;
	
	public Audio(){
		sounds = new ArrayList<Sound>();
		String path = "data/";
		for (int i = 0; i < 9; i++) {
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
