package algorithms;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Dijkstra shortest path algorithm 
 * calculates the shortest path form 1 to all nodes
 *@ibrahimHeritch
 */
public class Dijkstra {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("dijkstraData.txt"));
		int[][] graph = new int[201][201];
		ArrayList<djkestraNode> ar=new ArrayList<>();
		ar.add(null);
		for(int i=0;i<201;i++) {
			Arrays.fill(graph[i], -1);
		}
		for (int i = 0; i < 200; i++) {
			int y = s.nextInt();
			String[] strs = s.nextLine().trim().split("(\\s+)|(,)");
			for(int j=0;j<strs.length;j+=2) {
				int x=Integer.parseInt(strs[j]);
				int value=Integer.parseInt(strs[j+1]);
				graph[y][x]=value;
				graph[x][y]=value;
			}
			
            ar.add(new djkestraNode(i+1));
		}
		dijkestra(ar,graph);
		for(int i=1;i<201;i++) {
			System.out.print(i+" "+ar.get(i).score+"\n");
		}
		

	}

	private static void dijkestra(ArrayList<djkestraNode> ar,int[][] graph) throws Exception {
		djkestraHeap heap =new djkestraHeap();
		boolean[] added= new boolean[201];
		ar.get(1).score=0;
		heap.add(ar.get(1));
		added[1]=true;
	
		while(!heap.isEmpty()) {
			djkestraNode n=heap.pop();
			for(int i=1;i<201;i++) {
				if(graph[n.index][i]>-1  ) {
				
					if(added[i] && n.score+graph[n.index][i]<ar.get(i).score) {
						ar.get(i).score=n.score+graph[n.index][i];
						heap.decreaseScore(ar.get(i));
						
					}else if(!added[i]){
						ar.get(i).score=n.score+graph[n.index][i];
						heap.add(ar.get(i));
						added[i]=true;
					}
					
				}
			}
		}
		
	}

}
class djkestraHeap{
	private djkestraNode[] data;
	int size;
	
	public djkestraHeap() {
		super();
		this.data = new djkestraNode[202];
		this.size = 0;
	}

	private int parent(int i) { return (i-1)/2; }
	 
    public void decreaseScore(djkestraNode dNode) {
        while (dNode.heapIndex != 0 && data[parent(dNode.heapIndex)].score > data[dNode.heapIndex].score){
           swap(dNode.heapIndex, parent(dNode.heapIndex));
        }
		
	}

	// to get index of left child of node at index i
    private int left(int i) { return (2*i + 1); }
 
    // to get index of right child of node at index i
    private int right(int i) { return (2*i + 2); }
 
    // to extract the root which is the minimum element
    public djkestraNode peek() {
    	return data[0];
    }
    
    public void add(djkestraNode d) {
    	     size++;
    	    int i = size - 1;
    	    data[i]= d;
    	    d.heapIndex=i;
    	 
    	    // Fix the min heap property if it is violated
    	    while (i != 0 && data[parent(i)].score > data[i].score)
    	    {
    	       swap(i, parent(i));
    	       i = parent(i);
    	    }
    }
    
    

	private void swap(int i, int parent) {
		djkestraNode temp=data[i];
		data[i]=data[parent];
		data[parent]=temp;
		data[i].heapIndex=i;
		data[parent].heapIndex=parent;
		
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public djkestraNode pop() throws Exception {
		if (size <= 0)
	        throw new Exception("poping empty heap");
		else if (size == 1){
	        size--;
	        return data[0];
	    }
	 
	    
	    djkestraNode temp = data[0];
	    data[0] = data[size-1];
	    size--;
	    heapify(0);
	 
	    return temp;
	}

	private void heapify(int i) {
		int l = left(i);
	    int r = right(i);
	    int smallest = i;
	    if (l < size && data[l].score < data[i].score)
	        smallest = l;
	    if (r < size && data[r].score < data[smallest].score)
	        smallest = r;
	    if (smallest != i)
	    {
	        swap(i, smallest);
	        heapify(smallest);
	    }
		
	}
}

class 	djkestraNode implements Comparable<djkestraNode>{
    int index;
    int score;
    int heapIndex;
	public djkestraNode(int index) {
		super();
		this.index = index;
		this.score=1000000;
	}

	@Override
	public int compareTo(djkestraNode arg0) {
		return this.score-arg0.score;
	}
	
}