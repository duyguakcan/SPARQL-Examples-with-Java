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

@WebServlet("/sum")
public class sum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
 public sum() {
     super();
     // TODO Auto-generated constructor stub
 }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: hello world as das das").append(request.getContextPath());
		
		ParameterizedSparqlString qs = new ParameterizedSparqlString( "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
				" SELECT (SUM(xsd:double(?p)) as ?sum)\r\n" + 
				" WHERE\r\n" + 
				" {\r\n" + 
				" VALUES (?p) {\r\n" + 
				" (10.5) \r\n" + 
				" (20.5)\r\n" + 
				" (8)\r\n" + 
				" (155)\r\n" + 
				" }\r\n" + 
				" }");

     QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );
     ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );
     
     String resultString = "<table><tr><td><b>sum</b></td>";
     while ( results.hasNext() ) {
    	resultString +="<tr>";
      	RDFNode resource = results.next().get("sum");
      	resultString += "<td>" + resource.toString()  + "</td>";
      	
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
