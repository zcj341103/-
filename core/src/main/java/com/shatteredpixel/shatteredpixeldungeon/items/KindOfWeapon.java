/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
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

package com.shatteredpixel.shatteredpixeldungeon.items;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CertainCrit;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CritBonus;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Demonization;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Flurry;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Iaido;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.IntervalWeaponUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Jung;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SerialAttack;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sheathing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.WeaponEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfVorpal;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.BArray;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

abstract public class  KindOfWeapon extends EquipableItem {
	
	protected static final float TIME_TO_EQUIP = 1f;

	protected String hitSound = Assets.Sounds.HIT;
	protected float hitSoundPitch = 1f;
	
	@Override
	public boolean isEquipped( Hero hero ) {
		return hero.belongings.weapon() == this;
	}
	
	@Override
	public boolean doEquip( Hero hero ) {

		detachAll( hero.belongings.backpack );
		
		if (hero.belongings.weapon == null || hero.belongings.weapon.doUnequip( hero, true )) {
			
			hero.belongings.weapon = this;
			activate( hero );
			Talent.onItemEquipped(hero, this);
			ActionIndicator.updateIcon();
			updateQuickslot();
			
			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.n( Messages.get(KindOfWeapon.class, "equip_cursed") );
			}

			if (hero.subClass == HeroSubClass.WEAPONMASTER || (hero.hasTalent(Talent.QUICK_SWAP) && hero.buff(Talent.QuickSwapCooldown.class) == null)) {
				if (hero.hasTalent(Talent.QUICK_SWAP)) {
					Buff.affect( hero, WeaponEmpower.class).set( hero.pointsInTalent(Talent.QUICK_SWAP), 1f);
					Buff.affect( hero, Talent.QuickSwapCooldown.class, 5f);
				}
			} else {
				hero.spendAndNext( TIME_TO_EQUIP );
			}
			return true;
			
		} else {
			
			collect( hero.belongings.backpack );
			return false;
		}
	}

	@Override
	public boolean doUnequip( Hero hero, boolean collect, boolean single ) {
		if (super.doUnequip( hero, collect, single )) {

			hero.belongings.weapon = null;
			return true;

		} else {

			return false;

		}
	}

	public int min(){
		return min(buffedLvl());
	}

	public int max(){
		return max(buffedLvl());
	}

	public int STRReq() {
		return STRReq();
	}

	abstract public int min(int lvl);
	abstract public int max(int lvl);

	public int damageRoll(Char owner) {
		int critChance = 0;

		Demonization demonization = owner.buff(Demonization.class);
		if (demonization != null && demonization.isDemonated()) {
			critChance += (int)((hero.defenseSkill(owner) - (hero.lvl+5))/2);
		}

		if (Dungeon.hero.heroClass == HeroClass.SAMURAI && Dungeon.hero.belongings.weapon != null) {
			critChance += 2 * (Dungeon.hero.STR() - Dungeon.hero.belongings.weapon.STRReq());
			critChance += Dungeon.hero.lvl;
		}

		if (hero.buff(Talent.SurpriseStabTracker.class) != null) {
			critChance += 5 * hero.pointsInTalent(Talent.SURPRISE_STAB);
			hero.buff(Talent.SurpriseStabTracker.class).detach();
		}

		if (Dungeon.hero.buff(Sheathing.class) != null) {
			if (hero.buff(Iaido.class) != null) {
				critChance *= 1.2f + 0.3f * hero.buff(Iaido.class).getCount();
			} else {
				critChance *= 1.2f;
			}
		}

		if (Dungeon.hero.buff(Jung.class) != null) {
			critChance *= 2f + 0.5f * Dungeon.hero.pointsInTalent(Talent.JUNG_DETECTION);
		}

		if (Dungeon.hero.buff(CritBonus.class) != null) {
			critChance += Dungeon.hero.buff(CritBonus.class).level();
		}

		if (Dungeon.hero.hasTalent(Talent.DETECTION)) {
			critChance += 5 * Dungeon.hero.pointsInTalent(Talent.DETECTION);
		}

		if (Dungeon.hero.buff(IntervalWeaponUpgrade.class) != null) {
			critChance += 10 * Dungeon.hero.buff(IntervalWeaponUpgrade.class).boost();
		}

		if (Dungeon.hero.buff(CertainCrit.class) != null) {
			critChance = 100;
		}

		critChance += Math.round(100*RingOfVorpal.vorpalProc( hero ));

		if (owner == hero && Dungeon.hero.belongings.weapon() instanceof MeleeWeapon) {
			if (hero.buff(Talent.DetactiveSlashingTracker.class) != null && hero.subClass == HeroSubClass.SLASHER && hero.pointsInTalent(Talent.DETECTIVE_SLASHING) > 2) {
				Buff.affect(hero, SerialAttack.class).maxHit();
			}
			if ( critChance > 0 ) {
				if (Random.Int(100) < critChance) {
					Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
					hero.sprite.showStatus(CharSprite.NEUTRAL, "!");
					if (hero.buff(Iaido.class) != null && hero.pointsInTalent(Talent.SLASHING) > 1) {
						if (Random.Int(7) < hero.buff(Iaido.class).getCount()) {
							Buff.affect(hero, IntervalWeaponUpgrade.class).levelUp();
							Item.updateQuickslot();
						}
					}
					if (demonization != null && demonization.isDemonated() && hero.hasTalent(Talent.ENERGY_DRAIN)) {
						int pointUsed = hero.pointsInTalent(Talent.ENERGY_DRAIN);
						if (hero.buff(Barrier.class) == null || hero.buff(Barrier.class).shielding() < (10 * pointUsed - pointUsed)) {
							Buff.affect(hero, Barrier.class).incShield(pointUsed);
						} else {
							Buff.affect(hero, Barrier.class).setShield(10 * pointUsed);
						}
					}
					if (hero.buff(Flurry.class) != null) {
						int healAmt = 2;
						healAmt = Math.min(healAmt, hero.HT - hero.HP);
						if (healAmt > 0 && hero.isAlive()) {
							hero.HP += healAmt;
							hero.sprite.emitter().start(Speck.factory(Speck.HEALING), 0.4f, 2);
							hero.sprite.showStatus(CharSprite.POSITIVE, Integer.toString(healAmt));
						}
					}
					if (hero.pointsInTalent(Talent.JUNG_QUICK_DRAW) > 1 && hero.buff(Jung.class) != null && hero.buff(Sheathing.class) != null) {
						Buff.prolong(hero, Adrenaline.class, 4f);
					}
					if (hero.pointsInTalent(Talent.JUNG_QUICK_DRAW) > 2 && hero.buff(Jung.class) != null && hero.buff(Sheathing.class) != null) {
						Buff.affect(hero, Talent.JungQuickDrawTracker.class);
					}
					int damageBonus = 0;
					if (hero.hasTalent(Talent.POWERFUL_CRIT)) {
						damageBonus += 1 + 2 * hero.pointsInTalent(Talent.POWERFUL_CRIT);
					}
					if (hero.hasTalent(Talent.JUNG_QUICK_DRAW) && hero.buff(Jung.class) != null && hero.buff(Sheathing.class) != null) {
						damageBonus += Random.NormalIntRange(0, 20);
					}
					return Random.NormalIntRange(Math.round(0.75f * max()), max()) + damageBonus;
				} else {
					return Random.NormalIntRange( min(), max() );
				}
			} else {
				if (Random.Int(100) < -critChance) {
					hero.sprite.showStatus(CharSprite.NEUTRAL, "?");
					return Random.NormalIntRange(min(), Math.round(0.5f * max()));
				} else {
					return Random.NormalIntRange( min(), max() );
				}
			}
		} else {
			return Random.NormalIntRange( min(), max() );
		}
	}
	
	public float accuracyFactor( Char owner, Char target ) {
		return 1f;
	}
	
	public float delayFactor( Char owner ) {
		return 1f;
	}

	public int reachFactor( Char owner ){
		return 1;
	}
	
	public boolean canReach( Char owner, int target){
		if (Dungeon.level.distance( owner.pos, target ) > reachFactor(owner)){
			return false;
		} else {
			boolean[] passable = BArray.not(Dungeon.level.solid, null);
			for (Char ch : Actor.chars()) {
				if (ch != owner) passable[ch.pos] = false;
			}
			
			PathFinder.buildDistanceMap(target, passable, reachFactor(owner));
			
			return PathFinder.distance[owner.pos] <= reachFactor(owner);
		}
	}

	public int defenseFactor( Char owner ) {
		return 0;
	}
	
	public int proc( Char attacker, Char defender, int damage ) {
		return damage;
	}

	public void hitSound( float pitch ){
		Sample.INSTANCE.play(hitSound, 1, pitch * hitSoundPitch);
	}
	
}
