package com.meetup.memcached.test;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
 
import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

public class MemcachedTestSet {
		
	    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException
	    {
	        MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
	 
	        Future<Boolean> set = mcc.set("key1", 10, "hello");
	 
	        //�鿴�洢״̬
	        System.out.println(set.get());
	        
	        mcc.set("key2",2,4);
	 
	        System.out.println(mcc.get("key1"));
	 
	        int value = (int) mcc.get("key2");
	        System.out.println(value);
	 
	        mcc.set("key1", 10, "welcome");
	        System.out.println(mcc.get("key1"));
	 
	        mcc.shutdown();
	    }
	}
