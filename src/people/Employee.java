package people;

import com.sun.rowset.CachedRowSetImpl;
import exceptions.AlreadyInDbException;

import java.sql.SQLException;

public class Employee extends Person{

    int employeeId;
    Employee(Employee pracownik) throws AlreadyInDbException
    {
        this.employeeId = pracownik.employeeId;
    }
    Employee(String login, String password, String forename, String surename,
              String empDate, String earnings, int baseid, String workTime, String nip,
              String account, String DataName, int dataCountryId, String dataDetails, String zipCode, String city, String tel, String mail) throws AlreadyInDbException {

        try {
            if(Client.request("SELECT * FROM employee WHERE login = '" + login + "'").first())
            {
                throw new AlreadyInDbException();
            }
            else
            {

                Client.request("INSERT INTO data VALUES" +
                        "(0,\""+DataName+"\",\""+dataCountryId+"\",\""+dataDetails+"\",\""+zipCode+"\",\""+city+"\",\""+tel+"\",\""+mail+"\")");

                CachedRowSetImpl tmp = Client.request("SELECT MAX(dataid) FROM data");
                tmp.first();
                int dataId = Integer.parseInt(tmp.getString(1));

                Client.request("INSERT INTO employee VALUES " +
                        "(0,\""+login+"\",\""+password+"\",\""+forename+"\",\""+surename+"\",\""+dataId+"\",\""+empDate+"\",\""+earnings+"\"" +
                        ",\""+baseid+"\",\""+workTime+"\",\"0\",\""+nip+"\",\""+account+"\")");

                tmp = Client.request("SELECT MAX(employeeid) FROM employee");
                tmp.first();

                this.employeeId = Integer.parseInt(tmp.getString(1));//tutaj odpowiedź z serwera.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void followPackage(int packId) {


    }
}