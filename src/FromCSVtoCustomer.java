import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;



class cust extends csvExample
{
     @Override
     void Insertion(String customer, Connection con)
     {
         try
         {
               String query = "insert into customer values(?,?,?,?,?,?)";

               String[] data = customer.split(",");

          
               PreparedStatement stmt = con.prepareStatement(query);
               stmt.setInt(1, Integer.valueOf(data[0]));
               stmt.setInt(2, Integer.valueOf(data[1]));
               stmt.setString(3, data[2]);
               stmt.setString(4, data[3]);
               stmt.setString(5, data[4]);
               stmt.setInt(6, Integer.valueOf(data[5]));

               String updateQuery = "update books set CopiesAvailable = CopiesAvailable - 1 where (Bid) in (select Bid from customer where Cid=?)";

               PreparedStatement stmt1 = con.prepareStatement(updateQuery);

               stmt1.setInt(1, Integer.valueOf(data[1]));

                stmt.executeUpdate();
               stmt1.executeUpdate();
         }

         catch(Exception e)
         {

         }
     }

     
     //overloaded//
     void Update(String DateGiven, Connection con, int id) 
     {
         
          try
          {    
               String Query = "select DateGiven from customer where Cid="+id;

               PreparedStatement Stmt = con.prepareStatement(Query);

               ResultSet Rs = Stmt.executeQuery();

               String dategiven = "";

               while(Rs.next())
               {
                    dategiven = Rs.getString("DateGiven");  
               }

               
              if(dategiven.equals("NULL"))
              {
                    //Updating DateGiven
                    String query = "update customer set DateGiven=? where Cid="+id;

                    PreparedStatement stmt = con.prepareStatement(query);

                    stmt.setString(1, DateGiven);

                    stmt.executeUpdate();


                    //Finding no.of days customer took the book//
                    String query1 = "select DateTaken from customer where Cid="+id;

                    PreparedStatement stmt1 = con.prepareStatement(query1);

                    ResultSet rs = stmt1.executeQuery();

                    String DateTaken="";

                    while(rs.next())
                    {
                         DateTaken = rs.getString("DateTaken");
                    }

                    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(DateTaken);
                    Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(DateGiven);

                    long milliseconds = date2.getTime() - date1.getTime();  
               
                    long days = TimeUnit.MILLISECONDS.toDays(milliseconds) % 365;



                    //inserting total bill into customer table//
                    String query2 = "select CostPerDay from books where (Bid) in (select Bid from customer where Cid=?)";

                    PreparedStatement stmt2 = con.prepareStatement(query2);

                    stmt2.setInt(1, id);

                    ResultSet rs1 = stmt2.executeQuery();

                    int CostPerDay = 0;

                    while(rs1.next())
                    {
                         CostPerDay = rs1.getInt("CostPerDay");
                    }

                    long bill = days * CostPerDay;

                    int final_bill = (int) bill;

                    String query4 = "update customer set Bill=? where Cid="+id;

                    PreparedStatement stmt3 = con.prepareStatement(query4);

                    stmt3.setInt(1,final_bill);

                    stmt3.executeUpdate();


                   //updating CopiesAvailable after customer returns the book//
                   String query5 = "update books set CopiesAvailable = CopiesAvailable + 1 where (Bid) in (select Bid from customer where Cid=?)";

                   PreparedStatement stmt4 = con.prepareStatement(query5);

                   stmt4.setInt(1, Integer.valueOf(id));

                   stmt4.executeUpdate();
               }
          }

          catch(Exception e)
          { 
               
          }
     }
}
