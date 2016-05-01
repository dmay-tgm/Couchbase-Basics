package may_weber;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

/**
 * @author danie
 *
 */
public class CouchbaseConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cluster cluster = CouchbaseCluster.create();

	}

}
