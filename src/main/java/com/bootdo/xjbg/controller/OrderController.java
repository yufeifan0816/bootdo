package com.bootdo.xjbg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import com.bootdo.xjbg.domain.OrderItemDO;
import com.bootdo.xjbg.domain.ProductDO;
import com.bootdo.xjbg.domain.RoomDO;
import com.bootdo.xjbg.service.ProductService;
import com.bootdo.xjbg.service.RoomService;
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
public class OrderController extends BaseController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private DictService dictService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
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
	/**
	 * 开房
	 */
	@PostMapping("/kaifang")
	@ResponseBody
	public R kaifang(@RequestBody OrderDO order) {
		R r = new R();
		try {
			orderService.addOrder(order);
		} catch (Exception e) {
			r.put("code",500);
			r.put("msg","服务器出错,请开管理员排除");
			e.printStackTrace();
		}
		return new R();
	}
	/**进入开房界面*/
	@GetMapping("/operation/{roomId}")
	@RequiresPermissions("xjbg:room:room")
	String operation(@PathVariable("roomId") Integer id, Model model) {
		setDeictsToModle(model,new String[]{"order_type","room_type"});
		List<ProductDO> products = productService.list(new HashMap<>());
		List<DictDO> orderType = dictService.listByType("order_type");
		model.addAttribute("orderTypes", orderType);
		List<DictDO> roomTypes = dictService.listByType("room_type");
		RoomDO room = roomService.get(id);
		model.addAttribute("room", room);
		model.addAttribute("products",products);
		return "xjbg/order/operation";
	}

	/**修改开房信息*/
	@GetMapping("/modification/{roomId}")
	@RequiresPermissions("xjbg:room:room")
	String modification(@PathVariable("roomId") Integer id, Model model) {
		setDeictsToModle(model,new String[]{"order_type","room_type"});
		RoomDO room = roomService.get(id);
		OrderDO orderDO = orderService.findByRoomId(id);
		List<ProductDO> products = productService.list(new HashMap<>());
		model.addAttribute("order", orderDO);
		model.addAttribute("room", room);
		model.addAttribute("products",products);
		return "xjbg/order/modification";
	}

}