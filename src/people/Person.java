package people;

public abstract class Person {
    public void logIn(String login, String password) throws Exception{
        if(login.equals("") || password.equals("")){
                throw new Exception();
        }
        else{
            Client.request("SELECT login, password FROM user where login=" + login + " AND password=" + password);

        }
    }
}
