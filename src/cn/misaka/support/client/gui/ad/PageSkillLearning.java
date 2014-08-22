/**
 * Copyright (C) Lambda-Innovation, 2013-2014
 * This code is open-source. Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 */
package cn.misaka.support.client.gui.ad;

import java.util.Set;

import cn.liutils.api.client.gui.LIGuiPage;
import cn.liutils.api.client.gui.LIGuiPart;

/**
 * @author WeAthFolD
 *
 */
public class PageSkillLearning extends LIGuiPage {

	/**
	 * @param unlocalized_name
	 */
	public PageSkillLearning() {
		super("ad.learning", GuiAbilityDeveloper.PG_OFFSET_X, GuiAbilityDeveloper.PG_OFFSET_Y);
	}

	@Override
	public void drawPage() {
	}

	@Override
	public void addElements(Set<LIGuiPart> set) {
	}

	@Override
	public void onPartClicked(LIGuiPart part) {
		
	}

}
