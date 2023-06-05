package com.example.uploadflie.Service;

import com.example.uploadflie.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> finALL();
    Product finById(int id);
    void save (Product products);
    void remove(int id);
    void update(int id,Product products);


}
