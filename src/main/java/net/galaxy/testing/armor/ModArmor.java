package net.galaxy.testing.armor;

import net.galaxy.testing.Testing;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.Function;

public class ModArmor {
    public static final int BASE_DURABILITY = 20;

    public static final TagKey<Item> RUBY_REPAIR = TagKey.of(Registries.ITEM.getKey(), Identifier.of(Testing.MOD_ID, "ruby"));

    public static final RegistryKey<EquipmentAsset> RUBY_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(Testing.MOD_ID, "ruby_armor"));

    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    EquipmentType.HELMET, 3,
                    EquipmentType.CHESTPLATE, 8,
                    EquipmentType.LEGGINGS, 6,
                    EquipmentType.BOOTS, 3
            ),
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.5F,
            0.2F,
            RUBY_REPAIR,
            RUBY_ARMOR_MATERIAL_KEY
    );



}
