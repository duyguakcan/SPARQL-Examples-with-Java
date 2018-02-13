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

@WebServlet("/optional")
public class optional extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
 public optional() {
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
             "PREFIX dbr: <http://dbpedia.org/resource/>\r\n" + 
             "\r\n" + 
             "SELECT DISTINCT ?president ?birth ?death  \r\n" + 
             "WHERE {\r\n" + 
             "?person dbo:birthPlace dbr:Turkey .\r\n" + 
             "?person dbo:birthDate ?birth .\r\n" + 
             "?person dbo:president ?president    .\r\n" + 
             "OPTIONAL { ?person dbo:deathDate ?death . }\r\n" + 
             "}\r\n" + 
             "ORDER BY (?birth)");

     QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );
     ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );
     String resultString = "<table><tr><td><b>president</b></td><td><b>birth</b></td><td><b>death</b></td>";
     try {

	     while ( results.hasNext() ) {
    	resultString +="<tr>";
     	RDFNode name = results.next().get("president");
     	resultString += "<td>" + name.toString()  + "</td>";
     	
     	RDFNode child = results.next().get("birth");
     	resultString += "<td>" + child.toString()  + "</td>";
     	
     	RDFNode death = results.next().get("death");
     	if(death!=null)
     	resultString += "<td>" + death.toString()  + "</td>";
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
