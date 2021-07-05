package com.stone.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author chen
 * @create 2021-07-04 23:17
 **/

public class RedissonTest {


    public static void main(String[] args) throws InterruptedException {
        //1、配置Redis-cluster集群的ip和port
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("redis://ip:port")
                .addNodeAddress("~");


        //2、创建redisson客户端
        RedissonClient client = Redisson.create(config);

        //3、测试redisson可重入锁等功能
        testRedissonSimpleLock(client);

    }



    private static void testRedissonSimpleLock(RedissonClient client) throws InterruptedException {

        //2.1 获取key的锁
        RLock lock = client.getLock("anyKey");

        //2.2 加锁
        lock.lock();

        Thread.sleep(1000);


        //2.3 释放锁
        lock.unlock();

    }
}
