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

import com.bootdo.xjbg.domain.OrderDO;
import com.bootdo.xjbg.service.OrderService;
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
@RequestMapping("/xjbg/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping()
	@RequiresPermissions("xjbg:order:order")
	String Order(){
	    return "xjbg/order/order";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xjbg:order:order")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderDO> orderList = orderService.list(query);
		int total = orderService.count(query);
		PageUtils pageUtils = new PageUtils(orderList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xjbg:order:add")
	String add(){
	    return "xjbg/order/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("xjbg:order:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OrderDO order = orderService.get(id);
		model.addAttribute("order", order);
	    return "xjbg/order/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xjbg:order:add")
	public R save( OrderDO order){
		if(orderService.save(order)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xjbg:order:edit")
	public R update( OrderDO order){
		orderService.update(order);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xjbg:order:remove")
	public R remove( Long id){
		if(orderService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xjbg:order:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		orderService.batchRemove(ids);
		return R.ok();
	}
	
}
