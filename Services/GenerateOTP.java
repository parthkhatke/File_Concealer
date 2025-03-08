package Services;

import com.sun.security.jgss.GSSUtil;

import java.util.Random;

public class GenerateOTP {
    public static String generateotp()
    {
        Random ran = new Random();
        int otp=0;
        for(int i=0;i<4;i++)
        {
            otp=otp*10;
            otp=otp+ran.nextInt(10);
        }

        return String.format("%s",otp);
    }


}
