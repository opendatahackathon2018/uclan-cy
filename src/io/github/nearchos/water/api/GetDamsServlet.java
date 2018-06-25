package io.github.nearchos.water.api;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.nearchos.water.data.Dam;
import io.github.nearchos.water.data.DatastoreHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

public class GetDamsServlet extends HttpServlet {

    public static final String MEM_CACHE_KEY_DAMS = "dams-data";

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    private final MemcacheService memcacheService = MemcacheServiceFactory.getMemcacheService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        if(memcacheService.contains(MEM_CACHE_KEY_DAMS)) {
            final String damsJson = (String) memcacheService.get(MEM_CACHE_KEY_DAMS);
            response.getWriter().println(damsJson); // write the response
        } else {
            final Vector<Dam> dams = DatastoreHelper.getDams();
            if (dams == null || dams.isEmpty()) {
                final Message errorMessage = Message.getErrorMessage("Hmmm, missing dams data. Please contact the developer.");
                response.getWriter().println(gson.toJson(errorMessage));
            } else {
                final String damsJson = gson.toJson(dams.toArray());
                // store the result in memcache
                memcacheService.put(MEM_CACHE_KEY_DAMS, damsJson);
                // return the result as JSON
                response.getWriter().println(gson.toJson(damsJson));
            }
        }
    }

    public static void main(String[] args) {
        final Vector<Dam> dams = DatastoreHelper.getDams();
        System.out.println(dams);
        System.out.println(gson.toJson(dams));

    }
}