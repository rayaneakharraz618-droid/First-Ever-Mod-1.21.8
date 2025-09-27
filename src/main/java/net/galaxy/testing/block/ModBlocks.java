package net.galaxy.testing.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.galaxy.testing.Testing;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {

    public static final Block RUBY_ORE = register(
            "ruby_ore",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE).requiresTool().hardness(1.5f),
            true
    );

    public static final Block CRYPTONITE_ORE = register(
            "cryptonite_ore",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE).requiresTool().hardness(1f),
            true
    );

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Testing.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Testing.MOD_ID, name));
    }

    public static void registerModItems() {
        Testing.LOGGER.info("Registering Mod Blocks for " + Testing.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(RUBY_ORE);
            entries.add(CRYPTONITE_ORE);
        });

    }


}
