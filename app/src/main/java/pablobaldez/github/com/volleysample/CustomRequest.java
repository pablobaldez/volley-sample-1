package pablobaldez.github.com.volleysample;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * @author Pablo
 * @since 24/08/2015
 */
public class CustomRequest extends StringRequest {
    public CustomRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public CustomRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    // TODO: 24/08/2015 AQUI NÓS CONTINUAREMOS NA SEGUNDA PARTE DO POST :)
}
