/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab032si4;

/**
 *
 * @author Siddesh
 */
public class MyStack {
    private Node head; //implement node class in Stack class as linked list implemenation of stacks
    private int size; 
    
    public MyStack(){
        head = null;
        size =0;
    }
    
    public void push(TNode v){
        head = new Node (v,head);
        size++;
    }
    
    public TNode pop(){
        TNode s = head.tree;
        head = head.next; 
        size--;
        return s; 
    }
    
    public TNode top(){
        return head.tree; 
    }
    
    public int getSize(){
        return size;
    }
}
