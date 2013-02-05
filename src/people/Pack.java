package people;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

public class Pack
{
    int shipmentId;
	int weightId;
	int serviceId;
	int statusId;
    int recipentId;
    //pełny konstruktor
    public Pack(int recipentId, int senderId, int serviceId, int weightId, int amount, int paymentId, int statusId, int paid)
    {
        Client.request("INSERT INTO `pds`.`shipment` (`shipmentid`, `dataid`, `sourceid`, `returnid`, `serviceid`, " +
                "`weightid`, `amount`, `paymentid`, `sdate`, `ddate`, `statusid`, `paid`) VALUES (NULL, \'"+recipentId+"\', \'"+senderId+"\', \'"+senderId+"\'" +
                ", \'"+serviceId+"\', \'"+weightId+"\', \'"+amount+"\', \'"+paymentId+"\', " +
                "CURRENT_TIMESTAMP, \'"+statusId+"\', \'"+paid+"\');");

        CachedRowSetImpl tmp = Client.request("SELECT MAX(shipmentid) FROM shipment");

        try {
            tmp.first();
            this.shipmentId = Integer.parseInt(tmp.getString(1));
            this.weightId = weightId;
            this.serviceId = serviceId;
            this.statusId = statusId;
            this.recipentId = recipentId;
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
    public Pack(int recipentId, int senderId, int amount)
    {
        int serviceId = 2; //id stałe gdyż wyłącznie jedna opcja dostawy
        int paymentId = 1; //tylko jeden sposób zapłaty
        System.out.println("AAAA");
        Client.request("INSERT INTO `pds`.`shipment` (`shipmentid`, `dataid`, `sourceid`, `returnid`, `serviceid`, " +
                "`weightid`, `amount`, `paymentid`, `sdate`, `statusid`, `paid`) VALUES (0, \'"+recipentId+"\', \'"+senderId+"\', \'"+senderId+"\'" +
                ", \'"+serviceId+"\', NULL, \'"+amount+"\', \'"+paymentId+"\', " +
                "CURRENT_TIMESTAMP, \'1\', NULL);");          //przy tworzeniu paczki status automatycznie ustawiany na 1

        /*
        Client.request("INSERT INTO `pds`.`shipment` (`shipmentid`, `dataid`, `sourceid`, `returnid`, `serviceid`, " +
                "`weightid`, `amount`, `paymentid`, `sdate`, `ddate`, `statusid`, `paid`) VALUES (0, \'"+recipentId+"\', \'"+senderId+"\', \'"+senderId+"\'" +
                ", \'"+serviceId+"\', NULL, \'"+amount+"\', \'"+paymentId+"\', " +
                "CURRENT_TIMESTAMP,TUTAJ COS, \'1\', NULL);");          //przy tworzeniu paczki status automatycznie ustawiany na 1
         */
        //(0, '22', '9', '9', '1', NULL, '1', '1', CURRENT_TIMESTAMP, '1', NULL)
        CachedRowSetImpl tmp = Client.request("SELECT MAX(shipmentid) FROM shipment");

        try {
            tmp.first();
            this.shipmentId = Integer.parseInt(tmp.getString(1));

            this.serviceId = serviceId;

            this.recipentId = recipentId;
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
    void setParam(int weightId, int statusId, int paid)
    {
        this.weightId = weightId;
        this.statusId = statusId;
    }
}