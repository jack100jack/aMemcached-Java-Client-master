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
            String key = "memcached";//��Memcached�д�һ��keyΪ"memcached"������
            String value = "Hello World";//valueΪHello World
            int expireTime = 5; // ����ʱ�䣬��λs; ��д��ʱ�̿�ʼ��ʱ������ expireTime s�󣬸����ݹ���ʧЧ���޷��ٶ�����
            doExcute(client, key, value, expireTime);//ִ�в���
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *��Memcachedд���ݷ���
     */
    private static void doExcute(MemcachedClient client, String key, String value, int expireTime)
    {
        try
        {
            OperationFuture<Boolean> future = client.set(key, expireTime, value);
            future.get();// spymemcached set()���첽�ģ�future.get() �ȴ�cache.set()����������Ҳ���Բ��ȴ����û������Լ�����ѡ��;
            System.out.println("Set�����ɹ�");
            System.out.println("Get����:" + client.get(key));
            Thread.sleep(6000);//�ȴ�6000���룬��6�룬�����ݽ������ʧЧ���޷��ٶ���
            System.out.println("6�����ִ��Get����:" + client.get(key));

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
