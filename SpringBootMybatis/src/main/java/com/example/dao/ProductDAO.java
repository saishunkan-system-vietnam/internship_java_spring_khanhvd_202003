package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.daoimp.ProductImp;
import com.example.entities.Product;
import com.example.mapper.ProductMapper;

@Repository
public class ProductDAO implements ProductImp {
	@Autowired
	ProductMapper productMapper;

	@Override
	public List<Product> getAllListProduct() {
		// TODO Auto-generated method stub
		List<Product> products = productMapper.getAllProducts();
		return products;
	}
}
