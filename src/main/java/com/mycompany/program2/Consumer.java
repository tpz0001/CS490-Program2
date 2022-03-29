/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.program2;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Tim
 */
public class Consumer implements Runnable {
    
    private int idleTime_ms;
    private MinHeap heap;
    private ProcessNode currNode;
    private int consumerID;//for debug/demonstration
    
    public Consumer(MinHeap heap, int idleTime_ms, int consumerID){
        this.heap = heap;
        this.idleTime_ms = idleTime_ms;
        this.consumerID = consumerID;
    }
    
    private void getNode(ProcessNode node){
        currNode = node;
    }
    
    /**
     * Reports process id, priority, and time process was completed.
     * Destroys the current node after printing
     */
    public void reportCompletion(){
        System.out.println("ProcessID: " + currNode.getProcessId() +
                            " Priority: " + currNode.getPriority() +
                            " Time Completed: " + java.time.LocalTime.now());
        destroyCurrentNode();
    }
    
    /**
     * Sets current node to null. This will help prevent confusion
     */
    public void destroyCurrentNode(){
        currNode = null;
    }
    
    public void run(){
        int i = 0;
        while(i < 15){//arbitrary run time
            if(heap.getHeap().length == 0){//if heap is empty, we will idle
                currNode = null;
            }
            else currNode = heap.consumeNode();
            
            //TODO: Do I need to compare to null?
            if(currNode == null){//if node does not exist, wait for prescripted time
                System.out.println("Consumer " + consumerID + " is Waiting...");
                try{
                    Thread.sleep(idleTime_ms);
                }catch(InterruptedException ex){
                    //TODO: Deal with exception
                }
            }
            else{//simulate work by waiting for process execution time
                System.out.println("Consumer " + consumerID + 
                        " Running Process " + currNode.getProcessId());
                try{
                    Thread.sleep(currNode.getTimeSlice());
                } catch(InterruptedException ex){
                    //TODO: Deal with exception
                }
                reportCompletion();
            }
            i++;
        }
    }
    
}
