package com.b5wang.javalab.datastore.cassandra;

import java.time.Instant;
import java.util.UUID;

public class CassandraConnection {

    static final String COLUMN_ICCID = "iccid";
    static final String COLUMN_BEARER = "bearer";
    static final String COLUMN_CAMPAIGN_ID = "campaign_id";
    static final String COLUMN_ERROR_CODE = "error_code";
    static final String COLUMN_ERROR_MESSAGE = "error_message";
    static final String COLUMN_SCENARIO_ID = "scenario_id";
    static final String COLUMN_STATE = "state";
    static final String COLUMN_UPDATE_TIME = "update_time";
    static final String COLUMN_ORDER_ID = "order_id";

    static final String COLUMN_ID = "id";
    static final String COLUMN_VAL_1 = "val_1";
    static final String COLUMN_VAL_2 = "val_2";
    static final String COLUMN_VAL_3 = "val_3";
    static final String COLUMN_VAL_4 = "val_4";
    static final String COLUMN_VAL_5 = "val_5";
    static final String COLUMN_UPDATED_TIME = "updated_time";


//    private String serverAddress = "192.168.99.100";
//    private int serverPort = 31042;
//    private String keyspace = "dev_ota_campaign";
//
//    private Cluster cluster;
//    private Session session;
//
//    private PreparedStatement psInsert;
//
//    private PreparedStatement psInsertUnsetuuid;
//
//    public CassandraConnection(){
//    }
//
//    public void connect(){
//        cluster = Cluster.build().addContactPoint(serverAddress).withPort(serverPort).build();
//        session = cluster.connect(keyspace);
//
//        psInsert = session.prepare(QueryBuilder.insertInto(keyspace, "ota_campaign_targets")
//                .value(COLUMN_ICCID, QueryBuilder.bindMarker())
//                .value(COLUMN_BEARER, QueryBuilder.bindMarker())
//                .value(COLUMN_CAMPAIGN_ID, QueryBuilder.bindMarker())
//                .value(COLUMN_ERROR_CODE, QueryBuilder.bindMarker())
//                .value(COLUMN_ERROR_MESSAGE, QueryBuilder.bindMarker())
//                //.value(COLUMN_SCENARIO_ID, QueryBuilder.bindMarker())
//                .value(COLUMN_STATE, QueryBuilder.bindMarker())
//                .value(COLUMN_UPDATE_TIME, QueryBuilder.bindMarker())
//                //.value(COLUMN_ORDER_ID, QueryBuilder.bindMarker())
//        );
//
//        psInsertUnsetuuid = session.prepare(QueryBuilder.insertInto("unsetcolumns1")
//                .value(COLUMN_ID, QueryBuilder.bindMarker())
//                .value(COLUMN_VAL_1, QueryBuilder.bindMarker())
//                .value(COLUMN_VAL_2, QueryBuilder.bindMarker())
//                .value(COLUMN_VAL_3, QueryBuilder.bindMarker())
//                .value(COLUMN_VAL_4, QueryBuilder.bindMarker())
//                .value(COLUMN_VAL_5, QueryBuilder.bindMarker())
//                .value(COLUMN_UPDATED_TIME, QueryBuilder.bindMarker())
//        );
//    }
//
//    public void disconnect(){
//        if(cluster != null){
//            cluster.close();
//        }
//    }
//
//    public void storeWithOriginalValue(int id, UUID val1, String val2, String val3, Boolean val4, Long val5, Long updatedDate){
//        BoundStatement bs = psInsertUnsetuuid.bind();
//        bs.setInt(COLUMN_ID, id);
//        bs.setUUID(COLUMN_VAL_1, val1);
//        bs.setString(COLUMN_VAL_2, val2);
//        bs.setString(COLUMN_VAL_3, val3);
//        bs.setBool(COLUMN_VAL_4, val4);// null value is java.lang.NullPointerException
//        bs.setLong(COLUMN_VAL_5, val5);// null value is java.lang.NullPointerException
//        bs.set(COLUMN_UPDATED_TIME, updatedDate, SimpleTimestampCodec.instance);
//        session.execute(bs);
//    }
//
//    public void storeWithUnset(int id, UUID val1, String val2, String val3, Boolean val4, Long val5, Long updatedDate){
//        BoundStatement bs = psInsertUnsetuuid.bind()
//                .setInt(COLUMN_ID, id)
//                .setUUID(COLUMN_VAL_1, val1)
//                .setString(COLUMN_VAL_2, val2)
//                .setString(COLUMN_VAL_3, val3)
//                .setBool(COLUMN_VAL_4, val4)// null value is java.lang.NullPointerException
//                .setLong(COLUMN_VAL_5, val5)// null value is java.lang.NullPointerException
//                .set(COLUMN_UPDATED_TIME, updatedDate, SimpleTimestampCodec.instance);
//
//        if(val1 == null){
//            bs.unset(COLUMN_VAL_1);
//        }
//        if(val2 == null){
//            bs.unset(COLUMN_VAL_2);
//        }
//        if(val3 == null){
//            bs.unset(COLUMN_VAL_3);
//        }
//        if(updatedDate == null){
//            bs.unset(COLUMN_UPDATED_TIME);
//        }
//
//        session.execute(bs);
//    }
//
//
//    public ResultSetFuture storeAsync(TargetEntity targetEntity) {
//        BoundStatement bs = psInsert.bind()
//                .setString(COLUMN_ICCID, targetEntity.getIccid())
//                .setString(COLUMN_BEARER, targetEntity.getBearer())
//                .setUUID(COLUMN_CAMPAIGN_ID, targetEntity.getCampaignId())
//                //.setUUID(COLUMN_SCENARIO_ID, targetEntity.getScenarioId())
//                .setString(COLUMN_STATE, targetEntity.getState())
//                .set(COLUMN_UPDATE_TIME, targetEntity.getUpdateTime(), SimpleTimestampCodec.instance);
//
//        if(targetEntity.getScenarioId() == null){
//            //bs.setToNull(COLUMN_SCENARIO_ID);
//        }else{
//            //bs = bs.setUUID(COLUMN_SCENARIO_ID, targetEntity.getScenarioId());
//        }
//        if(targetEntity.getErrorCode() == null){
//            bs.unset(COLUMN_ERROR_CODE);
//        }else{
//            bs = bs.setString(COLUMN_ERROR_CODE, targetEntity.getErrorCode());
//        }
//        if(targetEntity.getErrorMessage() == null){
//            bs.unset(COLUMN_ERROR_MESSAGE);
//        }else{
//            bs = bs.setString(COLUMN_ERROR_MESSAGE, targetEntity.getErrorMessage());
//        }
//        if(targetEntity.getOrderId() == null){
//            //bs.setToNull(COLUMN_ORDER_ID);
//        }else{
//            //bs = bs.setUUID(COLUMN_ORDER_ID, targetEntity.getOrderId());
//        }
//
//        return session.executeAsync(bs);
//    }
//
//    public static void saveUnsetuuid(){
//        CassandraConnection connection = new CassandraConnection();
//        connection.connect();
//
//        //connection.storeWithOriginalValue(1,UUIDs.timeBased(),"val2","val3",Boolean.TRUE,100L,Instant.now().toEpochMilli());
//        //connection.storeWithOriginalValue(3,null,null,null,Boolean.TRUE,9L,Instant.now().toEpochMilli());
//        //connection.storeWithOriginalValue(3,null,null,null,Boolean.FALSE,0L,null);
////        connection.storeWithUnset(4,null,null,null,null,null,null);
//        //connection.storeWithUnset(1,UUIDs.timeBased(),"val2","val3",Boolean.TRUE,100L,Instant.now().toEpochMilli());
//        connection.storeWithUnset(5,null,null,null,Boolean.FALSE,0L,null);
//
//        connection.disconnect();
//        System.out.println("Done!");
//    }
//
//    public static void saveTarget(){
//        CassandraConnection connection = new CassandraConnection();
//        connection.connect();
//
//        TargetEntity entity = new TargetEntity();
//        entity.setCampaignId(UUID.fromString("8b4ca5a0-f43d-11ea-bdfd-63fdf1c8380c"));
//        entity.setBearer("ROH");
//        entity.setIccid("89448000000000010005");
//        //entity.setScenarioId(UUID.fromString("8b437de0-f43d-11ea-bdfd-63fdf1c8380c"));
//        entity.setState("CREATED");
//        entity.setUpdateTime(Instant.now().toEpochMilli());
//
//        ResultSetFuture future = connection.storeAsync(entity);
//        future.getUninterruptibly();
//
//        connection.disconnect();
//
//        System.out.println("Done!");
//    }
//
//    public static void main(String[] args){
////        saveTarget();
//        saveUnsetuuid();
//    }

}
