package com.java.dao.impl;

import com.java.dao.AuthManageDao;
import com.java.pojo.AuthManage;
import com.java.util.EntityMapTransUtils;
import com.java.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * description: 数据库交互
 * author: ws
 * time: 2019/11/20 20:29
 */
@Repository
public class AuthManageDaoImpl implements AuthManageDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 新增授权信息
     * @param authManage
     * @return
     */
    @Override
    public String createAuthManage(AuthManage authManage) {
        StringBuilder sql = new StringBuilder("INSERT INTO `fp_auth_management`(`uuid`, `project_name`, `envir_head`, `phone`, `provinces`, `cities`, `address`, `mac`," +
                " `master_ip`, `download_time`, `envir_note`, `sn_file`, `feedback`, `note`, `create_time`, `update_time`) VALUES (:uuid, :projectName, :envirHead, :phone, :provinces, :cities, :address, :mac," +
                " :masterIp, :downloadTime, :envirNote, :snFile, :feedback, :note, :createTime, :updateTime)");

        //指定部分字段值
        authManage.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
        authManage.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        authManage.setUpdateTime(null);

        //EntityMapTransUtils.entityToMap1(authManage)实体类转map
        int count = namedParameterJdbcTemplate.update(sql.toString(), EntityMapTransUtils.entityToMap1(authManage));
        if (count > 0){
            System.out.println("authManage.getUuid()值为" + authManage.getUuid());
            return authManage.getUuid();
        } else {
            return "0";
        }

    }

    /**
     * 获取所有授权信息（包括条件查询）
     * @param page
     * @param authManage
     * @return
     */
    @Override
    public List<AuthManage> getAllAuthManage(Page page, AuthManage authManage) {
        //sql:返回所有数据
        StringBuilder sql = new StringBuilder("SELECT * FROM fp_auth_management where 1=1 and is_available != '1' ");
        Map<String, Object> parames = null; //获取界面输入的关键字等条件参数
        Map<String, Object> map = concatSql(sql, authManage);   //封装条件查询，结果是：无额外条件sql不变，含条件sql改变，返回的value分别有StringBuilder类型的sql,map类型的关键字等条件参数
        //经过带指定查询条件后的sql
        sql = (StringBuilder) map.get("sql");   //封装后的sql
        parames = (Map<String, Object>) map.get("parames");
        //分页
        //1.先统计数据总量
        StringBuilder countsql = new StringBuilder("select count(*) as totalrows from fp_auth_management where 1=1 and is_available != '1' ");
        if(page!=null){ //页数，每页多少行数据是前端设定的，默认page=null,即展示首页，如果界面点击第2页，则接口中传参page=2，以此类推
            map = concatSql(countsql, authManage);  //同上，无额外条件返回数据总量，有条件，返回带条件后的数据总量，按精确条件查询返回的数据量条数
            countsql = (StringBuilder) map.get("sql");  //封装后的sql
            List<Map<String, Object>> count = namedParameterJdbcTemplate.queryForList(countsql.toString(), parames);    //以parames类型统计封装后的数据量
            if (count.get(0).get("totalrows") != null){
                int total = Integer.valueOf(count.get(0).get("totalrows").toString());
                page.setTotalRows(total);       //总条数
            }
            //加上分页查询
            sql.append(" limit " + page.getRowStart() + "," + page.getPageSize());  //   分别是前端指定那一页的起始行,那一页的返回条数,意思就是指定前端界面选取那一页的进行查询，返回那一页的全部数据
        }
        return namedParameterJdbcTemplate.query(sql.toString(), parames, new BeanPropertyRowMapper<>(AuthManage.class));
    }








    /**
     * 封装"条件查询"方法,根据额外已选条件进行精确查询,返回所有结果
     * @param sql
     * @param authManage
     * @return
     */
    private Map<String, Object> concatSql(StringBuilder sql,AuthManage authManage){
        Map<String, Object> resultMap = new HashMap<>();    //用于条件查询后拼接得到的sql
        Map<String, Object> parames = new HashMap<>();      //
        if (authManage != null){ //如果对象值不为空
            if (StringUtils.isNotBlank(authManage.getProjectName())){ //如果项目名称条件不为空
                sql.append(" and project_name in (:projectNames) ");    //可以同时选择多个项目
                List<String> projectNames = EntityMapTransUtils.StringToList(authManage.getProjectName());
                parames.put("projectNames", projectNames);  //插入map
            }
            if (StringUtils.isNotBlank(authManage.getEnvirNote())){ //如果环境信息(线上、研发、已停用)不为空
                sql.append(" and envir_note =:envirNote "); //只能选择三种条件之一
                parames.put("envirNote", authManage.getEnvirNote());
            }
            if (StringUtils.isNotBlank(authManage.getCities())){ // 安装地区不为空
                sql.append(" and cities in (:cities) ");    //可同时多选
                List<String> cities = EntityMapTransUtils.StringToList(authManage.getCities());
                parames.put("cities", cities);
            }
            if (StringUtils.isNotBlank(authManage.getFeedback())){ //如果授权反馈不为空
                sql.append(" and feedback =:feedback");
                parames.put("feedback", authManage.getFeedback());
            }
            if (StringUtils.isNotBlank(authManage.getStartTime()) && StringUtils.isNotBlank(authManage.getEndTime())){  //下载证书日期范围
                sql.append(" and download_time between :startTime and :endTime ");
                parames.put("startTime", authManage.getStartTime());
                parames.put("endTime", authManage.getEndTime());
            }
            if(StringUtils.isNotBlank(authManage.getKeyWord())){    //关键字不为空,需模糊检索
                sql.append(" and(project_name like concat('%',keyWord,'%') or envir_head like concat('%',:keyWord,'%') or phone like concat('%',:keyWord,'%') or provinces like concat('%',:keyWord,'%') or cities like concat('%',:keyWord,'%') or master_ip like concat('%',:keyWord,'%') )");
                parames.put("keyWord", authManage.getKeyWord());
            }
            if (StringUtils.isNotBlank(authManage.getSortField())){
                if ("desc".equals(authManage.getSortField())){
                    sql.append(" ORDER BY download_time DESC ");
                } else if ("asc".equals(authManage.getSortField())){
                    sql.append(" ORDER BY download_time ASC");
                }
            }
        }
        resultMap.put("sql", sql);   //将按照条件查询后拼接的sql作为resultMap的value
        resultMap.put("parames", parames);  //获取界面输入的关键字等条件参数,value为map形式
        return resultMap;
    }


}
