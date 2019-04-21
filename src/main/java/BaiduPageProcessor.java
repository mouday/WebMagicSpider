import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class BaiduPageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(1)
            .setSleepTime(1000)
            .setCharset("utf-8");

    public void process(Page page) {
        page.putField("title", page.getHtml().css("title", "text").toString());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BaiduPageProcessor())
                .addUrl("http://www.baidu.com/")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new JsonFilePipeline("/Users/qmp/myproject/WebMagicSpider"))
                .thread(1)
                .run();
    }
}

