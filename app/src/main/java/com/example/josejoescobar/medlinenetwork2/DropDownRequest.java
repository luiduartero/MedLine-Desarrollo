package com.example.josejoescobar.medlinenetwork2;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DropDownRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://medlinenetwork.xyz/dropdownrequest.php"; //TODO PAGAINA
    private Map<String, String> params;

    public DropDownRequest(Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

