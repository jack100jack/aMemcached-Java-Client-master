package com.meetup.memcached.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
 
import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

/**
 * Created by wzj on 2018/6/5.
 */
public class MemcachedTestPrepend
{
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException
    {
        //���ӱ��ص� Memcached ����
        MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
        System.out.println("Connection to server sucessful.");
 
        // ��ӵ�һ�� key=��value ��
        mcc.set("key", 900, "1111");
 
        mcc.append(1, "key", "append");
 
        System.out.println(mcc.get("key"));
 
 
        mcc.prepend(1, "key", "prepend");
 
        System.out.println(mcc.get("key"));
        // �ر�����
        mcc.shutdown();
    }
}

