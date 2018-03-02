import javax.ws.rs.ApplicationPath;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ApplicationPath("/services")
public  class  Application  extends javax.ws.rs.core.Application {
    private Set <Object > singletons = new  HashSet <Object >();
    private Set <Class <?>> empty = new HashSet<Class <?>>();

    public  Application() {
        Map<String, String> users = new HashMap<>();
        users.put("Olzhas", "456");
        users.put("Darkhan", "456");
        singletons.add(new LoginService(users));
        singletons.add(new RegisterService());
    }
    @Override
    public Set <Class <?>> getClasses () {
        return  empty;
    }
    @Override
    public Set<Object > getSingletons () {
        return  singletons;
    }
}
