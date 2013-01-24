package people;


public class Sender extends Person {


    @Override
    public void followPackage(int packageID) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    public Pack sendPackage(){
          Pack pack = new Pack(10, "Szkło", 5, true, "tralala");  //Przykladowe wartosci
        return pack;
    }
}
