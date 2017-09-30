package com.moobin.util;

import java.util.function.Function;

public interface EntitySetBuilder {

	<K, V> EntitySet<K, V> create(Class<V> valueType, Class<K> keyType, Function<V, K> keyFunction);
	
}
