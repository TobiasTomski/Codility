import java.util.ArrayList;
import java.util.Random;

class RedSegment {
	public static void main (String[] args) {
		//String inputChain = "RWWWRWWRW";
		String inputChain = RandomChainGenerator.genRandomChain();
		calculateMinStepsToGroup(inputChain, true);
		System.out.println("TEST:");
		testMassExecution(10000);
	}

	private static void calculateMinStepsToGroup(String input, boolean verbose) {
		Solution solver = new Solution();
		long start = System.nanoTime();
		int result = solver.solution(input);
		long end = System.nanoTime();
		long duration = end - start;
		String printVal;
		if (input.length() > 20) {
			printVal = input.substring(0, 20) + "[...]";
		} else {
			printVal = input;
		}
		if (verbose) {
			System.out.println("Test string length: " + input.length());
			System.out.println("For input chain '" + printVal + "' the minimum number of swaps to groups is: " + result);
			System.out.println("Solution took " + duration + " nanoseconds / " + duration / 1000000F + " milliseconds / " + duration / 1000000000F + " seconds");
		}
	}

	private static void testMassExecution(int numberOfExecutions) {
		Solution solver = new Solution();
		String[] chains = new String[numberOfExecutions];
		for(int i = 0; i < numberOfExecutions; i++) {
			chains[i] = RandomChainGenerator.genRandomChain(50000L);
		}

		int counter = 0;
		long start = System.nanoTime();
		while (counter < numberOfExecutions) {
			int result = solver.solution(chains[counter]);
			counter++;
		}
		long end = System.nanoTime();
		long duration = end - start;
		float durationInMS = duration / 1000000F;
		float durationInS = duration / 1000000000F;
		System.out.println("Total execution time for " + numberOfExecutions + " executions: " + durationInS + " seconds.");
		System.out.println("Average execution time per solution: " + durationInMS / numberOfExecutions + "ms / " + durationInS / numberOfExecutions + "s");
	}
}

class RandomChainGenerator {
	public static String genRandomChain() {
		return genRandomChain(200000L);
	}

	public static String genRandomChain(long maxLength) {
		int length = (int) (Math.random() * maxLength);
		StringBuilder chain = new StringBuilder();
		int i = 0;
		while ( i < length ) {
			if (Math.random() > 0.5) {
				chain.append("R");
			} else {
				chain.append("W");
			}
			i++;
		}
		return chain.toString();
	}
}

class Solution {
	private ArrayList<Integer> _rMap;

	public int solution(String s) {
		if(s == null || s.length() == 0) {
			return -1;
		}
		constructMap(s);
		int numberOfReds = _rMap.size();
		if (numberOfReds > 0) {
			int pos = _rMap.size() / 2;
			return getSwapsToGroupAtPos(pos);	
		} else {
			return 0;
		}			
	}

	private void constructMap(String s) {
		_rMap = new ArrayList<>(s.length());
		int currentPosition = 0;
		for (char ball : s.toCharArray() ) {
			if (ball == 'R') {
				_rMap.add(currentPosition);
			}
			currentPosition++;
		}
	}

	private int getSwapsToGroupAtPos(int pos) {
		if(pos > _rMap.size()-1 || 0 > pos) {
			return -1;
		}
		int result = 0;
		for (int i = 0; i < _rMap.size(); i++) {
			result += Math.abs(_rMap.get(pos) - _rMap.get(i) - pos + i);
			if (result > Math.pow(10, 9)){
				result = -1;
				break;
			}
		}
		return result;
	}
}
