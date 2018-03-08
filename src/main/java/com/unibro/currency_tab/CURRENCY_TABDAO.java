package com.unibro.currency_tab;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.unibro.utils.ApiClient;
import com.unibro.utils.Global;
import com.unibro.utils.RequestFilter;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Nguyen Duc Tho
 */
public class CURRENCY_TABDAO {

    final Logger logger = Logger.getLogger(this.getClass().getName());

    public CURRENCY_TABDAO() {
        //super(); 
        //this.setConnectionPool(Global.getDbConnectionPool());
    }

    public long getTotalObject(List<RequestFilter> filters) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/CURRENCY_TAB/datasize");
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

    public CURRENCY_TAB getObjectByKey(String id) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/CURRENCY_TAB/" + id);
        JsonObject data = client.executeGetQuery(null);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonObject().toString(), CURRENCY_TAB.class);
            } else {
                return null;
            }
        }
    }

    public List<CURRENCY_TAB> load(int first, int size, String sortfield, int sortorder, List<RequestFilter> filters) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/CURRENCY_TAB/filter/" + first + "/" + size + "/" + sortfield + "/" + sortorder);
        JsonObject data = client.executePostQuery(filters);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonArray().toString(), new TypeToken<ArrayList<CURRENCY_TAB>>() {
                }.getType());
            } else {
                return null;
            }
        }
    }

    public CURRENCY_TAB create(CURRENCY_TAB obj) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/CURRENCY_TAB");
        JsonObject data = client.executePostQuery(obj);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonObject().toString(), CURRENCY_TAB.class);
            } else {
                return null;
            }
        }
    }

    public int create(List<CURRENCY_TAB> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        //Write your code here   
        return 0;
    }

    public CURRENCY_TAB edit(CURRENCY_TAB obj) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/CURRENCY_TAB");
        JsonObject data = client.executePutQuery(obj);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonObject().toString(), CURRENCY_TAB.class);
            } else {
                return null;
            }
        }
    }

    public int delete(CURRENCY_TAB obj) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/CURRENCY_TAB/" + obj.getCODE_A3());
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

    public int delete(List<CURRENCY_TAB> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        //Write your code here   
        return 0;
    }
}
