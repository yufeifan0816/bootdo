package com.bootdo.xjbg.service;

import com.bootdo.xjbg.domain.ProductDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-05-27 14:45:21
 */
public interface ProductService {
	
	ProductDO get(Long id);
	
	List<ProductDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductDO product);
	
	int update(ProductDO product);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	/**
	 * @Author yufeifan@wondersgroup.com
	 * @Description //文件上传
	 * @Date 10:57 2019/5/28
	 * @Param [pic]
	 * @return void
	 **/
    String  uploadPic(MultipartFile[] pic) throws Exception;

	void  rmPic(String path) throws Exception;
}
