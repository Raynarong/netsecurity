package com.netsecurity.util;
import java.util.List;



import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;

import com.netsecurity.bean.GroupCheck;
final public class HibernateUtil {
	private static SessionFactory sessionFactory=null;
	//使用线程局部模式
	private static ThreadLocal<Session> threadLocal=new ThreadLocal<Session>();
	private HibernateUtil(){};
	static {
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	//获取全新的全新的sesession
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	//获取和线程关联的session
	public static Session getCurrentSession(){
		
		Session session=threadLocal.get();
		//判断是否得到
		if(session==null){
			session=sessionFactory.openSession();
			//把session对象设置到 threadLocal,相当于该session已经和线程绑定
			threadLocal.set(session);
		}
		return session;
		
		
	}
	
	public static void closeCurrentSession(){
		
		Session s=getCurrentSession();
		
		if(s!=null&& s.isOpen() ){
			s.close();
			threadLocal.set(null);
		}
	}
	
	//这里提供一个根据id返回对象的方法
	public static Object findById(Class clazz,java.io.Serializable id){

		Session s=null;
		Transaction tx=null;
		Object obj=null;
		try {
			s=openSession();
			
			tx=s.beginTransaction();
			//load默认是懒加载的
			//obj=s.load(clazz, id);
			obj=s.get(clazz, id);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			if(s!=null&&s.isOpen()){
				s.close();
			}
			
		}
		
		return obj;
	}
	
	//统一的一个修改和删除(批量 hql) hql"delete upate ...??"
	public static boolean executeUpdate(String hql,String [] parameters){
		
		Session s=null;
		Transaction tx=null;
		int num=0;
		try {
			s=openSession();
			
			tx=s.beginTransaction();
			Query query=s.createQuery(hql);
			//先判断是否有参数要绑定
			if(parameters!=null&& parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i, parameters[i]);
				}
			}
			num=query.executeUpdate();
			tx.commit();
			if(num==0)
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			/**
			 * 抛异常？
			 */
			//throw new RuntimeException(e.getMessage());
		}finally{
			
			if(s!=null&&s.isOpen()){
				s.close();
			}
		}
		return true;
	}
	
	//如果要配置openSessionInView
	//统一的一个修改和删除(批量 hql) hql"delete upate ...??"
	public static void executeUpdateOpenInView(String hql,String [] parameters){
		
		
		
		
			Session s=getCurrentSession();
			
			
			Query query=s.createQuery(hql);
			//先判断是否有参数要绑定
			if(parameters!=null&& parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i, parameters[i]);
				}
			}
			query.executeUpdate();
			
		
		
	}
	
	//统一的添加的方法
	public  static void save(Object obj){
		Session s=null;
		Transaction tx=null;
		
		try {
			s=openSession();
			tx=s.beginTransaction();
			s.save(obj);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null){
				tx.rollback();
			}
			
			throw new RuntimeException(e.getMessage());
	
		}finally{
			if(s!=null && s.isOpen()){
				s.close();
			}
		}
		
	}
	//统一的添加的方法
	public  static boolean save2(Object obj){
		Session s=null;
		Transaction tx=null;
		Object result=null;
		try {
			s=openSession();
			tx=s.beginTransaction();
			
			result=s.save(obj);
			tx.commit();
			if(result!=null)
				return true;
			else
				return false;
		} catch (Exception e) {
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
			return false;
			//throw new RuntimeException(e.getMessage());
			
		}finally{
			if(s!=null && s.isOpen()){
				s.close();
			}
		}
		
	}
	
	
	//提供一个统一的查询方法(带分页) hql 形式 from 类  where 条件=? ..
	public static List executeQueryByPage(String hql,String [] parameters,int pageSize,int pageNow){
		Session s=null;
		List list=null;
		
		try {
			s=openSession();
			Query query=s.createQuery(hql);
			//先判断是否有参数要绑定
			if(parameters!=null&& parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i, parameters[i]);
				}
			}
			query.setFirstResult((pageNow-1)*pageSize).setMaxResults(pageSize);
			
			list=query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			
			if(s!=null&&s.isOpen()){
				s.close();
			}
			
		}
		return list;
	}
	public static void main(String[] args) {
		String sql="select l.lab_id, u.userid,u.username,max(case when l.lab_id=gu.groupid then 1 else 0 END) aa" +
				" from laboratory l LEFT OUTER JOIN userinfo u ON l.lab_id=u.lab_id LEFT JOIN " +
				"groupuser gu on gu.userid= u.userid where l.lab_id=1  GROUP BY u.userid";
//		sql="select * from laboratory";
		String[]parameters={};
		List<GroupCheck> list=HibernateUtil.executeSQL(sql, parameters);
		System.out.println(list.size());
		
	}
	
public static List executeSQLToCheckGroup(String sql,String []parameters,String maxAlias){
		
		Session s=null;
		List list=null;
		
		try {
			s=openSession();
			Query query=s.createSQLQuery(sql).addScalar(maxAlias, Hibernate.INTEGER)
			.addScalar("userid",Hibernate.STRING)
			.addScalar("username",Hibernate.STRING)
			.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  ;
			//先判断是否有参数要绑定
			if(parameters!=null&& parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i, parameters[i]);
				}
			}
			
			list=query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			
			if(s!=null&&s.isOpen()){
				s.close();
			}
			
		}
		return list;
		
	}
	
	public static List executeSQL(String sql,String []parameters){
		
		Session s=null;
		List list=null;
		
		try {
			s=openSession();
			Query query=s.createSQLQuery(sql).addScalar("inLab", Hibernate.INTEGER);  ;
			//先判断是否有参数要绑定
			if(parameters!=null&& parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i, parameters[i]);
				}
			}
			
			list=query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			
			if(s!=null&&s.isOpen()){
				s.close();
			}
			
		}
		return list;
		
	}
	//提供一个统一的查询方法 hql 形式 from 类  where 条件=? ..
	public static List executeQuery(String hql,String [] parameters){
		
		Session s=null;
		List list=null;
		
		try {
			s=openSession();
			Query query=s.createQuery(hql);
			//先判断是否有参数要绑定
			if(parameters!=null&& parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i, parameters[i]);
				}
			}
			list=query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			
			if(s!=null&&s.isOpen()){
				s.close();
			}
			
		}
		return list;
	}
	//try
public static List executeSqlQuery(String sql ){
		
		Session s=null;
		List list=null;
		
		try {
			s=openSession();
			Query query=s.createSQLQuery(sql);
			//先判断是否有参数要绑定
			 
			list=query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			
			if(s!=null&&s.isOpen()){
				s.close();
			}
			
		}
		return list;
	}
	//提供一个统一的查询方法 但是我们知道总是返回一个对象
	public static Object uniqueQuery(String hql,String [] parameters){
		
		Session s=null;
		Object obj=null;
		
		try {
			s=openSession();
			Query query=s.createQuery(hql);
			//先判断是否有参数要绑定
			if(parameters!=null&& parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i, parameters[i]);
				}
			}
			obj=query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			
			if(s!=null&&s.isOpen()){
				s.close();
			}
			
		}
		return obj;
	}
	
	
}
