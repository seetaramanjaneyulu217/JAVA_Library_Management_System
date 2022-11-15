import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


class Book
{
    int Bid;
    String Name;
    String Author;
    String Genre;
    int No_of_copies_available;
    int Cost_per_day;

    public Book()
    {

    }

    

    public Book(int bid, String name, String author, String genre, int no_of_copies_available, int cost_per_day) 
    {
         Bid = bid;
         Name = name;
         Author = author;
         Genre = genre;
         No_of_copies_available = no_of_copies_available;
         Cost_per_day = cost_per_day;
    }


    public void Insertion()
    {
         try
         {
              Connection con = conn.con();

              String filepath = "book.csv";

              BufferedReader Rowreader = new BufferedReader(new FileReader(filepath));

              String Rowtext;

              while((Rowtext=Rowreader.readLine()) != null)
              {
                  csvExample csvfile = new csvExample();
                  csvfile.Insertion(Rowtext, con);  
              }

              Rowreader.close();
         }

         catch(Exception e)
         {
              e.printStackTrace();
         }
    }


    public void Update()
    {
         try
         {
               Connection con = conn.con();
            
               String filepath = "book.csv";
               String filepath1 = "book1.csv";

               BufferedReader Rowreader = new BufferedReader(new FileReader(filepath1));

               String Rowtext;

               while((Rowtext=Rowreader.readLine()) != null)
               {
                    csvExample csvfile1 = new csvExample();
                    csvfile1.Update(filepath,Rowtext,con);
               } 

               Rowreader.close();
         }

         catch(Exception e)
         {
               e.printStackTrace();
         }
    }


    public void Deletion(int id)
    {
          Connection con = conn.con();
     
          csvExample csvfile = new csvExample();
          csvfile.Deletion(con,id);        
    }


    @Override
     public String toString() 
     { 
          return String.format("|%5d|%25s|%25s|%20s|%10s|%10s|",Bid,Name,Author,Genre,No_of_copies_available,Cost_per_day);  
     }
}




class Customer
{
    int Bid;
    int customerid;
    String customername;
    String DateTaken;
    String DateGiven;
    int Bill;

    
     public Customer() 
     {

     }

     public Customer(int bid, int customerid, String customername, String dateTaken, String dateGiven, int bill) 
     {
          Bid = bid;
          this.customerid = customerid;
          this.customername = customername;
          DateTaken = dateTaken;
          DateGiven = dateGiven;
          Bill = bill;
     }




     public void Insertion()
     {
          try
          {
               Connection con = conn.con();

               String filepath = "customer.csv";

               BufferedReader Rowreader = new BufferedReader(new FileReader(filepath));

               String Rowtext;

               while((Rowtext=Rowreader.readLine()) != null)
               {
                    cust ins = new cust();
                    ins.Insertion(Rowtext, con);  
               }

               Rowreader.close();
          }

          catch(Exception e)
          {
               e.printStackTrace();
          }
     }


     public void Update(String DateGiven, int id)
     {
          Connection con = conn.con();

          cust upd = new cust();

          upd.Update(DateGiven,con,id);
             
     }

     @Override
     public String toString() 
     {
         return String.format("|%10s|%10s|%20s|%15s|%15s|%10s|",Bid,customerid,customername,DateTaken,DateGiven,Bill);     
     }

}



class Library 
{

     ArrayList<Book> BList = new ArrayList<>();
     ArrayList<Customer> CList = new ArrayList<>();


     public void importFromDb() throws SQLException
     {
          Connection con = conn.con();

          PreparedStatement stm=con.prepareStatement("select * from books");
          ResultSet rs=stm.executeQuery();

          while(rs.next())
          {
              Book b=new Book(Integer.valueOf(rs.getInt(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
              CommandLine.lib.BList.add(b);
          }

          PreparedStatement stm1=con.prepareStatement("select * from customer");
          ResultSet rs1=stm1.executeQuery();

          while(rs1.next())
          {
              Customer c=new Customer(rs1.getInt(1),rs1.getInt(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getInt(6));
               CommandLine.lib.CList.add(c);
          }
     }


          public void printBooks()
          {
               Iterator<Book> itr = CommandLine.lib.BList.iterator();

               while(itr.hasNext())
               {
                   System.out.println(itr.next());
               }
                
          }

          public void printCustomer()
          {
               Iterator<Customer> itr = CommandLine.lib.CList.iterator();

               while(itr.hasNext())
               {
                   System.out.println(itr.next());
               }    
          }

}
