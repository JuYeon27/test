package test.com.kgcoffee.web.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.kgcoffee.web.users.dao.UsersDAO;

class TestInsertUser {

	@Test
	void testExecute() {


		
		
		try {
			UsersDAO dao = UsersDAO.getInstance();
			
			
			LocalDate now = LocalDate.now();
		      
		      LocalDate start = now.minusYears(90);

		      Random random = new Random();
		      
		      
		      

			for (int i = 1; i <= 100; i++) {

				Long ranDay = (long) (random.nextInt((int) (now.toEpochDay() -start.toEpochDay()+1)) + start.toEpochDay());
			      
				
				String user_id = "user" + i;
				String user_pw = "pw" + i;
				String user_name = "user" + i;
				
				String birthday = LocalDate.ofEpochDay(ranDay).toString();;
				String tel = "010-1234-1234"; 

				assertEquals(true, 
						dao.insert_user(user_id, user_pw, user_name, birthday, tel));
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
