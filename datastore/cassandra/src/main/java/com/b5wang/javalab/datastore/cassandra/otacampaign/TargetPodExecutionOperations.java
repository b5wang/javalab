package com.b5wang.javalab.datastore.cassandra.otacampaign;

import com.b5wang.javalab.datastore.cassandra.connection.CassandraConnection;

public class TargetPodExecutionOperations {

    public final static String HOST = "192.168.99.100";
    public final static int PORT = 31042;
    public final static String DATACENTER = "datacenter1";

    public static void main(String[] args){
        CassandraConnection.init(HOST,PORT,DATACENTER);
    }

}
