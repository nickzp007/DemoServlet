import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java .util.regex.Matcher;
import java.util.regex.Pattern;
@WebServlet(
        description="Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name="user", value = "nikhil"),
                @WebInitParam(name="password", value ="nick123")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        Validation validate = new Validation();
        PrintWriter out = resp.getWriter();
        if (!validate.validateName(user)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            out.println("<font color = red> Name must be starts with UpperCaseLetter and required minimum 3 characters </font> ");
            rd.include(req, resp);
        }
        else if (!validate.validatePassword(pwd)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            out.println("<font color = red> Password must contain 8 Characters atleast 1 UpperLetter 1 number,1 Special Character </font> ");
            rd.include(req, resp);
        }
        else if(userID.equals(user) && password.equals(pwd)) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        }
        else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");

            out.println("<font color=red>Either Username or password is Incorrect.</font>");
            rd.include(req, resp);
        }

    }
}