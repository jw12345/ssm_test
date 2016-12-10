package com.jw.ssm.util;

import java.io.Serializable;
/**
 * 分页
 */
public class Page implements Serializable{

	/**
	 * 需要两个重要属性：记录总数和每页的记录数，然后可以通过计算得到页数
	 */
	private static final long serialVersionUID = 1L;
	
	private int pageNow = 1;	//当前所在页，开始默认是第一页
	
	private int pageSize = 5;	//每页显示的记录条数，默认5条
	
	private int totalCount;		//总记录条数
	
	@SuppressWarnings("unused")
	private int totalPageCount; //总页数
	
	@SuppressWarnings("unused")
	private int startPos;		//开始位置（指查询条件limit的开始位置），默认从0开始
	
	@SuppressWarnings("unused")
	private boolean hasFirst;	//是否有首页
	
	@SuppressWarnings("unused")
	private boolean hasPre;		//是否有前一页
	
	@SuppressWarnings("unused")
	private boolean hasNext;	//是否有下一页
	
	@SuppressWarnings("unused")
	private boolean hasLast;  	//是否有尾页
	
	//构造器
	public Page(){
		super();
	}
	public Page(int totalCount,int pageNow){
		this.totalCount = totalCount;
		this.pageNow = pageNow;
	}
	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	//得到当前的页数=总记录条数/每页的记录条数
	public int getTotalPageCount() {
		int pageCount = this.getTotalCount()/this.getPageSize();
		return (this.getTotalCount()%this.getPageSize()==0)?pageCount:pageCount+1;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount; 
	}
	//得到开始位置
	public int getStartPos() {
		return (this.getPageNow()-1)*this.getPageSize();
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}
	/**
	 * 当前页为第一页的时候就没有首页
	 * @return
	 */
	public boolean isHasFirst() {
		return (this.getPageNow()==1)?false:true;
	}

	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}
	/**
	 * 如果当前页为首页即第一页，则没有前一页
	 * @return
	 */
	public boolean isHasPre() {
		return this.isHasFirst()?false:true;
	}

	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}
	/**
	 * 如果当前页是尾页即最后一页，则没有下一页
	 * @return
	 */
	public boolean isHasNext() {
		return isHasLast()?false:true;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	/**
	 * 如果是最后一页则没有尾页
	 * @return
	 */
	public boolean isHasLast() {
		return (this.getPageNow()==this.getTotalPageCount())?false:true;
	}

	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}

}
