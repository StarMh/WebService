package cn.wangtao34.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangtao34.user.User;
import cn.wangtao34.webservice.AxiomClient;
import cn.wangtao34.webservice.PhoneNumberUtil;
import cn.wangtao34.webservice.RPCClient;
import cn.wangtao34.webservice.WSDLClient;

/**
 * 
 * @author wangtao34
 * @time 2017年8月16日 下午2:18:19
 */
@Controller
public class WebController {

	@RequestMapping(value = "/staticPage", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:/pages/final.html";
	}
	@RequestMapping(value = "/first", method = RequestMethod.GET)
	public String first() {
		return "first";
	}
	@ResponseBody
	@RequestMapping(value = "axiomHello", produces={"text/html;charset=UTF-8;","application/json;"})
	public String axiomHello(@RequestParam("name") String name) {
		return AxiomClient.hello(name);
	}
	@ResponseBody
	@RequestMapping(value = "RPCHello", produces={"text/html;charset=UTF-8;","application/json;"})
	public String RPCHello(@RequestParam("name") String name) {
		return RPCClient.hello(name);
	}
	@ResponseBody
	@RequestMapping(value = "WSDLHello", produces={"text/html;charset=UTF-8;","application/json;"})
	public String WSDLHello(@RequestParam("name") String name) {
		return WSDLClient.hello(name);
	}
	
	@ResponseBody
	@RequestMapping(value = "phoneNumber", produces={"text/html;charset=UTF-8;","application/json;"})
	public String phoneNumber(@RequestParam("name") String number) {
		return PhoneNumberUtil.phoneNumber(number);
	}
}
