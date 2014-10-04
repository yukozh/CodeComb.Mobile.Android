package com.codecomb.infrastructure;

import com.google.gson.annotations.Expose;

public class KeyValue {
	@Expose
	public String Key;
	@Expose
	public Object Value;

	public KeyValue(String key, Object value) {
		// TODO Auto-generated constructor stub

		this.Key = key;
		this.Value = value;
	}
}
