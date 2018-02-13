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

@WebServlet("/where")
public class where extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
 public where() {
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
             "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
             "\r\n" + 
             "select DISTINCT ?name ?comment where { \r\n" + 
             "	?Actor rdfs:subClassOf dbo:Artist.\r\n" + 
             "    ?Actor rdfs:comment ?comment.\r\n" + 
             "    ?VoiceActor rdfs:subClassOf ?Actor.\r\n" + 
             "	?name rdf:type ?VoiceActor.\r\n" + 
             "    FILTER (lang(?comment) = \"en\")\r\n" + 
             "}     \r\n" + 
             "LIMIT 100 " );

     QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );
     ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );
     
     String resultString = "<table><tr><td><b>name</b></td><td><b>comment</b></td>";
     while ( results.hasNext() ) {
    	 resultString +="<tr>";
       	RDFNode name = results.next().get("name");
       	resultString += "<td>" + name.toString()  + "</td>";
      	
       	RDFNode comment = results.next().get("comment");
       	resultString += "<td>" + comment.toString()  + "</td>";
       	
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
