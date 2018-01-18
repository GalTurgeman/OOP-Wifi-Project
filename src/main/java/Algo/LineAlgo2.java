package Algo;
import java.util.LinkedList;

import parameters.Parameters;
import parameters.Wifi;
/**
 * LineAlgo2 get Input line from no gps file and Line from DB.
 * Calc diff,w1 , and reutrn the PI for this 2 lines.
 * 
 * 
 * @author gal
 *
 */
public class LineAlgo2 {

	public double p = 0 ;
	private double multW = 1;
	private double LAT ,LON , ALT;
	private LinkedList<Wifi> Input = new LinkedList<Wifi>();
	private LinkedList<Wifi> Sample = new LinkedList<Wifi>();
	
	public LineAlgo2(LinkedList<Wifi> Input , LinkedList<Wifi> Sample) {
		this.Input = Input;//The input from no gps file.
		this.Sample = Sample;//Line from DB.
		funcW(this.Sample, this.Input);
	}
	/**
	 * Calculate the Difference between two wifis.
	 * @param w1
	 * @param w2
	 * @return
	 */
	private double funcDiff(Wifi w1 , Wifi w2) {
		return Math.abs(w1.getRSSIAsNum() - w2.getRSSIAsNum());
	}
	/**
	 * Calculate the PI for each Line.
	 * @param oneLineDB
	 * @param input
	 */
	private void funcW(LinkedList<Wifi> oneLineDB , LinkedList<Wifi> input) {
	double tmp = 0;
	boolean flag = false;
		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < oneLineDB.size(); j++) {
				
				this.setLAT (oneLineDB.getFirst().getLAT_double());
				this.setLON (oneLineDB.getFirst().getLON_double());
				this.setALT (oneLineDB.getFirst().getALT_double());
				
				if(input.get(i).getMAC().equals(oneLineDB.get(j).getMAC())) {//if there is a match.
					tmp = funcDiff(input.get(i),oneLineDB.get(j));
					flag = true;
					input.get(i).setWeightAlgoB(tmp);
					this.multW = this.multW *input.get(i).getWeightAlgoB();
				}
			}
			if(!flag) {//if there isnt.
				tmp = Parameters.diffNoSig;
				input.get(i).setRXL(""+Parameters.noSignal);
				input.get(i).setWeightAlgoB(Parameters.diffNoSig);
				this.multW = this.multW * input.get(i).getWeightAlgoB();
			}
		}
		 this.p = multW;
		 
	}
	public double getP() {
		return p;
	}
	public void setP(double p) {
		this.p = p;
	}
	public LinkedList<Wifi> getInput() {
		return Input;
	}
	public void setInput(LinkedList<Wifi> input) {
		Input = input;
	}
	public LinkedList<Wifi> getSample() {
		return Sample;
	}
	public void setSample(LinkedList<Wifi> sample) {
		Sample = sample;
	}
	public double getALT() {
		return ALT;
	}
	public double getLAT() {
		return LAT;
	}
	public void setLAT(double lAT) {
		LAT = lAT;
	}
	public double getLON() {
		return LON;
	}
	public void setLON(double lON) {
		LON = lON;
	}
	public void setALT(double aLT) {
		ALT = aLT;
	}
	
}
