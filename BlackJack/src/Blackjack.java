import java.util.Scanner;
import java.util.Random;

public class Blackjack {
    public static void main(String[] args){
//        インスタンス化
        Deck black1 = new Deck();
        Deal black2 = new Deal();
        Player black3 = new Player();


//        インスタンスのメソッド呼び出し
        black2.introduction();
        /* プレイヤーに手札を配る */
        black3.dist(black1);
        /* 手札のアナウンス */
        black3.Announcement_Dist();
        /* ディーラーに手札を配る */
        black2.dist(black1);
        /* 手札のアナウンス */
        black2.announcement_Dist();
        /* プレイヤーのアクション(hit) */
        black3.hit(black3,black1);
        if (21<black3.sum_p){
            System.out.println("バーストしました、あなたの負けです。");
            System.exit(0);
        }
        /* ディーラーのアクション */
        black2.action(black2,black1);
        if (black2.sum_d > 21) {
            System.out.println("ディーラーの手札がバーストしました、あなたの勝利です！");
            System.exit(0);
        }
        /*勝敗判定*/
        if(black3.sum_p<21 && black2.sum_d<21 && black3.sum_p>black2.sum_d){
            System.out.println("おめでとうございます！あなたの勝利です！いえぇーい!GG");
        }else {
            System.out.println("おーまいおー、あなたの負けです、GG");
        };
        if(black3.sum_p==21&&black2.sum_d==21){
            System.out.println("分けた、分けた! 引き分けだーーーー");
        }


    }
}



//山札のクラス、後程プレイヤーやディーラーから参照される
class Deck{
    //    属性
    int[][] yamafuda = new int [13][4];

    //    コンストラクタメソッド
    Deck(){
        for(int j = 0; j<4; j++){
            for(int i = 0; i<13; i++){
                yamafuda[i][j] = i+1;
            }
        }
    }
}



//ディーラークラス
class Deal{
    //    属性
    int[] hand_d;
    int sum_d;

    //    コンストラクタメソッド
    Deal(){
        hand_d = new int[10];
    }

    //    以下メソッド
    void introduction(){
        System.out.println("お初にお目にかかります、私はブラックジャック王国の第21代国王のブラクジャクである。");
        System.out.println("さて出会って5秒もたっておらぬが、いきなりあなたにこれから2枚のカードを配る、最終的に21に近いものが勝者となり次期国王になる、よろしいか。");
    }

    void dist(Deck black1){
//        山札クラスblack1インスタンスから無差別に参照し表示する
        int pick = 0;

        for(int c = 0 ; c<2; c++){
            int i = new Random().nextInt(13);
            int j = new Random().nextInt(4);
            /* デッキから同じカードがひかれないようにするif文 */
            if (black1.yamafuda[i][j]==0){
                i = new Random().nextInt(13);
                j = new Random().nextInt(4);}
            else{
                /* ジャック、クイーン、キングは10として扱うためのif文 */
                if (black1.yamafuda[i][j]>10){pick=10;}
                else{
                    pick = black1.yamafuda[i][j];
                    black1.yamafuda[i][j]=0;        /*pickしたものについては0を代入*/
                }
            }
            this.hand_d[c] = pick;
        }
        this.sum_d = this.hand_d[0]+this.hand_d[1];
    }

    void announcement_Dist(){
        System.out.println("ディーラー(ブラクジャク)にも手札が配られました。");
        System.out.println("ディーラー(ブラクジャク)の一枚目は"+this.hand_d[0]+"です。");
    }

    void action(Deal black2,Deck black1){
        System.out.println("次はディーラー(ブラクジャク)のアクションです。手札の合計が17以上になるまでhitし続けます。");
        System.out.println("ディーラーの合計点は"+sum_d+"でした。");
        int k = 2;
        while (sum_d<17) {
            int pick = 0;
            int i = new Random().nextInt(12);
            int j = new Random().nextInt(3);
            /* デッキから同じカードがひかれないようにするif文 */
            if (black1.yamafuda[i][j] == 0) {
                i = new Random().nextInt(12);
                j = new Random().nextInt(3);
            } else {
                /* ジャック、クイーン、キングは10として扱うためのif文 */
                if (black1.yamafuda[i][j] > 10) {
                    pick = 10;
                } else {
                    pick = black1.yamafuda[i][j];
                    black1.yamafuda[i][j]=0;        /*pickしたものについては0を代入*/
                }
            }
            this.hand_d[k] = pick;
            System.out.println("ディーラー(ブラクジャク)の手札に"+this.hand_d[k]+"が加えられました。");
            sum_d += this.hand_d[k];
            System.out.println("ディーラー(ブラクジャク)の合計点は"+this.sum_d);
            k++;

        }
    }
}



