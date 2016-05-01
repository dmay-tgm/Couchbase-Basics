package may_weber;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

/**
 * Testing custom couchbase environment
 * 
 * @author Daniel May
 * @version 1.0
 *
 */
public class Environment {

	/**
	 * Main function for testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Building custom environment");
		CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder().connectTimeout(10000).build();
		System.out.println("Create connection");
		Cluster cluster = CouchbaseCluster.create(env, "192.168.38.140");

		System.out.println("disconnect");
		cluster.disconnect();
	}
}