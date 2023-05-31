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
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class DoubleGreatSword extends MeleeWeapon {

	{
		image = ItemSpriteSheet.DOUBLE_GREATSWORD;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1f;

		tier = 6;
		DLY = 0.5f;
		alchemy = true;
	}

	@Override
	public int proc(Char attacker, Char defender, int damage) {
		if (Random.Int(10) == 0) {
			Buff.affect(defender, Cripple.class, 3f);
		}
		return super.proc( attacker, defender, damage );
	}

	@Override
	public int STRReq(int lvl) {
		return STRReq(8, lvl); //base 24
	}

	@Override
	public int max(int lvl) {
		return  5*(tier) +    //base
				lvl*(tier);   //level scaling
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		{
			inputs =  new Class[]{Greatsword.class, LiquidMetal.class};
			inQuantity = new int[]{2, 100};

			cost = 10;

			output = DoubleGreatSword.class;
			outQuantity = 1;
		}
	}

}
