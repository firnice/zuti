package com.firnice.zuti.leetcode;

public class MaxProfit {


    /**
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
     * <p>
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * <p>
     * 返回获得利润的最大值。
     * <p>
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     * <p>
     * 示例 1:
     * <p>
     * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
     * 输出: 8
     * 解释: 能够达到的最大利润:
     * 在此处买入 prices[0] = 1
     * 在此处卖出 prices[3] = 8
     * 在此处买入 prices[4] = 4
     * 在此处卖出 prices[5] = 9
     * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
     * 注意:
     * <p>
     * 0 < prices.length <= 50000.
     * 0 < prices[i] < 50000.
     * 0 <= fee < 50000.
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        boolean isBuy = false;
        int result = 0;
        int buyprice = 0;


        //头尾指针
        int head = 1, tail = 0, mid = 0;
        if (prices.length == 1) {
            return 0;
        }

        do {

            //还没买
            if (!isBuy) {
                //涨了
                if (prices[tail] < prices[head]) {
                    //还是涨
                    while (head < prices.length - 1 && prices[head] < prices[head + 1]) {
                        head = head + 1;
                    }

                    //涨幅大于手续费
                    //head要么没有下一步，要么下一步就是跌
                    if (prices[tail] - prices[head] > fee) {
                        isBuy = true;
                        result += prices[tail] - prices[head] - fee;
                        buyprice = prices[tail];
                        tail = head;
                        head = head + 1;
                    }

                } else {
                    //还再跌,找最低点
                    while (head < prices.length - 1 && prices[head] > prices[head + 1]) {
                        tail = head;
                        head = head + 1;
                    }
                }

            }else {
                //卖

                //还是跌,找最低点
                while (head < prices.length - 1 && prices[head] > prices[head + 1]) {
                    head = head + 1;
                }

                //跌幅大于手续费
                //head要么没有下一步，要么下一步就是跌
                if (prices[tail] - prices[head] > fee) {
                    isBuy = false;
                    result += prices[tail] - prices[head] - fee;
                    buyprice = prices[tail];
                    tail = head;
                    head = head + 1;
                }


            }
        } while (head <prices.length);


        for (
                int i = 0;
                i < prices.length; i++) {
            //有余粮
            if (!isBuy && i != prices.length - 1) {
                //价格低
                if (prices[i] < prices[i + 1]) {
                    isBuy = true;
                    buyprice = prices[i];
                }
                continue;
            }

            //无余粮
            if (isBuy && i != prices.length - 1) {
                //价格跌了，卖掉
                if (prices[i] > prices[i + 1]) {


                    isBuy = false;
                    result = result + prices[i] - buyprice;
                    buyprice = 0;
                }
            }
            //最后一天，还没卖，说明一直在涨。。卖掉
            if (isBuy && i == prices.length - 1) {
                isBuy = false;
                result = result + prices[i] - buyprice;
                buyprice = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};


    }


}
