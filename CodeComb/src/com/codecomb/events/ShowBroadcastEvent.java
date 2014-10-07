
/**
 * @author UFreedom
 * @since 2014 2014-10-7 上午11:48:52
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.events;



public class ShowBroadcastEvent {
	
	public ShowBroadcastEvent(){
		
	}
	
	public ShowBroadcastEvent(String content){
		this.content = content;
	}
	
	
	private String content ;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}

