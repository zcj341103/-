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

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;
import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.level;
import static com.shatteredpixel.shatteredpixeldungeon.items.Item.updateQuickslot;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Bones;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blizzard;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ConfusionGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.CorrosiveGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Inferno;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ParalyticGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Regrowth;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.SacrificialFire;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.SmokeScreen;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.StenchGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.StormCloud;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Web;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AccuracyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AdrenalineSurge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AnkhInvulnerability;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArmorEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArrowEnhance;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArtifactRecharge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AttackSpeedBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Awareness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barkskin;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Berserk;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bleeding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BlessingArea;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Combo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corrosion;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CounterAttack;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CounterAttackDef;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Demonization;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dong;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Drowsy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ElectroBullet;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.EnhancedRingsCombo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.EvasionEnhance;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.EvasiveMove;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FireBullet;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Flurry;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Focusing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Foresight;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FrostBullet;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.GhostSpawner;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.GodFury;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Haste;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.HealingArea;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.HoldFast;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Iaido;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Jung;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.KnightsBlocking;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LanceBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LargeSwordBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalCombo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MindVision;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Momentum;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Outlaw;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ParalysisTracker;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PinCushion;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.QuickStep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Regeneration;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Roots;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SerialAttack;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Shadows;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sheathing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ShieldCoolDown;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SnipersMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.StanceCooldown;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Surgery;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SurgeryTracker;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SurgeryUse;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.UpgradeShare;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.WeaponEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.gunner.ReinforcedArmor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.gunner.Riot;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.NaturesPower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Endure;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Monk;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Snake;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Tengu;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.CheckedCell;
import com.shatteredpixel.shatteredpixeldungeon.effects.Lightning;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.EnergyParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.FlameParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.PoisonParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;
import com.shatteredpixel.shatteredpixeldungeon.items.Ankh;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.Dewdrop;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap.Type;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.StunGun;
import com.shatteredpixel.shatteredpixeldungeon.items.StunGunAP;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Brimstone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Viscosity;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CapeOfThorns;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.EtherealChains;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.MasterThievesArmband;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.journal.Guidebook;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.CrystalKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.GoldenKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.Key;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.SkeletonKey;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfExperience;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfTalent;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfDivineInspiration;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfAccuracy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfArcana;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEvasion;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfForce;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFuror;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFury;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfReload;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfRush;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfSatisfying;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfSharpshooting;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfShield;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfTenacity;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfVampire;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfVorpal;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfWealth;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.GoldenBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.NaturesBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.PoisonBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.WindBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blooming;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Kinetic;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Lucky;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.*;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.LevelTransition;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.MagicalFireRoom;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ShadowCaster;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
import com.shatteredpixel.shatteredpixeldungeon.scenes.AlchemyScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.InterlevelScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.SurfaceScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.StatusPane;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndHero;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndMessage;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndResurrect;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTradeItem;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.GameMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Point;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Hero extends Char {

	{
		actPriority = HERO_PRIO;
		
		alignment = Alignment.ALLY;
	}
	
	public static final int MAX_LEVEL = 30;

	public static final int STARTING_STR = 10;
	
	private static final float TIME_TO_REST		    = 1f;
	private static final float TIME_TO_SEARCH	    = 2f;
	private static final float HUNGER_FOR_SEARCH	= 6f;

	public HeroClass heroClass = HeroClass.ROGUE;
	public HeroSubClass subClass = HeroSubClass.NONE;
	public ArmorAbility armorAbility = null;
	public ArrayList<LinkedHashMap<Talent, Integer>> talents = new ArrayList<>();
	public LinkedHashMap<Talent, Talent> metamorphedTalents = new LinkedHashMap<>();
	
	private int attackSkill = 10;
	private int defenseSkill = 5;

	public boolean ready = false;
	private boolean damageInterrupt = true;
	public HeroAction curAction = null;
	public HeroAction lastAction = null;

	private Char enemy;
	
	public boolean resting = false;
	
	public Belongings belongings;
	
	public int STR;
	
	public float awareness;
	
	public int lvl = 1;
	public int exp = 0;
	
	public int HTBoost = 0;
	
	private ArrayList<Mob> visibleEnemies;

	//This list is maintained so that some logic checks can be skipped
	// for enemies we know we aren't seeing normally, resulting in better performance
	public ArrayList<Mob> mindVisionEnemies = new ArrayList<>();

	public Hero() {
		super();

		HP = HT = (Dungeon.isChallenged(Challenges.SUPERMAN)) ? 10 : 20;
		STR = STARTING_STR;
		
		belongings = new Belongings( this );
		
		visibleEnemies = new ArrayList<>();
	}
	
	public void updateHT( boolean boostHP ){
		int curHT = HT;

		HT = (Dungeon.isChallenged(Challenges.SUPERMAN)) ? 10 : 20 + 5 * (lvl-1) + HTBoost;
		if (this.hasTalent(Talent.MAX_HEALTH)) {
			HT += 5*this.pointsInTalent(Talent.MAX_HEALTH);
		}
		float multiplier = RingOfMight.HTMultiplier(this);
		HT = Math.round(multiplier * HT);
		
		if (buff(ElixirOfMight.HTBoost.class) != null){
			HT += buff(ElixirOfMight.HTBoost.class).boost();
		}
		
		if (boostHP){
			HP += Math.max(HT - curHT, 0);
		}
		HP = Math.min(HP, HT);
	}

	public int STR() {
		int strBonus = 0;

		strBonus += RingOfMight.strengthBonus( this );
		
		AdrenalineSurge buff = buff(AdrenalineSurge.class);
		if (buff != null){
			strBonus += buff.boost();
		}

		if (hasTalent(Talent.STRONGMAN)){
			strBonus += (int)Math.floor(STR * (0.03f + 0.05f*pointsInTalent(Talent.STRONGMAN)));
		}

		return STR + strBonus;
	}

	private static final String CLASS       = "class";
	private static final String SUBCLASS    = "subClass";
	private static final String ABILITY     = "armorAbility";

	private static final String ATTACK		= "attackSkill";
	private static final String DEFENSE		= "defenseSkill";
	private static final String STRENGTH	= "STR";
	private static final String LEVEL		= "lvl";
	private static final String EXPERIENCE	= "exp";
	private static final String HTBOOST     = "htboost";
	
	@Override
	public void storeInBundle( Bundle bundle ) {

		super.storeInBundle( bundle );

		bundle.put( CLASS, heroClass );
		bundle.put( SUBCLASS, subClass );
		bundle.put( ABILITY, armorAbility );
		Talent.storeTalentsInBundle( bundle, this );
		
		bundle.put( ATTACK, attackSkill );
		bundle.put( DEFENSE, defenseSkill );
		
		bundle.put( STRENGTH, STR );
		
		bundle.put( LEVEL, lvl );
		bundle.put( EXPERIENCE, exp );
		
		bundle.put( HTBOOST, HTBoost );

		belongings.storeInBundle( bundle );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {

		lvl = bundle.getInt( LEVEL );
		exp = bundle.getInt( EXPERIENCE );

		HTBoost = bundle.getInt(HTBOOST);

		super.restoreFromBundle( bundle );

		heroClass = bundle.getEnum( CLASS, HeroClass.class );
		subClass = bundle.getEnum( SUBCLASS, HeroSubClass.class );
		armorAbility = (ArmorAbility)bundle.get( ABILITY );
		Talent.restoreTalentsFromBundle( bundle, this );
		
		attackSkill = bundle.getInt( ATTACK );
		defenseSkill = bundle.getInt( DEFENSE );
		
		STR = bundle.getInt( STRENGTH );

		belongings.restoreFromBundle( bundle );
	}
	
	public static void preview( GamesInProgress.Info info, Bundle bundle ) {
		info.level = bundle.getInt( LEVEL );
		info.str = bundle.getInt( STRENGTH );
		info.exp = bundle.getInt( EXPERIENCE );
		info.hp = bundle.getInt( Char.TAG_HP );
		info.ht = bundle.getInt( Char.TAG_HT );
		info.shld = bundle.getInt( Char.TAG_SHLD );
		info.heroClass = bundle.getEnum( CLASS, HeroClass.class );
		info.subClass = bundle.getEnum( SUBCLASS, HeroSubClass.class );
		Belongings.preview( info, bundle );
	}

	public boolean hasTalent( Talent talent ){
		return pointsInTalent(talent) > 0;
	}

	public int pointsInTalent( Talent talent ){
		for (LinkedHashMap<Talent, Integer> tier : talents){
			for (Talent f : tier.keySet()){
				if (f == talent) return tier.get(f);
			}
		}
		return 0;
	}

	public void upgradeTalent( Talent talent ){
		for (LinkedHashMap<Talent, Integer> tier : talents){
			for (Talent f : tier.keySet()){
				if (f == talent) tier.put(talent, tier.get(talent)+1);
			}
		}
		Talent.onTalentUpgraded(this, talent);
	}

	public int talentPointsSpent(int tier){
		int total = 0;
		for (int i : talents.get(tier-1).values()){
			total += i;
		}
		return total;
	}

	public int talentPointsAvailable(int tier){
		if (lvl < (Talent.tierLevelThresholds[tier] - 1)
			|| (tier == 3 && subClass == HeroSubClass.NONE)
			|| (tier == 4 && armorAbility == null)) {
			return 0;
		} else if (lvl >= Talent.tierLevelThresholds[tier+1]){
			return Talent.tierLevelThresholds[tier+1] - Talent.tierLevelThresholds[tier] - talentPointsSpent(tier) + bonusTalentPoints(tier);
		} else {
			return 1 + lvl - Talent.tierLevelThresholds[tier] - talentPointsSpent(tier) + bonusTalentPoints(tier);
		}
	}

	public int bonusTalentPoints(int tier){
		int bonusPoints = 0;
		if (lvl < (Talent.tierLevelThresholds[tier]-1)
				|| (tier == 3 && subClass == HeroSubClass.NONE)
				|| (tier == 4 && armorAbility == null)) {
			return 0;
		} else if (buff(PotionOfDivineInspiration.DivineInspirationTracker.class) != null
					&& buff(PotionOfDivineInspiration.DivineInspirationTracker.class).isBoosted(tier)) {
			bonusPoints += 2;
		}
		if (tier == 3 && buff(ElixirOfTalent.BonusTalentTracker.class) != null) {
			bonusPoints += 4;
		}
		return bonusPoints;
	}
	
	public String className() {
		return subClass == null || subClass == HeroSubClass.NONE ? heroClass.title() : subClass.title();
	}

	@Override
	public String name(){
		return className();
	}

	@Override
	public void hitSound(float pitch) {
		if ( belongings.weapon() != null ){
			belongings.weapon().hitSound(pitch);
		} else if (RingOfForce.getBuffedBonus(this, RingOfForce.Force.class) > 0) {
			//pitch deepens by 2.5% (additive) per point of strength, down to 75%
			super.hitSound( pitch * GameMath.gate( 0.75f, 1.25f - 0.025f*STR(), 1f) );
		} else {
			super.hitSound(pitch * 1.1f);
		}
	}

	@Override
	public boolean blockSound(float pitch) {
		if ( (belongings.weapon() != null && belongings.weapon().defenseFactor(this) >= 4) || (hero.buff(KnightsBlocking.class) != null && hero.buff(KnightsBlocking.class).level() >= 4) ){
			Sample.INSTANCE.play( Assets.Sounds.HIT_PARRY, 1, pitch);
			return true;
		}
		return super.blockSound(pitch);
	}

	public void live() {
		for (Buff b : buffs()){
			if (!b.revivePersists) b.detach();
		}
		Buff.affect( this, Regeneration.class );
		Buff.affect( this, Hunger.class );
	}
	
	public int tier() {
		Armor armor = belongings.armor();
		if (armor instanceof ClassArmor){
			return 6;
		} else if (armor != null){
			return armor.tier;
		} else {
			return 0;
		}
	}
	
	public boolean shoot( Char enemy, MissileWeapon wep ) {

		this.enemy = enemy;
		boolean wasEnemy = enemy.alignment == Alignment.ENEMY;

		//temporarily set the hero's weapon to the missile weapon being used
		//TODO improve this!
		belongings.thrownWeapon = wep;
		boolean hit = attack( enemy );
		Invisibility.dispel();
		belongings.thrownWeapon = null;

		if (hit && subClass == HeroSubClass.GLADIATOR && wasEnemy){
			Buff.affect( this, Combo.class ).hit( enemy );
		}

		return hit;
	}

	@Override
	public int attackSkill( Char target ) {
		KindOfWeapon wep = belongings.weapon();
		KindOfWeapon equippedWep = belongings.weapon;

		float accuracy = 1;
		accuracy *= RingOfAccuracy.accuracyMultiplier( this );

		if (Dungeon.isChallenged(Challenges.SUPERMAN)) {
			accuracy *= 2;
		}

		if (hero.buff(LargeSwordBuff.class) != null) {
			accuracy *= hero.buff(LargeSwordBuff.class).getAccuracyFactor();
		}

		if (hero.buff(Surgery.class) != null) {
			accuracy *= 1 + 0.02 * hero.buff(Surgery.class).getCount();
		}

		if (hero.hasTalent(Talent.ACC_ENHANCE)) {
			accuracy *= 1 + 0.05f * hero.pointsInTalent(Talent.ACC_ENHANCE);
		}

		if (hero.buff(Sheathing.class) != null) {
			if (hero.buff(Iaido.class) != null) {
				accuracy *= 1.2f + 0.1f * hero.buff(Iaido.class).getCount();
			} else {
				accuracy *= 1.2f;
			}
		}

		if (buff(CounterAttackDef.class) != null) {
			accuracy = INFINITE_ACCURACY;
		}

		if (buff(SurgeryTracker.class) != null) {
			accuracy = INFINITE_ACCURACY;
		}

		if (hero.buff(Jung.class) != null && hero.buff(Sheathing.class) != null && wep instanceof MeleeWeapon) {
			accuracy = INFINITE_ACCURACY;
		}

		if (hero.buff(GodFury.class) != null) {
			accuracy = INFINITE_ACCURACY;
		}

		if (hero.buff(AccuracyBuff.class) != null) {
			accuracy *= 1 + 0.05f * hero.buff(AccuracyBuff.class).getCount();
		}
		
		if (wep instanceof MissileWeapon){
			if ((wep instanceof AutoHandgun.Bullet && ((AutoHandgun)hero.belongings.weapon).auto)) {
				if (Dungeon.level.adjacent( pos, target.pos )) {
					accuracy *= 1.25f;
				} else {
					accuracy *= 0.50f;
				}
			}

			if ((wep instanceof AutoHandgun.Bullet && !((AutoHandgun)hero.belongings.weapon).auto)) {
				accuracy *= 1.25f;
			}

			if ((wep instanceof CrudePistol.Bullet && ((CrudePistol)hero.belongings.weapon).short_barrel)
					|| (wep instanceof Pistol.Bullet && ((Pistol)hero.belongings.weapon).short_barrel)
					|| (wep instanceof GoldenPistol.Bullet && ((GoldenPistol)hero.belongings.weapon).short_barrel)
					|| (wep instanceof Handgun.Bullet && ((Handgun)hero.belongings.weapon).short_barrel)
					|| (wep instanceof Magnum.Bullet && ((Magnum)hero.belongings.weapon).short_barrel)
					|| (wep instanceof TacticalHandgun.Bullet && ((TacticalHandgun)hero.belongings.weapon).short_barrel)
					|| (wep instanceof AutoHandgun.Bullet && ((AutoHandgun)hero.belongings.weapon).short_barrel)
					|| (wep instanceof DualPistol.Bullet && ((DualPistol)hero.belongings.weapon).short_barrel)
					|| (wep instanceof SubMachinegun.Bullet && ((SubMachinegun)hero.belongings.weapon).short_barrel)
					|| (wep instanceof AssultRifle.Bullet && ((AssultRifle)hero.belongings.weapon).short_barrel)
					|| (wep instanceof HeavyMachinegun.Bullet && ((HeavyMachinegun)hero.belongings.weapon).short_barrel)
					|| (wep instanceof MiniGun.Bullet && ((MiniGun)hero.belongings.weapon).short_barrel)
					|| (wep instanceof AutoRifle.Bullet && ((AutoRifle)hero.belongings.weapon).short_barrel)
					|| (wep instanceof Revolver.Bullet && ((Revolver)hero.belongings.weapon).short_barrel)
					|| (wep instanceof HuntingRifle.Bullet && ((HuntingRifle)hero.belongings.weapon).short_barrel)
					|| (wep instanceof Carbine.Bullet && ((Carbine)hero.belongings.weapon).short_barrel)
					|| (wep instanceof SniperRifle.Bullet && ((SniperRifle)hero.belongings.weapon).short_barrel)
					|| (wep instanceof AntimaterRifle.Bullet && ((AntimaterRifle)hero.belongings.weapon).short_barrel)
					|| (wep instanceof MarksmanRifle.Bullet && ((MarksmanRifle)hero.belongings.weapon).short_barrel)
					|| (wep instanceof WA2000.Bullet && ((WA2000)hero.belongings.weapon).short_barrel)
					|| (wep instanceof ShotGun.Bullet && ((ShotGun)hero.belongings.weapon).short_barrel)
					|| (wep instanceof KSG.Bullet && ((KSG)hero.belongings.weapon).short_barrel)
			) {
				if (Dungeon.level.adjacent( pos, target.pos )) {
					accuracy *= 1.25f;
				} else {
					accuracy *= 0.75f;
				}
			}

			if ((wep instanceof CrudePistol.Bullet && ((CrudePistol)hero.belongings.weapon).long_barrel)
					|| (wep instanceof Pistol.Bullet && ((Pistol)hero.belongings.weapon).long_barrel)
					|| (wep instanceof GoldenPistol.Bullet && ((GoldenPistol)hero.belongings.weapon).long_barrel)
					|| (wep instanceof Handgun.Bullet && ((Handgun)hero.belongings.weapon).long_barrel)
					|| (wep instanceof Magnum.Bullet && ((Magnum)hero.belongings.weapon).long_barrel)
					|| (wep instanceof TacticalHandgun.Bullet && ((TacticalHandgun)hero.belongings.weapon).long_barrel)
					|| (wep instanceof AutoHandgun.Bullet && ((AutoHandgun)hero.belongings.weapon).long_barrel)
					|| (wep instanceof DualPistol.Bullet && ((DualPistol)hero.belongings.weapon).long_barrel)
					|| (wep instanceof SubMachinegun.Bullet && ((SubMachinegun)hero.belongings.weapon).long_barrel)
					|| (wep instanceof AssultRifle.Bullet && ((AssultRifle)hero.belongings.weapon).long_barrel)
					|| (wep instanceof HeavyMachinegun.Bullet && ((HeavyMachinegun)hero.belongings.weapon).long_barrel)
					|| (wep instanceof MiniGun.Bullet && ((MiniGun)hero.belongings.weapon).long_barrel)
					|| (wep instanceof AutoRifle.Bullet && ((AutoRifle)hero.belongings.weapon).long_barrel)
					|| (wep instanceof Revolver.Bullet && ((Revolver)hero.belongings.weapon).long_barrel)
					|| (wep instanceof HuntingRifle.Bullet && ((HuntingRifle)hero.belongings.weapon).long_barrel)
					|| (wep instanceof Carbine.Bullet && ((Carbine)hero.belongings.weapon).long_barrel)
					|| (wep instanceof SniperRifle.Bullet && ((SniperRifle)hero.belongings.weapon).long_barrel)
					|| (wep instanceof AntimaterRifle.Bullet && ((AntimaterRifle)hero.belongings.weapon).long_barrel)
					|| (wep instanceof MarksmanRifle.Bullet && ((MarksmanRifle)hero.belongings.weapon).long_barrel)
					|| (wep instanceof WA2000.Bullet && ((WA2000)hero.belongings.weapon).long_barrel)
					|| (wep instanceof ShotGun.Bullet && ((ShotGun)hero.belongings.weapon).long_barrel)
					|| (wep instanceof KSG.Bullet && ((KSG)hero.belongings.weapon).long_barrel)
			) {
				if (Dungeon.level.adjacent( pos, target.pos )) {
					accuracy *= 0.75f;
				} else {
					accuracy *= 1.1f;
				}
			}

			if ((wep instanceof CrudePistol.Bullet && ((CrudePistol)hero.belongings.weapon).magazine)
					|| (wep instanceof Pistol.Bullet && ((Pistol)hero.belongings.weapon).magazine)
					|| (wep instanceof GoldenPistol.Bullet && ((GoldenPistol)hero.belongings.weapon).magazine)
					|| (wep instanceof Handgun.Bullet && ((Handgun)hero.belongings.weapon).magazine)
					|| (wep instanceof Magnum.Bullet && ((Magnum)hero.belongings.weapon).magazine)
					|| (wep instanceof TacticalHandgun.Bullet && ((TacticalHandgun)hero.belongings.weapon).magazine)
					|| (wep instanceof AutoHandgun.Bullet && ((AutoHandgun)hero.belongings.weapon).magazine)
					|| (wep instanceof DualPistol.Bullet && ((DualPistol)hero.belongings.weapon).magazine)
					|| (wep instanceof SubMachinegun.Bullet && ((SubMachinegun)hero.belongings.weapon).magazine)
					|| (wep instanceof AssultRifle.Bullet && ((AssultRifle)hero.belongings.weapon).magazine)
					|| (wep instanceof HeavyMachinegun.Bullet && ((HeavyMachinegun)hero.belongings.weapon).magazine)
					|| (wep instanceof MiniGun.Bullet && ((MiniGun)hero.belongings.weapon).magazine)
					|| (wep instanceof AutoRifle.Bullet && ((AutoRifle)hero.belongings.weapon).magazine)
					|| (wep instanceof Revolver.Bullet && ((Revolver)hero.belongings.weapon).magazine)
					|| (wep instanceof HuntingRifle.Bullet && ((HuntingRifle)hero.belongings.weapon).magazine)
					|| (wep instanceof Carbine.Bullet && ((Carbine)hero.belongings.weapon).magazine)
					|| (wep instanceof SniperRifle.Bullet && ((SniperRifle)hero.belongings.weapon).magazine)
					|| (wep instanceof AntimaterRifle.Bullet && ((AntimaterRifle)hero.belongings.weapon).magazine)
					|| (wep instanceof MarksmanRifle.Bullet && ((MarksmanRifle)hero.belongings.weapon).magazine)
					|| (wep instanceof WA2000.Bullet && ((WA2000)hero.belongings.weapon).magazine)
					|| (wep instanceof ShotGun.Bullet && ((ShotGun)hero.belongings.weapon).magazine)
					|| (wep instanceof KSG.Bullet && ((KSG)hero.belongings.weapon).magazine)
			) {
				if (!(Dungeon.level.adjacent( pos, target.pos ))) {
					accuracy *= 0.85f;
				}
			}

			if ((wep instanceof CrudePistol.Bullet && ((CrudePistol)hero.belongings.weapon).light)
					|| (wep instanceof Pistol.Bullet && ((Pistol)hero.belongings.weapon).light)
					|| (wep instanceof GoldenPistol.Bullet && ((GoldenPistol)hero.belongings.weapon).light)
					|| (wep instanceof Handgun.Bullet && ((Handgun)hero.belongings.weapon).light)
					|| (wep instanceof Magnum.Bullet && ((Magnum)hero.belongings.weapon).light)
					|| (wep instanceof TacticalHandgun.Bullet && ((TacticalHandgun)hero.belongings.weapon).light)
					|| (wep instanceof AutoHandgun.Bullet && ((AutoHandgun)hero.belongings.weapon).light)
					|| (wep instanceof DualPistol.Bullet && ((DualPistol)hero.belongings.weapon).light)
					|| (wep instanceof SubMachinegun.Bullet && ((SubMachinegun)hero.belongings.weapon).light)
					|| (wep instanceof AssultRifle.Bullet && ((AssultRifle)hero.belongings.weapon).light)
					|| (wep instanceof HeavyMachinegun.Bullet && ((HeavyMachinegun)hero.belongings.weapon).light)
					|| (wep instanceof MiniGun.Bullet && ((MiniGun)hero.belongings.weapon).light)
					|| (wep instanceof AutoRifle.Bullet && ((AutoRifle)hero.belongings.weapon).light)
					|| (wep instanceof Revolver.Bullet && ((Revolver)hero.belongings.weapon).light)
					|| (wep instanceof HuntingRifle.Bullet && ((HuntingRifle)hero.belongings.weapon).light)
					|| (wep instanceof Carbine.Bullet && ((Carbine)hero.belongings.weapon).light)
					|| (wep instanceof SniperRifle.Bullet && ((SniperRifle)hero.belongings.weapon).light)
					|| (wep instanceof AntimaterRifle.Bullet && ((AntimaterRifle)hero.belongings.weapon).light)
					|| (wep instanceof MarksmanRifle.Bullet && ((MarksmanRifle)hero.belongings.weapon).light)
					|| (wep instanceof WA2000.Bullet && ((WA2000)hero.belongings.weapon).light)
					|| (wep instanceof ShotGun.Bullet && ((ShotGun)hero.belongings.weapon).light)
					|| (wep instanceof KSG.Bullet && ((KSG)hero.belongings.weapon).light)
			) {
				accuracy *= 0.9f;
			}

			if ((wep instanceof CrudePistol.Bullet && ((CrudePistol)hero.belongings.weapon).heavy)
					|| (wep instanceof Pistol.Bullet && ((Pistol)hero.belongings.weapon).heavy)
					|| (wep instanceof GoldenPistol.Bullet && ((GoldenPistol)hero.belongings.weapon).heavy)
					|| (wep instanceof Handgun.Bullet && ((Handgun)hero.belongings.weapon).heavy)
					|| (wep instanceof Magnum.Bullet && ((Magnum)hero.belongings.weapon).heavy)
					|| (wep instanceof TacticalHandgun.Bullet && ((TacticalHandgun)hero.belongings.weapon).heavy)
					|| (wep instanceof AutoHandgun.Bullet && ((AutoHandgun)hero.belongings.weapon).heavy)
					|| (wep instanceof DualPistol.Bullet && ((DualPistol)hero.belongings.weapon).heavy)
					|| (wep instanceof SubMachinegun.Bullet && ((SubMachinegun)hero.belongings.weapon).heavy)
					|| (wep instanceof AssultRifle.Bullet && ((AssultRifle)hero.belongings.weapon).heavy)
					|| (wep instanceof HeavyMachinegun.Bullet && ((HeavyMachinegun)hero.belongings.weapon).heavy)
					|| (wep instanceof MiniGun.Bullet && ((MiniGun)hero.belongings.weapon).heavy)
					|| (wep instanceof AutoRifle.Bullet && ((AutoRifle)hero.belongings.weapon).heavy)
					|| (wep instanceof Revolver.Bullet && ((Revolver)hero.belongings.weapon).heavy)
					|| (wep instanceof HuntingRifle.Bullet && ((HuntingRifle)hero.belongings.weapon).heavy)
					|| (wep instanceof Carbine.Bullet && ((Carbine)hero.belongings.weapon).heavy)
					|| (wep instanceof SniperRifle.Bullet && ((SniperRifle)hero.belongings.weapon).heavy)
					|| (wep instanceof AntimaterRifle.Bullet && ((AntimaterRifle)hero.belongings.weapon).heavy)
					|| (wep instanceof MarksmanRifle.Bullet && ((MarksmanRifle)hero.belongings.weapon).heavy)
					|| (wep instanceof WA2000.Bullet && ((WA2000)hero.belongings.weapon).heavy)
					|| (wep instanceof ShotGun.Bullet && ((ShotGun)hero.belongings.weapon).heavy)
					|| (wep instanceof KSG.Bullet && ((KSG)hero.belongings.weapon).heavy)
			) {
				accuracy *= 1.1f;
			}

			float accMulti;

			if (Dungeon.level.adjacent( pos, target.pos )) {
				accMulti = (0.5f + 0.2f*pointsInTalent(Talent.POINT_BLANK));
				if ((wep instanceof ShotGun.Bullet && !(equippedWep instanceof ShotGunAP))
						|| (wep instanceof KSG.Bullet && !(equippedWep instanceof KSGAP))) {
					accMulti += 3;
				}
				if (wep instanceof Revolver.Bullet
						|| wep instanceof HuntingRifle.Bullet
						|| wep instanceof Carbine.Bullet
						|| wep instanceof SniperRifle.Bullet
						|| wep instanceof AntimaterRifle.Bullet
						|| wep instanceof MarksmanRifle.Bullet
						|| wep instanceof WA2000.Bullet){
					accMulti = 0;
				}
			} else {
				accMulti = 1.5f;
				if (wep instanceof Revolver.Bullet
						|| wep instanceof Carbine.Bullet
						|| wep instanceof SniperRifle.Bullet
						|| wep instanceof AntimaterRifle.Bullet
						|| wep instanceof MarksmanRifle.Bullet
						|| wep instanceof WA2000.Bullet) {
					accMulti = 2f;
				}
				if (wep instanceof CrudePistol.Bullet
						|| wep instanceof Pistol.Bullet
						|| wep instanceof GoldenPistol.Bullet
						|| wep instanceof Handgun.Bullet
						|| wep instanceof Magnum.Bullet
						|| wep instanceof AutoHandgun.Bullet
						|| wep instanceof DualPistol.Bullet
						|| wep instanceof SubMachinegun.Bullet
						|| wep instanceof AssultRifle.Bullet
						|| wep instanceof HeavyMachinegun.Bullet
						|| wep instanceof AutoRifle.Bullet
						|| wep instanceof MiniGun.Bullet
						|| wep instanceof RocketLauncher.Rocket
						|| wep instanceof ShotGun.Bullet
						|| wep instanceof KSG.Bullet
				) {
					accMulti = 1f;
				}
				if ((wep instanceof ShotGun.Bullet && !(equippedWep instanceof ShotGunAP))
						|| (wep instanceof KSG.Bullet && !(equippedWep instanceof KSGAP))) {
					accMulti = 0;
				}
				if (wep instanceof TacticalHandgun.Bullet) {
					accMulti = 1.3f;
				}
				if (wep instanceof TacticalShield.Bullet) {
					accMulti = 0.7f;
				}
			}

			accuracy *= accMulti;

			if (this.hasTalent(Talent.BULLET_FOCUS)) {
				if (wep instanceof CrudePistol.Bullet
						|| wep instanceof Pistol.Bullet
						|| wep instanceof GoldenPistol.Bullet
						|| wep instanceof Handgun.Bullet
						|| wep instanceof Magnum.Bullet
						|| wep instanceof TacticalHandgun.Bullet
						|| wep instanceof AutoHandgun.Bullet
						|| wep instanceof DualPistol.Bullet
						|| wep instanceof SubMachinegun.Bullet
						|| wep instanceof AssultRifle.Bullet
						|| wep instanceof HeavyMachinegun.Bullet
						|| wep instanceof MiniGun.Bullet
						|| wep instanceof AutoRifle.Bullet
						|| wep instanceof Revolver.Bullet
						|| wep instanceof HuntingRifle.Bullet
						|| wep instanceof Carbine.Bullet
						|| wep instanceof SniperRifle.Bullet
						|| wep instanceof AntimaterRifle.Bullet
						|| wep instanceof MarksmanRifle.Bullet
						|| wep instanceof WA2000.Bullet
						|| wep instanceof RocketLauncher.Rocket
						|| wep instanceof RPG7.Rocket
						|| wep instanceof GrenadeLauncher.Rocket
						|| wep instanceof GrenadeLauncherAP.Rocket
						|| wep instanceof GrenadeLauncherHP.Rocket
						|| wep instanceof TacticalShield.Bullet
				) {
					accuracy *= 1.05f + 0.05f * hero.pointsInTalent(Talent.BULLET_FOCUS);
				}
			}
		}

		if (Dungeon.hero.hasTalent(Talent.ENHANCED_FOCUSING)) {
			accuracy *= 1f + (0.1f * hero.pointsInTalent(Talent.ENHANCED_FOCUSING));
		}

		if (Dungeon.hero.hasTalent(Talent.ACC_PRACTICE)) {
			if (wep instanceof SubMachinegun.Bullet
					|| wep instanceof AssultRifle.Bullet
					|| wep instanceof HeavyMachinegun.Bullet
					|| wep instanceof MiniGun.Bullet
					|| wep instanceof AutoRifle.Bullet
			) {
				accuracy *= 1f + (0.1f * hero.pointsInTalent(Talent.ACC_PRACTICE));
			}
		}

		if (wep instanceof CrudePistol.Bullet
				|| wep instanceof Pistol.Bullet
				|| wep instanceof GoldenPistol.Bullet
				|| wep instanceof Handgun.Bullet
				|| wep instanceof Magnum.Bullet
				|| wep instanceof TacticalHandgun.Bullet
				|| wep instanceof AutoHandgun.Bullet
		) {
			if (buff(FireBullet.class) != null) buff(FireBullet.class).proc(enemy);
			if (buff(FrostBullet.class) != null) buff(FrostBullet.class).proc(enemy);
			if (buff(ElectroBullet.class) != null) buff(ElectroBullet.class).proc(enemy);
			if (buff(Talent.RollingTracker.class) != null) accuracy *= 1.5f;
		}

		if (hero.buff(Riot.riotTracker.class) != null && hero.hasTalent(Talent.SHOT_CONCENTRATION)) {
			if (wep instanceof CrudePistol.Bullet
			 || wep instanceof Pistol.Bullet
			 || wep instanceof GoldenPistol.Bullet
			 || wep instanceof Handgun.Bullet
			 || wep instanceof Magnum.Bullet
			 || wep instanceof TacticalHandgun.Bullet
			 || wep instanceof AutoHandgun.Bullet
			 || wep instanceof DualPistol.Bullet
			 || wep instanceof SubMachinegun.Bullet
			 || wep instanceof AssultRifle.Bullet
			 || wep instanceof HeavyMachinegun.Bullet
			 || wep instanceof MiniGun.Bullet
			 || wep instanceof AutoRifle.Bullet
			 || wep instanceof Revolver.Bullet
			 || wep instanceof HuntingRifle.Bullet
			 || wep instanceof Carbine.Bullet
			 || wep instanceof SniperRifle.Bullet
			 || wep instanceof AntimaterRifle.Bullet
			 || wep instanceof MarksmanRifle.Bullet
			 || wep instanceof WA2000.Bullet
			 || wep instanceof ShotGun.Bullet
			 || wep instanceof KSG.Bullet
			 || wep instanceof RocketLauncher.Rocket
			 || wep instanceof RPG7.Rocket
			 //|| wep instanceof FlameThrower.Bullet
			 //|| wep instanceof FlameThrowerAP.Bullet
			 //|| wep instanceof FlameThrowerHP.Bullet
			 //|| wep instanceof PlasmaCannon.Bullet
			 //|| wep instanceof PlasmaCannonAP.Bullet
			 //|| wep instanceof PlasmaCannonHP.Bullet
			 || wep instanceof GrenadeLauncher.Rocket
			 || wep instanceof GrenadeLauncherAP.Rocket
			 || wep instanceof GrenadeLauncherHP.Rocket
			) {
				if (Random.Int(4) < hero.pointsInTalent(Talent.SHOT_CONCENTRATION)) {
					Riot.riotTracker riot = hero.buff(Riot.riotTracker.class);
					riot.extend();
				}
			}
		}
		
		if (wep != null) {
			return (int)(attackSkill * accuracy * wep.accuracyFactor( this, target ));
		} else {
			return (int)(attackSkill * accuracy);
		}
	}
	
	@Override
	public int defenseSkill( Char enemy ) {

		if (buff(Combo.ParryTracker.class) != null){
			if (canAttack(enemy)){
				Buff.affect(this, Combo.RiposteTracker.class).enemy = enemy;
			}
			return INFINITE_EVASION;
		}

		if (buff(CounterAttack.class) != null){
			if (canAttack(enemy)){
				if (enemy instanceof Monk) {
					if (enemy.buff(Monk.Focus.class) != null) enemy.buff(Monk.Focus.class).detach();
				}
				Buff.affect(this, CounterAttackDef.class).enemy = enemy;
				Sample.INSTANCE.play(Assets.Sounds.HIT_PARRY);
				if (hero.hasTalent(Talent.MYSTICAL_COUNTER)) {
					for (Buff b : hero.buffs()){
						if (b instanceof Artifact.ArtifactBuff && !((Artifact.ArtifactBuff) b).isCursed() ) {
							((Artifact.ArtifactBuff) b).charge(hero, hero.pointsInTalent(Talent.MYSTICAL_COUNTER)/2f);
						}
					}
				}
			}
			return INFINITE_EVASION;
		}

		if (buff(EvasiveMove.class) != null) {
			return INFINITE_EVASION;
		}
		
		float evasion = defenseSkill;
		
		evasion *= RingOfEvasion.evasionMultiplier( this );

		if (hero.hasTalent(Talent.SWIFT_MOVEMENT)) {
			evasion += hero.STR()-10;
		}

		if (buff(EvasionEnhance.class) != null) {
			evasion *= 1.2f;
		}

		if (Dungeon.isChallenged(Challenges.SUPERMAN)) {
			evasion *= 3;
		}

		if (hero.hasTalent(Talent.EVA_ENHANCE)) {
			evasion *= 1 + 0.05f * hero.pointsInTalent(Talent.EVA_ENHANCE);
		}
		
		if (paralysed > 0) {
			evasion /= 2;
		}

		if (belongings.armor() != null) {
			evasion = belongings.armor().evasionFactor(this, evasion);
		}

		if (belongings.artifact() instanceof CapeOfThorns && belongings.artifact().cursed) {
			evasion /= 2;
		}

		return Math.round(evasion);
	}

	@Override
	public String defenseVerb() {
		Combo.ParryTracker parry = buff(Combo.ParryTracker.class);
		if (subClass ==HeroSubClass.GLADIATOR) {
			if (parry == null){
				return super.defenseVerb();
			} else {
				parry.parried = true;
				if (buff(Combo.class).getComboCount() + hero.pointsInTalent(Talent.SKILL_ENHANCE) < 9 || pointsInTalent(Talent.ENHANCED_COMBO) < 2) {
					parry.detach();
				}
				return Messages.get(Monk.class, "parried");
			}
		}

		CounterAttack counter = buff(CounterAttack.class);
		if (subClass == HeroSubClass.FORTRESS) {
			if (counter == null) {
				return super.defenseVerb();
			} else {
				counter.detach();
				return Messages.get(Monk.class, "parried");
			}
		}

		return super.defenseVerb();
	}


	@Override
	public int drRoll() {
		int dr = 0;

		if (belongings.armor() != null) {
			int armDr = Random.NormalIntRange( belongings.armor().DRMin(), belongings.armor().DRMax());
			if (STR() < belongings.armor().STRReq()){
				armDr -= 2*(belongings.armor().STRReq() - STR());
			}
			if (armDr > 0) dr += armDr;
		}
		if (belongings.weapon() != null)  {
			int wepDr = Random.NormalIntRange( 0 , belongings.weapon().defenseFactor( this ) );
			if (STR() < ((Weapon)belongings.weapon()).STRReq()){
				wepDr -= 2*(((Weapon)belongings.weapon()).STRReq() - STR());
			}
			if (wepDr > 0) dr += wepDr;
		}

		if (buff(HoldFast.class) != null){
			dr += Random.NormalIntRange(0, 2*pointsInTalent(Talent.HOLD_FAST));
		}

		if (buff(Sheathing.class) != null) {
			dr += Random.NormalIntRange(0, 5*pointsInTalent(Talent.PARRY));
		}

		if (hasTalent(Talent.ARMOR_ENHANCE)) {
			dr += Random.NormalIntRange(0, 2);
			if (pointsInTalent(Talent.ARMOR_ENHANCE) > 1) {
				dr += 1;
			}
		}

		dr += RingOfShield.armorMultiplier( this );
		
		return dr;
	}
	
	@Override
	public int damageRoll() {
		KindOfWeapon wep = belongings.weapon();
		int dmg;

		if (wep != null) {
			dmg = wep.damageRoll( this );
			if (!(wep instanceof MissileWeapon)) dmg += RingOfForce.armedDamageBonus(this);
		} else {
			if (enemy.buff(AllyBuff.AllyBuffTracker.class) != null) {
				dmg = 0;
				enemy.buff(AllyBuff.AllyBuffTracker.class).detach();
			} else {
				dmg = RingOfForce.damageRoll(this);
			}
		}
		if (dmg < 0) dmg = 0;
		
		return dmg;
	}
	
	@Override
	public float speed() {

		float speed = super.speed();

		speed *= RingOfHaste.speedMultiplier(this);

		if (hero.buff(ReinforcedArmor.reinforcedArmorTracker.class) != null && hero.hasTalent(Talent.PLATE_ADD)) {
			speed *= (1 - hero.pointsInTalent(Talent.PLATE_ADD)/8f);
		}

		if (hero.hasTalent(Talent.DONG_SHEATHING) && hero.buff(Dong.class) != null && hero.buff(Sheathing.class) != null) {
			speed *= 0.25f + 0.25f * hero.pointsInTalent(Talent.DONG_SHEATHING);
		}

		if (hero.buff(Flurry.class) != null) {
			speed *= 1 + pointsInTalent(Talent.FLURRY);
		}

		if (hero.subClass == HeroSubClass.RANGER) {
			if (hero.belongings.weapon instanceof CrudePistol
			 || hero.belongings.weapon instanceof Pistol
			 || hero.belongings.weapon instanceof GoldenPistol
			 || hero.belongings.weapon instanceof Handgun
			 || hero.belongings.weapon instanceof Magnum
			 || hero.belongings.weapon instanceof TacticalHandgun
			 || hero.belongings.weapon instanceof DualPistol
			 || hero.belongings.weapon instanceof AutoHandgun
			) {
				speed *= 1.1f;
			}
		}

		if (hero.hasTalent(Talent.MOVESPEED_ENHANCE)) {
			speed *= 1 + 0.1*hero.pointsInTalent(Talent.MOVESPEED_ENHANCE);
		}

		if (hero.buff(Riot.riotTracker.class) != null && hero.hasTalent(Talent.HASTE_MOVE)) {
			speed *= 1f + 0.25f * hero.pointsInTalent(Talent.HASTE_MOVE);
		}

		if (belongings.armor() != null) {
			speed = belongings.armor().speedFactor(this, speed);
		}
		
		Momentum momentum = buff(Momentum.class);
		if (momentum != null){
			((HeroSprite)sprite).sprint( momentum.freerunning() ? 1.5f : 1f );
			speed *= momentum.speedMultiplier();
		} else {
			((HeroSprite)sprite).sprint( 1f );
		}

		NaturesPower.naturesPowerTracker natStrength = buff(NaturesPower.naturesPowerTracker.class);
		if (natStrength != null){
			speed *= (2f + 0.25f*pointsInTalent(Talent.GROWING_POWER));
		}

		speed = AscensionChallenge.modifyHeroSpeed(speed);
		
		return speed;
	}

	@Override
	public boolean canSurpriseAttack(){
		if (belongings.weapon() == null || !(belongings.weapon() instanceof Weapon))   return true;
		if (STR() < ((Weapon)belongings.weapon()).STRReq())                            return false;
		if (belongings.weapon() instanceof Flail)                                      return false;
		if (belongings.weapon() instanceof ChainFlail)                                 return false;
		if (belongings.weapon() instanceof MiniGun.Bullet)				          	   return false;
		if (belongings.weapon() instanceof ShotGun.Bullet) {
			if (belongings.weapon instanceof ShotGunAP) {
				return true;
			} else {
				return false;
			}
		}
		if (belongings.weapon() instanceof KSG.Bullet){
			if (belongings.weapon instanceof KSGAP) {
				return true;
			} else {
				return false;
			}
		}
		//if (belongings.weapon() instanceof GrenadeLauncher.Rocket)					return false;
		//if (belongings.weapon() instanceof GrenadeLauncherAP.Rocket)					return false;
		//if (belongings.weapon() instanceof GrenadeLauncherHP.Rocket)					return false;  			//폭발류도 기습 가능

		return super.canSurpriseAttack();
	}

	public boolean canAttack(Char enemy){
		if (enemy == null || pos == enemy.pos || !Actor.chars().contains(enemy)) {
			return false;
		}

		//can always attack adjaceadjacent enemies
		if (Dungeon.level.adjacent(pos, enemy.pos)) {
			return true;
		}

		KindOfWeapon wep = Dungeon.hero.belongings.weapon();

		if (wep != null){
			return wep.canReach(this, enemy.pos);
		} else {
			return false;
		}
	}
	
	public float attackDelay() {
		if (buff(Talent.LethalMomentumTracker.class) != null){
			buff(Talent.LethalMomentumTracker.class).detach();
			return 0;
		}

		if (buff(Talent.DetactiveSlashingTracker.class) != null && buff(Sheathing.class) != null){
			buff(Talent.DetactiveSlashingTracker.class).detach();
			return 0;
		}

		if (buff(Talent.CounterAttackTracker.class) != null && hero.belongings.weapon == null) {
			buff(Talent.CounterAttackTracker.class).detach();
			return 0;
		}

		if (buff(Talent.MysticalPunchTracker.class) != null && hero.belongings.weapon == null) {
			buff(Talent.MysticalPunchTracker.class).detach();
			return 0;
		}

		if (buff(Sheathing.class) != null && buff(Jung.class) != null) {
			if (buff(Talent.JungQuickDrawTracker.class) == null) {
				hero.buff(Jung.class).detach();
				Buff.affect(hero, Dong.class);
				Buff.affect(hero, StanceCooldown.class, 9f);
				buff(Dong.class).actionIcon();
			} else {
				buff(Talent.JungQuickDrawTracker.class).detach();
			}
			return 0;
		}

		if (belongings.weapon() != null) {
			return belongings.weapon().delayFactor( this );
		} else {
			//Normally putting furor speed on unarmed attacks would be unnecessary
			//But there's going to be that one guy who gets a furor+force ring combo
			//This is for that one guy, you shall get your fists of fury!
			return 1f / RingOfFuror.attackSpeedMultiplier(this);
		}
	}

	@Override
	public void spend( float time ) {
		justMoved = false;
		
		super.spend(time);
	}

	public void spendAndNextConstant( float time ) {
		busy();
		spendConstant( time );
		next();
	}

	public void spendAndNext( float time ) {
		busy();
		spend( time );
		next();
	}
	
	@Override
	public boolean act() {
		
		//calls to dungeon.observe will also update hero's local FOV.
		fieldOfView = Dungeon.level.heroFOV;

		if (buff(Endure.EndureTracker.class) != null){
			buff(Endure.EndureTracker.class).endEnduring();
		}
		
		if (!ready) {
			//do a full observe (including fog update) if not resting.
			if (!resting || buff(MindVision.class) != null || buff(Awareness.class) != null) {
				Dungeon.observe();
			} else {
				//otherwise just directly re-calculate FOV
				Dungeon.level.updateFieldOfView(this, fieldOfView);
			}
		}
		
		checkVisibleMobs();
		BuffIndicator.refreshHero();
		BuffIndicator.refreshBoss();
		
		if (paralysed > 0) {
			
			curAction = null;
			
			spendAndNext( TICK );
			return false;
		}
		
		boolean actResult;
		if (curAction == null) {
			
			if (resting) {
				spendConstant( TIME_TO_REST );
				next();
			} else {
				ready();
			}
			
			actResult = false;
			
		} else {
			
			resting = false;
			
			ready = false;
			
			if (curAction instanceof HeroAction.Move) {
				actResult = actMove( (HeroAction.Move)curAction );
				
			} else if (curAction instanceof HeroAction.Interact) {
				actResult = actInteract( (HeroAction.Interact)curAction );
				
			} else if (curAction instanceof HeroAction.Buy) {
				actResult = actBuy( (HeroAction.Buy)curAction );
				
			}else if (curAction instanceof HeroAction.PickUp) {
				actResult = actPickUp( (HeroAction.PickUp)curAction );
				
			} else if (curAction instanceof HeroAction.OpenChest) {
				actResult = actOpenChest( (HeroAction.OpenChest)curAction );
				
			} else if (curAction instanceof HeroAction.Unlock) {
				actResult = actUnlock((HeroAction.Unlock) curAction);
				
			} else if (curAction instanceof HeroAction.LvlTransition) {
				actResult = actTransition( (HeroAction.LvlTransition)curAction );
				
			} else if (curAction instanceof HeroAction.Attack) {
				actResult = actAttack( (HeroAction.Attack)curAction );
				
			} else if (curAction instanceof HeroAction.Alchemy) {
				actResult = actAlchemy( (HeroAction.Alchemy)curAction );
				
			} else {
				actResult = false;
			}
		}
		
		if(hasTalent(Talent.BARKSKIN) && Dungeon.level.map[pos] == Terrain.FURROWED_GRASS){
			Buff.affect(this, Barkskin.class).set( (lvl*pointsInTalent(Talent.BARKSKIN))/2, 1 );
		}

		if(Dungeon.level.map[pos] == Terrain.FURROWED_GRASS && hero.hasTalent(Talent.SHADOW) && hero.buff(Shadows.class) != null) {
			Buff.prolong(this, Shadows.class, 1.0001f);
		}
		
		return actResult;
	}
	
	public void busy() {
		ready = false;
	}
	
	private void ready() {
		if (sprite.looping()) sprite.idle();
		curAction = null;
		damageInterrupt = true;
		waitOrPickup = false;
		ready = true;

		AttackIndicator.updateState();
		
		GameScene.ready();
	}
	
	public void interrupt() {
		if (isAlive() && curAction != null &&
			((curAction instanceof HeroAction.Move && curAction.dst != pos) ||
			(curAction instanceof HeroAction.LvlTransition))) {
			lastAction = curAction;
		}
		curAction = null;
		GameScene.resetKeyHold();
	}
	
	public void resume() {
		curAction = lastAction;
		lastAction = null;
		damageInterrupt = false;
		next();
	}

	public boolean isStandingOnTrampleableGrass(){
		return !rooted && !flying &&
				(Dungeon.level.map[pos] == Terrain.HIGH_GRASS || ((heroClass != HeroClass.HUNTRESS && heroClass != HeroClass.PLANTER && hero.subClass != HeroSubClass.RIFLEMAN) && Dungeon.level.map[pos] == Terrain.FURROWED_GRASS));
	}
	
	private boolean actMove( HeroAction.Move action ) {

		if (getCloser( action.dst )) {
			return true;

		//Hero moves in place if there is grass to trample
		} else if (isStandingOnTrampleableGrass()){
			Dungeon.level.pressCell(pos);
			spendAndNext( 1 / speed() );
			return false;
		} else {
			ready();
			return false;
		}
	}
	
	private boolean actInteract( HeroAction.Interact action ) {
		
		Char ch = action.ch;

		if (ch.canInteract(this)) {
			
			ready();
			sprite.turnTo( pos, ch.pos );
			return ch.interact(this);
			
		} else {
			
			if (fieldOfView[ch.pos] && getCloser( ch.pos )) {

				return true;

			} else {
				ready();
				return false;
			}
			
		}
	}
	
	private boolean actBuy( HeroAction.Buy action ) {
		int dst = action.dst;
		if (pos == dst) {

			ready();
			
			Heap heap = Dungeon.level.heaps.get( dst );
			if (heap != null && heap.type == Type.FOR_SALE && heap.size() == 1) {
				Game.runOnRenderThread(new Callback() {
					@Override
					public void call() {
						GameScene.show( new WndTradeItem( heap ) );
					}
				});
			}

			return false;

		} else if (getCloser( dst )) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actAlchemy( HeroAction.Alchemy action ) {
		int dst = action.dst;
		if (Dungeon.level.distance(dst, pos) <= 1) {

			ready();
			
			AlchemistsToolkit.kitEnergy kit = buff(AlchemistsToolkit.kitEnergy.class);
			if (kit != null && kit.isCursed()){
				GLog.w( Messages.get(AlchemistsToolkit.class, "cursed"));
				return false;
			}

			AlchemyScene.clearToolkit();
			ShatteredPixelDungeon.switchScene(AlchemyScene.class);
			return false;

		} else if (getCloser( dst )) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	//used to keep track if the wait/pickup action was used
	// so that the hero spends a turn even if the fail to pick up an item
	public boolean waitOrPickup = false;

	private boolean actPickUp( HeroAction.PickUp action ) {
		int dst = action.dst;
		if (pos == dst) {
			
			Heap heap = Dungeon.level.heaps.get( pos );
			if (heap != null) {
				Item item = heap.peek();
				if (item.doPickUp( this )) {
					heap.pickUp();

					if (item instanceof Dewdrop
							|| item instanceof TimekeepersHourglass.sandBag
							|| item instanceof DriedRose.Petal
							|| item instanceof Key
							|| item instanceof Guidebook) {
						//Do Nothing
					} else {

						//TODO make all unique items important? or just POS / SOU?
						boolean important = item.unique && item.isIdentified() &&
								(item instanceof Scroll || item instanceof Potion);
						if (important) {
							GLog.p( Messages.capitalize(Messages.get(this, "you_now_have", item.name())) );
						} else {
							GLog.i( Messages.capitalize(Messages.get(this, "you_now_have", item.name())) );
						}
					}
					
					curAction = null;
				} else {

					if (waitOrPickup) {
						spendAndNextConstant(TIME_TO_REST);
					}

					//allow the hero to move between levels even if they can't collect the item
					if (Dungeon.level.getTransition(pos) != null){
						throwItems();
					} else {
						heap.sprite.drop();
					}

					if (item instanceof Dewdrop
							|| item instanceof TimekeepersHourglass.sandBag
							|| item instanceof DriedRose.Petal
							|| item instanceof Key) {
						//Do Nothing
					} else {
						GLog.newLine();
						GLog.n(Messages.capitalize(Messages.get(this, "you_cant_have", item.name())));
					}

					ready();
				}
			} else {
				ready();
			}

			return false;

		} else if (getCloser( dst )) {

			return true;

		} else {
			ready();
			return false;
		}
	}
	
	private boolean actOpenChest( HeroAction.OpenChest action ) {
		int dst = action.dst;
		if (Dungeon.level.adjacent( pos, dst ) || pos == dst) {
			
			Heap heap = Dungeon.level.heaps.get( dst );
			if (heap != null && (heap.type != Type.HEAP && heap.type != Type.FOR_SALE)) {
				
				if ((heap.type == Type.LOCKED_CHEST && Notes.keyCount(new GoldenKey(Dungeon.depth)) < 1)
					|| (heap.type == Type.CRYSTAL_CHEST && Notes.keyCount(new CrystalKey(Dungeon.depth)) < 1)){

						GLog.w( Messages.get(this, "locked_chest") );
						ready();
						return false;

				}
				
				switch (heap.type) {
				case TOMB:
					Sample.INSTANCE.play( Assets.Sounds.TOMB );
					Camera.main.shake( 1, 0.5f );
					break;
				case SKELETON:
				case REMAINS:
					break;
				default:
					Sample.INSTANCE.play( Assets.Sounds.UNLOCK );
				}
				
				sprite.operate( dst );
				
			} else {
				ready();
			}

			return false;

		} else if (getCloser( dst )) {

			return true;

		} else {
			ready();
			return false;
		}
	}
	
	private boolean actUnlock( HeroAction.Unlock action ) {
		int doorCell = action.dst;
		if (Dungeon.level.adjacent( pos, doorCell )) {
			
			boolean hasKey = false;
			int door = Dungeon.level.map[doorCell];
			
			if (door == Terrain.LOCKED_DOOR
					&& Notes.keyCount(new IronKey(Dungeon.depth)) > 0) {
				
				hasKey = true;
				
			} else if (door == Terrain.CRYSTAL_DOOR
					&& Notes.keyCount(new CrystalKey(Dungeon.depth)) > 0) {

				hasKey = true;

			} else if (door == Terrain.LOCKED_EXIT
					&& Notes.keyCount(new SkeletonKey(Dungeon.depth)) > 0) {

				hasKey = true;
				
			}
			
			if (hasKey) {
				
				sprite.operate( doorCell );
				
				Sample.INSTANCE.play( Assets.Sounds.UNLOCK );
				
			} else {
				GLog.w( Messages.get(this, "locked_door") );
				ready();
			}

			return false;

		} else if (getCloser( doorCell )) {

			return true;

		} else {
			ready();
			return false;
		}
	}
	
	private boolean actTransition(HeroAction.LvlTransition action ) {
		int stairs = action.dst;
		LevelTransition transition = Dungeon.level.getTransition(stairs);

		if (rooted) {
			Camera.main.shake(1, 1f);
			ready();
			return false;
		} else if (transition != null && transition.inside(pos)) {

			if (transition.type == LevelTransition.Type.SURFACE){
				if (belongings.getItem( Amulet.class ) == null) {
					Game.runOnRenderThread(new Callback() {
						@Override
						public void call() {
							GameScene.show( new WndMessage( Messages.get(Hero.this, "leave") ) );
						}
					});
					ready();
				} else {
					Statistics.ascended = true;
					Badges.silentValidateHappyEnd();
					Dungeon.win( Amulet.class );
					Dungeon.deleteGame( GamesInProgress.curSlot, true );
					Game.switchScene( SurfaceScene.class );
				}

			} else if (transition.type == LevelTransition.Type.REGULAR_ENTRANCE
					&& Dungeon.depth == 30
					//ascension challenge only works on runs started on v1.3+
					&& Dungeon.initialVersion > ShatteredPixelDungeon.v1_2_3
					&& belongings.getItem(Amulet.class) != null
					&& buff(AscensionChallenge.class) == null) {

				Game.runOnRenderThread(new Callback() {
					@Override
					public void call() {
						GameScene.show( new WndOptions( new ItemSprite(ItemSpriteSheet.AMULET),
								Messages.get(Amulet.class, "ascent_title"),
								Messages.get(Amulet.class, "ascent_desc"),
								Messages.get(Amulet.class, "ascent_yes"),
								Messages.get(Amulet.class, "ascent_no")){
							@Override
							protected void onSelect(int index) {
								if (index == 0){
									Buff.affect(Hero.this, AscensionChallenge.class);
									Statistics.highestAscent = 30;
									actTransition(action);
								}
							}
						} );
					}
				});
				ready();

			} else {

				curAction = null;

				TimekeepersHourglass.timeFreeze timeFreeze = buff(TimekeepersHourglass.timeFreeze.class);
				if (timeFreeze != null) timeFreeze.disarmPressedTraps();
				Swiftthistle.TimeBubble timeBubble = buff(Swiftthistle.TimeBubble.class);
				if (timeBubble != null) timeBubble.disarmPressedTraps();

				InterlevelScene.curTransition = transition;
				//TODO probably want to make this more flexible when more types exist
				if (transition.type == LevelTransition.Type.REGULAR_EXIT) {
					InterlevelScene.mode = InterlevelScene.Mode.DESCEND;
				} else {
					InterlevelScene.mode = InterlevelScene.Mode.ASCEND;
				}
				Game.switchScene(InterlevelScene.class);

			}

			return false;

		} else if (getCloser( stairs )) {

			return true;

		} else {
			ready();
			return false;
		}
	}
	
	private boolean actAttack( HeroAction.Attack action ) {

		enemy = action.target;

		if (enemy.isAlive() && canAttack( enemy ) && !isCharmedBy( enemy ) && enemy.invisible == 0) {
			
			sprite.attack( enemy.pos );

			return false;

		} else {

			if (fieldOfView[enemy.pos] && getCloser( enemy.pos )) {

				return true;

			} else {
				ready();
				return false;
			}

		}
	}

	public Char enemy(){
		return enemy;
	}

	public void rest( boolean fullRest ) {
		spendAndNext( TIME_TO_REST );

		if (hasTalent(Talent.HOLD_FAST)){
			Buff.affect(this, HoldFast.class);
		}

		if (Dungeon.level.map[pos] == Terrain.FURROWED_GRASS && hero.hasTalent(Talent.SHADOW) && hero.buff(Shadows.class) == null) {
			Buff.affect(this, Shadows.class, 1.0001f);
		}

		if (hero.heroClass == HeroClass.SAMURAI) {
			if (hero.buff(Sheathing.class) == null && hero.hasTalent(Talent.FLOW_AWAY) && hero.buff(Talent.FlowAwayCooldown.class) == null) {
				Buff.affect(hero, EvasiveMove.class, 0.9999f);
				Buff.affect(hero, Talent.FlowAwayCooldown.class, 70 - 20 * hero.pointsInTalent(Talent.FLOW_AWAY));
			}

			if (hero.buff(Sheathing.class) == null) {
				SerialAttack serialAttack = hero.buff(SerialAttack.class);
				if (hero.subClass == HeroSubClass.SLASHER && serialAttack != null) {
					if (hero.hasTalent(Talent.DETECTIVE_SLASHING) && hero.buff(Talent.DetectiveSlashingCooldown.class) == null) {
						int count = serialAttack.getCount();
						if (Random.Int(7) < count) {
							Buff.affect(hero, EvasiveMove.class, 0.9999f);
						}
						serialAttack.detach();
						Buff.affect(hero, Talent.DetectiveSlashingCooldown.class, 5);
					} else if (hero.hasTalent(Talent.SLASHING)) {
						int count = serialAttack.getCount();
						Buff.affect(hero, Iaido.class).set(count);
						serialAttack.detach();
					}
				}
			}

			if (hero.subClass == HeroSubClass.MASTER && hero.buff(StanceCooldown.class) == null) {
				if (hero.buff(Dong.class) == null && hero.buff(Jung.class) == null) {

					Buff.affect(hero, Dong.class);

				} else if (hero.buff(Dong.class) != null && hero.buff(Sheathing.class) == null) {
					hero.buff(Dong.class).detach();
					Buff.affect(hero, Jung.class);

					if (hero.hasTalent(Talent.JUNG_INCISIVE_BLADE)) {
						Buff.affect(hero, Talent.IncisiveBladeTracker.class);
					}
				}
			}
			Buff.affect(hero, Sheathing.class);
		}

		if (!fullRest) {
			if (belongings.weapon instanceof LargeSword){
				Buff.affect(this, LargeSwordBuff.class).setDamageFactor(belongings.weapon.buffedLvl());
				if (hero.sprite != null) {
					Emitter e = hero.sprite.centerEmitter();
					if (e != null) e.burst(EnergyParticle.FACTORY, 15);
				}
			}

			if (Dungeon.hero.subClass == HeroSubClass.CHASER
					&& hero.buff(Talent.ChaseCooldown.class) == null
					&& hero.buff(Invisibility.class) == null
					&& hero.buff(CloakOfShadows.cloakStealth.class) == null ) {
				if (hero.hasTalent(Talent.MASTER_OF_CLOAKING)) {
					Buff.affect(Dungeon.hero, Invisibility.class, 6f);
				} else {
					Buff.affect(Dungeon.hero, Invisibility.class, 5f);
				}
				if (hero.pointsInTalent(Talent.MASTER_OF_CLOAKING) > 1) {
					Buff.affect(Dungeon.hero, Talent.ChaseCooldown.class, 10f);
				} else {
					Buff.affect(Dungeon.hero, Talent.ChaseCooldown.class, 15f);
				}
			}

			if (hasTalent(Talent.DISINFECTION)) {
				if (Random.Int(10) < pointsInTalent(Talent.DISINFECTION)) {
					for (Buff b : hero.buffs()){
						if (b.type == Buff.buffType.NEGATIVE
								&& !(b instanceof AllyBuff)
								&& !(b instanceof LostInventory)){
							b.detach();
						}
					}
				}
			}

			if (hero.hasTalent(Talent.OUTLAW_OF_BARRENLAND)) {
				Buff.affect(hero, Outlaw.class).count();
			}

			if (Random.Int(10) < hero.pointsInTalent(Talent.FIRST_AID) && !hero.buff(Hunger.class).isStarving()) {
				int healAmt = 1;
				healAmt = Math.min( healAmt, hero.HT - hero.HP );
				if (healAmt > 0 && hero.isAlive()) {
					hero.HP += healAmt;
					hero.sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1);
					hero.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );
				}
			}

			if (sprite != null) {
				sprite.showStatus(CharSprite.DEFAULT, Messages.get(this, "wait"));
			}
		}
		resting = fullRest;
	}
	
	@Override
	public int attackProc( final Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		
		KindOfWeapon wep = belongings.weapon();

		if (wep != null) damage = wep.proc( this, enemy, damage );

		if (buff(Talent.SpiritBladesTracker.class) != null
				&& Random.Int(10) < 3*pointsInTalent(Talent.SPIRIT_BLADES)){
			SpiritBow bow = belongings.getItem(SpiritBow.class);
			if (bow != null) damage = bow.proc( this, enemy, damage );
			buff(Talent.SpiritBladesTracker.class).detach();
		}

		if (hero.heroClass != HeroClass.SAMURAI && hero.hasTalent(Talent.DETECTION)) {
			if (Random.Int(4) < 1+hero.pointsInTalent(Talent.DETECTION)) {
				Buff.affect(hero, WeaponEmpower.class).set(1, 3f);
			}
		}

		if (hero.buff(GodFury.class) != null) {
			Buff.affect(enemy, Slow.class, 3f);
			hero.buff(GodFury.class).detach();
		}

		if (hero.subClass == HeroSubClass.CRUSADER && hero.buff(Bless.class) != null && hero.buff(KnightsBlocking.class) != null) {
			hero.buff(KnightsBlocking.class).add(Math.round(damage*0.2f));
		}

		if (hero.subClass == HeroSubClass.CRUSADER && hero.buff(GodFury.class) != null && hero.buff(KnightsBlocking.class) != null) {
			hero.buff(KnightsBlocking.class).add(Math.round((hero.lvl + hero.belongings.armor.buffedLvl())*0.1f));
		}

		damage = Talent.onAttackProc( this, enemy, damage );
		
		switch (subClass) {
		case SNIPER:
			if (wep instanceof MissileWeapon && !(wep instanceof SpiritBow.SpiritArrow
					|| wep instanceof WindBow.SpiritArrow
					|| wep instanceof PoisonBow.SpiritArrow
					|| wep instanceof GoldenBow.SpiritArrow
					|| wep instanceof NaturesBow.SpiritArrow) && enemy != this) {
				Actor.add(new Actor() {
					
					{
						actPriority = VFX_PRIO;
					}
					
					@Override
					protected boolean act() {
						if (enemy.isAlive()) {
							int bonusTurns = hasTalent(Talent.SHARED_UPGRADES) ? wep.buffedLvl() : 0;
							Buff.prolong(Hero.this, SnipersMark.class, SnipersMark.DURATION + bonusTurns).set(enemy.id(), bonusTurns);
						}
						Actor.remove(this);
						return true;
					}
				});
			}
			break;
		default:
		}

		return damage;
	}

	@Override
	public int defenseProc( Char enemy, int damage ) {

		if (damage > 0 && subClass == HeroSubClass.BERSERKER){
			Berserk berserk = Buff.affect(this, Berserk.class);
			berserk.damage(damage);
		}

		if (hero.subClass == HeroSubClass.GLADIATOR && Random.Int(3) < hero.pointsInTalent(Talent.OFFENSIVE_DEFENSE)) {
			Combo combo = Buff.affect(this, Combo.class);
			combo.hit(enemy);
		}

		if (belongings.armor() != null) {
			damage = belongings.armor().proc( enemy, this, damage );
		}

		if (hasTalent(Talent.HOLY_PROTECTION)) {
			if (Random.Int(5) < pointsInTalent(Talent.HOLY_PROTECTION)) {
				damage *= 0.4f;
			}
		}

		if (hero.heroClass != HeroClass.SAMURAI && hero.hasTalent(Talent.PARRY)) {
			if (Random.Int(4) < 1+hero.pointsInTalent(Talent.PARRY)) {
				Buff.affect(hero, ArmorEmpower.class).set(1, 3f);
			}
		}

		if (Random.Int(3) < hero.pointsInTalent(Talent.OVERCOMING)) {
			Momentum momentum = buff(Momentum.class);
			if (momentum != null && momentum.freerunning()) {
				Buff.affect(this, EvasiveMove.class, 1f);
			}
		}

		if (hero.heroClass != HeroClass.KNIGHT && hero.hasTalent(Talent.DEFENSE_STANCE)) {
			if (Random.Int(20) < hero.pointsInTalent(Talent.DEFENSE_STANCE)) {
				Buff.detach(hero, Talent.ImprovisedProjectileCooldown.class);
				Buff.detach(hero, Talent.RejuvenatingStepsCooldown.class);
				Buff.detach(hero, Talent.SeerShotCooldown.class);
				Buff.detach(hero, Talent.ChainCooldown.class);
				Buff.detach(hero, Talent.LethalCooldown.class);
				Buff.detach(hero, Talent.ReloadCooldown.class);
				Buff.detach(hero, Talent.TakeDownCooldown.class);
				Buff.detach(hero, Talent.StreetBattleCooldown.class);
				Buff.detach(hero, Talent.PushbackCooldown.class);
				Buff.detach(hero, Talent.QuickSwapCooldown.class);
				Buff.detach(hero, Talent.KickCooldown.class);
			}
		}

		if (hero.heroClass != HeroClass.KNIGHT && hero.hasTalent(Talent.BLOCKING)) {
			if (!hero.isStarving()) {
				int healAmt = hero.pointsInTalent(Talent.BLOCKING);
				healAmt = Math.min( healAmt, hero.HT - hero.HP );
				if (healAmt > 0 && hero.isAlive()) {
					hero.HP += healAmt;
					hero.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
					hero.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );
				}
			}
		}

		WandOfLivingEarth.RockArmor rockArmor = buff(WandOfLivingEarth.RockArmor.class);
		if (rockArmor != null) {
			damage = rockArmor.absorb(damage);
		}

		if (hero.hasTalent(Talent.EMERGENCY_ESCAPE) && Random.Int(50) < hero.pointsInTalent(Talent.EMERGENCY_ESCAPE)) {
			Buff.prolong(this, Invisibility.class, 3f);
		}

		if (hero.hasTalent(Talent.ACTIVE_BARRIER) && enemy.buff(Talent.ActiveBarrierTracker.class) == null) {
			Buff.affect(this, Barrier.class).setShield(1+2*pointsInTalent(Talent.ACTIVE_BARRIER));
			Buff.affect(enemy, Talent.ActiveBarrierTracker.class);
		}

		if (hero.hasTalent(Talent.DEFENSE_STANCE) && Random.Int(20) < hero.pointsInTalent(Talent.DEFENSE_STANCE) && hero.buff(ShieldCoolDown.class) != null) {
			Buff.detach(this, ShieldCoolDown.class);
		}

		if (hero.hasTalent(Talent.THORNY_VINE) && (level.map[hero.pos] == Terrain.FURROWED_GRASS || level.map[hero.pos] == Terrain.HIGH_GRASS)) {
			Buff.affect(enemy, Bleeding.class).set(hero.pointsInTalent(Talent.THORNY_VINE));
		}

		if (heroClass == HeroClass.NURSE && Random.Int(100) <= 20+lvl+5*hero.pointsInTalent(Talent.MEDICAL_SUPPORT)) {
			Buff.affect(this, HealingArea.class).setup(this.pos, 5, 1, true);
		}

		if (hero.hasTalent(Talent.INNER_MIRROR) && Random.Int(10) == 0) {
			ScrollOfMirrorImage.spawnImages(Dungeon.hero, hero.pointsInTalent(Talent.INNER_MIRROR));
		}

		if (hero.hasTalent(Talent.ANGEL) && hero.buff(HealingArea.class) != null) {
			Buff.affect( enemy, Charm.class, Charm.DURATION ).object = hero.id();
		}

		if (hero.subClass == HeroSubClass.ANGEL && Random.Int(10) <= hero.pointsInTalent(Talent.PERSUASION)) {
			Charm charm = Buff.affect(enemy, Charm.class, Charm.DURATION/2f);
			charm.object = hero.id();
			charm.ignoreHeroAllies = true;
			enemy.sprite.centerEmitter().start( Speck.factory( Speck.HEART ), 0.2f, 3 );
		}

		if (hero.subClass == HeroSubClass.CRUSADER && hero.buff(KnightsBlocking.class) != null) {
			Buff.affect(hero, Bless.class, 1+(int)Math.floor(belongings.armor.buffedLvl()/3f));
		}

		if (hero.subClass == HeroSubClass.CRUSADER && hero.buff(KnightsBlocking.class) != null && Random.Float() < (belongings.armor.buffedLvl()+1f)/(belongings.armor.buffedLvl()+8f)) {
			Buff.affect(hero, GodFury.class);
		}

		if (hero.hasTalent(Talent.SHIELD_OF_LIGHT) && hero.buff(ShieldCoolDown.class) != null) {
			Buff.affect(hero, ShieldCoolDown.class).use(3*hero.pointsInTalent(Talent.SHIELD_OF_LIGHT));
		}
		
		return super.defenseProc( enemy, damage );
	}
	
	@Override
	public void damage( int dmg, Object src ) {
		if (buff(TimekeepersHourglass.timeStasis.class) != null)
			return;

		if (!(src instanceof Hunger || src instanceof Viscosity.DeferedDamage) && damageInterrupt) {
			interrupt();
			resting = false;
		}

		if (this.buff(Drowsy.class) != null){
			Buff.detach(this, Drowsy.class);
			GLog.w( Messages.get(this, "pain_resist") );
		}

		Endure.EndureTracker endure = buff(Endure.EndureTracker.class);
		if (!(src instanceof Char)){
			//reduce damage here if it isn't coming from a character (if it is we already reduced it)
			if (endure != null){
				dmg = Math.round(endure.adjustDamageTaken(dmg));
			}
			//the same also applies to challenge scroll damage reduction
			if (buff(ScrollOfChallenge.ChallengeArena.class) != null){
				dmg *= 0.67f;
			}
		}

		CapeOfThorns.Thorns thorns = buff( CapeOfThorns.Thorns.class );
		if (thorns != null) {
			dmg = thorns.proc(dmg, (src instanceof Char ? (Char)src : null),  this);
		}

		dmg = (int)Math.ceil(dmg * RingOfTenacity.damageMultiplier( this ));

		LargeSwordBuff largeSwordBuff = hero.buff(LargeSwordBuff.class);
		if (largeSwordBuff != null) {
			dmg = (int)Math.ceil(dmg * largeSwordBuff.getDefenseFactor());
		}

		//TODO improve this when I have proper damage source logic
		if (belongings.armor() != null && belongings.armor().hasGlyph(AntiMagic.class, this)
				&& AntiMagic.RESISTS.contains(src.getClass())){
			dmg -= AntiMagic.drRoll(this, belongings.armor().buffedLvl());
		}

		if (belongings.weapon() instanceof ObsidianShield && ObsidianShield.RESISTS.contains(src.getClass())) {
			dmg -= ObsidianShield.drRoll(belongings.weapon().buffedLvl());
		}

		if (hero.belongings.weapon instanceof LanceNShield && LanceNShield.RESISTS.contains(src.getClass()) && !((LanceNShield)hero.belongings.weapon).stance){
			dmg -= LanceNShield.drRoll(belongings.weapon().buffedLvl());
		}


		if (buff(Talent.WarriorFoodImmunity.class) != null){
			if (pointsInTalent(Talent.IRON_STOMACH) == 1)       dmg = Math.round(dmg*0.25f);
			else if (pointsInTalent(Talent.IRON_STOMACH) == 2)  dmg = Math.round(dmg*0.00f);
		}

		int preHP = HP + shielding();
		super.damage( dmg, src );
		int postHP = HP + shielding();
		int effectiveDamage = preHP - postHP;

		if (effectiveDamage <= 0) return;

		//flash red when hit for serious damage.
		float percentDMG = effectiveDamage / (float)preHP; //percent of current HP that was taken
		float percentHP = 1 - ((HT - postHP) / (float)HT); //percent health after damage was taken
		// The flash intensity increases primarily based on damage taken and secondarily on missing HP.
		float flashIntensity = 0.25f * (percentDMG * percentDMG) / percentHP;
		//if the intensity is very low don't flash at all
		if (flashIntensity >= 0.05f){
			flashIntensity = Math.min(1/3f, flashIntensity); //cap intensity at 1/3
			GameScene.flash( (int)(0xFF*flashIntensity) << 16 );
			if (isAlive()) {
				if (flashIntensity >= 1/6f) {
					Sample.INSTANCE.play(Assets.Sounds.HEALTH_CRITICAL, 1/3f + flashIntensity * 2f);
				} else {
					Sample.INSTANCE.play(Assets.Sounds.HEALTH_WARN, 1/3f + flashIntensity * 4f);
				}
			}
		}
	}
	
	public void checkVisibleMobs() {
		ArrayList<Mob> visible = new ArrayList<>();

		boolean newMob = false;

		Mob target = null;
		for (Mob m : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (fieldOfView[ m.pos ] && m.alignment == Alignment.ENEMY) {
				visible.add(m);
				if (!visibleEnemies.contains( m )) {
					newMob = true;
				}

				if (!mindVisionEnemies.contains(m) && QuickSlotButton.autoAim(m) != -1){
					if (target == null){
						target = m;
					} else if (distance(target) > distance(m)) {
						target = m;
					}
					if (m instanceof Snake && Dungeon.level.distance(m.pos, pos) <= 4
							&& !Document.ADVENTURERS_GUIDE.isPageRead(Document.GUIDE_EXAMINING)){
						GLog.p(Messages.get(Guidebook.class, "hint"));
						GameScene.flashForDocument(Document.ADVENTURERS_GUIDE, Document.GUIDE_EXAMINING);
						//we set to read here to prevent this message popping up a bunch
						Document.ADVENTURERS_GUIDE.readPage(Document.GUIDE_EXAMINING);
					}
				}
			}
		}

		Char lastTarget = QuickSlotButton.lastTarget;
		if (target != null && (lastTarget == null ||
							!lastTarget.isAlive() ||
							lastTarget.alignment == Alignment.ALLY ||
							!fieldOfView[lastTarget.pos])){
			QuickSlotButton.target(target);
		}
		
		if (newMob) {
			interrupt();
			if (resting){
				Dungeon.observe();
				resting = false;
			}
		}

		visibleEnemies = visible;
	}
	
	public int visibleEnemies() {
		return visibleEnemies.size();
	}
	
	public Mob visibleEnemy( int index ) {
		return visibleEnemies.get(index % visibleEnemies.size());
	}

	public ArrayList<Mob> getVisibleEnemies(){
		return new ArrayList<>(visibleEnemies);
	}
	
	private boolean walkingToVisibleTrapInFog = false;
	
	//FIXME this is a fairly crude way to track this, really it would be nice to have a short
	//history of hero actions
	public boolean justMoved = false;
	
	private boolean getCloser( final int target ) {

		if (target == pos)
			return false;

		if (rooted) {
			Camera.main.shake( 1, 1f );
			return false;
		}
		
		int step = -1;
		
		if (Dungeon.level.adjacent( pos, target )) {

			path = null;

			if (Actor.findChar( target ) == null) {
				if (Dungeon.level.pit[target] && !flying && !Dungeon.level.solid[target]) {
					if (!Chasm.jumpConfirmed){
						Chasm.heroJump(this);
						interrupt();
					} else {
						Chasm.heroFall(target);
					}
					return false;
				}
				if (Dungeon.level.passable[target] || Dungeon.level.avoid[target]) {
					step = target;
				}
				if (walkingToVisibleTrapInFog
						&& Dungeon.level.traps.get(target) != null
						&& Dungeon.level.traps.get(target).visible){
					return false;
				}
			}
			
		} else {

			boolean newPath = false;
			if (path == null || path.isEmpty() || !Dungeon.level.adjacent(pos, path.getFirst()))
				newPath = true;
			else if (path.getLast() != target)
				newPath = true;
			else {
				if (!Dungeon.level.passable[path.get(0)] || Actor.findChar(path.get(0)) != null) {
					newPath = true;
				}
			}

			if (newPath) {

				int len = Dungeon.level.length();
				boolean[] p = Dungeon.level.passable;
				boolean[] v = Dungeon.level.visited;
				boolean[] m = Dungeon.level.mapped;
				boolean[] passable = new boolean[len];
				for (int i = 0; i < len; i++) {
					passable[i] = p[i] && (v[i] || m[i]);
				}

				PathFinder.Path newpath = Dungeon.findPath(this, target, passable, fieldOfView, true);
				if (newpath != null && path != null && newpath.size() > 2*path.size()){
					path = null;
				} else {
					path = newpath;
				}
			}

			if (path == null) return false;
			step = path.removeFirst();

		}

		if (step != -1) {

			if (Dungeon.isChallenged(Challenges.CURSED_DUNGEON) && hero.buff(GhostSpawner.class) == null) {
				Buff.affect(hero, GhostSpawner.class);
			}

			if (subClass == HeroSubClass.FREERUNNER){
				Buff.affect(this, Momentum.class).gainStack();
			}

			if (hero.belongings.weapon instanceof Lance) {
				Buff.affect(this, LanceBuff.class).setDamageFactor(1+(hero.speed()));
			}

			if (hero.belongings.weapon instanceof LanceNShield && ((LanceNShield)hero.belongings.weapon).stance) {
				Buff.affect(this, LanceBuff.class).setDamageFactor(1+(hero.speed()));
			}

			if (Dungeon.hero.subClass == HeroSubClass.RANGER && Random.Int(50) <= pointsInTalent(Talent.SPECIAL_TRAINING)){
				Buff.affect(this, Haste.class,5);
			}

			if (hero.subClass == HeroSubClass.SLAYER && hero.buff(Demonization.class) == null) {
				Buff.affect(this, Demonization.class).indicate();
			}

			if (hero.hasTalent(Talent.QUICK_RELOAD) && Random.Int(50) < hero.pointsInTalent(Talent.QUICK_RELOAD)*hero.speed()) {
				if (hero.belongings.weapon() instanceof CrudePistol && ((CrudePistol)hero.belongings.weapon).round < ((CrudePistol)hero.belongings.weapon).max_round) {

					((CrudePistol)hero.belongings.weapon).round = Math.min(((CrudePistol)hero.belongings.weapon).round+1, ((CrudePistol)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof CrudePistolAP && ((CrudePistolAP)hero.belongings.weapon).round < ((CrudePistolAP)hero.belongings.weapon).max_round) {

					((CrudePistolAP)hero.belongings.weapon).round = Math.min(((CrudePistolAP)hero.belongings.weapon).round+1, ((CrudePistolAP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof CrudePistolHP && ((CrudePistolHP)hero.belongings.weapon).round < ((CrudePistolHP)hero.belongings.weapon).max_round) {

					((CrudePistolHP)hero.belongings.weapon).round = Math.min(((CrudePistolHP)hero.belongings.weapon).round+1, ((CrudePistolHP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof Pistol && ((Pistol)hero.belongings.weapon).round < ((Pistol)hero.belongings.weapon).max_round) {

					((Pistol)hero.belongings.weapon).round = Math.min(((Pistol)hero.belongings.weapon).round+1, ((Pistol)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof PistolAP && ((PistolAP)hero.belongings.weapon).round < ((PistolAP)hero.belongings.weapon).max_round) {

					((PistolAP)hero.belongings.weapon).round = Math.min(((PistolAP)hero.belongings.weapon).round+1, ((PistolAP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof PistolHP && ((PistolHP)hero.belongings.weapon).round < ((PistolHP)hero.belongings.weapon).max_round) {

					((PistolHP)hero.belongings.weapon).round = Math.min(((PistolHP)hero.belongings.weapon).round+1, ((PistolHP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof GoldenPistol && ((GoldenPistol)hero.belongings.weapon).round < ((GoldenPistol)hero.belongings.weapon).max_round) {

					((GoldenPistol)hero.belongings.weapon).round = Math.min(((GoldenPistol)hero.belongings.weapon).round+1, ((GoldenPistol)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof GoldenPistolAP && ((GoldenPistolAP)hero.belongings.weapon).round < ((GoldenPistolAP)hero.belongings.weapon).max_round) {

					((GoldenPistolAP)hero.belongings.weapon).round = Math.min(((GoldenPistolAP)hero.belongings.weapon).round+1, ((GoldenPistolAP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof GoldenPistolHP && ((GoldenPistolHP)hero.belongings.weapon).round < ((GoldenPistolHP)hero.belongings.weapon).max_round) {

					((GoldenPistolHP)hero.belongings.weapon).round = Math.min(((GoldenPistolHP)hero.belongings.weapon).round+1, ((GoldenPistolHP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof Handgun && ((Handgun)hero.belongings.weapon).round < ((Handgun)hero.belongings.weapon).max_round) {

					((Handgun)hero.belongings.weapon).round = Math.min(((Handgun)hero.belongings.weapon).round+1, ((Handgun)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof HandgunAP && ((HandgunAP)hero.belongings.weapon).round < ((HandgunAP)hero.belongings.weapon).max_round) {

					((HandgunAP)hero.belongings.weapon).round = Math.min(((HandgunAP)hero.belongings.weapon).round+1, ((HandgunAP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof HandgunHP && ((HandgunHP)hero.belongings.weapon).round < ((HandgunHP)hero.belongings.weapon).max_round) {

					((HandgunHP)hero.belongings.weapon).round = Math.min(((HandgunHP)hero.belongings.weapon).round+1, ((HandgunHP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof Magnum && ((Magnum)hero.belongings.weapon).round < ((Magnum)hero.belongings.weapon).max_round) {

					((Magnum)hero.belongings.weapon).round = Math.min(((Magnum)hero.belongings.weapon).round+1, ((Magnum)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof MagnumAP && ((MagnumAP)hero.belongings.weapon).round < ((MagnumAP)hero.belongings.weapon).max_round) {

					((MagnumAP)hero.belongings.weapon).round = Math.min(((MagnumAP)hero.belongings.weapon).round+1, ((MagnumAP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof MagnumHP && ((MagnumHP)hero.belongings.weapon).round < ((MagnumHP)hero.belongings.weapon).max_round) {

					((MagnumHP)hero.belongings.weapon).round = Math.min(((MagnumHP)hero.belongings.weapon).round+1, ((MagnumHP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof TacticalHandgun && ((TacticalHandgun)hero.belongings.weapon).round < ((TacticalHandgun)hero.belongings.weapon).max_round) {

					((TacticalHandgun)hero.belongings.weapon).round = Math.min(((TacticalHandgun)hero.belongings.weapon).round+1, ((TacticalHandgun)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof TacticalHandgunAP && ((TacticalHandgunAP)hero.belongings.weapon).round < ((TacticalHandgunAP)hero.belongings.weapon).max_round) {

					((TacticalHandgunAP)hero.belongings.weapon).round = Math.min(((TacticalHandgunAP)hero.belongings.weapon).round+1, ((TacticalHandgunAP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof TacticalHandgunHP && ((TacticalHandgunHP)hero.belongings.weapon).round < ((TacticalHandgunHP)hero.belongings.weapon).max_round) {

					((TacticalHandgunHP)hero.belongings.weapon).round = Math.min(((TacticalHandgunHP)hero.belongings.weapon).round+1, ((TacticalHandgunHP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof DualPistol && ((DualPistol)hero.belongings.weapon).round < ((DualPistol)hero.belongings.weapon).max_round) {

					((DualPistol)hero.belongings.weapon).round = Math.min(((DualPistol)hero.belongings.weapon).round+1, ((DualPistol)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof DualPistolAP && ((DualPistolAP)hero.belongings.weapon).round < ((DualPistolAP)hero.belongings.weapon).max_round) {

					((DualPistolAP)hero.belongings.weapon).round = Math.min(((DualPistolAP)hero.belongings.weapon).round+1, ((DualPistolAP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof DualPistolHP && ((DualPistolHP)hero.belongings.weapon).round < ((DualPistolHP)hero.belongings.weapon).max_round) {

					((DualPistolHP)hero.belongings.weapon).round = Math.min(((DualPistolHP)hero.belongings.weapon).round+1, ((DualPistolHP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof AutoHandgun && ((AutoHandgun)hero.belongings.weapon).round < ((AutoHandgun)hero.belongings.weapon).max_round) {

					((AutoHandgun)hero.belongings.weapon).round = Math.min(((AutoHandgun)hero.belongings.weapon).round+1, ((AutoHandgun)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof AutoHandgunAP && ((AutoHandgunAP)hero.belongings.weapon).round < ((AutoHandgunAP)hero.belongings.weapon).max_round) {

					((AutoHandgunAP)hero.belongings.weapon).round = Math.min(((AutoHandgunAP)hero.belongings.weapon).round+1, ((AutoHandgunAP)hero.belongings.weapon).max_round);

				} else if (hero.belongings.weapon() instanceof AutoHandgunHP && ((AutoHandgunHP)hero.belongings.weapon).round < ((AutoHandgunHP)hero.belongings.weapon).max_round) {

					((AutoHandgunHP)hero.belongings.weapon).round = Math.min(((AutoHandgunHP)hero.belongings.weapon).round+1, ((AutoHandgunHP)hero.belongings.weapon).max_round);

				}
				updateQuickslot();
			}

			if (hero.hasTalent(Talent.MIND_VISION) && Random.Int(100) < hero.pointsInTalent(Talent.MIND_VISION)) {
				Buff.affect(this, MindVision.class, 2f);
			}

			float speed = speed();
			
			sprite.move(pos, step);
			move(step);

			if (buff(QuickStep.class) != null) {
				spend(-1 / speed());
				buff(QuickStep.class).detach();
			}
			spend( 1 / speed );
			justMoved = true;
			
			search(false);

			return true;

		} else {

			return false;
			
		}

	}
	
	public boolean handle( int cell ) {
		
		if (cell == -1) {
			return false;
		}

		if (fieldOfView == null || fieldOfView.length != Dungeon.level.length()){
			fieldOfView = new boolean[Dungeon.level.length()];
			Dungeon.level.updateFieldOfView( this, fieldOfView );
		}
		
		Char ch = Actor.findChar( cell );
		Heap heap = Dungeon.level.heaps.get( cell );
		
		if (Dungeon.level.map[cell] == Terrain.ALCHEMY && cell != pos) {
			
			curAction = new HeroAction.Alchemy( cell );
			
		} else if (fieldOfView[cell] && ch instanceof Mob) {

			if (ch.alignment != Alignment.ENEMY && ch.buff(Amok.class) == null) {
				curAction = new HeroAction.Interact( ch );
			} else {
				curAction = new HeroAction.Attack( ch );
			}

		} else if (heap != null
				//moving to an item doesn't auto-pickup when enemies are near...
				&& (visibleEnemies.size() == 0 || cell == pos ||
				//...but only for standard heaps. Chests and similar open as normal.
				(heap.type != Type.HEAP && heap.type != Type.FOR_SALE))) {

			switch (heap.type) {
			case HEAP:
				curAction = new HeroAction.PickUp( cell );
				break;
			case FOR_SALE:
				curAction = heap.size() == 1 && heap.peek().value() > 0 ?
					new HeroAction.Buy( cell ) :
					new HeroAction.PickUp( cell );
				break;
			default:
				curAction = new HeroAction.OpenChest( cell );
			}
			
		} else if (Dungeon.level.map[cell] == Terrain.LOCKED_DOOR || Dungeon.level.map[cell] == Terrain.CRYSTAL_DOOR || Dungeon.level.map[cell] == Terrain.LOCKED_EXIT) {
			
			curAction = new HeroAction.Unlock( cell );

		} else if (Dungeon.level.getTransition(cell) != null
				//moving to a transition doesn't automatically trigger it when enemies are near
				&& (visibleEnemies.size() == 0 || cell == pos)
				&& !Dungeon.level.locked
				&& (Dungeon.depth < 31 || Dungeon.level.getTransition(cell).type == LevelTransition.Type.REGULAR_ENTRANCE) ) {

			curAction = new HeroAction.LvlTransition( cell );
			
		}  else {
			
			if (!Dungeon.level.visited[cell] && !Dungeon.level.mapped[cell]
					&& Dungeon.level.traps.get(cell) != null && Dungeon.level.traps.get(cell).visible) {
				walkingToVisibleTrapInFog = true;
			} else {
				walkingToVisibleTrapInFog = false;
			}
			
			curAction = new HeroAction.Move( cell );
			lastAction = null;
			
		}

		return true;
	}
	
	public void earnExp( int exp, Class source ) {
		
		this.exp += exp;
		float percent = exp/(float)maxExp();

		EtherealChains.chainsRecharge chains = buff(EtherealChains.chainsRecharge.class);
		if (chains != null) chains.gainExp(percent);

		HornOfPlenty.hornRecharge horn = buff(HornOfPlenty.hornRecharge.class);
		if (horn != null) horn.gainCharge(percent);
		
		AlchemistsToolkit.kitEnergy kit = buff(AlchemistsToolkit.kitEnergy.class);
		if (kit != null) kit.gainCharge(percent);

		MasterThievesArmband.Thievery armband = buff(MasterThievesArmband.Thievery.class);
		if (armband != null) armband.gainCharge(percent);
		
		Berserk berserk = buff(Berserk.class);
		if (berserk != null) berserk.recover(percent);
		
		if (source != PotionOfExperience.class) {
			for (Item i : belongings) {
				i.onHeroGainExp(percent, this);
			}
			if (buff(Talent.RejuvenatingStepsFurrow.class) != null){
				buff(Talent.RejuvenatingStepsFurrow.class).countDown(percent*200f);
				if (buff(Talent.RejuvenatingStepsFurrow.class).count() <= 0){
					buff(Talent.RejuvenatingStepsFurrow.class).detach();
				}
			}
		}
		
		boolean levelUp = false;
		while (this.exp >= maxExp()) {
			this.exp -= maxExp();
			if (lvl < MAX_LEVEL) {
				lvl++;
				levelUp = true;
				
				if (buff(ElixirOfMight.HTBoost.class) != null){
					buff(ElixirOfMight.HTBoost.class).onLevelUp();
				}
				
				updateHT( true );
				attackSkill++;
				defenseSkill++;

			} else {
				Buff.prolong(this, Bless.class, Bless.DURATION);
				this.exp = 0;

				GLog.newLine();
				GLog.p( Messages.get(this, "level_cap"));
				Sample.INSTANCE.play( Assets.Sounds.LEVELUP );
			}
			
		}
		
		if (levelUp) {
			
			if (sprite != null) {
				GLog.newLine();
				GLog.p( Messages.get(this, "new_level") );
				sprite.showStatus( CharSprite.POSITIVE, Messages.get(Hero.class, "level_up") );
				Sample.INSTANCE.play( Assets.Sounds.LEVELUP );
				if (lvl < Talent.tierLevelThresholds[Talent.MAX_TALENT_TIERS+1]){
					GLog.newLine();
					GLog.p( Messages.get(this, "new_talent") );
					StatusPane.talentBlink = 10f;
					WndHero.lastIdx = 1;
				}
			}
			
			updateQuickslot();
			
			Badges.validateLevelReached();
		}
	}
	
	public int maxExp() {
		return maxExp( lvl );
	}
	
	public static int maxExp( int lvl ){
		return 5 + lvl * 5;
	}
	
	public boolean isStarving() {
		return Buff.affect(this, Hunger.class).isStarving();
	}
	
	@Override
	public void add( Buff buff ) {

		if (buff(TimekeepersHourglass.timeStasis.class) != null)
			return;

		super.add( buff );

		if (sprite != null && buffs().contains(buff)) {
			String msg = buff.heroMessage();
			if (msg != null){
				GLog.w(msg);
			}

			if (buff instanceof Paralysis || buff instanceof Vertigo) {
				interrupt();
			}

		}
		
		BuffIndicator.refreshHero();
	}
	
	@Override
	public void remove( Buff buff ) {
		super.remove( buff );

		BuffIndicator.refreshHero();
	}
	
	@Override
	public float stealth() {
		float stealth = super.stealth();
		
		if (belongings.armor() != null){
			stealth = belongings.armor().stealthFactor(this, stealth);
		}

		if (hero.buff(Bless.class) != null && hero.hasTalent(Talent.MYSTICAL_VEIL)) {
			stealth += hero.pointsInTalent(Talent.MYSTICAL_VEIL);
		}
		
		return stealth;
	}
	
	@Override
	public void die( Object cause ) {
		
		curAction = null;

		Ankh ankh = null;

		//look for ankhs in player inventory, prioritize ones which are blessed.
		for (Ankh i : belongings.getAllItems(Ankh.class)){
			if (ankh == null || i.isBlessed()) {
				ankh = i;
			}
		}

		if (ankh != null) {
			interrupt();
			resting = false;

			if (ankh.isBlessed()) {
				this.HP = HT / 4;

				PotionOfHealing.cure(this);
				Buff.prolong(this, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);

				SpellSprite.show(this, SpellSprite.ANKH);
				GameScene.flash(0x80FFFF40);
				Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
				GLog.w(Messages.get(this, "revive"));
				Statistics.ankhsUsed++;

				ankh.detach(belongings.backpack);

				for (Char ch : Actor.chars()) {
					if (ch instanceof DriedRose.GhostHero) {
						((DriedRose.GhostHero) ch).sayAnhk();
						return;
					}
				}
			} else {

				//this is hacky, basically we want to declare that a wndResurrect exists before
				//it actually gets created. This is important so that the game knows to not
				//delete the run or submit it to rankings, because a WndResurrect is about to exist
				//this is needed because the actual creation of the window is delayed here
				WndResurrect.instance = new Object();
				Ankh finalAnkh = ankh;
				Game.runOnRenderThread(new Callback() {
					@Override
					public void call() {
						GameScene.show( new WndResurrect(finalAnkh) );
					}
				});

				if (cause instanceof Hero.Doom) {
					((Hero.Doom)cause).onDeath();
				}

				SacrificialFire.Marked sacMark = buff(SacrificialFire.Marked.class);
				if (sacMark != null){
					sacMark.detach();
				}

			}
			return;
		}
		
		Actor.fixTime();
		super.die( cause );
		reallyDie( cause );
	}
	
	public static void reallyDie( Object cause ) {
		
		int length = Dungeon.level.length();
		int[] map = Dungeon.level.map;
		boolean[] visited = Dungeon.level.visited;
		boolean[] discoverable = Dungeon.level.discoverable;
		
		for (int i=0; i < length; i++) {
			
			int terr = map[i];
			
			if (discoverable[i]) {
				
				visited[i] = true;
				if ((Terrain.flags[terr] & Terrain.SECRET) != 0) {
					Dungeon.level.discover( i );
				}
			}
		}
		
		Bones.leave();
		
		Dungeon.observe();
		GameScene.updateFog();
				
		Dungeon.hero.belongings.identify();

		int pos = Dungeon.hero.pos;

		ArrayList<Integer> passable = new ArrayList<>();
		for (Integer ofs : PathFinder.NEIGHBOURS8) {
			int cell = pos + ofs;
			if ((Dungeon.level.passable[cell] || Dungeon.level.avoid[cell]) && Dungeon.level.heaps.get( cell ) == null) {
				passable.add( cell );
			}
		}
		Collections.shuffle( passable );

		ArrayList<Item> items = new ArrayList<>(Dungeon.hero.belongings.backpack.items);
		for (Integer cell : passable) {
			if (items.isEmpty()) {
				break;
			}

			Item item = Random.element( items );
			Dungeon.level.drop( item, cell ).sprite.drop( pos );
			items.remove( item );
		}

		for (Char c : Actor.chars()){
			if (c instanceof DriedRose.GhostHero){
				((DriedRose.GhostHero) c).sayHeroKilled();
			}
		}

		Game.runOnRenderThread(new Callback() {
			@Override
			public void call() {
				GameScene.gameOver();
				Sample.INSTANCE.play( Assets.Sounds.DEATH );
			}
		});

		if (cause instanceof Hero.Doom) {
			((Hero.Doom)cause).onDeath();
		}

		Dungeon.deleteGame( GamesInProgress.curSlot, true );
	}

	//effectively cache this buff to prevent having to call buff(...) a bunch.
	//This is relevant because we call isAlive during drawing, which has both performance
	//and thread coordination implications if that method calls buff(...) frequently
	private Berserk berserk;

	@Override
	public boolean isAlive() {
		
		if (HP <= 0){
			if (berserk == null) berserk = buff(Berserk.class);
			return berserk != null && berserk.berserking();
		} else {
			berserk = null;
			return super.isAlive();
		}
	}

	@Override
	public void move(int step, boolean travelling) {
		boolean wasHighGrass = Dungeon.level.map[step] == Terrain.HIGH_GRASS;

		super.move( step, travelling);
		
		if (!flying && travelling) {
			if (Dungeon.level.water[pos]) {
				Sample.INSTANCE.play( Assets.Sounds.WATER, 1, Random.Float( 0.8f, 1.25f ) );
			} else if (Dungeon.level.map[pos] == Terrain.EMPTY_SP) {
				Sample.INSTANCE.play( Assets.Sounds.STURDY, 1, Random.Float( 0.96f, 1.05f ) );
			} else if (Dungeon.level.map[pos] == Terrain.GRASS
					|| Dungeon.level.map[pos] == Terrain.EMBERS
					|| Dungeon.level.map[pos] == Terrain.FURROWED_GRASS){
				if (step == pos && wasHighGrass) {
					Sample.INSTANCE.play(Assets.Sounds.TRAMPLE, 1, Random.Float( 0.96f, 1.05f ) );
				} else {
					Sample.INSTANCE.play( Assets.Sounds.GRASS, 1, Random.Float( 0.96f, 1.05f ) );
				}
			} else {
				Sample.INSTANCE.play( Assets.Sounds.STEP, 1, Random.Float( 0.96f, 1.05f ) );
			}
		}
	}
	
	@Override
	public void onAttackComplete() {

		int exStr = 0;
		if (hero.belongings.weapon != null) {
			exStr = hero.STR() - hero.belongings.weapon.STRReq();
		}
		AttackIndicator.target(enemy);
		boolean wasEnemy = enemy.alignment == Alignment.ENEMY;

		boolean hit = attack( enemy );
		
		Invisibility.dispel();
		spend( attackDelay() );

		if (hero.buff(Sheathing.class) != null) {

			if (hit) {
				if (buff(Talent.IncisiveBladeTracker.class) != null && hasTalent(Talent.JUNG_INCISIVE_BLADE)) {
					Buff.affect(enemy, Bleeding.class).set(Math.round(Math.min(3, exStr)*(1+0.5f*hero.pointsInTalent(Talent.DEEP_SCAR))*(1+pointsInTalent(Talent.JUNG_INCISIVE_BLADE))));
					buff(Talent.IncisiveBladeTracker.class).detach();
				} else {
					Buff.affect(enemy, Bleeding.class).set(Math.round(Math.min(3, exStr)*(1+0.5f*hero.pointsInTalent(Talent.DEEP_SCAR))));
				}
			}
			if (!enemy.isAlive() && Dungeon.hero.hasTalent(Talent.FAST_LEAD) && Random.Int(3) < Dungeon.hero.pointsInTalent(Talent.FAST_LEAD)) {
				Buff.affect(hero, Sheathing.class);
			} else {
				hero.buff(Sheathing.class).detach();
			}
			if (hero.buff(Iaido.class) != null) {
				hero.buff(Iaido.class).detach();
			}
		}

		if (hit && buff(Shovel.CrippleTracker.class) != null) {
			Buff.affect(enemy, Cripple.class, 3f);
		}

		if (!enemy.isAlive() && hero.hasTalent(Talent.FAST_LEAD) && hero.heroClass != HeroClass.SAMURAI) {
			if (Random.Int(3) < hero.pointsInTalent(Talent.FAST_LEAD)) {
				hero.spend(-1f);
				Buff.prolong(hero, Adrenaline.class, 2f);
			}
		}

		if (hit &&  Random.Int(10) < hero.pointsInTalent(Talent.MADNESS)) {
			Buff.prolong(enemy, Amok.class, 3f);
		}

		if (hit && hero.hasTalent(Talent.PUSHBACK) && hero.buff(Talent.PushbackCooldown.class) == null) {
			Ballistica trajectory = new Ballistica(pos, enemy.pos, Ballistica.STOP_TARGET);
			trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
			WandOfBlastWave.throwChar(enemy, trajectory, 3, true, false, hero.getClass());
			Buff.affect(hero, Talent.PushbackCooldown.class, 35-5*Dungeon.hero.pointsInTalent(Talent.PUSHBACK));
		}

		if (hit && hero.hasTalent(Talent.ABSOLUTE_ZERO) && Random.Int(10) < hero.pointsInTalent(Talent.ABSOLUTE_ZERO)) {
			if (!enemy.properties().contains(Property.BOSS) && !enemy.properties().contains(Property.MINIBOSS)) {
				new FlavourBuff(){
					{actPriority = VFX_PRIO;}
					public boolean act() {
						Buff.affect( enemy, Frost.class, 20f);
						return super.act();
					}
				}.attachTo(enemy);
			}
		}

		if (hit
		 && subClass == HeroSubClass.FORTRESS
		 &&	Random.Int(20) < 1+belongings.armor.buffedLvl()+2*pointsInTalent(Talent.PREPARATION) //base 5%, +5%*armor lvl, +10%*talent lvl
		 && hero.belongings.armor != null
		 && buff(KnightsBlocking.class) != null) {
			Buff.affect(this,  CounterAttack.class, Actor.TICK);
			if (Random.Int(10) < 2*hero.pointsInTalent(Talent.COUNTER_MOMENTUM) && hero.buff(ShieldCoolDown.class) != null) {
				hero.buff(ShieldCoolDown.class).detach();
			}
		}

		if (hit && hasTalent(Talent.UPGRADE_SHARE) && buff(Talent.UpgradeShareCooldown.class) == null) {
			Buff.affect(hero, UpgradeShare.class).set(hero.belongings.weapon.level(), 3 * pointsInTalent(Talent.UPGRADE_SHARE));
			Item.updateQuickslot();
			Buff.affect(hero, Talent.UpgradeShareCooldown.class, 3 * pointsInTalent(Talent.UPGRADE_SHARE));
		}

		if (hit && subClass == HeroSubClass.GLADIATOR && wasEnemy){
			Buff.affect( this, Combo.class ).hit( enemy );
		}

		if (hit && subClass == HeroSubClass.BATTLEMAGE && hero.belongings.weapon() instanceof MagesStaff && hero.hasTalent(Talent.BATTLE_MAGIC)) {
			Buff.affect( this, MagicalCombo.class).hit( enemy );
		}

		if (hit && hasTalent(Talent.CHEMICAL)) {
			if (pointsInTalent(Talent.CHEMICAL) >= 1 && Random.Int(10) == 0) {
				Buff.affect( enemy, Poison.class ).set( 3 + Dungeon.scalingDepth()/2);
			}
			if (pointsInTalent(Talent.CHEMICAL) >= 2 && Random.Int(10) == 0) {
				Buff.affect( enemy, Ooze.class ).set(Ooze.DURATION/2);
			}
			if (pointsInTalent(Talent.CHEMICAL) == 3 && Random.Int(10) == 0) {
				Buff.affect( enemy, Corrosion.class ).set(5f, Dungeon.scalingDepth()/3);
			}
		}

		MagicalEmpower magicalEmpower = hero.buff(MagicalEmpower.class);
		if (hit && magicalEmpower != null && hero.belongings.weapon() instanceof MagesStaff) {
			magicalEmpower.use();
		}

		if (hit && buff(ParalysisTracker.class) != null) {
			Buff.affect(enemy, Paralysis.class, 2f);
			Buff.detach(this, ParalysisTracker.class);
		}

		if (hit && subClass == HeroSubClass.WEAPONMASTER) {
			if (belongings.weapon() instanceof WornShortsword
				|| belongings.weapon() instanceof WornShortsword_Energy
				|| belongings.weapon() instanceof Shortsword
				|| belongings.weapon() instanceof Sword
				|| belongings.weapon() instanceof Longsword
				|| belongings.weapon() instanceof Greatsword
				|| belongings.weapon() instanceof HugeSword
				|| belongings.weapon() instanceof HolySword
				|| belongings.weapon() instanceof Greataxe
				|| belongings.weapon() instanceof Quarterstaff
				|| belongings.weapon() instanceof Saber
				|| belongings.weapon() instanceof Saber_Energy
				|| belongings.weapon() instanceof RunicBlade
				|| belongings.weapon() instanceof TrueRunicBlade
				|| belongings.weapon() instanceof Bible
				|| belongings.weapon() instanceof UnholyBible
				|| belongings.weapon() instanceof SpellBook
				) {
				Buff.affect(this, AttackSpeedBuff.class).attack(enemy);
			}

			if (belongings.weapon() instanceof Gloves
					|| belongings.weapon() instanceof Gloves_Energy
					|| belongings.weapon() instanceof Sai
					|| belongings.weapon() instanceof Gauntlet
					|| belongings.weapon() instanceof BeamSaber
					|| belongings.weapon() instanceof ForceGlove
					|| belongings.weapon() instanceof DoubleGreatSword
					|| belongings.weapon() instanceof Nunchaku
			) {
				Buff.affect(this, AccuracyBuff.class).attack(enemy);
			}

			if ((belongings.weapon() instanceof HandAxe
					|| belongings.weapon() instanceof Mace
					|| belongings.weapon() instanceof BattleAxe
					|| belongings.weapon() instanceof WarHammer
					|| belongings.weapon() instanceof IronHammer
					|| belongings.weapon() instanceof LargeSword)
			) {
				if (Random.Int(40) < Math.min(1+belongings.weapon.buffedLvl(), 10)){
					Buff.affect(enemy, Vulnerable.class, 20f);
				}
			}

			if ((belongings.weapon() instanceof Scimitar
					|| belongings.weapon() instanceof FlameScimitar
					|| belongings.weapon() instanceof FrostScimitar
					|| belongings.weapon() instanceof PoisonScimitar
					|| belongings.weapon() instanceof ElectroScimitar)
			) {
				Buff.affect(enemy, Bleeding.class).set(Math.min(1+belongings.weapon.buffedLvl(), 10));
			}

			if ((belongings.weapon() instanceof Whip || belongings.weapon() instanceof ChainWhip) && Random.Int(20) < Math.min(belongings.weapon.buffedLvl()+1, 10)) {
				Buff.affect(enemy, Roots.class, 2f);
			}

			if ((belongings.weapon() instanceof Flail || belongings.weapon() instanceof ChainFlail) && Random.Int(40) < Math.min(belongings.weapon.buffedLvl()+1, 10)) {
				Buff.affect(enemy, Paralysis.class, 2f);
			}

			if ((belongings.weapon() instanceof Crossbow
						|| belongings.weapon() instanceof ExplosiveCrossbow
						|| belongings.weapon() instanceof Ballista
						|| belongings.weapon() instanceof CrudePistol
						|| belongings.weapon() instanceof CrudePistol_Energy
					  	|| belongings.weapon() instanceof Pistol
					  	|| belongings.weapon() instanceof GoldenPistol
					  	|| belongings.weapon() instanceof Handgun
					  	|| belongings.weapon() instanceof Magnum
					  	|| belongings.weapon() instanceof TacticalHandgun
					  	|| belongings.weapon() instanceof AutoHandgun
					  	|| belongings.weapon() instanceof DualPistol
					  	|| belongings.weapon() instanceof SubMachinegun
					  	|| belongings.weapon() instanceof AssultRifle
					  	|| belongings.weapon() instanceof HeavyMachinegun
					  	|| belongings.weapon() instanceof MiniGun
					  	|| belongings.weapon() instanceof AutoRifle
						|| belongings.weapon() instanceof Revolver
					  	|| belongings.weapon() instanceof HuntingRifle
						|| belongings.weapon() instanceof Carbine
					  	|| belongings.weapon() instanceof SniperRifle
					  	|| belongings.weapon() instanceof AntimaterRifle
					  	|| belongings.weapon() instanceof MarksmanRifle
						|| belongings.weapon() instanceof WA2000
					  	|| belongings.weapon() instanceof ShotGun
					  	|| belongings.weapon() instanceof KSG
					  	|| belongings.weapon() instanceof RocketLauncher
					  	|| belongings.weapon() instanceof RPG7
					  	|| belongings.weapon() instanceof FlameThrower
					  	|| belongings.weapon() instanceof PlasmaCannon)
					&& level.adjacent(enemy.pos, this.pos)
					&& Random.Int(40) < Math.min(1+belongings.weapon.buffedLvl(), 10) ) {
				Ballistica trajectory = new Ballistica(pos, enemy.pos, Ballistica.STOP_TARGET);
				trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
				WandOfBlastWave.throwChar(enemy, trajectory, 3, true, false, hero.getClass());
			}
		}


		if (hit && subClass == HeroSubClass.VETERAN){
			if (Dungeon.hero.belongings.weapon() instanceof CrudePistol
					|| Dungeon.hero.belongings.weapon() instanceof Pistol
					|| Dungeon.hero.belongings.weapon() instanceof GoldenPistol
					|| Dungeon.hero.belongings.weapon() instanceof Handgun
					|| Dungeon.hero.belongings.weapon() instanceof Magnum
					|| Dungeon.hero.belongings.weapon() instanceof TacticalHandgun
					|| Dungeon.hero.belongings.weapon() instanceof AutoHandgun
					|| Dungeon.hero.belongings.weapon() instanceof DualPistol
					|| Dungeon.hero.belongings.weapon() instanceof SubMachinegun
					|| Dungeon.hero.belongings.weapon() instanceof AssultRifle
					|| Dungeon.hero.belongings.weapon() instanceof HeavyMachinegun
					|| Dungeon.hero.belongings.weapon() instanceof MiniGun
					|| Dungeon.hero.belongings.weapon() instanceof AutoRifle
					|| Dungeon.hero.belongings.weapon() instanceof Revolver
					|| Dungeon.hero.belongings.weapon() instanceof HuntingRifle
					|| Dungeon.hero.belongings.weapon() instanceof Carbine
					|| Dungeon.hero.belongings.weapon() instanceof SniperRifle
					|| Dungeon.hero.belongings.weapon() instanceof AntimaterRifle
					|| Dungeon.hero.belongings.weapon() instanceof MarksmanRifle
					|| Dungeon.hero.belongings.weapon() instanceof WA2000
					|| Dungeon.hero.belongings.weapon() instanceof ShotGun
					|| Dungeon.hero.belongings.weapon() instanceof KSG
					|| Dungeon.hero.belongings.weapon() instanceof RocketLauncher
					|| Dungeon.hero.belongings.weapon() instanceof RPG7
					|| Dungeon.hero.belongings.weapon() instanceof FlameThrower
					|| Dungeon.hero.belongings.weapon() instanceof PlasmaCannon
			) {
				Buff.affect( this, Focusing.class ).hit( enemy );
			}
		}

		if (hit && subClass == HeroSubClass.VETERAN && belongings.weapon() instanceof MissileWeapon && hero.pointsInTalent(Talent.FOCUS_UPGRADE) == 3) {
			BrokenSeal.WarriorShield shield = hero.buff(BrokenSeal.WarriorShield.class);
			if (shield != null){
				Buff.affect( this, Focusing.class ).hit( enemy );
			}
		}

		if (hit && hero.hasTalent(Talent.ELASTIC_WEAPON) && Random.Int(5) < hero.pointsInTalent(Talent.ELASTIC_WEAPON)) {
			if (Dungeon.hero.belongings.weapon() instanceof CrudePistol
					|| Dungeon.hero.belongings.weapon() instanceof Pistol
					|| Dungeon.hero.belongings.weapon() instanceof GoldenPistol
					|| Dungeon.hero.belongings.weapon() instanceof Handgun
					|| Dungeon.hero.belongings.weapon() instanceof Magnum
					|| Dungeon.hero.belongings.weapon() instanceof TacticalHandgun
					|| Dungeon.hero.belongings.weapon() instanceof AutoRifle
					|| Dungeon.hero.belongings.weapon() instanceof DualPistol
					|| Dungeon.hero.belongings.weapon() instanceof SubMachinegun
					|| Dungeon.hero.belongings.weapon() instanceof AssultRifle
					|| Dungeon.hero.belongings.weapon() instanceof HeavyMachinegun
					|| Dungeon.hero.belongings.weapon() instanceof MiniGun
					|| Dungeon.hero.belongings.weapon() instanceof AutoRifle
					|| Dungeon.hero.belongings.weapon() instanceof Revolver
					|| Dungeon.hero.belongings.weapon() instanceof HuntingRifle
					|| Dungeon.hero.belongings.weapon() instanceof Carbine
					|| Dungeon.hero.belongings.weapon() instanceof SniperRifle
					|| Dungeon.hero.belongings.weapon() instanceof AntimaterRifle
					|| Dungeon.hero.belongings.weapon() instanceof MarksmanRifle
					|| Dungeon.hero.belongings.weapon() instanceof WA2000
					|| Dungeon.hero.belongings.weapon() instanceof ShotGun
					|| Dungeon.hero.belongings.weapon() instanceof KSG
					|| Dungeon.hero.belongings.weapon() instanceof RocketLauncher
					|| Dungeon.hero.belongings.weapon() instanceof RPG7
					|| Dungeon.hero.belongings.weapon() instanceof FlameThrower
					|| Dungeon.hero.belongings.weapon() instanceof PlasmaCannon
					|| Dungeon.hero.belongings.weapon() instanceof GrenadeLauncher
					|| Dungeon.hero.belongings.weapon() instanceof GrenadeLauncherAP
					|| Dungeon.hero.belongings.weapon() instanceof GrenadeLauncherHP
			) {
				//trace a ballistica to our target (which will also extend past them
				Ballistica trajectory = new Ballistica(pos, enemy.pos, Ballistica.STOP_TARGET);
				//trim it to just be the part that goes past them
				trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
				//knock them back along that ballistica
				WandOfBlastWave.throwChar(enemy, trajectory, 3, true, false, hero.getClass());
			}
		}

		if (hit && hero.hasTalent(Talent.BAYONET) && hero.buff(ReinforcedArmor.reinforcedArmorTracker.class) != null){
			if (Dungeon.hero.belongings.weapon() instanceof CrudePistol
					|| Dungeon.hero.belongings.weapon() instanceof Pistol
					|| Dungeon.hero.belongings.weapon() instanceof GoldenPistol
					|| Dungeon.hero.belongings.weapon() instanceof Handgun
					|| Dungeon.hero.belongings.weapon() instanceof Magnum
					|| Dungeon.hero.belongings.weapon() instanceof TacticalHandgun
					|| Dungeon.hero.belongings.weapon() instanceof AutoHandgun
					|| Dungeon.hero.belongings.weapon() instanceof DualPistol
					|| Dungeon.hero.belongings.weapon() instanceof SubMachinegun
					|| Dungeon.hero.belongings.weapon() instanceof AssultRifle
					|| Dungeon.hero.belongings.weapon() instanceof HeavyMachinegun
					|| Dungeon.hero.belongings.weapon() instanceof MiniGun
					|| Dungeon.hero.belongings.weapon() instanceof AutoRifle
					|| Dungeon.hero.belongings.weapon() instanceof Revolver
					|| Dungeon.hero.belongings.weapon() instanceof HuntingRifle
					|| Dungeon.hero.belongings.weapon() instanceof Carbine
					|| Dungeon.hero.belongings.weapon() instanceof SniperRifle
					|| Dungeon.hero.belongings.weapon() instanceof AntimaterRifle
					|| Dungeon.hero.belongings.weapon() instanceof MarksmanRifle
					|| Dungeon.hero.belongings.weapon() instanceof WA2000
					|| Dungeon.hero.belongings.weapon() instanceof ShotGun
					|| Dungeon.hero.belongings.weapon() instanceof KSG
					|| Dungeon.hero.belongings.weapon() instanceof RocketLauncher
					|| Dungeon.hero.belongings.weapon() instanceof RPG7
					|| Dungeon.hero.belongings.weapon() instanceof FlameThrower
					|| Dungeon.hero.belongings.weapon() instanceof PlasmaCannon
					|| Dungeon.hero.belongings.weapon() instanceof GrenadeLauncher
					|| Dungeon.hero.belongings.weapon() instanceof GrenadeLauncherAP
					|| Dungeon.hero.belongings.weapon() instanceof GrenadeLauncherHP
			) {
				Buff.affect( enemy, Bleeding.class ).set( 4 + hero.pointsInTalent(Talent.BAYONET));
			}
		}

		if (hero.hasTalent(Talent.SURPRISE_STAB) && ((Mob) enemy).surprisedBy(hero) && hero.buff(Talent.SurpriseStabTracker.class) == null) {
			Buff.affect(this, Talent.SurpriseStabTracker.class);
		}

		if (hero.subClass == HeroSubClass.CHASER && hero.hasTalent(Talent.CHAIN_CLOCK) && ((Mob) enemy).surprisedBy(hero) && hero.buff(Talent.ChainCooldown.class) == null){
			Buff.affect( this, Invisibility.class, 1f * hero.pointsInTalent(Talent.CHAIN_CLOCK));
			Buff.affect( this, Haste.class, 1f * hero.pointsInTalent(Talent.CHAIN_CLOCK));
			Buff.affect( this, Talent.ChainCooldown.class, 10f);
			Sample.INSTANCE.play( Assets.Sounds.MELD );
		}

		if (hero.subClass == HeroSubClass.CHASER && hero.hasTalent(Talent.LETHAL_SURPRISE) && ((Mob) enemy).surprisedBy(hero) && !enemy.isAlive() && hero.buff(Talent.LethalCooldown.class) == null) {
			if (hero.pointsInTalent(Talent.LETHAL_SURPRISE) >= 1) {
				for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
					if (mob.alignment != Char.Alignment.ALLY && Dungeon.level.heroFOV[mob.pos]) {
						Buff.affect( mob, Vulnerable.class, 1f);
					}
				}
				Buff.affect(hero, Talent.LethalCooldown.class, 5f);
			}
			if (hero.pointsInTalent(Talent.LETHAL_SURPRISE) >= 2) {
				for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
					if (mob.alignment != Char.Alignment.ALLY && Dungeon.level.heroFOV[mob.pos]) {
						Buff.affect( mob, Paralysis.class, 1f);
					}
				}
			}
			if (hero.pointsInTalent(Talent.LETHAL_SURPRISE) == 3) {
				Buff.affect(hero, Swiftthistle.TimeBubble.class).twoTurns();
			}
		}

		if (hit && hero.hasTalent(Talent.DESTRUCTIVE_ATK)) {
			if (Random.Int(40) < hero.pointsInTalent(Talent.DESTRUCTIVE_ATK)) {
				Buff.affect(enemy, Vulnerable.class, 20f);
			}
			Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
		}

		if (!hit && hero.belongings.weapon == null && hero.subClass == HeroSubClass.FIGHTER && Random.Int(5) == 0 && hero.pointsInTalent(Talent.SWIFT_MOVEMENT) > 1) {
			Buff.prolong(hero, EvasiveMove.class, 0.9999f);
		}
		if (hit && hero.belongings.weapon == null && hero.subClass == HeroSubClass.FIGHTER && hero.hasTalent(Talent.RING_KNUCKLE) && !((hero.belongings.misc instanceof RingOfForce) || (hero.belongings.ring instanceof RingOfForce))) {
			Buff.prolong(hero, EnhancedRingsCombo.class, (Dungeon.hero.pointsInTalent(Talent.RING_KNUCKLE) >= 2) ? 2f : 1f).hit();
			hero.updateHT(false);
			updateQuickslot();
		}
		if (hit && hero.belongings.weapon == null && hero.subClass == HeroSubClass.FIGHTER && Random.Int(3) < hero.pointsInTalent(Talent.QUICK_STEP)) {
			Buff.prolong(hero, QuickStep.class, 1.0001f);
		}
		if (hit && hero.subClass == HeroSubClass.ENGINEER) {
			if (Dungeon.hero.belongings.weapon() instanceof CrudePistol
					|| Dungeon.hero.belongings.weapon() instanceof Pistol
					|| Dungeon.hero.belongings.weapon() instanceof GoldenPistol
					|| Dungeon.hero.belongings.weapon() instanceof Handgun
					|| Dungeon.hero.belongings.weapon() instanceof Magnum
					|| Dungeon.hero.belongings.weapon() instanceof TacticalHandgun
					|| Dungeon.hero.belongings.weapon() instanceof AutoHandgun
					|| Dungeon.hero.belongings.weapon() instanceof DualPistol
					|| Dungeon.hero.belongings.weapon() instanceof SubMachinegun
					|| Dungeon.hero.belongings.weapon() instanceof AssultRifle
					|| Dungeon.hero.belongings.weapon() instanceof HeavyMachinegun
					|| Dungeon.hero.belongings.weapon() instanceof MiniGun
					|| Dungeon.hero.belongings.weapon() instanceof AutoRifle
					|| Dungeon.hero.belongings.weapon() instanceof Revolver
					|| Dungeon.hero.belongings.weapon() instanceof HuntingRifle
					|| Dungeon.hero.belongings.weapon() instanceof Carbine
					|| Dungeon.hero.belongings.weapon() instanceof SniperRifle
					|| Dungeon.hero.belongings.weapon() instanceof AntimaterRifle
					|| Dungeon.hero.belongings.weapon() instanceof MarksmanRifle
					|| Dungeon.hero.belongings.weapon() instanceof WA2000
					|| Dungeon.hero.belongings.weapon() instanceof ShotGun
					|| Dungeon.hero.belongings.weapon() instanceof KSG
					|| Dungeon.hero.belongings.weapon() instanceof RocketLauncher
					|| Dungeon.hero.belongings.weapon() instanceof RPG7
					|| Dungeon.hero.belongings.weapon() instanceof FlameThrower
					|| Dungeon.hero.belongings.weapon() instanceof PlasmaCannon
			) {
				if (hero.pointsInTalent(Talent.CONNECTING_CHARGER) >= 1 && hero.pointsInTalent(Talent.CONNECTING_CHARGER) < 3 && Random.Int(5) == 0 && hero.pointsInTalent(Talent.CONNECTING_CHARGER) < 3) {
					Buff.affect(this, Recharging.class, 1f);
				}
				if (hero.pointsInTalent(Talent.CONNECTING_CHARGER) == 2 && Random.Int(5) == 0 && hero.pointsInTalent(Talent.CONNECTING_CHARGER) < 3) {
					Buff.affect(this, ArtifactRecharge.class).set( 1 );
				}
				if (hero.pointsInTalent(Talent.CONNECTING_CHARGER) == 3) {
					if (Random.Int(4) == 0) {
						Buff.affect(this, Recharging.class, 2f);
					}
					if (Random.Int(4) == 0) {
						Buff.affect(this, ArtifactRecharge.class).prolong( 2 );
					}
				}
				if (hero.hasTalent(Talent.BATTERY_CHARGE)) {
					MagesStaff staff = hero.belongings.getItem(MagesStaff.class);
					if (staff != null) {
						staff.gainCharge(0.5f, false);
						ScrollOfRecharging.charge(hero);
					}
				}
				if (Random.Int(5) == 0) {
					MagesStaff staff = hero.belongings.getItem(MagesStaff.class);
					int chance = 50;
					if (hero.pointsInTalent(Talent.BATTERY_CHARGE) == 3) {
						chance += 4*staff.getCurCharges();
					}
					if (Random.Int(100) < chance) {
						Buff.affect(enemy, Paralysis.class, 2f + hero.pointsInTalent(Talent.HIGH_VOLT));
						enemy.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
						enemy.sprite.flash();
						Sample.INSTANCE.play( Assets.Sounds.LIGHTNING );
					}
					int shield = 10;
					if (hero.pointsInTalent(Talent.BATTERY_CHARGE) > 1) {
						shield += staff.getCurCharges();
					}
					Buff.affect(this, Barrier.class).setShield(shield);
					if (hero.hasTalent(Talent.STATIC_ENERGY)) {
						ArrayList<Lightning.Arc> arcs = new ArrayList<>();
						ArrayList<Char> affected = new ArrayList<>();
						affected.clear();
						arcs.clear();

						Shocking.arc(hero, enemy, 2, affected, arcs);

						affected.remove(enemy); //defender isn't hurt by lightning
						for (Char ch : affected) {
							if (ch.alignment != hero.alignment) {
								ch.damage(Math.round(5), this);
								if (hero.pointsInTalent(Talent.STATIC_ENERGY) >= 2 ) {
									Buff.affect(ch, Paralysis.class, 2f + hero.pointsInTalent(Talent.HIGH_VOLT));
								}
							}
						}
						if (hero.pointsInTalent((Talent.STATIC_ENERGY)) == 3 ) {
							GameScene.add( Blob.seed( enemy.pos, 3, Electricity.class ) );
						}

						hero.sprite.parent.addToFront( new Lightning( arcs, null ) );
						Sample.INSTANCE.play( Assets.Sounds.LIGHTNING );
					}
				}
			}
		}

		if (hit && hero.heroClass == HeroClass.KNIGHT && hero.buff(ShieldCoolDown.class) != null) {
			hero.buff(ShieldCoolDown.class).hit(hero.belongings.armor.buffedLvl());
		}

		if (hit && hero.hasTalent(Talent.POISONOUS_BLADE)
				&& enemy instanceof Mob && ((Mob) enemy).surprisedBy(hero)
		) {
			Buff.affect(enemy, Poison.class).set(2+hero.pointsInTalent(Talent.POISONOUS_BLADE));
		}

		if (hit && hero.pointsInTalent(Talent.MASTER_OF_CLOAKING) == 3
				&& enemy instanceof Mob && ((Mob) enemy).surprisedBy(hero)
		) {
			if (hero.buff(Talent.ChaseCooldown.class) != null) {
				hero.buff(Talent.ChaseCooldown.class).spendTime();
			}
			if (hero.buff(Talent.ChainCooldown.class) != null) {
				hero.buff(Talent.ChainCooldown.class).spendTime();
			}
			if (hero.buff(Talent.LethalCooldown.class) != null) {
				hero.buff(Talent.LethalCooldown.class).spendTime();
			}
		}

		if (hit && hero.hasTalent(Talent.KICK)
				&& enemy.buff(PinCushion.class) != null
				&& hero.buff(Talent.KickCooldown.class) == null
		) {
			Item item = enemy.buff(PinCushion.class).grabOne();
			if (item.doPickUp(hero, enemy.pos)){
				hero.spend(-1); //attacking enemy already takes a turn
			} else {
				GLog.w(Messages.get(this, "cant_grab"));
				Dungeon.level.drop(item, enemy.pos).sprite.drop();
				return;
			}
			Ballistica trajectory = new Ballistica(hero.pos, enemy.pos, Ballistica.STOP_TARGET);
			trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size() - 1), Ballistica.PROJECTILE);
			int dist = hero.pointsInTalent(Talent.KICK);
			WandOfBlastWave.throwChar(enemy, trajectory, dist, true, false ,hero.getClass());
			Buff.affect(hero, Talent.KickCooldown.class, 10f);
		}

		if (hit && hero.hasTalent(Talent.SOUL_COLLECT)
				&& enemy instanceof Mob && ((Mob) enemy).surprisedBy(hero) && !enemy.isAlive()
		) {
			int healAmt = 3*hero.pointsInTalent(Talent.SOUL_COLLECT);
			healAmt = Math.min( healAmt, hero.HT - hero.HP );
			if (healAmt > 0 && hero.isAlive()) {
				hero.HP += healAmt;
				hero.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 2 );
				hero.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );
			}
		}

		if (hit && hero.hasTalent(Talent.TRAIL_TRACKING)
				&& enemy instanceof Mob && ((Mob) enemy).surprisedBy(hero) && !enemy.isAlive()
		) {
			Buff.affect(hero, MindVision.class, hero.pointsInTalent(Talent.TRAIL_TRACKING));
		}

		if (hit && hero.belongings.weapon == null && hero.hasTalent(Talent.MYSTICAL_PUNCH)) {
			if (RingOfAccuracy.getBuffedBonus(this, RingOfAccuracy.Accuracy.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(100) < RingOfAccuracy.getBuffedBonus(this, RingOfAccuracy.Accuracy.class) && !(enemy.properties().contains(Property.BOSS) || enemy.properties().contains(Property.MINIBOSS))) {
					enemy.damage( enemy.HP, this );
					enemy.sprite.emitter().burst( ShadowParticle.UP, 5 );
				}
			}
			if (RingOfElements.getBuffedBonus(this, RingOfElements.Resistance.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				switch (Random.Int(5)) {
					case 0: default:
						Buff.affect(enemy, Burning.class).reignite(enemy);
						break;
					case 1:
						Buff.affect(enemy, Poison.class).set(RingOfElements.getBuffedBonus(this, RingOfElements.Resistance.class));
						if (Dungeon.level.heroFOV[enemy.pos]) {
							CellEmitter.center( enemy.pos ).burst( PoisonParticle.SPLASH, 3 );
						}
						break;
					case 2:
						float durationToAdd = 3f;
						Chill existing = enemy.buff(Chill.class);
						if (existing != null){
							durationToAdd = Math.min(durationToAdd, 6f-existing.cooldown());
						}

						Buff.affect( enemy, Chill.class, durationToAdd );
						Splash.at( enemy.sprite.center(), 0xFFB2D6FF, 5);
						break;
					case 3:
						Buff.affect(enemy, Ooze.class).set(Ooze.DURATION/2);
						enemy.sprite.burst( 0x000000, 5 );
					case 4:
						Buff.affect(enemy, Paralysis.class, 2f);
						enemy.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			if (RingOfEnergy.getBuffedBonus(this, RingOfEnergy.Energy.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				for (Buff b : hero.buffs()){
					if (b instanceof Artifact.ArtifactBuff && !((Artifact.ArtifactBuff) b).isCursed() ) {
						((Artifact.ArtifactBuff) b).charge(hero, 0.2f*RingOfEnergy.getBuffedBonus(this, RingOfEnergy.Energy.class));
					}
				}
				ScrollOfRecharging.charge(hero);
			}
			if (RingOfEvasion.getBuffedBonus(this, RingOfEvasion.Evasion.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(25) < RingOfEvasion.getBuffedBonus(this, RingOfEvasion.Evasion.class)) {
					Buff.affect(hero, EvasiveMove.class, 1f);
				}
			}
			if (RingOfForce.getBuffedBonus(this, RingOfForce.Force.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(3) == 0) {
					enemy.damage( RingOfForce.getBuffedBonus(this, RingOfForce.Force.class), this );
				}
			}
			if (RingOfFuror.getBuffedBonus(this, RingOfFuror.Furor.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(100) < RingOfFuror.getBuffedBonus(this, RingOfFuror.Furor.class)) {
					Buff.affect(hero, Talent.MysticalPunchTracker.class);
				}
			}
			if (RingOfFury.getBuffedBonus(this, RingOfFury.Fury.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Float() < (float)((hero.HT-hero.HP)/hero.HT)) {
					enemy.damage( Math.round(RingOfFury.getBuffedBonus(this, RingOfFury.Fury.class) * RingOfFury.dealingMultiplier( hero )), this );
				}
			}
			if (RingOfHaste.getBuffedBonus(this, RingOfHaste.Haste.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(5) == 0) {
					Buff.affect(hero, Haste.class, RingOfHaste.getBuffedBonus(this, RingOfHaste.Haste.class));
				}
			}
			if (RingOfMight.getBuffedBonus(this, RingOfMight.Might.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(100) < RingOfMight.getBuffedBonus(this, RingOfMight.Might.class)) {
					int healAmt = Math.round((hero.HT - hero.HP)/10f);
					if (healAmt > 0 && hero.isAlive()) {
						hero.HP += healAmt;
						hero.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
						hero.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );
					}
				}
			}
			if (RingOfReload.getBuffedBonus(this, RingOfReload.Reload.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(5) < RingOfReload.getBuffedBonus(this, RingOfReload.Reload.class)) {
					Buff.prolong(hero, ArrowEnhance.class, ArrowEnhance.DURATION);
				}
			}
			if (RingOfRush.getBuffedBonus(this, RingOfRush.Rush.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(25) < RingOfRush.getBuffedBonus(this, RingOfRush.Rush.class)) {
					Buff.affect(hero, Talent.MysticalPunchTracker.class);
				}
			}
			if (RingOfSatisfying.getBuffedBonus(this, RingOfSatisfying.Satisfaction.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				Buff.affect(Dungeon.hero, Hunger.class).affectHunger(RingOfSatisfying.getBuffedBonus(this, RingOfSatisfying.Satisfaction.class));
			}
			if (RingOfSharpshooting.getBuffedBonus(this, RingOfSharpshooting.Aim.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(20) < RingOfSharpshooting.getBuffedBonus(this, RingOfSharpshooting.Aim.class)) {
					Buff.prolong(hero, ArrowEnhance.class, ArrowEnhance.DURATION);
				}
			}
			if (RingOfShield.getBuffedBonus(this, RingOfShield.Shield.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(8) == 0) {
					Buff.affect(hero, Barkskin.class).set(RingOfShield.getBuffedBonus(this, RingOfShield.Shield.class), 1);
				}
			}
			if (RingOfTenacity.getBuffedBonus(this, RingOfTenacity.Tenacity.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(3) == 0) {
					Buff.affect(hero, Barrier.class).setShield(RingOfTenacity.getBuffedBonus(this, RingOfTenacity.Tenacity.class));
				}
			}
			if (RingOfVampire.getBuffedBonus(this, RingOfVampire.Vampire.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				//heals for 10% of damage dealt
				int healAmt = Math.round(damageRoll() * 0.1f);
				healAmt = Math.min( healAmt, hero.HT - hero.HP );

				if (healAmt > 0 && hero.isAlive()) {

					hero.HP += healAmt;
					hero.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
					hero.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );

				}
			}
			if (RingOfVorpal.getBuffedBonus(this, RingOfVorpal.Vorpal.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(20) < RingOfVorpal.getBuffedBonus(this, RingOfVorpal.Vorpal.class)) {
					Buff.affect(enemy, Bleeding.class).set(Math.round(RingOfVorpal.getBuffedBonus(this, RingOfVorpal.Vorpal.class)/2f));
				}
			}
			if (RingOfWealth.getBuffedBonus(this, RingOfWealth.Wealth.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				if (Random.Int(25) < RingOfWealth.getBuffedBonus(this, RingOfWealth.Wealth.class)) {
					float powerMulti = Math.max(1f, RingOfArcana.enchantPowerMultiplier(hero));
					Buff.affect(enemy, Lucky.LuckProc.class).ringLevel = -10 + Math.round(5*powerMulti);
				}
			}
			if (RingOfArcana.getBuffedBonus(this, RingOfArcana.Arcana.class) > 0 && Random.Int(3) < hero.pointsInTalent(Talent.MYSTICAL_PUNCH)) {
				int level = (int)Math.ceil(hero.lvl / 5f);
				if (Random.Int(10) < RingOfArcana.getBuffedBonus(this, RingOfArcana.Arcana.class)) {
					float powerMulti = Math.max(1f, RingOfArcana.enchantPowerMultiplier(hero));
					switch (Random.Int(16)) {
						case 0: default:
							//Blazing Effect
							if (enemy.buff(Burning.class) != null){
								Buff.affect(enemy, Burning.class).reignite(enemy, 8f);
								int burnDamage = Random.NormalIntRange( 1, 3 + Dungeon.scalingDepth()/4 );
								enemy.damage( Math.round(burnDamage * 0.67f * powerMulti), this );
							} else {
								Buff.affect(enemy, Burning.class).reignite(enemy, 8f);
							}

							enemy.sprite.emitter().burst( FlameParticle.FACTORY, level + 1 );
							break;
						case 1:
							//Blocking Effect
							Blocking.BlockBuff b = Buff.affect(hero, Blocking.BlockBuff.class);
							b.setShield(hero.HT/10);
							hero.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 5);
							break;
						case 2:
							//Blooming Effect
							float plants = (1f + 0.1f*level) * powerMulti;
							if (Random.Float() < plants%1){
								plants = (float)Math.ceil(plants);
							} else {
								plants = (float)Math.floor(plants);
							}

							Blooming blooming = new Blooming();
							if (blooming.plantGrass(enemy.pos)){
								plants--;
								if (plants <= 0){
									break;
								}
							}

							ArrayList<Integer> positions = new ArrayList<>();
							for (int i : PathFinder.NEIGHBOURS8){
								if (enemy.pos + i != hero.pos) {
									positions.add(enemy.pos + i);
								}
							}
							Random.shuffle( positions );

							//The attacker's position is always lowest priority
							if (Dungeon.level.adjacent(hero.pos, enemy.pos)){
								positions.add(hero.pos);
							}

							for (int i : positions){
								if (blooming.plantGrass(i)){
									plants--;
									if (plants <= 0) {
										break;
									}
								}
							}
							break;
						case 3:
							//Chilling Effect
							float durationToAdd = 3f * powerMulti;
							Chill existing = enemy.buff(Chill.class);
							if (existing != null){
								durationToAdd = Math.min(durationToAdd, (6f*powerMulti)-existing.cooldown());
							}

							Buff.affect( enemy, Chill.class, durationToAdd );
							Splash.at( enemy.sprite.center(), 0xFFB2D6FF, 5);
							break;
						case 4:
							//Kinetic Effect, See RingOfForce.damageRoll too
							int conservedDamage = 0;
							if (hero.buff(Kinetic.ConservedDamage.class) != null) {
								conservedDamage = hero.buff(Kinetic.ConservedDamage.class).damageBonus();
								hero.buff(Kinetic.ConservedDamage.class).detach();
							}

							Buff.affect(hero, Kinetic.KineticTracker.class).conservedDamage = conservedDamage;
							break;
						case 5:
							//Corrupting Effect
							if (damageRoll() > enemy.HP
									&& !enemy.isImmune(Corruption.class)
									&& enemy.buff(Corruption.class) == null
									&& enemy instanceof Mob
									&& enemy.isAlive()){

								Mob mob = (Mob) enemy;

								Corruption.corruptionHeal(mob);
								Buff.affect(enemy, AllyBuff.AllyBuffTracker.class);

								AllyBuff.affectAndLoot(mob, hero, Corruption.class);

								if (powerMulti > 1.1f){
									//1 turn of adrenaline for each 20% above 100% proc rate
									Buff.affect(mob, Adrenaline.class, Math.round(5*(powerMulti-1f)));
								}
							}
							break;
						case 6:
							//Elastic Effect
							//trace a ballistica to our target (which will also extend past them
							Ballistica trajectory = new Ballistica(hero.pos, enemy.pos, Ballistica.STOP_TARGET);
							//trim it to just be the part that goes past them
							trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
							//knock them back along that ballistica
							WandOfBlastWave.throwChar(enemy,
									trajectory,
									Math.round(2 * powerMulti),
									true,
									true,
									getClass());
							break;
						case 7:
							//Grim Effect
							enemy.damage( enemy.HP, this );
							enemy.sprite.emitter().burst( ShadowParticle.UP, 5 );
							break;
						case 8:
							//Lucky Effect
							Buff.affect(enemy, Lucky.LuckProc.class).ringLevel = -10 + Math.round(5*powerMulti);
							break;
						case 9:
							//Shocking Effect
							ArrayList<Lightning.Arc> arcs = new ArrayList<>();
							ArrayList<Char> affected = new ArrayList<>();
							affected.clear();
							arcs.clear();

							Shocking.arc(hero, enemy, 2, affected, arcs);

							affected.remove(enemy); //defender isn't hurt by lightning
							for (Char ch : affected) {
								if (ch.alignment != hero.alignment) {
									ch.damage(Math.round(damageRoll() * 0.4f * powerMulti), this);
								}
							}
							break;
						case 10:
							//Vampiric Effect
							//heals for 50% of damage dealt
							int healAmt = Math.round(damageRoll() * 0.5f * powerMulti);
							healAmt = Math.min( healAmt, hero.HT - hero.HP );

							if (healAmt > 0 && hero.isAlive()) {
								hero.HP += healAmt;
								hero.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
								hero.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );
							}
							break;
						case 11:
							//Shiny Effect
							Buff.prolong( enemy, Blindness.class, Random.Float( 1f, (1f + level) * RingOfArcana.enchantPowerMultiplier(hero) ) );
							Buff.prolong( enemy, Cripple.class, Random.Float( 1f, (1f + level/2f) * RingOfArcana.enchantPowerMultiplier(hero) ) );
							enemy.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
							break;
						case 12:
							//Eldritch Effect
							Buff.prolong( enemy, Terror.class, 10f*powerMulti + 5f ).object = hero.id();
							break;
						case 13:
							//Stunning Effect
							Buff.prolong( enemy, Paralysis.class, 1f * powerMulti );
							enemy.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 12 );
							break;
						case 14:
							//Venomous Effect
							Buff.affect( enemy, Poison.class ).extend( ((int)(level/2) + 1) * powerMulti );
							CellEmitter.center(enemy.pos).burst( PoisonParticle.SPLASH, 5 );
							break;
						case 15:
							//Vorpal Effect
							Buff.affect(enemy, Bleeding.class).set((damageRoll()/10f) * powerMulti);
							Splash.at( enemy.sprite.center(), -PointF.PI / 2, PointF.PI / 6, enemy.sprite.blood(), 10 );
							break;
					}
				}
			}
		}

		//if (hit && !enemy.isAlive() && Random.Int(10) < 2 * hero.pointsInTalent(Talent.SINGULAR_STRIKE)) {
		//	Buff.affect(hero, Talent.SingularStrikeTracker.class);
		//}

		//if (hit && enemy.isAlive() && hero.belongings.weapon() == null && hero.buff(Talent.SingularStrikeTracker.class) != null) {
		//	Buff.affect(enemy, Vulnerable.class, Vulnerable.DURATION);
		//	hero.buff(Talent.SingularStrikeTracker.class).detach();
		//}

		if (hero.buff(Outlaw.class) != null) {
			hero.buff(Outlaw.class).detach();
		}

		if (hit && hero.subClass == HeroSubClass.TREASUREHUNTER && (hero.belongings.weapon() instanceof Shovel || hero.belongings.weapon() instanceof GildedShovel || hero.belongings.weapon() instanceof Spade || hero.belongings.weapon() instanceof MinersTool)) {

			int amount = 1 + Math.round(hero.belongings.weapon.level()/2f);
			if (hero.hasTalent(Talent.GOLD_MINER) && Random.Int(5) == 0) {
				amount *= 1+hero.pointsInTalent(Talent.GOLD_MINER);
			}
			Dungeon.level.drop( new Gold(amount), enemy.pos ).sprite.drop();
		}

		if (hit && Random.Int(10) < hero.pointsInTalent(Talent.EARTHQUAKE) && level.adjacent(hero.pos, enemy.pos) && hero.belongings.weapon() instanceof MeleeWeapon) {
			Buff.prolong(enemy, Cripple.class, 5);
		}

		if (hit && hero.hasTalent(Talent.CRITICAL_SHIELD) && !enemy.isAlive()) {
			Buff.affect(hero, Barrier.class).setShield(1+hero.pointsInTalent(Talent.CRITICAL_SHIELD));
		}

		if (hit && hero.hasTalent(Talent.WINNERS_FLAG) && !enemy.isAlive() && Random.Int(2) == 0) {
			Buff.affect(hero, BlessingArea.class).setup(hero.pos, 20, hero.pointsInTalent(Talent.WINNERS_FLAG));
		}

		if (hero.subClass == HeroSubClass.MEDIC && hit) {
			for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
				if (Dungeon.level.heroFOV[mob.pos]) {
					if (mob.isAlive() && mob.alignment == Alignment.ALLY) {
						int healAmt = 2+2*hero.pointsInTalent(Talent.HEAL_ENHANCE);
						healAmt = Math.min( healAmt, mob.HT - mob.HP );
						if (healAmt > 0 && mob.isAlive()) {
							mob.HP += healAmt;
							mob.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 2 );
							mob.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );
						}
					}
				}
			}
			if (hero.buff(HealingArea.class) != null) {
				Buff.affect(hero, HealingArea.class).extend(2);
			}
		}

		if (hit && hero.hasTalent(Talent.HEALING_SHIELD) && !enemy.isAlive()) {
			if (Random.Int(5) < hero.pointsInTalent(Talent.HEALING_SHIELD)) {
				Buff.affect(hero, HealingArea.class).setup(hero.pos, 10, 1, true);
			}
		}

		if (hit && hero.hasTalent(Talent.APPEASE) && Random.Int(5) == 0) {
			Buff.affect(enemy, Charm.class, Charm.DURATION/2).object = hero.id();
			enemy.sprite.centerEmitter().start( Speck.factory( Speck.HEART ), 0.2f, 3 );
		}

		if (hit && hero.hasTalent(Talent.AREA_OF_LIGHT)) {
			int duration = (hero.hasTalent(Talent.WINNERS_FLAG) ? 2 : 1);
			Buff.affect(hero, BlessingArea.class).setup(hero.pos, duration, hero.pointsInTalent(Talent.AREA_OF_LIGHT));
		}

		if (hero.subClass == HeroSubClass.SURGEON) {
			if (hero.buff(Surgery.class) != null) {
				if (hit)  {
					Buff.affect(hero, Surgery.class).hit();
				} else {
					hero.buff(Surgery.class).detach();
					if (hero.buff(SurgeryUse.class) != null) {
						hero.buff(SurgeryUse.class).detach();
					}
				}
			} else {
				if (hit) {
					Buff.affect(hero, Surgery.class).hit();
				}
			}
		}

		if (hit && hero.buff(SurgeryTracker.class) != null && !enemy.properties().contains(Property.BOSS) && !enemy.properties().contains(Property.MINIBOSS)) {
			enemy.damage( enemy.HP, this );
			enemy.sprite.emitter().burst( ShadowParticle.UP, 5 );
			hero.buff(SurgeryTracker.class).detach();
		}

		if (hit && hero.hasTalent(Talent.SCALPEL)) {
			Buff.affect(enemy, Bleeding.class).set(hero.pointsInTalent(Talent.SCALPEL));
		}

		if (hit && hero.belongings.weapon() instanceof MeleeWeapon && hero.buff(StunGun.StunningTracker.class) != null) {
			Buff.affect(enemy, Paralysis.class, 2f);
			enemy.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
			enemy.sprite.flash();
			Sample.INSTANCE.play( Assets.Sounds.LIGHTNING );
		}

		if (hit && hero.belongings.weapon() instanceof MeleeWeapon && hero.buff(StunGunAP.ShockingTracker.class) != null) {
			Buff.affect(enemy, Paralysis.class, 3f);
			enemy.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
			enemy.sprite.flash();
			ArrayList<Lightning.Arc> arcs2 = new ArrayList<>();
			ArrayList<Char> affected2 = new ArrayList<>();
			affected2.clear();
			arcs2.clear();

			Shocking.arc(hero, enemy, 2, affected2, arcs2);

			affected2.remove(enemy); //defender isn't hurt by lightning
			for (Char ch : affected2) {
				if (ch.alignment != hero.alignment) {
					ch.damage(Random.IntRange(4, 6), this);
					Buff.affect(ch, Paralysis.class, 3f);
				}
			}
			GameScene.add( Blob.seed( enemy.pos, 3, Electricity.class ) );

			hero.sprite.parent.addToFront( new Lightning( arcs2, null ) );
			Sample.INSTANCE.play( Assets.Sounds.LIGHTNING );
		}

		curAction = null;

		super.onAttackComplete();
	}
	
	@Override
	public void onMotionComplete() {
		GameScene.checkKeyHold();
	}
	
	@Override
	public void onOperateComplete() {
		
		if (curAction instanceof HeroAction.Unlock) {

			int doorCell = ((HeroAction.Unlock)curAction).dst;
			int door = Dungeon.level.map[doorCell];
			
			if (Dungeon.level.distance(pos, doorCell) <= 1) {
				boolean hasKey = true;
				if (door == Terrain.LOCKED_DOOR) {
					hasKey = Notes.remove(new IronKey(Dungeon.depth));
					if (hasKey) Level.set(doorCell, Terrain.DOOR);
				} else if (door == Terrain.CRYSTAL_DOOR) {
					hasKey = Notes.remove(new CrystalKey(Dungeon.depth));
					if (hasKey) {
						Level.set(doorCell, Terrain.EMPTY);
						Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
						CellEmitter.get( doorCell ).start( Speck.factory( Speck.DISCOVER ), 0.025f, 20 );
					}
				} else {
					hasKey = Notes.remove(new SkeletonKey(Dungeon.depth));
					if (hasKey) Level.set(doorCell, Terrain.UNLOCKED_EXIT);
				}
				
				if (hasKey) {
					GameScene.updateKeyDisplay();
					GameScene.updateMap(doorCell);
					spend(Key.TIME_TO_UNLOCK);
				}
			}
			
		} else if (curAction instanceof HeroAction.OpenChest) {
			
			Heap heap = Dungeon.level.heaps.get( ((HeroAction.OpenChest)curAction).dst );
			
			if (Dungeon.level.distance(pos, heap.pos) <= 1){
				boolean hasKey = true;
				if (heap.type == Type.SKELETON || heap.type == Type.REMAINS) {
					Sample.INSTANCE.play( Assets.Sounds.BONES );
				} else if (heap.type == Type.LOCKED_CHEST){
					hasKey = Notes.remove(new GoldenKey(Dungeon.depth));
				} else if (heap.type == Type.CRYSTAL_CHEST){
					hasKey = Notes.remove(new CrystalKey(Dungeon.depth));
				}
				
				if (hasKey) {
					GameScene.updateKeyDisplay();
					heap.open(this);
					spend(Key.TIME_TO_UNLOCK);
				}
			}
			
		}
		curAction = null;

		super.onOperateComplete();
	}

	@Override
	public boolean isImmune(Class effect) {
		if (effect == Burning.class
				&& belongings.armor() != null
				&& belongings.armor().hasGlyph(Brimstone.class, this)){
			return true;
		}
		if (effect == Weakness.class
				&& hero != null
				&& hero.hasTalent(Talent.IMMUNE_SYSTEM)) {
			return true;
		}
		if (effect == Vulnerable.class
				&& hero != null
				&& hero.hasTalent(Talent.IMMUNE_SYSTEM)) {
			return true;
		}
		if (effect == Bleeding.class
				&& hero != null
				&& hero.pointsInTalent(Talent.IMMUNE_SYSTEM) > 1) {
			return true;
		}
		if ((effect == Blizzard.class ||
				effect == ConfusionGas.class ||
				effect == CorrosiveGas.class ||
				effect == Electricity.class ||
				effect == Fire.class ||
				effect == MagicalFireRoom.EternalFire.class ||
				effect == Freezing.class ||
				effect == Inferno.class ||
				effect == ParalyticGas.class ||
				effect == Regrowth.class ||
				effect == SmokeScreen.class ||
				effect == StenchGas.class ||
				effect == StormCloud.class ||
				effect == ToxicGas.class ||
				effect == Web.class ||
				effect == Tengu.FireAbility.FireBlob.class)
				&& hero != null
				&& hero.pointsInTalent(Talent.IMMUNE_SYSTEM) > 2) {
			return true;
		}
		return super.isImmune(effect);
	}

	@Override
	public boolean isInvulnerable(Class effect) {
		return buff(AnkhInvulnerability.class) != null;
	}

	public boolean search( boolean intentional ) {
		
		if (!isAlive()) return false;
		
		boolean smthFound = false;

		boolean circular = pointsInTalent(Talent.WIDE_SEARCH) == 1;
		int distance = heroClass == HeroClass.ROGUE ? 2 : 1;
		if (hasTalent(Talent.WIDE_SEARCH)) distance++;
		
		boolean foresight = buff(Foresight.class) != null;
		boolean foresightScan = foresight && !Dungeon.level.mapped[pos];

		if (foresightScan){
			Dungeon.level.mapped[pos] = true;
		}

		if (foresight) {
			distance = Foresight.DISTANCE;
			circular = true;
		}

		Point c = Dungeon.level.cellToPoint(pos);

		TalismanOfForesight.Foresight talisman = buff( TalismanOfForesight.Foresight.class );
		boolean cursed = talisman != null && talisman.isCursed();

		int[] rounding = ShadowCaster.rounding[distance];

		int left, right;
		int curr;
		for (int y = Math.max(0, c.y - distance); y <= Math.min(Dungeon.level.height()-1, c.y + distance); y++) {
			if (!circular){
				left = c.x - distance;
			} else if (rounding[Math.abs(c.y - y)] < Math.abs(c.y - y)) {
				left = c.x - rounding[Math.abs(c.y - y)];
			} else {
				left = distance;
				while (rounding[left] < rounding[Math.abs(c.y - y)]){
					left--;
				}
				left = c.x - left;
			}
			right = Math.min(Dungeon.level.width()-1, c.x + c.x - left);
			left = Math.max(0, left);
			for (curr = left + y * Dungeon.level.width(); curr <= right + y * Dungeon.level.width(); curr++){

				if ((foresight || fieldOfView[curr]) && curr != pos) {

					if ((foresight && (!Dungeon.level.mapped[curr] || foresightScan))){
						GameScene.effectOverFog(new CheckedCell(curr, foresightScan ? pos : curr));
					} else if (intentional) {
						GameScene.effectOverFog(new CheckedCell(curr, pos));
					}

					if (foresight){
						Dungeon.level.mapped[curr] = true;
					}
					
					if (Dungeon.level.secret[curr]){
						
						Trap trap = Dungeon.level.traps.get( curr );
						float chance;

						//searches aided by foresight always succeed, even if trap isn't searchable
						if (foresight){
							chance = 1f;

						//otherwise if the trap isn't searchable, searching always fails
						} else if (trap != null && !trap.canBeSearched){
							chance = 0f;

						//intentional searches always succeed against regular traps and doors
						} else if (intentional){
							chance = 1f;
						
						//unintentional searches always fail with a cursed talisman
						} else if (cursed) {
							chance = 0f;
							
						//unintentional trap detection scales from 40% at floor 0 to 30% at floor 25
						} else if (Dungeon.level.map[curr] == Terrain.SECRET_TRAP) {
							chance = (hero.hasTalent(Talent.JUNGLE_ADVENTURE)) ? 1.25f*(0.4f - (Dungeon.depth / 250f)) : 0.4f - (Dungeon.depth / 250f);
							
						//unintentional door detection scales from 20% at floor 0 to 0% at floor 20
						} else {
							chance = (hero.hasTalent(Talent.JUNGLE_ADVENTURE)) ? 1.25f*(0.2f - (Dungeon.depth / 100f)) : 0.2f - (Dungeon.depth / 100f);;
						}

						//don't want to let the player search though hidden doors in tutorial
						if (SPDSettings.intro()){
							chance = 0;
						}
						
						if (Random.Float() < chance) {
						
							int oldValue = Dungeon.level.map[curr];
							
							GameScene.discoverTile( curr, oldValue );
							
							Dungeon.level.discover( curr );
							
							ScrollOfMagicMapping.discover( curr );
							
							if (fieldOfView[curr]) smthFound = true;
	
							if (talisman != null){
								if (oldValue == Terrain.SECRET_TRAP){
									talisman.charge(2);
								} else if (oldValue == Terrain.SECRET_DOOR){
									talisman.charge(10);
								}
							}
						}
					}
				}
			}
		}
		
		if (intentional) {
			sprite.showStatus( CharSprite.DEFAULT, Messages.get(this, "search") );
			sprite.operate( pos );
			if (!Dungeon.level.locked) {
				if (cursed) {
					GLog.n(Messages.get(this, "search_distracted"));
					Buff.affect(this, Hunger.class).affectHunger(TIME_TO_SEARCH - (2 * HUNGER_FOR_SEARCH));
				} else {
					Buff.affect(this, Hunger.class).affectHunger(TIME_TO_SEARCH - HUNGER_FOR_SEARCH);
				}
			}
			spendAndNext(TIME_TO_SEARCH);
			
		}
		
		if (smthFound) {
			GLog.w( Messages.get(this, "noticed_smth") );
			Sample.INSTANCE.play( Assets.Sounds.SECRET );
			if (Random.Int(3) < pointsInTalent(Talent.SHARP_INTUITION)) {
				Buff.affect(hero, MindVision.class, 1f);
			}
			interrupt();
		}

		if (foresight){
			GameScene.updateFog(pos, Foresight.DISTANCE+1);
		}
		
		return smthFound;
	}
	
	public void resurrect() {
		HP = HT;
		live();

		MagicalHolster holster = belongings.getItem(MagicalHolster.class);

		Buff.affect(this, LostInventory.class);
		Buff.affect(this, Invisibility.class, 3f);
		//lost inventory is dropped in interlevelscene

		//activate items that persist after lost inventory
		//FIXME this is very messy, maybe it would be better to just have one buff that
		// handled all items that recharge over time?
		for (Item i : belongings){
			if (i instanceof EquipableItem && i.isEquipped(this)){
				((EquipableItem) i).activate(this);
			} else if (i instanceof CloakOfShadows && i.keptThoughLostInvent && hasTalent(Talent.LIGHT_CLOAK)){
				((CloakOfShadows) i).activate(this);
			} else if (i instanceof Wand && i.keptThoughLostInvent){
				if (holster != null && holster.contains(i)){
					((Wand) i).charge(this, MagicalHolster.HOLSTER_SCALE_FACTOR);
				} else {
					((Wand) i).charge(this);
				}
			} else if (i instanceof MagesStaff && i.keptThoughLostInvent){
				((MagesStaff) i).applyWandChargeBuff(this);
			}
		}

		updateHT(false);
	}

	@Override
	public void next() {
		if (isAlive())
			super.next();
	}

	public static interface Doom {
		public void onDeath();
	}
}
