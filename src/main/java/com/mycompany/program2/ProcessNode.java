/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.program2;

/**
 *  @brief This class represents a process, and must have a process id number,
 *  priority (lower number = higher priority), and a time_slice in ms.
 * @author Tim
 */
public class ProcessNode {
    private int processId;
    private int priority;
    private int timeSlice_ms;
    
    public ProcessNode(int pID, int priority, int time_slice_ms){
        processId = pID;
        this.priority = priority;
        timeSlice_ms = time_slice_ms;
    }
    
    /**
     * 
     * @return process identification
     */
    public int getProcessId(){
        return processId;
    }
    /**
     * 
     * @return priority, lower number is a higher priority
     */
    public int getPriority(){
        return priority;
    }
    /**
     * 
     * @return process time slice in ms
     */
    public int getTimeSlice(){
        return timeSlice_ms;
    }
    
    
}
