package com.moobin.meta;

public interface EntityMeta<V, K> extends ValueMeta<V> {

	Class<K> getKeyType();
	
	K getKey(V value);
	
	<T> PropertyMeta<K, ?> getPropretyMeta(String name);
	
}
