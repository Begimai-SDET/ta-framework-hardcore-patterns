package com.begimai.framework.business.service;

import com.begimai.framework.business.model.ComputeEngineConfig;
import com.begimai.framework.business.model.EstimateResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingPricingServiceDecorator implements PricingService {

    private static final Logger log = LogManager.getLogger(LoggingPricingServiceDecorator.class);

    private final PricingService delegate;

    public LoggingPricingServiceDecorator(PricingService delegate) {
        this.delegate = delegate;
    }

    @Override
    public EstimateResult createComputeEngineEstimate(ComputeEngineConfig config) {
        log.info("[Decorator] Start createComputeEngineEstimate, config={}", config);
        EstimateResult result = delegate.createComputeEngineEstimate(config);
        log.info("[Decorator] Finished createComputeEngineEstimate, result={}", result);
        return result;
    }
}
