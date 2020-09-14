package com.project.algo;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;


public class PCA extends FeatureExtraction {

	
	int smw = 0;
	int smh = 0;
	public PCA()
	{}
	public PCA(ArrayList<Matrix> trainingSet, ArrayList<String> labels,
			   int numOfComponents) throws Exception {
		
		if(numOfComponents >= trainingSet.size()){
			throw new Exception("The expected dimensions could not be achieved!");
		}
		
		this.trainingSet = trainingSet;
		this.labels = labels;
		this.numOfComponents = numOfComponents;

		//this.meanMatrix = getMean(this.trainingSet);
		this.W = getFeature();

		// Construct projectedTrainingMatrix
		this.projectedTrainingSet = new ArrayList<ProjectedTrainingMatrix>();
		
		for (int i = 0; i < trainingSet.size(); i++) {
			
			ProjectedTrainingMatrix ptm = new ProjectedTrainingMatrix(this.W.transpose().times(trainingSet.get(i).minus(meanMatrix)),
					labels.get(i));
			this.projectedTrainingSet.add(ptm);
		}
	}

	// extract features, namely W
	private Matrix getFeature() {
		int i, j;
		ArrayList<Matrix> input=null;
		int row = input.get(0).getRowDimension();
		int column = input.size();
		Matrix X = new Matrix(row, column);

		for (i = 0; i < column; i++) {
			X.setMatrix(0, row - 1, i, i, input.get(i).minus(this.meanMatrix));
		}

		// get eigenvalues and eigenvectors
		Matrix XT = X.transpose();
		Matrix XTX = XT.times(X);
		
		EigenvalueDecomposition feature = XTX.eig();
		double[] d = feature.getd();

		//assert d.length >= K : "number of eigenvalues is less than K";
		int[] indexes = this.getIndexesOfKEigenvalues(d);

		Matrix eigenVectors = X.times(feature.getV());
		Matrix selectedEigenVectors = eigenVectors.getMatrix(0,
				eigenVectors.getRowDimension() - 1, indexes);

		// normalize the eigenvectors
		row = selectedEigenVectors.getRowDimension();
		column = selectedEigenVectors.getColumnDimension();
		for (i = 0; i < column; i++) {
			double temp = 0;
			for (j = 0; j < row; j++)
				temp += Math.pow(selectedEigenVectors.get(j, i), 2);
			temp = Math.sqrt(temp);

			for (j = 0; j < row; j++) {
				selectedEigenVectors.set(j, i, selectedEigenVectors.get(j, i) / temp);
			}
		}

		return selectedEigenVectors;

	}

	// get the first K indexes with the highest eigenValues
	private class mix implements Comparable {
		int index;
		double value;

		mix(int i, double v) {
			index = i;
			value = v;
		}

		public int compareTo(Object o) {
			double target = ((mix) o).value;
			if (value > target)
				return -1;
			else if (value < target)
				return 1;

			return 0;
		}
	}
	
