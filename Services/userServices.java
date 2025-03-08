package Services;
import DAO.userDAO;
import Model.user;

public class userServices {
    public static int saveuser(user user)
    {
        try
        {
            if(userDAO.userexists(user.getEmail()))
                return 0;
            else
                userDAO.saveuser(user);
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
        return -1;
    }
}
