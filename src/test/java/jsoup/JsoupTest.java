package jsoup;

import com.zhu.util.JsonUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Created by zhu on 2016/12/10.
 */
public class JsoupTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static List list = new ArrayList<>();
    private static Set set = new HashSet<>();
/*    @Test
    public void getTouTiaoFunny() throws IOException {
        getContent("0");

    }*/


    public void getContent(String max_behot_time) throws IOException {
        String url = "http://www.toutiao.com/api/pc/feed/?category=funny&utm_source=toutiao&widen=1&max_behot_time="+max_behot_time+"&max_behot_time_tmp="+max_behot_time;
        Connection.Response res = Jsoup.connect(url)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .cookie("tt_webid", "40716432391")
                .timeout(10000).ignoreContentType(true).execute();//.get();6362265201844207873

        String body = res.body();
        try {
            JSONObject jsonObject = new JSONObject(body);
            String message =  jsonObject.getString("message");
            max_behot_time =  jsonObject.getJSONObject("next").getString("max_behot_time");

            JSONArray dataList =  jsonObject.getJSONArray("data");

            for (int i = 0 ; i < dataList.length() ; i++){
                String group_id  = dataList.getJSONObject(i).getString("group_id");

                list.add(group_id);
                set.add(group_id);
            }

            log.debug("list 长度 = "+list.size());
            log.debug("set 长度 = "+set.size());
            log.debug("list ="+JsonUtil.toString(list));
            log.debug("set = "+JsonUtil.toString(list));
            /*log.debug(message);
            log.debug(max_behot_time);
            log.debug(group_id);
            log.debug(JsonUtil.toString(cookes));
*/
            try {
                Thread.sleep(5000L);
                getContent(max_behot_time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTouTiaoFunnyContent() throws IOException {
        String url = "http://www.toutiao.com/group/6361959594336518402/";
        Document  document = Jsoup.connect(url).get();//.get();
        String body = document.html();
        log.debug(body);
    }

}
