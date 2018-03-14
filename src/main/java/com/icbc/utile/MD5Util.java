package com.icbc.utile;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.icbc.config.ICBCConfig;
import com.icbc.config.ICBCSession;

public class MD5Util {
	
	public static String md5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UTF-8 is unsupported", e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MessageDigest不支持MD5Util", e);
		}
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	public static String sign(ICBCSession icbcSession, String signTime) {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("soaCertString", icbcSession.getSoaCertString());
		params.put("sysNo", icbcSession.getSysNo());
		params.put("userId", icbcSession.getUserId());
		params.put("signTime", signTime);
		return sign(params);
	}
	
	public static String sign(ICBCSession icbcSession) throws Exception {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("soaCertString", icbcSession.getSoaCertString());
		params.put("sysNo", icbcSession.getSysNo());
		params.put("userId", icbcSession.getUserId());
		return sign(params);
	}

	/**
	 * md5签名
	 * 
	 * 按参数名称升序，将参数值进行连接 签名
	 * 加时间戳
	 * @param appSecret
	 * @param params
	 * @return
	 */
	public static String sign(TreeMap<String, String> params) {
		StringBuilder paramValues = new StringBuilder();
		
		ICBCConfig icbcConfig = new ICBCConfig();
		
		params.put("appSecret", icbcConfig.getEncryptKey());

		for (Map.Entry<String, String> entry : params.entrySet()) {
			paramValues.append(entry.getValue());
		}
		System.out.println(md5(paramValues.toString()));
		return md5(paramValues.toString());
	}

	/**
	 * 请求参数签名验证
	 * 
	 * @param appSecret
	 * @param request
	 * @return true 验证通过 false 验证失败
	 * @throws Exception
	 */
	public static boolean verifySign(String signStr, ICBCSession icbcSession) throws Exception {
		TreeMap<String, String> params = new TreeMap<String, String>();

		if (StringUtils.isBlank(signStr)) {
			throw new RuntimeException("There is no signature field in the request parameter!");
		}

		params.put("soaCertString", icbcSession.getSoaCertString());
		params.put("sysNo", icbcSession.getSysNo());
		params.put("userId", icbcSession.getUserId());

		if (!sign(params).equals(signStr)) {
			return false;
		}
		return true;
	}

	public static boolean verifySign(HttpServletRequest request) throws UnsupportedEncodingException {
		TreeMap<String, String> params = new TreeMap<String, String>();

//      // 渠道编号  
//      String channelCode = request.getParameter("channelCode");  

		String signStr = request.getParameter("signStr");
		String signTime = request.getParameter("signTime");

		String soaCertString = request.getParameter("soaCertString");
		String sysNo = request.getParameter("sysNo");
		String userId = request.getParameter("userId");
		
		if (StringUtils.isBlank(signStr) || StringUtils.isBlank(signTime)) {
	          return false;  
		}

		params.put("soaCertString", soaCertString);
		params.put("sysNo", sysNo);
		params.put("userId", userId);
		params.put("signTime", signTime);

		if (!sign(params).equals(signStr)) {
			return false;
		}
		return true;
	}
}