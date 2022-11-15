import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class CommandLine 
{
    static Library lib = new Library();

    public static void main(String[] args) throws SQLException, NumberFormatException, ParseException, IOException 
    {
        Book b = new Book();

        Customer c = new Customer();

        search s = new search();

        switch (args[0]) 
        {
            case "-Insertintobooks":
                b.Insertion();
                break;


                
            case "-Updatebooks":
                b.Update();
                break;



            case "-Deletefrombooks":
                b.Deletion(Integer.valueOf(args[1]));
                break;



            case "-Insertintocustomer":
                c.Insertion();
                break;



            case "-Updatecustomer":
                c.Update(args[1], Integer.valueOf(args[2]));
                break;



            case "-Search":
                switch (args[1]) {
                    case "id":
                        s.searching_using_id(Integer.valueOf(args[2]));
                        break;

                    case "name":
                        s.searching_by_name(args[2]);
                        break;

                    case "copiesnumber":
                        s.searching_by_books_available(Integer.valueOf(args[2]));
                        break;

                    case "partialname":
                        s.searching_by_partialname(args[2]);
                        break;

                    default:
                        System.out.println("invalid request");
                }
                
                break;

            

            case "-print":
                lib.importFromDb();
                switch (args[1]) 
                {
                    case "books":
                        String temp[] = new String[] { "id", "book name", "Author name", "Genre", "num", "cpd" };
                        System.out.printf("+%5s+%25s+%25s+%20s+%10s+%10s+", "-----", "-------------------------",
                                "-------------------------", "--------------------", "----------", "----------");
                        System.out.println();
                        System.out.printf("|%5s|%25s|%25s|%20s|%10s|%10s|", temp[0], temp[1], temp[2], temp[3], temp[4],
                                temp[5]);
                        System.out.println();
                        System.out.printf("+%5s+%25s+%25s+%20s+%10s+%10s+", "-----", "-------------------------",
                                "-------------------------", "--------------------", "----------", "----------");

                        
                        System.out.println();
                        lib.printBooks();
                        System.out.printf("+%5s+%25s+%25s+%20s+%10s+%10s+", "-----", "-------------------------",
                                "-------------------------", "--------------------", "----------", "----------");
                        System.out.println();
                        break;


                    case "customer":
                        String temp1[] = new String[] { "Bid", "Cid", "Name", "Date taken", "Date given", "Bill" };
                        System.out.printf("+%10s+%10s+%20s+%15s+%15s+%10s+", "----------", "----------",
                                "--------------------", "---------------", "---------------", "----------");
                        System.out.println();
                        System.out.printf("|%10s|%10s|%20s|%15s|%15s|%10s|", temp1[0], temp1[1], temp1[2], temp1[3],
                                temp1[4], temp1[5]);
                        System.out.println();
                        System.out.printf("+%10s+%10s+%20s+%15s+%15s+%10s+", "----------", "----------",
                                "--------------------", "---------------", "---------------", "----------");
                        System.out.println();
                        lib.printCustomer();
                        System.out.printf("+%10s+%10s+%20s+%15s+%15s+%10s+", "----------", "----------",
                                "--------------------", "---------------", "---------------", "----------");
                        System.out.println();
                        break;
                    default:
                        System.out.println("oops! syntax error");
                }
                break;

            default:
                System.out.println("oops! syntax error");

        }
    }
}
