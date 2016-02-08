package com.mmventures.study.common.tiles;

import org.apache.tiles.extras.complete.CompleteAutoloadTilesInitializer;
import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.request.ApplicationContext;

public class SpringCompleteAutoloadTilesInitializer
	extends CompleteAutoloadTilesInitializer {
    
    @Override
    protected AbstractTilesContainerFactory createContainerFactory(
	    ApplicationContext context) {
	return new SpringCompleteAutoloadTilesContainerFactory();
    }
}
