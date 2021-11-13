import java.util.Random;
import java.util.concurrent.Flow.Subscription;

	@SuppressWarnings("unused")
	class summation<Summation> extends Thread {
		
		private int [] arr;
		
		private int low , high , partial ;
		
		public summation (int [] arr , int low , int high)
		
		
		{
			
			this. arr = arr;
			
			this.low = low;
			
			this.high = Math.min(high, arr.length);
		}
		
		public int getPartialSum()
		
		{
			
			return partial ;
			
			
		}
		
		public void run()
		
		{
			
			partial =sum(arr,low,high);
			
		}
		
		public static int sum (int [] arr , int low , int high)
		
		{
			
			
			int total = 0;
			
			for (int i = low ; i < high ; i++) {
				
			total += arr[i];
			
			}
			 return total;
		}
		
		public static int parallelSum(int [] arr)
		
		{
			
			return parallelSum(arr, Runtime.getRuntime().availableProcessors());
			
			
		}
		
		@SuppressWarnings({ "rawtypes", "null" })
		public static  <Summation> int parallelSum(int [] arr, int threads)
		
		{
			
			int size = (int) Math.ceil(arr.length * 1.0/threads);
			
			Summation [] sums = null;
			
			for (int i=0; i<threads ;i++) {
				
				sum (arr, i* size , (i + 1)* size );
				
				 ((Thread) sums [i]).start();
				
			}
			
			try {
				
				for (Summation sum : sums ) 
				
					
					((Thread) sum) .join ();
				
				
				
			
		}
			catch(InterruptedException e) {}
			
			int total = 0;
			
			for (Summation  sum : sums) {
				
				total += ((summation) sum).getPartialSum ();
				
			}
			
			return total ;
		}
		
	}

	public class Main {
		
		public static void main (String [] args )
		
		{
			
			Random rand = new Random ();
			
			int []arr = new int [200000000];
			for (int i=0; i<arr.length; i++) {
			arr[i]= rand.nextInt(10) +1 ;
			
			}
			
			long start = System.currentTimeMillis();
			
			System.out.println(summation.sum(arr, 0, 0));
			
			System.out.println ("Single:" + (System.currentTimeMillis()- start));
			
			start = System.currentTimeMillis();
			
			System.out.println(summation.parallelSum(arr));
			
			System.out.println("Parallel:" + (System.currentTimeMillis() - start));
			
			
		
		
			}

	

}
