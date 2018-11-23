package com.meetup.memcached.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;
 
/**
 * Created by wzj on 2018/6/5.
 */
public class MemcachedTestAdd
{
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException
    {
        // 连接本地的 Memcached 服务
        MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
 
        // 添加数据
        Future fo = mcc.set("key", 900, "hello");
 
        // 打印状态
        System.out.println(fo.get());
 
        // 输出
        System.out.println(mcc.get("key"));
 
        // 添加
        fo = mcc.add("key", 900, "memcached");
 
        // 打印状态
        System.out.println("add status:" + fo.get());
 
        // 添加新key
        fo = mcc.add("key3", 900, "key3");
 
        // 打印状态
        System.out.println("add status:" + fo.get());
 
        // 输出
        System.out.println(mcc.get("key3"));
 
        // 关闭连接
        mcc.shutdown();
    }
}
