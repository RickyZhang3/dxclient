package org.com.dx.dao;

import org.com.dx.bean.HisYxResultBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description: TODO
 * @Author xinghua.zhang
 * @Date 2019/12/27
 **/

public class HisYxResultBeanMapper implements RowMapper<HisYxResultBean> {
    @Override
    public HisYxResultBean mapRow(ResultSet rs, int i) throws SQLException {
        HisYxResultBean hisYxResultBean = new HisYxResultBean();

        hisYxResultBean.setFeedbackSecId(rs.getString("feedback_sec_id"));
        hisYxResultBean.setFeedbackName(rs.getString("feedback_name"));
        hisYxResultBean.setCustName(rs.getString("cust_name"));
        hisYxResultBean.setWeChat(rs.getString("wechat"));
        hisYxResultBean.setCompany(rs.getString("company"));
        hisYxResultBean.setBudget(rs.getString("budget"));
        hisYxResultBean.setDemand(rs.getString("demand"));
        hisYxResultBean.setRemark(rs.getString("remark"));
        hisYxResultBean.setLableId(rs.getString("lable_id"));
        hisYxResultBean.setTalkDetail(rs.getString("talk_detail"));
        hisYxResultBean.setDmpLableName(rs.getString("dmp_lable_name"));
        return hisYxResultBean;
    }
}
