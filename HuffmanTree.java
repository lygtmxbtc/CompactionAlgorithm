import java.util.*; 

public class HuffmanTree {
	public static class Node<E>  
    {  
        E data;  
        double weight;     //Frequency of characters
        Node leftChild;  
        Node rightChild;  
        public Node(E data , double weight)  //Define node
        {  
            this.data = data;  
            this.weight = weight;  
        }  
        public String toString()  
        {  
            return "Node[data=" + data  
                + ", weight=" + weight + "]";  
        }  
    }  
    public static void main(String[] args)  
    {  
        List<Node> nodes = new ArrayList<Node>();  
        nodes.add(new Node("A" , 40.0));  
        nodes.add(new Node("B" , 8.0));  
        nodes.add(new Node("C" , 10.0));  
        nodes.add(new Node("D" , 30.0));  
        nodes.add(new Node("E" , 10.0));  
        nodes.add(new Node("F" , 2.0));  
        Node root = HuffmanTree.createTree(nodes);  
        System.out.println(breadthFirst(root));  
    }  
    /** 
     * Structure HuffmanTree 
     * @param nodes All nodes of HuffmanTree 
     * @return Root node 
     */  
    private static Node createTree(List<Node> nodes)  
    {  
        //There are still over 2 nodes
        while (nodes.size() > 1)  
        {  
            quickSort(nodes);  
            //Get two nodes with the minimum weight  
            Node left = nodes.get(nodes.size() - 1);  
            Node right = nodes.get(nodes.size() - 2);  
            //Generate new node, the weight is left child node add right child node  
            Node parent = new Node(null , left.weight + right.weight);  
            //New node becomes the parent node
            parent.leftChild = left;  
            parent.rightChild = right;  
            //Remove the two minimum nodes  
            nodes.remove(nodes.size() - 1);  
            nodes.remove(nodes.size() - 1);  
            //Add new parent node to the nodes  
            nodes.add(parent);  
        }  
        //Return root node  
        return nodes.get(0);  
    }  
    //Swap node(i) and node(j)   
    private static void swap(List<Node> nodes, int i, int j)  
    {  
        Node tmp;  
        tmp = nodes.get(i);  
        nodes.set(i , nodes.get(j));  
        nodes.set(j , tmp);  
    }  
    //From maximum to minimum, sort the nodes  
    private static void subSort(List<Node> nodes  
        , int start , int end)  
    {    
        if (start < end)  
        {  
            //First element as base
            Node base = nodes.get(start);  
            //From left, search the i >= base  
            int i = start;  
            //From right, search the j<= base
            int j = end + 1;  
            while(true)  
            {            
                while(i < end && nodes.get(++i).weight >= base.weight);                  
                while(j > start && nodes.get(--j).weight <= base.weight);  
                if (i < j)  
                {  
                    swap(nodes , i , j);  
                }  
                else  
                {  
                    break;  
                }  
            }  
            swap(nodes , start , j);    
            subSort(nodes , start , j - 1);  
            subSort(nodes , j + 1, end);  
        }  
    } 
    //Define quicksort 
    public static void quickSort(List<Node> nodes)   
    {  
        subSort(nodes , 0 , nodes.size() - 1);  
    }  
    //Breadth-first traversal  
    public static List<Node> breadthFirst(Node root)  
    {  
        Queue<Node> queue = new ArrayDeque<Node>();  
        List<Node> list = new ArrayList<Node>();  
        if( root != null)  
        {  
            //add root to queue  
            queue.offer(root);  
        }  
        while(!queue.isEmpty())  
        {  
            //add the element in the head of the queue to list
            list.add(queue.peek());  
            Node p = queue.poll();
            //get the element in the head of the queue and remove it
            if(p.leftChild != null)  
            {  
                queue.offer(p.leftChild);  
            }    
            if(p.rightChild != null)  
            {  
                queue.offer(p.rightChild);  
            }  
        }  
        return list;  
    }  
}  


