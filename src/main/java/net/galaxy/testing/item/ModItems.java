package net.galaxy.testing.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.galaxy.testing.Testing;
import net.galaxy.testing.armor.ModArmor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    // Items
    public static final Item RUBY = register("ruby", Item::new, new Item.Settings());
    public static final Item CRYPTONITE = register("cryptonite", Item::new, new Item.Settings());
    public static final Item CRYPTONITE_PART = register("cryptonite_part", Item::new, new Item.Settings());
    public static final Item PALE_STICK = register("pale_stick", Item::new, new Item.Settings());
    public static final Item RUBY_STICK = register("ruby_stick", Item::new, new Item.Settings());

    // Used for Repairing
    public static final TagKey<Item> RUBY_REPAIR = TagKey.of(Registries.ITEM.getKey(), Identifier.of(Testing.MOD_ID, "ruby"));
    public static final TagKey<Item> CRYPTONITE_REPAIR = TagKey.of(Registries.ITEM.getKey(), Identifier.of(Testing.MOD_ID, "cryptonite_part"));

    // The attributes of the tools
    public static final ToolMaterial RUBY_TOOL_MATERIAL = new ToolMaterial(
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

    public static final ToolMaterial CRYPTONITE_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2537,
            12f,
            3f,
            15,
            CRYPTONITE_REPAIR
    );

    // Ruby Tools
    public static final Item RUBY_PICKAXE = register("ruby_pickaxe", Item::new, new Item.Settings().pickaxe(RUBY_TOOL_MATERIAL, 1f,0.5f));
    public static final Item RUBY_SWORD = register(
            "ruby_sword",
            Item::new,
            new Item.Settings()
                    .sword(RUBY_SWORD_MATERIAL, 7.0f, -2.4f) // attack damage & speed
    );
    public static final Item RUBY_SHOVEL = register("ruby_shovel", Item::new, new Item.Settings().shovel(RUBY_TOOL_MATERIAL, 1f, 0.5f));
    public static final Item RUBY_AXE = register("ruby_axe", Item::new, new Item.Settings().axe(RUBY_TOOL_MATERIAL, 5.5f, -3f));

    // Ruby Armor
    public static final Item RUBY_HELMET = register("ruby_helmet", Item::new, new Item.Settings().armor(ModArmor.INSTANCE, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(ModArmor.BASE_DURABILITY)));
    public static final Item RUBY_CHESTPLATE = register("ruby_chestplate", Item::new, new Item.Settings().armor(ModArmor.INSTANCE, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(ModArmor.BASE_DURABILITY)));
    public static final Item RUBY_LEGGINGS = register("ruby_leggings", Item::new, new Item.Settings().armor(ModArmor.INSTANCE, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(ModArmor.BASE_DURABILITY)));
    public static final Item RUBY_BOOTS = register("ruby_boots", Item::new, new Item.Settings().armor(ModArmor.INSTANCE, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(ModArmor.BASE_DURABILITY)));

    // Cryptonite Tools
    public static final Item CRYPTONITE_PICKAXE = register("cryptonite_pickaxe", Item::new, new Item.Settings().pickaxe(CRYPTONITE_TOOL_MATERIAL, 2f, 0.5f));


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
            entries.add(CRYPTONITE);
            entries.add(CRYPTONITE_PART);
            entries.add(PALE_STICK);
            entries.add(RUBY_STICK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(RUBY_PICKAXE);
            entries.add(CRYPTONITE_PICKAXE);
            entries.add(RUBY_SHOVEL);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register( entries -> {
            entries.add(RUBY_SWORD);
            entries.add(RUBY_AXE);
            entries.add(RUBY_HELMET);
            entries.add(RUBY_CHESTPLATE);
            entries.add(RUBY_LEGGINGS);
            entries.add(RUBY_BOOTS);
        });
    }


}
