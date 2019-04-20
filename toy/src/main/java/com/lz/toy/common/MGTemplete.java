package com.lz.toy.common;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.*;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.log4j.Log4j;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * mongoDb操作类
 * @author liuzhao
 *
 */
@Log4j
public class MGTemplete{
	
	/**
	 * mongo组件类
	 */
	private static MongoClient mongoClient;
	
	/**
	 * 构造类
	 * @param mongoIp		ip地址
	 * @param mongoPort		端口
	 */

	public MGTemplete(String mongoIp, int mongoPort, String userName, String password)
	{
		MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(10).build();
		log.debug("刘朝, 初始化MGdb");
		if(userName != null && !"".equals(userName) && password != null && !"".equals(password)){
			// 用户名 数据库 密码
			MongoCredential credential = MongoCredential.createCredential(userName, "admin", password.toCharArray());
			mongoClient = new MongoClient(new ServerAddress(mongoIp, mongoPort), Arrays.asList(credential), options);
		}else{
			mongoClient = new MongoClient(new ServerAddress(mongoIp, mongoPort), options);
		}
		
	}
	
	/**
	 * 通过数据库名和表名获取collection
	 * @param DbName		数据库名
	 * @param tableName		表名
	 * @return
	 */
	public static MongoCollection<Document> getCollection(String DbName, String tableName){
		MongoDatabase mongoDatabase = mongoClient.getDatabase(DbName);
		return mongoDatabase.getCollection(tableName);
	}
	
	/**
	 * 查询方法
	 * @param DbName		数据库名
	 * @param tableName		表名
	 * @param whereMap		查询条件
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public static <T> List<T> find(String DbName, String tableName,HashMap<String,Object> whereMap){
    	List<T> list = new ArrayList<T>();
    	Bson filter = new BasicDBObject(whereMap);
    	FindIterable<Document> documents =  getCollection(DbName,tableName).find(filter);
    	for (Document document : documents) {
    		list.add((T) document);
		}
    	return list;
    }
    
    /**
     * 查询方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param whereMap		查询条件
     * @param clazz			实体类
     * @param flag			是否保留 _id
     * @return
     */
    public static <T> List<T> find(String DbName, String tableName,HashMap<String,Object> whereMap,Class<T> clazz, Boolean flag){
    	List<T> list = new ArrayList<T>();
    	Bson filter = new BasicDBObject(whereMap);
    	FindIterable<Document> documents =  getCollection(DbName,tableName).find(filter);
    	for (Document document : documents) {
    		list.add((T) toBean(document,clazz,flag));
    	}
    	return list;
    }
    
    /**
     * 查询方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param whereMap		查询条件
     * @param sortMap		排序条件
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> find(String DbName, String tableName,HashMap<String,Object> whereMap,HashMap<String,Object> sortMap){
    	List<T> list = new ArrayList<T>();
    	Bson filter = new BasicDBObject(whereMap);
    	Bson sort = new BasicDBObject(sortMap);
    	FindIterable<Document> documents =  getCollection(DbName,tableName).find(filter).sort(sort);
    	for (Document document : documents) {
    		list.add((T) document);
    	}
    	return list;
    }
    
    /**
     * 查询方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param whereMap		查询条件
     * @param sortMap		排序条件
     * @param clazz			实体类
     * @return
     */
    public static <T> List<T> find(String DbName, String tableName,HashMap<String,Object> whereMap,HashMap<String,Object> sortMap,Class<T> clazz){
    	List<T> list = new ArrayList<T>();
    	Bson filter = new BasicDBObject(whereMap);
    	Bson sort = new BasicDBObject(sortMap);
    	FindIterable<Document> documents =  getCollection(DbName,tableName).find(filter).sort(sort);
    	for (Document document : documents) {
    		list.add((T) toBean(document,clazz,true));
    	}
    	return list;
    }
    
