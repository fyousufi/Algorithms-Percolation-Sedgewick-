
/*
 * Percolation Class for Algorithms Part 1 by Farjad Yousufi 
 *  Program intializes a grid and uses the Weighted 
 */

public class Percolation {
	//main class that contains assignment for creation of NxN grid with sites 
	private WeightedQuickUnionUF Quick;
	private int size;
	private int[][] grid_sites;
	public static void main(String[] args) {
		

	}
	//creates a grid with N rows and N columns and N*N+2 sites (with top and bottom sites included that are naturally open)
	public Percolation(int N){
		
		size = N;
		
		grid_sites = new int[size][size]; //stores the state of the site
		
		
		Quick= new WeightedQuickUnionUF(size*size+2); //data structure to house the site indices and for checking unions and connections 
		//0 equals blocked site
		//1 equals open sites. This is initially only the top sites 
		//set all grid sites to be blocked
	   for(int i = 0; i < size; i++){
		   	//System.out.println("");
		   for(int j=0; j< size; j++ ){
			   grid_sites[i][j] = 0;
			   //System.out.print(grid_sites[i][j]);
		   }
	   }
		//grid_sites[size][size] = 1; //top site is open 0,0 is top site
		//System.out.println(grid_sites[1][1]);
		//grid_sites[N+1][1] = 1; //bottom site is open remember that (N, N+1) is bottom site
		//System.out.println(grid_sites[N+1][1]);
		//connects All top row nodes to top site
		//for(int i = 1; i<= size; i++){
			//Quick.union(size*size, xyto1D(1, i));
		//}
	
		//connects All bottom row nodes to bottom site
		
		//for(int i =1; i <= size;i++){
			//Quick.union(size*size+1, xyto1D(size,i));
		//}
		
	
		
	}
	
	//ensures that the values that are passed in are actually within the grid 
	private void check_range(int i, int j){
		if(i > size || j > size || i < 1 || j < 1 ){
			throw new IndexOutOfBoundsException("This value is not within the grid");
		}
	}
	
	//maps the 2D coordinates to 1D coordinates so that Weighted Quick Union can refer to the actual sites
	private int xyto1D(int i, int j){
		int site_index;
		check_range(i,j);
		site_index = (i-1)*(size) + (j-1);
		
		//System.out.println("Coverts" + i + "," + j+ "to" + site_index);
		return site_index;
		
		
	}
	//if the site is not open then opens it and then connects it to its neighboring open sites unless its on the edge of the matrix 
	public void open(int i, int j){
		check_range(i,j);
		if(!isOpen(i,j)){
			grid_sites[i-1][j-1]=1;
			if(i==1){Quick.union(size*size, xyto1D(i, j)); /*System.out.println("Combines" + xyto1D(i,j) + "and" + size*size );*/}
			if(i==size){Quick.union(size*size+1, xyto1D(i,j)); /*System.out.println("Combines" + xyto1D(i,j) + "and" + (size*size+1) );*/}
		}
		if(i < size){
			if(isOpen(i+1,j)&& !Quick.connected(xyto1D(i,j), xyto1D(i+1,j))){
				Quick.union(xyto1D(i,j), xyto1D(i+1,j));
				//System.out.println("Combines" + xyto1D(i,j) + "and" + xyto1D(i+1,j) );
			}
		}
		if(i > 1){
			if(isOpen(i-1,j)&& !Quick.connected(xyto1D(i,j), xyto1D(i-1,j)))
				{
					Quick.union(xyto1D(i,j), xyto1D(i-1,j));
					//System.out.println("Combines" + xyto1D(i,j) + "and" + xyto1D(i-1,j) );
				}
		}
		if(j < size){
			if(isOpen(i,j+1)&& !Quick.connected(xyto1D(i,j), xyto1D(i,j+1))){
				Quick.union(xyto1D(i,j), xyto1D(i,j+1));
				//System.out.println("Combines" + xyto1D(i,j) + "and" + xyto1D(i,j+1) );
			}
		}
		if( j > 1){
			if(isOpen(i,j-1) && !Quick.connected(xyto1D(i,j), xyto1D(i,j-1))){
				Quick.union(xyto1D(i,j), xyto1D(i,j-1));
				//System.out.println("Combines" + xyto1D(i,j) + "and" + xyto1D(i,j-1) );
			}
		}
		
	}
	
	//check if the site is open and returns true else returns false
	public boolean isOpen(int x, int y){
		if(grid_sites[x-1][y-1]==0){
			
			
			return false;
		}
			
		else
			return true;
		
	}
	
	public boolean isFull(int i, int j){
		if(Quick.connected(size*size, xyto1D(i,j))){
			return true; 
		}
		else{ return false;}
	}
	
	public boolean percolates(){
		if(Quick.connected(size*size,size*size+1)){
			//System.out.println("it percolated\n");
			return true;
		}
		else{ return false;}
	}

}
