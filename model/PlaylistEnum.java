package model;

import java.util.HashMap;
import java.util.Map;

public enum PlaylistEnum {
	
	DUEL("Duel",1),
	DOUBLES("Doubles",2),
	STANDARD("Standard",3),
	CHAOS("Chaos",4),
	RANKED_DUEL("Duel Ranked",10),
	RANKED_DOUBLES("Doubles Ranked",11),
	RANKED_SOLO_STANDARD("Standard (Solo) Ranked",12),
	RANKED_STANDARD("Standard Ranked" ,13),
	MUTATOR_MASHUP("Mutators",14),
	SNOW_DAY("Snow Day",15),
	ROCKET_LABS("Rocket Labs",16),
	HOOPS("Hoops",17),
	RUMBLE("Rumble",18),
	DROPSHOT("Dropshot",23);
	
	private String name;
	private int number;
	
	private PlaylistEnum(String name, int number) {
		this.name = name;
		this.number = number;
	}
	@Override
	public String toString() {
		return name;
	}

	public int getIntValue() {
		return number;
	}

}