//プレイヤークラス
class Player {
    //属性
    int[] hand_p;
    int sum_p;

    //    コンストラクタメソッド
    Player() {
        hand_p = new int[10];
    }

    //    以下メソッド
    void dist(Deck black1) {
        /* 山札クラスblack1インスタンスから無差別に参照し表示する */
        int pick = 0;

        for (int c = 0; c < 2; c++) {
            int i = new Random().nextInt(12);
            int j = new Random().nextInt(3);
            if (black1.yamafuda[i][j] == 0) {        /* デッキから同じカードがひかれないようにするif文 */
                i = new Random().nextInt(12);
                j = new Random().nextInt(3);
            } else {
                if (black1.yamafuda[i][j] > 10) {        /* ジャック、クイーン、キングは10として扱うためのif文 */
                    pick = 10;
                } else {
                    pick = black1.yamafuda[i][j];
                    black1.yamafuda[i][j]=0;        /*pickしたものについては0を代入*/
                }
            }
            this.hand_p[c] = pick;
            System.out.println(this.hand_p[c]);
        }
        this.sum_p = this.hand_p[0]+this.hand_p[1];
    }

    void Announcement_Dist() {
        System.out.println("あなたに手札が配られました。");
        System.out.println("あなたの手札は" + this.sum_p + "です。");
    }

    void hit(Player black3, Deck black1) {

        while (21>=sum_p) {
            int k = 2;
            if (21==sum_p){
                System.out.println("おめでとうございます、ブラックジャックです！");
                break;
            }else {
                if (21 >= sum_p) {
                    System.out.println("hitする場合は1を、しない場合は0を押してください。");
                    System.out.println("press 1 or 0");
                    Scanner scan = new Scanner(System.in);
                    int flag = scan.nextInt();
                    System.out.println(flag);
                    /* プレイヤーがヒットするか否か聞くif文 */
                    if (!(flag == 1 || flag == 0)) {        /*いたずらで1でも0でもない数字を入れられた際*/
                        System.out.println("\"game_over\"いたずらしちゃだめよ");
                        System.exit(0);
                    } else {
                        if (flag == 1) {
                            int pick = 0;
                            int i = /*new Random().nextInt(12);*/
                            System.out.println("iは"+i);
                            int j = /*new Random().nextInt(3);*/
                            System.out.println("jは"+j);
                            /* デッキから同じカードがひかれないようにするif文 */
                            if (black1.yamafuda[i][j] == 0) {
                                i = new Random().nextInt(12);
                                j = new Random().nextInt(3);
                            } else {
                                if (black1.yamafuda[i][j] > 10) {        /* ジャック、クイーン、キングは10として扱うためのif文 */
                                    pick = 10;
                                } else {
                                    /*何故か1以外でもエースとして扱われてしまう…*/
                                    if(black1.yamafuda[i][j]==1){
                                        System.out.println("A(エース)を引きましたので\"1\"か\"11\"を選べます");
                                        Scanner select_eleve = new Scanner(System.in);
                                        int select = scan.nextInt();
                                        if(select==11){
                                            pick=11;
                                        }else{
                                            pick=1;
                                        }
                                    }
                                    pick = black1.yamafuda[i][j];
                                    black1.yamafuda[i][j]=0;        /*pickしたものについては0を代入してあげる*/
                                }
                            }
                            this.hand_p[k] = pick;
                            System.out.println("引いたカードは、山札[" + i + "]" + "[" + j + "]=" + pick);
                        } else {
                            System.out.println("スタンドします。");
                            System.out.println("最終的なあなたの手札は" + sum_p + "です。");
                            break;
                        }
                    }
                    sum_p += this.hand_p[k];
                    System.out.println("現在のあなたの合計点は"+sum_p+"です。");
                }
                k++;
            }
        }
    }
}
