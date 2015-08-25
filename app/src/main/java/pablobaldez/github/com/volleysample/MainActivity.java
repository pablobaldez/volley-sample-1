package pablobaldez.github.com.volleysample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    //TIMEOUT em milisegundos
    private static final int TIMEOUT = 15000;


    private TextView textView;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);

        // Inicia a fila de requisições da atividade
        queue = Volley.newRequestQueue(this);

        // Criação da request definindo quatro parâmetros:
        //      - O tipo de método HTTP (GET, POST, PUT, DELETE...)
        //      - A url a ser acessada (www.algumacoisa.com)
        //      - O responsável por tratar a resposta. Neste caso uma classe anônima que exibe o resultado em um TextView
        //      - O responsável por tratar possíveis erros. Neste caso também usamos uma classe anônima
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "algumaUrl",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("A resposta é: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Ocorreu algum erro :(");
            }
        });
        stringRequest.setTag(TAG);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adiciona a requisição a fila. A partir deste momento ela será iniciada em qualquer
        // instante
        queue.add(stringRequest);

    }

    @Override
    protected void onStop() {
        super.onStop();
        queue.cancelAll(TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
