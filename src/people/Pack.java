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
    public Pack(int userid, int dataid, int sourceid, int returnid, int serviceid, int weightid, int amount, int paymentid, String sdate, int statusid, boolean paid){


        try{
            CachedRowSetImpl tmp = Client.request("SELECT Max(shipmentid) FROM shipment");
            tmp.first();
            this.shipmentid = Integer.parseInt(tmp.getString(1)) + 1;
            Client.request()
        }catch (Exception e){
           e.printStackTrace();
        }

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