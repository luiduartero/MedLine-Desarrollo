package com.example.josejoescobar.medlinenetwork2;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eclipse on 15/08/17.
 */

public class AgregarMedicinaRequest extends StringRequest {
    private static final String PRES_MED = Constant.PRES;
    private Map<String, String> params;
    public AgregarMedicinaRequest( String enfermedadT, String recetaT, String  cantidadT,
                                   String tiempoT, String getDiaT, String observacionT,
                                   String idT, String fechaRecibida, String fechaFinal, Response.Listener<String> listener){
        super(Request.Method.POST, PRES_MED, listener, null);
        System.out.println("PaginaWeb= "+PRES_MED);
        params = new HashMap<>();
        params.put("id", idT);
        params.put("diagnostico", enfermedadT);
        params.put("fecha", fechaRecibida);
        params.put("medicamento", recetaT);
        params.put("dosis", cantidadT);
        params.put("veces_al_dia", tiempoT);
        params.put("tiempo_de_uso", getDiaT);
        params.put("observacion", observacionT );
        params.put("fecha_final", fechaFinal);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
