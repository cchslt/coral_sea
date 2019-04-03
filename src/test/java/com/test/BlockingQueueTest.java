package com.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueTest {


    public static class Basket {
        BlockingQueue<String> basket = new ArrayBlockingQueue<>(3);

        public void produce() throws InterruptedException {
            basket.put("An apple");
        }


        public String consume() throws InterruptedException {
           String apple = basket.take();
           return apple;
        }

        public int getAppleNum() {
            return basket.size();
        }
    }



    public static void testBasket() {
        final Basket basket = new Basket();

        class Produce implements Runnable{

            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("p:生产者准备生产苹果：" + System.currentTimeMillis());
                        basket.produce();

                        System.out.println("p:生产完毕 " + System.currentTimeMillis());
                        System.out.println("p:生产完之后有苹果： " + basket.getAppleNum());

                        Thread.sleep(300);
                    }
                } catch (Exception e) {

                }
            }
        }

        class Consume implements Runnable{

            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("c:消费者准备生产苹果：" + System.currentTimeMillis());
                        basket.consume();

                        System.out.println("c:消费完毕 " + System.currentTimeMillis());
                        System.out.println("c:消费完之后有苹果： " + basket.getAppleNum());

                        Thread.sleep(1000);
                    }
                } catch (Exception e) {

                }
            }
        }

        ExecutorService service = Executors.newCachedThreadPool();
        Produce produce = new Produce();
        Consume consume = new Consume();
        service.submit(produce);
        service.submit(consume);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.shutdownNow();
    }


    public static void main(String[] args) {
        BlockingQueueTest.testBasket();
    }
}
