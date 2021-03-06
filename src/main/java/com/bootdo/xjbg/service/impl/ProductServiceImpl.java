package com.bootdo.xjbg.service.impl;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.xjbg.dao.ProductDao;
import com.bootdo.xjbg.domain.ProductDO;
import com.bootdo.xjbg.service.ProductService;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private BootdoConfig bootdoConfig;

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
        product.setCreateUser(ShiroUtils.getUser().getName());
        product.setCreateTime(new Date());
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

	@Override
	public String uploadPic(MultipartFile[] pic) throws Exception {
		MultipartFile multipartFile = pic[0];
        String fileName = FileUtil.renameToUUID(multipartFile.getOriginalFilename());
        FileUtil.uploadFile(multipartFile.getBytes(),bootdoConfig.getProductPath(),fileName);
		return BootdoConfig.PRODUCT_PATH+fileName;
	}

	@Override
	public void rmPic(String path) throws Exception {
		String realyPaht =  bootdoConfig.getProductPath() + path.replace(BootdoConfig.PRODUCT_PATH,"");
		File file = new File(realyPaht);
		if(file.exists()){
			file.delete();
		}

	}

}
