class CalculateTheSquare {
	public static void main(String[] args) {
		int a = 2;
		int b = 1000000000;
		int result = maxNumberOfSquareRoots(a, b);
		System.out.println("For A = " + a + " and B = " + b + " the result is " + result);
	}

	private static int maxNumberOfSquareRoots(int a, int b) {
		int maximum = 0;
		int currentRootDepth;
		int maxVal = 0;
		while (a <= b) {
			currentRootDepth = calcMaxSqrtDepth(a, false);
			if (currentRootDepth > maximum) {
				maximum = currentRootDepth;
				maxVal = a;
			}
			a++;
		}
		System.out.println("Found maxVal a = " + maxVal);
		calcMaxSqrtDepth(maxVal, true);
		return maximum;
	}

	private static int calcMaxSqrtDepth(int number, boolean verbose) {
		double root = Math.sqrt(number);
		int intRoot = (int) root;
		if(root != intRoot) {
			if(verbose) {
				System.out.println("No further root found. Returning 0.");
			}
			return 0;
			
		}
		if (verbose) {
			System.out.println("One level found with sqrt: " + intRoot + ". Continuing.");
		}
		return 1 + calcMaxSqrtDepth(intRoot, verbose);
	}
}
