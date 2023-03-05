import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ExitOn {
Timer timer = new Timer();
TimerTask exitApp = new TimerTask() {
    @Override
    public void run() {
        System.exit(0);
    }
};
public ExitOn() {
timer.schedule(exitApp, new Date(System.currentTimeMillis()+5*1000));//Exits after 5sec of starting the app
while(true)
    System.out.println("hello");
}
public static void sum ()
{
	System.out.println(1+2);
}

while (System.currentTimeMillis() < end) {
    // Some expensive operation on the item.
}



public static void main(String[] args) {
	long startTime = System.currentTimeMillis();
sum();
	long endTime = System.currentTimeMillis();
	System.out.println("That took " + (endTime - startTime) + " milliseconds");


}}