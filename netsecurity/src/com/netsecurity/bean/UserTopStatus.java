package com.netsecurity.bean;

public class UserTopStatus {
	private String opera;
	private String timeTop1;
	private String Top1Value;
	private String timeTop2;
	private String Top2Value;
	private String timeTop3;
	private String Top3Value;
	private String timeTop4;
	private String Top4Value;
	
	public UserTopStatus(String timeTop1, String top1Value, String timeTop2,
			String top2Value, String timeTop3, String top3Value,
			String timeTop4, String top4Value) {
		super();
		this.timeTop1 = timeTop1;
		Top1Value = top1Value;
		this.timeTop2 = timeTop2;
		Top2Value = top2Value;
		this.timeTop3 = timeTop3;
		Top3Value = top3Value;
		this.timeTop4 = timeTop4;
		Top4Value = top4Value;
	}
	public String getOpera() {
		return opera;
	}
	public void setOpera(String opera) {
		this.opera = opera;
	}
	public String getTimeTop1() {
		return timeTop1;
	}
	public void setTimeTop1(String timeTop1) {
		this.timeTop1 = timeTop1;
	}
	public String getTop1Value() {
		return Top1Value;
	}
	public void setTop1Value(String top1Value) {
		Top1Value = top1Value;
	}
	public String getTimeTop2() {
		return timeTop2;
	}
	public void setTimeTop2(String timeTop2) {
		this.timeTop2 = timeTop2;
	}
	public String getTop2Value() {
		return Top2Value;
	}
	public void setTop2Value(String top2Value) {
		Top2Value = top2Value;
	}
	public String getTimeTop3() {
		return timeTop3;
	}
	public void setTimeTop3(String timeTop3) {
		this.timeTop3 = timeTop3;
	}
	public String getTop3Value() {
		return Top3Value;
	}
	public void setTop3Value(String top3Value) {
		Top3Value = top3Value;
	}
	public String getTimeTop4() {
		return timeTop4;
	}
	public void setTimeTop4(String timeTop4) {
		this.timeTop4 = timeTop4;
	}
	public String getTop4Value() {
		return Top4Value;
	}
	public void setTop4Value(String top4Value) {
		Top4Value = top4Value;
	}

	
}
