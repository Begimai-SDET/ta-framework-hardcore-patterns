package com.begimai.framework.business.service;

import com.begimai.framework.business.model.ComputeEngineConfig;
import com.begimai.framework.business.model.EstimateResult;

public interface PricingService {

    EstimateResult createComputeEngineEstimate(ComputeEngineConfig config);
}
