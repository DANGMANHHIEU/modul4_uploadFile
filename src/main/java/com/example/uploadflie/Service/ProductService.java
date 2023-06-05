package com.example.uploadflie.Service;

import com.example.uploadflie.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    private List<Product> products = new ArrayList<>();
    @Override
    public List<Product> finALL() {
        return products;
    }

    @Override
    public Product finById(int id) {
        int x=-1;
        for (Product p : products) {
            if (p.getId() == id) {
                x=products.indexOf(p);
                break;
            }
        }
        if(x !=-1){
            return products.get(x);
        }
        return null;
    }
    @Override
    public void save(Product product) {
            products.add(product);
    }

    @Override
    public void remove(int id) {
        int x=-1;
        for (Product p : products) {
            if (p.getId() == id) {
                x=products.indexOf(p);
                break;
            }
        }
        if(x !=-1){
             products.remove(x);
        }
    }

    @Override
    public void update(int id, Product product) {
        int x=-1;
        for (Product p : products) {
            if (p.getId() == id) {
                x=products.indexOf(p);
                break;
            }
        }
        if(x !=-1){
            products.set(x,product);
        }
        }
    }



