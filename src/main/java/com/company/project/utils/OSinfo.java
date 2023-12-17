package com.company.project.utils;

public class OSinfo {

	private static String OS = System.getProperty("os.name").toLowerCase();

	private static OSinfo _instance = new OSinfo();

	private EPlatform platform;

	private OSinfo() {
	}

	public static boolean isLinux() {
		return OS.indexOf("linux") >= 0;
	}

	public static boolean isMacOS() {
		return OS.indexOf("mac") >= 0 && OS.indexOf("os") >= 0;
	}

	public static boolean isMacOSX() {
		return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
	}

	public static boolean isWindows() {
		return OS.indexOf("windows") >= 0 || OS.indexOf("Windows") >= 0;
	}

	/**
	 *
	 * @return
	 */
	public static EPlatform getOSname() {
		if (isLinux()) {
			_instance.platform = EPlatform.Linux;
		} else if (isMacOS()) {
			_instance.platform = EPlatform.Mac_OS;
		} else if (isMacOSX()) {
			_instance.platform = EPlatform.Mac_OS_X;
		} else if (isWindows()) {
			_instance.platform = EPlatform.Windows;
		}
		return _instance.platform;
	}

}
