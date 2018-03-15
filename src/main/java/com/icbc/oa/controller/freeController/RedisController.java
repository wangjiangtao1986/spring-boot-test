package com.icbc.oa.controller.freeController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.icbc.config.ICBCSession;
import com.icbc.oa.service.RedisService;
import com.icbc.oa.service.RedisSessionService;
import com.icbc.utile.MD5Util;

@RestController
public class RedisController {

	@Autowired
	RedisSessionService redisSessionService;
	
	@Autowired
	RedisService redisService;
	

    @GetMapping("/index")
    public String index(HttpSession httpSession) {
        httpSession.setAttribute("user", "helloword");
        return httpSession.getId();
    }

    @GetMapping("/helloword")
    public String hello(HttpSession httpSession) {
        return httpSession.getId() + httpSession.getAttribute("user");
    }
	
	@PutMapping("/createSession")
	public JSONObject createSession(ICBCSession icbcSession) throws Exception {
		String signTime = System.currentTimeMillis()+"";
		String signStr = MD5Util.sign(icbcSession,signTime);
		
		redisSessionService.addObject(signStr,icbcSession);
		JSONObject jo = new JSONObject();
		jo.put("signStr", signStr);
		jo.put("signTime", signTime);
		return jo;
	}
	@GetMapping("/getSession")
	JSONObject getSession(ICBCSession icbcSession) throws Exception {
		return redisSessionService.getSession(MD5Util.sign(icbcSession));
	}
	@GetMapping("/setSessionExpire")
	Boolean setSessionExpire(ICBCSession icbcSession) throws Exception {
		return redisSessionService.setSessionExpire(MD5Util.sign(icbcSession));
	}
	@GetMapping("/removeSession")
	JSONObject removeSession(ICBCSession icbcSession) throws Exception {
		return redisSessionService.removeSession(MD5Util.sign(icbcSession));
	}
}