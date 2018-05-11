package king.generic.test002;

import com.king.generic.test002.Element;
import com.king.generic.test002.TimeOutPriorityQueue;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;


public class TestTimeOutPriorityQueue {


    @Test
    public void testQueue(){


        TimeOutPriorityQueue<Element> queue = new TimeOutPriorityQueue<>();

        queue.setExpireTime(100L);
        for(int i=0;i<100;i++){
            int id = (int)(Math.random() * 1000);
            int priority = (int)(Math.random() * 100);

            Element element = new Element(id,priority);

            try {
                queue.put(element);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("=========================分割线=============================================");


        for(int i=0;i<100;i++){
            try {
                Element e = queue.poll();
                if(e!=null){
                    System.out.println("有效数据："+e.toString());
                }

            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

    }
}
