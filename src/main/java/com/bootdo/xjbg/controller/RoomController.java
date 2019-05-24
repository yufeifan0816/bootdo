package com.bootdo.xjbg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
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

import com.bootdo.xjbg.domain.RoomDO;
import com.bootdo.xjbg.service.RoomService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-05-24 09:34:36
 */

@Controller
@RequestMapping("/xjbg/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private DictService dictService;
    @Autowired
    private UserService userService;


    @GetMapping()
    @RequiresPermissions("xjbg:room:room")
    String Room(Model model) {
        this.addDict(model);
        return "xjbg/room/room";
    }

    @GetMapping("roomMng")
    @RequiresPermissions("xjbg:room:roomMng")
    String roomMng(Model model) {
        List<RoomDO> romms = roomService.getAll();
        Map<Integer, List<RoomDO>> map = new HashMap<Integer, List<RoomDO>>();
        for (RoomDO room : romms) {
            List<RoomDO> roomDOS = map.get(room.getFloor());
            if (roomDOS == null || roomDOS.size() == 0) {
                List<RoomDO> list = new ArrayList<>();
                list.add(room);
                map.put(room.getFloor(), list);
            }else{
                roomDOS.add(room);
            }

        }
        this.addDict(model);
        model.addAttribute("rooms",map);
        return "xjbg/room/roomMng";
    }

    public void addDict(Model model) {
        List<DictDO> roomTypes = dictService.listByType("room_type");
        List<DictDO> floors = dictService.listByType("floor");
        List<DictDO> roomStates = dictService.listByType("room_state");
        List<UserDO> users = userService.listAllUser();
        model.addAttribute("roomTypes", roomTypes);
        model.addAttribute("floors", floors);
        model.addAttribute("roomStates", roomStates);
        model.addAttribute("users", users);
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("xjbg:room:room")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<RoomDO> roomList = roomService.list(query);
        int total = roomService.count(query);
        PageUtils pageUtils = new PageUtils(roomList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("xjbg:room:add")
    String add(Model model) {
        List<DictDO> roomTypes = dictService.listByType("room_type");
        List<DictDO> floors = dictService.listByType("floor");
        List<DictDO> roomStates = dictService.listByType("room_state");
        model.addAttribute("roomTypes", roomTypes);
        model.addAttribute("floors", floors);
        model.addAttribute("roomStates", roomStates);
        return "xjbg/room/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("xjbg:room:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        List<DictDO> roomTypes = dictService.listByType("room_type");
        List<DictDO> floors = dictService.listByType("floor");
        List<DictDO> roomStates = dictService.listByType("room_state");
        model.addAttribute("roomTypes", roomTypes);
        model.addAttribute("floors", floors);
        model.addAttribute("roomStates", roomStates);
        RoomDO room = roomService.get(id);
        model.addAttribute("room", room);
        return "xjbg/room/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("xjbg:room:add")
    public R save(RoomDO room) {
        if (roomService.save(room) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("xjbg:room:edit")
    public R update(RoomDO room) {
        roomService.update(room);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("xjbg:room:remove")
    public R remove(Integer id) {
        if (roomService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("xjbg:room:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        roomService.batchRemove(ids);
        return R.ok();
    }

}
