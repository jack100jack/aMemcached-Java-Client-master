package com.meetup.memcached.test;
import java.io.IOException;
import java.net.InetSocketAddress;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

/**
 * Created by wzj on 2018/6/5.
 */
public class MemcachedTest11211
{
    public static void main(String[] args) throws IOException
    {
        // 本地连接 Memcached 服务
        MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
        System.out.println("Connection to server sucessful.");
 
        // 关闭连接
        mcc.shutdown();
    }
}

