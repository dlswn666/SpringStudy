package student;

public class CommandFactory {
	
	// 싱글톤 패턴
	// 싱글톤 패턴을 이용한 팩토리 패턴 
	// 다른곳에서 객체 생성 못하게 private로 객체를 미리 생성함. 
	private CommandFactory() {}
	private static CommandFactory instance = new CommandFactory();
	// 객체를 불러올 수 있는 메소드를 만든다. 
	public static CommandFactory getInstance() {
		return instance;
	}
	
	public CommandIf createCommand(String cmd) {
		CommandIf cmdIf = null;
		if (cmd.equals("insert")) {
			cmdIf = new InsertCommand();
		}else if (cmd.equals("delete")) {
			cmdIf = new DeleteCommand();
		}else if (cmd.equals("find")) {
			cmdIf = new FindCommand();
		}else if (cmd.equals("list")) {
			cmdIf = new ListCommand();
		}else if (cmd.equals("start")) {
			cmdIf = new StartCommand();
		}
		
		return cmdIf;
	}
	

}
