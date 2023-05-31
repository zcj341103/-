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

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.gunner.FirstAidKit;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.gunner.ReinforcedArmor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.gunner.Riot;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.NaturesPower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpectralBlades;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpiritHawk;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.knight.HolyShield;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.knight.StimPack;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.knight.UnstableAnkh;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.ElementalBlast;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WarpBeacon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.nurse.AngelWing;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.nurse.GammaRayEmmit;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.nurse.HealareaGenerator;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.planter.Root;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.planter.Sprout;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.planter.TreasureMap;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.DeathMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.SmokeBomb;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.samurai.Abil_Kunai;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.samurai.Awake;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.samurai.ShadowBlade;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Endure;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.HeroicLeap;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Shockwave;
import com.shatteredpixel.shatteredpixeldungeon.items.AmmoBelt;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.GammaRayGun;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.HandMirror;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KnightsShield;
import com.shatteredpixel.shatteredpixeldungeon.items.Teleporter;
import com.shatteredpixel.shatteredpixeldungeon.items.TengusMask;
import com.shatteredpixel.shatteredpixeldungeon.items.Waterskin;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.PlateArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.SandalsOfNature;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfExperience;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfParalyticGas;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfPurity;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfReload;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfWealth;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.APBullet;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.AdvancedEvolution;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Evolution;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.GunSmithingTool;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.HPBullet;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.HandyBarricade;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAugmentation;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AntimaterRifle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AntimaterRifleAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AssassinsBlade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.CrudePistol;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Dagger;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.FlameThrower;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.FlameThrowerAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.FlameThrowerHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Gloves;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.HealBook;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.HeavyMachinegun;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.KSG;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.LargeSword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Magnum;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RocketLauncher;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Saber;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.ShotGun;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.ShotGunAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.SniperRifle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.TacticalShield;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.TestWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WornKatana;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WornShortsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.FishingSpear;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingKnife;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingStone;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.HealingDart;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.watabou.utils.DeviceCompat;

public enum HeroClass {

	WARRIOR	( HeroSubClass.BERSERKER, HeroSubClass.GLADIATOR, HeroSubClass.VETERAN ),
	MAGE	( HeroSubClass.BATTLEMAGE, HeroSubClass.WARLOCK, HeroSubClass.ENGINEER ),
	ROGUE	( HeroSubClass.ASSASSIN, HeroSubClass.FREERUNNER, HeroSubClass.CHASER ),
	HUNTRESS( HeroSubClass.SNIPER, HeroSubClass.WARDEN, HeroSubClass.FIGHTER ),
	GUNNER	( HeroSubClass.LAUNCHER , HeroSubClass.RANGER , HeroSubClass.RIFLEMAN ),
	SAMURAI	( HeroSubClass.SLASHER , HeroSubClass.MASTER , HeroSubClass.SLAYER ),
	PLANTER	( HeroSubClass.TREASUREHUNTER, HeroSubClass.ADVENTURER, HeroSubClass.RESEARCHER),
	KNIGHT	( HeroSubClass.WEAPONMASTER, HeroSubClass.FORTRESS, HeroSubClass.CRUSADER),
	NURSE	( HeroSubClass.MEDIC, HeroSubClass.ANGEL, HeroSubClass.SURGEON );

	private HeroSubClass[] subClasses;

	HeroClass( HeroSubClass...subClasses ) {
		this.subClasses = subClasses;
	}

	public void initHero( Hero hero ) {

		hero.heroClass = this;
		Talent.initClassTalents(hero);

		Item i = new ClothArmor().identify();
		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ClothArmor)i;

		i = new Food();
		if (!Challenges.isItemBlocked(i)) i.collect();

		new VelvetPouch().collect();
		Dungeon.LimitedDrops.VELVET_POUCH.drop();

		Waterskin waterskin = new Waterskin();
		waterskin.collect();

		new ScrollOfIdentify().identify();

