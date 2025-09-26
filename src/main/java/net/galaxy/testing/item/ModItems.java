package net.galaxy.testing.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.galaxy.testing.Testing;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    // Ruby Items
    public static final Item RUBY = register("ruby", Item::new, new Item.Settings());
    public static final Item PALE_STICK = register("pale_stick", Item::new, new Item.Settings());
    // Used for Repairing
    public static final TagKey<Item> RUBY_REPAIR = TagKey.of(Registries.ITEM.getKey(), Identifier.of(Testing.MOD_ID, "ruby"));
    // The attributes of the tools
    public static final ToolMaterial RUBY_PICKAXE_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            1753,
            8.5F,
            1.5F,
            15,
            RUBY_REPAIR
    );

    public static final ToolMaterial RUBY_SWORD_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            1753,
            1f,
            1.5f,
            15,
            RUBY_REPAIR
    );

    // Ruby Tools
    public static final Item RUBY_PICKAXE = register("ruby_pickaxe", Item::new, new Item.Settings().pickaxe(RUBY_PICKAXE_MATERIAL, 1f,0.5f));
    public static final Item RUBY_SWORD = register(
            "ruby_sword",
            Item::new,
            new Item.Settings()
                    .sword(RUBY_SWORD_MATERIAL, 5.0f, -2.4f) // attack damage & speed
    );

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Testing.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }


    public static void registerModItems() {
        Testing.LOGGER.info("Registering Mod Items for " + Testing.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RUBY);
            entries.add(PALE_STICK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(RUBY_PICKAXE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register( fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(RUBY_SWORD);
        });
    }


}
