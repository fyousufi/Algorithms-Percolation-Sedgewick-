/*
 * Written by Farjad Yousufi for Algorithms Part 1 to run Monte Carlo Simulation
 */
public class PercolationStats {
	
	private Percolation percolate_test;
	private double[] percolation_thresholds;
	
	
	public static void main(String[] args) {
		
		
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(N,T);
		ps.Print_Stats();
		
		
	}
	
	public PercolationStats(int N, int T){
		if( N<= 0 || T <= 0) throw new IllegalArgumentException("This is out of bounds");
		else{
				
				percolation_thresholds = new double[T];
			
				
			
				for(int i =0; i < T; i++)
				{	percolate_test = new Percolation(N);
					int open_sites=0; 
					while(!percolate_test.percolates()){// loop through until Percolation object percolates
						
						int random_i = StdRandom.uniform(N)+1;
						int random_j = StdRandom.uniform(N)+1;
						
						if( random_i > N){random_i=random_i-1;}
						if( random_j > N){random_j=random_j-1;}
					
						//System.out.println("Check pair" + random_i + random_j);
						
						if(!percolate_test.isOpen(random_i, random_j)){
							percolate_test.open(random_i, random_j);
							//System.out.println("Open Site"+ random_i + random_j);
							open_sites++;
							//System.out.println(open_sites);
						}
						if(percolate_test.isFull(random_i, random_j)){
							percolate_test.percolates();
						}
					}
						
					
						
							percolation_thresholds[i]=(open_sites/(double)(N*N)); //store percolation thresholds in data array
							//System.out.println(percolation_thresholds[i]);
						
					
				}
			}
	}
	public double mean(){
		return StdStats.mean(percolation_thresholds);
	}
	
	public double stddev(){
		return StdStats.stddev(percolation_thresholds);
	}
	
	public double confidenceLo(){
		return (mean() - (1.96* stddev())/Math.sqrt(percolation_thresholds.length));
	}
	
	public double confidenceHi(){
		return (mean() + (1.96* stddev())/Math.sqrt(percolation_thresholds.length));
	}
	
	private void Print_Stats(){
		System.out.println("mean  = " + mean());
		System.out.println("stddev = " +stddev());
		System.out.println("95% confidence interval  = " + confidenceLo() + ", " +confidenceHi());
		
		
	}
}
