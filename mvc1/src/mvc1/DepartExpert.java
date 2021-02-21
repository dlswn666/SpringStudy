package mvc1;

import java.util.ArrayList;
import java.util.List;

public class DepartExpert {
	
	public String checkName(String name) {
		if(name.equals("") || name == "") {
			name = "이름을 입력해주세요.";
		}
		
		
		return name;
	}
	
	public List<String> getAdvice(String depart){
		List<String> list = new ArrayList<String>();
		
		
		switch(depart) {
		case"kor":
			list.add("국어를 공부하는 것");
			list.add("국어를 잘 해야 합니다.");
			break;
		case"eng":
			list.add("영어를 공부하는 것");
			list.add("영어를 잘 해야 합니다.");
			break;
		case"com":
			list.add("컴퓨터를 공부하는 것");
			list.add("컴퓨터를 잘 해야 합니다.");
			break;
		case"game":
			list.add("게임를 공부하는 것");
			list.add("게임를 잘 해야 합니다.");
			break;
		default :
			list.add("받은 자료가 없습니다");
		}
		return list;
	}

}
