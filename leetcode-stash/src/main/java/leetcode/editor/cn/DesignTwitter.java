package leetcode.editor.cn;

import java.util.*;

/**
 * 355.设计推特
 *
 * @author
 * @date 2022-12-31 20:31:09
 */
@SuppressWarnings("all")
public class DesignTwitter {
    public static void main(String[] args) {
        // 测试代码
        Twitter twitter = new DesignTwitter().new Twitter();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Twitter {

        public int timetamp = 0;

        public class User{

            public int id;
            public Set<Integer> followed;

            public Tweet head;

            public User(int userId) {
                this.id = userId;
                followed = new HashSet<>();
                // 关注自己
                follow(userId);
            }

            public void follow(int followeeId) {
                followed.add(followeeId);
            }

            public void unfollow(int unfolloweeId) {
                // 不能取关自己
                if(this.id != unfolloweeId) {
                    followed.remove(unfolloweeId);
                }
            }

            public void postTweet(int tweetId) {
                Tweet tweet = new Tweet(tweetId, timetamp);
                timetamp++;
                // 头插法
                tweet.next = this.head;
                this.head = tweet;
            }
        }
        private class Tweet{

            /**
             * 当前twitter消息的id
             */
            public int tweetId;

            /**
             * 推文的时间
             */
            public int time;

            /**
             * 以链表的方式存储推文信息, 插入时使用头插法
             */
            public Tweet next;

            public Tweet(int tweetId, int time) {
                this.tweetId = tweetId;
                this.time = time;
                this.next = null;
            }

        }

        // 用于关联 userId 和 User对象
        Map<Integer, User> userMap = new HashMap<>();

        public Twitter() {
        }

        /**
         * 根据给定的 tweetId 和 userId 创建一条新推文
         * note: 每次调用此函数都会使用一个不同的 tweetId
         *
         * @param userId
         * @param tweetId
         */
        public void postTweet(int userId, int tweetId) {

            checkUserExist(userId);
            User user = userMap.get(userId);
            user.postTweet(tweetId);
        }

        /**
         * 检索当前用户新闻推送中最近  10 条推文的 ID 。
         * 新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。
         * 推文必须 按照时间顺序由最近到最远排序
         *
         * @param userId
         * @return
         */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> ans = new ArrayList<>();
            if(!userMap.containsKey(userId)) {
                return ans;
            }
            // 获取所有的用户列表
            Set<Integer> followedList = userMap.get(userId).followed;

            // 算法 : 合并 k 个有序链表

            // 构造优先队列
            PriorityQueue<Tweet> queue = new PriorityQueue<>(followedList.size(), (t1, t2) -> {
                return t2.time - t1.time;
            });

            for (Integer id : followedList) {
                Tweet tweet = userMap.get(id).head;
                // 需要判断下, 因为有的用户可能没有发推文
                if(tweet != null) {
                    queue.add(tweet);
                }
            }

            while(!queue.isEmpty()) {
                if (ans.size() == 10) {
                    break;
                }

                Tweet tweet = queue.peek();
                ans.add(tweet.tweetId);
                queue.poll();
                if(tweet.next != null) {
                    queue.add(tweet.next);
                }
            }

            return ans;
        }

        /**
         * ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户
         * followerId 关注了 followeeId
         *
         * @param followerId
         * @param followeeId
         */
        public void follow(int followerId, int followeeId) {
            // 判断followerId是否存在, 如果不存在就创建
            checkUserExist(followerId);
            // 判断followeeId是否存在
            checkUserExist(followeeId);

            User follower = userMap.get(followerId);
            follower.follow(followeeId);
        }

        /**
         * 检查 userId 对应的User对象是否存在于UserMap中, 如果不存在, 就创建, 并添加到UserMap中
         * @param userId
         */
        private void checkUserExist(int userId) {
            if(!userMap.containsKey(userId)) {
                userMap.put(userId, new User(userId));
            }
        }

        /**
         * ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户
         *
         * @param followerId
         * @param followeeId
         */
        public void unfollow(int followerId, int followeeId) {
            if(userMap.containsKey(followerId)) {
                // 如果followerId不存在就什么都不用做
                User user = userMap.get(followerId);
                user.unfollow(followeeId);
            }
        }
    }

    /**
     * Your Twitter object will be instantiated and called as such:
     * Twitter obj = new Twitter();
     * obj.postTweet(userId,tweetId);
     * List<Integer> param_2 = obj.getNewsFeed(userId);
     * obj.follow(followerId,followeeId);
     * obj.unfollow(followerId,followeeId);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}