    /**
     * 查询方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param whereMap		查询条件
     * @param limit			限制条数
     * @param sortMap		排序条件
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> find(String DbName, String tableName,HashMap<String,Object> whereMap,int limit,HashMap<String,Object> sortMap){
    	List<T> list = new ArrayList<T>();
    	Bson filter = new BasicDBObject(whereMap);
    	Bson sort = new BasicDBObject(sortMap);
    	FindIterable<Document> documents =  getCollection(DbName,tableName).find(filter).limit(limit).sort(sort);
    	for (Document document : documents) {
    		list.add((T) document);
    	}
    	return list;
    }
    
    /**
     * 查询方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param whereMap		查询条件
     * @param limit			限制条数
     * @param sortMap		排序条件
     * @param clazz			实体类
     * @return
     */
    public static <T> List<T> find(String DbName, String tableName,HashMap<String,Object> whereMap,int limit,HashMap<String,Object> sortMap,Class<T> clazz){
    	List<T> list = new ArrayList<T>();
    	Bson filter = new BasicDBObject(whereMap);
    	Bson sort = new BasicDBObject(sortMap);
    	FindIterable<Document> documents =  getCollection(DbName,tableName).find(filter).limit(limit).sort(sort);
    	for (Document document : documents) {
    		list.add((T) toBean(document,clazz));
    	}
    	return list;
    }
    
    
    /**
     * 新增方法
     * @param <T>
     * @param DbName		数据库名
     * @param tableName		表名
     * @param entity		实体类
     */
    public static <T> void insertOne(String DbName, String tableName,T entity){
    	getCollection(DbName,tableName).insertOne(new Document(beanToMap(entity)));
    }
    
    /**
     * 批量新增方法
     * @param <T>
     * @param DbName
     * @param tableName
     * @param entitys
     */
    public static <T> void insertMany(String DbName, String tableName,List<T> entitys){
    	List<Document> list = new ArrayList<Document>();
    	for (T entity : entitys) {
    		list.add(new Document(beanToMap(entity)));
    	}
    	getCollection(DbName,tableName).insertMany(list);
    }

    
    /**
     * javaBean转map
     * @param obj	实体类
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> beanToMap(Object obj) { 
    	//如果是map,直接返回
    	if(obj instanceof Map){
    		return (Map<String, Object>) obj;
    	} 
    	
        Map<String, Object> params = new HashMap<String, Object>(0); 
        try { 
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj); 
            for (int i = 0; i < descriptors.length; i++) { 
                String name = descriptors[i].getName(); 
                if (!"class".equals(name)) { 
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name)); 
                } 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return params; 
    }
    
    /**
     * 将mongoDB每行数据转成实体类
     * @param document	每行数据
     * @param clazz		实体类
     * @return
     */
	public static <T> T toBean(Document document, Class<T> clazz){
    	document.remove("_id");
    	String json = JSONObject.toJSONString(document);
    	T javaObject = JSONObject.toJavaObject(JSONObject.parseObject(json),clazz); 
    	return  javaObject;
    }
	
	/**
	 * 将mongoDB每行数据转成实体类
	 * @param document	每行数据
	 * @param clazz		实体类
	 * @param flag		是否保留id字段（实体类唯一值是_id）
	 * @return
	 */
	public static <T> T toBean(Document document, Class<T> clazz,boolean flag){
		if(!flag){
			document.remove("_id");
		}
		String json = JSONObject.toJSONString(document);
		return JSONObject.toJavaObject(JSONObject.parseObject(json),clazz);
	}
	
    /**
     * 根据条件查询数量
     * @param DbName		数据库名
     * @param tableName		表名
     * @param whereMap		查询条件
     * @return  			记录条数
     */
	public static Long findCount(String DbName, String tableName,HashMap<String,Object> whereMap) {
    	Bson filter = new BasicDBObject(whereMap);
    	return getCollection(DbName,tableName).count(filter);
	}
	
	/**
	 * 根据条件查询数量
	 * @param DbName		数据库名
	 * @param tableName		表名
	 * @return  			记录条数
	 */
	public static Long findAllCount(String DbName, String tableName) {
		return getCollection(DbName,tableName).count();
	}
	
	/**分页查询方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param whereMap		查询条件
     * @param sortMap		排序条件
	 * @param page			当前显示页数
	 * @param pageSize		每页记录数
	 * @param pageCount		选取该页起始记录数
	 * @return 
	 */
	public static List<Document> find(String DbName, String tableName,HashMap<String,Object> whereMap,
    		HashMap<String,Object> sortMap, int page, int pageSize) {
    	List<Document> list = new ArrayList<Document>();
    	Bson filter = new BasicDBObject(whereMap);
    	Bson sort = new BasicDBObject(sortMap);
    	int pageCount = (page-1)*pageSize;
    	FindIterable<Document> documents =  getCollection(DbName,tableName).find(filter).sort(sort).skip(pageCount).limit(pageSize);
    	for (Document document : documents) {
    		list.add(document);
    	}
    	return list;
	}
	
