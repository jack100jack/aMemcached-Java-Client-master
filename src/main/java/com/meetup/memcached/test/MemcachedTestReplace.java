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
public class MemcachedTestReplace
{
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException
    {
        //连接本地的 Memcached 服务
        MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
        System.out.println("Connection to server sucessful.");
 
        // 添加第一个 key=》value 对
        Future fo = mcc.set("key", 900, "1111");
 
        // 输出执行 add 方法后的状态
        System.out.println(fo.get());
 
        // 获取键对应的值
        System.out.println(mcc.get("key"));
 
        // 添加新的 key
        fo = mcc.replace("key", 900, "2222");
 
        // 输出执行 set 方法后的状态
        System.out.println(fo.get());
 
        // 获取键对应的值
        System.out.println(mcc.get("key"));
 
        // 关闭连接
        mcc.shutdown();
    }
}