		//TODO: to delete after testing
		/*
		.identify()
		.quantity()
		.upgrade()
		.collect();
		 */
		//new AdvancedEvolution().quantity(100).collect();
		//new AssassinsBlade().identify().collect();
		//new StoneOfAugmentation().quantity(100).collect();
		//new FishingSpear().quantity(100).collect();
		//new PotionOfLiquidFlame().identify().quantity(20).collect();
		//new PotionOfFrost().identify().quantity(20).collect();
		//new RapidGrowth().quantity(20).collect();
		//new APBullet().quantity(2).collect();
		//new HPBullet().quantity(2).collect();
		//new Evolution().quantity(50).collect();
		//new HandyBarricade().quantity(20).collect();
		//new TengusMask().collect();
		//new KingsCrown().collect();
		//new PotionOfExperience().identify().quantity(29).collect();
		//new ScrollOfMagicMapping().identify().quantity(29).collect();
		//new PlateArmor().identify().upgrade(100).collect();
		//new ScrollOfUpgrade().identify().quantity(1000).collect();
		//new ScrollOfExtract().quantity(20).collect();
		//new TestWeapon().identify().collect();
		//new Teleporter().collect();
		//new PotionOfStrength().identify().quantity(20).collect();
		//new PotionOfMindVision().identify().quantity(20).collect();
		//new Dart().quantity(500).collect();
		//new ScrollOfTransmutation().identify().quantity(1000).collect();
		//new DriedRose().identify().upgrade(10).collect();
		//new Carbine().identify().collect();
		//new ScrollOfEnchantment().identify().quantity(1000).collect();
		//new Evolution().quantity(100).collect();
		//new AdvancedEvolution().quantity(20).collect();
		//new AlchemistsToolkit().upgrade(10).identify().collect();
		//new RingOfRush().identify().upgrade(50).collect();
		//new ThrowingStone().identify().quantity(100).collect();
		//new Recycle().quantity(600).collect();
		//for testing

		switch (this) {
			case WARRIOR:
				initWarrior( hero );
				break;

			case MAGE:
				initMage( hero );
				break;

			case ROGUE:
				initRogue( hero );
				break;

			case HUNTRESS:
				initHuntress( hero );
				break;

			case GUNNER:
				initGunner( hero );
				break;

			case SAMURAI:
				initSamurai( hero );
				break;

			case PLANTER:
				initPlanter( hero );
				break;

			case KNIGHT:
				initKnight( hero );
				break;

			case NURSE:
				initNurse( hero );
				break;
		}

