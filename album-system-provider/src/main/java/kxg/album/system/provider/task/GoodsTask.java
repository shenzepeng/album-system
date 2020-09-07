package kxg.album.system.provider.task;

import kxg.album.system.provider.dao.GoodsDao;
import kxg.album.system.provider.dao.GoodsDicDao;
import kxg.album.system.provider.pojo.Goods;
import kxg.album.system.provider.pojo.GoodsDic;
import kxg.album.system.provider.utils.JsonUtils;
import kxg.fuck.weishangxiangce.service.dto.GoodsDto;
import kxg.fuck.weishangxiangce.service.dto.ImgDto;
import kxg.fuck.weishangxiangce.service.request.FindGoodsNumberRequest;
import kxg.fuck.weishangxiangce.service.respopnse.FindGoodsCountNumbersResponse;
import kxg.fuck.weishangxiangce.service.respopnse.FindGoodsResponse;
import kxg.fuck.weishangxiangce.service.request.FindGoodsRequest;
import kxg.fuck.weishangxiangce.service.service.WSXCGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 要写注释呀
 */
@Component
@Slf4j
public class GoodsTask {
    @Autowired
    private WSXCGoodsService goodsService;
    @Autowired
    private GoodsDicDao goodsDicDao;
    @Autowired
    private GoodsDao goodsDao;

    @Scheduled(fixedRate = 5000)
    private void addGoods() {
        log.info("执行静态定时任务时间 {}", LocalDateTime.now());
        Map<String, GoodsDic> dicTypeMap = goodsDicDao.findAll().stream().collect(Collectors.toMap(GoodsDic::getType, goodsDic -> goodsDic));
        List<String> dicType = goodsDicDao.findAll()
                .stream()
                .filter(t -> t.getWant().equals((short) 0))
                .map(GoodsDic::getType)
                .collect(Collectors.toList());
        log.info("dicType {}", dicType);
        for (String s : dicType) {
            FindGoodsNumberRequest request = new FindGoodsNumberRequest();
            request.setSearchInfo(s);
            FindGoodsCountNumbersResponse goodsNumber = goodsService.findGoodsNumber(request);
            int numbers = (int) goodsNumber.getNumbers();
            FindGoodsRequest findGoodsRequest = new FindGoodsRequest();
            findGoodsRequest.setPageNumber(1);
            findGoodsRequest.setSearchInfo(s);
            findGoodsRequest.setPageSize(numbers);
            FindGoodsResponse findGoodsResponse = goodsService.findGoods(findGoodsRequest);
            List<Goods> goodsList = findGoodsResponse.getGoodsDtos().stream().map(new Function<GoodsDto, Goods>() {
                @Override
                public Goods apply(GoodsDto goodsDto) {
                    Goods good = new Goods();
                    ImgDto imgDtoBig = new ImgDto();
                    imgDtoBig.setImgs(goodsDto.getBigPicture());
                    good.setBigPic(JsonUtils.objectToJson(imgDtoBig));
                    ImgDto imgDtoSmall = new ImgDto();
                    imgDtoSmall.setImgs(goodsDto.getSmallPicture());
                    good.setSmallPic(JsonUtils.objectToJson(imgDtoSmall));
                    good.setGoodsName(goodsDto.getGoodsName());
                    good.setContent(goodsDto.getContent());
                    good.setGoodsIndex(goodsDto.getGoodsIndex());
                    good.setCreateTime(new Date());
                    good.setTypeId(dicTypeMap.get(s).getId());
                    return good;
                }
            }).collect(Collectors.toList());
            List<Goods> list = getNeedList(goodsList);
            list.removeAll(Collections.singleton(null));
            if (CollectionUtils.isEmpty(goodsList)) {
                return;
            }
            addGoods(list);
        }

    }

    private List<Goods> getNeedList(List<Goods> list) {
        List<String> goodsIndex = list.stream().map(Goods::getGoodsIndex).collect(Collectors.toList());
        List<String> localGoodsIndex = goodsDao.findGoodsByGoodsIndex(goodsIndex).stream().map(Goods::getGoodsIndex).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        return list.stream().filter(t -> !localGoodsIndex.contains(t.getGoodsIndex())).collect(Collectors.toList());
    }

    private Integer addGoods(List<Goods> goods) {
        if (CollectionUtils.isEmpty(goods)) {
            log.info("当前面没有要添加的数据");
            return 1;
        }
        List<List<Goods>> list = createList(goods, 300);
        for (List<Goods> goodsList : list) {
            goodsDao.addGoodsList(goodsList);
        }
        return 1;
    }


    public static List<List<Goods>> createList(List<Goods> target, int size) {
        List<List<Goods>> listArr = new ArrayList<List<Goods>>();
        //获取被拆分的数组个数
        int arrSize = target.size() % size == 0 ? target.size() / size : target.size() / size + 1;
        for (int i = 0; i < arrSize; i++) {
            List<Goods> sub = new ArrayList<Goods>();
            //把指定索引数据放入到list中
            for (int j = i * size; j <= size * (i + 1) - 1; j++) {
                if (j <= target.size() - 1) {
                    //得到拆分后的集合
                    sub.add(target.get(j));
                }
            }
            //拆分的集合可以做点什么
            //sub.dosomething();
            //将拆分后的集合综合为一个集合
            listArr.add(sub);
        }
        return listArr;


    }
}
