package gui;

import java.io.IOException;
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

@WebServlet("/filter")
public class filter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
 public filter() {
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
             "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
             "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
             "\r\n" + 
             "SELECT DISTINCT ?name ?child ?birthYear\r\n" + 
             "WHERE {\r\n" + 
             "?person dbo:child ?child .\r\n" + 
             "?child dbo:birthYear ?birthYear .\r\n" + 
             "?person rdfs:label ?name FILTER (lang(?name) = \"en\").\r\n" + 
             " FILTER (?birthYear > \"2000-01-01\"^^xsd:date)   \r\n" + 
             "}\r\n");

     QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );
     ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );
     String resultString = "<table><tr><td><b>name</b></td><td><b>child</b></td><td><b>birthYear</b></td>";
     while ( results.hasNext() ) {
    	resultString +="<tr>";
     	RDFNode name = results.next().get("name");
     	resultString += "<td>" + name.toString()  + "</td>";
     	
     	RDFNode child = results.next().get("child");
     	resultString += "<td>" + child.toString()  + "</td>";
     	
     	RDFNode birthYear = results.next().get("birthYear");
     	resultString += "<td>" + birthYear.toString()  + "</td>";
     	
     	resultString +="</tr>";
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
