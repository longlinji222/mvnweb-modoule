package com.company.mybatis.commn;

import java.util.List;
/**
 * 
 * @author ray <a href='mailto:in.think@163.com'>in.think@16.com</a>
 * @version 1.0
 *
 * @param <T>查询出的集合中的实体对象
 * 
 * 
 */
public class PageBean<T>{

	/**ORACLE数据库**/
	public final static int ORACLE=1; 
	/**SQLSERVER数据库**/
	public final static int SQLSERVER=2; 
	/**MYSQL数据库**/
	public final static int MYSQL=3;  
	
	/**数据库类型（使用ORACLE，SQLSERVER，MYSQL常量标识）**/
	private int dbType=ORACLE;

	
	/**当前是第几页**/
	private int curPage=1;
	/**一共有多少行**/
	private int maxRow;
	/**总页数**/
	private int maxPage;
	/**每页多少行**/
	private int rowsPerPage=5;
	/**本页中显示的数据**/
	private List<T> data;//
	
	
	/**前置条件，主要用于查询指定列，以Select开头，如select ename,sal**/
	private String preSQL="select * ";
	/**查询条件**/
	private String condition="";
	/**要查询的表名(多表连接时，事实表必须在前，必须同时设置表别名tableAlias属性)**/
	private String tableName="";
	/**表别名(多表条件下设置，设置事实表的别名，单表可不设置)**/
	private String tableAlias=""; 
	/**根据那个字段排序,Oracle中一般必须指定**/
	private String sort="";
	/**排序方式，默认升序**/
	private String sortOrder="asc";
	
	/**
	 * 根据属性信息获得分页SQL语句
	 * @return 返回根据表信息和条件自动生成当前dbType对应的数据库的分页SQL语句
	 */
	public String getSql() {
		String sql="";
		int start=(curPage-1)*rowsPerPage;  //开始量
		int end=start+rowsPerPage; //结束量
		if(dbType==ORACLE){
			sql ="select B.* from (select  A.*,rownum r  from ("+preSQL+" from  "+tableName+" where 1=1 ";
			if(isNotNullOrEmpty(condition)){
				sql+=condition;
			}
			if(isNotNullOrEmpty(sortOrder)){
				sql+=" order by "+sort;
				if(isNotNullOrEmpty(sortOrder)){
					sql+=" "+sortOrder;
				}
				if(isNotNullOrEmpty(tableAlias)){
					sql+=","+tableAlias+".rowid";;
				}else{
					sql+=",rowid";
				}
			}
			sql+=")   A  where  rownum<="+end+"  ) B where  B.r>"+start;  //分页语句
			
		}else if(dbType==SQLSERVER){
			
			if(isNotNullOrEmpty(preSQL)){
				preSQL=preSQL.substring(preSQL.toLowerCase().indexOf("select")+6);
			}else{
				preSQL="*";
			}
			
			
			sql="select top "+rowsPerPage+" "+preSQL+" from "+tableName+" where "+sort+" not in(select top "+start+" "+sort+" from "+tableName+" where 1=1";
			//如果存在查询条件，根据条件查询
			if(isNotNullOrEmpty(condition)){
				sql+=" "+condition;
			}
			
			sql+=" order by "+sort+" "+sortOrder+")";

		}else if(dbType==MYSQL){

			sql=preSQL+" from "+tableName+" where 1=1 ";
			if(isNotNullOrEmpty(condition)){
				sql+=condition;
			}
			if(isNotNullOrEmpty(sortOrder)){
				sql+=" order by "+sort;
				if(isNotNullOrEmpty(sortOrder)){
					sql+=" "+sortOrder;;
				}
			}
			sql+=" limit "+start+","+rowsPerPage;  //分页语句
		}
		
		return sql;
	}
	/**
	 * 获得查询数据总条数的SQL语句
	 * @return 返回根据表信息和条件自动生成当前dbType对应的数据库的查询总条数SQL语句
	 */
	public String getSqlCount(){
		String sqlCount="select count(*) from "+tableName+" where 1=1 "; //计算总数量语句
		if(isNotNullOrEmpty(condition)){
			sqlCount+=condition;
		}

		return sqlCount;
	}
	/**
	 * 判断字符串是否非空
	 * @param s 要判断的字符串
	 * @return 判断结果
	 */
	public static boolean isNotNullOrEmpty(String s){
		if (null==s){
			return false;
		}
		if (s.trim().equals("")){
			return false;
		}
		return true;
	} 

	
	/**
      * 根据总条数和每页显示数量得到总共有多少页
	 * @return 最大可分的的页数
	 */
	public int getMaxPage() {
		return maxPage=(this.getMaxRow() - 1) / this.getRowsPerPage() + 1;
	}
	
