package com.unibro.tbl_hos_req_to_book;

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
public class TBL_HOS_REQ_TO_BOOKDAO {

    final Logger logger = Logger.getLogger(this.getClass().getName());

    public TBL_HOS_REQ_TO_BOOKDAO() {
        //super(); 
        //this.setConnectionPool(Global.getDbConnectionPool());
    }

    public long getTotalObject(List<RequestFilter> filters) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HOS_REQ_TO_BOOK/datasize");
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

    public TBL_HOS_REQ_TO_BOOK getObjectByKey(String id) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HOS_REQ_TO_BOOK/" + id);
        JsonObject data = client.executeGetQuery(null);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonObject().toString(), TBL_HOS_REQ_TO_BOOK.class);
            } else {
                return null;
            }
        }
    }

    public List<TBL_HOS_REQ_TO_BOOK> load(int first, int size, String sortfield, int sortorder, List<RequestFilter> filters) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HOS_REQ_TO_BOOK/filter/" + first + "/" + size + "/" + sortfield + "/" + sortorder);
        JsonObject data = client.executePostQuery(filters);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonArray().toString(), new TypeToken<ArrayList<TBL_HOS_REQ_TO_BOOK>>() {
                }.getType());
            } else {
                return null;
            }
        }
    }

    public TBL_HOS_REQ_TO_BOOK create(TBL_HOS_REQ_TO_BOOK obj) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HOS_REQ_TO_BOOK");
        JsonObject data = client.executePostQuery(obj);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonObject().toString(), TBL_HOS_REQ_TO_BOOK.class);
            } else {
                return null;
            }
        }
    }

    public int create(List<TBL_HOS_REQ_TO_BOOK> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        //Write your code here   
        return 0;
    }

    public TBL_HOS_REQ_TO_BOOK edit(TBL_HOS_REQ_TO_BOOK obj) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HOS_REQ_TO_BOOK");
        JsonObject data = client.executePutQuery(obj);
        if (data == null) {
            return null;
        } else {
            if (data.get("code").getAsInt() == 200) {
                Gson gson = Global.getGsonObject("yyyy-MM-dd'T'HH:mm:ss.Z");
                return gson.fromJson(data.get("result").getAsJsonObject().toString(), TBL_HOS_REQ_TO_BOOK.class);
            } else {
                return null;
            }
        }
    }

    public int delete(TBL_HOS_REQ_TO_BOOK obj) {
        //Write your code here   
        ApiClient client = new ApiClient(Global.getConfigValue("APP.API_ADDRESS") + "/TBL_HOS_REQ_TO_BOOK/" + obj.getID());
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

    public int delete(List<TBL_HOS_REQ_TO_BOOK> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        //Write your code here   
        return 0;
    }
}
