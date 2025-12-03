package com.begimai.framework.business.model;

public class ComputeEngineConfig {

    private final int instances;

    public ComputeEngineConfig(int instances) {
        this.instances = instances;
    }

    public int getInstances() {
        return instances;
    }

    @Override
    public String toString() {
        return "ComputeEngineConfig{" +
                "instances=" + instances +
                '}';
    }
}
