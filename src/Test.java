import java.util.Hashtable;
import java.util.Map;

import com.shebao.basis.Base;
import com.shebao.basis.BaseObject;

public class Test implements BaseObject,Runnable{
	
	public static void main(String[] args) {
		Map<String, String> config = new Hashtable<String, String>();
		config.put("dbConnectionDriver", "localhost");
		config.put("dbDatabaseName", "shebaofq");
		config.put("dbConnectionName", "yimin");
		config.put("dbConnectionPassword", "123456");
		config.put("testEnvironment", "N");
		config.put("logLevel", "7");
		config.put("dbPoolConnNumber", "11");
		Base.getIns(config).init();

		Test test = new Test();
		Thread thread1 = new Thread(test);
		thread1.start();
		thread1 = new Thread(test);
		thread1.start();
		thread1 = new Thread(test);
		thread1.start();
		thread1 = new Thread(test);
		thread1.start();
		thread1 = new Thread(test);
		thread1.start();
		thread1 = new Thread(test);
		thread1.start();
		thread1 = new Thread(test);
		thread1.start();
		thread1 = new Thread(test);
		thread1.start();
		thread1 = new Thread(test);
		thread1.start();
		thread1 = new Thread(test);
		thread1.start();
		
	}


	@Override
	public void run() {
		for (int i = 0; i < 99999; i++) {
			dbOperation.runUpdate("insert into ti values(" + i + ",1,'这是线程" + Thread.currentThread().getName() + "写入');");
		}
		
	}
}
