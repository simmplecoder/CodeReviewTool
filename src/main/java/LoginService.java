import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class LoginService extends javax.servlet.http.HttpServlet {
    private Map<String, String> users;

    public LoginService(Map<String, String> users) {
        this.users = users;
    }

    @POST
    @Consumes("application/json")
    @Produces("")
    public Response login(String json) {
        Response.ResponseBuilder builder;
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> loginAttempt = new Gson().fromJson(json, type);

        String username = users.get(loginAttempt.get("username"));
        String password = users.get(loginAttempt.get("password"));

        if (!users.containsKey(username) || !users.get(username).equals(password)) {
            builder = Response.status(Response.Status.UNAUTHORIZED);
        } else {
            builder = Response.ok("/editor.html");
        }
        return builder.build();
    }
}
