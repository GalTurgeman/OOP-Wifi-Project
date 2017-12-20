
public class Parameters {
//	2	power
//	10000	norm
//	0.4	sig diff
//	3	min diff
//	-120	no signal
//	100	diff no sig
	public static double power = 2 ;
	public static double norm = 10000;
	public static double sigDiff = 0.4;
	public static double minDiff = 3;
	public static double noSignal = -120;
	public static double diffNoSig = 100;
	public static int number = 3;//do getter setter

	public static int getNumber() {
		return number;
	}

	public static void setNumber(int number) {
		Parameters.number = number;
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

//	public Parameters() {
//		this.power = 2;
//		this.norm = 10000;
//		this.sigDiff = 0.4;
//		this.minDiff = 3;
//		this.noSignal = -120;
//		this.diffNoSig = 100;
//	}

}
