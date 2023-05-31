/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.ShockingBrew;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Ballista extends MeleeWeapon {
	
	{
		image = ItemSpriteSheet.BALLISTA;
		hitSound = Assets.Sounds.HIT;
		hitSoundPitch = 1f;
		
		//check FishingSpear.class and other spear missiles for additional properties
		
		tier = 5;
		alchemy = true;
	}
	
	@Override
	public int max(int lvl) {
		return  4*(tier) +    	//20 base
				lvl*(tier-1);   //+4 per level
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{Crossbow.class, LiquidMetal.class};
			inQuantity = new int[]{2, 50};

			cost = 8;

			output = Ballista.class;
			outQuantity = 1;
		}
	}
}
