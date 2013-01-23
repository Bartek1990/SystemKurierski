public class Nadawca extends Osoba
{
    String password, login;
    int NIP;
    public Nadawca(Osoba osoba, String login, String password)
    {
        super(osoba.name, osoba.surname);
        this.login = login;
        this.password = password;
    }
    public Nadawca(Osoba osoba, String login, String password, int NIP)
    {
        super(osoba.name, osoba.surname);
        this.login = login;
        this.password = password;
        this.NIP = NIP;
    }

	public Paczka nadajPaczke()
    {
		Paczka paczka = new Paczka(1000, "prostytutki", 100, true, "w drodze kurwa");
        return paczka;
	}

}