	public static boolean identity(String file1,String file2) throws InterruptedException
	{
		Boolean resultStatus=Boolean.FALSE;
		long start = System.currentTimeMillis();
		//String file11="D:/upload/facedetect.jpg";
		//getFeature();
		//String file12="D:/upload/facedetect.jpg";
		File file = new File(file1);
		Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
		Image image2 = Toolkit.getDefaultToolkit().getImage(file2);
		
		
		BufferedImage image;
		try {
			image = ImageIO.read(file);
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			int[][] clr = new int[width][height];
			File files = new File(file2);
			BufferedImage images = ImageIO.read(files);
			int widthe = images.getWidth(null);
			int heighte = images.getHeight(null);
			int[][] clre = new int[widthe][heighte];
			int smw = 0;
			int smh = 0;
			int p = 0;
			// CALUCLATING THE SMALLEST VALUE AMONG WIDTH AND HEIGHT
			if (width > widthe) {
				smw = widthe;
			} else {
				smw = width;
			}
			if (height > heighte) {
				smh = heighte;
			} else {
				smh = height;
			}
			// CHECKING NUMBER OF PIXELS SIMILARITY
			for (int a = 0; a < smw; a++) {
				for (int b = 0; b < smh; b++) {
					clre[a][b] = images.getRGB(a, b);
					clr[a][b] = image.getRGB(a, b);
					if (clr[a][b] == clre[a][b]) {
						p = p + 1;
					}
				}
			}

			float w, h = 0;
			if (width > widthe) {
				w = width;
			} else {
				w = widthe;
			}
			if (height > heighte) {
				h = height;
			} else {
				h = heighte;
			}
			float s = (smw * smh);
			// CALCULATING PERCENTAGE
			
			float x = (100 * p) / s;

			System.out.println("THE PERCENTAGE SIMILARITY IS APPROXIMATELY =" + x
					+ "%");
			if(x>=60)
			{
				resultStatus=Boolean.TRUE;
			}
			long stop = System.currentTimeMillis();
			System.out.println("TIME TAKEN IS =" + (stop - start));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PixelGrabber grab1 =new PixelGrabber(image1, 0, 0, -1, -1, false);
		PixelGrabber grab2 =new PixelGrabber(image2, 0, 0, -1, -1, false);
		 
		int[] data1 = null;
		 
		if (grab1.grabPixels()) {
		int width = grab1.getWidth();
		int height = grab1.getHeight();
		data1 = new int[width * height];
		//data1 = (int[]) grab1.getPixels();
		}
		 
		int[] data2 = null;
		 
		if (grab2.grabPixels()) {
		int width = grab2.getWidth();
		int height = grab2.getHeight();
		data2 = new int[width * height];
		//data2 = (int[]) grab2.getPixels();
		}
		java.util.Arrays.equals(data1, data2);
		System.out.println("Pixels equal" );
		return resultStatus;
	}

		public static boolean retinaIdentification(String file1,String file2)
	{
		Boolean resultStatus=Boolean.FALSE;
		long start = System.currentTimeMillis();
		//String file11="D:/upload/retinadetect.jpg";
		
		//String file12="D:/upload/facedetect.jpg";
		File file = new File(file1);
		
		BufferedImage image;
		try {
			image = ImageIO.read(file);
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			int[][] clr = new int[width][height];
			File files = new File(file2);
			BufferedImage images = ImageIO.read(files);
			int widthe = images.getWidth(null);
			int heighte = images.getHeight(null);
			int[][] clre = new int[widthe][heighte];
			int smw = 0;
			int smh = 0;
			int p = 0;
			// CALUCLATING THE SMALLEST VALUE AMONG WIDTH AND HEIGHT
			if (width > widthe) {
				smw = widthe;
			} else {
				smw = width;
			}
			if (height > heighte) {
				smh = heighte;
			} else {
				smh = height;
			}
			// CHECKING NUMBER OF PIXELS SIMILARITY
			for (int a = 0; a < smw; a++) {
				for (int b = 0; b < smh; b++) {
					clre[a][b] = images.getRGB(a, b);
					clr[a][b] = image.getRGB(a, b);
					if (clr[a][b] == clre[a][b]) {
						p = p + 1;
					}
				}
			}

			float w, h = 0;
			if (width > widthe) {
				w = width;
			} else {
				w = widthe;
			}
			if (height > heighte) {
				h = height;
			} else {
				h = heighte;
			}
			float s = (smw * smh);
			// CALCULATING PERCENTAGE
			float x = (100 * p) / s;

			System.out.println("THE PERCENTAGE SIMILARITY IS APPROXIMATELY =" + x
					+ "%");
			if(x>=60)
			{
				resultStatus=Boolean.TRUE;
			}
			long stop = System.currentTimeMillis();
			System.out.println("TIME TAKEN IS =" + (stop - start));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultStatus;
	}
	private int[] getIndexesOfKEigenvalues(double[] d) {
		mix[] mixes = new mix[d.length];
		int i;
		int k=0;
		for (i = 0; i < d.length; i++)
			mixes[i] = new mix(i, d[i]);

		Arrays.sort(mixes);

		int[] result = new int[k];
		for (i = 0; i < k; i++)
			result[i] = mixes[i].index;
		return result;
	}

	// The matrix has already been vectorized
	private static Matrix getMean(ArrayList<Matrix> input) {
		int rows = input.get(0).getRowDimension();
		int length = input.size();
		Matrix all = new Matrix(rows, 1);

		for (int i = 0; i < length; i++) {
			all.plusEquals(input.get(i));
		}

		return all.times((double) 1 / length);
	}

	@Override
	public Matrix getW() {
		return this.W;
	}

	@Override
	public ArrayList<ProjectedTrainingMatrix> getProjectedTrainingSet() {
		return this.projectedTrainingSet;
	}
	
	@Override
	public Matrix getMeanMatrix() {
		// TODO Auto-generated method stub
		return meanMatrix;
	}
	
	public ArrayList<Matrix> getTrainingSet(){
		return this.trainingSet;
	}
	
	public Matrix reconstruct(int whichImage, int dimensions) throws Exception{
		if(dimensions > this.numOfComponents)
			throw new Exception("dimensions should be smaller than the number of components");
		
		Matrix afterPCA = this.projectedTrainingSet.get(whichImage).matrix.getMatrix(0, dimensions-1, 0, 0);
		Matrix eigen = this.getW().getMatrix(0, 10304-1, 0, dimensions - 1);
		//System.out.println("Face Matched");
		return eigen.times(afterPCA).plus(this.getMeanMatrix());
		
	}
	public void imagecluster(int k, BufferedImage filterImg, String[] dstpath) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<ProjectedTrainingMatrix> getProjectedtrainingSet() {
		// TODO Auto-generated method stub
		return null;
	}
     

}
