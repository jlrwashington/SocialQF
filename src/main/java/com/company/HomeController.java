package com.company;

import com.evdb.javaapi.data.User;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView helloWorld() {
        ModelAndView mv = new
                ModelAndView("welcome");

        mv.addObject("title", "iSocialQ");
        return mv;
    }

//    @RequestMapping("/response")
//    public ModelAndView response(
//            @RequestParam("fName") String fName
//    )
//
//    {
//
//        ModelAndView mv = new ModelAndView("response");
//
//        mv.addObject("fName", fName);
//        return mv;
//    }

    //   ADD SQL DATABASE------------------------------------------>
    @RequestMapping(value = "response")
    public ModelAndView getAllTypes(@RequestParam("fName") String fName,
                                    @RequestParam("sign") String sign)
    {


        //Load the driver
        try {

            Class.forName("com.mysql.jdbc.Driver");

            //Create the DataBase connection object
            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(Credentials.DBADDRESS, Credentials.DBUSER, Credentials.DBPASS);

            //Create DB Statement
            String readCustomersCommand = "select * from zodiac where Sign='" + sign + "'";
            System.out.println(readCustomersCommand);
            Statement readCustomers = mysqlConnection.createStatement(); //Creates the statement
            ResultSet results = readCustomers.executeQuery(readCustomersCommand); //Executes the statement
            //add code here to start looping through results
            results.next();
            String trait = results.getString("Trait");
            String description = results.getString("Description");
            String activity = results.getString("Activity");


             // API STARTS HERE
            //this http client will make requests from the other server
            HttpClient http = HttpClientBuilder.create().build();

            //HttpGet will actually retrieve the information from the
            //specific url
           HttpGet getpage = new HttpGet("http://api.eventful.com/json/events/search?app_key=" + Credentials.APIKEY +"&category="
                   +activity + "&location=Detroit");
//            HttpGet getpage = new HttpGet("/json/events/search?app_key=" + Information.dCS8dgFTfSw3GNbh + "&l=Detroit");

            //actually run it and pull in the response
            HttpResponse resp = http.execute(getpage);

            //get the actual content from inside the response
            String jsonString = EntityUtils.toString(resp.getEntity());

            System.out.println(resp);
            System.out.println(jsonString);

            //turn into a Json object
            JSONObject json = new JSONObject(jsonString);

            //The lines below won't run

           JSONArray arr = json.getJSONObject("events").getJSONArray("event");
           ArrayList<Event> events = new ArrayList<Event>();

            for(int i = 0; i <arr.length(); i++) {

                String title= arr.getJSONObject(i).getString("title");
                System.out.println(title);
                String startTime = arr.getJSONObject(i).getString("start_time");
                System.out.println(startTime);

                String desc = null;
                if (arr.getJSONObject(i).has("description") && !arr.getJSONObject(i).isNull("description")) {
                    desc = arr.getJSONObject(i).getString("description");
                }
                System.out.println(desc);
                String url = arr.getJSONObject(i).getString("url");
                System.out.println(url);

                Event event = new Event(title,startTime,desc, url);
                events.add(event);
            }

            //set to the properties inside of object

//            int status = resp.getStatusLine().getStatusCode();
//            String prodCenter = json.get("productionCenter").toString();
//
//            JSONArray days = json.getJSONObject("time").getJSONArray("startPeriodName");
//            JSONArray temps = json.getJSONObject("data").getJSONArray("temperatures");


            ModelAndView mv = new ModelAndView("response");
            mv.addObject("fName", fName);
            mv.addObject("sign", sign);
            mv.addObject("trait", trait);
            mv.addObject("description", description);
            mv.addObject("activity", activity);

            //mv.addObject("json", jsonString);
            mv.addObject("events", events);

            return mv;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}