package people;

import exceptions.AlreadyInDbException;

public class Courier extends Employee {


    private int courierID;

    public Courier(Employee courier) throws AlreadyInDbException {
        super(courier);


    }
	public void setStatus(Package pack) {

	}

}