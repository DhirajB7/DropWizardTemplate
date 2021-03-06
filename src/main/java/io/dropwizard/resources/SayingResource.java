package io.dropwizard.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.api.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class SayingResource {

    private String template;
    private String defaultName;
    private AtomicLong counter;

    public SayingResource(String template, String defaultName){

        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name){
        String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(),value);
    }

}
