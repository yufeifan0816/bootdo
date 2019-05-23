package com.bootdo.weixin.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.weixin.domain.WeixinUserDO;
import com.bootdo.weixin.service.WeixinUserService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author yff
 * @email 1992lcg@163.com
 * @date 2019-05-22 17:51:52
 */
 
@Controller
@RequestMapping("/weixin/weixinUser")
public class WeixinUserController {
	String prefix = "weixin/weixinUser";
	@Autowired
	private WeixinUserService weixinUserService;
	
	@GetMapping()
	@RequiresPermissions("weixin:weixinUser:weixinUser")
	String WeixinUser(){
	    return prefix+"/weixinUser";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("weixin:weixinUser:weixinUser")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WeixinUserDO> weixinUserList = weixinUserService.list(query);
		int total = weixinUserService.count(query);
		PageUtils pageUtils = new PageUtils(weixinUserList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("weixin:weixinUser:add")
	String add(){
	    return prefix+"/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("weixin:weixinUser:edit")
	String edit(@PathVariable("id") Long id,Model model){
		WeixinUserDO weixinUser = weixinUserService.get(id);
		model.addAttribute("weixinUser", weixinUser);
	    return prefix+"/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("weixin:weixinUser:add")
	public R save( WeixinUserDO weixinUser){
		if(weixinUserService.save(weixinUser)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("weixin:weixinUser:edit")
	public R update( WeixinUserDO weixinUser){
		weixinUserService.update(weixinUser);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("weixin:weixinUser:remove")
	public R remove( Long id){
		if(weixinUserService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("weixin:weixinUser:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		weixinUserService.batchRemove(ids);
		return R.ok();
	}
	
}
