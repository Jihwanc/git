package com.mmventures.study.common.tiles;

import org.springframework.web.servlet.view.tiles3.SpringLocaleResolver;

import org.apache.tiles.extras.complete.CompleteAutoloadTilesContainerFactory;
import org.apache.tiles.locale.LocaleResolver;
import org.apache.tiles.request.ApplicationContext;

public class SpringCompleteAutoloadTilesContainerFactory
	extends CompleteAutoloadTilesContainerFactory {
	
    @Override
    protected LocaleResolver createLocaleResolver(
	    ApplicationContext applicationContext) {
	return new SpringLocaleResolver();
    }
}
