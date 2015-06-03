package za.co.authoritativelabpro.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * 1. empty web.xml
 * 2. Application path represent --> rest path declaration/declaration-ws
 * 
 * 3. ApplicationPath is the Java EE 6 approach to activating JAX-RS.
 *
 */
@ApplicationPath("/declaration-ws")
public class JaxRsActivator extends Application {
    /* class body intentionally left blank */
}
