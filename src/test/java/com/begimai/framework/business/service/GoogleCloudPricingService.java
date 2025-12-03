package com.begimai.framework.business.service;

import com.begimai.framework.business.model.ComputeEngineConfig;
import com.begimai.framework.business.model.EstimateResult;
import com.begimai.framework.pages.ComputeEnginePage;
import com.begimai.framework.pages.EstimatePanelPage;
import com.begimai.framework.pages.GoogleCloudMainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoogleCloudPricingService {

    private static final Logger log = LogManager.getLogger(GoogleCloudPricingService.class);

    public EstimateResult createComputeEngineEstimate(ComputeEngineConfig config) {

        log.info("Create Compute Engine estimate for config: {}", config);

        ComputeEnginePage computePage = new GoogleCloudMainPage()
                .open()
                .clickAddToEstimate()
                .chooseComputeEngine();

        computePage.setInstances(String.valueOf(config.getInstances()));

        EstimatePanelPage estimate = new EstimatePanelPage();

        boolean cardPresent = estimate.isComputeEngineCardPresent();
        String total = estimate.getTotalCost();

        log.info("Estimate result: cardPresent={}, total={}", cardPresent, total);

        return new EstimateResult(cardPresent, total);
    }
}
