public class Pracownik extends Osoba{

	/**
	 * 
	 * @param zgoda
	 * @param paczka
	 */
	public void wybierzTransport(boolean zgoda, Paczka paczka) {
	}

	/**
	 * 
	 * @param paczka
	 */
	public boolean zbadajPrzesylke(Paczka paczka) {
        return false;
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