	/**分页查询方法
	 * @param DbName		数据库名
	 * @param tableName		表名
	 * @param whereMap		查询条件
	 * @param sortMap		排序条件
	 * @param page			当前显示页数
	 * @param pageSize		每页记录数
	 * @param pageCount		选取该页起始记录数
	 * @param clazz			泛型对象
	 * @return 泛型对象序列
	 */
	public static <T> List<T> find(String DbName, String tableName,HashMap<String,Object> whereMap,
			HashMap<String,Object> sortMap, int page, int pageSize,Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		Bson filter = new BasicDBObject(whereMap);
		Bson sort = new BasicDBObject(sortMap);
		int pageCount = (page-1)*pageSize;
		FindIterable<Document> documents =  getCollection(DbName,tableName).find(filter).sort(sort).skip(pageCount).limit(pageSize);
		for (Document document : documents) {
			list.add((T) toBean(document,clazz));
		}
		return list;
	}

	/**分页查询方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param whereMap		查询条件
     * @param sortMap		排序条件
	 * @param page			当前显示页数
	 * @param pageSize		每页记录数
	 * @param pageCount		选取该页起始记录数
	 * @param clazz			泛型对象
	 * @param flag			转换类型时是否保留id字段（实体类唯一值是_id）
	 * @return 泛型对象序列
	 */
	public static <T> List<T> find(String DbName, String tableName,HashMap<String,Object> whereMap,
    		HashMap<String,Object> sortMap, int page, int pageSize,Class<T> clazz,Boolean flag) {
    	List<T> list = new ArrayList<T>();
    	Bson filter = new BasicDBObject(whereMap);
    	Bson sort = new BasicDBObject(sortMap);
    	int pageCount = (page-1)*pageSize;
    	FindIterable<Document> documents =  getCollection(DbName,tableName).find(filter).sort(sort).skip(pageCount).limit(pageSize);
    	for (Document document : documents) {
    		list.add((T) toBean(document,clazz,flag));
    	}
    	return list;
	}
    /**
     * 查询方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param whereMap		查询条件
     * @param sortMap		排序条件
     * @param clazz			实体类
     * @param flag			转换类型时是否保留id字段（实体类唯一值是_id）
     * @return
     */
    public static <T> List<T> find(String DbName, String tableName,HashMap<String,Object> whereMap,HashMap<String,Object> sortMap,Class<T> clazz,Boolean flag){
    	List<T> list = new ArrayList<T>();
    	Bson filter = new BasicDBObject(whereMap);
    	Bson sort = new BasicDBObject(sortMap);
    	FindIterable<Document> documents =  getCollection(DbName,tableName).find(filter).sort(sort);
    	for (Document document : documents) {
    		list.add((T) toBean(document,clazz,flag));
    	}
    	return list;
    }
    
    /**
     * 查询方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param groupByName	分组名称
     * @param sortMap		排序名称
     * @param limitCount	限制条数
     * @return
     */
    public static List<Object> groupBy(String DbName, String tableName,String groupByName,HashMap<String,Object> sortMap,int limitCount){
    	//结果集合
    	List<Object> list = new ArrayList<Object>();
    	//聚合参数
    	List<Bson> bson = new ArrayList<Bson>();
    	//group  
    	if(groupByName != null && !"".equals(groupByName)){
    		DBObject groupFields = new BasicDBObject( "_id", "$"+groupByName);   
    		groupFields.put("count", new BasicDBObject( "$sum", 1));   
    		Bson group = new BasicDBObject("$group", groupFields);  
    		bson.add(group);
    	}
        //sort  
    	if(sortMap != null && sortMap.size() > 0){
    		Bson sort = new BasicDBObject("$sort", sortMap);  
    		bson.add(sort);
    	}
        //limit  
    	if(limitCount != 0){
    		Bson limit = new BasicDBObject("$limit", limitCount);
    		bson.add(limit);
    	}
        
    	AggregateIterable<Document> documents = getCollection(DbName,tableName).aggregate(bson);
    	for (Document document : documents) {
    		list.add(document);
		}
    	return list;
    }
    
