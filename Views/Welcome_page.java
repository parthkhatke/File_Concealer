package Views;
import DAO.userDAO;
import Model.user;
import Services.GenerateOTP;
import Services.SendOTP;
import Views.userView;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome_page {

    public void welcomepge()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to File Concealer");
        System.out.println("Press 1 for login ");
        System.out.println("Press 2 for sign up");
        System.out.println("Press 0 to exit");
        System.out.println("Press 9 for admin login");
        int choice = sc.nextInt();
        switch (choice)
        {
            case 1-> login();
            case 2-> signup();
            case 0-> System.exit(0);
            case 9-> adminlogin();
        }
    }

    private void signup() {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Please enter your Name - ");
        String name = scanner.nextLine();
        System.out.println("Enter your email ID - ");
        String email= scanner.nextLine();
        try {
            if(userDAO.userexists(email))
            {
                System.out.println("user already exist kindly login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String otp= GenerateOTP.generateotp();
        SendOTP.sendOTP(email,otp);
        System.out.println("enter otp ");
        String input_otp= scanner.nextLine();
        if(otp.equals(input_otp))
        {
            System.out.println("otp is correct");
            user user = new user(email,name);
            try {
                userDAO.saveuser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("User registered");
            new userView(email).userview();
        }else
        {
            System.out.println("otp incorrect");
        }

    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email ID - ");
        String email= sc.nextLine();

        try {
            if(userDAO.userexists(email))
            {
                String otp= GenerateOTP.generateotp();
                SendOTP.sendOTP(email,otp);
                    System.out.println("enter otp ");
                    String input_otp= sc.nextLine();
                    if(otp.equals(input_otp))
                    {
                        new userView(email).userview();
                    }else
                    {
                        System.out.println("otp incorrect");
                    }
            }
            else
            {
                System.out.println("user does not exist kindly sign up");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void adminlogin(){
        System.out.println("enter admin password- ");
        Scanner sc = new Scanner(System.in);
        String pass= sc.nextLine();
        if(pass.equals("parth"))
        {
            String email="parthkhatke@gmail.com";
            new userView(email).userview();
        }

    }
}
