package edu.upc.eetac.dsa.beeter;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by carlos on 1/03/16.
 */
public class BeeterResourceConfig extends ResourceConfig {

    public BeeterResourceConfig() {
        packages("edu.upc.eetac.dsa.beeter");
    }
}
