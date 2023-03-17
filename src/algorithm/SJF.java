package algorithm;
import java.util.Scanner;

//CPU Scheduling algorithm: shortest job first(SJF)
 class process{
	int processNumber;
	int arrivalTime;
	int burstTime;
	
	public process(int processNumber, int arrivalTime, int burstTime) {
		this.processNumber = processNumber;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
	}
}

public class SJF {
	public static void main(String [] args) {
		
		//taking input from user to put in the table
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of processes: ");
		int x = input.nextInt();
		int[] processNumber = new int[x];
		int[] arrivalTime = new int[x];
		int[] burstTime = new int[x];
		int[] completionTime = new int[x];
		int[] waitingTime = new int[x];
		int[] turnaroundTime = new int[x];
		int[] flag = new int[x]; //checks if process is complete
		int total = 0;
		int startTime = 0;
		double AverageWaitingTime = 0;
		double AverageTurnAroundTime = 0;
		
		System.out.println("Enter arrival time and burst time for each process:  ");
		
		for(int i =0; i<x; i++) {
			System.out.println("Arrival time of process " +(i+1) +"=>");
			arrivalTime[i] = input.nextInt();
			
			System.out.println("Burst time of process " + (i+1) +"=>");
			burstTime[i] = input.nextInt();
		}
		
		
		//how the algorithm works
		boolean a =true;
		while(true) {
			int currentTime = x;
			int min = 999;
			if(total == x)
				break;
			for(int j=0; j <x; j++) {
				if ((arrivalTime[j] <= startTime) && (flag[j] == 0) && (burstTime[j] < min)) {
					min = burstTime[j];
					currentTime = j;
				}
			}
			if(currentTime == x)
				startTime++;
			else {
				completionTime[currentTime] = startTime + burstTime[currentTime];
				startTime += burstTime[currentTime];
				turnaroundTime[currentTime] = completionTime[currentTime] - arrivalTime[currentTime];
				waitingTime[currentTime] = turnaroundTime[currentTime] - burstTime[currentTime];
				flag[currentTime] = 1;
				total++;
			}
			
		}
		//output result
		System.out.println("\n Process\t ArrivalTime\t BurstTime\t CompletionTime\t TurnAroundTime\t WaitingTime");
		for(int y=0; y<x; y++) {
			AverageWaitingTime += waitingTime[y];
			AverageTurnAroundTime += turnaroundTime[y];
			System.out.println((y+1) + "\t\t" +arrivalTime[y] + "\t\t" + burstTime[y] + "\t\t" + completionTime[y] + "\t\t" + turnaroundTime[y] + "\t\t" + waitingTime[y]);
		}
		System.out.println("\n Average Turn Around Time is " +(double)(AverageTurnAroundTime/x));
		System.out.println("Average Waiting Time is " +(double)(AverageWaitingTime/x));
		input.close();
	}
}
