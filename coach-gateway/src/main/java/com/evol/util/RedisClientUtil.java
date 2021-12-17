package com.evol.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

/**
 */
@Component
@Slf4j
public class RedisClientUtil {


    private static final Logger logger = LoggerFactory.getLogger(RedisClientUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Autowired
//    private RedissonClient redissonClient;

//    @Autowired
//    private RedisTemplate redisNotTransaction;

    private String key="redisTopic";

    public static final String orderKey = "orderRedisTopic";

    private static int EXPIRE_PERIOD_MIS = 60 * 1000 * 60 * 2;
//    /**
//     * scan 实现
//     * @param pattern	表达式
//     * @param consumer	对迭代到的key进行操作
//     */
//    public void scan(String pattern, Consumer<byte[]> consumer) {
//        this.stringRedisTemplate.execute((RedisConnection connection) -> {
//            try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().count(Long.MAX_VALUE).match(pattern).build())) {
//                cursor.forEachRemaining(consumer);
//                return null;
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//        });
//    }
//
//    /**
//     * 获取符合条件的key
//     * @param pattern	表达式
//     * @return
//     */
//    public List<String> keys(String pattern) {
//        List<String> keys = new ArrayList<>();
//        this.scan(pattern, item -> {
//            //符合条件的key
//            String key = new String(item, StandardCharsets.UTF_8);
//            keys.add(key);
//        });
//        return keys;
//    }
//
//    /**
//     * 写入缓存
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public boolean set(final String key, String value) {
//        boolean result = false;
//        try {
//            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//            operations.set(key, value);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 写入缓存设置时效时间
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public boolean set(final String key, String value, Long expireTime) {
//        boolean result = false;
//        try {
//            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//            operations.set(key, value);
//            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 追加内容
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public boolean append(final String key, String value, Long expireTime) {
//        boolean result = false;
//        try {
//            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//            operations.append(key, value);
//            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 批量删除对应的value
//     *
//     * @param keys
//     */
//    public void remove(final String... keys) {
//        for (String key : keys) {
//            remove(key);
//        }
//    }
//
//    /**
//     * 批量删除key
//     *
//     * @param pattern
//     */
//    public void removePattern(final String pattern) {
//        Set<Serializable> keys = redisTemplate.keys(pattern);
//        if (keys.size() > 0)
//            redisTemplate.delete(keys);
//    }
//
//    /**
//     * 删除对应的value
//     *
//     * @param key
//     */
//    public void remove(final String key) {
//        boolean flag =exists(key);
//        if (flag) {
//            stringRedisTemplate.delete(key);
//        }
//    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        String result = null;
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public <T> T getByKey(final String key) {
        Set<Object> result = null;
        SetOperations<String, Object> operations = redisTemplate.opsForSet();
        result = operations.members(key);
        if(!result.isEmpty()){
            return (T) result.toArray()[0];
        }
        return null;
    }

//    /**
//     * 哈希 添加
//     *
//     * @param key
//     * @param hashKey
//     * @param value
//     */
//    public void hmSet(String key, Object hashKey, Object value) {
//        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
//        hash.put(key, hashKey, value);
//    }
//
//    /**
//     * 哈希 添加
//     *
//     * @param key
//     * @param hashKey
//     * @param value
//     */
//    public void hmSet(String key, Object hashKey, Object value, Long expireTime) {
//        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
//        hash.put(key, hashKey, value);
//        stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//    }
//    /**
//     * 哈希添加
//     *
//     * @param key
//     * @param hashKey
//     * @param value
//     */
//    public void hmSetHashKey(String key, String hashKey, Object value, Long expireTime) {
//        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
//        hash.put(key, hashKey, value);
//        stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//    }
//    /**
//     * 哈希获取数据
//     *
//     * @param key
//     * @param hashKey
//     * @return
//     */
//    public Object hmGet(String key, Object hashKey) {
//        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
//        return hash.get(key, hashKey);
//    }
//
//    /**
//     * 哈希获取数据
//     *
//     * @param key
//     * @return
//     */
//    public Map<Object, Object> hmGetAll(String key) {
//        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
//        return hash.entries(key);
//    }
//
//    /**
//     * 列表添加
//     *
//     * @param k
//     * @param v
//     */
//    public void lPush(String k, Object v) {
//        ListOperations<String, Object> list = redisTemplate.opsForList();
//        list.rightPush(k, v);
//    }
//
//    /**
//     * 列表获取
//     *
//     * @param k
//     * @param l
//     * @param l1
//     * @return
//     */
//    public List<Object> lRange(String k, long l, long l1) {
//        ListOperations<String, Object> list = redisTemplate.opsForList();
//        return list.range(k, l, l1);
//    }
//
    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }
//
//    /**
//     * 集合获取
//     *
//     * @param key
//     * @return
//     */
//    public Set<Object> setMembers(String key) {
//        SetOperations<String, Object> set = redisTemplate.opsForSet();
//        return set.members(key);
//    }
//
//    /**
//     * 有序集合添加
//     *
//     * @param key
//     * @param value
//     * @param scoure
//     */
//    public void zAdd(String key, Object value, double scoure) {
//        ZSetOperations<String, Object> set = redisTemplate.opsForZSet();
//        set.add(key, value, scoure);
//    }
//
//    /**
//     * 有序集合获取
//     *
//     * @param key
//     * @param scoure
//     * @param scoure1
//     * @return
//     */
//    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
//        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
//        return zset.rangeByScore(key, scoure, scoure1);
//    }
//
//    /**
//     * 哈希 删除
//     * @param key
//     * @param hashKey
//     */
//    public void hmDel(String key, Object hashKey) {
//        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
//        hash.delete(key, hashKey);
//    }
//
//    /**
//     * @param lockKey
//     * @param lockTime 注意设置锁定时间要在这次定时任务和下次定时任务执行之间
//     */
//    public synchronized boolean taskLock(String lockKey,long lockTime) {
//        Boolean flag = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
//            @Override
//            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
//                StringRedisSerializer serializer = new StringRedisSerializer();
//                Boolean success = connection.setNX(serializer.serialize(lockKey), serializer.serialize(String.valueOf(lockTime)));
//                connection.close();
//                return success;
//            }
//        });
//        if(flag){
//            logger.info("开始设置过期时间 lockKey：{} 锁定时长:{} 秒",lockKey,lockTime);
//            //设置超时时间，释放内存
//            redisTemplate.expire(lockKey, lockTime, TimeUnit.SECONDS);
//        }
//        return flag;
//    }
//
//    /**
//     * 直接删除锁
//     * @param lockKey
//     */
//    public synchronized void unlockKey(String lockKey) {
//        try {
//            this.remove(lockKey);
//            logger.info("解锁成功 lockKey:{}",lockKey);
//        }catch (Exception e){
//            logger.error("e:{}",e);
//        }
//    }
//
//    /**
//     * 在不知道失效时间的情况下尝试删除锁
//     * @param lockKey
//     * @param lockTime
//     * @return
//     */
//    public synchronized boolean unBeforeLock(String lockKey, long lockTime) {
//        try {
//            String expireTime = this.get(lockKey);
//            if (expireTime != null && (System.currentTimeMillis() - Long.parseLong(expireTime)) > lockTime) {
//                this.remove(lockKey);
//                logger.info("解锁成功 lockKey:{}",lockKey);
//                return true;
//            }else {
//                Thread.sleep(new Random().nextInt(1000));
//            }
//        }catch (Exception e){
//            logger.error("e:{}",e);
//            return false;
//        }
//        return false;
//    }
//
//    /**
//     * 设置NX值
//     * @param key
//     * @param value
//     * @return
//     */
//    public boolean setNXStr(final String key, final String value) {
//        Object obj = null;
//        try {
//            obj = redisTemplate.execute(new RedisCallback<Object>() {
//                @Override
//                public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                    StringRedisSerializer serializer = new StringRedisSerializer();
//                    Boolean success = connection.setNX(serializer.serialize(key), serializer.serialize(value));
//                    //Boolean success = connection.hSetNX(serializer.serialize(key), serializer.serialize(value),serializer.serialize("10000"));
//                    connection.close();
//                    return success;
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return obj != null ? (Boolean) obj : false;
//    }
//
//    /**
//     * 根据后缀模糊获取
//     * @param suffix
//     * @return
//     */
//    public Set<String> getKeySuffix(String suffix){
//        String key = "*"+suffix;
//        return redisTemplate.keys(key);
//    }
//
//    /**
//     * 根据前缀模糊获取
//     * @param prex
//     * @return
//     */
//    public Set<String> getKeyByPrex(String prex){
//        String key = prex+"*";
//        return redisTemplate.keys(key);
//    }
//
//    /**
//     * 根据后缀删除
//     * @param suffix
//     */
//    public void deleteBySuffix(String suffix) {
//        Set<String> keys=redisTemplate.keys("*"+suffix);
//        redisTemplate.delete(keys);
//    }
//
    /**
     * 删除多个key
     * @param keys
     */
    public void deleteByKeys(String... keys) {
        redisTemplate.delete(Arrays.asList(keys));
    }
//    /**
//     *根据前缀模糊删除
//     * @param prex
//     */
//    public void deleteByPrex(String prex) {
//        String key = prex+"*";
//        Set<String> keys=redisTemplate.keys(key);
//        redisTemplate.delete(keys);
//    }
//
//    /**
//     * 对某个主键对应的值加一,value值必须是全数字的字符串
//     *
//     * @param key the key
//     * @return the long
//     */
//    public long incr(final String key) {
//        return (long) redisTemplate.execute((RedisCallback<Long>) connection -> {
//            RedisSerializer<String> redisSerializer = getRedisSerializer();
//            return connection.incr(redisSerializer.serialize(key));
//        });
//    }
//
//    /**
//     * 获取 RedisSerializer
//     *
//     * @return the redis serializer
//     */
//    protected RedisSerializer<String> getRedisSerializer() {
//        return redisTemplate.getStringSerializer();
//    }
//
//
//    /**
//     * 队列里面添加数据
//     * @param message
//     */
//    public void push(String message){
//        redisTemplate.opsForList().leftPush(key,message);
//    }
//
//    /**
//     * 队列里面获取数据
//     * rightPop：1.key,2.超时时间，3.超时时间类型
//     * @return
//     */
//    public Object pop(){
//        return redisTemplate.opsForList().rightPop(key,60, TimeUnit.SECONDS);
//    }
//
//
//    /**
//     * 发送消息
//     * @param message
//     */
//    public void sendMessage(String message){
//        stringRedisTemplate.convertAndSend(key,message);
//    }
//
//
//    /**
//     * 发送消息
//     * @param message
//     */
//    public void sendMessage(String topic, String message){
//        stringRedisTemplate.convertAndSend(topic,message);
//    }
//
//
//    /**
//     * 分布式高并发事务加锁
//     * @param lockKey
//     * @return
//     */
//    public RLock lock(String lockKey) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock();
//        return lock;
//    }
//
//    /**
//     * 分布式高并发事务加锁，过期自动释放
//     * @param lockKey
//     * @param leaseTime	自动释放锁时间
//     * @return
//     */
//    public RLock lock(String lockKey, long leaseTime) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock(leaseTime, TimeUnit.SECONDS);
//        return lock;
//    }
//
//    /**
//     * 分布式高并发事务加锁，过期自动释放，时间单位传入
//     * @param lockKey
//     * @param unit		时间单位
//     * @param leaseTime	上锁后自动释放时间
//     * @return
//     */
//    public RLock lock(String lockKey, TimeUnit unit, long leaseTime) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock(leaseTime, unit);
//        return lock;
//    }
//
//    /**
//     * 分布式高并发事务尝试获锁
//     * @param lockKey
//     * @param unit		时间单位
//     * @param waitTime	最多等待时间
//     * @param leaseTime	上锁后自动释放时间
//     * @return
//     */
//    public boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime) {
//        RLock lock = redissonClient.getLock(lockKey);
//        try {
//            return lock.tryLock(waitTime, leaseTime, unit);
//        } catch (InterruptedException e) {
//            return false;
//        }
//    }
//
//    /**
//     * 分布式高并发事务尝试获取锁
//     * @param lockKey
//     * @param waitTime	最多等待时间
//     * @param leaseTime	上锁后自动释放锁时间
//     * @return
//     */
//    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
//        RLock lock = redissonClient.getLock(lockKey);
//        try {
//            return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            return false;
//        }
//    }
//
//    /**
//     * 分布式高并发事务释放锁
//     * @param lockKey
//     */
//    public void unlock(String lockKey) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.unlock();
//    }
//
//    /**
//     * 分布式高并发事务释放锁
//     * @param lock
//     */
//    public void unlock(RLock lock) {
//        lock.unlock();
//    }
}
