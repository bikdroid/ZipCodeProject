import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZipRangeControl {

	List<ZipCodeRange> ziplist = new ArrayList<ZipCodeRange>();

	public void processNewRange(ZipCodeRange zrange) { // adds a new zip code
														// range to the
														// collection.

		if (ziplist.size() != 0) {
			Iterator it = ziplist.iterator();

			boolean addrange = false;
			while (it.hasNext()) {

				ZipCodeRange zr = (ZipCodeRange) it.next();

				/* make comparisons to this range */
				if (zr.compareLowers(zrange) <= 0 && zr.compareUppers(zrange) >= 0) {
					addrange = false;
				}

				/* if new range is separate from given range, add it */
				else if (zr.lower.compare(zrange.upper) > 0 || zr.upper.compare(zrange.lower) < 0) {
					addrange = true;
				} else {

					/*
					 * if new range is partially in the given range, we update
					 * the upper and lower of new range, add it
					 */

					/* Overlapping */
					if (zr.compareLowers(zrange) < 0) {
						zrange.setLower(zr.getLower()); // change the zrange
														// lower
														// limit
					}
					if (zr.compareUppers(zrange) > 0) {
						zrange.setUpper(zr.getUpper()); // change the zrange
														// upper
														// limit
					}

					addrange = true;
					it.remove();

				}

			}
			if (addrange == true) {
				ziplist.add(zrange);
			}
		} else {

			// add the new range to the list
			ziplist.add(zrange);
		}
	}

	public List<ZipCodeRange> getFinalZipRanges() {
		return this.ziplist;
	}

	public static void main(String args[]) {
		List<String> list = new ArrayList<String>();
		List<ZipCodeRange> ziplist = new ArrayList<ZipCodeRange>();

		System.out.println("---DataSet 1---");
		Integer[][] rangeinput = { { 94133, 94133 }, { 94200, 94299 }, { 94226, 94399 }

		};

		for (int i = 0; i < rangeinput.length; i++) {
			ZipCodeRange z1 = new ZipCodeRange(new ZipCode(rangeinput[i][0]), new ZipCode(rangeinput[i][1]));
			ziplist.add(i, z1);
		}

		ZipRangeControl control = new ZipRangeControl();
		for (ZipCodeRange z : ziplist) {
			control.processNewRange(z);

		}

		List<ZipCodeRange> zlist = control.getFinalZipRanges();
		for (ZipCodeRange z : zlist) {
			System.out.println("[" + z.lower.zipcode + "," + z.upper.zipcode + "]");
		}

		System.out.println("\n---DataSet 2---");
		Integer[][] rangeinput2 = { { 94133, 94133 }, { 94200, 94299 }, { 94600, 94699 }

		};

		ziplist.clear();
		for (int i = 0; i < rangeinput2.length; i++) {
			ZipCodeRange z1 = new ZipCodeRange(new ZipCode(rangeinput2[i][0]), new ZipCode(rangeinput2[i][1]));
			ziplist.add(i, z1);
		}
		ZipCodeRange z3 = new ZipCodeRange(new ZipCode(94600), new ZipCode(94699));

		control = new ZipRangeControl();
		for (ZipCodeRange z : ziplist) {
			control.processNewRange(z);
		}

		zlist = control.getFinalZipRanges();
		for (ZipCodeRange z : zlist) {
			System.out.println("[" + z.lower.zipcode + "," + z.upper.zipcode + "]");
		}

	}

}
