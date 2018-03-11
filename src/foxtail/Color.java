package foxtail;

import foxtail.player.BlackPlayer;
import foxtail.player.Player;
import foxtail.player.WhitePlayer;

public enum Color {
    WHITE {
        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return whitePlayer;
        }
    },

    BLACK {
        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return blackPlayer;
        }
    };

    public abstract boolean isWhite();
    public abstract boolean isBlack();
    public abstract int getDirection();
    public abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
}
