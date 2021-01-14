package com.b5wang.javalab.springbootex.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "t_position")
public class Position {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "type")
    private String type;

    @Column(name = "product")
    private String product;

    @Column(name = "leverage")
    private long leverage;

    @Column(name = "direction")
    private String direction;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "volume")
    private BigDecimal volume;

    @Column(name = "open_time")
    private Long openTime;

    @Column(name = "order_price")
    private BigDecimal order_price;

    @Column(name = "order_volume")
    private BigDecimal order_volume;

    @Column(name = "order_time")
    private Long orderTime;

    @Column(name = "margin")
    private BigDecimal margin;

    @Column(name = "take_profit")
    private BigDecimal takeProfit;

    @Column(name = "stop_loss")
    private BigDecimal stopLoss;

    @Column(name = "status")
    private String status;

    @Column(name = "tick_time")
    private Long tickTime;

    @Column(name = "update_time")
    private Long updateTime;

    @Column(name = "profit_loss")
    private BigDecimal profit_loss;

    @Column(name = "init_volume")
    private BigDecimal initVolume;

    @Column(name = "remark")
    private String remark;

    @Column(name = "swap")
    private BigDecimal swap;

    @Column(name = "commission")
    private BigDecimal commission;

    @Column(name = "contract_size")
    private long contractSize;

    @Column(name = "margin_change_rate")
    private BigDecimal marginChangeRate;

    @Column(name = "min_price_offset")
    private BigDecimal minPriceOffset;

    @Column(name = "min_trans_volumes")
    private BigDecimal minTransVolumes;

    @Column(name = "digits")
    private long digits;

    @Column(name = "ask_spread_diff")
    private BigDecimal askSpreadDiff;

    @Column(name = "bid_spread_diff")
    private BigDecimal bidSpreadDiff;

    @Column(name = "max_price_offset")
    private BigDecimal maxPriceOffset;

    @Column(name = "profit_currency")
    private String profitCurrency;

    @Column(name = "base_currency")
    private String baseCurrency;

    @Column(name = "margin_currency")
    private String marginCurrency;

    @Column(name = "product_ask_spread")
    private BigDecimal productAskSpread;

    @Column(name = "product_bid_spread")
    private BigDecimal productBidSpread;

    @Column(name = "close_time")
    private Long closeTime;

    @Column(name = "close_volume")
    private BigDecimal closeVolume;

    @Column(name = "open_tick_no")
    private String openTickNo;

    @Column(name = "close_tick_no")
    private String closeTickNo;

    @Column(name = "position_id")
    private Long positionId;

    @Column(name = "profit")
    private BigDecimal profit;
}
