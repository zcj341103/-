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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.ArcaneResin;
import com.shatteredpixel.shatteredpixeldungeon.items.Cartridge;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

import java.util.HashSet;

public class ObsidianShield extends MeleeWeapon {

    {
        image = ItemSpriteSheet.OBSIDIAN_SHIELD;

        tier = 6;
    }

    public static final HashSet<Class> RESISTS = new HashSet<>();

    static {
        RESISTS.addAll(AntiMagic.RESISTS);
    }

    @Override
    public int max(int lvl) {
        return 4*(tier-1) + //20 base
                lvl*(tier-1); //+5 per +1
    }

    @Override
    public int defenseFactor(Char owner) {
        return 4 + 2 * buffedLvl();    //4 extra defence, plus 2 per level;
    }

    //see Hero.damage for antimagic effects
    public static int drRoll(int level) {
        return Random.NormalIntRange(0, 4 + 2 * level); //4 extra defence, plus 2 per level;
    }

    public String statsInfo() {
        if (isIdentified()) {
            return Messages.get(this, "stats_desc", 4 + 2 * buffedLvl());
        } else {
            return Messages.get(this, "typical_stats_desc", 4);
        }
    }
}
