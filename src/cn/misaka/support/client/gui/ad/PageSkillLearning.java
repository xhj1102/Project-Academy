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

import cn.liutils.api.client.gui.LIGuiButton;
import cn.liutils.api.client.gui.LIGuiPart;
import cn.misaka.support.client.gui.ad.GuiAbilityDeveloper.Page;

/**
 * @author WeAthFolD
 *
 */
public class PageSkillLearning extends Page {

	/**
	 * @param unlocalized_name
	 */
	public PageSkillLearning(GuiAbilityDeveloper dev) {
		dev.super("ad.skilllearning");
	}

	/* (non-Javadoc)
	 * @see cn.misaka.support.client.gui.ad.GuiAbilityDeveloper.Page#renderPageArea()
	 */
	@Override
	public void renderPageArea() {
	}

	/* (non-Javadoc)
	 * @see cn.misaka.support.client.gui.ad.GuiAbilityDeveloper.Page#addElements(java.util.Set)
	 */
	@Override
	public void addElements(Set<LIGuiPart> set) {
	
	}

	/* (non-Javadoc)
	 * @see cn.misaka.support.client.gui.ad.GuiAbilityDeveloper.Page#onButtonClicked(cn.liutils.api.client.gui.LIGuiButton)
	 */
	@Override
	public void onButtonClicked(LIGuiButton button) {
	}

}
