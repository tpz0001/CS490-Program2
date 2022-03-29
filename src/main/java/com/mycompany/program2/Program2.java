/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.program2;

/**
 *
 * @author Tim
 */
public class Program2 {
    
    public static void main(String[] args){
        
        //create some processes for testing
        int nodeArraySize = 5;
        ProcessNode[] nodes = new ProcessNode[nodeArraySize];
        for(int i = nodeArraySize - 1; i > -1; i--){
            nodes[i] = new ProcessNode(i,i*2,i*1000);
        }
        
        MinHeap heap = new MinHeap(nodes);
        
        for(ProcessNode node : heap.getHeap()){
            System.out.println(node.getPriority());
        }
        
        /**
        heap.addNode(new ProcessNode(1,3,1));
        System.out.println("add node w/ priorty 3");
        for(ProcessNode node : heap.getHeap()){
            System.out.println(node.getPriority());
        }
        */
        //Create a consumer thread
        int consumerIdleTime_ms = 1000;
        Consumer consumer1 = new Consumer(heap, consumerIdleTime_ms, 1);
        consumerIdleTime_ms -= 100;
        Consumer consumer2 = new Consumer(heap, consumerIdleTime_ms, 2);
        Thread threadOne = new Thread(consumer1);
        Thread threadTwo = new Thread(consumer2);
        
        int producerRuntime_ms = 5000;
        Producer producer = new Producer(heap, producerRuntime_ms);
        Thread threadProducer = new Thread(producer);
        //Create a producer thread

        threadOne.start();
        threadProducer.start();
        threadTwo.start();
        try{
            threadOne.join();
            threadProducer.join();
            threadTwo.join();
        } catch(Exception e){
            //TODO: handle error
        }
        
        for(ProcessNode node : heap.getHeap()){
            System.out.println(node.getPriority());
        }
        
        System.exit(0);
    }
    
}
