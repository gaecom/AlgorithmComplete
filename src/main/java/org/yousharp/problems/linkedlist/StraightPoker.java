package org.yousharp.problems.linkedlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * take out five cards from Poker cards, check if they are in straight order.
 *  the two kings can be treated as any cards and J is 11, Q is 12 and K is 12.
 * User: Daniel
 * Date: 14-1-19
 * Time: 下午11:32
 */
public class StraightPoker {
    private static Logger logger = LoggerFactory.getLogger(StraightPoker.class);
    private static final int ALL_CARDS_SIZE = 14;

    /**
     * sort the five cards (treat kings as 0), count the number of kings. then we get the index of the first card and
     *  the last card, the value of the first card and the last card. In order to be straight, there is
     *  a relationship between the them: (lastIndex - firstIndex + 1) - (length - numOfKings) <= numOfKings.
     *  in this method, we use bucket sort since the maximum size is 14.
     * @param cards the five cards selected
     * @param length    the length of the cards selected
     * @return  if the cards selected can be a straight Poker
     */
    public boolean checkStraightPoker(int[] cards, int length) {
        // by default, elements in allCards are initialized to 0
        int[] allCards = new int[ALL_CARDS_SIZE];
        for (int i = 0; i < length; i++) {
            allCards[cards[i]]++;
        }
        // kings are represented as 0
        int numOfKings = allCards[0];

        // the index of the first card
        int firstIndex = 1;
        while (1 != allCards[firstIndex]) {
            if (allCards[firstIndex] > 1) {
                logger.info("error: identical cards.");
                return false;
            }
            firstIndex ++;
        }
        // the index of the last card
        int lastIndex = ALL_CARDS_SIZE - 1;
        while (1 != allCards[lastIndex]) {
            if (allCards[lastIndex] > 1) {
                logger.info("error: identical cards.");
                return false;
            }
            lastIndex --;
        }
        // the condition to form a straight Poker
        if ((lastIndex - firstIndex + 1) <= length) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        StraightPoker pokerInstance = new StraightPoker();
        int[] cards1 = new int[] {0, 0, 2, 7, 5};
        int[] cards2 = new int[] {0, 3, 4, 5, 0};
        int[] cards3 = new int[] {2, 4, 6, 0, 0};
        logger.info("Is it a straight poker, 1. {}, 2. {}, 3. {}", pokerInstance.checkStraightPoker(cards1, 5),
                pokerInstance.checkStraightPoker(cards2, 5), pokerInstance.checkStraightPoker(cards3, 5));
    }

}

/* 问题描述：
     从13张扑克牌里任意取出5张，判断是否为一个顺子。其中大王和小王可以作为任意牌，J，Q，K分别为11，12，13.
 思路：
     对这5张牌排序，大小王作为0，然后判断0的个数是否大于牌中的空隙数。如：
      根据牌的索引，如果是顺子，需要牌数：expectedCard = (lastIndex - firstIndex + 1);
      那么在这个索引范围内的实际牌数为：actualCard = (length - numOfKings);
      则空隙数为：space = expectedCard - actualCard;   则有：space <= numOfKings;  则：
            (lastIndex - firstIndex + 1) - (length - numOfKings) <= numOfKings;  即：
            (lastIndex - firstIndex + 1) <= length;
*/