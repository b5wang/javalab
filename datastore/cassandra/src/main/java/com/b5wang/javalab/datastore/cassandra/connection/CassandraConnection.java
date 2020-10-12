package com.b5wang.javalab.datastore.cassandra.connection;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.datastax.oss.driver.api.core.CqlSession;

public class CassandraConnection {

    private static CassandraConnection cassandraConnection;

    private List<InetSocketAddress> contactPoints;

    private String localDatacenter;

    private CqlSession session;

    private CassandraConnection(String host, int port, String localDatacenter){
        this.contactPoints = new ArrayList<>();
        this.contactPoints.add(new InetSocketAddress(host,port));
        this.localDatacenter = localDatacenter;
        this.session = CqlSession.builder().addContactPoints(contactPoints).withLocalDatacenter(localDatacenter).build();
    }

    public static CassandraConnection init(String host, int port, String localDatacenter){
        cassandraConnection = new CassandraConnection(host, port, localDatacenter);
        return cassandraConnection;
    }

    public static CassandraConnection getInstance(){
        if(cassandraConnection == null){
            throw new RuntimeException("CassandraConnection has not be built! Please call init() first!");
        }
        return cassandraConnection;
    }

    public CqlSession getSession(){
        return session;
    }
}
