package gui;


import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDFS;


@WebServlet("/orderby")
public class orderby extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderby() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: hello world as das das").append(request.getContextPath());
		
		ParameterizedSparqlString qs = new ParameterizedSparqlString( "" +
	             "PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
	             "SELECT ?team ?countryOfTeam ?stadiumcapacity \r\n" + 
	             "{ \r\n" + 
	             "   ?team dbo:capacity ?stadiumcapacity ; \r\n" + 
	             "   dbo:ground ?countryOfTeam . \r\n" + 
	             "   ?countryOfTeam a dbo:Country .\r\n" + 
	             "FILTER (?stadiumcapacity > 60000)\r\n" + 
	             "} \r\n" + 
	             "order by DESC(?stadiumcapacity)");

	     QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );
	     ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );
	     String resultString = "<table><tr><td><b>team</b></td><td><b>countryOfTeam</b><td><b>stadiumcapacity</b></td>";
	     
	     try {
	    	 while ( results.hasNext() ) {

	 	    	resultString +="<tr>";
	 	      	RDFNode team = results.next().get("team");
	 	      	resultString += "<td>" + team.toString()  + "</td>";
	 	      	
	 	      	RDFNode countryOfTeam = results.next().get("countryOfTeam");
	 	      	resultString += "<td>" + countryOfTeam.toString()  + "</td>";
	 	      	
	 	      	RDFNode stadiumcapacity = results.next().get("stadiumcapacity");
	 	      	resultString += "<td>" + stadiumcapacity.toString()  + "</td>";
	 	      	
	 	    	resultString +="</tr>";
	 	    	 
	     	 }
	        } catch (NoSuchElementException e) {
	        	resultString +="</tr>";
	            //do something clever with the exception
	        } finally {
	        	
	        }
	     
	      resultString += "</table>";
        response.getWriter().append(resultString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}