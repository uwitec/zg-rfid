package javacommon.util;

import cn.org.rapid_framework.page.PageRequest;

public class PageRequestExt extends PageRequest {
	private int totalNum = 0;

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

}
