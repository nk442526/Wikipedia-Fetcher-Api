package utilitydy_10;

import org.jooq.Keyword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class TopKeywordAnalyser13 implements Runnable {

    private final String filepath;

    public TopKeywordAnalyser13(String filePath){
        this.filepath=filePath;
    }

    public void run() {

        ArrayList<String> keywordsFileData  = fileutility.readFileAsList(filepath);
        HashMap<String , Integer> keywordCounter = new HashMap<String, Integer>();

        for (String row : keywordsFileData) {
            String[] keywords = row.split(" ");
            for(String  keyword:keywords)
            {
                String str = keyword.toLowerCase();
                if(!keywordCounter.containsKey(str)){
                    keywordCounter.put(str,1);
                }
                else{
                    Integer value=keywordCounter.get(str);
                    keywordCounter.put(str,value+1);
                }
            }
        }

        ArrayList<KeywordCounter13> keywordCounter13sArrayList = new ArrayList<KeywordCounter13>();

        for (String keyword : keywordCounter.keySet()){
            KeywordCounter13 keywordCounter13 = new KeywordCounter13  (keyword,keywordCounter.get(keyword));
            keywordCounter13sArrayList.add(keywordCounter13);
        }
        Collections.sort(keywordCounter13sArrayList, new Comparator<KeywordCounter13>() {
            public int compare(KeywordCounter13 o1, KeywordCounter13 o2) {
                return o2.count-o1.count;
            }
        });

        for(KeywordCounter13 keywordCounter13: keywordCounter13sArrayList){
            System.out.println(keywordCounter13.keyword+" "+keywordCounter13.count);
        }
    }

    public static void main(String[] args) {
       TaskManager taskManager=new TaskManager(1);
       taskManager.waitTillQueueIsFreeAndAddTask(new TopKeywordAnalyser13("C:\\Users\\Neha\\Desktop\\Anthem\\IndianNationalAnthem.txt"));

    }


}
