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
        Consumer consumer = new Consumer(heap, 1000);
        Thread threadOne = new Thread(consumer);
        threadOne.start();
        try{
            threadOne.join();
        } catch(Exception e){
            //TODO: handle error
        }
        
        System.exit(0);
    }
    
}
