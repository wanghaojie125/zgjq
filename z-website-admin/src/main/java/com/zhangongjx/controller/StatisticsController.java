package com.zhangongjx.controller;

import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.message.statistics.view.StatisticsMemberOrderViewModel;
import com.zhangongjx.message.statistics.view.StatisticsMemberRankingViewModel;
import com.zhangongjx.message.statistics.view.StatisticsOrderGrowthViewModel;
import com.zhangongjx.message.statistics.view.StatisticsTransactionViewModel;
import com.zhangongjx.model.w.MemberEntity;
import com.zhangongjx.repository.l.ILogInterviewRepository;
import com.zhangongjx.repository.m.IGoodsRepository;
import com.zhangongjx.repository.o.IOrderRepository;
import com.zhangongjx.repository.w.IDWGRepository;
import com.zhangongjx.repository.w.IMemberRepository;
import com.zhangongjx.result.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IGoodsRepository goodsRepository;
    @Autowired
    private IDWGRepository idwgRepository;
    @Autowired
    private IMemberRepository memberRepository;
    @Autowired
    private ILogInterviewRepository logInterviewRepository;


    //transaction
    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public String transaction() {
        return "statistics/transaction";
    }

    //flow
    @RequestMapping(value = "/flow", method = RequestMethod.GET)
    public String flow() {
        return "statistics/flow";
    }

    //goods
    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public String goods() {
        return "statistics/goods";
    }

    //member
    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public String member() {
        return "statistics/member";
    }

    //composite
    @RequestMapping(value = "/composite", method = RequestMethod.GET)
    public String composite() {
        return "statistics/composite";
    }

    //member
    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public String ranking() {
        return "statistics/ranking";
    }

    @ResponseBody
    @RequestMapping(value = "/order_count_by_time", method = RequestMethod.GET)
    public ResutlMsg<Object> orderCountByTime(String day, String week, String month, String start, String end) {
        ResutlMsg res = ResutlMsg.success();

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int cur_year = c.get(Calendar.YEAR);
        int cur_month = c.get(Calendar.MONTH) + 1;
        int cur_date = c.get(Calendar.DAY_OF_MONTH);
        String query = "DATE_FORMAT(create_at,'%Y')=" + cur_year + " and DATE_FORMAT(create_at,'%c')=" + cur_month + " and DATE_FORMAT(create_at,'%e')=" + cur_date + " ";
        if (day != null && day.equals("1")) {
            query = query;
        }
        if (week != null && week.equals("1")) {
            Calendar c_w = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
            int cur_week = c.get(Calendar.DAY_OF_WEEK) - 1;
            int off_set_start = 1 - cur_week;
            c_w.add(Calendar.DAY_OF_MONTH, off_set_start);
            String start_weeek = new Timestamp(c_w.getTime().getTime()).toString();
            c_w.add(Calendar.DAY_OF_MONTH, 6);
            String end_weeek = new Timestamp(c_w.getTime().getTime()).toString();
            query = "create_at>='" + start_weeek + "' and create_at<='" + end_weeek + "' ";
        }
        if (month != null && month.equals("1")) {
            query = "DATE_FORMAT(create_at,'%Y')=" + cur_year + " and DATE_FORMAT(create_at,'%c')=" + cur_month + " ";
        }
        if (start != null && end != null && !start.equals("") && !end.equals("")) {
            query = "create_at>='" + start + "' and create_at<='" + end + "' ";
        }
        List<StatisticsOrderCountAndAmountForTimeEntity> items = orderRepository.statisticsOrderCountForTime(query);
        res.setData(items);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/order_amount_by_time", method = RequestMethod.GET)
    public ResutlMsg<Object> orderAmountByTime(String day, String week, String month, String start, String end) {
        ResutlMsg res = ResutlMsg.success();

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int cur_year = c.get(Calendar.YEAR);
        int cur_month = c.get(Calendar.MONTH) + 1;
        int cur_date = c.get(Calendar.DAY_OF_MONTH);
        String query = "DATE_FORMAT(create_at,'%Y')=" + cur_year + " and DATE_FORMAT(create_at,'%c')=" + cur_month + " and DATE_FORMAT(create_at,'%e')=" + cur_date + " ";
        if (day != null && day.equals("1")) {
            query = query;
        }
        if (week != null && week.equals("1")) {
            Calendar c_w = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
            int cur_week = c.get(Calendar.DAY_OF_WEEK) - 1;
            int off_set_start = 1 - cur_week;
            c_w.add(Calendar.DAY_OF_MONTH, off_set_start);
            String start_weeek = new Timestamp(c_w.getTime().getTime()).toString();
            c_w.add(Calendar.DAY_OF_MONTH, 6);
            String end_weeek = new Timestamp(c_w.getTime().getTime()).toString();
            query = "create_at>='" + start_weeek + "' and create_at<='" + end_weeek + "' ";
        }
        if (month != null && month.equals("1")) {
            query = "DATE_FORMAT(create_at,'%Y')=" + cur_year + " and DATE_FORMAT(create_at,'%c')=" + cur_month + " ";
        }
        if (start != null && end != null && !start.equals("") && !end.equals("")) {
            query = "create_at>='" + start + "' and create_at<='" + end + "' ";
        }
        List<StatisticsOrderCountAndAmountForTimeEntity> items = orderRepository.statisticsOrderAmountForTime(query);

        res.setData(items);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/order_7_count_amount_by_time", method = RequestMethod.GET)
    public ResutlMsg<Object> order7CountAmountByTime() {
        ResutlMsg res = ResutlMsg.success();
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int cur_date = c.get(Calendar.DAY_OF_MONTH);
        String end = new Timestamp(c.getTime().getTime()).toString();

        c.add(Calendar.DAY_OF_MONTH, -6);
        String start = new Timestamp(c.getTime().getTime()).toString();

        String query = query = "create_at>='" + start + "' and create_at<='" + end + "' ";
        List<StatisticsOrderCountAndAmountForTimeEntity> items = orderRepository.statisticsOrderCountAndAmountForTime(query);
        res.setData(items);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/order_status", method = RequestMethod.GET)
    public ResutlMsg<Object> orderStatus() {
        ResutlMsg res = ResutlMsg.success();

        String query = query = " 1=1 ";
        List<StatisticsOrderStatusEntity> items = orderRepository.statisticsOrderStatus(query);
        res.setData(items);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/order_growth", method = RequestMethod.GET)
    public ResutlMsg<Object> statisticsOrderGrowth() {
        ResutlMsg res = ResutlMsg.success();
        String query = query = " 1=1 ";

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

        String cur_month = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1);
        String cur_week = c.get(Calendar.YEAR) + "-" + c.get(Calendar.WEEK_OF_YEAR);

        Calendar last_month_cl = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        last_month_cl.add(Calendar.MONTH, -1);
        String last_month = last_month_cl.get(Calendar.YEAR) + "-" + (last_month_cl.get(Calendar.MONTH) + 1);

        Calendar last_week_cl = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        last_week_cl.add(Calendar.WEEK_OF_YEAR, -1);
        String last_week = last_week_cl.get(Calendar.YEAR) + "-" + last_week_cl.get(Calendar.WEEK_OF_YEAR);

        String end = new Timestamp(c.getTime().getTime()).toString();
        c.add(Calendar.MONTH, -2);
        String start = new Timestamp(c.getTime().getTime()).toString();
        query = "create_at>='" + start + "' and create_at<='" + end + "' ";

        List<StatisticsOrderGrowthEntity> items = orderRepository.statisticsOrderGrowth(query);

        StatisticsOrderGrowthViewModel growthViewModel = new StatisticsOrderGrowthViewModel();

        StatisticsOrderGrowthEntity cur_m = getGrowthByMonth(items, cur_month);
        StatisticsOrderGrowthEntity last_m = getGrowthByMonth(items, last_month);

        growthViewModel.setMonthCount(cur_m.getCount());
        growthViewModel.setMonthAmount(cur_m.getAmount());
        growthViewModel.setMonthCountGrowth(((cur_m.getCount() - last_m.getCount()) / (last_m.getCount() == 0 ? 1 : last_m.getCount())) * 100);
        growthViewModel.setMonthAmountGrowth(((cur_m.getAmount() - last_m.getAmount()) / (last_m.getAmount() == 0 ? 1 : last_m.getAmount())) * 100);


        StatisticsOrderGrowthEntity cur_w = getGrowthByWeek(items, cur_week);
        StatisticsOrderGrowthEntity last_w = getGrowthByWeek(items, last_week);

        growthViewModel.setWeekCount(cur_w.getCount());
        growthViewModel.setWeekAmount(cur_w.getAmount());
        growthViewModel.setWeekCountGrowth(((cur_w.getCount() - last_w.getCount()) / (last_w.getCount() == 0 ? 1 : last_w.getCount())) * 100);
        growthViewModel.setWeekAmountGrowth(((cur_w.getAmount() - last_w.getAmount()) / (last_w.getAmount() == 0 ? 1 : last_w.getAmount())) * 100);

        res.setData(growthViewModel);
        return res;
    }

    private StatisticsOrderGrowthEntity getGrowthByMonth(List<StatisticsOrderGrowthEntity> source, String month) {
        for (StatisticsOrderGrowthEntity i : source) {
            if (i.getMonths().equals(month)) {
                return i;
            }
        }
        return new StatisticsOrderGrowthEntity();
    }

    private StatisticsOrderGrowthEntity getGrowthByWeek(List<StatisticsOrderGrowthEntity> source, String week) {
        for (StatisticsOrderGrowthEntity i : source) {
            if (i.getWeeks().equals(week)) {
                return i;
            }
        }
        return new StatisticsOrderGrowthEntity();
    }

    @ResponseBody
    @RequestMapping(value = "/dwg_status", method = RequestMethod.GET)
    public ResutlMsg<Object> dwgStatus() {
        ResutlMsg res = ResutlMsg.success();
        String query = query = " 1=1 ";
        List<StatisticsDWGStatusEntity> items = idwgRepository.statisticsDWGStatus(query);
        res.setData(items);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/goods_status", method = RequestMethod.GET)
    public ResutlMsg<Object> goodsStatus() {
        ResutlMsg res = ResutlMsg.success();
        String query = query = " 1=1 ";
        List<StatisticsGoodsStatusEntity> items = goodsRepository.statisticsGoodsStatus(query);
        res.setData(items);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/member_by_time", method = RequestMethod.GET)
    public ResutlMsg<Object> memberByTime() {
        ResutlMsg res = ResutlMsg.success();
        String query = query = " 1=1 ";

        //当前时间
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int cur_year = c.get(Calendar.YEAR);
        int cur_month = c.get(Calendar.MONTH) + 1;
        int cur_date = c.get(Calendar.DAY_OF_MONTH);
        String day = "(select count(id)  from w_member where deleted=0 and DATE_FORMAT(create_at,'%Y')=" + cur_year + " and DATE_FORMAT(create_at,'%c')=" + cur_month + " and DATE_FORMAT(create_at,'%e')=" + cur_date + ") as day,";
        String month = "(select count(id)  from w_member where deleted=0 and DATE_FORMAT(create_at,'%Y')=" + cur_year + " and DATE_FORMAT(create_at,'%c')=" + cur_month + ") as month";
        //昨天
        c.add(Calendar.DAY_OF_MONTH, -1);
        cur_year = c.get(Calendar.YEAR);
        cur_month = c.get(Calendar.MONTH) + 1;
        cur_date = c.get(Calendar.DAY_OF_MONTH);

        String yesterday = "(select count(id)  from w_member where deleted=0 and DATE_FORMAT(create_at,'%Y')=" + cur_year + " and DATE_FORMAT(create_at,'%c')=" + cur_month + " and DATE_FORMAT(create_at,'%e')=" + cur_date + ") as yesterday,";
        StatisticsMemberForTimeEntity item = memberRepository.statisticsCountByTime(day + yesterday + month, query);
        res.setData(item);
        return res;
    }


    //交易
    @ResponseBody
    @RequestMapping(value = "/transaction_by_time", method = RequestMethod.GET)
    public ResutlMsg<Object> transactionByTime(String day, String week, String month, String start, String end) {
        ResutlMsg res = ResutlMsg.success();

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String query = "1=1 ";

        if (day != null && day.equals("1")) {
            end = sdf.format(new Timestamp(c.getTime().getTime()));
            c.add(Calendar.DAY_OF_MONTH, -1);
            start = sdf.format(new Timestamp(c.getTime().getTime()));
        }
        if (week != null && week.equals("1")) {
            end = sdf.format(new Timestamp(c.getTime().getTime()));
            c.add(Calendar.WEEK_OF_YEAR, -1);
            start = sdf.format(new Timestamp(c.getTime().getTime()));
        }
        if (month != null && month.equals("1")) {
            end = sdf.format(new Timestamp(c.getTime().getTime()));
            c.add(Calendar.MONTH, -1);
            start = sdf.format(new Timestamp(c.getTime().getTime()));
        }
        if (start != null && end != null && !start.equals("") && !end.equals("")) {
            start = start;
            end = end;
        }
        query = "create_at>='" + start + "' and create_at<'" + end + "' ";

        StatisticsTransactionViewModel item = new StatisticsTransactionViewModel();

        item.setBrowseCount(logInterviewRepository.getInterviewCount(query));
        item.setDwgCount(idwgRepository.getCount(query));
        item.setOrderCount(orderRepository.getCount(query));
        item.setPayOrderCount(orderRepository.getPayCount(query));
        item.setPayAmount(orderRepository.getPayAmount(query));
        res.setData(item);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/mem_by_time", method = RequestMethod.GET)
    public ResutlMsg<Object> memByTime(String month_t, String month_y, String start) {
        ResutlMsg res = ResutlMsg.success();

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        String query = "1=1 ";

        int cur_year = c.get(Calendar.YEAR);
        int cur_month = c.get(Calendar.MONTH) + 1;
        String in_year = cur_year + "";
        if (cur_month == 1) {
            in_year = cur_year + "," + (cur_year - 1) + "";
        }

        if (start != null && !start.equals("")) {
            Timestamp timestamp = Timestamp.valueOf(start);
            cur_year = timestamp.getYear();
            cur_month = timestamp.getMonth();
            in_year = cur_year + "";
            if (cur_month == 1) {
                in_year = cur_year + "," + (cur_year - 1) + "";
            }
        }
        query = "DATE_FORMAT(o.create_at,'%Y') in (" + in_year + ") ";

        List<StatisticsTranForMemEntity> items = orderRepository.statisticsTranForMemByTime(query);
        res.setData(items);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/tran_for_amount_range_by_time", method = RequestMethod.GET)
    public ResutlMsg<Object> statisticsTranForAmountRangeByTime(String day, String week, String month, String start, String end) {
        ResutlMsg res = ResutlMsg.success();

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String query = "1=1 ";

        if (day != null && day.equals("1")) {
            end = sdf.format(new Timestamp(c.getTime().getTime()));
            c.add(Calendar.DAY_OF_MONTH, -1);
            start = sdf.format(new Timestamp(c.getTime().getTime()));
        }
        if (week != null && week.equals("1")) {
            end = sdf.format(new Timestamp(c.getTime().getTime()));
            c.add(Calendar.WEEK_OF_YEAR, -1);
            start = sdf.format(new Timestamp(c.getTime().getTime()));
        }
        if (month != null && month.equals("1")) {
            end = sdf.format(new Timestamp(c.getTime().getTime()));
            c.add(Calendar.MONTH, -1);
            start = sdf.format(new Timestamp(c.getTime().getTime()));
        }
        if (start != null && end != null && !start.equals("") && !end.equals("")) {
            start = start;
            end = end;
        }
        query = "create_at>='" + start + "' and create_at<'" + end + "' and status in (1,2,3,4)";

        StatisticsTranForAmountRangeEntity item = orderRepository.statisticsTranForAmountRangeByTime(query);
        res.setData(item);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/mem_growth_by_time", method = RequestMethod.GET)
    public ResutlMsg<Object> statisticsMemGrowthByTime(String type, String year, String month) {
        ResutlMsg res = ResutlMsg.success();

        String query = "1=1 ";
        String select = "DATE_FORMAT(create_at,'%Y-%c-%e') as day,count(id) as count ";
        String group = "group by day order by day";
        if (type != null && type.equals("month")) {
            select = "DATE_FORMAT(create_at,'%Y-%c-%e') as day,count(id) as count ";
            query = "DATE_FORMAT(create_at,'%Y')=" + year + " and DATE_FORMAT(create_at,'%c')=" + month + " ";
        }
        if (type != null && type.equals("year")) {
            select = "DATE_FORMAT(create_at,'%Y-%c') as month,count(id) as count ";
            query = "DATE_FORMAT(create_at,'%Y')=" + year + " ";
            group = "group by month order by month";
        }
        List<StatisticsMemberForGrowthEntity> item = memberRepository.statisticsMemGrowthByTime(select, query, group);
        res.setData(item);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/get_mem_amount", method = RequestMethod.GET)
    public ResutlMsg<Object> getMemAmount(String type, String start, String end) {
        ResutlMsg res = ResutlMsg.success();

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

        String query = "1=1 ";
        if (type != null && type.equals("all")) {
            query = query;
        }
        if (type != null && type.equals("30")) {
            end = new Timestamp(c.getTime().getTime()).toString();
            c.add(Calendar.DATE, -30);
            start = new Timestamp(c.getTime().getTime()).toString();
        }
        if (type != null && type.equals("90")) {
            end = new Timestamp(c.getTime().getTime()).toString();
            c.add(Calendar.DATE, -90);
            start = new Timestamp(c.getTime().getTime()).toString();
        }
        if (start != null && end != null && !start.equals("") && !end.equals("")) {
            start = start;
            end = end;
        }
        query = "create_at>='" + start + "' and create_at<='" + end + "' ";
        List<StatisticsOrderStatusEntity> item = orderRepository.statisticsOrderStatus(query);
        res.setData(item);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/get_order_composite", method = RequestMethod.GET)
    public ResutlMsg<Object> getOrderComposite(String type, String start, String end) {
        ResutlMsg res = ResutlMsg.success();

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

        String query = "1=1 ";

        if (type != null && type.equals("30")) {
            end = new Timestamp(c.getTime().getTime()).toString();
            c.add(Calendar.DATE, -30);
            start = new Timestamp(c.getTime().getTime()).toString();
        }
        if (type != null && type.equals("90")) {
            end = new Timestamp(c.getTime().getTime()).toString();
            c.add(Calendar.DATE, -90);
            start = new Timestamp(c.getTime().getTime()).toString();
        }
        if (start != null && end != null && !start.equals("") && !end.equals("")) {
            start = start;
            end = end;
        }
        if (type != null && type.equals("all")) {
            query = query;
        } else {
            query = "create_at>='" + start + "' and create_at<='" + end + "' ";
        }
        List<StatisticsOrderStatusEntity> item = orderRepository.statisticsOrderStatus(query);
        res.setData(item);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/get_mem_composite", method = RequestMethod.GET)
    public ResutlMsg<Object> getMemComposite(String type, String start, String end) {
        ResutlMsg res = ResutlMsg.success();

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

        String query = "1=1 ";

        if (type != null && type.equals("30")) {
            end = new Timestamp(c.getTime().getTime()).toString();
            c.add(Calendar.DATE, -30);
            start = new Timestamp(c.getTime().getTime()).toString();
        }
        if (type != null && type.equals("90")) {
            end = new Timestamp(c.getTime().getTime()).toString();
            c.add(Calendar.DATE, -90);
            start = new Timestamp(c.getTime().getTime()).toString();
        }
        if (start != null && end != null && !start.equals("") && !end.equals("")) {
            start = start;
            end = end;
        }
        if (type != null && type.equals("all")) {
            query = query;
        } else {
            query = "create_at>='" + start + "' and create_at<='" + end + "' ";
        }
        List<MemberEntity> members = memberRepository.getDatasByQuery(query);

        List<String> queryIn = new ArrayList<>();
        for (MemberEntity i : members) {
            queryIn.add(i.getId() + "");
        }

        String orderQuery = "member_id in (" + String.join(",", queryIn) + ") and status in (1,2,3,4) ";
        List<StatisticsMemberForOrderEntity> item = orderRepository.statisticsMemberOrder(orderQuery);

        List<StatisticsMemberRankingViewModel> result = new ArrayList<>();
        for (StatisticsMemberForOrderEntity i : item) {
            String use_name = members.stream().filter((MemberEntity m) -> i.getMember_id() == m.getId()).collect(Collectors.toList()).get(0).getUser_name();
            ;
            result.add(new StatisticsMemberRankingViewModel(i.getCount(), i.getAmount(), use_name));
        }
        res.setData(result);
        return res;
    }
}
