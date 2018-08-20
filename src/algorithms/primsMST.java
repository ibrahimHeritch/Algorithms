package algorithms;


import java.util.ArrayList;
import java.util.Scanner;
/*
 * Prim's minimum spanning tree algorithm
 * 
 * input format
 * [number_of_nodes] [number_of_edges]

[one_node_of_edge_1] [other_node_of_edge_1] [edge_1_cost]

[one_node_of_edge_2] [other_node_of_edge_2] [edge_2_cost]
 */
public class primsMST {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		int numberNodes=s.nextInt();
		int numberEdges=s.nextInt();
		int[][] graph = new int[numberNodes+1][numberNodes+1];
		ArrayList<dNode> ar=new ArrayList<>();
		ar.add(null);
		for(int i=0;i<numberNodes+1;i++) {
			ar.add(new dNode(i+1));
		}
		for (int i = 0; i < numberEdges; i++) {
			int y = s.nextInt();
				int x=s.nextInt();
				int value=s.nextInt();
				graph[y][x]=value;
				graph[x][y]=value;
		
			
            
		}
		prims(ar,graph,numberNodes);
	    long sum=0;
		for(int i=1;i<numberNodes+1;i++) {
			System.out.print(i+" "+ar.get(i).score+"\n");
			sum+=ar.get(i).score;
		}
		System.out.println(sum);

	}

	private static void prims(ArrayList<dNode> ar,int[][] graph,int numberNodes) throws Exception {
		heap heap =new heap();
		boolean[] added= new boolean[numberNodes+1];
		ar.get(1).score=0;
		heap.add(ar.get(1));
		added[1]=true;
		boolean[] wasRemoved=new boolean[numberNodes+1];
		while(!heap.isEmpty()) {
			dNode n=heap.pop();
			wasRemoved[n.index]=true;
			System.out.println("node n "+n);
			for(int i=1;i<numberNodes+1;i++) {
				if(graph[n.index][i]!=0  ) {
				
					if(added[i] && graph[n.index][i]<ar.get(i).score && !wasRemoved[i]) {
						
						ar.get(i).score=graph[n.index][i];
						heap.decreaseScore(ar.get(i));
						System.out.println("updated "+ar.get(i));
					}else if(!added[i]){
						ar.get(i).score=graph[n.index][i];
						heap.add(ar.get(i));
						added[i]=true;
						System.out.println("added "+ar.get(i));
					}
					
				}
			}
		}
		
	}

}
class heap{
	private dNode[] data;
	int size;
	
	public heap() {
		super();
		this.data = new dNode[1000];
		this.size = 0;
	}

	private int parent(int i) { return (i-1)/2; }
	 
    public void decreaseScore(dNode dNode) {
        while (dNode.heapIndex != 0 && data[parent(dNode.heapIndex)].score > data[dNode.heapIndex].score){
           swap(dNode.heapIndex, parent(dNode.heapIndex));
        }
		
	}

	// to get index of left child of node at index i
    private int left(int i) { return (2*i + 1); }
 
    // to get index of right child of node at index i
    private int right(int i) { return (2*i + 2); }
 
    // to extract the root which is the minimum element
    public dNode peek() {
    	return data[0];
    }
    
    public void add(dNode d) {
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
		dNode temp=data[i];
		data[i]=data[parent];
		data[parent]=temp;
		data[i].heapIndex=i;
		data[parent].heapIndex=parent;
		
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public dNode pop() throws Exception {
		if (size <= 0)
	        throw new Exception("poping empty heap");
		else if (size == 1){
	        size--;
	        return data[0];
	    }
	 
	    
	    dNode temp = data[0];
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

class dNode implements Comparable<dNode>{
    int index;
    int score;
    @Override
	public String toString() {
		return "dNode [index=" + index + ", score=" + score + ", heapIndex=" + heapIndex + "]";
	}

	int heapIndex;
	public dNode(int index) {
		super();
		this.index = index;
		this.score=1000000;
	}

	@Override
	public int compareTo(dNode arg0) {
		return this.score-arg0.score;
	}
	
}