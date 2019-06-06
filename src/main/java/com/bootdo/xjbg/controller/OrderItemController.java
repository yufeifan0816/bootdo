package com.bootdo.xjbg.controller;

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

import com.bootdo.xjbg.domain.OrderItemDO;
import com.bootdo.xjbg.service.OrderItemService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-05-27 10:27:48
 */
 
@Controller
@RequestMapping("/xjbg/orderItem")
public class OrderItemController {
	@Autowired
	private OrderItemService orderItemService;
	
	@GetMapping()
	@RequiresPermissions("xjbg:orderItem:orderItem")
	String OrderItem(){
	    return "xjbg/orderItem/orderItem";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xjbg:orderItem:orderItem")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderItemDO> orderItemList = orderItemService.list(query);
		int total = orderItemService.count(query);
		PageUtils pageUtils = new PageUtils(orderItemList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xjbg:orderItem:add")
	String add(){
	    return "xjbg/orderItem/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("xjbg:orderItem:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OrderItemDO orderItem = orderItemService.get(id);
		model.addAttribute("orderItem", orderItem);
	    return "xjbg/orderItem/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xjbg:orderItem:add")
	public R save( OrderItemDO orderItem){
		if(orderItemService.save(orderItem)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xjbg:orderItem:edit")
	public R update( OrderItemDO orderItem){
		orderItemService.update(orderItem);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xjbg:room:roomMng")
	public R remove( Long id){
		if(orderItemService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xjbg:orderItem:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		orderItemService.batchRemove(ids);
		return R.ok();
	}

}
