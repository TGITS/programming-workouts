package com.github.tgits.asset;

import java.util.List;
import java.util.function.Predicate;

public class AssetUtil {

    public static int totalAssetValues(final List<Asset> assets, final Predicate<Asset> assetSelector) {
        return assets.stream().filter(assetSelector).mapToInt(Asset::getValue).sum();
    }
}
