package cn.wangtao34.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangtao34.webservice.PicUtil;

/**
 * 
 * @author wangtao34
 * @time 2017年8月17日 下午3:58:23
 */
@Controller
public class ImageController {
	
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public String image() {
		return "image";
	}

	@ResponseBody
	@RequestMapping(value = "getImage", produces = { "text/html;charset=UTF-8;", "application/json;" })
	public String getImage() {
		String name = PicUtil.pic();
		return name;
	}
}
