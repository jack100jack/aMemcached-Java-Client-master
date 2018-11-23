package com.meetup.memcached.test;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
 
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
 
/**
 * Created by wzj on 2018/6/5.
 */
public class MemcachedTestCas
{
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException
    {
        // ���ӱ��ص� Memcached ����
        MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
 
        // �������
        Future fo = mcc.set("runoob", 900, "Free Education");
 
        // ���ִ�� set �������״̬
        System.out.println("set status:" + fo.get());
 
        // ʹ�� get ������ȡ����
        System.out.println("runoob value in cache - " + mcc.get("runoob"));
 
        // ͨ�� gets ������ȡ CAS token�����ƣ�
        CASValue casValue = mcc.gets("runoob");
 
        // ��� CAS token�����ƣ� ֵ
        System.out.println("CAS token - " + casValue);
 
        // ����ʹ��cas��������������
        CASResponse casresp = mcc.cas("runoob", casValue.getCas(),  "11111");
 
        // ��� CAS ��Ӧ��Ϣ
        System.out.println("CAS Response - " + casresp);
 
        // ���ֵ
        System.out.println("runoob value in cache - " + mcc.get("runoob"));
 
        //�������»�ʧ�ܵ�,��Ϊ������casidΪ mcc.gets("runoob").getCas() + 1����ʵ����idΪmcc.gets("runoob").getCas()
        casresp = mcc.cas("runoob", mcc.gets("runoob").getCas() + 1,  "2222");
 
        // ��� CAS ��Ӧ��Ϣ
        System.out.println("CAS Response - " + casresp);
 
        // ���ֵ
        System.out.println("runoob value in cache - " + mcc.get("runoob"));
 
        // �ر�����
        mcc.shutdown();
    }
}
