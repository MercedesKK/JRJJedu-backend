package com.company.project.utils;

import java.util.UUID;

/**
 * 令牌生成器
 */
public class TokenUtil {
	/**
	 * 获取用户种子
	 */
	public static String getSeed(String username) {
		username = username == null ? "" : username;
		String seed = Md5Utils.getMd5(username + "seed");
		return seed;
	}

	/**
	 * 获取登录令牌
	 */
	public static String getToken() {
		UUID uuid = UUID.randomUUID();
		return Md5Utils.getToken(uuid.toString());
	}

	public static String getNoSplitUniqueId() {

		return UUID.randomUUID().toString().replace("-", "");

	}

	public static String getUniqueId() {

		return UUID.randomUUID().toString();

	}

}
