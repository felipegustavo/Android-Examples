package br.ufba.connect3;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ImageView board;
    private ImageView piece1;
    private ImageView piece2;
    private ImageView piece3;
    private ImageView piece4;
    private ImageView piece5;
    private ImageView piece6;
    private ImageView piece7;
    private ImageView piece8;
    private ImageView piece9;
    private LinearLayout messageContainer;
    private TextView message;

    // turn = false means RED turn and turn = true means YELLOW turn.
    private boolean turn;
    private int[] boardMat;

    // Tells if the game has ended or not.
    private boolean end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting the layout elemnts
        board = (ImageView) findViewById(R.id.board);
        piece1 = (ImageView) findViewById(R.id.piece1);
        piece2 = (ImageView) findViewById(R.id.piece2);
        piece3 = (ImageView) findViewById(R.id.piece3);
        piece4 = (ImageView) findViewById(R.id.piece4);
        piece5 = (ImageView) findViewById(R.id.piece5);
        piece6 = (ImageView) findViewById(R.id.piece6);
        piece7 = (ImageView) findViewById(R.id.piece7);
        piece8 = (ImageView) findViewById(R.id.piece8);
        piece9 = (ImageView) findViewById(R.id.piece9);

        messageContainer = (LinearLayout) findViewById(R.id.messageContainer);
        message = (TextView) findViewById(R.id.message);

        // Initializing the game
        turn = false;
        end = false;
        boardMat = new int[9];
        Arrays.fill(boardMat, -1);
    }

    public void play(View view) {
        if (end) {
            return;
        }

        ImageView piece = (ImageView) view;
        // The position is already set, we do not need it clickable anymore.
        piece.setClickable(false);

        // Put the pieces on the board and change the turn to the next player.
        int pos = Integer.valueOf((String) piece.getTag());
        String pieceName;
        if (turn) {
            boardMat[pos] = 1;
            pieceName = "YELLOW";
            piece.setImageResource(R.drawable.yellow);
        } else {
            boardMat[pos] = 0;
            pieceName = "RED";
            piece.setImageResource(R.drawable.red);
        }
        turn = !turn;

        // Animation stuff.
        piece.setAlpha(0f);
        piece.animate().alpha(1f).setDuration(500);

        // Verify the winner.
        if (verifyHorizontal(pos, boardMat[pos]) || verifyVertical(pos, boardMat[pos]) || verifyDiagonal(pos, boardMat[pos])) {
            //Toast.makeText(this, String.format("The %s player won!!!", pieceName), Toast.LENGTH_LONG).show();
            showEndMessage("The "+pieceName+" player won!!!");
            end = true;
        }
    }

    public void tryAgain(View view) {
        end = false;
        messageContainer.setVisibility(View.INVISIBLE);
        turn = false;
        Arrays.fill(boardMat, -1);

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.mainLayout);
    }

    private void showEndMessage(String msg) {
        message.setText(msg);
        messageContainer.setVisibility(View.VISIBLE);
    }

    private boolean verifyHorizontal(int pos, int piece) {
        // Verify from the most left position.
        if (pos == 0 || pos == 3 || pos == 6) {
            if ((boardMat[pos] == boardMat[pos+1]) && (boardMat[pos] == boardMat[pos+2])) {
                return true;
            }
            return false;
        }

        // Verify from the most right position.
        if (pos == 2 || pos == 5 || pos == 8) {
            if ((boardMat[pos] == boardMat[pos-1]) && (boardMat[pos] == boardMat[pos-2])) {
                return true;
            }
            return false;
        }

        // Verify from the middle position.
        if ((boardMat[pos] == boardMat[pos+1]) && (boardMat[pos] == boardMat[pos-1])) {
            return true;
        }
        return false;
    }

    private boolean verifyVertical(int pos, int piece) {
        // Verify from the higher position.
        if (pos == 0 || pos == 1 || pos == 2) {
            if ((boardMat[pos] == boardMat[pos+3]) && (boardMat[pos] == boardMat[pos+6])) {
                return true;
            }
            return false;
        }

        // Verify from the shortest position.
        if (pos == 6 || pos == 7 || pos == 8) {
            if ((boardMat[pos] == boardMat[pos-3]) && (boardMat[pos] == boardMat[pos-6])) {
                return true;
            }
            return false;
        }

        // Verify from the middle position.
        if ((boardMat[pos] == boardMat[pos+3]) && (boardMat[pos] == boardMat[pos-3])) {
            return true;
        }
        return false;
    }

    private boolean verifyDiagonal(int pos, int piece) {
        if (pos == 0) {
            if ((boardMat[0] == boardMat[4]) && (boardMat[0] == boardMat[8])) {
                return true;
            }
            return false;
        }

        if (pos == 2) {
            if ((boardMat[2] == boardMat[4]) && (boardMat[2] == boardMat[6])) {
                return true;
            }
            return false;
        }

        if (pos == 6) {
            if ((boardMat[6] == boardMat[4]) && (boardMat[2] == boardMat[6])) {
                return true;
            }
            return false;
        }

        if (pos == 8) {
            if ((boardMat[8] == boardMat[4]) && (boardMat[0] == boardMat[8])) {
                return true;
            }
            return false;
        }

        return false;
    }

}