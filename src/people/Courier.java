package people;
     //Kolejny komentarz
import exceptions.AlreadyInDbException;

public class Courier extends Employee {


    private int courierID;

    public Courier(Employee courier, String companyPhoneNumber) throws AlreadyInDbException {
        super(courier);
        this.courierID = courier.employeeId;
        Client.request("INSERT INTO courier VALUES" +
                "(0, \""+this.employeeId+"\", null, null,\""+companyPhoneNumber+"\")");


    }
	public void setStatus(Package pack) {

	}

}