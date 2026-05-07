package com.kryeit.kryeit.compat;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeEqualityPredicate;
import net.minecraft.server.level.ServerPlayer;

public class Permissions {
	public static boolean check(ServerPlayer player, String permission) {
		LuckPerms luckPerms = LuckPermsProvider.get();

		User user = luckPerms.getUserManager().getUser(player.getUUID());
		if (user == null) return false;

		return user.data()
				.contains(Node.builder(permission).build(), NodeEqualityPredicate.IGNORE_VALUE)
				.asBoolean();
	}
}
