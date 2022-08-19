package com.amar.employeemanagemetsystem.controller;

import com.amar.employeemanagemetsystem.models.Asset;
import com.amar.employeemanagemetsystem.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/asset/")
public class AssetController {
    @Autowired
    AssetService assetService;


    @PostMapping//it should be always above THE method ie. AT METHOD LEVEL . it is used for creation
    public ResponseEntity<Asset> saveAsset(@Valid @RequestBody Asset asset){//ResponseEntity represents Http response including headers,body,status.we will get data in the form of ResponseEntity
        return new ResponseEntity<Asset>( assetService.saveAsset(asset), HttpStatus.CREATED);//here we are creating and saving data in db
    }                                                               //if it is created the status will be 201 Ok
    @GetMapping("getall")
    public List<Asset> getAllAsset(){
        return  assetService.getAllAsset();
    }

    @GetMapping("getbyid/{id}")//or @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ResponseEntity<Asset> getAssetById(@PathVariable("id") int id){
        return new ResponseEntity<Asset>(assetService.getAssetById(id),HttpStatus.OK);
    }

    @PutMapping("updatebyid/{id}")//for updation we can also use @Postmapping("{id}") which work in same manner
    public ResponseEntity<Asset> updateAsset(@Valid @PathVariable("id")int id, @RequestBody Asset asset){
        return new ResponseEntity<Asset>(assetService.updateAsset(asset,id),HttpStatus.OK);
    }
    @DeleteMapping("deletebyid/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable("id")int id){
        assetService.deleteAsset(id);
        return new ResponseEntity<String>("Customer data deleted successfully",HttpStatus.OK);
    }
}
