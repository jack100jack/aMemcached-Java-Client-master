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
        // 连接本地的 Memcached 服务
        MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
 
        // 添加数据
        Future fo = mcc.set("runoob", 900, "Free Education");
 
        // 输出执行 set 方法后的状态
        System.out.println("set status:" + fo.get());
 
        // 使用 get 方法获取数据
        System.out.println("runoob value in cache - " + mcc.get("runoob"));
 
        // 通过 gets 方法获取 CAS token（令牌）
        CASValue casValue = mcc.gets("runoob");
 
        // 输出 CAS token（令牌） 值
        System.out.println("CAS token - " + casValue);
 
        // 尝试使用cas方法来更新数据
        CASResponse casresp = mcc.cas("runoob", casValue.getCas(),  "11111");
 
        // 输出 CAS 响应信息
        System.out.println("CAS Response - " + casresp);
 
        // 输出值
        System.out.println("runoob value in cache - " + mcc.get("runoob"));
 
        //这样更新会失败的,因为期望的casid为 mcc.gets("runoob").getCas() + 1，但实际上id为mcc.gets("runoob").getCas()
        casresp = mcc.cas("runoob", mcc.gets("runoob").getCas() + 1,  "2222");
 
        // 输出 CAS 响应信息
        System.out.println("CAS Response - " + casresp);
 
        // 输出值
        System.out.println("runoob value in cache - " + mcc.get("runoob"));
 
        // 关闭连接
        mcc.shutdown();
    }
}
