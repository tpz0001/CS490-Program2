/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.program2;

import java.util.Random;

/**
 * This class will produce more process nodes at a regular interval for a 
 * specified amount of time
 * 
 * New processes have a properly incremented processId, random priority, and
 * constant time slice.
 * @author Tim
 */
public class Producer implements Runnable {
    private int runTime_ms;//total amount of time producer will run
    private int delayTime_ms = 500;//time between spawning new threads
    private MinHeap heap;
    
    public Producer(MinHeap heap, int runTime_ms){
        this.runTime_ms = runTime_ms;
        this.heap = heap;
    }
    
    /**
     * Create a random node with 0 < priority < 4, 500 < time slice < 5000
     * @return 
     */
    private ProcessNode createNode(){
        int minPriority = 0; int maxPriority = 5;
        int processID = heap.getNextProcessId();//keep consistent process ids
        
        Random random = new Random();
        int priority = random.nextInt(maxPriority-minPriority);
        
        //actually, lets have a constant time slice for now
        int timeSlice = 750;
        
        return new ProcessNode(processID, priority, timeSlice);
    }
    
    
    public void run(){
        while(runTime_ms > 0){
            try{
                Thread.sleep(delayTime_ms);
            }catch(InterruptedException ex){
                //TODO: Deal with exception
            }
            heap.addNode(createNode());
            runTime_ms -= delayTime_ms;
        }
    }
}
