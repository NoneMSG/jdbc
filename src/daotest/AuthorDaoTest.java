package daotest;

import java.util.List;

import dao.AuthorDao;
import vo.AuthorVo;

public class AuthorDaoTest {
	public static void main(String args[]){
		insertTest();
		selectTest();
	}
	public static void insertTest(){
		AuthorVo authorVo = new  AuthorVo();
		authorVo.setName("Robert");
		authorVo.setBio("blah blah blah");
		
		new AuthorDao().insert(authorVo);
	}
	public static void updateTest(){
		AuthorVo authorVo = new AuthorVo();
		authorVo.setNo(1L);
		authorVo.setName("Robert2");
		authorVo.setBio("asdasdsad");
		
		new AuthorDao().insert(authorVo);
	}
	public static void selectTest(){
		List<AuthorVo> list = new AuthorDao().getList();
		for(AuthorVo vo : list){
			System.out.println(vo);
		}
	}
	public static void selectTest(Long no){
		List<AuthorVo> list = new AuthorDao().getList();
		for(AuthorVo vo : list){
			System.out.println(vo);
		}
	}
	public static void deleteTest(Long no){
		new AuthorDao().delete(no);
	}
}
