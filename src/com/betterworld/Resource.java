
/**
 * @author dj
 */

package com.betterworld;
  
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 

@Path("/company")
public class Resource {

	DAO dao = new DAO();

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String findAll() throws org.json.JSONException {
		return dao.findAll();
	}
	 
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createCompany(String company) throws org.json.JSONException {
		return dao.createCompany(company);
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCompany(String company) throws org.json.JSONException {
		return dao.updateCompany(company);
	}

	@PUT
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteCompany(String company) throws org.json.JSONException {
		return dao.deleteCompany(company);
	}  

}
