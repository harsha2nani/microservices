package com.demo.restuarant.controller;

import com.demo.restuarant.Model.RestuarantModel;
import com.demo.restuarant.service.RestuarantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class RestuarantController {

    @Autowired
    private RestuarantService restuarantService;

    @PostMapping("/addRestuarant")
    public ResponseEntity<?> addRestuarant(@RequestBody RestuarantModel restuarantModel){
      RestuarantModel model =  this.restuarantService.addRestaurant(restuarantModel);
      return ResponseEntity.status(HttpStatus.CREATED).body(restuarantModel);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        List<RestuarantModel> restuarantModelList = this.restuarantService.getAllRestuarants();
        return ResponseEntity.status(200).body(restuarantModelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
       RestuarantModel model = this.restuarantService.getRestById(id);
       return ResponseEntity.status(200).body(model);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id,@RequestBody RestuarantModel model){
       RestuarantModel res = this.restuarantService.updateRest(id,model);
       return ResponseEntity.status(200).body(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRest(@PathVariable Long id){
       RestuarantModel model = this.restuarantService.deleteRestaurant(id);
       return ResponseEntity.status(200).body(model);
    }

    @GetMapping("getProdFromRest")
    public ResponseEntity<?> webClient(){
        return ResponseEntity.status(200).body(this.restuarantService.clientService());
    }
}
