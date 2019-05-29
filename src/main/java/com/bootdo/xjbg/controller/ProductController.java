package com.bootdo.xjbg.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.xjbg.domain.ProductDO;
import com.bootdo.xjbg.service.ProductService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-05-27 14:45:21
 */
 
@Controller
@RequestMapping("/xjbg/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping()
	@RequiresPermissions("xjbg:product:product")
	String Product(){
	    return "xjbg/product/product";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xjbg:product:product")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductDO> productList = productService.list(query);
		int total = productService.count(query);
		PageUtils pageUtils = new PageUtils(productList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xjbg:product:add")
	String add(){
	    return "xjbg/product/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("xjbg:product:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ProductDO product = productService.get(id);
		model.addAttribute("product", product);
	    return "xjbg/product/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xjbg:product:add")
	public R save(ProductDO product, MultipartFile[] pic){
        R r = new R();
        try {
            String path = productService.uploadPic(pic);
            product.setProductPic(path);
            productService.save(product);
        } catch (Exception e) {
            r.put("code",100);
            r.put("msg","文件上传出错");
            e.printStackTrace();
        }
        return  r;
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xjbg:product:edit")
	public R update( ProductDO product){
		productService.update(product);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xjbg:product:remove")
	public R remove( Long id){
		if(productService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xjbg:product:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		productService.batchRemove(ids);
		return R.ok();
	}
	
}
