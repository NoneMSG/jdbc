package daotest;

import dao.AuthorDao;
import vo.AuthorVo;

public class AuthorDaoTest {
	public static void main(String args[]){
		insertTest();
	}
	public static void insertTest(){
		AuthorVo authorVo = new  AuthorVo();
		authorVo.setName("Robert");
		authorVo.setBio("blah blah blah");
		
		new AuthorDao().insert(authorVo);
	}
}
