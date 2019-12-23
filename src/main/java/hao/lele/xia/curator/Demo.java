package hao.lele.xia.curator;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/21 10:43
 */
public class Demo {

    private static final String url = "127.0.0.1:2181";
    private ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3,Integer.MAX_VALUE);
    private CuratorFramework client = null;


    public void initClient(){
        if(client == null){
            client = CuratorFrameworkFactory.newClient(url, retryPolicy);
        }
        client.start();
    }

    public void createPersisentNode(String path){
        try {
            if(client.checkExists().forPath(path) == null){
                client.create()
                        .creatingParentContainersIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(path,path.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createEphemeralNode(String path){
        try {
            String result = client.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path, path.getBytes());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getNode(String path){
        try {
            List<String> strings = client.getChildren().forPath(path);
            return strings;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteNode(String path){
        try {
            client.delete()
                    .deletingChildrenIfNeeded()
                    .forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addListener1(String path){
        try {
            client.getData().usingWatcher(new CuratorWatcher() {
                @Override
                public void process(WatchedEvent watchedEvent) throws Exception {
                    System.out.println(watchedEvent.getType().name());
                    if(Watcher.Event.EventType.NodeDeleted == watchedEvent.getType()){
                            
                    }
                }
            })
            .forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InterProcessMutex getLock(String path){
        return new InterProcessMutex(client,path);
    }

    public void addListener(String path){
        List<String> nodes = getNode("/lock").stream().map(s -> "/lock/" + s).collect(Collectors.toList());
        String min = Collections.min(nodes);
        if(path.equalsIgnoreCase(min)){
            //创建操作,获取锁
            InterProcessMutex lock = getLock("/lock");
            try {
                boolean acquire = lock.acquire(500, TimeUnit.MILLISECONDS);
                if(!acquire){
                    return;
                }
                System.out.println(Thread.currentThread().getName());
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    if(lock.isOwnedByCurrentThread()){
                        lock.release();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            //添加监听小自己一号的
            String less = path.substring(path.lastIndexOf("-")+1);
            String p1 = path.substring(0,path.lastIndexOf("-")+1) + (Integer.parseInt(less) - 1);
            NodeCache node = new NodeCache(client,p1);
            try {
                node.start(true);
                node.getListenable().addListener(new NodeCacheListener() {
                    @Override
                    public void nodeChanged() throws Exception {
                        ChildData data = node.getCurrentData();
                        if(data == null){
                            //节点删除
                            addListener(path);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        demo.initClient();
        demo.createPersisentNode("/lock");
        String result1 = demo.createEphemeralNode("/lock/mda");
        String result2 = demo.createEphemeralNode("/lock/mda");
        Thread.sleep(5000);
        demo.deleteNode(result2);
        demo.addListener(result1);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
