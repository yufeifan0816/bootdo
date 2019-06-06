package com.bootdo.xjbg.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.xjbg.domain.RoomDO;
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

import com.bootdo.xjbg.domain.RoomPriceDO;
import com.bootdo.xjbg.service.RoomPriceService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-06-06 16:47:41
 */
 
@Controller
@RequestMapping("/xjbg/roomPrice")
public class RoomPriceController extends BaseController {
	@Autowired
	private RoomPriceService roomPriceService;

	@Autowired
	private RoomService roomService;
	
	@GetMapping()
	@RequiresPermissions("xjbg:roomPrice:roomPrice")
	String RoomPrice(){
	    return "xjbg/roomPrice/roomPrice";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xjbg:roomPrice:roomPrice")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RoomPriceDO> roomPriceList = roomPriceService.list(query);
		int total = roomPriceService.count(query);
		PageUtils pageUtils = new PageUtils(roomPriceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xjbg:roomPrice:add")
	String add(Model model){
	    this.setDeictsToModle(model,new String[]{"order_type","room_types"});
		List<RoomDO> rooms = roomService.getAll();
		model.addAttribute("rooms", rooms);
		return "xjbg/roomPrice/add";
	}

	@GetMapping("/edit/{roomId}")
	@RequiresPermissions("xjbg:roomPrice:edit")
	String edit(@PathVariable("roomId") Long roomId,Model model){
		RoomPriceDO roomPrice = roomPriceService.get(roomId);
		model.addAttribute("roomPrice", roomPrice);
	    return "xjbg/roomPrice/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xjbg:roomPrice:add")
	public R save( RoomPriceDO roomPrice){
		if(roomPriceService.save(roomPrice)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xjbg:roomPrice:edit")
	public R update( RoomPriceDO roomPrice){
		roomPriceService.update(roomPrice);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xjbg:roomPrice:remove")
	public R remove( Long roomId){
		if(roomPriceService.remove(roomId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xjbg:roomPrice:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] roomIds){
		roomPriceService.batchRemove(roomIds);
		return R.ok();
	}
	
}
