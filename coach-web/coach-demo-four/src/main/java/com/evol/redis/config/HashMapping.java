package com.evol.redis.config;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;

import java.util.Map;

public class HashMapping<T> {

    HashOperations<String, byte[], byte[]> hashOperations;

    HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();

    public HashMapping(HashOperations hashOperations){
        this.hashOperations = hashOperations;
    }

    public void writeHash(String key, T t) {
        Map<byte[], byte[]> mappedHash = mapper.toHash(t);
        hashOperations.putAll(key, mappedHash);
    }

    public T loadHash(String key) {
        Map<byte[], byte[]> loadedHash = hashOperations.entries(key);
        return (T) mapper.fromHash(loadedHash);
    }

}
