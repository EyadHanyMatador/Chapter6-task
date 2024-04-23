package EJBs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Controller {

    @PersistenceContext(unitName = "hello")
    private EntityManager EM;

    @POST
    @Path("/calc")
    public int calculate(Calculations calc) {
        int result = calc.calculate();
        EM.persist(calc);
        return result;
    }

    @GET
    @Path("/calculations")
    public List<Calculations> getCalculations() {
        return EM.createQuery("SELECT c FROM Calculations c", Calculations.class).getResultList();
    }
}




