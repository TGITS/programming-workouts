package com.github.tgits.asset;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AssetUtilTest {

    @Test
    public void testTotalAssetValues() {
        final List<Asset> assets = Arrays.asList(
                new Asset(AssetType.BOND, 1000),
                new Asset(AssetType.BOND, 2000),
                new Asset(AssetType.STOCK, 3000),
                new Asset(AssetType.STOCK, 4000)
        );

        Assert.assertEquals("Should be equals to 10 000", 10_000, AssetUtil.totalAssetValues(assets, asset -> true));
    }

    @Test
    public void testTotalBondAssetValues() {
        final List<Asset> assets = Arrays.asList(
                new Asset(AssetType.BOND, 5000),
                new Asset(AssetType.BOND, 2000),
                new Asset(AssetType.STOCK, 3000),
                new Asset(AssetType.STOCK, 4000),
                new Asset(AssetType.BOND, 4000)
        );

        Assert.assertEquals("Should be equals to 11 000", 11_000, AssetUtil.totalAssetValues(assets, asset -> asset.getType() == AssetType.BOND));
    }

    @Test
    public void testTotalStockAssetValues() {
        final List<Asset> assets = Arrays.asList(
                new Asset(AssetType.BOND, 5000),
                new Asset(AssetType.BOND, 2000),
                new Asset(AssetType.STOCK, 3000),
                new Asset(AssetType.STOCK, 4000),
                new Asset(AssetType.BOND, 4000)
        );

        Assert.assertEquals("Should be equals to 7 000", 7_000, AssetUtil.totalAssetValues(assets, asset -> asset.getType() == AssetType.STOCK));
    }
}
