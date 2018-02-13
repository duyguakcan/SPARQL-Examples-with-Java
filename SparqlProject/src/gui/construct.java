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

@WebServlet("/construct")
public class construct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
 public construct() {
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
             "PREFIX  dbpedia-owl:  <http://dbpedia.org/ontology/>\r\n" + 
             "PREFIX      dbpprop:  <http://dbpedia.org/property/>\r\n" + 
             "CONSTRUCT { ?s  dbpprop:meaning  ?meaning }\r\n" + 
             "WHERE \r\n" + 
             "  {\r\n" + 
             "    ?s a  dbpedia-owl:Name; dbpprop:name  ?name ;  dbpprop:meaning  ?meaning.\r\n" + 
             " }" );

     QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );
     ResultSetRewindable result = ResultSetFactory.makeRewindable(exec.execSelect());

     
     String resultString = "<table><tr><td><b>subject</b></td><td><b>predicate</b></td><td><b>object</b></td>";
     while (result.hasNext()) {
    	resultString +="<tr>";
    	QuerySolution querysolution = result.next();

      	resultString += "<td>" + querysolution.get("s").toString()  + "</td>";
     	
      	resultString += "<td>" + querysolution.get("p").toString()  + "</td>";
      	
      	resultString += "<td>" + querysolution.get("o").toString()  + "</td>";    	
      	
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
