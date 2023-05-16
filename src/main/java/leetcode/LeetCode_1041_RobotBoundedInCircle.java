package leetcode;

// https://leetcode-cn.com/problems/robot-bounded-in-circle/
public class LeetCode_1041_RobotBoundedInCircle {
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static class Location {
        public int row;
        public int col;
        public Direction direction;

        public Location() {
            row = 0;
            col = 0;
            direction = Direction.UP;
        }

        public boolean isBondage(Location end) {
            boolean case1 = this.row == end.row && this.col == end.col;
            boolean case2 = end.direction != this.direction;
            return case1 || case2;
        }
    }

    // 如果经过ins以后，源点和目的点之间有夹角，且方向呈90°，则经过若干次后，必返回源点（被困住）
    public static boolean isRobotBounded(String ins) {
        // 初始位置
        Location start = new Location();
        Location cur = new Location();
        char[] str = ins.toCharArray();
        for (char command : str) {
            rule(command, cur);
        }
        return start.isBondage(cur);
    }

    public static void rule(char command, Location cur) {
        if (command == 'R') {
            if (cur.direction == Direction.UP) {
                cur.direction = Direction.RIGHT;
            } else if (cur.direction == Direction.DOWN) {
                cur.direction = Direction.LEFT;
            } else if (cur.direction == Direction.LEFT) {
                cur.direction = Direction.UP;
            } else if (cur.direction == Direction.RIGHT) {
                cur.direction = Direction.DOWN;
            }
        } else if (command == 'L') {
            if (cur.direction == Direction.UP) {
                cur.direction = Direction.LEFT;
            } else if (cur.direction == Direction.DOWN) {
                cur.direction = Direction.RIGHT;
            } else if (cur.direction == Direction.LEFT) {
                cur.direction = Direction.DOWN;
            } else if (cur.direction == Direction.RIGHT) {
                cur.direction = Direction.UP;
            }
        } else if (command == 'G') {
            if (cur.direction == Direction.UP) {
                cur.row++;
            } else if (cur.direction == Direction.DOWN) {
                cur.row--;
            } else if (cur.direction == Direction.LEFT) {
                cur.col--;
            } else if (cur.direction == Direction.RIGHT) {
                cur.col++;
            }
        }
        // do nothing
    }

}
