
public class ZipCode {

	Integer zipcode;

	public ZipCode(Integer zip) {
		this.zipcode = zip;
	}

	public int compare(ZipCode z) {
		if (this.zipcode.equals(z.zipcode))
			return 0;
		else if (this.zipcode.compareTo(z.zipcode) < 0)
			return -1;
		else {
			return 1;
		}
	}

}
