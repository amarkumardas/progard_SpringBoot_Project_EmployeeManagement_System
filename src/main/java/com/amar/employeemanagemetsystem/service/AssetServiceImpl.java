package com.amar.employeemanagemetsystem.service;

import com.amar.employeemanagemetsystem.models.Asset;
import com.amar.employeemanagemetsystem.repository.AssetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssetServiceImpl implements AssetService{
   @Autowired
    AssetRepo assetRepo;

    public AssetServiceImpl(AssetRepo assetRepo) {
        this.assetRepo = assetRepo;
    }

    @Override
    public Asset saveAsset(Asset asset) {

        return assetRepo.save(asset);
    }

    @Override
    public List<Asset> getAllAsset() {

        return assetRepo.findAll();//to get all data
    }

    @Override
    public Asset getAssetById(int id) {

        return assetRepo.findById(id).orElseThrow();
    }

    @Override
    public Asset updateAsset(Asset asset, int id) {
        Asset existingAsset=assetRepo.findById(id).orElseThrow();
        existingAsset.setNoOfComputers(asset.getNoOfComputers());
        assetRepo.save(existingAsset);
        return existingAsset;
    }

    @Override
    public void deleteAsset(int id) {
       assetRepo.findById(id).orElseThrow();
       assetRepo.deleteById(id);
    }
}
