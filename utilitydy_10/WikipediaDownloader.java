package utilitydy_10;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jooq.util.derby.sys.Sys;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;

public class WikipediaDownloader  implements Runnable{
//hum WikipediaDownloader class me multithreade ka use krna chhte the isliye isme runnable class ko implement kr diye.
// implemnet krne  se ky hua ki sara runnable wla chij ab isme bhi use ho skega

    private  String keyword;
   // private  String result;
   // private Object WikipediaResult;

    public  WikipediaDownloader(){

    }
    public  WikipediaDownloader(String keyword){
        this.keyword=keyword;
    }

    public void run() {
        //1.Get clean keyword
        //2.Get URL for wikipedia
        //3.Make GET request to wikipedia
        //4.Parsing the useful results using jsoup
        //5.showing results

        if(this.keyword==null || this.keyword.length()==0){
            return;
        }
        //step 1
        this.keyword=this.keyword.trim().replaceAll("[ ]+","_");//trim used to remove space from removing end and begin.

        //step2
        String wikiUrl=getWikipediaUrlForQuery(this.keyword);
        String  response="";
        String imageUrl = null;

        try {
            //step3
            String  wikipediaResponseHTML=HttpURLConnectionExample.sendGet(wikiUrl);//sendGet function is already made in HttpURLConnectionExaml.
           // System.out.println(wikipediaResponseHTML);

            //step4
            Document document = Jsoup.parse(wikipediaResponseHTML, "https://en.wikipedia.org");
            //document.body().select(".mw-parser-output");

            //here Elements class used is already defined in jsoup
            Elements childElements=document.body().select(".mw-parser-output > *");

            int state =0;

            for(Element childElement : childElements){
                if(state==0) {
                    if(childElement.tagName().equals("table")){
                        state=1;
                    }
                    else if(state==1){
                        if(childElement.tagName().equals("p")){
                            state=2;
                            response = childElement.text();
                            break;
                        }
                    }
                }
              //  System.out.println(childElement.tagName());
            }
            try {
                 imageUrl = document.body().select(".infobox img").get(0).attr("src");
            }
            catch (Exception ex){

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        WikipediaResult wikipediaResult=new WikipediaResult(this.keyword, response, imageUrl);
        //push result into database

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(wikipediaResult);
        System.out.println(json);
    }

    //getWikipediaUrlForQuery function is defined here
    private String getWikipediaUrlForQuery(String cleanKeyword) {
        return "https://en.wikipedia.org/wiki/"+cleanKeyword;
    }


    public static void main(String[] args) {
        TaskManager taskManager=new TaskManager(20);
        String arr[]={"India","United States"};
        System.out.println("This is Neha");
        System.out.println("Running wikipedia downloader at"+new Date().getTime());

        for(String keyword : arr) {
            WikipediaDownloader wikipediaDownloader = new WikipediaDownloader(keyword);
            taskManager.waitTillQueueIsFreeAndAddTask(wikipediaDownloader);
        }
    }
}
