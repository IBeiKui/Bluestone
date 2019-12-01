package io.github.techdweebgaming.bluestone.blocks;

import io.github.techdweebgaming.bluestone.Bluestone;
import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Bluestone.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Bluestone.MODID)
public class BluestoneBlocks {

    private static List<Block> blocks = new ArrayList<>();

    public static final Block bluestoneEmitter = register(new BluestoneEmitter(getProperties(Material.ROCK, SoundType.GLASS)), "bluestone_emitter");
    public static final Block bluestoneReceiver = register(new BluestoneReceiver(getProperties(Material.ROCK, SoundType.GLASS)), "bluestone_receiver");
    public static final Block bluestoneLever = register(new BluestoneLever(getProperties(Material.MISCELLANEOUS, SoundType.WOOD, 2.0F, 4.0F, true)), "bluestone_lever");
    public static final Block bluestoneLamp = register(new BluestoneLamp(getProperties(Material.REDSTONE_LIGHT, SoundType.GLASS)), "bluestone_lamp");
    public static final Block bluestoneObserver = register(new BluestoneObserver(getProperties(Material.ROCK, SoundType.GLASS)), "bluestone_observer");
    public static final Block bluestoneTNT = register(new BluestoneTNT(getProperties(Material.TNT, SoundType.PLANT)), "bluestone_tnt");
    public static final Block bluestoneDispenser = register(new BluestoneDispenser(getProperties(Material.ROCK, SoundType.STONE)), "bluestone_dispenser");
    public static final Block bluestoneDropper = register(new BluestoneDropper(getProperties(Material.ROCK, SoundType.STONE)), "bluestone_dropper");
    public static final Block bluestoneStoneButton = register(new BluestoneButton(false, getProperties(Material.MISCELLANEOUS, SoundType.STONE, 2.0F, 4.0F, true)), "bluestone_button_stone");
    public static final Block bluestoneWoodenButton = register(new BluestoneButton(true, getProperties(Material.MISCELLANEOUS, SoundType.WOOD, 2.0F, 4.0F, true)), "bluestone_button_wood");
    public static final Block bluestonePiston = register(new BluestonePiston(false, getProperties(Material.PISTON, SoundType.STONE)), "bluestone_piston");
    public static final Block bluestonePistonSticky = register(new BluestonePiston(true, getProperties(Material.PISTON, SoundType.STONE)), "bluestone_piston_sticky");
    public static final Block bluestoneStonePressurePlate = register(new BluestonePressurePlate(PressurePlateBlock.Sensitivity.MOBS, getProperties(Material.ROCK, SoundType.STONE, 2.0F, 4.0F, true)), "bluestone_pressure_plate_stone");
    public static final Block bluestoneWoodenPressurePlate = register(new BluestonePressurePlate(PressurePlateBlock.Sensitivity.EVERYTHING, getProperties(Material.WOOD, SoundType.WOOD, 2.0F, 4.0F, true)), "bluestone_pressure_plate_wood");

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(blocks.toArray(new Block[blocks.size()]));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for(Block block : blocks) {
            event.getRegistry().register(new BlockItem(block, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName(block.getRegistryName()));
        }
    }

    private static Block.Properties getProperties(Material material, SoundType sound, float hardness, float resistance, boolean doesNotBlockMovement) {
        Block.Properties properties = Block.Properties.create(material).sound(sound).hardnessAndResistance(2.0F, 4.0F);
        if(doesNotBlockMovement) properties = properties.doesNotBlockMovement();
        return properties;
    }

    private static Block.Properties getProperties(Material material, SoundType sound) {
        return getProperties(material, sound, 2.0F, 4.0F, false);
    }

    private static Block register(Block block, String registryName) {
        block.setRegistryName(Bluestone.MODID, registryName);
        blocks.add(block);
        return block;
    }

}
