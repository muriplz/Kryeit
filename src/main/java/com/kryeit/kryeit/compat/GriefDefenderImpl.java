package com.kryeit.kryeit.compat;

import java.util.UUID;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.User;

public class GriefDefenderImpl {

    public static int getClaimBlocks(UUID playerID) {
        User user = GriefDefender.getCore().getUser(playerID);
        return user == null ? -1 : user.getPlayerData().getInitialClaimBlocks() + user.getPlayerData().getAccruedClaimBlocks() + user.getPlayerData().getBonusClaimBlocks();
    }

}
