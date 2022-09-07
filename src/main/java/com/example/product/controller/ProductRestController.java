package com.example.product.controller;

import com.example.product.dto.ProductAdminDTO;
import com.example.product.dto.ProductUserDTO;
import com.example.product.model.Product;
import com.example.product.service.ProductService;
import com.example.product.util.ProductAdminMap;
import com.example.product.util.ProductMap;
import com.example.product.util.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProductRestController {

    UploadFileUtils uploadFileUtils = new UploadFileUtils();
    ProductAdminMap adminMap = new ProductAdminMap();
    @Autowired
    ProductService productService;

//    FileUtils fileUtils = new FileUtils();

    @GetMapping(value = "/products")
    public ResponseEntity getAllProductsUser(@RequestParam(value = "page") int currentPage,
                                             @RequestParam(value = "size") int pageSize){
        List<Product> listProduct = new ArrayList<>();

        List<ProductUserDTO> listDTO = new ArrayList<>();
        listProduct = productService.selectAll(currentPage,pageSize);
        listDTO = ProductMap.dtoMapProduct(listProduct);

        return new ResponseEntity<>(listDTO,listDTO==null? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @GetMapping(value = "/productsAdmin")
    public ResponseEntity getAllProductsAdmin(@RequestParam(value = "page") int currentPage,
                                              @RequestParam(value = "size") int pageSize){
        List<Product> listProduct = new ArrayList<>();

        List<ProductAdminDTO> listDTO = new ArrayList<>();
        listProduct = productService.selectAll(currentPage,pageSize);
        listDTO = adminMap.dtoMapProduct(listProduct);

        return new ResponseEntity<>(listDTO,listDTO==null? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity getDetailProductUser(@PathVariable("id") Long id){
        Product prd = productService.selectByPrimaryKey(id);
        ProductUserDTO prdDTO = ProductMap.dtoMapProduct(prd);
        return new ResponseEntity<>(prdDTO,prdDTO==null? HttpStatus.BAD_REQUEST : HttpStatus.OK);

    }
    @GetMapping(value = "/productsAdmin/{id}")
    public ResponseEntity getDetailProductAdmin(@PathVariable("id") Long id){
        Product prd = productService.selectByPrimaryKey(id);
        ProductAdminDTO prdDTO = adminMap.dtoMapProduct(prd);
        return new ResponseEntity<>(prdDTO,prdDTO==null? HttpStatus.BAD_REQUEST : HttpStatus.OK);

    }

    @GetMapping(value = "/search")
    public ResponseEntity searchProductUser(@RequestParam(value = "name") String name ,
                                            @RequestParam(value = "page") int currentPage,
                                            @RequestParam(value = "size") int pageSize){
        List<Product> list  = new ArrayList<>();
        list = productService.search(name,currentPage,pageSize);

        List<ProductUserDTO> listDTO = ProductMap.dtoMapProduct(list);

        return new ResponseEntity<>(listDTO,listDTO==null? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @PostMapping(value = "/addProduct")
    public ResponseEntity addProduct(@RequestParam("files") MultipartFile[] multipartFile){
        List<String> listAddedImg = new ArrayList<>();
        try {
            for(MultipartFile file: multipartFile){
             String name =    uploadFileUtils.handleUploadFile(file);
                listAddedImg.add(name);
            }

          int id=  productService.insert(new Product("name",100.00,"desc"));
            System.out.println(id);
            return  new ResponseEntity<>(HttpStatus.OK);
        }

        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }



    }
}
