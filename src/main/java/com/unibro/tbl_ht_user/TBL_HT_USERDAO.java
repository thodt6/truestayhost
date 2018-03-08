package com.unibro.tbl_ht_user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.unibro.utils.ApiClient;
import com.unibro.utils.Global;
import com.unibro.utils.RequestFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Nguyen Duc Tho
 */
public class TBL_HT_USERDAO {

    final Logger logger = Logger.getLogger(this.getClass().getName());

    public TBL_HT_USERDAO() {
        //super(); 
        //this.setConnectionPool(Global.getDbConnectionPool());
    }

    public long getTotalObject(List<RequestFilter> filters) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HT_USER/datasize");
        JsonObject data = client.executePostQuery(filters);
        if (data == null) {
            return 0;
        } else {
            if (data.get("code").getAsInt() == 200) {
                return data.get("result").getAsLong();
            } else {
                return 0;
            }
        }
    }

    public TBL_HT_USER getObjectByKey(String id) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HT_USER/" + id);
        JsonObject data = client.executeGetQuery(null);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonObject().toString(), TBL_HT_USER.class);
            } else {
                return null;
            }
        }
    }
    
    public TBL_HT_USER getObjectByUSERNAME(String USERNAME) {
        //Write your code here   
        try{
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HT_USER/USERNAME?USERNAME=" + URLEncoder.encode(USERNAME,"UTF-8"));
        JsonObject data = client.executeGetQuery(null);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonObject().toString(), TBL_HT_USER.class);
            } else {
                return null;
            }
        }
        }catch(JsonSyntaxException ex){
            return null;
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    public List<TBL_HT_USER> load(int first, int size, String sortfield, int sortorder, List<RequestFilter> filters) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HT_USER/filter/" + first + "/" + size + "/" + sortfield + "/" + sortorder);
        JsonObject data = client.executePostQuery(filters);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonArray().toString(), new TypeToken<ArrayList<TBL_HT_USER>>() {
                }.getType());
            } else {
                return null;
            }
        }
    }

    public TBL_HT_USER create(TBL_HT_USER obj) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HT_USER");
        JsonObject data = client.executePostQuery(obj);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonObject().toString(), TBL_HT_USER.class);
            } else {
                return null;
            }
        }
    }

    public int create(List<TBL_HT_USER> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        //Write your code here   
        return 0;
    }

    public TBL_HT_USER edit(TBL_HT_USER obj) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HT_USER");
        JsonObject data = client.executePutQuery(obj);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonObject().toString(), TBL_HT_USER.class);
            } else {
                return null;
            }
        }
    }

    public int delete(TBL_HT_USER obj) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HT_USER/" + obj.getID());
        JsonObject data = client.executeDeleteQuery();
        if (data == null) {
            return 0;
        } else {
            if (data.get("code").getAsInt() == 200) {
                return data.get("result").getAsInt();
            } else {
                return 0;
            }
        }
    }

    public int delete(List<TBL_HT_USER> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        //Write your code here   
        return 0;
    }
}
