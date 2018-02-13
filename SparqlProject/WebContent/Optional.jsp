<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="assets/img/sparqllogo.png" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SPARQL Examples</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sparql examples</title>
    <!-- Core CSS - Include with every page -->
    <link href="assets/plugins/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="assets/plugins/pace/pace-theme-big-counter.css" rel="stylesheet" />
    <link href="assets/css/style.css" rel="stylesheet" />
    <link href="assets/css/main-style.css" rel="stylesheet" />
    <!-- Page-Level CSS -->
    <link href="assets/plugins/morris/morris-0.4.3.min.css" rel="stylesheet" />
   </head>
</head>
<body>
    <div id="wrapper">
        <!-- navbar side -->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <!-- sidebar-collapse -->
            <div class="sidebar-collapse">
                <!-- side-menu -->
                <ul class="nav" id="side-menu">
                    <li>
                        <!-- user image section-->
                        <div class="user-section">
                            <div class="user-section-inner">
                                <img src="assets/img/sparqllogo.png" alt="">
                            </div>
                            <div class="user-info">
                                <div><strong>SPARQL</strong></div>
                                <div class="user-text-online">
                                    &nbsp;Examples
                                </div>
                            </div>
                        </div>
                        <!--end user image section-->
                    </li>
                    <li class="sidebar-search">
                        <!-- search section-->
                        <div class="input-group custom-search-form">
                            
                        </div>
                        <!--end search section-->
                    </li> 
                    <li >
                        <a href="Home.jsp">SPARQL Home</a>
                    </li>                                       
                     <li>
                        <a href="Select.jsp">Select</a>
                    </li>  
                    <li>
                        <a href="Where.jsp">Where</a>
                    </li>
                     <li>
                        <a href="Filter.jsp">Filter</a>
                    </li>
                     <li>
                        <a href="Offset.jsp">Offset</a>
                    </li>
                     <li class="selected">
                        <a href="Optional.jsp">Optional</a>
                    </li>
                     <li>
                        <a href="OrderBy.jsp">Order By</a>
                    </li> 
                     <li>
                        <a href="GroupBy.jsp">Group By</a>
                    </li>              
                     <li>
                        <a href="Distinct.jsp">Distinct</a>
                    </li>         
                     <li>
                        <a href="Construct.jsp">Construct</a>
                    </li> 
                    <li >
                        <a href="Ask.jsp">Ask</a>
                    </li>                       
                       <li>
                        <a href="Union.jsp">Union</a>
                    </li> 
                     <li>
                        <a href="Count.jsp">Count</a>
                    </li>        
                     <li>
                        <a href="Sum.jsp">Sum</a>
                    </li>        
                     <li>
                        <a href="Min.jsp">Min</a>
                    </li> 
                       <li>
                        <a href="Max.jsp">Max</a>
                    </li>                                                                             
                </ul>
                <!-- end side-menu -->
            </div>
            <!-- end sidebar-collapse -->
        </nav>
        <!-- end navbar side -->
        <!--  page-wrapper -->
        <div id="page-wrapper">

            <div class="row">
                <!-- Page Header -->
                <div class="col-lg-12">
                    <h1 class="page-header">SPARQL Optional</h1>
                </div>
                <!--End Page Header -->
            </div>

            <div class="row">
                <!-- Welcome -->
                <div class="col-lg-12">
                    <div class="alert alert-info">
Basic graph patterns allow applications to make queries where the entire query pattern must match for there 
to be a solution. For every solution of a query containing only group graph patterns with at least one basic graph pattern, every variable is bound to an 
RDF Term in a solution. However, regular, complete structures cannot be assumed in all RDF graphs. It is useful to be able to have queries that allow information
to be added to the solution where the information is available, but do not reject the solution because some part of the query pattern does not match. 
Optional matching provides this facility: if the optional part does not match, it creates no bindings but does not eliminate the solution.

</br></br>
<b>Usage:</b></br>
PREFIX foaf: &lt;http://xmlns.com/foaf/0.1/&gt;</br>
SELECT ?name ?mbox</br>
WHERE  { ?x foaf:name  ?name .</br>
         OPTIONAL { ?x  foaf:mbox  ?mbox }</br>
       }</br>

                    </div>
                </div>
                <!--end  Welcome -->
            </div>
            <div class="row">
      
                    <!-- Notifications-->
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Example Query
                        </div>

                        <div class="panel-body" id="queryPlace">
                        All Turkish presidents'name, birth date also their death date sorted by birth date.</br></br>
							PREFIX dbo: &lt;http://dbpedia.org/ontology/&gt;</br>
							PREFIX dbr: &lt;http://dbpedia.org/resource/&gt;</br></br>
							
							SELECT DISTINCT ?president ?birth ?death</br>  
							WHERE {</br>
							?person dbo:birthPlace dbr:Turkey .</br>
							?person dbo:birthDate ?birth .</br>
							?person dbo:president ?president    .</br>
							OPTIONAL { ?person dbo:deathDate ?death . }</br>
							}</br>
							ORDER BY (?birth)  </br>           
                        </div>
                         <div class="panel-footer">
             			
             			<script>
             			var showResultFrame = function(){
             				document.getElementById("resultFrame").src = "/SparqlPeoject/optional";
             			}
             			</script>
             			<button type="button" onclick="showResultFrame()" class="btn btn-warning">Run</button>  
                    </div>

                </div>
          </div>
                  <div class="row">
    
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Result
                        </div>

                        <div class="panel-body">
                            <iframe id="resultFrame" src="" style="width:100%;height:400px;border:none;"></iframe>
                        </div>

                        <div class="panel-footer">

                        </div>
        
                </div>  
                </div>  
                
       
            </div>



        </div>
        
<div style="text-align:center;font-size:12px;font-weight:bold;">
  	This project is prepared for CENG685 Semantic Web Course.
</div>

        
        <!-- end page-wrapper -->


    <!-- Core Scripts - Include with every page -->
    <script src="assets/plugins/jquery-1.10.2.js"></script>
    <script src="assets/plugins/bootstrap/bootstrap.min.js"></script>
    <script src="assets/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="assets/plugins/pace/pace.js"></script>
    <script src="assets/scripts/siminta.js"></script>
    <!-- Page-Level Plugin Scripts-->
    <script src="assets/plugins/morris/raphael-2.1.0.min.js"></script>
    <script src="assets/plugins/morris/morris.js"></script>
    <script src="assets/scripts/dashboard-demo.js"></script>
    
</body>
</html>