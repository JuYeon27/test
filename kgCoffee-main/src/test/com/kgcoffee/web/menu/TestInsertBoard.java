package test.com.kgcoffee.web.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.kgcoffee.web.board.dao.SnsDAO;

class TestInsertBoard {

	@Test
	void testExecute() {

		try {
			SnsDAO dao = new SnsDAO();

			String jemok = "";
			String writer = "";
			String content = "";
			String filename = "";

			for (int i = 1; i <= 40; i++) {
				
				
				jemok = "제목"+i;
				content = "내용입니다"+i;
				filename="";
				writer = "user"+i;
				assertEquals(true, dao.insert(jemok, writer, content, filename, "user"+i));


			}

		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
