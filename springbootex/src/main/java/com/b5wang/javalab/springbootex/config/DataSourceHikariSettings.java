package com.b5wang.javalab.springbootex.config;

import lombok.Data;

import java.util.Properties;

@Data
public class DataSourceHikariSettings {

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private int minimumIdle;
    private int maximumPoolSize;
    private String connectionTestQuery;
    private int maxLifetime;
    private int connectionTimeout;
    private String poolName;
    private boolean autoCommit;

    private Properties dataSourceProperties = new Properties();

    public int getPrepStmtCacheSize(){
        return Integer.valueOf((String)dataSourceProperties.get("prepStmtCacheSize"));
    }

    public int getPrepStmtCacheSqlLimit(){
        return Integer.valueOf((String)dataSourceProperties.get("prepStmtCacheSqlLimit"));
    }

    public boolean getCachePrepStmts(){
        return Boolean.valueOf((String)dataSourceProperties.get("cachePrepStmts"));
    }

    public boolean getUseServerPrepStmts(){
        return Boolean.valueOf((String)dataSourceProperties.get("useServerPrepStmts"));
    }

    public boolean getRewriteBatchedStatements(){
        return Boolean.valueOf((String)dataSourceProperties.get("rewriteBatchedStatements"));
    }

    public boolean getCacheServerConfiguration(){
        return Boolean.valueOf((String)dataSourceProperties.get("cacheServerConfiguration"));
    }

    public boolean getCacheResultSetMetaData(){
        return Boolean.valueOf((String)dataSourceProperties.get("cacheResultSetMetaData"));
    }

    public int getMetadataCacheSize(){
        return Integer.valueOf((String)dataSourceProperties.get("metadataCacheSize"));
    }

    public boolean getMaintainTimeStats(){
        return Boolean.valueOf((String)dataSourceProperties.get("maintainTimeStats"));
    }

    public boolean getUseCursorFetch(){
        return Boolean.valueOf((String)dataSourceProperties.get("useCursorFetch"));
    }

    public int getDefaultFetchSize(){
        return Integer.valueOf((String)dataSourceProperties.get("defaultFetchSize"));
    }

    public boolean getUseCompression(){
        return Boolean.valueOf((String)dataSourceProperties.get("useCompression"));
    }

}
