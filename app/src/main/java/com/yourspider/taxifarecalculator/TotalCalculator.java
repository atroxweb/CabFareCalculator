package com.yourspider.taxifarecalculator;

public class TotalCalculator {

	double base;
	double dis;
	double tm;
	double perKm;
	double perMin;
	double total;
	public TotalCalculator(double disF, double tmF, double baseF, double perMinF, double perKmF)
	{
		base = baseF;
	    perKm = perKmF;
		perMin = perMinF;
		dis = disF;
		tm = tmF;
		total=0.0;
	}
	
	public double totalCal()
	{
      total = base + (tm/60)*perMin + (dis/1000)*perKm;
      return total;
	}
}
