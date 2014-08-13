package cn.misaka.ability.api.control.modifier;

import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.api.data.PlayerDataClient;
import cn.misaka.ability.system.control.preset.ControlPreset;
import net.minecraft.entity.player.EntityPlayer;

public interface IPresetModifier {
	void applyModification(EntityPlayer player, PlayerData data, ControlPreset preset);
}
