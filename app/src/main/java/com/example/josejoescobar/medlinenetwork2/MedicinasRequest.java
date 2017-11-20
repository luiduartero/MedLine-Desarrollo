package com.example.josejoescobar.medlinenetwork2;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eclipse on 11/10/17.
 */


public class MedicinasRequest extends StringRequest {
    private static final String SHOW_MED = Constant.SHOW_MED;
    private Map<String, String> params;
    public MedicinasRequest(int idT, Response.Listener<String> listener){
        super(Method.POST, SHOW_MED, listener, null);
        System.out.println("PaginaWeb= "+SHOW_MED);
        params = new HashMap<>();
        params.put("id", idT+"");
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
