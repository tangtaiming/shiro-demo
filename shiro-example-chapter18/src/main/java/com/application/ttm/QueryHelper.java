package com.application.ttm;

/**
 *
 * 调用方式】 QueryHelper helper = new QueryHelper(Reply.class, "r")//
 * .addCondition("r.topic=?", topic)// .addOrderProperty("r.postTime", true);
 *
 * PageBean pageBean = replyService.getPageBean(pageNum, pageSize, helper);
 * 或者添加condition和orderby之后直接调用preparePageBean
 */

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBetweenExpr;
import com.alibaba.druid.sql.builder.FunctionBuilder;
import com.alibaba.druid.sql.builder.SQLSelectBuilder;
import com.alibaba.druid.sql.builder.impl.SQLSelectBuilderImpl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 用于生成 select .... 必须select，from前面的 需要查找哪些字段，如果全部就用*表示
 *
 * 用于生成sqlExceptSelect from ... 必须 哪个表 where 可选 条件1 and 条件2 and ... 条件n brder by
 * 可选 属性1, 属性2, ... 属性n 用于辅助拼接SQL语句
 *
 *
 * 支持方法连缀 生成select和sqlExceptSelect语句 $生成参数列表$ $生成查询总记录数的SQL，去除掉OrderBy$
 *
 * @author 熊诗言 StringBuffer代替String应该还能提升性能
 */
public class QueryHelper{


    private String       select;                                  // select语句
    private String       fromClause;                              // from子句
    private StringBuffer whereClause   = new StringBuffer("");    // where子句
    private StringBuffer orderByClause = new StringBuffer("");    // order by子句


    private List<Object> parameters    = new ArrayList<Object>(); // 参数列表


    /**
     * 用于一张表的情况，生成From子句
     *
     * from topic t
     */
    public QueryHelper(String select, String tableName, String alias){
        this.select = select;
        fromClause = new StringBuffer(" from ").append(tableName).append(" ").append(alias).toString();
    }


    /**
     * 用于left join 这种可以写在一个from字句中
     */
    public QueryHelper(String select, String from){
        this.select = select;
        fromClause = new StringBuffer(" from ").append(from).toString();
    }


    /**
     * 用于两张表联合的情况，生成From子句，类似from table1 a,table2 b 然后添加where条件
     * 这个方法的调用格式为("select...","table1","alias1","table2","alias2"...),至少两对，
     * 如果只有一个表， 那就按照第一个构造方法调用 。后面的表和别名是一对一对出现的
     */
    public QueryHelper(String select, String[] table_alias){
        if(table_alias.length <= 2 || table_alias.length % 2 != 0){
            throw new RuntimeException("你的参数有误，请看该方法的说明");
        }
        this.select = select;
        StringBuffer buffer = new StringBuffer(" from ");


        int group = table_alias.length / 2;// 有这么多个表
        for(int i = 0; i < group; i++){
            buffer.append(table_alias[i * 2]).append(" " + table_alias[i * 2 + 1]).append(",");
        }
        buffer.deleteCharAt(buffer.length() - 1);
        fromClause = buffer.toString();
    }


    /**
     * 拼接where子句 d.id between ? and ? d.parent=? d.parent is null
     *
     * @param condition
     * @param params
     */
    public QueryHelper addCondition(String condition, Object... params){
        // 拼接
        if(whereClause.length() == 0){
            whereClause = new StringBuffer(" where ").append(condition);
        } else{
            whereClause.append(" and ").append(condition);
        }


        // 参数
        if(params != null){
            for(Object p : params){
                parameters.add(p);
            }
        }


        return this;
    }


    /**
     * 如果第一个参数为true，则拼接where子句
     *
     * @param append
     * @param condition
     * @param params
     */
    public QueryHelper addCondition(boolean append, String condition, Object... params){
        if(append){
            addCondition(condition, params);
        }
        return this;
    }


