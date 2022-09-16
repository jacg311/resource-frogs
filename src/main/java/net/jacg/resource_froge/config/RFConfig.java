package net.jacg.resource_froge.config;

public class RFConfig {
    //
    public int maxEnclosureHeight = 32;
    public int maxEnclosureWidth = 32;
    public int maxEnclosureDepth = 32;

    public RFConfig(int maxHeight, int maxWidth, int maxDepth) {
        this.maxEnclosureHeight = maxHeight;
        this.maxEnclosureWidth = maxWidth;
        this.maxEnclosureDepth = maxDepth;
    }

    public RFConfig() {
    }
}
