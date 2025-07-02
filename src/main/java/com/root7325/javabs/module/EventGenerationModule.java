package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import com.root7325.javabs.laser.logic.event.strategy.*;

import java.util.List;
import java.util.Set;

/**
 * @author root7325 on 02.07.2025
 */
public class EventGenerationModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<IEventGenerationStrategy> strategyBinder =
                Multibinder.newSetBinder(binder(), IEventGenerationStrategy.class);

        strategyBinder.addBinding().to(BattleRoyaleGenerationStrategy.class);
        strategyBinder.addBinding().to(BattleRoyaleTeamGenerationStrategy.class);
        strategyBinder.addBinding().to(CoinRushGenerationStrategy.class);
        strategyBinder.addBinding().to(DailyGenerationStrategy.class);
    }
}
