package org.cwitmer34.invasion.npcs;

import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.cwitmer34.invasion.enums.InvasionTier;
import org.cwitmer34.invasion.npcs.traits.AlienTrait;
import org.cwitmer34.invasion.util.AlienUtil;
import org.mcmonkey.sentinel.SentinelTrait;

@Getter
public class Alien {

  private final NPC npc;

  public Alien(InvasionTier tier, Location spawnLoc) {
    NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, AlienUtil.getName(tier));
    npc.getOrAddTrait(AlienTrait.class);
    LookClose look = npc.getOrAddTrait(LookClose.class);
    look.lookClose(true);
    look.setRange(100);
    SentinelTrait sentinel = npc.getOrAddTrait(SentinelTrait.class);
    int[] attributes = AlienUtil.getAttributes(tier);
    sentinel.setHealth(attributes[0]);
    sentinel.damage = attributes[1];
    sentinel.speed = attributes[2];
    sentinel.attackRate = 45;
    sentinel.chaseRange = 250;
    sentinel.addTarget("allinone:player");

    Equipment equipment = npc.getOrAddTrait(Equipment.class);
    ItemStack[] gear = AlienUtil.getGear(tier);
    equipment.set(Equipment.EquipmentSlot.HELMET, gear[0]);
    equipment.set(Equipment.EquipmentSlot.CHESTPLATE, gear[1]);
    equipment.set(Equipment.EquipmentSlot.LEGGINGS, gear[2]);
    equipment.set(Equipment.EquipmentSlot.BOOTS, gear[3]);
    equipment.set(Equipment.EquipmentSlot.HAND, gear[4]);

    SkinTrait npcSkin = npc.getOrAddTrait(SkinTrait.class);
    npcSkin.setSkinPersistent(
      "4683e4ed0ab5ab22a6aafffc6d88ffeaf886dcf1c4f91038047d8d9c7ad63109",
      "lC9s9EMcCr8kU1if1pWvktxd1bN3mYyabD/dXK7e7fZqf0rpVdjwv8CzW4xUt8waBXxNitN9b0qzQe5WVSaLhhoRI2jDHbWo0rO9ZQ7/wWY1hzhM31G3qbgzVMZDP3eb63yk+5HnGuDZbSKBIz3xz0GYcD6ormcba+nsguX2jEFlvLqNJcMjb6KQcMsrR26u+/FEn2xzMJYRCLd4yDHUTK870g3O9YSGXS+SIXb9z/vQY1Kl6JUA0r4QupbtJeJN6CSzqe89+moMIuHT5y8PrpKPp+ChIGZqL3G5srSFpGTS0bVzGfF+hogB971b9qAC1giNuWfOZBdHMuSBE0Xe/CuDEnOQGnLBhTwPn5QgVQXESIQBghlpFbtxSyW7IEwwphJGJQvwM/WfD0rT/DkT64cpkaUe5oUZfcBeIJ6uOcudoU/FISalDwnPzrcpHNhK9XJrK+NzxZV/Jnp516mBIhDxBvvcP22/TcFJW8sO0erioNdTnV3aOQb1cZ8JVjCASN34YDCi4kJJxTjxRtPh6FOWwhDtRYc2IPSLDAHVecMqSOp06rp/67PSGvdI6OMGGxUHAqZcI8cHgOoNJjka34XzL0iO0DCPbBi7L+7hjUiTKitd8/zj/SKr1I9XVJ5zn8Tj3wz8U0nPiMtG3NOLG0Rx6U8OeG9B68h85HwTJRE=",
      "ewogICJ0aW1lc3RhbXAiIDogMTcyMzk2NTI1MDU3MywKICAicHJvZmlsZUlkIiA6ICJiYWRkZjIxZTFmNWE0ZGYzOGVjZmNkOTYwY2E0YzA5YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJBbmRlckJUIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQ2ODNlNGVkMGFiNWFiMjJhNmFhZmZmYzZkODhmZmVhZjg4NmRjZjFjNGY5MTAzODA0N2Q4ZDljN2FkNjMxMDkiCiAgICB9CiAgfQp9"
    );

    this.npc = npc;
    npc.spawn(spawnLoc);
  }
}
