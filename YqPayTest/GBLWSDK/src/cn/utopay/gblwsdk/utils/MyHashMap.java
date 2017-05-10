package cn.utopay.gblwsdk.utils;

import java.util.concurrent.ConcurrentHashMap;

public class MyHashMap<K, V> extends ConcurrentHashMap<K, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3529113088052069528L;

	@Override
	public String put(K key, String value) {
		if (value == null) {
			value = "";
		}
		return super.put(key, value);
	}
	
}
