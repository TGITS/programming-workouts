package com.github.tgits.asset;

public class Asset {
    private final AssetType type;
    private final int value;

    public Asset(final AssetType type, final int value){
        this.type = type;
        this.value = value;
    }

    public AssetType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
