package com.yang.demo;

// test for object escape during construct method
public class Demo1 {

    private String value ="default";
    public Demo1() throws InterruptedException {

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new CallBack() {
                    public void receiveDemo1(Demo1 demo1) {
                        System.out.println("get value of demo1:"+demo1.value);
                    }
                }.receiveDemo1(Demo1.this);
            }
        }.start();
        Thread.sleep(1);
        this.value= "changed";
    }



    public static  void  main (String [] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        new Demo1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

    }
}

interface CallBack {
    void receiveDemo1(Demo1 demo1);
}
