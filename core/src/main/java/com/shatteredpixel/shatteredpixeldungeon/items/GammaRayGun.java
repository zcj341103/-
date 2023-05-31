package com.shatteredpixel.shatteredpixeldungeon.items;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.HealingArea;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.PoisonParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class GammaRayGun extends Item {

	{
		image = ItemSpriteSheet.GAMMA_RAY_GUN;

		defaultAction = AC_SHOOT;
		usesTargeting = true;

		bones = false;
		unique = true;
	}

	private static final String AC_SHOOT = "SHOOT";

	@Override
	public ArrayList<String> actions(Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_SHOOT );
		return actions;
	}

	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_SHOOT)) {
			usesTargeting = true;
			curUser = hero;
			curItem = this;
			GameScene.selectCell(shooter);
		}
	}

	private CellSelector.Listener shooter = new CellSelector.Listener() {
		@Override
		public void onSelect( Integer target ) {
			if (target != null) {
				if (target == curUser.pos) {
					Buff.affect(curUser, HealingArea.class).setup(curUser.pos, Math.round(curUser.HT*0.3f), 1, true);
					hero.sprite.operate(curUser.pos);
				} else {
					Ballistica beam = new Ballistica(curUser.pos, target, Ballistica.PROJECTILE);
					Char ch = Actor.findChar(beam.collisionPos);
					if (ch != null) {
						if (ch.alignment == Char.Alignment.ENEMY) {
							if (hero.subClass == HeroSubClass.ANGEL) {
								Buff.affect(ch, Charm.class, Charm.DURATION/2).object = curUser.id();
								ch.sprite.centerEmitter().start( Speck.factory( Speck.HEART ), 0.2f, 3 );
							} else {
								Buff.affect(ch, Poison.class).set( 3 + Math.round(2*Dungeon.depth / 3f) );
								if (Dungeon.level.heroFOV[ch.pos]) {
									CellEmitter.center( ch.pos ).burst( PoisonParticle.SPLASH, 3 );
								}
							}
						}
						if (ch.alignment == Char.Alignment.ALLY && (ch != curUser)) { //ch != hero is always false
							int healAmt = 5+Math.round(curUser.lvl/2f);
							if (hero.hasTalent(Talent.HIGHER_HEAL)) {
								healAmt *= 1 + 0.2f * hero.pointsInTalent(Talent.HIGHER_HEAL);
							}
							healAmt = Math.min( healAmt, ch.HT - ch.HP );
							if (healAmt > 0 && ch.isAlive()) {
								ch.HP += healAmt;
								ch.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
								ch.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );
							}
						}
					}
					curUser.sprite.zap(target);
					curUser.sprite.parent.add( new Beam.GreenRay(curUser.sprite.center(), DungeonTilemap.raisedTileCenterToWorld(beam.collisionPos)) );
				}
				if (curUser.buff(GammaRayCooldown.class) != null) {
					if (curUser.buff(Poison.class) != null) {
						Buff.affect(curUser, Poison.class).extend(5+Math.round(curUser.lvl/2f));
					} else {
						Buff.affect(curUser, Poison.class).set(5+Math.round(curUser.lvl/2f));
					}
					BuffIndicator.refreshHero();
					CellEmitter.center( curUser.pos ).burst( PoisonParticle.SPLASH, 3 );
				}
				if (Random.Int(3) == 0) {
					Buff.affect(hero, GammaRayCooldown.class).set(Random.NormalIntRange(3, 5));
				}
				hero.spendAndNext(Actor.TICK);
			}
		}
		@Override
		public String prompt() {
			return Messages.get(SpiritBow.class, "prompt");
		}
	};

	public static class GammaRayCooldown extends Buff {

		int duration;

		{
			type = buffType.POSITIVE;
		}

		public void set(int time) {
			duration = time;
		}

		@Override
		public boolean act() {
			duration-=TICK;
			spend(TICK);
			if (duration <= 0) {
				detach();
			}
			return true;
		}

		private static final String DURATION  = "duration";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(DURATION, duration);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			duration = bundle.getInt( DURATION );
		}
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public int value() {
		return -1;
	}
}
