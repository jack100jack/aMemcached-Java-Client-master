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
        // ���ӱ��ص� Memcached ����
        MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
 
        // �������
        Future fo = mcc.set("key", 900, "hello");
 
        // ��ӡ״̬
        System.out.println(fo.get());
 
        // ���
        System.out.println(mcc.get("key"));
 
        // ���
        fo = mcc.add("key", 900, "memcached");
 
        // ��ӡ״̬
        System.out.println("add status:" + fo.get());
 
        // �����key
        fo = mcc.add("key3", 900, "key3");
 
        // ��ӡ״̬
        System.out.println("add status:" + fo.get());
 
        // ���
        System.out.println(mcc.get("key3"));
 
        // �ر�����
        mcc.shutdown();
    }
}
