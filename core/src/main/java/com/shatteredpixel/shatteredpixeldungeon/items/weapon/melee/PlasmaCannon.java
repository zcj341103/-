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
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArtifactRecharge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Focusing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.InfiniteBullet;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Momentum;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.gunner.Riot;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Eye;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Rebel;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.BlastParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.PurpleParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SmokeParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.AmmoBelt;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfReload;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfSharpshooting;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.GoldenBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.NaturesBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.PoisonBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.WindBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ConeAOE;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.text.DecimalFormat;
import java.util.ArrayList;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;

public class PlasmaCannon extends MeleeWeapon {

    public static final String AC_SHOOT		= "SHOOT";
    public static final String AC_RELOAD = "RELOAD";

    public int max_round;
    public int initial_max_round;
    public int round = 0;
    public float reload_time;
    private static final String TXT_STATUS = "%d/%d";

    int maxDistance;
    int maxDistanceBonus;

    {
        initial_max_round = 2;
        maxDistanceBonus = buffedLvl();
        maxDistance = 5;

        defaultAction = AC_SHOOT;
        usesTargeting = true;

        image = ItemSpriteSheet.PLASMA_CANNON;
        hitSound = Assets.Sounds.HIT_CRUSH;
        hitSoundPitch = 0.8f;

        tier = 5;
    }

