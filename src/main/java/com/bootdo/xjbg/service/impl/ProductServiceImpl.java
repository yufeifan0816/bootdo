package com.bootdo.xjbg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.xjbg.dao.ProductDao;
import com.bootdo.xjbg.domain.ProductDO;
import com.bootdo.xjbg.service.ProductService;



@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Override
	public ProductDO get(Long id){
		return productDao.get(id);
	}
	
	@Override
	public List<ProductDO> list(Map<String, Object> map){
		return productDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productDao.count(map);
	}
	
	@Override
	public int save(ProductDO product){
		return productDao.save(product);
	}
	
	@Override
	public int update(ProductDO product){
		return productDao.update(product);
	}
	
	@Override
	public int remove(Long id){
		return productDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return productDao.batchRemove(ids);
	}
	
}
