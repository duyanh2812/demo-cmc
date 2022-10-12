package com.example.product.controller;

import com.example.common.ResponseVo;
import com.example.file.FileStorageService;
import com.example.product.dto.ProductAddRequestDTO;
import com.example.product.dto.ProductAdminDTO;
import com.example.product.dto.ProductUserDTO;
import com.example.product.model.Image;
import com.example.product.model.Product;
import com.example.product.service.ImageService;
import com.example.product.service.ProductCategoryService;
import com.example.product.service.ProductService;
import com.example.product.util.ProductAdminMap;
import com.example.product.util.ProductMap;
import com.example.product.util.UploadFileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class ProductRestController {

    UploadFileUtils uploadFileUtils = new UploadFileUtils();
    ProductAdminMap adminMap = new ProductAdminMap();

    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    ImageService imageService;
    @Autowired
    ProductService productService;
    
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping(value = "/products")
    public ResponseEntity<ResponseVo> getAllProductsUser(@RequestParam(value = "page") int currentPage,
                                             @RequestParam(value = "size") int pageSize, ProductUserDTO input){
    	List<Product> listProduct = new ArrayList<>();
        ResponseVo response = new ResponseVo("OK");
        

        List<ProductAdminDTO> listDTO = new ArrayList<>();
        listProduct = productService.selectAll(currentPage,pageSize, input);
        listDTO = adminMap.dtoMapProduct(listProduct);
        response.setVoList(listProduct);
        response.setTotal(productService.countAll(input));

        return new ResponseEntity<>(response,listDTO==null? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @GetMapping(value = "/admin/products")
    public ResponseEntity<ResponseVo> getAllProductsAdmin(@RequestParam(value = "page") int currentPage,
                                              @RequestParam(value = "size") int pageSize, ProductUserDTO input){
        List<Product> listProduct = new ArrayList<>();
        ResponseVo response = new ResponseVo("OK");
        List<ProductAdminDTO> listDTO = new ArrayList<>();
        listProduct = productService.selectAll(currentPage,pageSize, input);
        listDTO = adminMap.dtoMapProduct(listProduct);
        response.setVoList(listProduct);
        response.setTotal(productService.countAll(input));
        return new ResponseEntity<>(response,listDTO==null? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductUserDTO> getDetailProductUser(@PathVariable("id") Long id){
        Product prd = productService.selectByPrimaryKey(id);
        ProductUserDTO prdDTO = ProductMap.dtoMapProduct(prd);
        return new ResponseEntity<>(prdDTO,prdDTO==null? HttpStatus.BAD_REQUEST : HttpStatus.OK);

    }
    @GetMapping(value = "/admin/product/{id}")
    public ResponseEntity<ProductAdminDTO> getDetailProductAdmin(@PathVariable("id") Long id){
        Product prd = productService.selectByPrimaryKey(id);
        ProductAdminDTO prdDTO = adminMap.dtoMapProduct(prd);
        return new ResponseEntity<>(prdDTO,prdDTO==null? HttpStatus.BAD_REQUEST : HttpStatus.OK);

    }

    @PostMapping(value = "/admin/addProduct")
    public ResponseEntity<String> addProduct(
            @RequestParam(value = "files", required = false) MultipartFile[] multipartFile,
            String jsonFile){
        List<String> listAddedImg = new ArrayList<>();
        try {
            for(MultipartFile file: multipartFile){
             String name =    fileStorageService.storeFile(file);
                listAddedImg.add(name);
            }
            ProductAddRequestDTO productDTO = new ObjectMapper().readValue(jsonFile, ProductAddRequestDTO.class);

            int id=  productService.insert(new Product(productDTO.getDisplayName(),productDTO.getPrice(),productDTO.getDescription()));
            System.out.println("productID from mybatis builtIn: " +id);
            Product newestProduct = productService.latestCreatedProduct();
            System.out.println("productID from constructor- latestCreatedProduct Function: " + newestProduct.getId());

            // insert image for product
            for(String name: listAddedImg){
                Image newImg = new Image(newestProduct.getId(), name, null);
                imageService.insert(newImg);
            }

            for(long cateID: productDTO.getCategories()){
                productCategoryService.insert(cateID,newestProduct.getId(), null);
            }



            return  new ResponseEntity<>("Chạy Không có lỗi gì cả.",HttpStatus.OK);
        }

        catch (Exception e){
            return  new ResponseEntity<>("Something bị ốm!",HttpStatus.BAD_REQUEST);
        }



    }
}
