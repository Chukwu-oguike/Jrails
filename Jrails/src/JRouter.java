package jrails;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.*;

public class JRouter {

    private HashMap<String, String> map = new HashMap<>();

    public void addRoute(String verb, String path, Class clazz, String method) {

        String key = verb + "#"+ path;
        String value = clazz.getName() + "#" + method;
        map.put(key, value);

    }

    // Returns "clazz#method" corresponding to verb+URN
    // Null if no such route
    public String getRoute(String verb, String path) {
        String temp = this.map.get(verb + "#"+ path);
        return temp;
    }

    // Call the appropriate controller method and
    // return the result
    public Html route(String verb, String path, Map<String, String> params) {
        String temp = this.map.get(verb + "#" + path);
        if (temp == null) {
            throw new UnsupportedOperationException();
        } else {
            String[] temp2 = temp.split("#");
            try {
                Class<?> c = Class.forName(temp2[0]);
                Class[] cArg = new Class[1];
                cArg[0] = Map.class;
                Method method = c.getDeclaredMethod(temp2[1], cArg);
                return (Html) method.invoke(c, params);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return null;
    }
}
