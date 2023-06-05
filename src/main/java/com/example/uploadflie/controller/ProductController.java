package com.example.uploadflie.controller;

import com.example.uploadflie.Service.IProductService;
import com.example.uploadflie.Service.ProductService;
import com.example.uploadflie.model.Product;
import com.example.uploadflie.model.ProductForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/home")
public class ProductController {
    private final IProductService productService = new ProductService();

    @GetMapping("")
    public ModelAndView index(){
        List<Product> productList = productService.finALL();
       ModelAndView modelAndView = new ModelAndView("index");
       modelAndView.addObject("productList",productList);
       return modelAndView;
    }

@GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("productForm",new ProductForm());
        return modelAndView;
    }
    @Value("${fileUpload}")
    private String fileUpload;

    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute ProductForm productForm) {
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getDescription(), fileName);
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("productForm", productForm);
        modelAndView.addObject("message", "Created new product successfully !");
        return modelAndView;
    }

}
