package com.begimai.framework.business.model;

public class EstimateResult {

    private final boolean computeEngineCardPresent;
    private final String totalCost;

    public EstimateResult(boolean computeEngineCardPresent, String totalCost) {
        this.computeEngineCardPresent = computeEngineCardPresent;
        this.totalCost = totalCost;
    }

    public boolean isComputeEngineCardPresent() {
        return computeEngineCardPresent;
    }

    public String getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "EstimateResult{" +
                "computeEngineCardPresent=" + computeEngineCardPresent +
                ", totalCost='" + totalCost + '\'' +
                '}';
    }
}
