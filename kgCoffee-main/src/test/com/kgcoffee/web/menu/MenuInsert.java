package test.com.kgcoffee.web.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.Test;

import com.kgcoffee.web.menu.menuDAO.MenuDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

class MenuInsert {

	

	@Test
	void testMenuAction() {

		MenuInsert service = new MenuInsert();
		

		try {

			for (int i = 0; i < 40; i++) {
				String caffeineType = "";
				String menuType = "";
				String menuImg = "ame.jpg";
				if (i % 2 == 0) {
					caffeineType = "decaffeine";
					menuType = "Ice";
				} else {
					caffeineType = "caffeine";
					menuType = "Hot";
				}
				String menuName = "아메리카노 " + i;
				int menuPrice = i;
				String menuExplain = i + "번째 아메리카노";

				MenuDAO mdao = new MenuDAO();

				assertEquals(true, mdao.insertMenu(menuImg, caffeineType, menuName, menuPrice, menuExplain, menuType));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// System.out.println("MenuInsert Error");
		}

	}

}
