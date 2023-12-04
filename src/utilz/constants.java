package utilz;

public class constants {

    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int JUMP = 1;
        public static final int FALLING = 2;
        public static final int RUNNING = 3;

        public static int GetSpriteAmount(int player_action){
            switch (player_action){
                case RUNNING:
                    return 5;
                case IDLE:
                case JUMP:
                    return 4;
                case FALLING:
                default:
                    return 1;
            }
        }
    }
}