    /**
     * 拼接order by子句
     *
     * @param propertyName
     *            参与排序的属性名
     * @param asc
     *            true表示升序，false表示降序
     */
    public QueryHelper addOrderProperty(String propertyName, boolean asc){
        if(orderByClause.length() == 0){
            orderByClause = new StringBuffer(" order by ").append(propertyName + (asc ? " asc" : " desc"));
        } else{
            orderByClause.append(", ").append(propertyName + (asc ? " asc" : " desc"));
        }
        return this;
    }


    /**
     * 如果第一个参数为true，则拼接order by子句
     *
     * @param append
     * @param propertyName
     * @param asc
     */
    public QueryHelper addOrderProperty(boolean append, String propertyName, boolean asc){
        if(append){
            addOrderProperty(propertyName, asc);
        }
        return this;
    }


    public String getSelect(){
        return this.select;
    }


    /**
     * 获取生成的用于查询数据列表的sqlExceptSelect语句
     *
     * @return
     */
    public String getSqlExceptSelect(){
        // 这里面含有一些?，下面来填充
        String sqlExceptSelect = new StringBuffer(fromClause).append(whereClause).append(orderByClause).toString();
        // 填充参数
        if(parameters != null){ // 设置参数
            for(int i = 0; i < parameters.size(); i++){
                // 巧妙利用替换一次之后，后面的?就自动往前移动一位，那么replaceFirst每次替换的就是下一个?
                sqlExceptSelect = sqlExceptSelect.replaceFirst("\\?", "'" + parameters.get(i) + "'");
            }
        }
        return sqlExceptSelect;
    }


    /**
     * 获取生成的用于查询总记录数的SQL语句
     *
     * @return
     */
    public String getCountQuerySql(){
        return "select count(*) " + fromClause + whereClause;
    }


    /**
     * 获取SQL中的参数值列表
     *
     * @return
     */
    public Object[] getParameters(){
        return parameters.toArray();
    }


    /**
     * 获取生成的用于查询数据列表的sqlExceptSelect语句，里面可能含有?，需要配合getParameters
     *
     * @return
     */
    public String getSqlExceptSelectWithPadding(){
        // 这里面含有一些?，下面来填充
        String sqlExceptSelect = new StringBuffer(fromClause).append(whereClause).append(orderByClause).toString();
        return sqlExceptSelect;
    }

    public static void main(String[] args){
//        QueryHelper helper = new QueryHelper("select a.*,b.*", "table1 a");
//        helper.addCondition("a.parentId=b.id");
//        helper.addCondition("a.table", "1");
//        System.out.println(helper.getSelect());
//        System.out.println(helper.getSqlExceptSelectWithPadding());


//        ConcurrentHashMap<String, String> searchInfo = new ConcurrentHashMap<String, String>();
//        searchInfo.put("name", "颜色");


//        QueryHelper helper2 = SkuService.service.getQueryHelper(searchInfo);
//        System.out.println(helper2.select + helper2.getSqlExceptSelectWithPadding());

//        Map<String, Object> query = new HashMap<>();
//        query.put("sku > ", "aaa");
//        SqlHelper sqlHelper = new SqlHelper();
//        sqlHelper.set()

//        String sql = sqlHelper.select("*", true).where(query, true).limit(1, 20).getCompiledSelect("syn_product", true);
//        System.out.println(sql);

        SQLExpr sqlExpr = new SQLBetweenExpr();
        sqlExpr.addAfterComment("a");
        FunctionBuilder functionBuilder = new FunctionBuilder("mysql");
        functionBuilder.ltrim(sqlExpr);

        SQLSelectBuilder selectBuilder = new SQLSelectBuilderImpl("mysql");
//        selectBuilder.
//        String s = selectBuilder.from("sys_product").whereAnd("a > 1").whereAnd("b > 3").whereAnd("c ");
//        System.out.println(s);
    }
}