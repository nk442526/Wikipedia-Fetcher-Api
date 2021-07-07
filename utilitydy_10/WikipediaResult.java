package utilitydy_10;

public class WikipediaResult {
    private  String query;
    private  String text_result;
    private  String image_url;


    public WikipediaResult() {
    }

    public WikipediaResult(String query, String text_result, String image_url){
        this.query = query;
        this.text_result = text_result;
        this.image_url = image_url;
    }


}