    /**
     * 查询方法
     * @param DbName		数据库名
     * @param tableName		表名
     * * @param wheremap	查询条件
     * @param groupByName	分组名称
     * @param sortMap		排序名称
     * @param limitCount	限制条数
     * @return
     */
	public static List<Object> groupBy(String DbName, String tableName,HashMap<String,Object> whereMap,String groupByName,HashMap<String,Object> sortMap,
    		int limitCount){
    	//结果集合
    	List<Object> list = new ArrayList<Object>();
    	//聚合参数
    	List<Bson> bson = new ArrayList<Bson>();
    	//条件
    	if(whereMap != null && !"".equals(whereMap)){
    		Bson filter = new BasicDBObject("$match",whereMap);
    		bson.add(filter);
    	}
    	
    	//group  
    	if(groupByName != null && !"".equals(groupByName)){
    		DBObject groupFields = new BasicDBObject( "_id", "$"+groupByName);   
    		groupFields.put("count", new BasicDBObject( "$sum", 1));   
    		Bson group = new BasicDBObject("$group", groupFields);  
    		bson.add(group);
    	}
        //sort  
    	if(sortMap != null && sortMap.size() > 0){
    		Bson sort = new BasicDBObject("$sort", sortMap);  
    		bson.add(sort);
    	}
        //limit  
    	if(limitCount != 0){
    		Bson limit = new BasicDBObject("$limit", limitCount);
    		bson.add(limit);
    	}
    	
    	AggregateIterable<Document> documents =  getCollection(DbName,tableName).aggregate(bson);
    	for (Document document : documents) {
    		list.add(document);
		}
    	return list;
    }
    
    /**
     * 管道方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param bson			条件
     */
	public static List<Object> aggregate(String DbName, String tableName,List<Bson> bson){
    	List<Object> list = new ArrayList<Object>();
    	//聚合参数
    	AggregateIterable<Document> documents = getCollection(DbName,tableName).aggregate(bson);
    	for (Document document : documents) {
    		list.add(document);
		}
    	return list;
    }
	/**
     * 管道方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param bson			条件
     * @param clazz			实体类
     * @param flag			转换类型时是否保留id字段（实体类唯一值是_id）
     * 
     */
	public static <T> List<T> aggregate(String DbName, String tableName,List<Bson> bson,Class<T> clazz,Boolean flag){
    	List<T> list = new ArrayList<T>();
    	//聚合参数
    	AggregateIterable<Document> documents = getCollection(DbName,tableName).aggregate(bson);
    	for (Document document : documents) {
    		list.add((T) toBean(document,clazz,flag));
		}
    	return list;
    }
	
    /**
     * 查询方法
     * @param DbName		数据库名
     * @param tableName		表名
     * * @param wheremap	查询条件
     * @param lookUpMap		连接条件
     * @param sortMap		排序名称
     * @param limitCount	限制条数
     * @return
     */
	public static List<Object> groupBy(String DbName, String tableName,HashMap<String,Object> whereMap,HashMap<String,Object> lookUpMap,HashMap<String,Object> sortMap){
    	//结果集合
    	List<Object> list = new ArrayList<>();
    	//聚合参数
    	List<Bson> bson = new ArrayList<>();
    	//条件
    	if(whereMap != null && !"".equals(whereMap)){
    		Bson filter = new BasicDBObject("$match",whereMap);
    		bson.add(filter);
    	}
    	
    	//lookup 
    	if(lookUpMap != null && lookUpMap.size() > 0){
    		Bson lookup = new BasicDBObject("$lookup", lookUpMap);  
    		bson.add(lookup);
    	}
        //sort  
    	if(sortMap != null && sortMap.size() > 0){
    		Bson sort = new BasicDBObject("$sort", sortMap);  
    		bson.add(sort);
    	}
    	
    	AggregateIterable<Document> documents =  getCollection(DbName,tableName).aggregate(bson);
    	for (Document document : documents) {
    		list.add(document);
		}
    	return list;
    }
    