		if (!PixelScene.landscape()) {
			for (int s = 0; s < 4; s++){
				if (Dungeon.quickslot.getItem(s) == null){
					Dungeon.quickslot.setSlot(s, waterskin);
					break;
				}
			}
		} else {
			for (int s = 0; s < 8; s++){
				if (Dungeon.quickslot.getItem(s) == null){
					Dungeon.quickslot.setSlot(s, waterskin);
					break;
				}
			}
		}
	}

	public Badges.Badge masteryBadge() {
		switch (this) {
			case WARRIOR:
				return Badges.Badge.MASTERY_WARRIOR;
			case MAGE:
				return Badges.Badge.MASTERY_MAGE;
			case ROGUE:
				return Badges.Badge.MASTERY_ROGUE;
			case HUNTRESS:
				return Badges.Badge.MASTERY_HUNTRESS;
			case GUNNER:
				return Badges.Badge.MASTERY_GUNNER;
			case SAMURAI:
				return Badges.Badge.MASTERY_SAMURAI;
			case PLANTER:
				return Badges.Badge.MASTERY_PLANTER;
			case KNIGHT:
				return Badges.Badge.MASTERY_KNIGHT;
			case NURSE:
				return Badges.Badge.MASTERY_NURSE;

		}
		return null;
	}

	private static void initWarrior( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();
		ThrowingStone stones = new ThrowingStone();
		stones.quantity(3).collect();
		Dungeon.quickslot.setSlot(0, stones);

		if (hero.belongings.armor != null) {
			hero.belongings.armor.affixSeal(new BrokenSeal());
		}

		if (Dungeon.isChallenged(Challenges.GAMBLER)) {
			RingOfWealth wealth = new RingOfWealth();
			(hero.belongings.ring = wealth).identify();
			hero.belongings.ring.activate( hero );
		}

		new PotionOfHealing().identify();
		new ScrollOfRage().identify();
	}

	private static void initMage( Hero hero ) {
		MagesStaff staff;

		staff = new MagesStaff(new WandOfMagicMissile());

		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);

		if (Dungeon.isChallenged(Challenges.GAMBLER)) {
			RingOfWealth wealth = new RingOfWealth();
			(hero.belongings.ring = wealth).identify();
			hero.belongings.ring.activate( hero );
		}

		Dungeon.quickslot.setSlot(0, staff);

		new ScrollOfUpgrade().identify();
		new PotionOfLiquidFlame().identify();
	}

	private static void initRogue( Hero hero ) {
		(hero.belongings.weapon = new Dagger()).identify();

		CloakOfShadows cloak = new CloakOfShadows();
		(hero.belongings.artifact = cloak).identify();
		hero.belongings.artifact.activate( hero );

		ThrowingKnife knives = new ThrowingKnife();
		knives.quantity(3).collect();

		if (Dungeon.isChallenged(Challenges.GAMBLER)) {
			RingOfWealth wealth = new RingOfWealth();
			(hero.belongings.ring = wealth).identify();
			hero.belongings.ring.activate( hero );
		}

		Dungeon.quickslot.setSlot(0, cloak);
		Dungeon.quickslot.setSlot(1, knives);

		new ScrollOfMagicMapping().identify();
		new PotionOfInvisibility().identify();
	}

	private static void initHuntress( Hero hero ) {

		(hero.belongings.weapon = new Gloves()).identify();
		SpiritBow bow = new SpiritBow();
		bow.identify().collect();

		if (Dungeon.isChallenged(Challenges.GAMBLER)) {
			RingOfWealth wealth = new RingOfWealth();
			(hero.belongings.ring = wealth).identify();
			hero.belongings.ring.activate( hero );
		}

		Dungeon.quickslot.setSlot(0, bow);

		new PotionOfMindVision().identify();
		new ScrollOfLullaby().identify();
	}

	private static void initGunner( Hero hero ) {
		CrudePistol crude = new CrudePistol();
		(hero.belongings.weapon = crude).identify();
		RingOfReload reload = new RingOfReload();
		reload.start = true;
		(hero.belongings.ring = reload).identify().upgrade(3);
		hero.belongings.ring.activate( hero );
		AmmoBelt ammoBelt = new AmmoBelt();
		ammoBelt.collect();

		if (Dungeon.isChallenged(Challenges.GAMBLER)) {
			RingOfWealth wealth = new RingOfWealth();
			(hero.belongings.misc = wealth).identify();
			hero.belongings.misc.activate( hero );
		}

		Dungeon.quickslot.setSlot(0, crude);
		Dungeon.quickslot.setSlot(1, ammoBelt);

		new PotionOfHaste().identify();
		new ScrollOfTeleportation().identify();
	}

	private static void initSamurai( Hero hero ) {

		hero.STR = 8;

		WornKatana wornKatana = new WornKatana();
		(hero.belongings.weapon = wornKatana).identify();

		RingOfMight might = new RingOfMight();
		might.start = true;
		(hero.belongings.ring = might).identify().upgrade(1);
		hero.belongings.ring.activate( hero );

		ThrowingKnife knives = new ThrowingKnife();
		knives.quantity(3).collect();

		if (Dungeon.isChallenged(Challenges.GAMBLER)) {
			RingOfWealth wealth = new RingOfWealth();
			(hero.belongings.misc = wealth).identify();
			hero.belongings.misc.activate( hero );
		}

		Dungeon.quickslot.setSlot(0, knives);

		new ScrollOfRetribution().identify();
		new PotionOfStrength().identify();
	}

	private static void initPlanter( Hero hero ) {
		Shovel shovel = new Shovel();
		(hero.belongings.weapon = shovel).identify();
		hero.belongings.weapon.activate(hero);
		ThrowingStone stones = new ThrowingStone();
		stones.quantity(3).collect();
		Dungeon.quickslot.setSlot(0, stones);

		SandalsOfNature sandals = new SandalsOfNature();
		sandals.start = true;
		Generator.Category cat = Generator.Category.ARTIFACT;
		cat.probs[7]--; //removes SandalsOfNature in pool
		(hero.belongings.artifact = sandals).identify();
		hero.belongings.artifact.activate(hero);

		if (Dungeon.isChallenged(Challenges.GAMBLER)) {
			RingOfWealth wealth = new RingOfWealth();
			(hero.belongings.ring = wealth).identify();
			hero.belongings.ring.activate(hero);
		}

		Dungeon.quickslot.setSlot(0, shovel);
		Dungeon.quickslot.setSlot(1, stones);

		new ScrollOfMirrorImage().identify();
		new PotionOfPurity().identify();

		//new Teleporter().collect();
		//new MinersTool().identify().collect();
		//new RingOfMight().identify().upgrade(10).collect();
		//new PlateArmor().identify().upgrade(100).collect();
	}

	private static void initKnight( Hero hero ) {
		Saber saber = new Saber();
		(hero.belongings.weapon = saber).identify();
		hero.belongings.weapon.activate(hero);
		KnightsShield shield = new KnightsShield();
		shield.collect();
		Dungeon.quickslot.setSlot(0, shield);
		ThrowingStone stones = new ThrowingStone();
		stones.quantity(3).collect();
		Dungeon.quickslot.setSlot(1, stones);

		if (Dungeon.isChallenged(Challenges.GAMBLER)) {
			RingOfWealth wealth = new RingOfWealth();
			(hero.belongings.ring = wealth).identify();
			hero.belongings.ring.activate( hero );
		}

		new ScrollOfRemoveCurse().identify();
		new PotionOfParalyticGas().identify();
	}

	private static void initNurse( Hero hero ) {
		HealBook healBook = new HealBook();
		(hero.belongings.weapon = healBook).identify();
		hero.belongings.weapon.activate(hero);

		GammaRayGun gammaRayGun = new GammaRayGun();
		gammaRayGun.collect();
		Dungeon.quickslot.setSlot(0, gammaRayGun);

		HandMirror handMirror = new HandMirror();
		handMirror.collect();
		Dungeon.quickslot.setSlot(1, handMirror);

		HealingDart healingDart = new HealingDart();
		healingDart.quantity(2).collect();
		Dungeon.quickslot.setSlot(2, healingDart);

		if (Dungeon.isChallenged(Challenges.GAMBLER)) {
			RingOfWealth wealth = new RingOfWealth();
			(hero.belongings.ring = wealth).identify();
			hero.belongings.ring.activate( hero );
		}

		new ScrollOfMirrorImage().identify();
		new PotionOfHealing().identify();

		//new TengusMask().collect();
		//new PlateArmor().identify().upgrade(100).collect();
		//new PotionOfExperience().identify().quantity(30).collect();
		//new TestWeapon().identify().collect();
		//new WandOfTransfusion().identify().collect();
		//new ScrollOfSirensSong().identify().quantity(50).collect();
	}

	public String title() {
		return Messages.get(HeroClass.class, name());
	}

	public String desc(){
		return Messages.get(HeroClass.class, name()+"_desc");
	}

	public String shortDesc(){
		return Messages.get(HeroClass.class, name()+"_desc_short");
	}

	public HeroSubClass[] subClasses() {
		return subClasses;
	}

	public ArmorAbility[] armorAbilities(){
		switch (this) {
			case WARRIOR: default:
				return new ArmorAbility[]{new HeroicLeap(), new Shockwave(), new Endure()};
			case MAGE:
				return new ArmorAbility[]{new ElementalBlast(), new WildMagic(), new WarpBeacon()};
			case ROGUE:
				return new ArmorAbility[]{new SmokeBomb(), new DeathMark(), new ShadowClone()};
			case HUNTRESS:
				return new ArmorAbility[]{new SpectralBlades(), new NaturesPower(), new SpiritHawk()};
			case GUNNER:
				return new ArmorAbility[]{new Riot(), new ReinforcedArmor(), new FirstAidKit()};
			case SAMURAI:
				return new ArmorAbility[]{new Awake(), new ShadowBlade(), new Abil_Kunai()};
			case PLANTER:
				return new ArmorAbility[]{new Sprout(), new TreasureMap(), new Root()};
			case KNIGHT:
				return new ArmorAbility[]{new HolyShield(), new StimPack(), new UnstableAnkh()};
			case NURSE:
				return new ArmorAbility[]{new HealareaGenerator(), new AngelWing(), new GammaRayEmmit()};
		}
	}

	public String spritesheet() {
		switch (this) {
			case WARRIOR: default:
				return Assets.Sprites.WARRIOR;
			case MAGE:
				return Assets.Sprites.MAGE;
			case ROGUE:
				return Assets.Sprites.ROGUE;
			case HUNTRESS:
				return Assets.Sprites.HUNTRESS;
			case GUNNER:
				return Assets.Sprites.GUNNER;
			case SAMURAI:
				return Assets.Sprites.SAMURAI;
			case PLANTER:
				return Assets.Sprites.PLANTER;
			case KNIGHT:
				return Assets.Sprites.KNIGHT;
			case NURSE:
				return Assets.Sprites.NURSE;
		}
	}

	public String splashArt(){
		switch (this) {
			case WARRIOR: default:
				return Assets.Splashes.WARRIOR;
			case MAGE:
				return Assets.Splashes.MAGE;
			case ROGUE:
				return Assets.Splashes.ROGUE;
			case HUNTRESS:
				return Assets.Splashes.HUNTRESS;
			case GUNNER:
				return Assets.Splashes.GUNNER;
			case SAMURAI:
				return Assets.Splashes.SAMURAI;
			case PLANTER:
				return Assets.Splashes.PLANTER;
			case KNIGHT:
				return Assets.Splashes.KNIGHT;
			case NURSE:
				return Assets.Splashes.NURSE;
		}
	}

	public boolean isUnlocked(){
		//always unlock on debug builds
		if (DeviceCompat.isDebug()) return true;
		
		switch (this){
			case WARRIOR: default:
				return true;
			case MAGE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_MAGE);
			case ROGUE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_ROGUE);
			case HUNTRESS:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_HUNTRESS);
			case GUNNER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_GUNNER);
			case SAMURAI:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_SAMURAI);
			case PLANTER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_PLANTER);
			case KNIGHT:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_KNIGHT);
			case NURSE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_NURSE);
		}
	}
	
	public String unlockMsg() {
		return shortDesc() + "\n\n" + Messages.get(HeroClass.class, name()+"_unlock");
	}

}
