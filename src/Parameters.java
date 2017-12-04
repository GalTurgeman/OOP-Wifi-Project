
public class Parameters {

	public static double power;
	public static double norm;
	public static double sigDiff;
	public static double minDiff;
	public static double noSignal;
	public static double diffNoSig;

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

	public Parameters() {
		this.power = 2;
		this.norm = 1000;
		this.sigDiff = 0.4;
		this.minDiff = 3;
		this.noSignal = -120;
		this.diffNoSig = 100;
	}

}
