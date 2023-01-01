<p>设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近 <code>10</code> 条推文。</p>

<p>实现 <code>Twitter</code> 类：</p>

<ul> 
 <li><code>Twitter()</code> 初始化简易版推特对象</li> 
 <li><code>void postTweet(int userId, int tweetId)</code> 根据给定的 <code>tweetId</code> 和 <code>userId</code> 创建一条新推文。每次调用此函数都会使用一个不同的 <code>tweetId</code> 。</li> 
 <li><code>List&lt;Integer&gt; getNewsFeed(int userId)</code> 检索当前用户新闻推送中最近&nbsp; <code>10</code> 条推文的 ID 。新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。推文必须 <strong>按照时间顺序由最近到最远排序</strong> 。</li> 
 <li><code>void follow(int followerId, int followeeId)</code> ID 为 <code>followerId</code> 的用户开始关注 ID 为 <code>followeeId</code> 的用户。</li> 
 <li><code>void unfollow(int followerId, int followeeId)</code> ID 为 <code>followerId</code> 的用户不再关注 ID 为 <code>followeeId</code> 的用户。</li> 
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
[[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
<strong>输出</strong>
[null, null, [5], null, null, [6, 5], null, [5]]

<strong>解释</strong>
Twitter twitter = new Twitter();
twitter.postTweet(1, 5); // 用户 1 发送了一条新推文 (用户 id = 1, 推文 id = 5)
twitter.getNewsFeed(1);  // 用户 1 的获取推文应当返回一个列表，其中包含一个 id 为 5 的推文
twitter.follow(1, 2);    // 用户 1 关注了用户 2
twitter.postTweet(2, 6); // 用户 2 发送了一个新推文 (推文 id = 6)
twitter.getNewsFeed(1);  // 用户 1 的获取推文应当返回一个列表，其中包含两个推文，id 分别为 -&gt; [6, 5] 。推文 id 6 应当在推文 id 5 之前，因为它是在 5 之后发送的
twitter.unfollow(1, 2);  // 用户 1 取消关注了用户 2
twitter.getNewsFeed(1);  // 用户 1 获取推文应当返回一个列表，其中包含一个 id 为 5 的推文。因为用户 1 已经不再关注用户 2</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= userId, followerId, followeeId &lt;= 500</code></li> 
 <li><code>0 &lt;= tweetId &lt;= 10<sup>4</sup></code></li> 
 <li>所有推特的 ID 都互不相同</li> 
 <li><code>postTweet</code>、<code>getNewsFeed</code>、<code>follow</code> 和 <code>unfollow</code> 方法最多调用 <code>3 * 10<sup>4</sup></code> 次</li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>设计 | 哈希表 | 链表 | 堆（优先队列）</details><br>

<div>👍 328, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://mp.weixin.qq.com/s/NF8mmVyXVfC1ehdMOsO7Cw' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.github.io/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 已更新到 V2.1，[手把手刷二叉树系列课程](https://aep.xet.tech/s/3YGcq3) 上线。过年前最后一期打卡挑战 [开始报名](https://aep.xet.tech/s/1a9ByX)。**



<p><strong><a href="https://labuladong.github.io/article?qno=355" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

这道题比较经典，在特定场景下让你设计算法。其难点在于 `getNewsFeed` 方法，要按照时间线顺序展示所有关注用户的推文，这个方法要用到我在 [单链表的六大解题套路](https://labuladong.github.io/article/fname.html?fname=链表技巧) 解决 [23. 合并K个升序链表](/problems/merge-k-sorted-lists) 的合并多个有序链表的技巧：

你把一个用户发布的所有推文做成一条有序链表（靠近头部的推文是最近发布的），那么只要合并关注用户的推文链表，即可获得按照时间线顺序排序的信息流。

具体看代码吧，我注释比较详细。

**详细题解：[设计朋友圈时间线功能](https://labuladong.github.io/article/fname.html?fname=设计Twitter)**

**标签：[数据结构](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=1318892385270808576)，[设计](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2121998148662362112)**

## 解法代码

```java
class Twitter {
    // 全局时间戳
    int globalTime = 0;
    // 记录用户 ID 到用户示例的映射
    HashMap<Integer, User> idToUser = new HashMap<>();

    // Tweet 类
    class Tweet {
        private int id;
        // 时间戳用于对信息流按照时间排序
        private int timestamp;
        // 指向下一条 tweet，类似单链表结构
        private Tweet next;

        public Tweet(int id) {
            this.id = id;
            // 新建一条 tweet 时记录并更新时间戳 
            this.timestamp = globalTime++;
        }

        public int getId() {
            return id;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public Tweet getNext() {
            return next;
        }

        public void setNext(Tweet next) {
            this.next = next;
        }
    }

    // 用户类
    class User {
        // 记录该用户的 id 以及发布的 tweet
        private int id;
        private Tweet tweetHead;
        // 记录该用户的关注者
        private HashSet<User> followedUserSet;

        public User(int id) {
            this.id = id;
            this.tweetHead = null;
            this.followedUserSet = new HashSet<>();
        }

        public int getId() {
            return id;
        }

        public Tweet getTweetHead() {
            return tweetHead;
        }

        public HashSet<User> getFollowedUserSet() {
            return followedUserSet;
        }

        public boolean equals(User other) {
            return this.id == other.id;
        }

        // 关注其他人
        public void follow(User other) {
            followedUserSet.add(other);
        }

        // 取关其他人
        public void unfollow(User other) {
            followedUserSet.remove(other);
        }

        // 发布一条 tweet
        public void post(Tweet tweet) {
            // 把新发布的 tweet 作为链表头节点
            tweet.setNext(tweetHead);
            tweetHead = tweet;
        }
    }

    public void postTweet(int userId, int tweetId) {
        // 如果这个用户还不存在，新建用户
        if (!idToUser.containsKey(userId)) {
            idToUser.put(userId, new User(userId));
        }
        User user = idToUser.get(userId);
        user.post(new Tweet(tweetId));
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new LinkedList<>();
        if (!idToUser.containsKey(userId)) {
            return res;
        }
        // 获取该用户关注的用户列表
        User user = idToUser.get(userId);
        Set<User> followedUserSet = user.getFollowedUserSet();
        // 每个用户的 tweet 是一条按时间排序的链表
        // 现在执行合并多条有序链表的逻辑，找出时间线中的最近 10 条动态
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> {
            // 按照每条 tweet 的发布时间降序排序（最近发布的排在事件流前面）
            return b.timestamp - a.timestamp;
        });
        // 该用户自己的 tweet 也在时间线内
        if (user.getTweetHead() != null) {
            pq.offer(user.getTweetHead());
        }
        for (User other : followedUserSet) {
            if (other.getTweetHead() != null) {
                pq.offer(other.tweetHead);
            }
        }
        // 合并多条有序链表
        int count = 0;
        while (!pq.isEmpty() && count < 10) {
            Tweet tweet = pq.poll();
            res.add(tweet.getId());
            if (tweet.getNext() != null) {
                pq.offer(tweet.getNext());
            }
            count++;
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        // 如果用户还不存在，则新建用户
        if (!idToUser.containsKey(followerId)) {
            idToUser.put(followerId, new User(followerId));
        }
        if (!idToUser.containsKey(followeeId)) {
            idToUser.put(followeeId, new User(followeeId));
        }

        User follower = idToUser.get(followerId);
        User followee = idToUser.get(followeeId);
        // 关注者关注被关注者
        follower.follow(followee);
    }

    public void unfollow(int followerId, int followeeId) {
        if (!idToUser.containsKey(followerId) || !idToUser.containsKey(followeeId)) {
            return;
        }
        User follower = idToUser.get(followerId);
        User followee = idToUser.get(followeeId);
        // 关注者取关被关注者
        follower.unfollow(followee);
    }
}
```

</details>
</div>



