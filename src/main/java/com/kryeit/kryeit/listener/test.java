package com.kryeit.kryeit.listener;

import com.kryeit.MinecraftServerSupplier;
import com.kryeit.event.*;
import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;

public class test implements SchematicannonPlaceEvent {


    @Override
    public boolean onCannonPlace(BlockEntity cannon, BlockPos target) {
        System.out.println(Component.nullToEmpty("lel"));
        return true;
    }
}
