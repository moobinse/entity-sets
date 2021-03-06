package org.moobin.meta.impl;

import java.lang.reflect.Field;
import java.util.function.Function;

import org.moobin.meta.EntityMeta;

public class EntityMetaImpl<V, K> extends MetaImpl<V> implements EntityMeta<V, K> {

	private Function<V, K> keyFunction;
	private Class<K> keyType;

	public EntityMetaImpl(Class<V> valueType, Class<K> keyType, Function<V, K> keyFunction) {
		super(valueType);
		this.keyFunction = keyFunction;
		this.keyType = keyType;
	}
	
	@SuppressWarnings("unchecked")
	public EntityMetaImpl(Class<V> valueType, String keyField) throws NoSuchFieldException, SecurityException {
		super(valueType);
		Field field = valueType.getField(keyField);
		this.keyType = (Class<K>) field.getType();
		this.keyFunction = v -> {
			try {
				return (K) field.get(v);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException();
			}
		};
	}

	@Override
	public K getKey(V value) {
		return keyFunction.apply(value);
	}

	@Override
	public Class<K> getKeyType() {
		return keyType;
	}

}
