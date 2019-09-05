package com.bootdo.xjbg.vo;

import java.util.Date;

/**
 * @program: bootdo
 * @description: 工单记录视图模型
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-08-08 15:56
 **/
public class OrderVo {
    /**房间号**/
    private String roomNo;
    /**房间类型**/
    private String roomType;
    /**入住类型**/
    private String orderType;
    /**房间价格**/
    private int roomPrice;
    /**实际房费**/
    private int realityPrice;
    /**其他消费**/
    private int otherConsumption;
    /**入住天数**/
    private int checkInDays;
    /**总消费**/
    private int totalFee;
    /**是否欠款**/
    private Boolean isDebt;
    /**是否退房**/
    private Boolean isCheckOut;
    /**入住时间**/
    private Date checkInTime;
    /**退房时间**/
    private Date checkOutTime;
    /**开房操作人**/
    private String inOption;
    /**退房操作人**/
    private String outOption;

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getRealityPrice() {
        return realityPrice;
    }

    public void setRealityPrice(int realityPrice) {
        this.realityPrice = realityPrice;
    }

    public int getOtherConsumption() {
        return otherConsumption;
    }

    public void setOtherConsumption(int otherConsumption) {
        this.otherConsumption = otherConsumption;
    }

    public int getCheckInDays() {
        return checkInDays;
    }

    public void setCheckInDays(int checkInDays) {
        this.checkInDays = checkInDays;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public Boolean getDebt() {
        return isDebt;
    }

    public void setDebt(Boolean debt) {
        isDebt = debt;
    }

    public Boolean getCheckOut() {
        return isCheckOut;
    }

    public void setCheckOut(Boolean checkOut) {
        isCheckOut = checkOut;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getInOption() {
        return inOption;
    }

    public void setInOption(String inOption) {
        this.inOption = inOption;
    }

    public String getOutOption() {
        return outOption;
    }

    public void setOutOption(String outOption) {
        this.outOption = outOption;
    }
}
