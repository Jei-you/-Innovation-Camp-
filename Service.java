public class Service {

    public static void main(String[] args) {
    Bus bus1 = new Bus(1234);
    Bus bus2 = new Bus(5678);

    bus1.take(5);
    bus1.speedChange(50);
    bus1.statusChange(95);
    bus1.showInfo();

    //택시
    Taxi taxi1 = new Taxi(1234);
    Taxi taxi2 = new Taxi(5678);
    taxi1.take(2);
    taxi1.Fee(2);
    taxi1.statusChange(100);
    taxi1.speedChange(6);
    taxi1.showInfo();

    }
}