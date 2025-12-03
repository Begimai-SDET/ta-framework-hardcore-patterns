package com.begimai.framework.tests.regression;

import com.begimai.framework.business.model.ComputeEngineConfig;
import com.begimai.framework.business.model.EstimateResult;
import com.begimai.framework.business.service.GoogleCloudPricingService;
import com.begimai.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddComputeEngineTest extends BaseTest {

    @Test
    public void userCanAddComputeEngineToEstimate() {

        ComputeEngineConfig config = new ComputeEngineConfig(4);

        GoogleCloudPricingService service = new GoogleCloudPricingService();
        EstimateResult result = service.createComputeEngineEstimate(config);

        System.out.println("ESTIMATE RESULT = " + result);

        Assert.assertTrue(result.isComputeEngineCardPresent(),
                "Compute Engine card is not visible in estimate panel.");

        Assert.assertNotEquals(result.getTotalCost(), "--",
                "Total price is still '--', price was not calculated.");

        Assert.assertTrue(result.getTotalCost().contains("$"),
                "Total price does not contain '$'. Actual: " + result.getTotalCost());
    }
}
