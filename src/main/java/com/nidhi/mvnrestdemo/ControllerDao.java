package com.nidhi.mvnrestdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//not necessarily dao, but more like a controller as it handles request from the client.
@Path("users")
public class ControllerDao {
	
	DataRepository dp = new DataRepository();
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Model> getData() {
		
		System.out.println("retData of getData called");
		/*Model md = new Model();
		md.setId(1);
		md.setName("Shreenidhi");
		Model md2 = new Model();
		md.setId(2);
		md.setName("Rahul");
		List<Model> lt = new ArrayList<>(Arrays.asList(md, md2));*/
		
		return dp.retData();
		
	}
	
	@GET
	@Path("user/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Model getDatabyId(@PathParam("id") int id) {
		return dp.retDatabyId(id);
	}
	
	@POST
	@Path("user")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Model createData(Model md) {
		System.out.println(md);
		dp.create(md);
		
		return md;
	}
	
	@PUT
	@Path("user")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Model updateData(Model md) {
		System.out.println(md);
		if (dp.retDatabyId(md.getId()).getId() == 0)
			dp.create(md);
		else
			dp.update(md);
		
		return md;
	}
	
	//even this works.
	/*@DELETE
	@Path("user")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Model deleteData(Model md) {
		System.out.println(md);
		if (dp.retDatabyId(md.getId()).getName() != null)
			dp.delete(md);
		
		return md;
	}*/
	
	@DELETE
	@Path("user/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Model deleteData(@PathParam("id") int id) {
		Model md = new Model();
		md = dp.retDatabyId(id);
		if (md.getName() != null)
			dp.delete(id);
		return md;
	}
	
}
