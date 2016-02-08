package cscie599.asn1.tickexample;

public class NiceScale {
	
	  private double minPoint;
	  private double maxPoint;
	 
	  /**
	   * Constructor for NiceScale.
	   *
	   * @param min the minimum data point on the axis
	   * @param max the maximum data point on the axis
	   */
	  public NiceScale(double min, double max) {
	    this.minPoint = min;
	    this.maxPoint = max;
	  }
	 
	  /**
	   * Returns a nice rounded number approximately equal to range.
	   *  Rounds the number if round = true, Takes the ceiling if round = false.
	   *
	   * @param range the data range
	   * @param round whether to round the result
	   * @return a "nice" number to be used for the data range
	   */
	  private double niceNum(double range, boolean round) {
	    double exponent; /** exponent of range */
	    double fraction; /** fractional part of range */
	    double niceFraction; /** nice, rounded fraction */

	    exponent = Math.floor(Math.log10(range));
	    fraction = range / Math.pow(10, exponent);

	    if (round) {
	      if (fraction < 1.5)
	        niceFraction = 1;
	      else if (fraction < 3)
	        niceFraction = 2;
	      else if (fraction < 7)
	        niceFraction = 5;
	      else
	        niceFraction = 10;
	    } else {
	      if (fraction <= 1)
	        niceFraction = 1;
	      else if (fraction <= 2)
	        niceFraction = 2;
	      else if (fraction <= 5)
	        niceFraction = 5;
	      else
	        niceFraction = 10;
	    }

	    return niceFraction * Math.pow(10, exponent);
	  }


	public double getTickInterval(double range, double maxTicks) {
		return niceNum(range / (maxTicks - 1), true);
	}


	public double getNiceMin(double interval) {
		return Math.floor(minPoint / interval) * interval;
	}

	
	public double getNiceMax(double interval) {
		return Math.ceil(maxPoint / interval) * interval;
	}

	public double getRange(){
		
		return niceNum(maxPoint - minPoint, false);
	}

}
	  
