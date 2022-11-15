import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

interface Searching 
{
    void searching_using_id(int id);
    void searching_by_name(String name);
    void searching_by_books_available(int available);
}

class search implements Searching 
{
    @Override
    public void searching_using_id(int id) 
    {
        Connection con = conn.con();

        String query = "select * from books where Bid = ?";

        try{

            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) 
            {

                System.out.println("------------------");
                System.out.println("Book id          :" + rs.getInt("Bid"));
                System.out.println("Book name        :" + rs.getString("Bname"));
                System.out.println("Author name      :" + rs.getString("Author"));
                System.out.println("Genre            :" + rs.getString("Genre"));
                System.out.println("Number of copies :" + rs.getInt("CopiesAvailable"));
                System.out.println("Cost per day     :" + rs.getInt("CostPerDay"));

            }

        }

        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }



    @Override
    public void searching_by_name(String name) 
    {
        Connection con = conn.con();

        String query = "select * from books where Bname=?";

        try{

            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) 
            {
                System.out.println("------------------");
                System.out.println("Book id          :" + rs.getInt("Bid"));
                System.out.println("Book name        :" + rs.getString("Bname"));
                System.out.println("Author name      :" + rs.getString("Author"));
                System.out.println("Genre            :" + rs.getString("Genre"));
                System.out.println("Number of copies :" + rs.getInt("CopiesAvailable"));
                System.out.println("Cost per day     :" + rs.getInt("CostPerDay"));
            }
        }

        catch (Exception e) 
        {
            e.printStackTrace();
        }

    }



    @Override
    public void searching_by_books_available(int available) 
    {
        Connection con = conn.con();

        String query = "select * from books where CopiesAvailable=?";

        try{

            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setInt(1, available);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) 
            {
                System.out.println("------------------");
                System.out.println("Book id          :" + rs.getInt("Bid"));
                System.out.println("Book name        :" + rs.getString("Bname"));
                System.out.println("Author name      :" + rs.getString("Author"));
                System.out.println("Genre            :" + rs.getString("Genre"));
                System.out.println("Number of copies :" + rs.getInt("CopiesAvailable"));
                System.out.println("Cost per day     :" + rs.getInt("CostPerDay"));
            }

        }

        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }


    public void searching_by_partialname(String name) 
    {
        Connection con = conn.con();

        String query = "select * from books";

        try 
        {
            PreparedStatement stmt = con.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) 
            {
                String str = rs.getString(2);

                int flag = 0;

                for (int i = 0; i < str.length(); i++) 
                {
                    for (int j = i + 1; j <= str.length(); j++) 
                    {
                        if (str.substring(i, j).toUpperCase().equals(name.toUpperCase()))
                        flag = 1;
                    }
                }

                if (flag == 1) 
                {
                    System.out.println("------------------");
                    System.out.println("Book id          :" + rs.getInt("Bid"));
                    System.out.println("Book name        :" + rs.getString("Bname"));
                    System.out.println("Author name      :" + rs.getString("Author"));
                    System.out.println("Genre            :" + rs.getString("Genre"));
                    System.out.println("Number of copies :" + rs.getInt("CopiesAvailable"));
                    System.out.println("Cost per day     :" + rs.getInt("CostPerDay"));
                }
            }
        }

        catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
}