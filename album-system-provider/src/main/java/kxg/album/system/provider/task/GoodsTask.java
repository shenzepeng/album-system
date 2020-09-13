package kxg.album.system.provider.task;

import com.ctrip.framework.apollo.ConfigService;
import kxg.album.system.provider.dao.GoodsDao;
import kxg.album.system.provider.dao.GoodsDicDao;
import kxg.album.system.provider.dto.GoodsDtos;
import kxg.album.system.provider.dto.GoodsInfoRoot;
import kxg.album.system.provider.dto.GoodsNumbersRoot;
import kxg.album.system.provider.pojo.Goods;
import kxg.album.system.provider.pojo.GoodsDic;
import kxg.album.system.provider.utils.HttpClientUtil;
import kxg.album.system.provider.utils.JsonUtils;
import kxg.fuck.weishangxiangce.service.dto.GoodsDto;
import kxg.fuck.weishangxiangce.service.dto.ImgDto;
import kxg.fuck.weishangxiangce.service.request.FindGoodsNumberRequest;
import kxg.fuck.weishangxiangce.service.respopnse.FindGoodsCountNumbersResponse;
import kxg.fuck.weishangxiangce.service.respopnse.FindGoodsResponse;
import kxg.fuck.weishangxiangce.service.request.FindGoodsRequest;
//import kxg.fuck.weishangxiangce.service.service.WSXCGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 要写注释呀
 */
@Component
@Slf4j
public class GoodsTask {
//    @Autowired
//    private WSXCGoodsService goodsService;
    @Autowired
    private GoodsDicDao goodsDicDao;
    @Autowired
    private GoodsDao goodsDao;
    private  ExecutorService executorService = Executors.newSingleThreadExecutor();
    @Scheduled(fixedRate = 5000)
    private void addGoods() {
        String swith = ConfigService.getAppConfig().getProperty("get.wsxc.goods.swith", "open");
        if (StringUtils.isEmpty(swith)||swith.equals("close")){
            return;
        }
        log.info("执行静态定时任务时间 {}", LocalDateTime.now());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<GoodsDic> all = goodsDicDao.findAll();
                List<String> dicType = all
                        .stream()
                        .filter(t -> t.getWant().equals((short) 0))
                        .map(GoodsDic::getType)
                        .collect(Collectors.toList());
                log.info("dicType {}", dicType);
                Map<String, Long> dicTpyeMap = all.stream().collect(Collectors.toMap(GoodsDic::getType, GoodsDic::getId));
                for (String s : dicType) {
                    FindGoodsNumberRequest request = new FindGoodsNumberRequest();
                    request.setSearchInfo(s);
                    String goodsNumberUrl = ConfigService.getAppConfig().getProperty("wxsc.findGoodsNumber", "");
                    String goodsNumbersValue = HttpClientUtil.doPostJson(goodsNumberUrl, JsonUtils.objectToJson(request));
                    String goodsInfoUrlValue = ConfigService.getAppConfig().getProperty("wxsc.findGoods.info.url", "");
                    GoodsNumbersRoot goodsNumbersRoot = JsonUtils.jsonToPojo(goodsNumbersValue, GoodsNumbersRoot.class);
                    List<Integer> ids = new ArrayList<>();
                    for (int i = 0; i < goodsNumbersRoot.getData().getNumbers(); i++) {
                        ids.add(i);
                    }
                    log.info("goodsNumbersValue {}", ids.size());
                    List<List<Integer>> list = createList(ids, 200);
                    log.info(" List<List<Integer>> list {}", list.size());
                    int i=0;
                    for (List<Integer> integerList : list) {
                        FindGoodsRequest findGoodsRequest = new FindGoodsRequest();
                        findGoodsRequest.setSearchInfo(s);
                        findGoodsRequest.setPageSize(integerList.size());
                        findGoodsRequest.setPageNumber(i);
                        log.info("FindGoodsRequest findGoodsRequest {}", findGoodsRequest);
                        String goodInfoValues = HttpClientUtil.doPostJson(goodsInfoUrlValue, JsonUtils.objectToJson(findGoodsRequest));
                        GoodsInfoRoot goodsInfoRoot = JsonUtils.jsonToPojo(goodInfoValues, GoodsInfoRoot.class);
                        List<String> goodsIndex = goodsInfoRoot.getData().getGoodsDtos()
                                .stream()
                                .map(GoodsDtos::getGoodsIndex)
                                .collect(Collectors.toList());
                        List<String> goodsIndexs = goodsDao.findGoodsByGoodsIndex(goodsIndex)
                                .stream()
                                .map(Goods::getGoodsIndex)
                                .collect(Collectors.toList());
                        log.info("goodInfoValues {}", goodsInfoRoot.getData().getGoodsDtos().size());
                        List<Goods> goodsList = goodsInfoRoot.getData().getGoodsDtos()
                                .stream()
                                .filter(t -> !t.getContent().contains("衣") && !t.getContent().contains("裤") &&  !t.getContent().contains("包"))
                                .filter(t -> !goodsIndexs.contains(t.getGoodsIndex()))
                                .peek(t -> log.info("goodsInfoRoot.getData().getGoodsDtos() {}", t.getGoodsIndex()))
                                .map(new Function<GoodsDtos, Goods>() {
                                    @Override
                                    public Goods apply(GoodsDtos goodsDto) {
                                        Goods good = new Goods();
                                        ImgDto imgDtoBig = new ImgDto();
                                        imgDtoBig.setImgs(goodsDto.getBigPicture());
                                        good.setBigPic(JsonUtils.objectToJson(imgDtoBig));
                                        ImgDto imgDtoSmall = new ImgDto();
                                        imgDtoSmall.setImgs(goodsDto.getSmallPicture());
                                        good.setSmallPic(JsonUtils.objectToJson(imgDtoSmall));
                                        if (StringUtils.isEmpty(goodsDto.getGoodsName())) {
                                            good.setGoodsName("");
                                        }
                                        good.setContent(goodsDto.getContent().replaceAll("\\d+", ""));
                                        good.setGoodsIndex(goodsDto.getGoodsIndex());
                                        good.setCreateTime(new Date());
                                        good.setTypeId(dicTpyeMap.get(s));
                                        return good;
                                    }
                                }).collect(Collectors.toList());
                        i++;
                        log.info("now index values {}",i);
                        goodsList.removeAll(Collections.singleton(null));
                        if (CollectionUtils.isEmpty(goodsList)) {
                            continue;
                        }
                        addGoods(goodsList);
                    }
                }

            }
        });

    }


    private Integer addGoods(List<Goods> goods) {
        return   goodsDao.addGoodsList(goods);

    }


    public static List<List<Integer>> createList(List<Integer> target, int size) {
        List<List<Integer>> listArr = new ArrayList<List<Integer>>();
        //获取被拆分的数组个数
        int arrSize = target.size() % size == 0 ? target.size() / size : target.size() / size + 1;
        for (int i = 0; i < arrSize; i++) {
            List<Integer> sub = new ArrayList<Integer>();
            //把指定索引数据放入到list中
            for (int j = i * size; j <= size * (i + 1) - 1; j++) {
                if (j <= target.size() - 1) {
                    //得到拆分后的集合
                    sub.add(target.get(j));
                }
            }
            //拆分的集合可以做点什么
            //sub.dosomething();
            sub.stream().peek(new Consumer<Integer>() {
                @Override
                public void accept(Integer id) {
                    log.info("add  info{}",id);
                }
            });
            //将拆分后的集合综合为一个集合
            listArr.add(sub);
        }
        return listArr;
    }

}
