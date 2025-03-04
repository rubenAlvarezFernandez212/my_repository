package algstudent.s3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class ClosestPoints {
	private List<Point> points; //class to work with points
	
	public ClosestPoints() {
		this.points = new ArrayList<Point>();
	}
	  
	public void loadPoints(String name) {
		String line = "";
		StringTokenizer pair; //to get the two parts of each line (x and y coordinate of one point)
		BufferedReader reader = null; //to read each line of the file
		try {
			reader = new BufferedReader(new FileReader(name));
			int n = Integer.parseInt(reader.readLine()); //in the first line of the file we have the number of points 
			for (int i=0; i<n; i++) {
				line = reader.readLine();
				pair = new StringTokenizer(line);
				Point point = new Point(Integer.parseInt(pair.nextToken()), Integer.parseInt(pair.nextToken()));
				points.add(point); //points between 0..999999
			}
			reader.close();
		} catch(Exception e) { 
			 System.out.println(e.getMessage()); 
		}
	}
	
	public void loadRandomPoints(int n) {
		Random r = new Random();
		for (int i=0; i<n; i++) {
			int x = r.nextInt(1_000_000);
			int y = r.nextInt(1_000_000);
			points.add(new Point(x, y)); 
		}
	}
	  
	public void printPoints() {
		System.out.println("\n*******************************");
		System.out.println("SIZE OF THE CLOUD OF POINTS = " + points.size());
		System.out.println("POINTS ARE:");
		for (Point point : points)
			System.out.println(point);
		System.out.println();
	}
	
	//SOLUTION USING BRUTE FORCE - O(n^2)
	public Pair bruteForce() {
		return this.bruteForce(this.points);
	}
	
	private Pair bruteForce(List<Point> points) {
		Pair closestPoints = new Pair(points.get(0), points.get(1)); //the 2 first points by default
		closestPoints.setDistance(calculateDistance(points.get(0), points.get(1)));
		
		for (int i=0; i<points.size(); i++) {
			Point p1 = points.get(i);
			for (int j=0; j<points.size(); j++) {
				if (i != j) { //if it is not the same point
					Point p2 = points.get(j);
					double distance = calculateDistance(p1, p2);
					if (distance < closestPoints.getDistance())
						closestPoints.update(p1, p2, distance);
				}
			}
		}
		
		return closestPoints;
	}

	//CALCULATION OF THE DISTANCE BETWEEN 2 POINTS
	private double calculateDistance(Point p1, Point p2) {
		double a = p2.getX() - p1.getX();
		double b = p2.getY() - p1.getY();
		return Math.pow((a+b),2);
	}
	
	//SOLUTION USING DIVIDE AND CONQUER
	public Pair divideAndConquer() {
		Collections.sort(points, new PointComparator());//O(nlogn)
		return divide(0,points.size());
	}

	//a = 2 , b = 2, k = 1 O(nlogn)
	private Pair divide(int left, int right) {
		if (right-left > 3) {//left = 1 and right 5 , 5-1 = 4
			int center = (right +left )/2;
			Pair pairLeft = divide(left, center);
			Pair pairRight = divide(right,center);
			Pair pairCenter = merge(pairLeft, pairRight, left,right);
			return smallestDistance(pairLeft, pairRight, pairCenter);
		}
		return bruteForce(points.subList(left, right));//since this is always the same we can consider it as a constant 
	}

	private Pair smallestDistance(Pair pairLeft, Pair pairRight, Pair pairCenter) {
		if(pairLeft.getDistance() < pairRight.getDistance()) {
			if(pairLeft.getDistance() < pairCenter.getDistance()) {
				return pairLeft;
			}
			else return pairCenter; 
		}
		else if (pairRight.getDistance() < pairCenter.getDistance()) {
			return pairRight;
		}
		else {
			return pairCenter; 
		}
		
	}

	private Pair merge(Pair pairLeft, Pair pairRight, int left, int right) {
		int center = (right + left)/2;
		double min = (pairLeft.getDistance() < pairRight.getDistance() ? pairLeft.getDistance() : pairRight.getDistance());
		Point centralPoint = points.get(center);
		Pair pairCenter = pairLeft;
		List <Point> centralPoints = new ArrayList<Point>();
		
		
		for (Point p : points.subList(center, right)) { // MOVING FROM CENTER TO RIGHT
			if(p.getX() < centralPoint.getX() + min) {
				centralPoints.add(p);
			}
			else break; 
		}
		List <Point> reverseLeft = new ArrayList<Point>(points.subList(left, center));
		Collections.reverse(reverseLeft);
		for(Point p : reverseLeft) {
			if(p.getX() > centralPoint.getX() - min) {
				centralPoints.add(p);
			}
			else break;
		}
		
		if(centralPoints.size() >= 2) {
			pairCenter = bruteForce(centralPoints);
		}
		return pairCenter;
		
		
	}
}
