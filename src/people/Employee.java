package people;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

public class Employee extends Person{

    int employeeId;
    Employee(Employee pracownik)
    {
        this.employeeId = pracownik.employeeId;
    }
    Employee(String login, String password, String forename, String surename,
              String empDate, String earnings, int baseid, String workTime, String nip,
              String account, String DataName, int dataCountryId, String dataDetails, int zipCode, String city, String tel, String mail)
    {

        try {
            Client.request("INSERT INTO data VALUES" +
                    "(0,\""+DataName+"\",\""+dataCountryId+"\",\""+dataDetails+"\",\""+zipCode+"\",\""+city+"\",\""+tel+"\",\""+mail+"\")");

            CachedRowSetImpl tmp = Client.request("SELECT MAX(dataid) FROM data");
            tmp.first();
            int dataId = Integer.parseInt(tmp.getString(1));

            Client.request("INSERT INTO employee VALUES \" +\n" +
                    "\"(0,\""+login+"\",\""+password+"\",\""+forename+"\",\""+surename+"\",\""+dataId+"\",\""+empDate+"\",\""+earnings+"\"" +
                    ",\""+baseid+"\",\""+workTime+"\",\"false\",\""+nip+"\",\""+account+"\")");

            tmp = Client.request("SELECT MAX(employeeid) FROM employee");
            tmp.first();

            this.employeeId = Integer.parseInt(tmp.getString(1));//tutaj odpowiedź z serwera.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    @Override
    public void followPackage(int packId) {
        //To change body of implemented methods use File | Settings | File Templates.

    }
}