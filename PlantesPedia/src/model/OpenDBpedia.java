package model;

import java.util.ArrayList;
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
    
/**
 * Génère le morceau de requête pour rechercher une région.
 * 
 * @param region Le nom de la région à rechercher
 * @return La chaîne de caractère a ajouter à la requête
 */	
public static String Region(String region){
    return ". ?ville <http://dbpedia.org/ontology/region> <http://dbpedia.org/resource/"+region+">";
}

/**
 * Génère le morceau de requête pour rechercher un département.
 * 
 * @param departement le nom du département à rechercher
 * @return La chaîne de caractère à ajouter à la requête
 */
public static String Departement(String departement){
    return ". ?ville <http://dbpedia.org/ontology/department> <http://dbpedia.org/resource/"+departement+">";
}

/**
 * Génère le morceau de requête pour rechercher un code postal.
 * 
 * @param CP le code postal à rechercher
 * @return La chaîne de caractère à ajouter à la requête
 */
public static String Code_postal(String CP){
    return ". ?ville <http://dbpedia.org/ontology/postalCode> ?CP. FILTER(str(?CP) ='"+CP+"')";
}

/**
 * Génère le morceau de requête pour rechercher une ville bornée en population.
 * 
 * @param min la borne minimale de population
 * @param max la borne maximale de population
 * @return La chaîne de caractère à ajouter à la requête
 */
public static String Population(int min, int max){
    return ". ?ville <http://dbpedia.org/ontology/populationTotal> ?POP . FILTER(?POP >"+Integer.toString(min)+") .FILTER(?POP <"+Integer.toString(max)+")";
}

/**
 * Génère le morceau de requête pour rechercher une ville bornée en superficie.
 * 
 * @param min la borne minimale de superficie
 * @param max la borne maximale de superficie
 * @return La chaîne de caractère à ajouter à la requête
 */
public static String Superficie(int min, int max){
    return ". ?ville <http://dbpedia.org/ontology/area> ?SUP . FILTER(?SUP >"+Integer.toString(min)+") .FILTER(?SUP <"+Integer.toString(max)+")";
}

/**
 * Génère le morceau de requête pour rechercher une ville en fonction de son nom
 * 
 * @param nom le nom de la ville
 * @return La chaîne de caractère à ajouter à la requête
 */
private static String Nom(String nom) {
        String tmp1 = ". ?ville rdfs:label ?name . FILTER(str(?name) ='";
        String tmp2 = "')";
        return tmp1 + nom + tmp2;
}

/**
 * Permet d'obtenir une requête en fonction de plusieurs chams en paramètre.
 * On n'ajoute pas à la requête les champs null.
 * La requête de base correspond à la recherche de toutes les villes de France.
 * 
 * @param nom Le nom de la ville
 * @param region La région de la ville
 * @param dpt Le département de la ville
 * @param postCode Le code postal de la ville
 * @param nb_hab_min Le nombre d'habitants minimum de la ville
 * @param nb_hab_max Le nombre d'habitants maximum de la ville
 * @param sup_min La superficie minimale de la ville
 * @param sup_max La superficie maximale de la ville
 * @return La requête correspondant
 */
public static String getRequete(String nom, String region, String dpt, String postCode, String nb_hab_min, String nb_hab_max, String sup_min, String sup_max){
    
    String req = "Select distinct ?ville Where{ ?ville <http://dbpedia.org/ontology/country> <http://dbpedia.org/resource/France>";
    
    int hab_min, hab_max, super_min, super_max;
    
    if (nom != null) req = req+Nom(nom);
    
    if (region != null) req=req+Region(region);
    
    if (dpt != null) req=req+Departement(dpt);
    
    if (postCode != null) req=req+Code_postal(postCode);
    
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
        
    System.out.println(postCode);
    return req;
}	

/**
 * Permet d'obtenir une liste contenant les villes de réponses d'une requête.
 * 
 * @param requete La requête de recherche
 * @return la liste avec les villes correspondantes à la requêtes
 * @throws RepositoryException
 * @throws MalformedQueryException
 * @throws QueryEvaluationException 
 */
public static ArrayList<String> getVilles(String requete)throws RepositoryException,MalformedQueryException , QueryEvaluationException{
    
    ArrayList<String> sortie = new ArrayList<String>();
    
    HTTPRepository repo = new HTTPRepository("http://dbpedia.org/sparql","");
    RepositoryConnection connection = repo.getConnection();
    TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, requete);
    TupleQueryResult result = query.evaluate();
    while(result.hasNext()){
	BindingSet binSet = result.next();
        sortie.add(binSet.toString());
    }
    
    return sortie;
}
        
}


