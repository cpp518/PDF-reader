
//---------------------
//| 返回数据格式为json |
//|  2018/05/19       |
//---------------------
import java.util.*;

public class returnJson {
    private LinkedHashMap<String,Object> answer = new LinkedHashMap<String,Object>();
    private LinkedHashMap<String,Object> requests = new LinkedHashMap<String,Object>();
    private LinkedHashMap<String,Object> temp = null;
    public returnJson(int type,int result,int reason,String msg)
    {

         requests.put("type",type);
         requests.put("result",result);
         requests.put("reason",reason);
         requests.put("msg",msg);
         answer.put("request",requests);
    }
    public returnJson(int type,int result,int reason)
    {
        requests.put("type",type);
        requests.put("result",result);
        requests.put("reason",reason);
        answer.put("request",requests);
    }

    public returnJson()
    {

    }

    public String result(){
        return answer.toString().replaceAll("=",":");
    }

    private LinkedHashMap<String,Object> toJson(Object []key){
        temp = new LinkedHashMap<>();
        for(int i=1;i<key.length;i+=2){
            temp.put(key[i].toString(),key[i+1]);
        }
        return temp;
    }

    public void put(String key,returnJson value){
        answer.put(key,value.result());
    }

    public void put(String key,String value){
        answer.put(key,value);
    }

    public void add(Object... key){
       // System.out.println("999999999      " + Integer.parseInt(String.valueOf(key[0])));
       //  temp = new LinkedHashMap<>();
         answer.put("book"+key[0].toString(),toJson(key));

    }

}
