package parameters;

public class Parameters {

	public static double power = 2 ;
	public static double norm = 10000;
	public static double sigDiff = 0.4;
	public static double minDiff = 3;
	public static double noSignal = -120;
	public static double diffNoSig = 100;
	public static int numberAlgoA = 3;
	public static int numberAlgoB = 4;
			

	public static int getNumberAlgoA() {
		return numberAlgoA;
	}

	public static void setNumberAlgoA(int numberAlgoA) {
		Parameters.numberAlgoA = numberAlgoA;
	}

	public static int getNumberAlgoB() {
		return numberAlgoB;
	}

	public static void setNumberAlgoB(int numberAlgoB) {
		Parameters.numberAlgoB = numberAlgoB;
	}

	public static double getPower() {
		return power;
	}

	public static void setPower(double power) {
		Parameters.power = power;
	}

	public static double getNorm() {
		return norm;
	}

	public static void setNorm(double norm) {
		Parameters.norm = norm;
	}

	public static double getSigDiff() {
		return sigDiff;
	}

	public static void setSigDiff(double sigDiff) {
		Parameters.sigDiff = sigDiff;
	}

	public static double getMinDiff() {
		return minDiff;
	}

	public static void setMinDiff(double minDiff) {
		Parameters.minDiff = minDiff;
	}

	public static double getNoSignal() {
		return noSignal;
	}

	public static void setNoSignal(double noSignal) {
		Parameters.noSignal = noSignal;
	}

	public static double getDiffNoSig() {
		return diffNoSig;
	}

	public static void setDiffNoSig(double diffNoSig) {
		Parameters.diffNoSig = diffNoSig;
	}


}
