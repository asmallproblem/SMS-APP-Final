package nexo_quickstart;

/**
 * Created by thyge on 18-05-2017.
 */
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 The Util client for Nexmo, its let his confirm
 if the TLF number is valid or not
*/
/*
 Created by Soeren Schou 31-05-2017.
 Created by Magnus Thygesen 31-05-2017.
 Created by Jonas Overgaard 31-05-2017.
*/

/*
 Some code (most)have been from Nexmo on git-hub about there SMS API appllication
 we have been using inorder to use send SMS messages

 https://github.com/Nexmo/nexmo-java
*/
public class Util
{
    public Util()
    {
    }

    public static void configureLogging()
    {
        //initializing a value, and check if connection is legal
        String value = System.getenv("QUICKSTART_DEBUG");
        if(value != null)
        {
            ConsoleHandler handler = new ConsoleHandler();
            handler.setLevel(Level.FINEST);
            Logger logger = Logger.getLogger("com.nexmo");
            logger.setLevel(Level.FINEST);
            logger.addHandler(handler);
        }

    }

    public static String envVar(String key)
    {
        //make a exception to user to provide a legal number
        String value = System.getenv(key);
        if(value == null)
        {
            throw new IllegalArgumentException("You must provide the " + key + " environment variable!");
        } else
        {
            return value;
        }
    }
}
