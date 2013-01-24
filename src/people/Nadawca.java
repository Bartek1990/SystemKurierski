package people;

public class Nadawca  extends Osoba
{
    String password, login;
    int NIP;
    public Nadawca(String name, String surname, String login, String password)
    {
        setName(name);
        setSurname(surname);
        this.login = login;
        this.password = password;
    }
    public Nadawca(String name, String surname, String login, String password, int NIP)
    {
        setName(name);
        setSurname(surname);
        this.login = login;
        this.password = password;
        this.NIP = NIP;
    }

	public Paczka nadajPaczke()
    {
		Paczka paczka = new Paczka(1000, "prostytutki", 100, true, "w drodze kurwa");
        return paczka;
	}

    @Override
    public void zaloguj(String login, String haslo) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sledzPaczke(int idPaczki) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}