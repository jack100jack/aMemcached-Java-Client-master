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
        //���ӱ��ص� Memcached ����
        MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
        System.out.println("Connection to server sucessful.");
 
        // ��ӵ�һ�� key=��value ��
        Future fo = mcc.set("key", 900, "1111");
 
        // ���ִ�� add �������״̬
        System.out.println(fo.get());
 
        // ��ȡ����Ӧ��ֵ
        System.out.println(mcc.get("key"));
 
        // ����µ� key
        fo = mcc.replace("key", 900, "2222");
 
        // ���ִ�� set �������״̬
        System.out.println(fo.get());
 
        // ��ȡ����Ӧ��ֵ
        System.out.println(mcc.get("key"));
 
        // �ر�����
        mcc.shutdown();
    }
}

