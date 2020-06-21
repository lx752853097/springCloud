import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Date;

public class Test0 {

    @Test
    public void testDate(){
        Date date = new Date();
        ZonedDateTime zonedDateTime =  ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
