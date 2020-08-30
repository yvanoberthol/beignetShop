package com.yvanscoop.beignetShop.Response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Response {
	private Boolean messageStatus = false;
	private String messageString = "success";
}
