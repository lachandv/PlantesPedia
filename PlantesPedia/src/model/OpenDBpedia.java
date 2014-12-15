package model;

import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

public class OpenDBpedia {
	
	public static void main(String[] args) throws RepositoryException,MalformedQueryException , QueryEvaluationException{
		
		
		HTTPRepository repo = new HTTPRepository("http://dbpedia.org/sparql","");
		RepositoryConnection connection = repo.getConnection();
		TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, "select distinct ?Res where {<http://dbpedia.org/resource/Lyon> ?y ?Res} LIMIT 10");
		TupleQueryResult result = query.evaluate();
		while(result.hasNext()){
			BindingSet binSet = result.next();
			System.out.println("Res : ");
			System.out.println(binSet.getValue("Res"));
		}
	
		
		connection.close();
		
	}
	
	
	

}
