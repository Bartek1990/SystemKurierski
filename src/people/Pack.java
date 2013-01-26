package people;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

public class Pack
{
    int shipmentid;
	int weight;
	String content;
	int size;
	boolean priority;
	String status;
    public Pack(int recipentId, int senderId, int serviceId, int weightId, int amount, int paymentId, int statusId, boolean paid)
    {


        Client.request("INSERT INTO `pds`.`shipment` (`shipmentid`, `dataid`, `sourceid`, `returnid`, `serviceid`, " +
                "`weightid`, `amount`, `paymentid`, `sdate`, `ddate`, `statusid`, `paid`) VALUES (NULL, \'"+recipentId+"\', \'"+senderId+"\', \'"+senderId+"\'" +
                ", \'"+serviceId+"\', \'"+weightId+"\', \'"+amount+"\', \'"+paymentId+"\', " +
                "CURRENT_TIMESTAMP, '0000-00-00 00:00:00', \'"+statusId+"\', \'"+paid+"\');");

    }
    public Pack(int weight, String content, int size, boolean priority, String status)
    {
        this.weight = weight;
        this.content = content;
        this.size = size;
        this.priority = priority;
        this.status = status;


    }
    public Pack(Pack pack){
        this.shipmentid = pack.shipmentid;
    }
}