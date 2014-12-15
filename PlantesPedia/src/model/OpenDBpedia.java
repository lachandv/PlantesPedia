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
    
    public static String getRequete(String nom, String region, String dpt, String postCode, String nb_hab_min, String nb_hab_max, String sup_min, String sup_max){
    
    String req = "Select distinct ?ville Where{ ?ville <http://dbpedia.org/ontology/country> <http://dbpedia.org/resource/France>";
    
    int hab_min, hab_max, super_min, super_max;
    if (region != null) req=req+Region(region);
    
    if (dpt != null) req=req+Departement(dpt);
    
    if (postCode != null) req=req+Code_postal(Integer.parseInt(postCode));
    
    if (nb_hab_min == null) hab_min = 0;
    else hab_min = Integer.parseInt(nb_hab_min);
    
    if (nb_hab_max == null) hab_max = Integer.MAX_VALUE;
    else hab_max = Integer.parseInt(nb_hab_max);
    
    if (sup_min == null) super_min = 0;
    else super_min = Integer.parseInt(sup_min);
    
    if (sup_max == null) super_max = Integer.MAX_VALUE;
    else super_max = Integer.parseInt(sup_max);
    
    req = req+Population(hab_min, hab_max);
    req = req+Superficie(super_min, super_max);
    req = req+"} LIMIT 10";
        
    System.out.println(req);
    return req;
}	
	
public static String Region(String region){
    return ". ?ville <http://dbpedia.org/ontology/region> <http://dbpedia.org/resource/"+region+"/>";
}

public static String Departement(String departement){
    return ". ?ville <http://dbpedia.org/ontology/department> <http://dbpedia.org/resource/"+departement+"/>";
}
public static String Code_postal(int CP){
    return ". ?ville <http://dbpedia.org/ontology/postalCode> ?CP . FILTER(?CP ="+Integer.toString(CP)+")";
}

public static String Population(int min, int max){
    return ". ?ville <http://dbpedia.org/ontology/populationTotal> ?POP . FILTER(?POP >"+Integer.toString(min)+") .FILTER(?POP <"+Integer.toString(max)+")";
}

public static String Superficie(int min, int max){
    return ". ?ville <http://dbpedia.org/ontology/area> ?SUP . FILTER(?SUP >"+Integer.toString(min)+") .FILTER(?SUP <"+Integer.toString(max)+")";
}
	
	public static void main(String[] args) throws RepositoryException,MalformedQueryException , QueryEvaluationException{
		
		
		HTTPRepository repo = new HTTPRepository("http://dbpedia.org/sparql","");
		RepositoryConnection connection = repo.getConnection();
                String req = getRequete(null, null, null, null, null, null, null, null);
		TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, req);
		TupleQueryResult result = query.evaluate();
		while(result.hasNext()){
			BindingSet binSet = result.next();
			System.out.println("villezeufghz : ");
			System.out.println(binSet.getValue("ville"));
		}
	
		
		connection.close();
		
	}
        
}
