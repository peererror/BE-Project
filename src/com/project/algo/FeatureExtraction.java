package com.project.algo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public abstract class FeatureExtraction {
	ArrayList<Matrix> trainingSet;
	ArrayList<String> labels;
	int numOfComponents;
	Matrix meanMatrix;
	Matrix W;
	ArrayList<ProjectedTrainingMatrix> projectedTrainingSet;
	public abstract Matrix getW();
	public abstract ArrayList<ProjectedTrainingMatrix> getProjectedtrainingSet();
	public abstract Matrix getMeanMatrix(); 

	

	public static BufferedImage toCrop(File source) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<ProjectedTrainingMatrix> getProjectedTrainingSet() {
		// TODO Auto-generated method stub
		return null;
	}
	public static BufferedImage featureExtraction(File file) {
		// TODO Auto-generated method stub
		return null;
	}

}
