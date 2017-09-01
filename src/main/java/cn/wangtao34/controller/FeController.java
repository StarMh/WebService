package cn.wangtao34.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangtao34.webservice.RPCClient;

/**
 * 
 * @author wangtao34
 * @time 2017年8月17日 下午3:58:23
 */
@Controller
public class FeController {
	@RequestMapping(value = "/fe", method = RequestMethod.GET)
	public String fe() {
		return "fe";
	}

	@ResponseBody
	@RequestMapping(value = "getFe", produces = { "text/html;charset=UTF-8;", "application/json;" })
	public String getFe(@RequestParam("name") String name) {
		Integer number = Integer.valueOf(name);
		if (number <= 30 && number > 0) {
			Integer[] results = RPCClient.fe(number);
			return Arrays.toString(results);
		} else {
			return "输入数据不合法";
		}
	}
}
