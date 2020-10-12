package com.b5wang.javalab.datastore.cassandra.otacampaign.entities;

public enum TargetState {
    CREATED,

    PENDING,
    /**
     * waiting to be pushed
     * this is the intermediary state between
     * post and patch for
     * in time frame mechanism
     */
    EXECUTING,
    SUCCEEDED,
    FAILED;
}
