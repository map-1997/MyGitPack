package javabean;

import java.util.List;

public class PageBean<E> {
	int nowPage;//当前页面
	
	int pPage;//上一页
	
	int nPage;//下一页
	
	int count;//总条数
	
	int pageCount;//总页数
	
	int onePageMessage;//每页信息条数
	
	String name;
	
	List<E> user;//对象

	public int getNowPage() {
		return nowPage;
	}

	public int getpPage() {
		return pPage;
	}

	public int getnPage() {
		return nPage;
	}

	public int getCount() {
		return count;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getOnePageMessage() {
		return onePageMessage;
	}

	public List<E> getUser() {
		return user;
	}

	public String getName() {
		return name;
	}
	
	public PageBean(int nowPage, int count, int onePageMessage, List<E> user, String name) {
		this.nowPage = nowPage;
		this.count = count;
		this.onePageMessage = onePageMessage;
		this.user = user;
		this.name = name;
		this.pageCount = (count-1)/5+1;
		this.pPage = nowPage==1?1:nowPage-1;
		this.nPage = nowPage==pageCount?pageCount:nowPage+1;
	}
	
	
}
