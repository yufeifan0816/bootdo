package com.bootdo.common.controller;

import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.system.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class BaseController {
	@Autowired
	private DictService dictService;
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}

	public void setDeictToModle(Model modle,String type){
		modle.addAttribute(StringUtils.camelCaseName(type),dictService.listByType(type));
	}
	public void setDeictsToModle(Model modle,String[] types){
		for (String type : types) {
			modle.addAttribute(StringUtils.camelCaseName(type),dictService.listByType(type));
		}
	}

}