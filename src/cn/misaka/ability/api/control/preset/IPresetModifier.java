package cn.misaka.ability.api.control.preset;

import cn.misaka.ability.api.data.PlayerData;
import net.minecraft.entity.player.EntityPlayer;

public interface IPresetModifier {
	void applyModification(EntityPlayer player, PlayerData data, ControlPreset preset);
}
