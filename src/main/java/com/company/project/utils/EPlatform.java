package com.company.project.utils;

public enum EPlatform {

	Linux("Linux"), Mac_OS("Mac OS"), Mac_OS_X("Mac OS X"), Windows("Windows");

	private EPlatform(String desc) {
		this.description = desc;
	}

	public String toString() {
		return description;
	}

	private String description;
}
