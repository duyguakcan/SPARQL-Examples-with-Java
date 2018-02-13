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


@WebServlet("/union")
public class union extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public union() {
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
	             "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\r\n" + 
	             "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
	             "PREFIX dbr: <http://dbpedia.org/resource/>\r\n" + 
	             "\r\n" + 
	             "SELECT distinct ?name ?birth ?person ?birthPlace ?deathPlace\r\n" + 
	             "WHERE {      \r\n" + 
	             "{?person dbo:birthPlace dbr:England .      \r\n" + 
	             "?person dbo:birthDate ?birth . \r\n" + 
	             "?person foaf:name ?name .      \r\n" + 
	             "?person dbo:birthPlace ?birthPlace .\r\n" + 
	             "?person dbo:deathPlace ?deathPlace .\r\n" + 
	             "?person dbo:deathPlace dbr:Turkey . }\r\n" + 
	             "UNION\r\n" + 
	             "{?person dbo:deathPlace dbr:Germany .\r\n" + 
	             "    ?person dbo:birthDate ?birth . \r\n" + 
	             "   ?person foaf:name ?name .  \r\n" + 
	             "    ?person dbo:birthPlace ?birthPlace . }\r\n" + 
	             "}\r\n" + 
	             "order by DESC(?birth)");

	     QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );
	     ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );
	     String resultString = "<table><tr><td><b>name</b></td><td><b>birth</b><td><b>person</b></td><td><b>birthPlace</b></td><td><b>deathPlace</b></td>";
	     
	     try {
	    	 while ( results.hasNext() ) {

	 	    	resultString +="<tr>";
	 	      	RDFNode name = results.next().get("name");
	 	      	resultString += "<td>" + name.toString()  + "</td>";
	 	      	
	 	      	RDFNode birth = results.next().get("birth");
	 	      	resultString += "<td>" + birth.toString()  + "</td>";
	 	      	
	 	      	RDFNode person = results.next().get("person");
	 	      	resultString += "<td>" + person.toString()  + "</td>";
	 	      	
	 	      	
	 	      	RDFNode birthPlace = results.next().get("birthPlace");
	 	      	resultString += "<td>" + birthPlace.toString()  + "</td>";
	 	      	
	 	      	RDFNode deathPlace = results.next().get("deathPlace");
	 	      	if(deathPlace!=null)
	 	      	resultString += "<td>" + deathPlace.toString()  + "</td>";
	 	      	else
	 	      		resultString += "<td></td>";
	 	      		
	 	      	
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