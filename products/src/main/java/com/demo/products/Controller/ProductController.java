package com.demo.products.Controller;

import com.demo.products.DAO.ProductDAO;
import com.demo.products.DTO.ProdResDTO;
import com.demo.products.DTO.ProductDTO;
import com.demo.products.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService prodService;


    /**
     *
     * @return List of Products
     */
    @Operation(summary = "It will fetch all the Products/Items")
    @ApiResponse(responseCode = "200",description = "Records Returned Successfully!!!!")
    @GetMapping("/getAll")
    public ResponseEntity<?> prods(){
        List<ProdResDTO> productDTOList = prodService.getAllItems();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(productDTOList);
    }

    /**
     *
     * @param productDTO it accepts Product DTO Object as Request
     * @param image it accepts file as a Request
     * @return Status of product saved in database or not
     * @throws IOException it throws IO Exception while saving byte stream to DB
     */
    @Operation(summary = "Add Items to the Products List")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Record Inserted Successfully"),
                    @ApiResponse(responseCode = "400", description = "Record Not Inserted")
            }
    )
    @PostMapping("/add")
    public ResponseEntity<String> addItem(@ModelAttribute ProductDTO productDTO, @RequestParam("image")MultipartFile image) throws IOException {
       ProductDAO prod = prodService.addItem(productDTO,image);
       if(prod!=null){
           return new ResponseEntity<>("Product saved to DB Successfully!!!",HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Something went Wrong",HttpStatus.BAD_REQUEST);
       }
    }

    /**
     *
     * @param id It Accepts id to fetch Unique Record from the Product Table
     * @param productDTO It Accepts Product DTO Object
     * @param image It Accepts Multi part file
     * @return Status of the Updated Record
     * @throws IOException It Handles IO Exception while storing byte Array to DB
     */
    @Operation(summary = "Update the Item to latest")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Item/Prod Updated Successfully"),
            @ApiResponse(responseCode = "400",description="Record not Updated to DB")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") int id,@ModelAttribute ProductDTO productDTO,@RequestParam("image") MultipartFile image) throws IOException {
       ProductDAO prodDao = prodService.updateProduct(id,productDTO,image);
       if(prodDao!=null){
           return new ResponseEntity<>("Record Updated SuccessFully",HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Record not found with provided id",HttpStatus.BAD_REQUEST);
       }
    }

    /**
     *
     * @param id It Accepts Id as a unique Identifier
     * @return The Status of the Deleted Record
     */

    @Operation(summary = "Used for deleting the record from DB")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Item deleted Successfully!!"),
            @ApiResponse(responseCode = "204",description = "Record Not Found with the provided Id")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable("id") int id){
     ProductDAO productDAO =  prodService.deleteRecord(id);
     if(productDAO!=null)
         return ResponseEntity.status(HttpStatus.OK).body("Record Deleted Successfully!!!");
     else
         return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Record found in database");
    }

    @GetMapping("/admin")
    public String admin()
    {
      return "Welcome from admin";
    }
}
