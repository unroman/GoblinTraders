package com.mrcrayfish.goblintraders;

import com.mrcrayfish.configured.api.simple.BoolProperty;
import com.mrcrayfish.configured.api.simple.IntProperty;
import com.mrcrayfish.configured.api.simple.SimpleConfig;
import com.mrcrayfish.configured.api.simple.SimpleProperty;

/**
 * Author: MrCrayfish
 */
public final class Config
{
    @SimpleConfig(id = Reference.MOD_ID, name = "entities")
    public static final Entities ENTITIES = new Entities();

    public static class Entities
    {
        @SimpleProperty(name = "goblinTrader")
        public final Goblin goblinTrader;

        @SimpleProperty(name = "veinGoblinTrader")
        public final Goblin veinGoblinTrader;

        @SimpleProperty(name = "preventDespawnIfNamed", comment = "If true, prevents the trader from despawning if named")
        public final BoolProperty preventDespawnIfNamed = BoolProperty.create(true);

        private Entities()
        {
            this.goblinTrader = new Goblin(25, 24000, -64, 50);
            this.veinGoblinTrader = new Goblin(25, 24000, 0, 128);
        }

        public static class Goblin
        {
            @SimpleProperty(name = "traderSpawnChance", comment = "The chance out of one hundred that the trader will spawn in the over world")
            public final IntProperty traderSpawnChance;

            //TODO look into issue where high delay will still be present even if this value is lowered
            @SimpleProperty(name = "traderSpawnDelay", comment = "The amount of ticks before the trader will spawn again")
            public final IntProperty traderSpawnDelay;

            @SimpleProperty(name = "traderMinSpawnLevel", comment = "The minimum level the trader can spawn", worldRestart = true)
            public final IntProperty traderMinSpawnLevel;

            @SimpleProperty(name = "traderMaxSpawnLevel", comment = "The maximum level the trader can spawn", worldRestart = true)
            public final IntProperty traderMaxSpawnLevel;

            @SimpleProperty(name = "restockDelay", comment = "The amount of ticks before the trader will replenish it's trades. Set to -1 to disable restocking")
            public final IntProperty restockDelay;

            @SimpleProperty(name = "canAttackBack", comment = "If true, the goblin will try to hit back a player or mob that hit them")
            public final BoolProperty canAttackBack;

            @SimpleProperty(name = "gruntNoiseInterval", comment = "Goblins will make a grunt noise while walking around. If you find it happening too often, you can increase the interval. Value is represented in ticks.")
            public final IntProperty gruntNoiseInterval;

            private Goblin(int spawnChance, int spawnDelay, int minLevel, int maxLevel)
            {
                this.traderSpawnChance = IntProperty.create(spawnChance, 1, 100);
                this.traderSpawnDelay = IntProperty.create(spawnDelay, 0, Integer.MAX_VALUE);
                this.traderMinSpawnLevel = IntProperty.create(minLevel, -64, 320);
                this.traderMaxSpawnLevel = IntProperty.create(maxLevel, -64, 320);
                this.restockDelay = IntProperty.create(48000, -1, Integer.MAX_VALUE);
                this.canAttackBack = BoolProperty.create(true);
                this.gruntNoiseInterval = IntProperty.create(80, 1, 1000);
            }
        }
    }
}
