package com.example.bookstore;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet(){
        //提供一个threadLocal对象
        ThreadLocal tl=new ThreadLocal();

        //开启两个线程
        new Thread(()->{
            tl.set("硝烟");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"线程1").start();

        new Thread(()->{
            tl.set("药师");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"线程2").start();
    }
}
