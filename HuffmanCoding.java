import java.util.ArrayDeque;  
import java.util.ArrayList;  
import java.util.Collections;  
import java.util.HashSet;  
import java.util.List;  
import java.util.Queue;  
import java.util.Scanner;

public class HuffmanCoding {
	public static String writeString;  
	  
    public static class HNode {  
        String data = "";  
        String coding = "";  
  
        @Override  
        public String toString() {  
            return "HNode [coding=" + coding + ", data=" + data + "]";  
        }  
  
        public HNode(String data) {  
            super();  
            this.data = data;  
        }  
  
        @Override  
        public int hashCode() {  
            final int prime = 31;  
            int result = 1;  
            result = prime * result + ((data == null) ? 0 : data.hashCode());  
            return result;  
        }  
  
        @Override  
        public boolean equals(Object obj) {  
            if (this == obj)  
                return true;  
            if (obj == null)  
                return false;  
            if (getClass() != obj.getClass())  
                return false;  
            HNode other = (HNode) obj;  
            if (data == null) {  
                if (other.data != null)  
                    return false;  
            } else if (!data.equals(other.data))  
                return false;  
            return true;  
        }  
  
    }  
  
    public static class Node {  
        HNode data;  
        int weight;  
        Node leftChild;  
        Node rightChild;  
  
        public Node(HNode data, int weight) {  
            this.data = data;  
            this.weight = weight;  
        }  
  
        public String toString() {  
            return "Node[data=" + data + ", weight=" + weight + "]";  
        }  
  
        @Override  
        public int hashCode() {  
            final int prime = 31;  
            int result = 1;  
            result = prime * result + ((data == null) ? 0 : data.hashCode());  
            return result;  
        }  
  
        @Override  
        public boolean equals(Object obj) {  
            if (this == obj)  
                return true;  
            if (obj == null)  
                return false;  
            if (getClass() != obj.getClass())  
                return false;  
            Node other = (Node) obj;  
            if (data == null) {  
                if (other.data != null)  
                    return false;  
            } else if (!data.equals(other.data))  
                return false;  
            return true;  
        }  
  
    }  
  
    public static void main(String[] args) {  
        
    	//WriteString and set characters as nodes and count weight
    	System.out.println("Please enter in string£º");  
        Scanner scanner = new Scanner(System.in);  
        HuffmanCoding.writeString = scanner.nextLine();  
        char[] chars = writeString.toCharArray();  
        List<Node> nodes = new ArrayList<Node>();  
        for (int i = 0; i < chars.length; i++) {  
            Node t = new Node(new HNode(String.valueOf(chars[i])), 1);  
            if (nodes.contains(t)) {  
                nodes.get(nodes.indexOf(t)).weight++;  
            } else {  
                nodes.add(t);  
            }  
        }  
        // Create tree and coding each character 
        Node root = HuffmanCoding.createTree(nodes);  
        breadthFirst(root, nodes);  
  
        for (int i = 0; i < chars.length; i++) {  
            Node t = new Node(new HNode(String.valueOf(chars[i])), 1);  
            System.out.print(nodes.get(nodes.indexOf(t)).data.coding);  
        }  
    }  
  
    private static Node createTree(List<Node> nodess) {  
        List<Node> nodes = new ArrayList<Node>(nodess);  
  
        while (nodes.size() > 1) {  
            quickSort(nodes);  
              
            Node left = nodes.get(nodes.size() - 1);  
            Node right = nodes.get(nodes.size() - 2);  
             
            Node parent = new Node(new HNode(null), left.weight + right.weight);  
              
            parent.leftChild = left;  
            parent.rightChild = right;  
              
            nodes.remove(nodes.size() - 1);  
            nodes.remove(nodes.size() - 1);  
             
            nodes.add(parent);  
        }  
          
        return nodes.get(0);  
    }  
  
    public static void quickSort(List<Node> nodes) {  
        subSort(nodes, 0, nodes.size() - 1);  
    }  
    
    private static void swap(List<Node> nodes, int i, int j) {  
        Node tmp;  
        tmp = nodes.get(i);  
        nodes.set(i, nodes.get(j));  
        nodes.set(j, tmp);  
    } 
    
    private static void subSort(List<Node> nodes, int start, int end) {  
        if (start < end) {  
            Node base = nodes.get(start);  
            int i = start;  
            int j = end + 1;  
            while (true) {  
                while (i < end && nodes.get(++i).weight >= base.weight)  
                    ;  
                while (j > start && nodes.get(--j).weight <= base.weight)  
                    ;  
  
                if (i < j) {  
                    swap(nodes, i, j);  
                } else {  
                    break;  
                }  
            }  
            swap(nodes, start, j);  

            subSort(nodes, start, j - 1);  

            subSort(nodes, j + 1, end);  
        }  
    }  
  
    
  

    public static void breadthFirst(Node root, List<Node> nodes) {   
        Queue<Node> queue = new ArrayDeque<Node>();  
        List<Node> list = new ArrayList<Node>();  
        if (root != null) {  
  
            queue.offer(root);  
        }  
        while (!queue.isEmpty()) {  
 
            list.add(queue.peek());  
            Node p = queue.poll();  
  
            if (p.leftChild != null) {  
                queue.offer(p.leftChild);  
                p.leftChild.data.coding = p.data.coding + "0";  
            } else {  
                // System.out.println(p+" "+p.data+" "+p.data.data+  
                // " "+p.data.coding);  
  
                // System.out.println("nodes.indexOf(p)"+nodes.contains(p));  
                ((Node) nodes.get(nodes.indexOf(p))).data.coding = p.data.coding;  
            }  
 
            if (p.rightChild != null) {  
                queue.offer(p.rightChild);  
                p.rightChild.data.coding = p.data.coding + "1";  
            }  
            // else {  
            // nodes.get(nodes.indexOf(p)).data.coding=p.data.coding;  
            // System.out.println("you "+p.data.coding);  
            // }  
        }  
  
    }  

}