    /**
     * 查询方法(多表关联)
     * @param DbName		数据库名
     * @param tableName		表名
     * * @param wheremap	查询条件
     * @param lookUpMap		连接条件
     * @param sortMap		排序名称
     * @param limitCount	限制条数
     * @param projectMap    挑选字段
     * @param Class<T> clazz 实体类
     * @return
     */
    //$match $looup $project
    @SuppressWarnings("unchecked")
	public static<T> List<T> aggregateTable (
    		String DbName, String tableName,HashMap<String,Object> whereMap,
    		HashMap<String,Object> lookUpMap,HashMap<String,Object> sortMap,HashMap<String,Object> projectMap,Class<T> clazz){
    	//结果集合
    	List<T> list = new ArrayList<>();
    	//聚合参数
    	List<Bson> bson = new ArrayList<>();
    	//条件
    	if(whereMap != null && !"".equals(whereMap)){
    		Bson filter = new BasicDBObject("$match",whereMap);
    		bson.add(filter);
    	}
    	
    	//lookup 
    	if(lookUpMap != null && lookUpMap.size() > 0){
    		Bson lookup = new BasicDBObject("$lookup", lookUpMap);  
    		bson.add(lookup);
    	}
        //sort  
    	if(sortMap != null && sortMap.size() > 0){
    		Bson sort = new BasicDBObject("$sort", sortMap);  
    		bson.add(sort);
    	}
    	//project
    	if(projectMap != null && projectMap.size() > 0){
    		Bson project = new BasicDBObject("$project", projectMap);  
    		bson.add(project);
    	}
    	
    	AggregateIterable<Document> documents =  getCollection(DbName,tableName).aggregate(bson);
    	for (Document document : documents) { 
    		List<Document> documentList = (List<Document>) document.get("resultDocs");
    		for (Document document2 : documentList) {				
    				list.add((T) toBean(document2,clazz));
			}
		}
    	return list;
    }
    
    
    /**
     * 业户查岗 ->查询方法(多表关联)分页
     * @param DbName		数据库名
     * @param tableName		表名
     * @param whereMap		查询条件
     * @param unwindMap		解压条件
     * @param lookUpMap		连接条件
     * @param sortMap		排序名称
     * @param page			当前显示页数
	 * @param pageSize		每页记录数
     * @param projectMap    挑选字段
     * @param Class<T> clazz 实体类
     * @return
     */
    // $match $lookup $unwind $project
	public static<T> List<T> aggregateTable (
    		String DbName, String tableName,HashMap<String,Object> whereMap,HashMap<String,Object> unwindMap,
    		HashMap<String,Object> lookUpMap,HashMap<String,Object> sortMap,HashMap<String,Object> projectMap,
    		int page,int pageSize, Class<T> clazz){
    	//结果集合
    	List<T> list = new ArrayList<T>();
    	//聚合参数
    	List<Bson> bson = new ArrayList<Bson>();
    	
    	
    	//lookup 
    	if(lookUpMap != null && lookUpMap.size() > 0){
    		Bson lookup = new BasicDBObject("$lookup", lookUpMap);  
    		bson.add(lookup);
    	}
    	
    	// unwind 
    	if(unwindMap != null && unwindMap.size() >0 ){
    		Bson unwind = new BasicDBObject("$unwind", unwindMap.get("unwind"));  
    		bson.add(unwind);
    	}
    	
    	//条件
    	if(whereMap != null && !"".equals(whereMap)){
    		Bson filter = new BasicDBObject("$match",whereMap);
    		bson.add(filter);
    	}
    	
    	   //sort  
    	if(sortMap != null && sortMap.size() > 0){
    		Bson sort = new BasicDBObject("$sort", sortMap);  
    		bson.add(sort);
    	}
    	
    	int pageCount = (page-1)*pageSize;
    	
		Bson skip = new BasicDBObject("$skip", pageCount);  
		bson.add(skip);
    	
    	// limit
    	if(pageSize > 0){
    		Bson limit = new BasicDBObject("$limit", pageSize);  
    		bson.add(limit);
    	}
    	
     
    	
    	//project
    	if(projectMap != null && projectMap.size() > 0){
    		Bson project = new BasicDBObject("$project", projectMap);  
    		bson.add(project);
    	}
    	
 
    	AggregateIterable<Document> documents =  getCollection(DbName,tableName).aggregate(bson);
    	for (Document document : documents) { 
    		list.add((T) toBean(document,clazz));
		}
    	return list;
    }
	 /**
     * 业户查岗 ->查询方法(多表关联) 总条数
     * @param DbName		数据库名
     * @param tableName		表名
     * @param whereMap		查询条件
     * @param unwindMap		解压条件
     * @param lookUpMap		连接条件
     * @param sortMap		排序名称
     * @param projectMap    挑选字段
     * @param Class<T> clazz 实体类
     * @return
     */
	//$match $lookup $unwind $project
    public static<T> Integer aggregateTableCount(String DbName, String tableName, BasicDBObject whereMap,
			HashMap<String, Object> unwindMap, HashMap<String, Object> lookUpMap, HashMap<String, Object> sortMap,
			HashMap<String, Object> projectMap, Class<T> clazz) {
    	Integer count = 0 ;
    	
    	//结果集合
    	List<T> list = new ArrayList<T>();
    	//聚合参数
    	List<Bson> bson = new ArrayList<Bson>();
    	
    	
    	//lookup 
    	if(lookUpMap != null && lookUpMap.size() > 0){
    		Bson lookup = new BasicDBObject("$lookup", lookUpMap);  
    		bson.add(lookup);
    	}
    	
    	// unwind 
    	if(unwindMap != null && unwindMap.size() >0 ){
    		Bson unwind = new BasicDBObject("$unwind", unwindMap.get("unwind"));  
    		bson.add(unwind);
    	}
    	
    	//条件
    	if(whereMap != null && !"".equals(whereMap)){
    		Bson filter = new BasicDBObject("$match",whereMap);
    		bson.add(filter);
    	}
    	
    	
        //sort  
    	if(sortMap != null && sortMap.size() > 0){
    		Bson sort = new BasicDBObject("$sort", sortMap);  
    		bson.add(sort);
    	}
    	
    	//project
    	if(projectMap != null && projectMap.size() > 0){
    		Bson project = new BasicDBObject("$project", projectMap);  
    		bson.add(project);
    	}
    	
 
    	AggregateIterable<Document> documents =  getCollection(DbName,tableName).aggregate(bson);
    	for (Document document : documents) { 
    		list.add((T) toBean(document,clazz));
		}
    	if(list != null && list.size() > 0){
    		count = list.size();
    	}
    	return count;
	}
    