	/**
      * 得到每页显示多少条记录
	 * @return 每页显示数量
	 */
	public int getRowsPerPage() {
		return 0==rowsPerPage?5:rowsPerPage;
	}
	
	
	/**
	 * 获得总行数
	 * @return 总记录数
	 */
	public int getMaxRow() {
		return maxRow;
	}
	/**
	 * 设置总行数
	 * @param maxRow 总行数
	 */
	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}
	
	/**
	 * 获得查询出的数据集合
	 * @return 当前页的数据集合
	 */
	public List<T> getData() {
		return data;
	}
	/**
	 * 设置数据集合
	 * @param data 页面数据集合
	 */
	public void setData(List<T> data) {
		this.data = data;
	}
	/**
	 * 设置每页显示条数
	 * @param rowsPerPage 每页数量
	 */
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	/**
	 * 获得当前页数
	 * @return 当前页数
	 */
	public int getCurPage() {
		return curPage;
	}
	/**
	 * 设置当前要查看的页数
	 * @param curPage 当前页数
	 */
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	/**
	 * 获得排序的字段名
	 * @return 排序字段名
	 */
	public String getSortOrder() {
		return sortOrder;
	}
	/**
	 * 设置排序字段名
	 * @param order 排序字段名
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * 获得排序规则（desc降序，asc升序）
	 * @return desc（降序）或asc（升序）
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * 设置排序规则（desc降序，asc升序）
	 * @param sort desc（降序）或asc（升序）
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * 获得当前操作的表名信息
	 * @return 表名信息
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * 设置要操作的表名信息
	 * @param tableName 表名信息
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * 获得查询语句的条件
	 * @return 获得条件
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * 设置查询条件
	 * @param condition 查询条件
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
    /**
     * 获得前置SQL语句
     * @return 前置SQL语句
     */
	public String getPreSQL() {
		return preSQL;
	}
    /**
     * 设置前置SQL语句
     * @param preSQL 前置SQL语句
     */
	public void setPreSQL(String preSQL) {
		this.preSQL = preSQL;
	}
	/**
	 * 获得事实表表别名
	 * @return 事实表别名信息
	 */
	public String getTableAlias() {
		return tableAlias;
	}
	/**
	 * 设置事实表表别名
	 * @param tableAlias 事实表表别名
	 */
	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}
	/**
	 * 获得数据库类型（ORACLE=1，SQLSERVER=2，MYSQL=3 ）
	 * @return ORACLE=1，SQLSERVER=2，MYSQL=3
	 */
	public int getDbType() {
		return dbType;
	}
	/**
	 * 设置数据库类型（ORACLE=1，SQLSERVER=2，MYSQL=3 ）
	 * @param dbType PageBean.ORACLE，PageBean.SQLSERVER，PageBean.MYSQL
	 */
	public void setDbType(int dbType) {
		this.dbType = dbType;
	}
	
	public static void main(String[] args) {
		/**
		 * 普通查询示例1
		 */
		PageBean p=new PageBean();
//		p.setDbType(SQLSERVER);
		p.setTableName("emp");
		p.setPreSQL("select empno,ename,sal");
		p.setSort("sal");
		p.setSortOrder("desc");
		p.setCurPage(2);
		System.out.println(p.getSql());
		System.out.println(p.getSqlCount());
	
		
		/**
		 * 普通查询示例2
		 */
		PageBean p2=new PageBean();
		p2.setTableName("emp e");
		p2.setPreSQL("select e.empno,e.ename,e.sal");
		p2.setSort("e.sal");
		p2.setSortOrder("desc");
		p2.setCurPage(1);
		System.out.println(p2.getSql());
		System.out.println(p2.getSqlCount());
		
		
		/**
		 * 联合查询示例1
		 */
		PageBean p3=new PageBean();
		p3.setTableName("emp e,dept d"); 
		p3.setPreSQL("select e.empno,e.ename,e.sal,d.dname");
		p3.setTableAlias("e");  //多表情况下必须设置事实表别名
		p3.setCondition(" and e.deptno=d.deptno ");
		p3.setSort("e.sal");
		p3.setSortOrder("desc");
		p3.setCurPage(1);
		System.out.println(p3.getSql());
		System.out.println(p3.getSqlCount());
		
		
		/**
		 * 联合查询示例2
		 */
		PageBean p4=new PageBean();
		p4.setTableName("emp e inner join dept d on e.deptno=d.deptno");
		p4.setTableAlias("e"); //多表情况下必须设置事实表别名
		p4.setCurPage(1);
		p4.setPreSQL("select e.empno,e.ename,e.sal,d.dname");
		System.out.println(p4.getSql());
		System.out.println(p4.getSqlCount());
		
		
	}
}
