package cn.wangtao34.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.wangtao34.user.User;
import cn.wangtao34.webservice.RPCClient;

/**
 * 
 * @author wangtao34
 * @time 2017年8月17日 下午1:25:36
 */
@Controller
public class UserController {
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView user() {
		return new ModelAndView("userRegister", "command", new User());
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute User user, ModelMap model) {
		User newUser = RPCClient.userInfo(user);
		model.addAttribute("name", newUser.getName());
		model.addAttribute("gender", newUser.getGender());
		model.addAttribute("age", newUser.getAge());

		return "showUser";
	}
}
