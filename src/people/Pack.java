package people;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

public class Pack
{
    int shipmentId;
	int weightId;
	int serviceId;
	int statusId;

    //chciałbym żeby nikt nie znał konkretnych id, ma to być wyszukiwane programowo, dlatego tutaj tego nawet nie sprawdzam tylko ładuje do bazy!
    public Pack(int recipentId, int senderId, int serviceId, int weightId, int amount, int paymentId, int statusId, int paid)
    {
        Client.request("INSERT INTO `pds`.`shipment` (`shipmentid`, `dataid`, `sourceid`, `returnid`, `serviceid`, " +
                "`weightid`, `amount`, `paymentid`, `sdate`, `ddate`, `statusid`, `paid`) VALUES (NULL, \'"+recipentId+"\', \'"+senderId+"\', \'"+senderId+"\'" +
                ", \'"+serviceId+"\', \'"+weightId+"\', \'"+amount+"\', \'"+paymentId+"\', " +
                "CURRENT_TIMESTAMP, '0000-00-00 00:00:00', \'"+statusId+"\', \'"+paid+"\');");

        CachedRowSetImpl tmp = Client.request("SELECT MAX(shipmentid) FROM shipment");
        try {

            this.shipmentId = Integer.parseInt(tmp.getString(1));
            this.weightId = weightId;
            this.serviceId = serviceId;
            this.statusId = statusId;

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
    //konstruktor kopiujący
    public Pack(Pack pack)
    {
        this.shipmentId = pack.shipmentId;
        this.weightId = pack.weightId;
        this.serviceId = pack.serviceId;
        this.statusId = pack.statusId;
    }
}