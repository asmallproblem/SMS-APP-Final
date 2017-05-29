package Controller;

/*
 Created by Soeren Schou 31-05-2017.
 Created by Magnus Thygesen 31-05-2017.
 Created by Jonas Overgaard 31-05-2017.
*/

//our interface displases a method to be implemented to our controller class
public interface
iController {
    void sendSMS(String var1, String var2, String var3) throws Exception;
}
