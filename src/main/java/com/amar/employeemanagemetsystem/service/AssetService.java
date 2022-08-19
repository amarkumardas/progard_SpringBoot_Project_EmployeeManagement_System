package com.amar.employeemanagemetsystem.service;

import com.amar.employeemanagemetsystem.models.Asset;


import java.util.List;

public interface AssetService {
    Asset saveAsset(Asset asset);

    List<Asset> getAllAsset();

    Asset getAssetById(int id);

    Asset updateAsset(Asset asset, int id);

    void deleteAsset(int id);
}
