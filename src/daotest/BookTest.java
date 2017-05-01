package daotest;

import java.util.List;

import dao.AuthorDao;
import dao.BookDao;
import vo.AuthorVo;
import vo.BookVo;

public class BookTest {

	public static void main(String[] args) {
		insertTest();
		selectTest();
	}
	public static void insertTest(){
		BookVo bookVo = new  BookVo();
		bookVo.setTitle("mansbook");
		bookVo.setPrice(1000L);
		new BookDao().insert(bookVo);
	}
	public static void updateTest(){
		BookVo bookVo = new BookVo();
	
		bookVo.setTitle("mansbook");
		bookVo.setPrice(1000L);
		
		new BookDao().insert(bookVo);
	}
	public static void selectTest(){
		List<BookVo> list = new BookDao().getList();
		for(BookVo vo : list){
			System.out.println(vo);
		}
	}
	public static void selectTest(Long no){
		List<BookVo> list = new BookDao().getList();
		for(BookVo vo : list){
			System.out.println(vo);
		}
	}
	public static void deleteTest(Long no){
		new BookDao().delete(no);
	}
}
