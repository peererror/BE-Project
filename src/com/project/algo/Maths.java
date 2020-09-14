package com.project.algo;

public class Maths {

	public static double hypot(double p, double d) {
		// TODO Auto-generated method stub
		double r;
		if(Math.abs(p) > Math.abs(d)){
			r=d/p;
           r=Math.abs(p)*Math.sqrt(1+r*r);
           
		}else if (d !=0){
			r=p/d;
			r=Math.abs(d)*Math.sqrt(1+r*r);
		}else{
			r=0.0;
		}
				
				
				
				
				
				
				
				return r;
	}

}
