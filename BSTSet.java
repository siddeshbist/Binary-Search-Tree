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
public class BSTSet {
    private TNode root; //instance field
    
    public BSTSet()
    {
        root = null; //create an empty tree
    }
    public BSTSet( int[] input)
    {
        root = new TNode(input[0],null, null); //insert element 0 of the array into TNode
        
        for(int i =1; i<input.length; i++) //for each element in the array add into BSTSet
        {
            
            add(input[i]); //call method add if element already in BSTSet then add will return false
        }
    }
    
    public boolean isIn(int v){ 
        
        TNode tree = root; //tree represents root of tree
        
        if(tree == null)
        { 
            return false; //empty tree so return false
        }
        while(tree != null) 
        {
            if(v< tree.element){ //checks if v is smaller than current int element
                tree = tree.left; //traverse left branch of tree
            }
            
            else if(v > tree.element){ //checks if v is bigger than current int element
                tree = tree.right; //traverse right towards bigger element 
            }
            
            else if(v == tree.element){
                return true; //element is found in the list 
            }
        }
        return false; // element not in list, tree went to null 
    }
    
    public void add(int v){ 
        if(isIn(v))
        {
            return; //if v is in the BSTSet then the if statement runs and returns, rest of the funciton does not run
        }
        
        else if(root == null){
            root = new TNode(v,null,null);
            return;
        }
        else{
            TNode tree = root;
            boolean x = true;
            
            while(x)   //while loop will run as x is true
            {
                if(v <tree.element)
                {
                    if(tree.left == null)
                    {
                        tree.left = new TNode(v,null,null); //add v 
                        x = false; //exit while loop
                    }
                    tree = tree.left; //go the left node and start evaluating again
                }
                else if( v > tree.element){
                    if(tree.right == null){
                    tree.right = new TNode(v,null,null); //add the element to the right node
                    x = false; //exit the while loop
                    }
                    tree= tree.right; //go to the right node and start evaluating again
                }
            }
        }
    }
    
    public BSTSet union(BSTSet s){        
        if(root == null){
            return s;     //only set s contains elements
        }
        else if(s.root == null){
            return this;  //s does not contain any elements so just return this
                           //this is set created
        }
        BSTSet u = this; //a new set u  
        TNode t = s.root; 
        return u.unionADD(t);//add elements from one set to other
    }
    public BSTSet unionADD(TNode t){
        
        if( t!= null){ //traverse through the list in preorder traversal
             //add elements to BSTSet 
            unionADD(t.left); 
            this.add(t.element);
            unionADD(t.right);
        }
        return this; //returns BSTSet
    }
    
    public Boolean remove(int v){
        if(root == null){
            return false; 
        }
        else if(!isIn(v)) //element not in set
        {
            return false;
        }
        else{
            delTree(v,root);
            return true;
        }
    }
    public TNode delTree(int v, TNode t){
        if(v < t.element){                  //if v is less then the current element it is on the left 
            t.left = delTree(v, t.left);
        }
        else if(v > t.element){            //if v is greater then the current element it is on the right
            t.right = delTree(v,t.right);
        }
        else if(t.left != null && t.right != null){ //v has been found and now evaluating what node to delete
            t.element = (minTree(t.right)); //starting minTree funciton from right subtree and find smalletst element and replace
            t.right = removeMin(t.right);//now you have duplicate so removes the smallest element from the right subtree of node containing v
        }
        else{
            if(t.left != null){ //largest element in left subtree
                t = t.left;            
            }
            else{
                t = t.right; //t.right pointed to null and so replace node t with t.right
            }
        }
        return t;
    }
    public int minTree(TNode t){ //find minimum element in the tree
        while(t.left!=null){
            t = t.left;
        }
        return t.element;
    }
    public TNode removeMin(TNode t){ //removes the minimum node in the tree, if minimum element found then only right node will exist so just replace t with t.right
        if(t.left != null){
            t.left = removeMin(t.left);
        }
        else{
            t = t.right;
        }
        return t;
    }
    
    public BSTSet intersection(BSTSet s){
        BSTSet inter = new BSTSet(); //create new BSTSet inter which contain intersection of elements
        if(root == null || s.root == null){
            //BSTSet inter = new BSTSet();
            return inter;  //if both sets are empty then do not modify inter
        }
        int[] m = s.convertToArray(); //converts to array
        for(int i = 0; i<m.length; i++) //array of length m
        {
            if(this.isIn(m[i])) //is the element in the array also in BSTSet
            {
                if(inter.root == null)//if inter is empty add the new element at the root
                {
                    inter.root = new TNode(m[i],null,null);
                }
                else{
                    inter.add(m[i]);
                }
            }
        }
        return inter;        //return BSTSet
    }
    public int[] convertToArray(){
        int[] arrBST = new int[this.size()]; //size of BST Set is size of array
        extractValues(root,arrBST,0); //call method extractValues
        return arrBST; //return array containing element in s as results array is updated
    }
    public int extractValues(TNode t, int[] results,int index)//go throught each element and store the element in array and increase index to store next element
    {
        if(t.left != null)
        {
            index = extractValues(t.left,results,index);
        }
        if(t.right != null)
        {
            index = extractValues(t.right, results, index);
        }
        results[index] = t.element; //store element in array results
        return index + 1; //increment index by 1
        
    }   
    
    public int size(){
        return size(root);
    }
    public int size(TNode tree)
    {
        if (tree == null)
            return 0;
        else
            return(size(tree.left) + 1 + size(tree.right));//number of elements plus 1 as starting from below root
    }
    
    public int height(){
        return treeHeight(root); 
    }
    public int treeHeight(TNode root){
		if(root==null)
                    return -1; //height of empty set is -1
		return (1+ Math.max(treeHeight(root.left),treeHeight(root.right)));// add 1 because root not being counted
	}
    
    public void printBSTSet() { 
        if(root == null){
            System.out.println("The set is empty");
        }
        else{
            System.out.print("The set elements are: ");
            printBSTSet(root);
            System.out.print("\n");
        }
    }
    private void printBSTSet(TNode t){
        if(t!=null){
            printBSTSet(t.left);
            System.out.print("" + t.element + ", ");
            printBSTSet(t.right);
        }
    }
    public void printNonRec(){
        MyStack store = new MyStack();
        TNode tree = root;
        
        while(tree != null){
            store.push(tree);
            tree = tree.left;
        }
        
        while(store.getSize() >0)
        {
            tree = store.pop(); //store the popped element into TNode
            System.out.print(tree.element + ", "); //print in increasing order
            
            if(tree.right != null)
            {
                tree = tree.right;
                
                while(tree != null)
                {
                    store.push(tree);
                    tree = tree.left;
                }
            }
        }
    }
}

    

