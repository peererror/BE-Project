package com.project.algo;

import com.itextpdf.text.pdf.parser.Matrix;

public class ProjectedTrainingMatrix {
	Matrix matrix;
	String label;
	double distance = 0;

	public ProjectedTrainingMatrix(Matrix m, String l) {
		this.matrix = m;
		this.label = l;
	}
}
