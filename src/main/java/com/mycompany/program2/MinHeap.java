/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.program2;

import static java.lang.Math.floor;
import java.util.Arrays;

/**
 * Stores the process nodes so that a node with the lowest priority number at 
 * the top of the heap is ready to go when the next process is chosen
 * @author Tim
 */
public class MinHeap {
    private int nextProcessId; //use to properly label added processes
    private ProcessNode[] heap;
    
    public MinHeap(ProcessNode[] inNodes){
        nextProcessId = inNodes.length;
        heap = inNodes;
        MakeHeap();
    }
    
    private void MakeHeap(){
        for(int i = (int) floor(heap.length/2); i >=0; i--){
            //System.out.println("length: " + heap.length);
            FixHeapNode(i);
        }
    }
    
    /**
     * Requires left and right subtrees of p'th node to be heaps
     * @param p the p'th node of the heap
     */
    private void FixHeapNode(int p){
        //System.out.println("p: " + p);
        if(2*p < heap.length){//node has at least one child
            int child = 2*p; //take the first child by default
            //System.out.println("child: " + child);
            if((2*p+1 < heap.length)){//node has a second child
                //get smaller child
                if(heap[2*p+1].getPriority() < heap[2*p].getPriority()){
                    child = 2*p+1;
                } 
            }
            
            //swap parent and child node if parent is larget than child
            if(heap[p].getPriority() > heap[child].getPriority()){
                ProcessNode temp;
                temp = heap[p];
                heap[p] = heap[child];
                heap[child] = temp;
            }
        }
    }
    
    public ProcessNode[] getHeap(){
        return heap;
    }
    public int getNextProcessId(){
        return nextProcessId;
    }
    
    /**
     * insert a node into the heap, structure will be preserved
     * @param node 
     */
    public void addNode(ProcessNode node){
        nextProcessId += 1; //increment id for tracking/creating new processes
        heap = Arrays.copyOf(heap, heap.length + 1);//increase size of heap
        heap[heap.length-1] = node;//add new node to the end
        MakeHeap();//remake the heap
    }
    
    /**
     * Get the node at the top of the heap. Node will
     * be removed from the heap
     */
    public ProcessNode consumeNode(){
        ProcessNode topNode = heap[0];
        heap = Arrays.copyOfRange(heap, 1, heap.length);
        MakeHeap();
        return topNode;
    }
    
}
