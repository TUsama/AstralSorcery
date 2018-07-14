/*******************************************************************************
 * HellFirePvP / Astral Sorcery 2018
 *
 * All rights reserved.
 * The source code is available on github: https://github.com/HellFirePvP/AstralSorcery
 * For further details, see the License file there.
 ******************************************************************************/

package hellfirepvp.astralsorcery.common.constellation.perk.tree.root;

import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.astralsorcery.common.constellation.perk.AbstractPerk;
import hellfirepvp.astralsorcery.common.constellation.perk.tree.PerkTree;
import hellfirepvp.astralsorcery.common.constellation.perk.tree.PerkTreeOffset;
import hellfirepvp.astralsorcery.common.constellation.perk.tree.PerkTreePoint;
import hellfirepvp.astralsorcery.common.data.research.PlayerProgress;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: RootPerk
 * Created by HellFirePvP
 * Date: 08.07.2018 / 10:25
 */
public class RootPerk extends AbstractPerk {

    private final IConstellation constellation;

    public RootPerk(String name, IConstellation constellation, int x, int y) {
        super(name, x, y);
        this.constellation = constellation;
    }

    public IConstellation getConstellation() {
        return constellation;
    }

    @Override
    public PerkTreePoint getPoint() {
        return new PerkTreeOffset(this, getOffset(), constellation);
    }

    @Override
    public void applyPerkLogic(EntityPlayer player, Side side) {}

    @Override
    public void removePerkLogic(EntityPlayer player, Side side) {}

    @Override
    public boolean mayUnlockPerk(PlayerProgress progress) {
        if (progress.hasFreeAlignmentLevel()) {
            AbstractPerk core = PerkTree.PERK_TREE.getAstralSorceryPerk("core");
            if (core != null && progress.hasPerkUnlocked(core)) {
                return true;
            }
        }

        return super.mayUnlockPerk(progress);
    }
}
