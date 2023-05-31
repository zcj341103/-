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
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.CorrosiveGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ParalyticGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BlobImmunity;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SpellBookCoolDown;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorrosion;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class SpellBook_Corrosion extends SpellBook {

	{
		image = ItemSpriteSheet.CORROSION_SPELLBOOK;
		hitSound = Assets.Sounds.HIT;
		hitSoundPitch = 1.1f;

		tier = 3;
	}

	@Override
	public int proc(Char attacker, Char defender, int damage) {
		float procChance = (buffedLvl()+1f)/(buffedLvl()+5f);
		if (Random.Float() < procChance) {
			Buff.affect(defender, Ooze.class).set(3+buffedLvl());
		}
		return super.proc( attacker, defender, damage );
	}

	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_READ)) {
			if (hero.buff(SpellBookCoolDown.class) != null) {
				GLog.w( Messages.get(SpellBook_Empty.class, "fail") );
			} else if (!isIdentified()) {
				GLog.w( Messages.get(SpellBook_Empty.class, "need_id") );
			} else {
				GLog.w( Messages.get(this, "protected") );
				Buff.prolong( hero, BlobImmunity.class, 5+buffedLvl() );
				Buff.affect(hero, SpellBookCoolDown.class, Math.max(200f-10*buffedLvl(), 100f));
				if (buffedLvl() >= 10) {
					GameScene.add( Blob.seed( hero.pos, 300+20*buffedLvl(), ParalyticGas.class ) );
					GameScene.add( Blob.seed( hero.pos, 300+20*buffedLvl(), ToxicGas.class ) );
					GameScene.add( Blob.seed( hero.pos, 150+10*buffedLvl(), CorrosiveGas.class ) );
					Sample.INSTANCE.play( Assets.Sounds.GAS );
				} else {
					switch (Random.Int(3)) {
						case 0: default:
							GameScene.add( Blob.seed( hero.pos, 300+20*buffedLvl(), ParalyticGas.class ) );
							Sample.INSTANCE.play( Assets.Sounds.GAS );
							break;
						case 1:
							GameScene.add( Blob.seed( hero.pos, 300+20*buffedLvl(), ToxicGas.class ) );
							Sample.INSTANCE.play( Assets.Sounds.GAS );
							break;
						case 2:
							GameScene.add( Blob.seed( hero.pos, 150+10*buffedLvl(), CorrosiveGas.class ) );
							Sample.INSTANCE.play( Assets.Sounds.GAS );
							break;
					}
				}
				Invisibility.dispel();
				curUser.spend( Actor.TICK );
				curUser.busy();
				((HeroSprite)curUser.sprite).read();
				Sample.INSTANCE.play(Assets.Sounds.READ);
			}
		}
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{SpellBook_Empty.class, WandOfCorrosion.class};
			inQuantity = new int[]{1, 1};

			cost = 10;

			output = SpellBook_Corrosion.class;
			outQuantity = 1;
		}
	}
}
