package com.demo.restuarant.service;

import com.demo.restuarant.DTO.ProductDTO;
import com.demo.restuarant.Model.RestuarantModel;
import com.demo.restuarant.Repository.RestuarantRepo;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestuarantService {

    private final WebClient webClient;

    @Autowired
    public RestuarantService(WebClient.Builder builder){
        this.webClient = builder.baseUrl("http://localhost:8080/PRODUCT-SERVICE-DEV").build();
    }

    @Autowired
    private RestuarantRepo restuarantRepo;

    public RestuarantModel addRestaurant(RestuarantModel restuarantModel){
     return this.restuarantRepo.save(restuarantModel);
    }

    public List<RestuarantModel> getAllRestuarants(){
        return restuarantRepo.findAll();
    }

    public RestuarantModel getRestById(Long id){
        return this.restuarantRepo.findById(id).orElseThrow(()-> new RuntimeException("Record Not Found with this id : "+id));
    }

    public RestuarantModel updateRest(Long id,RestuarantModel restuarantModel){
      RestuarantModel restuarantModel1 =  this.restuarantRepo.findById(id).get();
      if(restuarantModel1!=null){
          restuarantModel1.setRestuarantName(restuarantModel.getRestuarantName());
          restuarantModel1.setAddress(restuarantModel.getAddress());
          restuarantModel1.setRating(restuarantModel.getRating());
          restuarantModel1.setPhoneNumber(restuarantModel.getPhoneNumber());
          restuarantRepo.save(restuarantModel1);
          return restuarantModel1;
      }else{
          return null;
      }
    }

    public RestuarantModel deleteRestaurant(Long id){
       RestuarantModel model = this.restuarantRepo.findById(id).get();
       if(model!=null) {
           this.restuarantRepo.deleteById(id);
           return model;
       }else{
           throw new RuntimeException("Record not found for deletion");
       }

    }

    public List<ProductDTO> clientService() {
        List<ProductDTO> productDTOList = new ArrayList<>();

        Mono<List<ProductDTO>> res = webClient.get()
                .uri("/product/getAll")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductDTO>>(){});
        return res.block();

    }
}
