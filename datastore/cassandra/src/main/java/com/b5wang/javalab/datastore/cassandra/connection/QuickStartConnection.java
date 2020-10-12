package com.b5wang.javalab.datastore.cassandra.connection;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class QuickStartConnection {

    /**
     *
     * (1) CqlSession is the main entry point of the driver. It holds the known state of the actual Cassandra cluster,
     * and is what you use to execute queries. It is thread-safe, you should create a single instance (per target Cassandra cluster),
     * and share it throughout your application;
     * (2) we use execute to send a query to Cassandra. This returns a ResultSet, which is an iterable of Row objects.
     * On the next line, we extract the first row (which is the only one in this case);
     * (3) we extract the value of the first (and only) column from the row.
     *
     * */
    public static void main(String[] args){

        List<InetSocketAddress> points = new ArrayList<>();
        points.add(new InetSocketAddress("192.168.99.100",30042));

        try (CqlSession session = CqlSession.builder().addContactPoints(points).withLocalDatacenter("datacenter1").build()) {
            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            System.out.println(row.getString("release_version"));
        }
    }

}
