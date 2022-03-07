package pt.iiq.custom.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

public class RestClient{

    protected String URL;
    protected String userName;
    protected String password;
    protected ClientConfig config;
    protected Client client;

    
    //Construtor padrão que eu uso quando preciso de user e pass
    RestClient(String url, String userName, String password){
        config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        
        this.URL = url;
        this.userName = userName;
        this.password = password;
        
        client = Client.create(config);
        client.addFilter(new HTTPBasicAuthFilter(this.userName, this.password));
    }
    
    public RestClient() {
    	
    	this.URL = "http://localhost:8100/user-service";
    	
    	config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        
        client = Client.create(config);
	}

    protected WebResource getWebResource(String uri){
        return client.resource(URL.concat(uri));
    }
    protected WebResource getWebResourceUsingStringParamenter(String url){
    	return client.resource(url);
    }
    
}
