abstract class Osoba
{
    private String name;
    private String surname;
    abstract public void zaloguj(String login, String haslo);
	abstract public void sledzPaczke(int idPaczki);
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }


}