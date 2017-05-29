package Controller;

/**
 * Created by thyge on 18-05-2017.
 */
import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import nexo_quickstart.Util;

/*
 Our controller to establise connection to our Nexmo client account
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
//implements the interface controller to this class with same name
public class Controller implements iController {

    public Controller() {
    }

    //set up the pramaters to connection for the local Nexmo account inorder to sent SMS messages
    public void sendSMS(String number, String msg, String sender) throws Exception {
        Util.configureLogging();
        String API_KEY = "39eca910";
        String API_SECRET = "04ffe9857dab0d6c";
        String TO_NUMBER = "0045" + number;
        TokenAuthMethod auth = new TokenAuthMethod(API_KEY, API_SECRET);
        NexmoClient client = new NexmoClient(new AuthMethod[]{auth});
        System.out.println(sender);
        SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(new TextMessage(sender, TO_NUMBER, msg));
        SmsSubmissionResult[] var11 = responses;
        int var12 = responses.length;

        for(int var13 = 0; var13 < var12; ++var13) {
            SmsSubmissionResult response = var11[var13];
            System.out.println(response);
        }

    }
}

