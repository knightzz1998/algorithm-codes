<p><strong>中位数</strong>是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。</p>

<ul> 
 <li>例如 <code>arr = [2,3,4]</code>&nbsp;的中位数是 <code>3</code>&nbsp;。</li> 
 <li>例如&nbsp;<code>arr = [2,3]</code> 的中位数是 <code>(2 + 3) / 2 = 2.5</code> 。</li> 
</ul>

<p>实现 MedianFinder 类:</p>

<ul> 
 <li> <p><code>MedianFinder() </code>初始化 <code>MedianFinder</code>&nbsp;对象。</p> </li> 
 <li> <p><code>void addNum(int num)</code> 将数据流中的整数 <code>num</code> 添加到数据结构中。</p> </li> 
 <li> <p><code>double findMedian()</code> 返回到目前为止所有元素的中位数。与实际答案相差&nbsp;<code>10<sup>-5</sup></code>&nbsp;以内的答案将被接受。</p> </li> 
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入</strong>
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
<strong>输出</strong>
[null, null, null, 1.5, null, 2.0]

<strong>解释</strong>
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0</pre>

<p><strong>提示:</strong></p>

<ul> 
 <li><code>-10<sup>5</sup>&nbsp;&lt;= num &lt;= 10<sup>5</sup></code></li> 
 <li>在调用 <code>findMedian</code>&nbsp;之前，数据结构中至少有一个元素</li> 
 <li>最多&nbsp;<code>5 * 10<sup>4</sup></code>&nbsp;次调用&nbsp;<code>addNum</code>&nbsp;和&nbsp;<code>findMedian</code></li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>设计 | 双指针 | 数据流 | 排序 | 堆（优先队列）</details><br>

<div>👍 785, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://mp.weixin.qq.com/s/NF8mmVyXVfC1ehdMOsO7Cw' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.github.io/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 已更新到 V2.1，[手把手刷二叉树系列课程](https://aep.xet.tech/s/3YGcq3) 上线。过年前最后一期打卡挑战 [开始报名](https://aep.xet.tech/s/1a9ByX)。**



<p><strong><a href="https://labuladong.github.io/article?qno=295" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

本题的核心思路是使用两个优先级队列。

![](https://labuladong.github.io/algo/images/中位数/1.jpeg)


小的倒三角就是个大顶堆，梯形就是个小顶堆，中位数可以通过它们的堆顶元素算出来：

![](https://labuladong.github.io/algo/images/中位数/2.jpeg)

**详细题解：[一道求中位数的算法题把我整不会了](https://labuladong.github.io/article/fname.html?fname=数据流中位数)**

**标签：二叉堆，[数学](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2122023604245659649)**

## 解法代码

```java
class MedianFinder {
    private PriorityQueue<Integer> large;
    private PriorityQueue<Integer> small;

    public MedianFinder() {
        // 小顶堆
        large = new PriorityQueue<>();
        // 大顶堆
        small = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
    }

    public double findMedian() {/**<extend up -100>

![](https://labuladong.github.io/algo/images/中位数/2.jpeg)
*/
        // 如果元素不一样多，多的那个堆的堆顶元素就是中位数
        if (large.size() < small.size()) {
            return small.peek();
        } else if (large.size() > small.size()) {
            return large.peek();
        }
        // 如果元素一样多，两个堆堆顶元素的平均数是中位数
        return (large.peek() + small.peek()) / 2.0;/**<extend up -100>

![](https://labuladong.github.io/algo/images/中位数/1.jpeg)
*/
    }

    public void addNum(int num) {
        if (small.size() >= large.size()) {
            small.offer(num);
            large.offer(small.poll());
        } else {
            large.offer(num);
            small.offer(large.poll());
        }
    }
}
```

**类似题目**：
  - [剑指 Offer 41. 数据流中的中位数 🔴](/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/)

</details>
</div>



