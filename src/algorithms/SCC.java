package algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
/*
 * computes strongly connected components (SCCs)
 */
public class SCC {
	public static int size=875715;
	public static void main(String[] ags) throws FileNotFoundException {
		
		Scanner s= new Scanner(/*"7 1\n4 7\n9 7\n1 4\n6 9\n8 6\n3 6\n9 3\n2 8\n5 2\n8 5"*/new File("SCC.txt"));
		ArrayList<Node> ar= new ArrayList<>(size); 
		for(int i=0;i<size;i++) {
			ar.add(new Node(i));
		}
	    while(s.hasNextLine()) {
	    	Scanner sc=new Scanner(s.nextLine());
	    	int a=sc.nextInt(),b=sc.nextInt();
	    	ar.get(a).addOut(ar.get(b));
	    	ar.get(b).addIn(ar.get(a));
	    }
	    ArrayList<ArrayList<Node>> a=DSFForward(ar,DFSBackward(ar));
	    Collections.sort(a,new myComparator());
	    for(ArrayList<Node> i:a) {
	    	System.out.println(i.size());
	    }
	    
	}
	public static Stack<Node> DFSBackward(ArrayList<Node> graph){
		HashSet<Integer> visited = new HashSet<>(size);
		HashSet<Integer> temp = new HashSet<>(size);
		Stack<Node> order= new Stack<>();
		Stack<Node> recursion = new Stack<>(); 
		for(Node n: graph) {
			if(!visited.contains(n.id)) {
				temp.add(n.id);
				recursion.add(n);
				
				while(!recursion.isEmpty()) {
					Node current= recursion.peek();
					int count=0;
					for(Node n1:current.inComing) {
						if(!visited.contains(n1.id) && !temp.contains(n1.id)) {
			               recursion.push(n1);
			               temp.add(n1.id);
			               break;
						}
						count++;
					}
					if(count==current.inComing.size()) {
						visited.add(recursion.peek().id);
						order.push(recursion.pop());
					}
					
				}
		      }
		}
		return order;
	}
	public static ArrayList<ArrayList<Node>> DSFForward(ArrayList<Node> graph, Stack<Node> order) {
		HashSet<Integer> visited = new HashSet<>(size);
		HashSet<Integer> temp = new HashSet<>(size);
		ArrayList<ArrayList<Node>> result= new ArrayList<>();
		Stack<Node> recursion = new Stack<>();
		Node n=order.pop();
		for(int k=0;k<graph.size()-1;k++,n=order.pop()) {
			if(!visited.contains(n.id)) {
				ArrayList<Node> scc=new ArrayList<Node>();
				temp.add(n.id);
				recursion.add(n);
				while(!recursion.isEmpty()) {
					int count=0;
					Node current= recursion.peek();
					for(Node n1:current.outGoing) {
						if(!visited.contains(n1.id) && !temp.contains(n1.id)) {
			               recursion.push(n1);
			               temp.add(n1.id);
			               break;
						}
						count++;
					}
					if(count==current.outGoing.size()) {
						visited.add(recursion.peek().id);
					    scc.add(recursion.pop());
					}
					
				}
				result.add(scc);
		      }
		}
		return result;
	}
}
class myComparator implements Comparator<ArrayList<Node>>{

	@Override
	public int compare(ArrayList<Node> arg0, ArrayList<Node> arg1) {
		return arg0.size()-arg1.size();
	}
	
}
class Node{
	int id;
	ArrayList<Node> outGoing;
	ArrayList<Node> inComing;
	Node(int id){
		this.id=id;
		outGoing=new ArrayList<>();
		inComing=new ArrayList<>();
	}
	void addOut(Node out) {
		outGoing.add(out);
	}
	void addIn(Node in) {
		inComing.add(in);
	}
	@Override
	public String toString() {
		return "Node [id="+id+" outGoing=" + outGoing.size() + ", inComing=" + inComing.size() + "]";
	}
	
	
}