    private static final String ROUND = "round";
    private static final String MAX_ROUND = "max_round";
    private static final String RELOAD_TIME = "reload_time";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(MAX_ROUND, max_round);
        bundle.put(ROUND, round);
        bundle.put(RELOAD_TIME, reload_time);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        max_round = bundle.getInt(MAX_ROUND);
        round = bundle.getInt(ROUND);
        reload_time = bundle.getFloat(RELOAD_TIME);
    }

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        if (isEquipped( hero )) {
            actions.add(AC_SHOOT);
            actions.add(AC_RELOAD);
        }
        return actions;
    }

    @Override
    public void execute(Hero hero, String action) {

        super.execute(hero, action);

        if (action.equals(AC_SHOOT)) {
            if (!isEquipped( hero )) {
                usesTargeting = false;
                GLog.w(Messages.get(this, "not_equipped"));
            } else {
                if (round <= 0) {
                    reload_time = (hero.hasTalent(Talent.HEAVY_GUNNER) && Random.Int(10) < hero.pointsInTalent(Talent.HEAVY_GUNNER)) ? 0 : 3f* RingOfReload.reloadMultiplier(Dungeon.hero);
                    reload();
                } else {
                    int STRReq = this.STRReq(this.buffedLvl());
                    if (this.masteryPotionBonus) STRReq -= 2;
                    if (hero.STR() < STRReq) {
                        usesTargeting = false;
                        GLog.w(Messages.get(this, "heavy_to_shoot"));
                    } else {
                        reload_time = (hero.hasTalent(Talent.HEAVY_GUNNER) && Random.Int(10) < hero.pointsInTalent(Talent.HEAVY_GUNNER)) ? 0 : 3f* RingOfReload.reloadMultiplier(Dungeon.hero);
                        usesTargeting = true;
                        curUser = hero;
                        curItem = this;
                        GameScene.selectCell(shooter);
                    }
                }
            }
        }
        if (action.equals(AC_RELOAD)) {
            max_round = initial_max_round;
            if (Dungeon.hero.hasTalent(Talent.LARGER_MAGAZINE)) {
                max_round += 1f * Dungeon.hero.pointsInTalent(Talent.LARGER_MAGAZINE);
            }
            if (round == max_round){
                GLog.w(Messages.get(this, "already_loaded"));
            } else {
                reload();
            }
        }
    }

    public void reload() {
        max_round = initial_max_round;
        if (Dungeon.hero.hasTalent(Talent.LARGER_MAGAZINE)) {
            max_round += 1f * Dungeon.hero.pointsInTalent(Talent.LARGER_MAGAZINE);
        }
        curUser.spend(reload_time);
        curUser.busy();
        Sample.INSTANCE.play(Assets.Sounds.UNLOCK, 2, 1.1f);
        curUser.sprite.operate(curUser.pos);
        round = Math.max(max_round, round);

        GLog.i(Messages.get(this, "reloading"));

        if (Dungeon.hero.hasTalent(Talent.SAFE_RELOAD) && Dungeon.hero.buff(Talent.ReloadCooldown.class) == null) {
            Buff.affect(hero, Barrier.class).setShield(1+2*hero.pointsInTalent(Talent.SAFE_RELOAD));
            Buff.affect(hero, Talent.ReloadCooldown.class, 5f);
        }

        updateQuickslot();
    }


    public int getRound() { return this.round; }

    @Override
    public String status() {
        max_round = initial_max_round;
        if (Dungeon.hero.hasTalent(Talent.LARGER_MAGAZINE)) {
            max_round += 1f * Dungeon.hero.pointsInTalent(Talent.LARGER_MAGAZINE);
        }
        return Messages.format(TXT_STATUS, round, max_round);
    }

    public int min(int lvl) {
        return tier +
                lvl;
    }

    public int max(int lvl) {
        return 3 * (tier + 1) +
                lvl;
    }

    public int Bulletmin(int lvl) {
        return tier +
                lvl +
                RingOfSharpshooting.levelDamageBonus(Dungeon.hero);
    }

    public int Bulletmax(int lvl) {
        return Math.round((3 * (tier + 1) +
                lvl * 3 +
                RingOfSharpshooting.levelDamageBonus(Dungeon.hero)));
    }

    @Override
    public String info() {

        max_round = initial_max_round;
        if (Dungeon.hero.hasTalent(Talent.LARGER_MAGAZINE)) {
            max_round += 1f * Dungeon.hero.pointsInTalent(Talent.LARGER_MAGAZINE);
        }
        reload_time = 3f* RingOfReload.reloadMultiplier(Dungeon.hero);
        String info = desc();

        if (levelKnown) {
            info += "\n\n" + Messages.get(MeleeWeapon.class, "stats_known", tier, augment.damageFactor(min()), augment.damageFactor(max()), STRReq());
            if (STRReq() > Dungeon.hero.STR()) {
                info += " " + Messages.get(Weapon.class, "too_heavy");
            } else if (Dungeon.hero.STR() > STRReq()){
                info += " " + Messages.get(Weapon.class, "excess_str", Dungeon.hero.STR() - STRReq());
            }
            info += "\n\n" + Messages.get(PlasmaCannon.class, "stats_known",
                    Bulletmin(PlasmaCannon.this.buffedLvl()),
                    Bulletmax(PlasmaCannon.this.buffedLvl()),
                    round, max_round, new DecimalFormat("#.##").format(reload_time));
        } else {
            info += "\n\n" + Messages.get(MeleeWeapon.class, "stats_unknown", tier, min(0), max(0), STRReq(0));
            if (STRReq(0) > Dungeon.hero.STR()) {
                info += " " + Messages.get(MeleeWeapon.class, "probably_too_heavy");
            }
            info += "\n\n" + Messages.get(PlasmaCannon.class, "stats_unknown",
                    Bulletmin(0),
                    Bulletmax(0),
                    round, max_round, new DecimalFormat("#.##").format(reload_time));
        }

        String statsInfo = statsInfo();
        if (!statsInfo.equals("")) info += "\n\n" + statsInfo;

        switch (augment) {
            case SPEED:
                info += " " + Messages.get(Weapon.class, "faster");
                break;
            case DAMAGE:
                info += " " + Messages.get(Weapon.class, "stronger");
                break;
            case NONE:
        }

        if (enchantment != null && (cursedKnown || !enchantment.curse())){
            info += "\n\n" + Messages.get(Weapon.class, "enchanted", enchantment.name());
            info += " " + Messages.get(enchantment, "desc");
        }

        if (cursed && isEquipped( Dungeon.hero )) {
            info += "\n\n" + Messages.get(Weapon.class, "cursed_worn");
        } else if (cursedKnown && cursed) {
            info += "\n\n" + Messages.get(Weapon.class, "cursed");
        } else if (!isIdentified() && cursedKnown){
            info += "\n\n" + Messages.get(Weapon.class, "not_cursed");
        }

        if (Dungeon.isChallenged(Challenges.DURABILITY) && levelKnown) {
            info += "\n\n" + Messages.get(Item.class, "durability_weapon", durability(), maxDurability());
        }

        return info;
    }

    @Override
    public int targetingPos(Hero user, int dst) {
        return knockBullet().targetingPos(user, dst);
    }

    private int targetPos;

    @Override
    public int damageRoll(Char owner) {
        int damage = augment.damageFactor(super.damageRoll(owner));

        if (owner instanceof Hero) {
            int exStr = ((Hero)owner).STR() - STRReq();
            if (exStr > 0) {
                damage += Random.IntRange( 0, exStr );
            }
        }

        return damage;
    }                           //초과 힘에 따른 추가 데미지

    @Override
    protected float baseDelay(Char owner) {
        float delay = augment.delayFactor(this.DLY);
        if (owner instanceof Hero) {
            int encumbrance = STRReq() - ((Hero)owner).STR();
            if (encumbrance > 0){
                delay *= Math.pow( 1.2, encumbrance );
            }
        }
        if (Dungeon.hero.hasTalent(Talent.MARTIAL_ARTS)) {
            delay -= 0.1f * Dungeon.hero.pointsInTalent(Talent.MARTIAL_ARTS);
        }
        if (hero.buff(Riot.riotTracker.class) != null) {
            delay *= 0.5f;
        }
        return delay;
    }

    public PlasmaCannon.Bullet knockBullet(){
        return new PlasmaCannon.Bullet();
    }
    public class Bullet extends MissileWeapon {

        {
            image = ItemSpriteSheet.NOTHING; //Also See MissileSprite.setup

            hitSound = Assets.Sounds.PUFF;
            tier = 5;
        }

        @Override
        public int buffedLvl(){
            return PlasmaCannon.this.buffedLvl();
        }

        @Override
        public int damageRoll(Char owner) {
            Hero hero = (Hero)owner;
            Char enemy = hero.enemy();
            int bulletdamage = Random.NormalIntRange(Bulletmin(PlasmaCannon.this.buffedLvl()),
                    Bulletmax(PlasmaCannon.this.buffedLvl()));

            if (owner.buff(Momentum.class) != null && owner.buff(Momentum.class).freerunning()) {
                bulletdamage = Math.round(bulletdamage * (1f + 0.15f * ((Hero) owner).pointsInTalent(Talent.PROJECTILE_MOMENTUM)));
            }

            if (owner.buff(Bless.class) != null && ((Hero) owner).hasTalent(Talent.BLESSED_TALENT)) {
                bulletdamage = Math.round(bulletdamage * (1f + 0.15f * ((Hero) owner).pointsInTalent(Talent.BLESSED_TALENT)));
            }

            if (owner.buff(Focusing.class) != null) {
                bulletdamage = Math.round(bulletdamage * (1.10f + 0.05f * ((Hero) owner).pointsInTalent(Talent.ARM_VETERAN)));
            }

            if (Dungeon.hero.hasTalent(Talent.HEAVY_ENHANCE)) {
                bulletdamage *= 1f + 0.05f*Dungeon.hero.pointsInTalent(Talent.HEAVY_ENHANCE);
            }

            if (hero.buff(Recharging.class) != null) {
                bulletdamage *= 1.2f;
            }

            if (hero.buff(ArtifactRecharge.class) != null) {
                bulletdamage *= 1.2f;
            }

            if (hero.buff(Riot.riotTracker.class) != null) {
                bulletdamage *= 0.5f;
            }

            if (enemy instanceof Eye) {
                bulletdamage *= 0.5f;
            }

            if (enemy instanceof Rebel) {
                bulletdamage *= 0.2f;
            }

            return bulletdamage;
        }

        @Override
        public boolean hasEnchant(Class<? extends Enchantment> type, Char owner) {
            return PlasmaCannon.this.hasEnchant(type, owner);
        }

        @Override
        public int proc(Char attacker, Char defender, int damage) {
            SpiritBow bow = hero.belongings.getItem(SpiritBow.class);
            WindBow bow2 = hero.belongings.getItem(WindBow.class);
            GoldenBow bow3 = hero.belongings.getItem(GoldenBow.class);
            NaturesBow bow4 = hero.belongings.getItem(NaturesBow.class);
            PoisonBow bow5 = hero.belongings.getItem(PoisonBow.class);
            if (PlasmaCannon.this.enchantment == null
                    && Random.Int(3) < hero.pointsInTalent(Talent.SHARED_ENCHANTMENT)
                    && hero.buff(MagicImmune.class) == null
                    && bow != null
                    && bow.enchantment != null) {
                return bow.enchantment.proc(this, attacker, defender, damage);
            } else if (PlasmaCannon.this.enchantment == null
                    && Random.Int(3) < hero.pointsInTalent(Talent.SHARED_ENCHANTMENT)
                    && hero.buff(MagicImmune.class) == null
                    && bow2 != null
                    && bow2.enchantment != null) {
                return bow2.enchantment.proc(this, attacker, defender, damage);
            } else if (PlasmaCannon.this.enchantment == null
                    && Random.Int(3) < hero.pointsInTalent(Talent.SHARED_ENCHANTMENT)
                    && hero.buff(MagicImmune.class) == null
                    && bow3 != null
                    && bow3.enchantment != null) {
                return bow3.enchantment.proc(this, attacker, defender, damage);
            } else if (PlasmaCannon.this.enchantment == null
                    && Random.Int(3) < hero.pointsInTalent(Talent.SHARED_ENCHANTMENT)
                    && hero.buff(MagicImmune.class) == null
                    && bow4 != null
                    && bow4.enchantment != null) {
                return bow4.enchantment.proc(this, attacker, defender, damage);
            } else if (PlasmaCannon.this.enchantment == null
                    && Random.Int(3) < hero.pointsInTalent(Talent.SHARED_ENCHANTMENT)
                    && hero.buff(MagicImmune.class) == null
                    && bow5 != null
                    && bow5.enchantment != null) {
                return bow5.enchantment.proc(this, attacker, defender, damage);
            } else {
                return PlasmaCannon.this.proc(attacker, defender, damage);
            }
        }

        @Override
        public float delayFactor(Char user) {
            if (hero.buff(Riot.riotTracker.class) != null) {
                return PlasmaCannon.this.delayFactor(user)/2f;
            } else {
                return PlasmaCannon.this.delayFactor(user);
            }
        }

        @Override
        public int STRReq(int lvl) {
            return PlasmaCannon.this.STRReq();
        }

        @Override
        protected void onThrow(int cell) {
            Ballistica aim = new Ballistica(hero.pos, cell, Ballistica.WONT_STOP); //Always Projecting and no distance limit, see MissileWeapon.throwPos
            ArrayList<Char> chars = new ArrayList<>();
            int maxDist = maxDistance+maxDistanceBonus;
            int dist = Math.min(aim.dist, maxDist);
            int cells = aim.path.get(Math.min(aim.dist, dist));
            boolean terrainAffected = false;
            for (int c : aim.subPath(1, maxDist)) {

                Char ch;
                if ((ch = Actor.findChar( c )) != null) {
                    chars.add( ch );
                }

                if (Dungeon.level.flamable[c]) {

                    Dungeon.level.destroy( c );
                    GameScene.updateMap( c );
                    terrainAffected = true;

                }

                CellEmitter.center( c ).burst( PurpleParticle.BURST, Random.IntRange( 1, 2 ) );
            }
            if (terrainAffected) {
                Dungeon.observe();
            }
            curUser.sprite.parent.add(new Beam.DeathRay(curUser.sprite.center(), DungeonTilemap.raisedTileCenterToWorld( cells )));
            for (Char ch : chars) {
                int damage = damageRoll(hero);
                ch.damage(damage, hero);
                ch.sprite.centerEmitter().burst( PurpleParticle.BURST, Random.IntRange( 1, 2 ) );
                ch.sprite.flash();
            }
            if (hero.buff(InfiniteBullet.class) != null) {
                //round preserves
            } else if (hero.buff(Riot.riotTracker.class) != null && Random.Int(10) <= hero.pointsInTalent(Talent.ROUND_PRESERVE)-1) {
                //round preserves
            } else {
                if (hero.subClass == HeroSubClass.LAUNCHER && Random.Int(10) <= hero.pointsInTalent(Talent.AMMO_SAVE)) {
                    //round preserves
                } else {
                    round --;
                }
            }
            for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
                if (mob.paralysed <= 0
                        && Dungeon.level.distance(curUser.pos, mob.pos) <= 4
                        && mob.state != mob.HUNTING) {
                    mob.beckon( curUser.pos );
                }
            }
            Invisibility.dispel();
            updateQuickslot();
            if (Dungeon.isChallenged(Challenges.DURABILITY)) {
                PlasmaCannon.this.use();
            }
        }

        @Override
        public void throwSound() {
            //Play Nothing
        }

        @Override
        public void cast(final Hero user, final int dst) {
            super.cast(user, dst);
        }
    }

    private CellSelector.Listener shooter = new CellSelector.Listener() {
        @Override
        public void onSelect( Integer target ) {
            AmmoBelt.OverHeat overHeat = hero.buff(AmmoBelt.OverHeat.class);
            if (target != null) {
                if (overHeat != null && Random.Float() < AmmoBelt.OverHeat.chance) {
                    usesTargeting = false;
                    GLog.w(Messages.get(CrudePistol.class, "failed"));
                    curUser.spendAndNext(Actor.TICK);
                } else {
                    if (target != null) {
                        if (target == curUser.pos) {
                            reload();
                        } else {
                            knockBullet().cast(curUser, target);
                        }
                    }
                }
            }
        }
        @Override
        public String prompt() {
            return Messages.get(SpiritBow.class, "prompt");
        }
    };

}