public class Pracownik {
    int employeeId;
    Pracownik(Pracownik pracownik)
    {
        this.employeeId = pracownik.employeeId;
    }
    Pracownik(String login, String password, String forename, String surename,
              String empDate, String earnings, int baseid, String workTime, String nip,
              String account, String DataName, int dataCountryId, String dataDetails, int zipCode, String city, String tel, String mail)
    {

        String query1 = "INSERT INTO data VALUES " +
                "(0,"+DataName+","+dataCountryId+","+dataDetails+","+zipCode+","+city+","+tel+","+mail+")";
        String queryDataId = "SELECT MAX(data_id) FROM data";
        //odpowiedź zapisze do dataId wartość
        int dataId = 1; //tutaj odpowiedź zamiast jedynki
        //trzeba mu przypisać baseId
        String query2 = "INSERT INTO employee VALUES " +
                "(0,"+login+","+password+","+forename+","+surename+","+dataId+","+empDate+","+earnings+","+baseid+","+workTime+","+false+","+nip+","+account+")";
        String queryDEmployeeId = "SELECT MAX(employee_id) FROM employee";
        this.employeeId = 1;//tutaj odpowiedź z serwera.

    }

	public void wybierzTransport(boolean zgoda, Paczka paczka)
    {

	}


	public boolean zbadajPrzesylke(Paczka paczka) {
        return false;
	}

}