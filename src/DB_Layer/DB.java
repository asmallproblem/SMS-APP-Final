package DB_Layer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
/*
 Our DB to the SMS application
 used for both get the size of the users
 but also pick the lucky vinder
*/

/*
 Created by Soeren Schou 31-05-2017.
 Created by Magnus Thygesen 31-05-2017.
 Created by Jonas Overgaard 31-05-2017.
*/
public class DB
{
    //Setting up fields
    private int currentContest;
    private int vinder;
    public ArrayList<Integer> deltagerer = new ArrayList<>();


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public void readToArray ()
    {
        //STEP 1: set Connection to null
        Connection conn = null;
        Statement stmt = null;
        try
        {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");

            String DB_Url = System.getProperty("JDBC_CONNECTION_STRING");
            String DB_User = System.getProperty("JDBC_USER");
            String DB_Password = System.getProperty("JDBC_PASSWORD");
            String DB_Connection_String = DB_Url + "?user=" + DB_User + "&password=" + DB_Password;

            conn = DriverManager.getConnection(DB_Connection_String);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            //SQL statement that takes the current running contect and put it into a arraylist
            String SQL = "Select kunde_Id FROM kunder_tilmeldt WHERE konkurrence_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1,currentContest);

            ResultSet rs = preparedStatement.executeQuery();

            //takes all the id from the users and put it into the arraylist
            while (rs.next())
            {
                System.out.println("Kunde_id:" + rs.getString("kunde_Id"));
                deltagerer.add(Integer.parseInt(rs.getString("kunde_Id")));
            }


            System.out.println(deltagerer.toString());


        } catch (SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally
        {
            //finally block used to close resources
            try
            {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se)
            {
            }// do nothing
            try
            {
                if (conn != null)
                    conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }//end finally try
        }//end try
    }


    public void getContestNumber ()
    {
        //STEP 1: set Connection to null
        Connection conn = null;
        Statement stmt = null;
        try
        {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");

            String DB_Url = System.getProperty("JDBC_CONNECTION_STRING");
            String DB_User = System.getProperty("JDBC_USER");
            String DB_Password = System.getProperty("JDBC_PASSWORD");
            String DB_Connection_String = DB_Url + "?user=" + DB_User + "&password=" + DB_Password;

            conn = DriverManager.getConnection(DB_Connection_String);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            //SQl statement that takes the id from the kurrence where the value is 'yes'
            String SQL = "Select id FROM konkurrence WHERE aktiv = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1,"yes");

            ResultSet rs1 = preparedStatement.executeQuery();

            //takes the selected id from kurrence and place it a selected value
            while(rs1.next())
            {
                currentContest = rs1.getInt("id");
                System.out.println(currentContest);
            }

        } catch (SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally
        {
            //finally block used to close resources
            try
            {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se)
            {
            }// do nothing
            try
            {
                if (conn != null)
                    conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }//end finally try
        }//end try
    }


    public int getNumber()
    {
        //STEP 1: set connection to null
        Connection conn = null;
        Statement stmt = null;
        try
        {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");

            String DB_Url = System.getProperty("JDBC_CONNECTION_STRING");
            String DB_User = System.getProperty("JDBC_USER");
            String DB_Password = System.getProperty("JDBC_PASSWORD");
            String DB_Connection_String = DB_Url + "?user=" + DB_User + "&password=" + DB_Password;

            conn = DriverManager.getConnection(DB_Connection_String);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            //SQL statement that takes the vinder from the random selected id as the vinder
            String SQL = "Select TLF FROM kunder WHERE Id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, vinder);

            //SET up the preparedstatement as a resultset
            ResultSet rs2 = preparedStatement.executeQuery();

            //Running through each columen with realiable data
            while (rs2.next())
            {
                //the selected vinder get noticfied by makinf vinder new value his TLF number.
                System.out.println("TLF:" + rs2.getInt("TLF"));
                vinder = rs2.getInt("TLF");
            }



        } catch (SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally
        {
            //finally block used to close resources
            try
            {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se)
            {
            }// do nothing
            try
            {
                if (conn != null)
                    conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }//end finally try
        }//end try
        return vinder;
    }


    public void Random()
    {
        // initializing the values
        int ggg;

        //Important: initilizing a random value from the arraylist containing all the users signed up
        Random r = new Random();
        ggg = r.nextInt(deltagerer.size());
        System.out.println("Størrelsen er " + deltagerer.size());
        System.out.println(ggg + " GGGG ER");

        //Printing out the potentiel vinders
        for(int i = 0; i <= deltagerer.size() - 1; i++)
        {
            System.out.println(i + ":" + deltagerer.get(i));
        }

        //Printing out the lucky vinder from the random selected index
        vinder = deltagerer.get(ggg);
        System.out.println("Vinder: " + vinder);
    }
}
