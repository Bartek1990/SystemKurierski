package people;
     //Kolejny komentarz
import com.sun.rowset.CachedRowSetImpl;
import exceptions.AlreadyInDbException;

import java.sql.SQLException;

public class Courier extends Employee {


    private int courierID;
    private Pack pack;
    public Courier(Employee courier, String companyPhoneNumber) throws AlreadyInDbException {
        super(courier);
        this.courierID = courier.employeeId;
        Client.request("INSERT INTO courier VALUES" +
                "(0, \""+this.employeeId+"\", null, null,\""+companyPhoneNumber+"\")");


    }
	public void setStatus() {

        //wpisz tutaj nowy status wybrany z dostępnych!
        String status = null;

        CachedRowSetImpl tmp = Client.request("SELECT * FROM status WHERE title = \'" + status + "\'");
        try {
            Client.request("UPDATE pack SET statusid = \'"+tmp.getString("statusid")+"\' WHERE shipmentid = \'"+this.pack.shipmentId+"\'");
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
    public void takePackToDelivery(Pack pack)
    {
        this.pack = pack;
    }

    public Pack givePackToRecipent()
    {
            String status = null;
            Pack toReturn = this.pack;
            CachedRowSetImpl tmp = Client.request("SELECT * FROM status WHERE title = 'Zakonczona'");
            try {
                Client.request("UPDATE pack SET statusid = \'"+tmp.getString("statusid")+"\' WHERE shipmentid = \'"+pack.shipmentId+"\'");
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            this.pack = null;
            return toReturn;
        //wpisz tutaj nowy status wybrany z dostępnych!

    }

}