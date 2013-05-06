package model;

public class BucketForCircles implements Comparable<BucketForCircles> {
	double a;
	double b;
	double r;
	int hits;

	public BucketForCircles(double a, double b, double r, int hits) {
		this.a = a;
		this.b = b;
		this.r = r;
		this.hits = hits;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equalA = a == ((BucketForCircles) obj).a;
		boolean equalB = b == ((BucketForCircles) obj).b;
		boolean equalR = r == ((BucketForCircles) obj).r;
		return equalA && equalB && equalR;
	}

	@Override
	public int hashCode() {
		return (int) (3 * a + 5 * b + 7 * r);
	}

	@Override
	public int compareTo(BucketForCircles obj) {
		return obj.hits - hits;
	}

	@Override
	public String toString() {
		return "A: " + a + " B: " + b + " R: " + r + " Hits: " + hits;
	}

}