    /**
     * 查询方法--分组去重（利用管道，进行两次分组）
     * @param DbName		数据库名
     * @param tableName		表名
     * @param wheremap	查询条件
     * @param groupByName	分组名称
     * @param distinct	           需去重名称
     * @param sortMap		排序名称
     * @param limitCount	限制条数
     * @return
     */
	public static List<Object> groupBy(String DbName, String tableName,HashMap<String,Object> whereMap,String groupByName,String distinct,HashMap<String,Object> sortMap,
    		int limitCount){
    	//结果集合
    	List<Object> list = new ArrayList<Object>();
    	//聚合参数
    	List<Bson> bson = new ArrayList<Bson>();
    	//条件
    	if(whereMap != null && !"".equals(whereMap)){
    		Bson filter = new BasicDBObject("$match",whereMap);
    		bson.add(filter);
    	}
    	
    	//groupOne  
    	if(groupByName != null && !"".equals(groupByName) && distinct != null && !"".equals(distinct)){
    		DBObject groupFields = new BasicDBObject( groupByName, "$"+groupByName);
    		groupFields.put( distinct, "$"+distinct);
    		Bson group1 = new BasicDBObject("$group", new BasicDBObject( "_id", groupFields));  
    		bson.add(group1);
    	}
    	
    	//groupTwo  
    	if(groupByName != null && !"".equals(groupByName)){
    		DBObject groupFields = new BasicDBObject( "_id", "$_id."+groupByName); 
    		groupFields.put("count", new BasicDBObject( "$sum", 1));   
    		Bson group2 = new BasicDBObject("$group", groupFields);  
    		bson.add(group2);
    	}
    	
        //sort  
    	if(sortMap != null && sortMap.size() > 0){
    		Bson sort = new BasicDBObject("$sort", sortMap);  
    		bson.add(sort);
    	}
        //limit  
    	if(limitCount != 0){
    		Bson limit = new BasicDBObject("$limit", limitCount);
    		bson.add(limit);
    	}
    	
    	AggregateIterable<Document> documents =  getCollection(DbName,tableName).aggregate(bson);
    	for (Document document : documents) {
    		list.add(document);
		}
    	return list;
    }
  
    /**
     * 管道方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param bson			条件
     */
	public static List<Document> aggregatebyDocument(String DbName, String tableName,List<Bson> bson){
    	List<Document> list = new ArrayList<Document>();
    	//聚合参数
    	AggregateIterable<Document> documents = getCollection(DbName,tableName).aggregate(bson);
    	for (Document document : documents) {
    		list.add(document);
		}
    	return list;
    }
	
	/**查询方法
     * @param DbName		数据库名
     * @param tableName		表名
     * @param whereMap		查询条件
	 * @return 
	 */
	public static List<Document> findDocuments(String DbName, String tableName,HashMap<String,Object> whereMap) {
    	List<Document> list = new ArrayList<Document>();
    	Bson filter = new BasicDBObject(whereMap);
    	FindIterable<Document> documents =  getCollection(DbName,tableName).find(filter);
    	for (Document document : documents) {
    		list.add(document);
    	}
    	return list;
	}
    
}
