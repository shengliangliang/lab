package king.concurrent.base.test003;

import com.king.concurrent.base.test003.MyTask;
import com.king.concurrent.base.test003.MyThreadFactory;
import org.junit.Test;

public class TestMyThreadFactory {



    @Test
    public void testNewThread() {
        MyThreadFactory myFactory=new MyThreadFactory("MyThreadFactory");
        MyTask task=new MyTask();
        Thread thread = myFactory.newThread(task);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Thread information.\n");

        System.out.printf("%s\n",thread);

        System.out.printf("Main: End of the example.\n");

    }
}
