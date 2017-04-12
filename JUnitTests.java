import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class JUnitTests {

	public List<ZipCodeRange> requiredList = new ArrayList<ZipCodeRange>();

	@Before
	public void setup() {
		System.out.println("Setup");
		this.requiredList = new ArrayList<ZipCodeRange>();
	}

	@After
	public void climax() {
		System.out.println("Climax");
		this.requiredList.clear();

	}

	@Test
	public void abctest() {

		setup();
		System.out.println("abctest");

		ZipCodeRange z1 = new ZipCodeRange(new ZipCode(94133), new ZipCode(94133));
		ZipCodeRange z2 = new ZipCodeRange(new ZipCode(94200), new ZipCode(94299));
		ZipCodeRange z3 = new ZipCodeRange(new ZipCode(94226), new ZipCode(94399));
		

		// Testing
		ZipRangeControl zrc = new ZipRangeControl();
		zrc.processNewRange(z1);
		zrc.processNewRange(z2);
		zrc.processNewRange(z3);

		/* Size */
		Assert.assertEquals("Test failed for expected returned list size (2)", 2, zrc.getFinalZipRanges().size());

		climax();

	}

	@Test
	public void rangeOverlapTest() { /* Range OverLap Test */

		setup();
		System.out.println("overlaptest");
		// dataset 1
		ZipCodeRange z1 = new ZipCodeRange(new ZipCode(94133), new ZipCode(94133));
		ZipCodeRange z2 = new ZipCodeRange(new ZipCode(94200), new ZipCode(94299));
		ZipCodeRange z3 = new ZipCodeRange(new ZipCode(94226), new ZipCode(94399));

		// Testing
		ZipRangeControl zrc = new ZipRangeControl();
		zrc.processNewRange(z1);
		zrc.processNewRange(z2);
		zrc.processNewRange(z3);

		ZipCodeRange z5 = new ZipCodeRange(new ZipCode(94133), new ZipCode(94133));
		ZipCodeRange z4 = new ZipCodeRange(new ZipCode(94200), new ZipCode(94399));
		requiredList.add(z5);
		requiredList.add(z4);

		for (ZipCodeRange z : zrc.getFinalZipRanges()) {
			System.out.println(z.lower.zipcode + "," + z.upper.zipcode);
		}
		for (ZipCodeRange z : requiredList) {
			System.out.println(z.lower.zipcode + "," + z.upper.zipcode);
		}

		Assert.assertEquals("Overlap test Size check passed ", requiredList.size(), zrc.getFinalZipRanges().size());

		ZipCodeRange[] ziparray = new ZipCodeRange[requiredList.size()];
		List<ZipCodeRange> zlist = zrc.getFinalZipRanges();

		for (int i = 0; i < requiredList.size(); i++) {
			Assert.assertThat("Lower Limit match failed at entry" + i, requiredList.get(i).lower.zipcode,
					org.hamcrest.CoreMatchers.equalTo(zlist.get(i).lower.zipcode));
			Assert.assertThat("Upper Limit match failed at entry" + i, requiredList.get(i).upper.zipcode,
					org.hamcrest.CoreMatchers.equalTo(zlist.get(i).upper.zipcode));
		}

		climax();

	}

	@Test
	public void seperateRangeTest() { /* Range Seperate Test */

		setup();
		System.out.println("seperate range test");
		ZipCodeRange z1 = new ZipCodeRange(new ZipCode(94133), new ZipCode(94133));
		ZipCodeRange z2 = new ZipCodeRange(new ZipCode(94200), new ZipCode(94299));
		ZipCodeRange z3 = new ZipCodeRange(new ZipCode(94600), new ZipCode(94699));

		// Testing
		ZipRangeControl zrc = new ZipRangeControl();
		zrc.processNewRange(z1);
		zrc.processNewRange(z2);
		zrc.processNewRange(z3);

		ZipCodeRange z4 = new ZipCodeRange(new ZipCode(94200), new ZipCode(94399));
		requiredList.add(z1);
		requiredList.add(z2);
		requiredList.add(z3);

		ZipCodeRange[] ziparray = new ZipCodeRange[requiredList.size()];
		List<ZipCodeRange> zlist = zrc.getFinalZipRanges();

		for (int i = 0; i < requiredList.size(); i++) {
			Assert.assertThat("Lower Limit match failed at entry" + i, requiredList.get(i).lower.zipcode,
					org.hamcrest.CoreMatchers.equalTo(zlist.get(i).lower.zipcode));
			Assert.assertThat("Upper Limit match failed at entry" + i, requiredList.get(i).upper.zipcode,
					org.hamcrest.CoreMatchers.equalTo(zlist.get(i).upper.zipcode));
		}
		climax();

	}

}
