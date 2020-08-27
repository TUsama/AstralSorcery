/*******************************************************************************
 * HellFirePvP / Astral Sorcery 2020
 *
 * All rights reserved.
 * The source code is available on github: https://github.com/HellFirePvP/AstralSorcery
 * For further details, see the License file there.
 ******************************************************************************/

package hellfirepvp.astralsorcery.common.util.world;

import hellfirepvp.astralsorcery.common.network.PacketChannel;
import hellfirepvp.astralsorcery.common.network.play.client.PktRequestSeed;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: WorldSeedCache
 * Created by HellFirePvP
 * Date: 02.06.2019 / 14:21
 */
public class WorldSeedCache {

    private static int activeSession = 0;

    private static Map<ResourceLocation, Long> cacheSeedLookup = new HashMap<>();

    @OnlyIn(Dist.CLIENT)
    public static void clearClient() {
        activeSession++;
        cacheSeedLookup.clear();
    }

    @OnlyIn(Dist.CLIENT)
    public static void updateSeedCache(ResourceLocation dim, int session, long seed) {
        if (activeSession == session) {
            cacheSeedLookup.put(dim, seed);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static Optional<Long> getSeedIfPresent(RegistryKey<World> dim) {
        if (dim == null) return Optional.empty();
        return getSeedIfPresent(dim.func_240901_a_());
    }

    @OnlyIn(Dist.CLIENT)
    public static Optional<Long> getSeedIfPresent(ResourceLocation dim) {
        if (!cacheSeedLookup.containsKey(dim)) {
            activeSession++;
            PktRequestSeed req = new PktRequestSeed(activeSession, dim);
            PacketChannel.CHANNEL.sendToServer(req);
            return Optional.empty();
        }
        return Optional.of(cacheSeedLookup.get(dim));
    }

}
