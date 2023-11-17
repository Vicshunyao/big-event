package org.kingsy;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocalSetAndGet(){
            ThreadLocal tl =new ThreadLocal();
//            开启俩线程
        new Thread(()->{
            tl.set("xiaoyan");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());

        },"蓝色").start();


        new Thread(()->{
            tl.set("姚晨");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());

        },"绿色").start();
        //
        }
    }

