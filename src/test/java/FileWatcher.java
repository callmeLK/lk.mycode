import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.io.watch.watchers.DelayWatcher;
import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * 文件监听Demo
 */
public class FileWatcher {

    @Test
    public void test() {
        File file = FileUtil.file("/Users/xiangyunan/Desktop/JAVA资料");
        //这里只监听文件或目录的修改事件
        WatchMonitor watchMonitor = WatchMonitor.create(file, WatchMonitor.ENTRY_MODIFY);
        //1.延迟处理监听事件
//        watchMonitor.setWatcher(new DelayWatcher(new SimpleWatcher(),500));

        //2.简单写法  只需要实现需要监听的接口
//        watchMonitor.setWatcher(new SimpleWatcher(){
//        @Override
//        public void onCreate(WatchEvent<?> event, Path currentPath) {
//            super.onCreate(event, currentPath);
//        }
//    });
        //3.标准写法
        watchMonitor.setWatcher(new Watcher() {
            @Override
            public void onCreate(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("创建：{}-> {}", currentPath, obj);
            }

            @Override
            public void onModify(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("修改：{}-> {}", currentPath, obj);
            }

            @Override
            public void onDelete(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("删除：{}-> {}", currentPath, obj);
            }

            @Override
            public void onOverflow(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("Overflow：{}-> {}", currentPath, obj);
            }
        });

        //设置监听目录的最大深入，目录层级大于制定层级的变更将不被监听，默认只监听当前层级目录
        watchMonitor.setMaxDepth(3);
        //启动监听
        watchMonitor.start();
    }


}
