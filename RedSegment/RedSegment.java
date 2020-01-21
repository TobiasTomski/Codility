class RedSegment {
	public static void main (String[] args) {
		String inputChain = "RWWWRWWRW";
		//String inputChain = RandomChainGenerator.genRandomChain();
		System.out.println("Test string length: " + inputChain.length());
		calculateMinStepsToGroup(inputChain);
	}

	private static void calculateMinStepsToGroup(String input) {
		Solution solver = new Solution();
		int result = solver.solution(input);
		String printVal;
		if (input.length() > 20) {
			printVal = input.substring(0, 20) + "[...]";
		} else {
			printVal = input;
		}
		System.out.println("For input chain '" + printVal + "' the minimum number of swaps to groups is: " + result);
	}
}

class Solution {
	private int[] _rMap;
	
	public int solution(String s) {
		if(s == null || s.length() == 0) {
			return -1;
		}
		constructMap(s);
		int numberOfReds = _rMap.length;
		if (numberOfReds > 0) {
			int pos = _rMap.length / 2;
			return getSwapsToGroupAtPos(pos);	
		} else {
			return 0;
		}			
	}

	private void constructMap(String s) {
		this._rMap = new int[this.countReds(s)];
		int j = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'R') {
				this._rMap[j] = i;
				j++;
			}
		}
	}

	private int getSwapsToGroupAtPos(int pos) {
		if(pos > _rMap.length-1 || 0 > pos) {
			return -1;
		}
		int result = 0;
		for (int i = 0; i < _rMap.length; i++) {
			result += Math.abs(_rMap[pos] - _rMap[i] - pos + i);
			if (result > Math.pow(10, 9)){
				result = -1;
				break;
			}
		}
		return result;
	}

	private int countReds(String s) {
		return s.length() - s.replace("R", "").length();
	}
}
