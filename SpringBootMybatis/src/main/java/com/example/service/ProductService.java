package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ProductDAO;
import com.example.daoimp.ProductImp;
import com.example.entities.Product;

@Service
public class ProductService implements ProductImp {
	
	@Autowired
	ProductDAO productDAO;
	@Override
	public List<Product> getAllListProduct() {
		// TODO Auto-generated method stub
		
		return productDAO.getAllListProduct();
	}
	
}
