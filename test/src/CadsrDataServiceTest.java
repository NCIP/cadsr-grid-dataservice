import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.data.client.DataServiceClient;
import java.util.Iterator;

import gov.nih.nci.cagrid.common.Utils;

import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;

import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;

import java.io.File;
import java.io.FilenameFilter;


public class CadsrDataServiceTest {
  
  private String serviceUrl;
  private String queryDirectory;
  
  public CadsrDataServiceTest(String serviceUrl, String queryDirectory) {
    this.serviceUrl = serviceUrl;
    this.queryDirectory = queryDirectory;
  }
  
  private void performQuery() {
    try {
      // initialize the generic data service client
      DataServiceClient client = new DataServiceClient(serviceUrl);

      
      String[] filenames = new File(queryDirectory).list(new FilenameFilter() {
          public boolean accept(File dir, String name) {
            return name.endsWith(".xml");
          }
        });
      
      if(filenames == null) 
        filenames = new String[0];
      
      if(filenames.length == 0) {
        System.out.println("No query files. Exiting");
        System.exit(0);
      }

      for(String queryFilename : filenames) {

        // deserialize the CQL query
        CQLQuery query = (CQLQuery) Utils.deserializeDocument(queryDirectory + "/" + queryFilename, CQLQuery.class);
        // execute the query on the data service
        System.out.println("Querying file:  " + queryFilename);
        long l = new java.util.Date().getTime();
        CQLQueryResults results = client.query(query);
        l = (new java.util.Date().getTime() - l);
        System.out.println("Time to run Query : " + l  + " ms");

        // create a results iterator
        System.out.println("Iterating");
        Iterator iter = new CQLQueryResultsIterator(results, true);
        // iterate and print XML
        System.out.println("-- RESULT --");
        int i = 0;
        while (iter.hasNext()) {
          String value = (String) iter.next();
          System.out.println(value);
          System.out.print("+");
          i++;
        }
        System.out.println("Nb of results: " + i);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println("USAGE: <serviceUrl> <queryDirectory>");
      System.exit(1);
    }

    CadsrDataServiceTest runner = new CadsrDataServiceTest(args[0], args[1]);
    runner.performQuery();
  }

}
