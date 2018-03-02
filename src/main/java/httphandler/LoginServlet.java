package httphandler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    private Map<String, String> users;

    public LoginServlet(Map<String, String> users) {
//        this.users = users;
        this.users.put("Darkhan", "123");
        this.users.put("Olzhas", "456");
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String json = request.getParameter("application/json");
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> loginAttempt = new Gson().fromJson(json, type);

        String username = users.get(loginAttempt.get("username"));
        String password = users.get(loginAttempt.get("password"));

        if (!users.containsKey(username)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (!users.get(username).equals(password)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
