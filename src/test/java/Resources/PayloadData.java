package Resources;

import java.util.ArrayList;

import com.github.javafaker.Faker;

import RequestPojo.ChildDataRequest;
import RequestPojo.PlaceDataRequest;

public class PayloadData {

	PlaceDataRequest p = new PlaceDataRequest();
	ChildDataRequest child = new ChildDataRequest();
	Faker f=new Faker();

	public PlaceDataRequest DataPayload(String name,String Pnumber,String Address) {

		child.setLat(-38.383494);
		child.setLng(33.427362);

		p.setAccuracy(50);
		p.setLocation(child);
		//p.setName(f.name().firstName());
		p.setName(name);
		//p.setPhone_number("43578899");
		p.setPhone_number(Pnumber);
		//p.setAddress(f.address().city());
		p.setAddress(Address);

		ArrayList<String> list = new ArrayList<>();

		list.add("shoe park");
		list.add("shop");

		p.setTypes(list);
		p.setWebsite("http://google.com");
		p.setLanguage("Odia");
		return p;

	}

}
