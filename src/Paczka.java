public class Paczka
{
	int masa;
	String zawartosc;
	int rozmiar;
	boolean priorytet;
	String status;
    public Paczka(int masa, String zawartosc, int rozmiar, boolean priorytet, String status)
    {
        this.masa = masa;
        this.zawartosc = zawartosc;
        this.rozmiar = rozmiar;
        this.priorytet = priorytet;
        this.status = status;
    }
}