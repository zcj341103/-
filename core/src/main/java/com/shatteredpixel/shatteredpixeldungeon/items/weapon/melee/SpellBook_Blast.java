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

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SpellBookCoolDown;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class SpellBook_Blast extends SpellBook {

	public static final String AC_READ		= "READ";

	{
		image = ItemSpriteSheet.BLAST_SPELLBOOK;
		hitSound = Assets.Sounds.HIT;
		hitSoundPitch = 1.1f;

		tier = 3;
	}

	@Override
	public int proc(Char attacker, Char defender, int damage) {
		float procChance = (buffedLvl()+1f)/(buffedLvl()+5f);
		if (Random.Float() < procChance) {
			Buff.affect(defender, Paralysis.class, (buffedLvl() >= 10) ? 1f : 2f);
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
				usesTargeting = true;
				curUser = hero;
				curItem = this;
				GameScene.selectCell(spell);
			}
		}
	}

	private CellSelector.Listener spell = new CellSelector.Listener() {
		@Override
		public void onSelect( Integer target ) {
			if (target != null) {
				Char ch = Actor.findChar(target);
				if (ch != null) {
					if (ch != hero) {
						//trace a ballistica to our target (which will also extend past them
						Ballistica trajectory = new Ballistica(hero.pos, ch.pos, Ballistica.STOP_TARGET);
						//trim it to just be the part that goes past them
						trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
						//knock them back along that ballistica
						if (buffedLvl() >= 10) {
							WandOfBlastWave.throwChar(ch, trajectory, 3 + buffedLvl(), true, true, hero.getClass());
						} else {
							WandOfBlastWave.throwChar(ch, trajectory, 2 + buffedLvl() / 2, true, true, hero.getClass());
						}
					} else {
						GLog.p( Messages.get(SpellBook_Blast.this, "cannot_hero") );
					}
				} else {
					GLog.p( Messages.get(SpellBook_Blast.this, "cannot_cast") );
				}
				Buff.affect(hero, SpellBookCoolDown.class, Math.max(100f-5*buffedLvl(), 50f));
				Invisibility.dispel();
				curUser.spend( Actor.TICK );
				curUser.busy();
				((HeroSprite)curUser.sprite).read();
				Sample.INSTANCE.play(Assets.Sounds.READ);
			}
		}
		@Override
		public String prompt() {
			return Messages.get(SpiritBow.class, "prompt");
		}
	};

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{SpellBook_Empty.class, WandOfBlastWave.class};
			inQuantity = new int[]{1, 1};

			cost = 10;

			output = SpellBook_Blast.class;
			outQuantity = 1;
		}
	}

}
