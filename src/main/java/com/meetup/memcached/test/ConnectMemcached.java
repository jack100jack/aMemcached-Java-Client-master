package com.meetup.memcached.test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

public class ConnectMemcached
{
    public static void main(String[] args)
    {
        //final String connectionaddress = "ip or domain name:port";//"ip or domain name:port"
    	final String connectionaddress = "127.0.0.1:1234";//"ip or domain name:port"

        MemcachedClient client = null;
        try
        {
            client = new MemcachedClient(new BinaryConnectionFactory(), AddrUtil.getAddresses(connectionaddress));
            String key = "memcached";//向Memcached中存一个key为"memcached"的数据
            String value = "Hello World";//value为Hello World
            int expireTime = 5; // 过期时间，单位s; 从写入时刻开始计时，超过 expireTime s后，该数据过期失效，无法再读出；
            doExcute(client, key, value, expireTime);//执行操作
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *向Memcached写数据方法
     */
    private static void doExcute(MemcachedClient client, String key, String value, int expireTime)
    {
        try
        {
            OperationFuture<Boolean> future = client.set(key, expireTime, value);
            future.get();// spymemcached set()是异步的，future.get() 等待cache.set()操作结束，也可以不等待，用户根据自己需求选择;
            System.out.println("Set操作成功");
            System.out.println("Get操作:" + client.get(key));
            Thread.sleep(6000);//等待6000毫秒，即6秒，该数据将会过期失效，无法再读出
            System.out.println("6秒后再执行Get操作:" + client.get(key));

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        if (client != null)
        {
            client.shutdown();
        }
    }
}
