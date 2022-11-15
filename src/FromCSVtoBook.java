import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class csv 
{
    abstract void Insertion(String book, Connection con);
    abstract void Update(String filepath, String book1, Connection con);
    abstract void Deletion(Connection con, int id);
}

class csvExample extends csv
{   

    @Override
    void Insertion(String book, Connection con) 
    {
        String query = "insert into Books values(?,?,?,?,?,?)";

        try 
        {
             String[] data = book.split(",");

             PreparedStatement stmt = con.prepareStatement(query);

             stmt.setInt(1, Integer.valueOf(data[0]));
             stmt.setString(2, data[1]);
             stmt.setString(3, data[2]);
             stmt.setString(4, data[3]);
             stmt.setInt(5, Integer.valueOf(data[4]));
             stmt.setInt(6, Integer.valueOf(data[5]));

             stmt.executeUpdate();
        } 
        
        catch (Exception e) 
        {
           
        }
    }

    @Override
    void Update(String filepath, String book1, Connection con) 
    {
        try 
        {
            String[] data1 = book1.split(",");

            String temp;

            BufferedReader Rowreader = new BufferedReader(new FileReader(filepath));

            int count=0;

            while((temp=Rowreader.readLine()) != null)
            {
                 String[] data = temp.split(",");

                 int id = Integer.valueOf(data[0]);

                 if(id == Integer.valueOf(data1[0]))
                 {
                     String query = "update Books set Bname=?,Author=?,Genre=?,CopiesAvailable=?,CostPerDay=? where Bid="+id;

                     PreparedStatement stmt = con.prepareStatement(query);

                     stmt.setString(1, data1[1]);
                     stmt.setString(2, data1[2]);
                     stmt.setString(3, data1[3]);
                     stmt.setInt(4, Integer.valueOf(data1[4]));
                     stmt.setInt(5, Integer.valueOf(data1[5]));

                     stmt.executeUpdate();

                     count++;

                     break;
                 }
            }

            if(count == 0)
            {
                 String query = "insert into Books values(?,?,?,?,?,?)";

                 PreparedStatement stmt = con.prepareStatement(query);

                 stmt.setInt(1, Integer.valueOf(data1[0]));
                 stmt.setString(2, data1[1]);
                 stmt.setString(3, data1[2]);
                 stmt.setString(4, data1[3]);
                 stmt.setInt(5, Integer.valueOf(data1[4]));
                 stmt.setInt(6, Integer.valueOf(data1[5]));

                 stmt.executeUpdate();
            }

            Rowreader.close();
        } 
        
        catch (Exception e) 
        {
            
        }
    }

    @Override
    void Deletion(Connection con, int id) 
    {
          String query = "delete from Books where Bid="+id;

          try
          {
               PreparedStatement stmt = con.prepareStatement(query);

               stmt.executeUpdate();
          } 
        
          catch (SQLException e) 
          {
               
          }
    }
    
}