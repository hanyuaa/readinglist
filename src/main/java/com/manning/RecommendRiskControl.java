package com.manning;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by hanyu on 2018/9/6.
 */
public class RecommendRiskControl {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    //单用户每日限制～
    private Integer singleUserEachDayLimit = 50;

    //单用户每月限制～
    private Integer singleUserEachMonthLimit = 200;

    public Integer getSingleUserEachDayLimit() {
        return singleUserEachDayLimit;
    }

    public void setSingleUserEachDayLimit(Integer singleUserEachDayLimit) {
        this.singleUserEachDayLimit = singleUserEachDayLimit;
    }

    public Integer getSingleUserEachMonthLimit() {
        return singleUserEachMonthLimit;
    }

    public void setSingleUserEachMonthLimit(Integer singleUserEachMonthLimit) {
        this.singleUserEachMonthLimit = singleUserEachMonthLimit;
    }

    public static void main(String[] args) throws JsonProcessingException {
        RecommendRiskControl recommendRiskControl = new RecommendRiskControl();
        //String json = JsonHelper.defaultMapper().toJson(recommendRiskControl);
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        String s = mapper.writeValueAsString(recommendRiskControl);
        System.out.println(s);
        System.out.println(recommendRiskControl);
       /* RecommendRiskControl recommendRiskControl2  = JsonHelper.defaultMapper().fromJson(json,RecommendRiskControl.class);
        System.out.println(recommendRiskControl2);*/
    }
}
