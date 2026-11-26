package com.example.programasuacactivity;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgramaAdaptador adaptador;
    private List<ProgramaUAC> listaProgramas;

    private static final String URL_API_GITHUB = "https://raw.githubusercontent.com/Juanse57/api-programas-uac/refs/heads/main/programas_uac.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerProgramasUac);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaProgramas = new ArrayList<>();
        adaptador = new ProgramaAdaptador(listaProgramas);
        recyclerView.setAdapter(adaptador);

        cargarProgramasDesdeGitHub();
    }

    private void cargarProgramasDesdeGitHub() {
        RequestQueue colaPeticiones = Volley.newRequestQueue(this);

        JsonArrayRequest peticion = new JsonArrayRequest(
                Request.Method.GET,
                URL_API_GITHUB,
                null,
                respuesta -> {
                    listaProgramas.clear();
                    for (int i = 0; i < respuesta.length(); i++) {
                        try {
                            JSONObject obj = respuesta.getJSONObject(i);
                            String programa = obj.optString("programa", "Sin nombre");
                            String facultad = obj.optString("facultad", "Sin facultad");
                            String descripcion = obj.optString("descripcion", "");
                            String imagenUrl = obj.optString("imagenUrl", "");

                            listaProgramas.add(new ProgramaUAC(programa, facultad, descripcion, imagenUrl));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adaptador.notifyDataSetChanged();
                    Toast.makeText(this, "Cargados " + listaProgramas.size() + " programas", Toast.LENGTH_SHORT).show();
                },
                error -> Toast.makeText(this, "Error de red: " + error.toString(), Toast.LENGTH_LONG).show()
        );

        colaPeticiones.add(peticion);
    }
}