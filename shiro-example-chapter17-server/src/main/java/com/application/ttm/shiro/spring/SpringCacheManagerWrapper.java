package com.application.ttm.shiro.spring;

import net.sf.ehcache.Ehcache;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.*;

/**
 * 包装Spring cache抽象
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-04-03</p>
 * <p>@Version 1.0</p>
 **/
public class SpringCacheManagerWrapper implements CacheManager {

    private org.springframework.cache.CacheManager cacheManager;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        org.springframework.cache.Cache springCache = cacheManager.getCache(s);
        return new SpringCacheWrapper(springCache);
    }

    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    static class SpringCacheWrapper implements Cache {

        private org.springframework.cache.Cache springCache;

        SpringCacheWrapper(org.springframework.cache.Cache springCache) {
            this.springCache = springCache;
        }

        @Override
        public Object get(Object o) throws CacheException {
            Object value = springCache.get(o);
            if (value instanceof SimpleValueWrapper) {
                return ((SimpleValueWrapper) value).get();
            }
            return value;
        }

        @Override
        public Object put(Object o, Object o2) throws CacheException {
            springCache.put(o, o2);
            return o2;
        }

        @Override
        public Object remove(Object o) throws CacheException {
            springCache.evict(o);
            return null;
        }

        @Override
        public void clear() throws CacheException {
            springCache.clear();
        }

        @Override
        public int size() {
            if (springCache.getNativeCache() instanceof Ehcache) {
                EhCache ehCache = (EhCache) springCache.getNativeCache();
                return ehCache.size();
            }
            throw new UnsupportedOperationException("invoke spring cache abstract size method not supported");
        }

        @Override
        public Set keys() {
            if (springCache.getNativeCache() instanceof Ehcache) {
                Ehcache ehcache = (Ehcache) springCache.getNativeCache();
                return new HashSet(ehcache.getKeys());
            }
            throw new UnsupportedOperationException("invoke spring cache abstract keys method not supported");
        }

        @Override
        public Collection values() {
            if (springCache.getNativeCache() instanceof Ehcache) {
                Ehcache ehcache = (Ehcache) springCache.getNativeCache();
                List keys = ehcache.getKeys();
                if (!CollectionUtils.isEmpty(keys)) {
                   List values = new ArrayList(keys.size());
                   for (Object key : keys) {
                       Object value = get(key);
                       if (!(null == value)) {
                           values.add(value);
                       }
                   }
                   return Collections.unmodifiableList(values);
                } else {
                    return Collections.emptyList();
                }
            }
            throw new UnsupportedOperationException("invoke spring cache abstract values method not supported");
        }
    }
}
