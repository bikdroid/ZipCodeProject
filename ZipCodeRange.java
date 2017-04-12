import java.util.List;

public class ZipCodeRange {
	ZipCode lower;
	ZipCode upper;

	public ZipCode getLower() {
		return this.lower;
	}

	public ZipCode getUpper() {
		return this.upper;
	}

	public void setLower(ZipCode lower) {
		this.lower = lower;
	}

	public void setUpper(ZipCode upper) {
		this.upper = upper;
	}

	public ZipCodeRange(ZipCode lower, ZipCode upper) {
		this.lower = lower;
		this.upper = upper;
	}

	public int compareLowers(ZipCodeRange z) { // compare to the lower limit of
												// the argument range
		if (this.lower.compare(z.lower) > 0) {
			return 1;
		} else if (this.lower.compare(z.lower) < 0) {
			return -1;
		} else {
			return 0;
		}
	}

	public int compareUppers(ZipCodeRange z) { // compare to the upper limit of
												// the argument range

		if (this.upper.compare(z.upper) > 0) {
			return 1;
		} else if (this.upper.compare(z.upper) < 0) {
			return -1;
		} else {
			return 0;
		}
	}

}
