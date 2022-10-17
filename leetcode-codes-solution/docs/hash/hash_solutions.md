## 874. 模拟行走机器人

### 题目描述 

机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令 commands ：

-2 ：向左转 90 度
-1 ：向右转 90 度
1 <= x <= 9 ：向前移动 x 个单位长度
在网格上有一些格子被视为障碍物 obstacles 。第 i 个障碍物位于网格点  obstacles[i] = (xi, yi) 。

机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。

返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 5 ，则返回 25 ）


注意：

北表示 +Y 方向。
东表示 +X 方向。
南表示 -Y 方向。
西表示 -X 方向。


示例 1：

输入：commands = [4,-1,3], obstacles = []
输出：25
解释：
机器人开始位于 (0, 0)：
1. 向北移动 4 个单位，到达 (0, 4)
2. 右转
3. 向东移动 3 个单位，到达 (3, 4)
   距离原点最远的是 (3, 4) ，距离为 32 + 42 = 25
   示例 2：

输入：commands = [4,-1,4,-2,4], obstacles = [[2,4]]
输出：65
解释：机器人开始位于 (0, 0)：
1. 向北移动 4 个单位，到达 (0, 4)
2. 右转
3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
4. 左转
5. 向北走 4 个单位，到达 (1, 8)
   距离原点最远的是 (1, 8) ，距离为 12 + 82 = 65


提示：

1 <= commands.length <= 104
commands[i] is one of the values in the list [-2,-1,1,2,3,4,5,6,7,8,9].
0 <= obstacles.length <= 104
-3 * 104 <= xi, yi <= 3 * 104
答案保证小于 231

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/walking-robot-simulation
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


### 代码实现 

```java
class Solution {

    public String calcHash(int x, int y) {
        return x + "," + y;
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        // 存储障碍物
        HashSet<String> blockers = new HashSet<>();
        for (int[] obstacle : obstacles) {
            blockers.add(calcHash(obstacle[0], obstacle[1]));
        }

        // (-200, 300000) ==> 整体平移, 防止出现负数 ==> (-200 + 30000 , 30000 + 30000)
        // (-200 + 30000 , 30000 + 30000) ==> hash ==> (-200 + 30000 , 30000 + 30000) * 60000
        // + 30000 + 30000
        // String : "-200, 30009"

        // 存储方向数组
        //                 N  E  S  W
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int x = 0;
        int y = 0;
        // 方向
        int dir = 0; // 默认 dx[dir] 北
        int ans = 0;
        // 每次移动以下就计算当前距离
        for (int cmd : commands) {
            if (cmd > 0) {
                // 向北
                for (int i = 0; i < cmd; i++) {
                    // 下一个位置
                    int nextX = x + dx[dir];
                    int nextY = y + dy[dir];
                    if (blockers.contains(calcHash(nextX, nextY))) {
                        // 遇到障碍物直接停下
                        break;
                    }
                    // 移动
                    x = nextX;
                    y = nextY;
                    ans = Math.max(ans, x * x + y * y);
                }
            } else if (cmd == -1) {
                // 右转 ==> 当前是不确定朝向的
                // 北 => 东, 东 => 南 , 南 => 西 , 西 => 北
                // 这里保证取模后一定是下一个方向
                dir = (dir + 1) % 4;

            } else if (cmd == -2) {
                // 左转 ==> 当前是不确定朝向的
                // 北 => 西, 东 => 北 , 南 => 东 , 西 => 南 (逆时针)
                // 这里保证取模后一定是下一个方向 , +4 为了防止dir-2是负数
                dir = (dir - 1 + 4) % 4;
            }
        }
        return ans;
    }
}
```





## 49. 字母异位词分组



### 题目描述 



给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。

 

示例 1:

输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
示例 2:

输入: strs = [""]
输出: [[""]]
示例 3:

输入: strs = ["a"]
输出: [["a"]]


提示：

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] 仅包含小写字母



#### 代码实现



```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 对字符串进行排序, 然后作为key
        Map<String, ArrayList<String>> results = new HashMap<>();
        for (String str : strs) {
            String copy = sort(str);
            if(results.containsKey(copy)) {
                results.get(copy).add(str);
            }else{
                ArrayList<String> values = new ArrayList<>();
                values.add(str);
                System.out.println("add" + copy + ":" + str);
                results.put(copy, values);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for(String key : results.keySet()) {
            ans.add(results.get(key));
        }
        return ans;
    }

    public String sort(String str) {
        char[] chs = str.toCharArray();
        Arrays.sort(chs);
        return new String(chs);
    }

    // 时间复杂度 : 
    // 每个字符串长度 : n1 , n2 ... n ...
    // sort : 使用的是快排 , 时间复杂度是 nlogn
    // O = n1 logn1 + n2 logn2 + .... < n1 logn + n2 logn .... 
    //   = (n1 + n2 ... + ) log n
    //     = nlogn
    // 最后再加上 n => nlogn + n
}
```

